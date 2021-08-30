$(document).ready(function(){
	
	var areaCode = $('#trackerSelForm').find('select[name="areaCode"]').val();
	setTrackerGroupSelBox(areaCode);
	var trackerGroupId = $('#trackerSelForm').find('select[name="trackerGroupId"]').val();
	setTrackerSelBox(trackerGroupId);
	
//	SELECT BOX SORTING
	$("select").each(function(){
		$(this).find("option").sortOptionsByParsedValue();
	});
});

// 지역선택(해당 지역 군집 목록 조회)
$('#trackerSelForm').on('change', 'select[name="areaCode"]', function(){
	var areaCode = $(this).val();
	setTrackerGroupSelBox(areaCode);
	
	var trackerGroupId = $('#trackerSelForm').find('select[name="trackerGroupId"]').val();
	setTrackerSelBox(trackerGroupId);
});

// 군집선택(해당 트래커 목록 조회)
$('#trackerSelForm').on('change', 'select[name="trackerGroupId"]', function(){
	var trackerGroupId = $(this).val();
	setTrackerSelBox(trackerGroupId);
});

// 선택한 군집 조회
$('#trackerSelForm').on('click', '.selectBtn', function(){
	var form = $('#trackerSelForm');
	var trackerGroupIdSel = form.find('select[name="trackerGroupId"]').val();
	if("" == trackerGroupIdSel){
		alert("군집을 선택 하여 주십시오.");
		return false;
	}
});

function validateForm(){
	
	var TimesSetBl = $(".searchTermsTx");
	
	if(TimesSetBl.length > 0){
		var startDate = $("[name='startDate']")[0].value;
		var endDate = $("[name='endDate']")[0].value;
		
		if(startDate === "" || endDate === ""){
			alert("기간을 선택해 주세요.");
			return false
		}
	}
	
	return true;
}

function setTrackerGroupSelBox(areaCode) {
	
  	var trackerGroupArr = new Array();

	$.ajax({
		type:"GET",
		url	: _cp + '/group/trackerGroupList',
		data: {areaCode : areaCode},
		async:false,
		success:function(data) {
			trackerGroupArr = data;

		}, error:function(error) {
			
		}
	});
	var form = $('#trackerSelForm');
	var trackerGroupIdSel = form.find('select[name="trackerGroupId"]');
	var hiddenTrackerGroupId = $('.searchRec').find('input[name="hiddenTrackerGroupId"]').val();
	
	trackerGroupIdSel.empty();
	
	if(trackerGroupArr.length > 0) {
		for(var i=0; i<trackerGroupArr.length; i++){
			
			var option = $("<option value='" + trackerGroupArr[i].tracker_group_id + "'>" + trackerGroupArr[i].tracker_group_name + "</option>");
			trackerGroupIdSel.append(option);
			if(trackerGroupArr[i].tracker_group_id == hiddenTrackerGroupId){
				trackerGroupIdSel.val(hiddenTrackerGroupId).attr("selected", "selected");
			}
		}
		
	} else {
		var option = $("<option value='' selected>" + "등록된 군집이 없습니다." + "</option>");
		trackerGroupIdSel.append(option);
	}
	
}

function setTrackerSelBox(trackerGroupId) {
	
	
	var form = $('#trackerSelForm');
	var trackerIdSel = form.find('select[name="trackerId"]');
	var trackerGroupId = $('#trackerSelForm').find('select[name="trackerGroupId"]').val();
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
	var loginRole = $('.searchRec').find('input[name="loginRole"]').val();
	
	console.log(trackerArr);
	
	if(trackerArr.length > 0) {
		if("tracker_admin" != loginRole) {
			var option = $("<option value=''>" + "전체" + "</option>");
			trackerIdSel.append(option);
		}
		
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