var _data_timer;
var energyInterval;
var realTimeInterval;

$(document).ready(function(){
	getData();
	getRealDate();
	$('.leftmenu').find('div[data-sidebar-name="monitoring"]').addClass("active");
});

setInterval(function(){
	getRealDate();
}, 1000);

// 시계
function getRealDate(){
	var date = new Date();
	var realDate = date.format('yyyy-MM-dd');
	var realTime = date.format('HH:mm:ss');
	$('.realDate').text(realDate);
	$(".realTime").text(realTime);
}

// 데이터 조회
function getData(timer) {
	clearTimeout(_data_timer);
	if(timer !== false){
		//  timer
		_data_timer = setTimeout(function(){
			getData();
		}, 1000 * 300);
	}
	
	setAlramTable();
	setToday();	// 최근조회 시간
	setGraph();	// 실시간, 시간당, 전일 시간당 발전량 그래프
	setInverterAccuEnergy();	// 누적발전량, 금일발전량, 전일발전량, 현재발전량, 도넛차트
	twinkle();
	
	$(".energyCount[data-current-energy]").text((realTimeEnergyGraph.lastEnergy).toFixed(2));
}

//  조회버튼
$('.contentwrap .titleRec .btnRefresh').on('click', function(e){
	getData();
});

//  상세 알람 조회 버튼
$('.btnRec').on('click', '.alarmMoveBtn', function(){
	var loginRole = $('.searchRec').find('input[name="loginRole"]').val();
	
	if("tracker_admin" == loginRole) {
		location.href = _cp + "/groupPresentState/tracker";
	} else {
		location.href = _cp + "/groupPresentState/group";
	}
	
//  상세 발전량 조회 버튼
}).on('click', '.advancedMoveBtn', function(){
	location.href = _cp + "/advancedPresentState/realTime";
});

// 총 누적발전량, 금일 발전량, 전일 발전량, 현재발전량 배치
var trackerCapacity;
function setInverterAccuEnergy(){
	//var inverterAccuEnergy = getInverterAccuEnergy();
	var callback = function(inverterAccuEnergy){
		// 누적발전량
		var totalInverterEnergy = inverterAccuEnergy.TOTAL_INVERTER_ENERGY || 0;
		// 금일발전량
		var todayEnergy = inverterAccuEnergy.TODAY_ENERGY || 0;
		// 전일발전량
		var yesterdayEnergy = inverterAccuEnergy.YESTERDAY_ENERGY || 0;
		// 현재발전량
		var currentEnergy = inverterAccuEnergy.CURRENT_ENERGY || 0;
		// 총용량
		var totalCapacity = inverterAccuEnergy.TOTAL_CAPACITY || 0;
		// 트래커용량
		trackerCapacity = inverterAccuEnergy.TRACKER_CAPACITY || 0;
		// 트래커용량 비율
		var trackerRatio = trackerCapacity/totalCapacity;		

		// 누적 발전량(누적발전량 * 트래커비율)
		$(".totalVolume .energyTxRec .energyTx").text((totalInverterEnergy*trackerRatio).toFixed(2));
		// 금일 발전량
		$(".energyCount[data-today-energy]").text(todayEnergy.toFixed(2));
		// 전일 발전량
		$(".energyCount[data-yesterday-energy]").text(yesterdayEnergy.toFixed(2));
		// 현재 발전량(최근 5분 발전량) + 그래프(수정이 필요해 보임)
//		$(".energyCount[data-current-energy]").text((currentEnergy).toFixed(2));
		
//		////
//		// 요청사항
//		var realTimeEnergyData = getRealTimeEnergyData();
//		var realEnergyData = new Map();
//		var completeGraph = [];
//		
//		for(var i = 0; i<realTimeEnergyData.length; i++){
//			var data = realTimeEnergyData[i].reg_date;
//			var date = new Date(data);
//			var hour = date.getHours();	
//			var minute = date.getMinutes();	
//			var key = hour * 6 + parseInt(minute/10);
//			var value = realTimeEnergyData[i].instant_energy;
//			var a = ((realEnergyData.get(key)||0) * 100)
//					+ (value * 100);
//			a = a / 100;
//			realEnergyData.set(key, a);
//		}
//		
//		var date = new Date();
//		var hour = date.getHours();	
//		var minute = date.getMinutes();	
//		var key = hour * 6 + parseInt(minute/10);
//		currentEnergy = realEnergyData.get(key) || 0;
//		
//		$(".energyCount[data-current-energy]").text((currentEnergy).toFixed(2));
//		////
//		
		// 현재발전량 도넛차트
		if(realTimeEnergyGraph){
			var p = realTimeEnergyGraph.lastEnergy / trackerCapacity * 100;
			setSvgEnergy( Math.min(p, 99.999999999) );
		}
	};
	getInverterAccuEnergy(callback);
}

// DB에서 금일 발전량, 전일발전량, 현재발전량, 트래커용량을 가져옴
function getInverterAccuEnergy(callback) {
	var hiddenTrackerGroupId = $('.searchRec').find('input[name="hiddenTrackerGroupId"]').val();
	var hiddenTrackerId = $('.searchRec').find('input[name="hiddenTrackerId"]').val();
	// 금일에너지, 전일에너지, 트래커용량, 현재발전량, 총용량
	var inverterAccuEnergyData;
	$.ajax({
		type:"GET",
		url	: _cp + '/inverter/inverterAccuEnergy',
		data: {trackerGroupId : hiddenTrackerGroupId, trackerId : hiddenTrackerId},
		async:true,
		success:function(data) {
			if(callback){
				callback(data);
			}
		}, error:function(error) {

		}
	});	
	return inverterAccuEnergyData;
}

// 현재발전량
function setSvgEnergy(percentage){
	  $('.js-graphItem').each(function(index, element){
		  $(this).attr('data-val', percentage);
		  this.text = new Graph(index, element, '#donutSvg');
	  });
	  if(typeof percentage !== 'number'){
		  percentage = parseFloat(percentage, 10);
	  }
	  if(isNaN(percentage)) {
		  percentage = 0;
	  }
	  
	  $(".donutEnergyPercentage").text(percentage.toFixed(2)+'%');
}

// 최근 조회시간
function setToday(){
	var date = new Date().format('yyyy-MM-dd HH:mm:ss');
	$('.lastDate').text(date);
}

// 실시간 발전량 DB에서 가져오는 함수
function getRealTimeEnergyData(){
	var hiddenTrackerGroupId = $('.searchRec').find('input[name="hiddenTrackerGroupId"]').val();
	var hiddenTrackerId = $('.searchRec').find('input[name="hiddenTrackerId"]').val();
	var today = getToday();
	var realTimeEnergyData;

	$.ajax({
		type:"GET",
		url	: _cp + '/advancedPresentState/realTimeEnergyGraph',
		data: {today : today, trackerGroupId : hiddenTrackerGroupId, trackerId : hiddenTrackerId},
		async:false,
		success:function(data) {
			realTimeEnergyData = data;
		}, error:function(error) {

		}
	});

	return realTimeEnergyData;
}

// 선택한 날짜 발전량 DB에서 가져오는 함수
function getEnergyData(day){
	var hiddenTrackerGroupId = $('.searchRec').find('input[name="hiddenTrackerGroupId"]').val();
	var hiddenTrackerId = $('.searchRec').find('input[name="hiddenTrackerId"]').val();
	var startDate = day + ' ' + '00:00:00';
	var endDate = day + ' ' + '23:59:59';
	var energyData;

	$.ajax({
		type:"GET",
		url	: _cp + '/advancedPresentState/energyGraph',
		data: {startDate : startDate, endDate : endDate, trackerGroupId : hiddenTrackerGroupId, trackerId : hiddenTrackerId, dateType : "typeHours"},
		async:false,
		success:function(result) {
			energyData = result;
		}, error:function(error) {

		}
	});	

	return energyData;
}

// 현재 발전중인 데이터 깜빡임 표시
function twinkle(){
	var date = new Date();
	var hour = date.getHours();	
	var minute = date.getMinutes();	
	var key = hour * 6 + parseInt(minute/10) + 1;
	var key2 = (hour+1) * 6;
	clearInterval(realTimeInterval);
	clearInterval(energyInterval);
	realTimeInterval = setInterval(function(){
        $('.c3-shapes-현재-출력 .c3-shape.c3-shape-'+ key +'.c3-circle.c3-circle-'+ key +'').toggle();
    }, 250);
	energyInterval = setInterval(function(){
		if($('.c3-shapes-시간당-발전량 .c3-shape.c3-shape-'+ key2 +'.c3-bar.c3-bar-'+ key2 +'').css('opacity') == '1') {
			$('.c3-shapes-시간당-발전량 .c3-shape.c3-shape-'+ key2 +'.c3-bar.c3-bar-'+ key2 +'').css('opacity', 0.5);
		} else {
			$('.c3-shapes-시간당-발전량 .c3-shape.c3-shape-'+ key2 +'.c3-bar.c3-bar-'+ key2 +'').css('opacity', 1)
		}
	}, 1000);
}

// 실시간 발전량
function getRealTimeEnergyGraph(timeArr, graphName){
	var realTimeEnergyData = getRealTimeEnergyData();
	var realEnergyData = new Map();
	var completeGraph = [];
	
	var lastEnergy;
	for(var i = 0; i<realTimeEnergyData.length; i++){
		var data = realTimeEnergyData[i].reg_date;
		var date = new Date(data);
		var hour = date.getHours();	
		var minute = date.getMinutes();	
		var key = hour * 6 + parseInt(minute/10);
		var value = realTimeEnergyData[i].instant_energy;
		{
			var a = ((realEnergyData.get(key)||0) * 100)
					+ (value * 100);
			a = a / 100;
			realEnergyData.set(key, a);
		}
		lastEnergy = value;
		//realEnergyData.set(key, (realEnergyData.get(key)||0)+value);
	}

	//var lastEnergy;
	for(var i = 0; i < timeArr.length-1; i++){
		if(realEnergyData.has(i)){
			completeGraph.push(realEnergyData.get(i));
			//lastEnergy = realEnergyData.get(i);
		}else{
			completeGraph.push(null);
		}
	}
	
	completeGraph.unshift(null);
	completeGraph.unshift(graphName);
	completeGraph.lastEnergy = lastEnergy;
	return completeGraph;
}

// 시간당 발전량
function getEnergyGraph(timeArr, graphName, date){
	var energyData = getEnergyData(date);
	var hourEnergyData = [];
	var completeGraph = [];

	for(var i = 0; i < 24; i++){
		for(var j = 0; j < energyData.length; j++){
			if(energyData[j].reg_date == i){
				hourEnergyData[i+1] = energyData[j].sum_energy;
			}
		}
		if(hourEnergyData[i] == undefined){
			hourEnergyData[i] = 0;
		}
	}

	for(var i = 0; i < timeArr.length-1; i++){
		if(i%6 == 0){
			completeGraph.push(hourEnergyData[i/6]);
		}else{
			completeGraph.push(null);
		}
	}
	completeGraph.unshift(graphName);

	return completeGraph;
}

// X축 시간 만들기
function getXLine(data){
	var hours = 24;
	var minutes = 60;
	var timeArr = [data];

	for(var i=0; i<hours; i++){
		for(var j=0; j<minutes; j++){
			if(j%10 == 0){
				timeArr.push(j);
			}
		}
	}

	return timeArr;
}

// 오늘 날짜 구하기
function getToday(){
	var date = new Date();
	var toDay = date.format('yyyy-MM-dd');
	return toDay;
}

// 어제 날짜 구하기
function getYesterday(){
	var date = new Date();
	var yesterDay = date.addDate(-1).format('yyyy-MM-dd');
	return yesterDay;
}

// 실시간, 시간당, 전일 발전량 그래프
var realTimeEnergyGraph;
function setGraph(){
	var today = getToday();
	var yesterday = getYesterday();
	var xLine = getXLine('x');
	realTimeEnergyGraph = getRealTimeEnergyGraph(xLine, '현재 출력');
	var todayEnergyGraph = getEnergyGraph(xLine, '시간당 발전량', today);
	var yesterDayEnergyGraph = getEnergyGraph(xLine, '전일 시간당 발전량', yesterday);
	
	var chart = c3.generate({
		bindto: '#graph02',
		x: 'x',
		data: {
			columns: [
					realTimeEnergyGraph,
					todayEnergyGraph,
					yesterDayEnergyGraph
				],
				types: {
					'시간당 발전량': 'bar',
					'전일 시간당 발전량': 'bar',
					'현재 출력': 'line',
				},
				colors: {
					'시간당 발전량': '#5B9BD5',
					'현재 출력': '#FF0E0E',
					'전일 시간당 발전량': '#FFC000'
				},
		},
		legend: {
			grmCustom: {
				layerStyle: {
					position: 'initial',
					width: '100%',
					'text-align': 'center',
					border: 'none'
				}
			}
		},
		line: {
			connectNull: true
		},
		bar: {
			width: 10
		},
		grid: {
			y: {
				show: true
			}
		}, 
		axis : {
			x: {
				label: '시간',
				type: 'timeless',
				tick: {
					culling: false,
					format: function(value){
						if(value%6==0){
							return value/6;
						}
					}
				}
			},
			y : {
				tick: {
					format: d3.format("KW")
				}
			}
		}, point: {
			r: 2.5
		}
	});
	console.log(chart);
}





$('.alarmListPageing').on('click', 'li[data-pageno]', function( event){
	var $this = $(this);
	var pageNo = $this.data('pageno');
	var alarmTableForm = $('#alarmTableForm');
	alarmTableForm.find('input[name="pageNo"]').val(pageNo);

	setAlramTable();

});


function setAlramTable(){
	var hiddenTrackerGroupId = $('.searchRec').find('input[name="hiddenTrackerGroupId"]').val();
	var hiddenTrackerId = $('.searchRec').find('input[name="hiddenTrackerId"]').val();
	
	
	var alarmTableForm = $('#alarmTableForm');
	var alarmGrade = alarmTableForm.find('select[name="alarmGrade"]').val();
	var pageNo = alarmTableForm.find('input[name="pageNo"]').val();
	var pageSize = alarmTableForm.find('input[name="pageSize"]').val();
	var startDescNo = 0;
	
	$.ajax({
		type:"GET",
		url	:_cp + "/groupPresentState/alarmList/pageing",
		data: {trackerGroupId : hiddenTrackerGroupId, trackerId : hiddenTrackerId, alarmGrade : alarmGrade, pageNo : pageNo, pageSize : pageSize},
		async:false,
		success:function(data){
			startDescNo = data.startDescNo;
			setPageForm(data);
			
		}, error:function(error){
			alert("검색에 실패 하였습니다.");
		}
	});
	
	
	
	$.ajax({
		type:"GET",
		url	:_cp + "/groupPresentState/alarmList",
		data: {trackerGroupId : hiddenTrackerGroupId, trackerId : hiddenTrackerId, alarmGrade : alarmGrade, pageNo : pageNo, pageSize : pageSize},
		success:function(list){
			
			
			var length = list.length;
			
			if(length > 0){
				$(".alertRec").addClass('active');
			}else{
				$(".alertRec").removeClass('active');
			}
			
			var $list = $(".alarmList");
			var $rowSample = "<tr style='text-align: center;'>" + 
								"<td colspan='5'>검색 결과가 없습니다.</td>" +
							 "</tr>";
			
			
			$list.find('tbody').empty();
			if(length < 1){
				
				$list.find('tbody').append($rowSample);
			}
			
			for(var i=0; i<length; i++){
				var attentionClass = "";
				var item = list[i];
				
				if(item.alarm_grade == "1"){
					attentionClass = "attention";
				}else if(item.alarm_grade == "0"){
					attentionClass = "warning";
				}
				
				var $rowSample = "<tr class='" + attentionClass + "'>" +
									"<td>"+ (startDescNo - i) + "</td>" +
									"<td>"+ item.tracker_id + "</td>" +
									"<td>"+ item.alarm_type + "</td>" +
									"<td>"+ item.alarm_grade + "</td>" +
									"<td>"+ item.upt_date + "</td>" +
								 "</tr>";				
				
				
				$list.find('tbody').append($rowSample);
			}
			
		}, error:function(error){
			alert("검색에 실패 하였습니다.");
		}
	});
}


function setPageForm(paging){
	var divPaging = $('.alarmListPageing');
	var html = "";
	
	html += "<ul class=''>";
	for (var i=paging.sheetStartPageNo; i<=paging.sheetEndPageNo; i++){
   			
		if(i == paging.pageNo){
			html += "<li class='pageNumBtn active' data-pageno='" + i + "'>";
		} else {
			html += "<li class='pageNumBtn' data-pageno='" + i + "'>";
		}
		
		html += "<a href='#' >" + i + "</a>"
		html += "</li>"
		
	}
	html += "</ul>";
	
	divPaging.empty();
	divPaging.append(html);

}

