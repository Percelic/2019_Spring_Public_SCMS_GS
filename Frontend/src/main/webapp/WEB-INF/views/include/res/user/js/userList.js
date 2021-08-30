$(document).ready(function(){
	$('.leftmenu').find('div[data-sidebar-name="manage"]').addClass("active");
});

// 상세보기로 이동
$('.tableBody').on('click', 'td[data-detailid]', function(){
	var detailId = $(this).data('detailid');
	
	location.href = _cp + "/user/detail/" + detailId;
	
// 체크박스 선택	
}).on('click', 'input[name="userId"]', function(){
	
	var $this = $(this);
	var isUserChecked = $this[0];
	
	userChecked(isUserChecked);
}).on('click', 'td[data-checked]', function(){
	var $this = $(this);
	var isUserChecked = $this.find('input[name="userId"]')[0];
	if (isUserChecked != undefined) {
		userChecked(isUserChecked);
	}
});


// 등록
$('.btnRec').on('click', 'button[data-reg]', function(){
	location.href = _cp + "/user/reg";
});

// 삭제
$('.btnRec').on('click', 'button[data-del]', function(){
	userDel();
});

function userChecked(isUserChecked){
	if(isUserChecked.checked){
		isUserChecked.checked = false;
	} else {
		isUserChecked.checked = true;
	}
}

function userDel(){
	
	var userCheck = $('.tableBody').find('input[name="userId"]');
	var delUserCnt = 0;
	for(var i=0; i<userCheck.length; i++){
		if(userCheck[i].checked == true){
			delUserCnt = delUserCnt + 1
		}
	}
	
	var retVal = confirm("선택된 "+ delUserCnt +"명의 사용자를 삭제합니다.\n삭제 하신 후에는 복구 할 수 없습니다.");
	if (retVal == false) {
		return false;
	}

	var params = $("#tableForm").serialize();

	$.ajax({
		type:"POST",
		url	: _cp + '/user/del',
		data: params,
		success:function() {
			alert('사용자 삭제에 성공 하였습니다.');
			location.replace(_cp + '/user/list'); 
		}, error:function(error) {
			alert('사용자 삭제에 실패 하였습니다.');
		}
	});
}