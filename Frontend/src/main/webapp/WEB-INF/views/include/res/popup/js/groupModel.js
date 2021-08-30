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

$('#groupModel').on('click', 'button[name="searchButton"]', function(){
	groupSearch($('.modalSearchForm'), "search");
});

$('#groupModel').find('input[name="searchWord"]').keypress(function (event){
	if(event.keyCode==13) {
		groupSearch($('.modalSearchForm'), "search");
	}
});

$('#groupModel').on('click', 'button[name="apply"]', function(){
	apply();
});

$('.selectedCheck').on('click', 'button[data-selcheck-del-btn]', function(){
	var $this = $(this);
	var trackerGroupId = $this.data('selcheck-del-btn');
	$(".selectedCheck").find('.trackerGroupId-'+trackerGroupId).remove();
	
	$(".modelList").find('.trackerGroupId-'+trackerGroupId).find('input[name="trackerGroupIdCheck"]')[0].checked = false;
});

$(".modelList").on('click', 'input[name="trackerGroupIdCheck"]', function() {
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
	var trackerGroupId = $this.data('trackergroupid');

	var isChecked = $this.find('input[name="trackerGroupIdCheck"]')[0];
	if (isChecked != undefined) {
		if(isChecked.checked) {
			isChecked.checked = false;
			
			$(".selectedCheck").find('.trackerGroupId-'+trackerGroupId).remove();
			
		} else {
			isChecked.checked = true;
			var trackerGroupId = $this.find('input[name="trackerGroupId"]').val();
			var trackerGroupName = $this.find('input[name="trackerGroupName"]').val();
			
			
			var trSel = "<li class='select-check trackerGroupId-" + trackerGroupId +"'>" +
					"<input type='hidden' name='trackerGroupId' value='" + trackerGroupId + "'>" +
					"<input type='hidden' name='trackerGroupName' value='" + trackerGroupName + "'>" +
						"<p class='name'>" + trackerGroupName + "</p>" +
						"<button class='delBtn' type='button' data-selcheck-del-btn='" + trackerGroupId + "'>" +
						"<i class='fa fa-trash-o'></i>" +
						"</button>" +
						"</li>";

			$('.select-body').find('ul').append(trSel);
			
		}
	}

});



function groupSearch(form, type) {

	var searchWord = form.find('input[name="searchWord"]').val();
	var pageNo = form.find('input[name="pageNo"]').val();
	var pageSize = form.find('input[name="pageSize"]').val();
	
	$.ajax({
		type:"GET",
		url	:_cp + "/group/trackerGroupModelList",
		data: {searchWord : searchWord, pageNo : pageNo, pageSize : pageSize},
		success:function(list){
			
			
			var length = list.length;
			var $list = $("#groupModel");
			
			$list.find('tbody').empty();
				
			var $rowSample = "<tr class='model-List' style='text-align: center;'>" + 
								"<td colspan='3'>검색 결과가 없습니다.</td>" +
							 "</tr>";				
			$list.find('tbody').append($rowSample);
			
			var isTableEmpty = true;
			for(var i=0; i<length; i++) {
				var item = list[i];
				
				if(undefined == $(".groupList").find('.trackerGroupId-'+item.tracker_group_id)[0]){
					if(isTableEmpty == true) {
						$list.find('tbody').empty();
					}
					
					
					var $rowSample = "<tr class='model-List trackerGroupId-" + item.tracker_group_id + "' data-trackergroupid='" + item.tracker_group_id + "'>" +
					"<input type='hidden' name='trackerGroupId' value='"+ item.tracker_group_id +"'>" + 
					"<input type='hidden' name='trackerGroupName' value='"+ item.tracker_group_name +"'>" + 
					"<td class='txC ft12'>"+ "<input type='checkbox' name='trackerGroupIdCheck' value='" + item.tracker_group_id + "'>" + "</td>" +
					"<td>"+ item.tracker_group_id + "</td>" +
					"<td>"+ item.tracker_group_name + "</td>" +
				 "</tr>";
					$list.find('tbody').append($rowSample);
					
					isTableEmpty = false;
				}
				
			}
			
			
			var $form = $('#modalPagingForm');
			$form.find('input[name="searchWord"]').val(searchWord);
			$form.find('input[name="pageNo"]').val(pageNo);
			$form.find('input[name="pageSize"]').val(pageSize);
			
			$.ajax({
				type:"GET",
				url	:_cp + "/group/trackerGroupModelList/pageing",
				data: {searchWord : searchWord, pageNo : pageNo, pageSize : pageSize},
				success:function(data){
					
					setPageForm(data);
					
				}, error:function(error){
					alert("검색에 실패 하였습니다.");
				}
			});
			
			
		}, error:function(error){
			alert("검색에 실패 하였습니다.");
		}
	});
}


function setPageForm(paging){
	var divPaging = $('#modalPaging');
	var html = "";
	
	html += "<ul class=''>";
	for (var i=paging.sheetStartPageNo; i<=paging.sheetEndPageNo; i++) {
   			
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

function apply(){
	
	var form = $("#selectCheckForm");
	
	var length = form.find('li').length;
	
	var $form = $('.groupListForm');

	for(var i=0; i<length; i++) {
		
		var groupList = $form.find('.groupList');
		
		var trackerGroupId = $('.selectedCheck').find('li:eq('+i+')').find('input[name="trackerGroupId"]').val();
		var trackerGroupName = $('.selectedCheck').find('li:eq('+i+')').find('input[name="trackerGroupName"]').val();
		
		
		var groupHtml = "<div class='groupGroup trackerGroupId-" + trackerGroupId + "'>" + "</div>";
		groupList.append(groupHtml);
		
		groupItem = groupList.find('.trackerGroupId-' + trackerGroupId);
//		var groupTextHtml = "<input type='text' class='inputField inverterId' name='trackerGroupName' readonly='readonly' value='" + trackerGroupName + " ( " + trackerGroupId + " )'/>";
		var groupTextHtml = "<input type='text' class='inputField' name='trackerGroupName' readonly='readonly' value='" + trackerGroupName + "'/>";
		var groupTextIdHtml = "<input type='hidden' name='trackerGroupId' value='" + trackerGroupId + "'/>";
		var groupBtnHtml = "<input type='button' class='btn deleteBtn' data-group-del-btn='" + trackerGroupId + "' value='- 제거'>";
		groupItem.append(groupTextHtml);
		groupItem.append(groupTextIdHtml);
		groupItem.append(groupBtnHtml);
	}
	
	$('#groupModel').modal('hide');
	$('.selectedCheck').find('.selectUl').empty();
	groupSearch($('.modalSearchForm'), "search");

	
}