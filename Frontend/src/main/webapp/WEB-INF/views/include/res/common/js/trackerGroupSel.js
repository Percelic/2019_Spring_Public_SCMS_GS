$(document).ready(function(){
	
	var areaCode = $('#trackerGroupSelForm').find('select[name="areaCode"]').val();
	setTrackerGroupSelBox(areaCode);
	
//	SELECT BOX SORTING
	$("select").each(function(){
		$(this).find("option").sortOptionsByParsedValue();
	});
	
});

// 지역선택(해당 지역 군집 목록 조회)
$('#trackerGroupSelForm').on('change', 'select[name="areaCode"]', function() {
	var areaCode = $(this).val();
	setTrackerGroupSelBox(areaCode);
});

// 선택한 군집 조회
$('#trackerGroupSelForm').on('click', '.selectBtn', function() {
	var form = $('#trackerGroupSelForm');
	var trackerGroupIdSel = form.find('select[name="trackerGroupId"]').val();
	if("" == trackerGroupIdSel) {
		alert("군집을 선택 하여 주십시오.");
		return false;
	}
});

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
	var form = $('#trackerGroupSelForm');
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