$(document).ready(function(){
	groupSearch();
});

// 군집 선택
$("#groupModel").on('click', 'tr', function(){
	groupSelect($(this));
}).on('dblclick', 'tr', function(){
	groupSelect($(this));
	var formId = $('#groupModel').find('button[name="groupApply"]').data('formid');
	groupApply(formId);
});

$('#groupModel').on('click', 'button[name="searchButton"]', function(){
	groupSearch();
});

$('#groupModel').find('input[name="searchButton"]').keypress(function (event){
	if(event.keyCode==13) {
		groupSearch();
	}
});

$('#groupModel').on('click', 'button[name="groupApply"]', function(){
	
	var formId = $('#groupModel').find('button[name="groupApply"]').data('formid');
	groupApply(formId);
});

function groupSelect($this){
	
	var trackerGroupId = $this.find('td').data('id');
	var trackerGroupName = $this.find('td').data('name');

	
	$('#groupModel').find('tr').removeClass('listSel');
	$this.addClass('listSel');
	
	$('#groupModel').find('input[name="trackerGroupId"]').val(trackerGroupId);
	$('#groupModel').find('input[name="trackerGroupName"]').val(trackerGroupName);
}


//function groupApply(formid){
//	
//	var $form = $('#'+formid);
//	var $model = $('#groupModel');
//	
//	$form.find('input[name="trackerGroupId"]').val($model.find('input[name="trackerGroupId"]').val());
//	$form.find('input[name="trackerGroupName"]').val($model.find('input[name="trackerGroupName"]').val());
//	$model.modal('hide');
//}


function groupSearch() {

	var searchWord = $('#groupModel').find('input[name="searchWord"]').val();

	$.ajax({
		type:"GET",
		url	:_cp + "/group/trackerGroupModelList",
		data: {searchWord : searchWord},
		success:function(list){
			
			
			var length = list.length;
			var $list = $("#groupModel");
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
				"<td class='corsor-pointer' data-id='" + item.tracker_group_id + "' + data-name='" + item.tracker_group_name + "'>"+ item.tracker_group_name + " ( " + item.tracker_group_id + " )" + "</td>" +
			 "</tr>";				
				
				
				$list.find('tbody').append($rowSample);
			}
			
		}, error:function(error){
			alert("검색에 실패 하였습니다.");
		}
	});
}