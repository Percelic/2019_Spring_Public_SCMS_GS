$(document).ready(function(){
	$('.leftmenu').find('div[data-sidebar-name="manage"]').addClass("active");
});


// 상세보기로 이동
$('.tableBody').on('click', 'td[data-detailid]', function(){
	var detailId = $(this).data('detailid');
	
	location.href = _cp + "/group/detail/" + detailId;
	
// 체크박스 선택	
}).on('click', 'input[name="trackerGroupId"]', function(){
	
	var $this = $(this);
	var isChecked = $this[0];
	
	groupChecked(isChecked);
}).on('click', 'td[data-checked]', function(){
	var $this = $(this);
	var isChecked = $this.find('input[name="trackerGroupId"]')[0];
	if (isChecked != undefined) {
		groupChecked(isChecked);
	}
});

//등록
$('.btnRec').on('click', 'button[data-reg]', function(){
	location.href = _cp + "/group/reg";
});

// 삭제
$('.btnRec').on('click', 'button[data-del]', function(){
	groupDel();
});

function groupChecked(isChecked){
	if(isChecked.checked){
		isChecked.checked = false;
	} else {
		isChecked.checked = true;
	}
}

function groupDel(){
	
	var groupCheck = $('.tableBody').find('input[name="trackerGroupId"]');
	var delCnt = 0;
	for(var i=0; i<groupCheck.length; i++){
		if(groupCheck[i].checked == true){
			delCnt = delCnt + 1
		}
	}
	
	var retVal = confirm("선택된 "+ delCnt +"개의 군집을 삭제합니다.\n군집 삭제시 하위 트래커 인버터도 삭제됩니다.\n삭제 하신 후에는 복구 할 수 없습니다.");
	if (retVal == false) {
		return false;
	}

	var params = $("#tableForm").serialize();

	$.ajax({
		type:"POST",
		url	: _cp + '/group/del',
		data: params,
		success:function() {
			alert('군집 삭제에 성공 하였습니다.');
			location.replace(_cp + '/group/list'); 
		}, error:function(error) {
			alert('군집 삭제에 실패 하였습니다.');
		}
	});
}