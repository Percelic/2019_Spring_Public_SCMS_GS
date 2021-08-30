$(document).ready(function(){
	
});
//페이징 ui
$('#modalPaging').on('click', 'li[data-pageno]', function( event){
	var $this = $(this);
	var pageNo = $this.data('pageno');
	var $form = $('#modalPagingForm');
	$form.find('input[name="pageNo"]').val(pageNo);

	groupSearch($('#modalPagingForm'), "paging");

});

$('#inverterModel').on('click', 'button[name="searchButton"]', function(){
	groupSearch($('.modalSearchForm'), "search");
});

$('#inverterModel').find('input[name="searchWord"]').keypress(function (event){
	if(event.keyCode==13) {
		groupSearch($('.modalSearchForm'), "search");
	}
});

$('#inverterModel').on('click', 'button[name="apply"]', function(){
	
//	var formId = $('#inverterModel').find('button[name="apply"]').data('formid');
	inverterApply();
});

$('.selectedCheck').on('click', 'button[data-selcheck-del-btn]', function(){
	var $this = $(this);
	var inverterId = $this.data('selcheck-del-btn');
	$(".selectedCheck").find('.inverterId-'+inverterId).remove();
	
	var inverterCheckEl = $(".modelList").find('.inverterId-'+inverterId).find('input[name="inverterIdCheck"]');
	if(inverterCheckEl){
		$(".modelList").find('.inverterId-'+inverterId).find('input[name="inverterIdCheck"]')[0].checked = false;
	}
});

$(".modelList").on('click', 'input[name="inverterIdCheck"]', function() {
	var $this = $(this);
	var isChecked = $this[0];
	if(isChecked.checked) {
		isChecked.checked = false;
	} else {
		isChecked.checked = true;
	}

});

$(".modelList").on('click', 'tr', function() {
	var $this = $(this);
	var inverterId = $this.data('inverterid');

	var isChecked = $this.find('input[name="inverterIdCheck"]')[0];
	if (isChecked != undefined) {
		if(isChecked.checked) {
			isChecked.checked = false;
			
			$(".selectedCheck").find('.inverterId-'+inverterId).remove();
			
		} else {
			isChecked.checked = true;
			var inverterId = $this.find('input[name="inverterId"]').val();
			
			var trSel = "<li class='select-check inverterId-" + inverterId +"'>" +
						"<input type='hidden' name='inverterId' value='" + inverterId + "'>" +
						"<p class='name'>" + inverterId + "</p>" +
						"<button class='delBtn' type='button' data-selcheck-del-btn='" + inverterId + "'>" +
						"<i class='fa fa-trash-o'></i>" +
						"</button>" +
						"</li>";

			$('.select-body').find('ul').append(trSel);
			
		}
	}

});

function inverterSearch() {
	
	var formId = $('#inverterModel').find('button[name="apply"]').data('formid');
	var $form = $('#'+formId);
	var trackerGroupId = $form.find('input[name="trackerGroupId"]').val();

	var inverterId = $('#inverterModel').find('input[name="inverterSearchWord"]').val();
	console.log(trackerGroupId);
	$.ajax({
		type:"GET",
		url	:_cp + "/inverter/inverterModelList",
		data: {trackerGroupId : trackerGroupId, inverterId : inverterId},
		success:function(list){
			console.log(list);
			$(".modelList tbody tr").remove();
			
			var $list = $("#inverterModel");
			
			if(list.length == 0){
				var $rowSample = "<tr class='model-List' style='text-align: center;'>" + 
				"<td colspan='2'>검색 결과가 없습니다.</td>" +
				"</tr>";		

				$list.find('tbody').append($rowSample);
				return;
			}
			
			for(var i=0; i<list.length; i++) {
				var item = list[i];
				
				if(undefined == $(".inverterList").find('.inverterId-'+item.inverter_id)[0]) {
//					if(isTableEmpty == true) {
//						$list.find('tbody').empty();
//					}
					
					var $rowSample = "<tr class='model-List inverterId-" + item.inverter_id + "' data-inverterid='" + item.inverter_id + "'>" +
					"<input type='hidden' name='inverterId' value='"+ item.inverter_id +"'>" + 
					"<td class='txC ft12'>"+ "<input type='checkbox' name='inverterIdCheck' value='" + item.inverter_id + "'>" + "</td>" +
					"<td>"+ item.inverter_id + "</td>" +
				 "</tr>";
					$list.find('tbody').append($rowSample);
					
//					isTableEmpty = false;

				}
				
			}
			
			/*$list.find('tbody').empty();
			if(list.length == 0){
				var $rowSample = "<tr class='model-List' style='text-align: center;'>" + 
					"<td colspan='2'>검색 결과가 없습니다.</td>" +
					"</tr>";		
	
				$list.find('tbody').append($rowSample);
				return;
			}
			
			console.log("인버터 리스트", list);
			var length = list.length;
			var $list = $("#inverterModel");
			
			var $rowSample = "<tr class='model-List' style='text-align: center;'>" + 
								"<td colspan='2'>검색 결과가 없습니다.</td>" +
							 "</tr>";		
			
			$list.find('tbody').append($rowSample);
			
//			var isTableEmpty = true;

			for(var i=0; i<length; i++) {
				var item = list[i];
				
				if(undefined == $(".inverterList").find('.inverterId-'+item.inverter_id)[0]) {
//					if(isTableEmpty == true) {
//						$list.find('tbody').empty();
//					}
					
					var $rowSample = "<tr class='model-List inverterId-" + item.inverter_id + "' data-inverterid='" + item.inverter_id + "'>" +
					"<input type='hidden' name='inverterId' value='"+ item.inverter_id +"'>" + 
					"<td class='txC ft12'>"+ "<input type='checkbox' name='inverterIdCheck' value='" + item.inverter_id + "'>" + "</td>" +
					"<td>"+ item.inverter_id + "</td>" +
				 "</tr>";
					$list.find('tbody').append($rowSample);
					
//					isTableEmpty = false;

				}
				
			}*/
			
			
		}, error:function(error){
			alert("검색에 실패 하였습니다.");
		}
	});
}


function inverterApply(){
	
	var form = $("#selectCheckForm");
	
	var length = form.find('li').length;
	
	var $form = $('.inverterListForm');

	for(var i=0; i<length; i++) {
		
		var inverterList = $form.find('.inverterList');
		
		var inverterId = $('.selectedCheck').find('li:eq('+i+')').find('input[name="inverterId"]').val();
		
		var groupHtml = "<div class='inverter inverterId-" + inverterId + "'>" + "</div>";
		inverterList.append(groupHtml);
		
		inverterItem = inverterList.find('.inverterId-' + inverterId);
		var groupTextHtml = "<input type='text' class='inputField inverterId' name='inverterId' readonly='readonly' value='" + inverterId + "'/>";
		var groupTextIdHtml = "<input type='hidden' name='inverterId' value='" + inverterId + "'/>";
		var groupBtnHtml = "<input type='button' class='btn deleteBtn' data-inverter-del-btn='" + inverterId + "' value='- 제거'>";
		inverterItem.append(groupTextHtml);
		inverterItem.append(groupTextIdHtml);
		inverterItem.append(groupBtnHtml);
	}
	
	$('#inverterModel').modal('hide');
	$('.selectedCheck').find('.selectUl').empty();
	inverterSearch();

	
}