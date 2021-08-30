$(document).ready(function(){
	
});

// 군집 선택
$("#inverterModel").on('click', 'tr', function(){
	inverterSelect($(this));
});

$('#inverterModel').on('click', 'button[name="inverterSearchButton"]', function(){
	inverterSearch();
});

$('#inverterModel').find('input[name="inverterSearchWord"]').keypress(function (event){
	if(event.keyCode==13) {
		inverterSearch();
	}
});

$('#inverterModel').on('click', 'button[name="apply"]', function(){
	
	var formId = $('#inverterModel').find('button[name="apply"]').data('formid');
	inverterApply(formId);
});

function inverterSelect($this){
	
	var id = $this.find('td').data('id');

	$('#inverterModel').find('tr').removeClass('listSel');
	$this.addClass('listSel');
	
	$('#inverterModel').find('input[name="inverterId"]').val(id);
}


function inverterApply(formId){
	
	var $form = $('#'+formId);
	var inverterList = $form.find('.inverterList');
	var $model = $('#inverterModel');
	var inverterId = $model.find('input[name="inverterId"]').val();
	var inverterItem = inverterList.find('.inverterId-' + inverterId);
	
	if(inverterItem.length > 0){
		$(".inverterList").find('.inverterId-' + inverterId).remove();
	}
	
	var inverterHtml = "<div class='inverterId-" + inverterId + "'>" + "</div>";
	inverterList.append(inverterHtml);
	
	inverterItem = inverterList.find('.inverterId-' + inverterId);
	var inverterTextHtml = "<input type='text' class='inputField inverterId' name='inverterId' readonly='readonly' value='" + inverterId + "'/>";
	var inverterBtnHtml = "<input type='button' class='btn overlap expand' data-inverter-del-btn='" + inverterId + "' value='- 인버터 제거'>";
	inverterItem.append(inverterTextHtml);
	inverterItem.append(inverterBtnHtml);
}


function inverterSearch() {
	
	var formId = $('#inverterModel').find('button[name="apply"]').data('formid');
	var $form = $('#'+formId);
	var trackerGroupId = $form.find('input[name="trackerGroupId"]').val();

	var inverterId = $('#inverterModel').find('input[name="inverterSearchWord"]').val();

	$.ajax({
		type:"GET",
		url	:_cp + "/inverter/inverterModelList",
		data: {trackerGroupId : trackerGroupId, inverterId : inverterId},
		success:function(list){
			
			
			var length = list.length;
			var $list = $("#inverterModel");
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
				"<td class='corsor-pointer' data-id='" + item.inverter_id + "'>"+ item.inverter_id + "</td>" +
			 "</tr>";				
				
				
				$list.find('tbody').append($rowSample);
			}
			
		}, error:function(error){
			alert("검색에 실패 하였습니다.");
		}
	});
}