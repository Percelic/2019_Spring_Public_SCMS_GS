var energyInterval;
var realTimeInterval;
$(document).ready(function(){
	$('.leftmenu').find('div[data-sidebar-name="advancedPresentState"]').addClass("active");
	$('.leftmenu').find('li[data-sidebar-sub-name="realTime"]').addClass("active");
	setToday();
	var searchDate = $('.searchRec').find('input[name="searchDate"]').val();
	var today = getToday();
	if(searchDate == today){
		setGraph();	// 현재 날짜 그래프 조회
		realTimeEnergyList(today);
		setTrackerInfo(today);	//트래커 정보
		twinkle();
	}
	if(searchDate != today){
		setPastGraph();	// 지난 그래프 조회
		realTimeEnergyList(searchDate);
		setTrackerInfo(searchDate); //트래커 정보
	}
});

$('.energyListPageing').on('click', 'li[data-pageno]', function( event){
	var $this = $(this);
	var pageNo = $this.data('pageno');
	var energyListForm = $('#energyListForm');
	var searchDate = $('.searchRec').find('input[name="searchDate"]').val();
	energyListForm.find('input[name="pageNo"]').val(pageNo);
	
	realTimeEnergyList(searchDate);
});

// 마지막 조회시간
function setToday(){
	var date = new Date();
	var toDay = date.format('yyyy-MM-dd HH:mm:ss');
	$('.lastDate').text(toDay);
}

// 트래커 용량, 발전량
function setTrackerInfo(date) {
	var callback = function(data){
		var trackerId = $('.searchRec').find('select[name="trackerId"]').val() || '전체';
		var trackerCapacity = 0;
		var trackerEnergy = 0;
		for(var i = 0; i < data.length; i++){
			trackerCapacity += data[i].capacity;
			trackerEnergy += data[i].energy || 0;
		}
		$(".energyTodayRec .tracker").text(trackerId);								// 트래커 이름
		$(".energyTodayRec .tracker_capacity").text((trackerCapacity*1.05).toFixed(2));	// 트래커 용량
		$(".energyTodayRec .tracker_energy").text(trackerEnergy.toFixed(2));		// 트래커 발전량
	};
	getTrackerInfoData(callback, date);
}

// 트래커의 해당날짜 발전량, 용량을 DB에서 가져오는 함수
function getTrackerInfoData(callback, date){
	var startDate = date + ' ' + '00:00:00';
	var endDate = date + ' ' + '23:59:59';
	var hiddenTrackerGroupId = $('.searchRec').find('input[name="hiddenTrackerGroupId"]').val();
	var hiddenTrackerId = $('.searchRec').find('input[name="hiddenTrackerId"]').val();

	$.ajax({
		type:"GET",
		url : _cp + '/advancedPresentState/trackerInfo',
		data: {startDate : startDate, endDate : endDate, trackerGroupId : hiddenTrackerGroupId, trackerId : hiddenTrackerId},
		async: true,
		success:function(data) {
			if(callback){
				callback(data);
			}
		}, error:function(error) {

		}
	});
}

function realTimeEnergyList(date){
	var startDate = date + ' ' + '00:00:00';
	var endDate = date + ' ' + '23:59:59';
	
	var hiddenTrackerGroupId = $('.searchRec').find('input[name="hiddenTrackerGroupId"]').val();
	var hiddenTrackerId = $('.searchRec').find('input[name="hiddenTrackerId"]').val();
	var startDescNo = 0;
	
	var energyListForm = $('#energyListForm');
	var pageNo = energyListForm.find('input[name="pageNo"]').val();
	var pageSize = energyListForm.find('input[name="pageSize"]').val();
	
	$.ajax({
		type:"GET",
		url	: _cp + '/advancedPresentState/realTimeEnergyList/pageing',
		data: {trackerGroupId : hiddenTrackerGroupId, trackerId : hiddenTrackerId, startDate : startDate, endDate : endDate, pageNo : pageNo, pageSize : pageSize},
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
		url	: _cp + '/advancedPresentState/realTimeEnergyList',
		data: {trackerGroupId : hiddenTrackerGroupId, trackerId : hiddenTrackerId, startDate : startDate, endDate : endDate, pageNo : pageNo, pageSize : pageSize},
		success:function(data) {
			$(".tableBody").empty();
			var insertRow = false;
			if(startDescNo > 0) {
				for(var i=0; i<data.length; i++){
					
					if(!data[i].tracker_id || !data[i].tracker_name) continue;
					insertRow = true;
					var interter_id = data[i].inverter_id || '연결된 인버터 없음';
					var capacity = data[i].capacity || '연결된 용량 정보 없음';
					var row = 	"<tr>" +
									"<td class='txC ft12'>" + (startDescNo - i) + "</td>" +
									"<td>" + data[i].tracker_name + "</td>" +
									"<td>" + data[i].tracker_id + "</td>" +
									"<td>" + interter_id + "</td>" +
									"<td class='num'>" + data[i].instant_energy + "</td>" +
									"<td class='num'>" + capacity + "</td>" +
									"<td>" + data[i].manager + "</td>" +
									"<td class='num'>" + data[i].reg_date + "</td>" +
								"</tr>"; 
					
					$(".tableBody").append(row);
				}
			}
			
			if(!insertRow){
				var row = 	"<tr style='text-align: center;'>" +
					"<td colspan='8' >조회된 목록이 없습니다.</td>" +
					"</tr>"
					$(".tableBody").append(row);
			}
			
		}, error:function(error) {

		}
	});
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

// 선택한 날짜 일사량 DB에서 가져오는 함수
function getIlluminationData(day){
	var hiddenTrackerGroupId = $('.searchRec').find('input[name="hiddenTrackerGroupId"]').val();
	var hiddenTrackerId = $('.searchRec').find('input[name="hiddenTrackerId"]').val();
	var startDate = day + ' ' + '00:00:00';
	var endDate = day + ' ' + '23:59:59';
	var illuminationData;
	
	$.ajax({
		type:"GET",
		url	: _cp + '/advancedPresentState/illuminationGraph',
		data: {startDate : startDate, endDate : endDate, trackerGroupId : hiddenTrackerGroupId, trackerId : hiddenTrackerId, dateType : "typeHours"},
		async:false,
		success:function(result) {
			illuminationData = result;
		}, error:function(error) {

		}
	});

	return illuminationData;
}

//현재 발전중인 데이터 깜빡임 표시
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
	
	for(var i = 0; i<realTimeEnergyData.length; i++){
		var data = realTimeEnergyData[i].reg_date;	// 실시간 발전량 시간을 담는 변수
		var date = new Date(data);					// 시간을 Date type으로 변경
		var hour = date.getHours();					// 시간변수
		var minute = date.getMinutes();				// 분변수
		var key = hour * 6 + parseInt(minute/10);	// completeGraph 배열의 index 값으로 key 값을 만들어줌
		var value = realTimeEnergyData[i].instant_energy;	// 실시간 발전량 변수
		
		{
			var a = ((realEnergyData.get(key)||0) * 100)
					+ (value * 100);
			a = a / 100;
			realEnergyData.set(key, a);
		}
	}
	
	for(var i = 0; i < timeArr.length-1; i++){
		if(realEnergyData.has(i)){						// key값이 있다면
			completeGraph.push(realEnergyData.get(i));	// completeGraph 배열에 실시간 발전량을 넣어줌
		}else{
			completeGraph.push(null);				// key값이 없다면 null
		}
	}
	completeGraph.unshift(null);
	completeGraph.unshift(graphName);

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
	console.log(graphName, completeGraph);
	return completeGraph;
}

// 일사량
function getIlluminationGraph(timeArr, graphName, date){
	var illuminationData = getIlluminationData(date);
	var hourIlluminationData = [];
	var completeGraph = [];
	
  	for(var i = 0; i < 24; i++){
		for(var j = 0; j < illuminationData.length; j++){
			if(illuminationData[j].reg_date == i){
				hourIlluminationData[i+1] = Math.round(illuminationData[j].average_illumination);
			}
		}
		if(hourIlluminationData[i] == undefined){
			hourIlluminationData[i] = null;
		}
	}  

	for(var i= 0; i < timeArr.length-1; i++){
		if(i%6 == 0){
			completeGraph.push(hourIlluminationData[i/6]);
		}else{
			completeGraph.push(null);
		}
	}
	completeGraph.unshift(graphName);
	console.log(graphName, completeGraph);
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

// 현재 날짜 그래프
function setGraph(){
	var today = getToday();
	var yesterday = getYesterday();
	var xLine = getXLine('x');
	var realTimeEnergyGraph = getRealTimeEnergyGraph(xLine, '현재 출력');
	var todayEnergyGraph = getEnergyGraph(xLine, '시간당 발전량', today);
	var yesterDayEnergyGraph = getEnergyGraph(xLine, '전일 시간당 발전량', yesterday);
	var todayIlluminationGraph = getIlluminationGraph(xLine, '일사량', today);
	var yesterDayIlluminationGraph = getIlluminationGraph(xLine, '전일 일사량', yesterday);
	
	var chart = c3.generate({
		bindto: '#graph02',
		x: 'x',
		data: {
			columns: [
					realTimeEnergyGraph,
					todayEnergyGraph,
					yesterDayEnergyGraph,
					todayIlluminationGraph,
					yesterDayIlluminationGraph
				],
				
				types: {
					'시간당 발전량': 'bar',
					'현재 출력': 'line',
					'전일 시간당 발전량': 'bar'
				},
				axes: {
					'일사량': 'y2',
					'전일 일사량': 'y2'
				},
				colors: {
					'시간당 발전량': '#5B9BD5',
					'현재 출력': '#FF0E0E',
					'전일 시간당 발전량': '#FFC000',
					'일사량': '#00B0F0',
					'전일 일사량': '#002060'
				},
		},
		line: {
			connectNull: true
		},
		legend: {
			grmCustom: {
				layerStyle: {
					right: '7%',
					top: '20px',
				}
			}
//	    	position: 'inset',
//	    	inset: {
//	    		anchor: 'top-right',
//	    		step: 3
//	    	}
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
				tick:{
					culling: false,
					format: function(value){
						if(value%6==0){
							return value/6;
						}
					}
				},
				padding: {
					left: 5,
					right: 5
				}
			},
			y : {
				padding: {bottom: 0},
				tick: {
					format: d3.format("KW")
				}
			},
			y2: {
				padding: {bottom: 0},
				show: true,
				tick: {
					format: d3.format("W/m^2")
				},
				max: 2000,
				min: 0
			}
		},
		point: {
			r: 2.5
		}
	});
	
	function toggle(id) {
        chart.toggle(id);
    }
}

// 지난 그래프
function setPastGraph(){
	var searchDate = $('.searchRec').find('input[name="searchDate"]').val();
	var xLine = getXLine('x');
	var todayEnergyGraph = getEnergyGraph(xLine, '시간당 발전량', searchDate);
	var todayIlluminationGraph = getIlluminationGraph(xLine, '일사량', searchDate);
	
	var chart = c3.generate({
		bindto: '#graph02',
		x: 'x',
		data: {
			columns: [
					todayEnergyGraph,
					todayIlluminationGraph,
				],
				
				types: {
					'시간당 발전량': 'bar',
				},
				axes: {
					'일사량': 'y2',
				},
				colors: {
					'시간당 발전량': '#5B9BD5',
					'일사량': '#00B0F0',
				},
		},
		line: {
			connectNull: true
		},
		legend: {
	    	position: 'inset',
	    	inset: {
	    		anchor: 'top-right',
	    		step: 2
	    	}
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
				tick:{
					culling: false,
					format: function(value){
						if(value%6==0){
							return value/6;
						}
					}
				},
				padding: {
					left: 5,
					right: 5
				}
			},
			y : {
				padding: {bottom: 0},
				tick: {
					format: d3.format("KW")
				}
			},
			y2: {
				padding: {bottom: 0},
				show: true,
				tick: {
					format: d3.format("W/m^2")
				}
			}
		},
		point: {
			r: 2.5
		}
	});
}

function setPageForm(paging){
	var divPaging = $('.energyListPageing');
	var html = "";
	
	html += "<ul class=''>";
	for (var i=paging.sheetStartPageNo; i<=paging.sheetEndPageNo; i++){
   			
		if(i == paging.pageNo){
			html += "<li class='pageNumBtn active' data-pageno='" + i + "'>";
		} else {
			html += "<li class='pageNumBtn' data-pageno='" + i + "'>";
		}
		
		html += "<a href='#' >" + i + "</a>";
		html += "</li>";
		
	}
	html += "</ul>";
	
	divPaging.empty();
	divPaging.append(html);

}