$(document).ready(function(){
	trackerSearch();
});

// 군집 선택
$("#trackerModel").on('click', 'tr', function(){
	trackerSelect($(this));
});

$('#trackerModel').on('click', 'button[name="searchButton"]', function(){
	trackerSearch();
});

$('#trackerModel').find('input[name="likeTrackerId"]').keypress(function (event){
	if(event.keyCode==13) {
		trackerSearch();
	}
});

$('#trackerModel').on('click', 'button[name="trackerApply"]', function(){
	
	var formId = $('#trackerModel').find('button[name="trackerApply"]').data('formid');
	trackerApply(formId);
});

// 지역선택(해당 지역 군집 목록 조회)
$('#trackerModel').on('change', 'select[name="eqAreaCode"]', function(){
	$('#trackerModel').find('input[name="likeTrackerId"]').val('');
	var eqAreaCode = $(this).val();
	setTrackerGroupSelBox(eqAreaCode);
	trackerSearch();
}).on('change', 'select[name="eqTrackerGroupId"]', function(){
	$('#trackerModel').find('input[name="likeTrackerId"]').val('');
	trackerSearch();
});

function trackerSelect($this){
	
	var id = $this.find('td').data('id');

	$('#trackerModel').find('tr').removeClass('listSel');
	$this.addClass('listSel');
	
	$('#trackerModel').find('input[name="trackerId"]').val(id);
}


function trackerApply(formId){
	
	var $form = $('#'+formId);
	var trackerList = $form.find('.trackerList');
	var $model = $('#trackerModel');
	var trackerId = $model.find('input[name="trackerId"]').val();
	var trackerItem = trackerList.find('.trackerId-' + trackerId);
	
	if(trackerItem.length > 0){
		$(".trackerList").find('.trackerId-' + trackerId).remove();
	}
	
	var trackerHtml = "<div class='trackerId-" + trackerId + "'>" + "</div>";
	trackerList.append(trackerHtml);
	
	trackerItem = trackerList.find('.trackerId-' + trackerId);
	var trackerTextHtml = "<input type='text' class='inputField inverterId' name='trackerId' readonly='readonly' value='" + trackerId + "'/>";
	var trackerBtnHtml = "<input type='button' class='btn overlap expand' data-tracker-del-btn='" + trackerId + "' value='- 인버터 제거'>";
	trackerItem.append(trackerTextHtml);
	trackerItem.append(trackerBtnHtml);
}


function trackerSearch() {
	var model = $('#trackerModel');
	var formId = model.find('button[name="apply"]').data('formid');
	var $form = $('#'+formId);
	var eqAreaCode = model.find('select[name="eqAreaCode"]').val();
	var eqTrackerGroupId = model.find('select[name="eqTrackerGroupId"]').val();
	var likeTrackerId = model.find('input[name="likeTrackerId"]').val();

	$.ajax({
		type:"GET",
		url	:_cp + "/tracker/trackerModelList",
		data: {eqAreaCode : eqAreaCode, eqTrackerGroupId : eqTrackerGroupId, likeTrackerId : likeTrackerId},
		success:function(list){
			
			
			var length = list.length;
			var $list = $("#trackerModel");
			var $rowSample = "<tr>" + 
								"<td data-id=''>검색 결과가 없습니다.</td>" +
							 "</tr>";
			
			$list.find('tbody').empty();
			if(length < 1){
				
				var $rowSample = "<tr>" + 
									"<td data-id=''>검색 결과가 없습니다.</td>" +
								 "</tr>";				
				
				
				$list.find('tbody').append($rowSample);
			}
			
			for(var i=0; i<length; i++){
				var item = list[i];
				var $rowSample = "<tr>" + 
				"<td class='corsor-pointer' data-id='" + item.tracker_id + "'>"+ item.tracker_id + "</td>" +
			 "</tr>";				
				
				
				$list.find('tbody').append($rowSample);
			}
			
		}, error:function(error){
			alert("검색에 실패 하였습니다.");
		}
	});
}


function setTrackerGroupSelBox(eqAreaCode) {
	
  	var trackerGroupArr = new Array();

	$.ajax({
		type:"GET",
		url	: _cp + '/group/trackerGroupList',
		data: {areaCode : eqAreaCode},
		async:false,
		success:function(data) {
			trackerGroupArr = data;

		}, error:function(error) {
			
		}
	});
	var form = $('#trackerModel');
	var trackerGroupIdSel = form.find('select[name="eqTrackerGroupId"]');
	
	trackerGroupIdSel.empty();
	
	
	if(trackerGroupArr.length > 0) {
		var option1 = $("<option value='' selected>" + "전체" + "</option>");
		trackerGroupIdSel.append(option1);
		
		for(var i=0; i<trackerGroupArr.length; i++){
			var option = $("<option value='" + trackerGroupArr[i].tracker_group_id + "'>" + trackerGroupArr[i].tracker_group_name + "</option>");
			trackerGroupIdSel.append(option);
		}
		
	} else {
		var option = $("<option value='' selected>" + "등록된 군집이 없습니다." + "</option>");
		trackerGroupIdSel.append(option);
	}
	
	
}