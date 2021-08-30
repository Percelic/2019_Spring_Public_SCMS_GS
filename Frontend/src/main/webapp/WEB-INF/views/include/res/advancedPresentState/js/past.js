$(document).ready(function(){
	$('.leftmenu').find('div[data-sidebar-name="advancedPresentState"]').addClass("active");
	
	getTotalEnergy();
	setGraph();
});

$(".btnTypeRec .btn").on('click', function(){
	var type = $(this).attr('data-type');
	$(".btnTypeRec .btn").removeClass('active');
	$(this).addClass('active');
	
	if(type == "day"){
		console.log("day");
	}else if(type == "time"){
		console.log("time");
	}
	
	getTotalEnergy();
	setGraph();
});

function setGraphFormatDate(){
	
	var dateArr = new Array();

	var strStartDate = $('.searchRec').find('input[name="startDate"]').val();
	var strEndDate = $('.searchRec').find('input[name="endDate"]').val();
	
	var startDate = new Date(strStartDate) ;
    var endDate = new Date(strEndDate) ;
    var interval  = endDate.getTime() - startDate.getTime() ;
    var day = 1000*60*60*24;
    var forLen = interval/day;
	dateArr[0] = 'x';
	for (var i=0; i<=forLen; i++){
		
		var setSeartDate = new Date(strStartDate);
		var day = strStartDate.split('-')[2];
		var setdate = new Date(setSeartDate.setDate(i+Number(day)));
		var strDate = setdate.getFullYear() + '-' + leadingZeros((Number(setdate.getMonth()) + 1), 2) + '-' + leadingZeros(setdate.getDate(), 2);
		
		dateArr[i+1] = strDate;
		
	}
    
	return dateArr;
}


function leadingZeros(n, digits) {
	var zero = '';
	n = n.toString();

	if (n.length < digits) {
		for (i = 0; i < digits - n.length; i++)
			zero += '0';
	}
	return zero + n;
}

// DB에서 일 발전량 정보 가져오기
function getEnergyGraph(callback){
	var startDate = $('.searchRec').find('input[name="startDate"]').val() + " " + "00:00:00";
	var endDate = $('.searchRec').find('input[name="endDate"]').val() + " " + "23:59:59";
	var hiddenTrackerGroupId = $('.searchRec').find('input[name="hiddenTrackerGroupId"]').val();
	var hiddenTrackerId = $('.searchRec').find('input[name="hiddenTrackerId"]').val();
	var dateType = "typeDays";

	$.ajax({
		type:"GET",
		url	: _cp + '/advancedPresentState/energyGraph',
		data: {startDate : startDate, endDate : endDate, trackerGroupId : hiddenTrackerGroupId, trackerId : hiddenTrackerId, dateType : dateType},
		async:false,
		success:function(data) {
			if(callback){
				callback(data);
			}
		}, 
		error:function(error) {
		}
	});
}

function getIlluminationData(callback){
	var startDate = $('.searchRec').find('input[name="startDate"]').val() + " " + "00:00:00";
	var endDate = $('.searchRec').find('input[name="endDate"]').val() + " " + "23:59:59";
	var hiddenTrackerGroupId = $('.searchRec').find('input[name="hiddenTrackerGroupId"]').val();
	var hiddenTrackerId = $('.searchRec').find('input[name="hiddenTrackerId"]').val();
	var dateType = "typeDays";
	
	$.ajax({
		type:"GET",
		url	: _cp + '/advancedPresentState/illuminationGraph',
		data: {startDate : startDate, endDate : endDate, trackerGroupId : hiddenTrackerGroupId, trackerId : hiddenTrackerId, dateType : dateType},
		async:false,
		success:function(data) {
			if(callback){
				callback(data);
			}
		}, 
		error:function(error) {
		}
	});
}

function getWeatherData(callback){
	var startDate = $('.searchRec').find('input[name="startDate"]').val() + " " + "00:00:00";
	var endDate = $('.searchRec').find('input[name="endDate"]').val() + " " + "23:59:59";
	var hiddenTrackerGroupId = $('.searchRec').find('input[name="hiddenTrackerGroupId"]').val();
	var hiddenTrackerId = $('.searchRec').find('input[name="hiddenTrackerId"]').val();
	var dateType = "typeDays";
	
	$.ajax({
		type:"GET",
		url	: _cp + '/advancedPresentState/weatherGraph',
		data: {startDate : startDate, endDate : endDate, trackerGroupId : hiddenTrackerGroupId, trackerId : hiddenTrackerId, dateType : dateType},
		async:false,
		success:function(data) {
			if(callback){
				callback(data);
			}
		}, 
		error:function(error) {
		}
	});
}

// 일 발전량
function setGraphData(){
	var sumEnergyArr = new Array('일 발전량');
	
	var callback = function(data){
		var dateArr = setGraphFormatDate();
		for(var i=1; i<dateArr.length; i++) {
			for(var j=0; j<data.length; j++) {
				var regDate = data[j].reg_date;
				if(regDate == dateArr[i]){
					sumEnergyArr[i] = data[j].sum_energy;
					break;
				} 
			}
			
			if(sumEnergyArr[i] == undefined){
				sumEnergyArr[i] = 0;
			}
		}
	};
	getEnergyGraph(callback);
	return sumEnergyArr;
}

// 일사량
function getIlluminationGraph(){
	var weatherArr = new Array('일 일사량');
	
	var callback = function(data){
		var dateArr = setGraphFormatDate();
		for(var i=1; i<dateArr.length; i++) {
			for(var j=0; j<data.length; j++) {
				var regDate = data[j].reg_date;
				if(regDate == dateArr[i]){
					weatherArr[i] = Math.round(data[j].sum_illumination);
					break;
				} 
			}
			
			if(weatherArr[i] == undefined){
				weatherArr[i] = null;
			}
		}
	};
	
	getIlluminationData(callback);
	return weatherArr;
}



function getWindSpeedGraph() {
	var weatherArr = new Array('일 풍속');
	
	var callback = function(data) {
		var dateArr = setGraphFormatDate();
		for(var i=1; i<dateArr.length; i++) {
			for(var j=0; j<data.length; j++) {
				var regDate = data[j].reg_date;
				if(regDate == dateArr[i]){
					weatherArr[i] = data[j].windSpeed;
					break;
				}
			}
			if(weatherArr[i] == null) {
				weatherArr[i] = null;
			}
		}
	};
	
	getWeatherData(callback);
	return weatherArr;
}

function getTempGraph() {
	var weatherArr = new Array('일 기온');
	
	var callback = function(data) {
		var dateArr = setGraphFormatDate();
		for(var i=1; i<dateArr.length; i++) {
			for(var j=0; j<data.length; j++) {
				var regDate = data[j].reg_date;
				if(regDate == dateArr[i]){
					weatherArr[i] = data[j].temp;
					break;
				}
			}
			if(weatherArr[i] == null) {
				weatherArr[i] = null;
			}
		}
	};
	
	getWeatherData(callback);
	return weatherArr;
}

// 기간 내 트래커 전체의 총 발전량
function getTotalEnergy(){
	var callback = function(data){
		var totalEnergy = 0;
		for(var i=0; i<data.length; i++){
			totalEnergy+=data[i].sum_energy;
		}
		$(".energyTx").text(totalEnergy.toFixed(2));
	}
	var startDate = $('.searchRec').find('input[name="startDate"]').val();
	var endDate = $('.searchRec').find('input[name="endDate"]').val();
	$(".startDate").text(startDate);
	$(".endDate").text(endDate);
	var energyGraph = getEnergyGraph(callback);
}

// 발전량 그래프
function setGraph(){
	// 잘못된 날짜 선택
	var startDate = $('.searchRec').find('input[name="startDate"]').val();
	var endDate = $('.searchRec').find('input[name="endDate"]').val();
	if(endDate==startDate || startDate>endDate){
		alert('날짜 선택 오류');
	}

	var GraphFormatDate = setGraphFormatDate();	// 동적 날짜
	var energyGraph = setGraphData();	// 일 발전량 데이터
	//var illuminationGraph = setGraphWeatherData();
	//var illuminationGraph = getIlluminationGraph();
	var temp = getTempGraph(temp);
	var windSpeed = getWindSpeedGraph(windSpeed);
	
	var chart = window.chart = c3.generate({
	 	bindto: '#graph02',
	    data: {
	    	x: 'x',
	        columns: [
	        	GraphFormatDate,
	        	energyGraph,
	        	temp,
	        	//illuminationGraph,
	        	windSpeed,
	        ],
	        types: {
	        	'일 발전량': 'bar',
	        	'일 기온': 'line',
	        	//'일 일사량': 'line',
	        	'일 풍속': 'line',
	        },
	        axes: {
	        	'일 일사량': 'y2'
	        },
	        colors: {
	        	'일 발전량' : '#1f77b4',
	        	'일 기온' : '#FFC000',
	        	//'일 일사량' : '#A9D18E',
	        	'일 풍속' : '#00b0f0',
	        },
	    },
	    legend: {
	    	grmCustom: true
//	    	position: 'inset',
//	    	inset: {
//	    		anchor: 'top-right',
//	    		step: 2
//	    	}
	    },
	    tooltip: {
	        format: {
	            value: function (value, ratio, id) {
	            	var format = d3.format(',');
	            	return format(value.toFixed(2));
	            }
	        }
	    },
	    axis : {
			x: {
	            type: 'timeseries',
	            tick: {
	                multiline: false,
	                format: '%Y-%m-%d'
	            }
	        },
	        y : {
				padding: {bottom: 0},
			},
			y2: {
				padding: {bottom: 0},
				
			}
		}, point: {
			r: 3
		}
	});
}