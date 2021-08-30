$(document).ready(function(){
	$('.leftmenu').find('div[data-sidebar-name="groupPresentState"]').addClass("active");

	$('.remote-start-group').hide();
	$('.remote-manual-group').hide();
	
	init();
	
	var trackerGroupId = $('#trackerGroupSelForm').find('select[name="trackerGroupId"]').val();
	setTrackerSelBox(trackerGroupId);
	
	setInterval(init, intervalTime);
	
});
var firstInit = false;

function init(){
	
	setAlramTable();
	setTrackerBatch();
	
	
}

var intervalTime = 10000;
var isRemoteStart = false;
var remoteClick = false;
var trackerBatchStart = false;
var tmpInterval;

$(window).on('click', function(){
	$('.trackerRec').find('.showInfo').removeClass('showInfo');
	$('.trackerRec').find('.trackerInfo').hide();
});

// 제어시작
$('.remote-banner').on('click', '.remote-start-btn', function(){
	$('.remote-start-group').show();
	$('.remote-stop-group').hide();
	isRemoteStart = true;
	$('.trackerRec').find('.trackerInfo').hide();
	$('.trackerEnergyRec').show();
	$('.trackerMoveRec').hide();
// 제어중지	
}).on('click', '.remote-stop-btn', function(){
	$('.remote-start-group').hide();
	$('.remote-stop-group').show();
	isRemoteStart = false;
	$('.trackerRec').find('.active').removeClass('active');
	$('.trackerEnergyRec').show();
	$('.trackerMoveRec').hide();
	
// 자동 모드	
}).on('click', '.auto_btn', function(){
	$('.remote-manual-group').hide();
	$('.remote-auto-group').show();
	$('.btnRec .auto_btn').addClass('active');
	$('.btnRec .manual_btn').removeClass('active');
	$('.trackerEnergyRec').show();
	$('.trackerMoveRec').hide();
// 수동 모드	
}).on('click', '.manual_btn', function(){
	$('.trackerEnergyRec').show();
	$('.trackerMoveRec').hide();
	
	$('.trackerAllCheckGroup').find('button[name="trackerAllCheck"]').removeClass('active');
	$('.trackerAllCheckGroup').find('button[name="trackerAllCheck"]').text('전체 선택');
	$('.trackerRec').find('.trackerBox').removeClass('active');
	
	$('.remote-manual-group').show();
	$('.remote-auto-group').hide();
	$('.btnRec .auto_btn').removeClass('active');
	$('.btnRec .manual_btn').addClass('active');
});

// 트래커 선택
$('.trackerRec').on('click', 'div[data-trackerid]', function(e){
	e.stopPropagation();
	var $this = $(this);
	var trackerId = $this.data('trackerid');
	
	if(isRemoteStart == true) {
//		var activeTrackerId = $('.trackerRec').find('.active').data('trackerid');
		if($this.hasClass('active')){
			$this.removeClass('active');
			$this.find('.trackerEnergyRec').show();
			$this.find('.trackerMoveRec').hide();
		}else{
			$this.addClass('active');
			$this.find('.trackerEnergyRec').hide();
			$this.find('.trackerMoveRec').show();
		}
		
		
//		$('.trackerRec').find('.active').removeClass('active');
//		if(activeTrackerId != trackerId) {
//			$this.addClass('active');
//		}
	} else {
		var showInfoTrackerId = $('.trackerRec').find('.showInfo').data('trackerid');
		$('.trackerRec').find('.showInfo').removeClass('showInfo');
		$('.trackerRec').find('.trackerInfo').hide();
		if(showInfoTrackerId != trackerId) {
//			var marginTop = $(".trackerTitleRec")[0].offsetHeight;
//			var marginTop = 0;
//			$this.find('.trackerInfo').css({'left': this.parentElement.offsetLeft + this.offsetWidth, 'top': this.parentElement.offsetTop + marginTop})
//			$this.find('.trackerInfo').css({'left': this.offsetLeft + this.offsetWidth, 'top': this.parentElement.offsetTop + marginTop})
			$this.find('.trackerInfo').show();
			$this.addClass('showInfo');
		}
	}
	$('.trackerAllCheckGroup').find('button[name="trackerAllCheck"]').removeClass('active');
	$('.trackerAllCheckGroup').find('button[name="trackerAllCheck"]').text('전체 선택');
});

//전체 선택
$('.remote-auto-group').on('click', 'button[name="trackerAllCheck"]', function(){
	var $this = $(this);
	
	if($this.hasClass('active')){
		$this.removeClass('active');
		$('.trackerRec').find('.trackerBox').removeClass('active');
		$('.trackerEnergyRec').show();
		$('.trackerMoveRec').hide();
		$this.text('전체 선택');
	} else {
		$this.addClass('active');
		$('.trackerRec').find('.trackerBox').addClass('active');
		$('.trackerEnergyRec').hide();
		$('.trackerMoveRec').show();
		$this.text('전체 해제');
	}
});



// 전체 선택
//$('.remote-auto-group').on('click', '.trackerAllCheckGroup', function(){
//	var $this = $(this);
//
//	var isChecked = $this.find('input[name="trackerAllCheck"]')[0];
//	
//	if(isChecked.checked){
//		$('.trackerRec').find('.trackerBox').addClass('active');
//	} else {
//		$('.trackerRec').find('.active').removeClass('active');
//	}
//	
//});


$('#alarmTableForm').on('click', '.selectBtn', function(e){
	e.stopPropagation();
	setAlramTable();
});


$('.alarmListPageing').on('click', 'li[data-pageno]', function( event){
	var $this = $(this);
	var pageNo = $this.data('pageno');
	var alarmTableForm = $('#alarmTableForm');
	alarmTableForm.find('input[name="pageNo"]').val(pageNo);

	setAlramTable();

});

//자동모드 실행
$(".auto-action-btn").on('click', function(e){
	var tBoxActiveList = $(".trackerBox.active");
	if(!tBoxActiveList || tBoxActiveList.length == 0){
		alert("제어 할 트래커를 선택해 주세요");
		return;
	}
	
	var trackerIdList = [];
	$(".trackerBox.active").each(function(tBox){
		trackerIdList.push($(this).attr('data-trackerid'));
	});
	
	console.log("자동 모드 실행", trackerIdList);
	requestSensorMode(trackerIdList)
});

//리모트모드 실행
$(".remote-manual-group [data-control]").on('click', function(e){
	e.preventDefault();
	
	var tBoxActiveList = $(".trackerBox.active");
	if(!tBoxActiveList || tBoxActiveList.length == 0){
		alert("제어 할 트래커를 선택해 주세요");
		return;
	}
	
	var trackerIdList = [];
	$(".trackerBox.active").each(function(tBox){
		trackerIdList.push($(this).attr('data-trackerid'));
	});
	
	remoteClick = true;
	var type = $(this).attr('data-control')
	var code;
	switch (type) {
		case "right":
			code = 4;
			break;
		case "left":
			code = 3;
			break;
		case "down":
			code = 2;
			break;
		case "up":
			code = 1;
			break;
		default:
			code = 0;
			break;
	}
	
	$(".remote-manual-group [data-control=stop]").on('click', function(e){
		if(!remoteClick){
			return;
		}
		clearInterval(tmpInterval);
		//alert('m :' + e.type)

		var tBoxActiveList = $(".trackerBox.active");
		var code = 0;
		
		var trackerIdList = [];
		$(".trackerBox.active").each(function(tBox){
			trackerIdList.push($(this).attr('data-trackerid'));
		});
		
		console.log("리모트 모드 실행", trackerIdList);
		
		var remoteData = {
				trackerIdList : trackerIdList,
				code : code
		}
		
		requestRemoteMode(remoteData);
		remoteClick = false;
	});
	
	clearInterval(tmpInterval);
	tmpInterval = setInterval(setTrackerBatch, 1000);
	console.log("리모트 모드 실행", trackerIdList, code);
	
	var remoteData = {
			trackerIdList : trackerIdList,
			code : code
	}
	
	requestRemoteMode(remoteData);
});

function requestSensorMode(trackerIdList){
	console.log(trackerIdList);
	
	$.ajaxSettings.traditional = true;
	
	$.ajax({
		type:"GET",
		url	:_cp + "/tracker/requestSensorMode",
		data: {trackerIdList : trackerIdList},
		success:function(data){
			var alertStr = [];
			alertStr.push("[ ");
			
			for (var i = 0; i < trackerIdList.length; i++) {
				alertStr.push(trackerIdList[i] + "번 ");
			}
			
			alertStr.push("] 트래커를 자동모드로 변경 하였습니다.");
			
			var str = alertStr.join("");
			
			alert(str)
			
		}, error:function(error){
			alert("자동모드가 실패 하였습니다.");
		}
	});
}

function requestRemoteMode(data){
	$.ajaxSettings.traditional = true;
	$.ajax({
		type:"GET",
		url	:_cp + "/tracker/requestRemoteMode",
		data: data,
		success:function(data){
			
		}, error:function(error){
			alert("수동모드가 실패 하였습니다.");
		}
	});
}

function setAlramTable(){
	var trackerGroupSelForm = $('#trackerGroupSelForm');
	var trackerGroupId = trackerGroupSelForm.find('select[name="trackerGroupId"]').val();

	var alarmTableForm = $('#alarmTableForm');
	var alarmGrade = alarmTableForm.find('select[name="alarmGrade"]').val();
	var trackerId = alarmTableForm.find('select[name="trackerId"]').val();
	var pageNo = alarmTableForm.find('input[name="pageNo"]').val();
	var pageSize = alarmTableForm.find('input[name="pageSize"]').val();
	var startDescNo = 0;
	
	$.ajax({
		type:"GET",
		url	:_cp + "/groupPresentState/alarmList/pageing",
		data: {trackerGroupId : trackerGroupId, alarmGrade : alarmGrade, pageNo : pageNo, pageSize : pageSize, trackerId : trackerId},
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
		data: {trackerGroupId : trackerGroupId, alarmGrade : alarmGrade, pageNo : pageNo, pageSize : pageSize, trackerId : trackerId},
		success:function(list){
			
			var length = list.length;
			var $list = $(".alarmList");
			var $rowSample = "<tr style='text-align: center;'>" + 
								"<td colspan='5'>검색 결과가 없습니다.</td>" +
							 "</tr>";
			
			
			$list.find('tbody').empty();
			if(length < 1){
				
				$list.find('tbody').append($rowSample);
			}
			
			for(var i=0; i<length; i++){
				var item = list[i];
				var $rowSample = "<tr>" +
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


function setTrackerSelBox(trackerGroupId) {
	
	
	var form = $('#alarmTableForm');
	var trackerIdSel = form.find('select[name="trackerId"]');
	var trackerGroupId = $('#trackerGroupSelForm').find('select[name="trackerGroupId"]').val();
	trackerIdSel.empty();

	if(trackerGroupId == ""){
		var option = $("<option value='' selected>" + "등록된 트래커가 없습니다." + "</option>");
		trackerIdSel.append(option);
		return;
	}
	

  	var trackerArr = new Array();

	$.ajax({
		type:"GET",
		url	: _cp + '/tracker/trackerModelList',
		data: {eqTrackerGroupId : trackerGroupId},
		async:false,
		success:function(data) {
			
			trackerArr = data;

		}, error:function(error) {
			
		}
	});

	var hiddenTrackerId = $('.searchRec').find('input[name="hiddenTrackerId"]').val();
	
	
	if(trackerArr.length > 0) {
		var option = $("<option value=''>" + "전체" + "</option>");
		trackerIdSel.append(option);
		
		for(var i=0; i<trackerArr.length; i++){
			
			var option = $("<option value='" + trackerArr[i].tracker_id + "'>" + trackerArr[i].tracker_id + "</option>");
			trackerIdSel.append(option);
			if(trackerArr[i].tracker_id == hiddenTrackerId){
				trackerIdSel.val(hiddenTrackerId).attr("selected", "selected");
			}
		}
		
	} else {
		var option = $("<option value='' selected>" + "등록된 트래커가 없습니다." + "</option>");
		trackerIdSel.append(option);
	}
	
}

function updateTracker(data){
//	$(".trackerRec div").remove();
	
	data.forEach(function(tracker){
		var $tracker = $("[data-trackerid='"+ tracker.tracker_id +"']");
		
		if(tracker.broken){
			$tracker.attr('data-broken', true);
		}
		
		if(tracker.controllerBroken){
			$tracker.attr('data-controllerBroken', true);
		}
		
		$tracker.find(".y_angle").text(tracker.y_angle);
		$tracker.find(".x_angle").text(tracker.x_angle);
		
		$tracker.css({'left': tracker.x, 'top': tracker.y});
		
		Object.keys(tracker).forEach(function(key){
			var value = tracker[key]; 
			$tracker.find(".trackerInfo #"+key+"+.tx").text(value);
		});
		
	});
	
}

function setTrackerBatch(){
	$.ajax({
		type:"GET",
		url	: _cp + '/tracker/getTrackerDataList',
		success:function(data) {
			console.log(data);
			if(!remoteClick){
				
				if(!firstInit){
					firstInit = true;
					makeTrackerTable(data.length);
				}
				
				trackerBrokenCheck(data);
	//			trackerAzimuthCheck(data);
				makeTrackerRec(data);
			}
			updateTracker(data);
		}, error:function(error) {
			
		}
	});
}

function trackerAzimuthCheck(data){
	var tracker1 = data[0];
	
	var illuminationLimit = 10;
	
//	낮인지 밤인지 체크 
	if(Number(tracker1.illumination) < illuminationLimit){
//		illuminationLimit 보다 작을 경우 밤으로 간주
		return;
	}
	
//	전체 트래커 평균 방위각
	var avaAzimuth = 0;
	data.forEach(function(tracker){
		console.log(tracker.x_angle, avaAzimuth);
		avaAzimuth += tracker.x_angle
	});
	console.log(avaAzimuth);

//	전체 방위각의 평균 방위각
	avaAzimuth = avaAzimuth / data.length;
	console.log("평균 방위각 : ", avaAzimuth);
	
//	방위각 +- 오차범위 기준 (임시로 10%)
	var standardPercent = 10;
	var standardAzimuth = avaAzimuth * standardPercent / 100;
 
	console.log("방위각 오차범위 : ", standardAzimuth);
	
	data.forEach(function(tracker){
		var azimuth = tracker.x_angle;
//		평균 방위각 +10%보다 크거나 평균 방위각 -10%보다 작을때 고장 표시
		if(azimuth > avaAzimuth + standardAzimuth || azimuth < avaAzimuth - standardAzimuth){
			tracker.controllerBroken = true;
			console.log("고장 트래커", azimuth , avaAzimuth - standardAzimuth,  tracker);
		}
	});
	
}

// 트래커 평균 에너지량 체크 후 불량 체크
function trackerBrokenCheck(data){
	var tracker1 = data[0];
	
//	밤/낮 기준 일사량 illumination < 10 밤 illumination > 50 흐리지 않은 주간  
	var illuminationLimit = 50;
	
//	트래커내 센서 트래커에서 일사량 가져옴
	var illumination = 0;
	data.forEach(function(tracker){
		if(tracker.illumination > 0){
			illumination = tracker.illumination;
		}
	});
	
//	TODO 기준 조도 추후에 변경 될 수 있음
	if(Number(illumination) < illuminationLimit){
		return;
	}
	
	var energySumPercent = 0;
	data.forEach(function(tracker){
		tracker.energy_percent = tracker.instant_energy / tracker.capacity * 100;
		energySumPercent += tracker.energy_percent; 
	});
	
	var avaEnergyPercent = energySumPercent / data.length
	console.log("전체 퍼센트 에너지 합 : , 평균 에너지 퍼센트: ", energySumPercent, avaEnergyPercent);
	
	var standardEnergyPercent = avaEnergyPercent / 10;
	console.log("평균 에너지 퍼센트 오차범위 : ", standardEnergyPercent);
	
	data.forEach(function(tracker){
		var energyPercent = tracker.energy_percent;
		
//		console.log(energyPercent, avaEnergyPercent + standardEnergyPercent, avaEnergyPercent - standardEnergyPercent)
		
		if(energyPercent > energyPercent + standardEnergyPercent || energyPercent < energyPercent - standardEnergyPercent){
			tracker.broken = true;
			console.log("tracker energy broken", tracker);
		}
	});
	
/*//	전체 트래커의 평균 발전량
	var avaEnergy = 0;
	data.forEach(function(tracker){
		avaEnergy += tracker.instant_energy;
	});
	
	console.log(data, avaEnergy);
	avaEnergy = avaEnergy / data.length;
	console.log("평균 에너지 : " + avaEnergy);
	
//	전체 크래커의 +- 오차범위 기준 (임시로 10%) 
//	var standardPlusEnergy =  
	var standardPercent = 10;
	var standardEnergy = avaEnergy * standardPercent / 100;
	
	console.log("오차 범위 : ", standardEnergy);
	
	data.forEach(function(tracker){
		var energy = tracker.instant_energy;
//		평균 에너지 + 10% 에너지보다 크거나 평균 에너지 - 10%에너지보다 작을때 고장 표시
		if(energy > avaEnergy + standardEnergy || energy < avaEnergy - standardEnergy){
			
			tracker.broken = true;
			console.log("tracker broken", tracker);
			
		}
	});*/
	
	
}

function makeTrackerRec(data){
	/*var lngList = data.slice(),
		latList = data.slice();
	
	var d_limit = 5;
	
	var sort = function(v1, v2, v3, v4){
		if(v1== v2){
			return v3.localeCompare(v4);
		}
		else if(v1 < v2) return -1;
		else return 1;
	}
	
	lngList.sort(function(t1, t2){
		t1.loc_lng = parseFloat( parseFloat(t1.loc_lng).toFixed(d_limit) );
		t2.loc_lng = parseFloat( parseFloat(t2.loc_lng).toFixed(d_limit) );
		
		return sort(
			t1.loc_lng, t2.loc_lng,
			t1.tracker_id, t2.tracker_id
		);
	});
	latList.sort(function(t1, t2){
		t1.loc_lat = parseFloat( parseFloat(t1.loc_lat).toFixed(d_limit) );
		t2.loc_lat = parseFloat( parseFloat(t2.loc_lat).toFixed(d_limit) );
		
		return sort(
			t1.loc_lat, t2.loc_lat,
			t1.tracker_id, t2.tracker_id
		);
	});
	
	var margin = 20;
	var width = 60 + margin, height = 60 + margin;
	
	var idx = -1, temp;
	lngList.forEach(function(tracker){
		if(tracker.loc_lng != temp){
			idx++;
			temp = tracker.loc_lng;
		}
		tracker.x = idx * width + margin;
	});
	idx = -1;
	latList.forEach(function(tracker){
		if(tracker.loc_lat != temp){
			idx++;
			temp = tracker.loc_lat;
		}
		tracker.y = idx * height + margin;
	});
	
	$(".trackerRec").css({'width': lngList[lngList.length-1].x + width, 'height': latList[latList.length-1].y + height});
	
	data.forEach(function(tracker){
		$(".trackerRec").append(trackerInfoDiv(tracker));
	});*/
	
	batchTrackerTable(data);
}

var _dragData;
function onDragStart(event){
	var target = event.target;
	
	$('.trackerRec').find('.trackerInfo').hide();
	
	if(target.classList.contains("remoteController")){
		trackerBatchStart = true;
	}
	
	if(trackerBatchStart === false) return;
//	event.preventDefault();
	
	var id;
	
	if(target.classList.contains("remoteController")){
		
		id = target.id;
		
	}else{
	
		id = target.id;
		console.log("onDragStart", id);
		if(!id){
	//		trackerDiv 안쪽 텍스트가 선택될때
			var parentEl = event.target.parentElement; 
			if(!parentEl.closest('[data-trackerid]')) {
				return;
			}
			id = parentEl.closest('[data-trackerid]').getAttribute('data-trackerid');
		}
	
	}

	// ie
	_dragData = {
		id: id
	};
	event.dataTransfer.setData("id", id);
	console.log("onDragStart", event.dataTransfer.getData("id"));
}

function onDrop(event){
	var id;
	try{
		// ie error
		id = event.dataTransfer.getData("id");
	}catch(e){
		id = _dragData.id;
		//console.error('event.dataTransfer.getData error', e);
	}
	
	var dragTrackerEl = document.getElementById(id);
	
	if(dragTrackerEl.classList.contains("remoteController")){
		trackerBatchStart = true;
	}
	
	if(trackerBatchStart === false) return;
	event.preventDefault();
	
//	원격제어 판 이동인지 체크
	if(dragTrackerEl.classList.contains("remoteController")){
		var marginTop = $(".trackerTitleRec")[0].offsetHeight;
		console.log(event.target);
//		$(dragTrackerEl).css({"left": event.target.offsetLeft, "top": event.target.offsetTop + marginTop });
		$(dragTrackerEl).css({"left": $(event.target).closest("TD").offsetLeft, "top": event.target.closest("TD").offsetTop + marginTop });
		trackerBatchStart = false;
		return;
	}
	
	var dragThEl = dragTrackerEl.closest("TD");
	console.log("drop", id);
	if(event.target.tagName !== "TD"){
//		드래그한 지점에 tracker element 가 있을 경우 서로 바꿔치기
		event.target.closest("TD").appendChild(dragTrackerEl);
		
		dragThEl.appendChild(event.target.closest("[data-trackerid]"));
		console.log("바꿔치기");
		return;
	}
	
	event.target.appendChild(dragTrackerEl);
	$(".trackerInfo").hide();
}

function onDrag(event){
	var target = event.target;
	
	if(target.classList.contains("remoteController")){
		trackerBatchStart = true;
	}
	
	if(trackerBatchStart === false) return;
	event.preventDefault();
//	event.dataTransfer.setData("test", "sisisi");
	/*var id = event.target.id;
	if(!id){
		if(!event.target.closest('[data-trackerid]')) {
			event.dataTransfer.setData("id", "sisisi");
			return;
		}
		id = event.target.closest('[data-trackerid]').getAttribute('data-trackerid');
	}
	
	console.log('drag', id);
	
	event.dataTransfer.setData("id", id);*/
	
	
}

function updateBatchTracker(){
	var trackerArr = [];
	$(".trackerBox").each(function(){
		var trackerId = this.getAttribute('data-trackerid');
		var td = this.closest('td');
		console.log(td.parentNode.rowIndex, td.cellIndex);
		var trackerData = {
				trackerId: trackerId,
				site_x: td.cellIndex,
				site_y: td.parentNode.rowIndex
		}
		trackerArr.push(trackerData);
	});
	
//	$.ajaxSettings.traditional = true;
	$.ajax({
		type:"POST",
		url	:_cp + "/tracker/updateBatchTracker",
		data: JSON.stringify(trackerArr),
		/*dataType: "json",*/
		contentType: "application/json",		
		success:function(data){
			console.log("success", data);
			alert("트래커 배치 저장이 성공 하였습니다.");
		}, error:function(error){
//			console.error(error);
			alert("트래커 배치 저장이 실패 하였습니다.");
//			alert("자동모드가 실패 하였습니다.");
		}
	});
	
	trackerBatchStart = false;
	$(".GrouptrackerInfo").show();
	$(".GroupTrackerEditMode").hide();
	$(".remoteController").show();
	
	console.log(trackerArr);
}

function trackerInfoDiv(tracker){
//	console.log(tracker);
	var $tracker = $("[data-trackerid='"+ tracker.tracker_id +"']");
	if($tracker.length > 0){
		return $tracker[0];
	}
	
	tracker.instant_energy = tracker.instant_energy || 0;
	tracker.alarm_grade = tracker.alarm_grade || -1;
	var alarmHtml;
	if(tracker.alarm_grade < 0){
		alarmHtml = '<div class="trackerAlertRec">';
	}else{
		alarmHtml = '<div class="trackerAlertRec"  style="display:block" >';
	}
		

	var htmlTag = 
		/*'<div data-trackerid=' + tracker.tracker_id + ' class="trackerBox" ondragstart="onDrag(event)" draggable="true">'*/
		'<div id=' + tracker.tracker_id + ' data-trackerid=' + tracker.tracker_id + ' class="trackerBox" ondragstart="onDragStart(event)" draggable="true">'
		+'<div class="trackerId">' + tracker.tracker_id + '</div>'
		+'<div class="trackerContentRec">'
		+	'<div class="trackerEnergyRec">'
		+		'<p class="trackerEnergy">' + tracker.instant_energy + '</p>'
		+		'<p class="trackerEnergyUnit">' + 'kW' + '</p>'
		+	'</div>'
		+	'<div class="trackerMoveRec">'
		+		'<p class="trackerMoveDeg01">'
		+			'<span>' + '고도각' + '</span>'
		+			'<span class="y_angle">' + tracker.y_angle + '</span>'
		+		'</p>'
		+		'<p class="trackerMoveDeg02">'
		+			'<span>' + '방위각' + '</span>'
		+			'<span class="x_angle">' + tracker.x_angle + '</span>'
		+		'</p>'
		+	'</div>'
		+'</div>'
		//+alarmHtml
		/*+	'<ul>'
		+		alarmHtml
		+		alarmHtml2
		+		alarmHtml3
		+		'<li class="trackerAlert grade00">' + '2' + '</li>'
		+		'<li class="trackerAlert grade01">' + '0' + '</li>'
		+		'<li class="trackerAlert grade02">' + '1' + '</li>'
		+	'</ul>'*/
		//+	'<div class="trackerAlert"></div>'
		//+'</div>'
		+'<div class="trackerInfo">'
		+	'<ul>'
		+		'<li>'
		+			'<span id="tracker_id" class="title">트래커 아이디</span><span class="tx"></span>'
		+		'</li>'
		+		'<li>'
		+			'<span id="capacity" class="title">용량</span><span class="tx"></span>'
		+		'</li>'
		+		'<li>'
		+			'<span id="x_angle" class="title">고도각</span><span class="tx"></span>'
		+		'</li>'
		+		'<li>'
		+			'<span id="y_angle" class="title">방위각</span><span class="tx"></span>'
		+		'</li>'
		+		'<li>'
		+			'<span id="body_temp" class="title">온도</span><span class="tx"></span>'
		+		'</li>'
		+		'<li>'
		+			'<span id="body_humd" class="title">습도</span><span class="tx"></span>'
		+		'</li>'
		+		'<li>'
		+			'<span id="trackerAlertRec" class="title">최근알람정보</span>'+alarmHtml
		+		'</li>'
		+	'</ul>'
		+'</div>'
		+'</div>';
	
//	var htmlTag = 
//		'<div data-trackerid=' + tracker.tracker_id + ' class="trackerBox" data-broken="'+tracker.broken+'" style="left:'+tracker.x+'px; top:'+tracker.y+'px;" >'
//		+'<span class="trackerId">'+ tracker.tracker_id + '</span>'
//		+'<div class="trackerInfo">'
//		+	'<ul>'
//		+		'<li>'
//		+			'<span class="title">트래커 아이디</span><span class="tx">'+tracker.tracker_id+'</span>'
//		+		'</li>'
//		+		'<li>'
//		+			'<span class="title">용량</span><span class="tx">'+tracker.capacity+'</span>'
//		+		'</li>'
//		+		'<li>'
//		+			'<span class="title">고도각</span><span class="tx">'+tracker.x_angle+'</span>'
//		+		'</li>'
//		+		'<li>'
//		+			'<span class="title">방위각</span><span class="tx">'+tracker.y_angle+'</span>'
//		+		'</li>'
//		+		'<li>'
//		+			'<span class="title">온도</span><span class="tx">'+tracker.body_temp+'</span>'
//		+		'</li>'
//		+		'<li>'
//		+			'<span class="title">습도</span><span class="tx">'+tracker.body_humd+'</span>'
//		+		'</li>'
//		+	'</ul>'
//		+'</div>'
//		+'</div>';
	
//	var htmlTag = 
//	'<div data-trackerid=' + tracker.tracker_id + ' class="trackerBox" style="left:'+tracker.x+'px; top:'+tracker.y+'px;" >'
//	+'<span class="trackerId">'+ tracker.tracker_id + '</span>'
//	+'<div class="trackerInfo">'
//	+	'<ul>'
//	+		'<li>'
//	+			'<span class="title">트래커 아이디</span><span class="tx">'+tracker.tracker_id+'</span>'
//	+		'</li>'
//	+		'<li>'
//	+			'<span class="title">용량</span><span class="tx">'+tracker.capacity+'</span>'
//	+		'</li>'
//	+		'<li>'
//	+			'<span class="title">고도각</span><span class="tx">'+tracker.x_angle+'</span>'
//	+		'</li>'
//	+		'<li>'
//	+			'<span class="title">방위각</span><span class="tx">'+tracker.y_angle+'</span>'
//	+		'</li>'
//	+		'<li>'
//	+			'<span class="title">온도</span><span class="tx">'+tracker.body_temp+'</span>'
//	+		'</li>'
//	+		'<li>'
//	+			'<span class="title">습도</span><span class="tx">'+tracker.body_humd+'</span>'
//	+		'</li>'
//	+	'</ul>'
//	+'</div>'
//	+'</div>'
	
	return $(htmlTag)[0];
	
}

function batchTrackerTable(data){
	console.log("batchTrackerTable", data);
	
	var $table = $(".trackerRec table")[0];
	
	for(var i=0; i<data.length; i++){
		var tracker = data[i];
		var x_position = tracker.site_x;
		var y_position = tracker.site_y;
		var trackerDiv = trackerInfoDiv(tracker);
		
//		좌표 null 일경우 -1로 들어옴
		if(x_position < 0 || y_position < 0 ){
			insertTrackerToDefaultTable(trackerDiv, 0, 0);
			continue;
		};
		
		var tableTh = $table.rows[y_position].cells[x_position];
		$(tableTh).append(trackerDiv);
	}
	
	var tdLength = $(".trackerRec table tr").eq(0).find("td").length;
	if(tdLength < 10){
		$(".trackerRec table")[0].style.width = '';
	}
	
	
	function insertTrackerToDefaultTable(trackerDiv, x_position, y_position){
		if(!$table) return;

		var tableTh = $table.rows[y_position] && $table.rows[y_position].cells[x_position];
		if(!tableTh) {
			insertTrackerToDefaultTable(trackerDiv, 0, y_position+1);
			return;
		}
		
//		x,y 좌표가 -1일경우 순서대로 삽입
		if($(tableTh).find(".trackerBox").length > 0){
			var contains = $table.rows[y_position].cells[x_position].contains(trackerDiv);
			if(contains){
				return;
			}
			
			var x_limit = $table.rows[y_position].cells.length;
			if(x_limit > x_position){
				insertTrackerToDefaultTable(trackerDiv, x_position + 1, y_position);
			}else{
				insertTrackerToDefaultTable(trackerDiv, x_position, y_position +1);
			}
			
			return;
		}
		
		$(tableTh).append(trackerDiv);
		
	}
}

function makeTrackerTable(size){
//	var x_size = y_size = 50;
	size = size || 20;
	var x_size = size;
	var y_size = size;
	
	var table = document.createElement('table');
	$(table).addClass('trackerTable');
	$(".trackerTableRec").append(table);
	$(table).css('width', '100%');
	
	
//	ondrop="onDrop(event)" ondragover="onDrag(event)"
	for(var i=0; i<y_size; i++){
		var tr = document.createElement('tr');
		for(var j=0; j<x_size; j++){
			
			var td = document.createElement('td');
			
			$(td).attr('ondrop', 'onDrop(event)');
			$(td).attr('ondragover', 'onDrag(event)');
			
			$(tr).append(td);
			
			/*var th = document.createElement('th');
			
			$(tr).attr('ondrop', 'onDrop(event)');
			$(tr).attr('ondragover', 'onDrag(event)');
			
			$(tr).append(th);*/
			
		}
		$(table).append(tr);
	}

	
	console.log($(table));
	
}

function readyBatchTracker(){
	$(".GrouptrackerInfo").hide();
	$(".GroupTrackerEditMode").show();
	$(".remoteController").hide();
	
	trackerBatchStart = true;
	
	alert("트래커 배치 잠금이 풀렸습니다. 원하시는 트래커 박스를 드래그 하여 원하는 곳에 위치시킨 후 트래커 배치 저장 버튼을 눌러주세요.");
}