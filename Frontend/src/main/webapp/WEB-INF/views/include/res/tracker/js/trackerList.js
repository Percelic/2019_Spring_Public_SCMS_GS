$(document).ready(function(){
	$('.leftmenu').find('div[data-sidebar-name="manage"]').addClass("active");
});


// 상세보기로 이동
$('.tableBody').on('click', 'td[data-detailid]', function(){
	var detailId = $(this).data('detailid');
	var trackerGroupId = $(this.closest("tr")).find("[name='trackerGroupId']").val();
	location.href = _cp + "/tracker/detail/"+ trackerGroupId + "/" + detailId;

// 체크박스 선택	
}).on('click', 'input[name="trackerId"]', function(){
	
	var $this = $(this);
	var isChecked = $this[0];
	
	checked(isChecked);
}).on('click', 'td[data-checked]', function(){
	var $this = $(this);
	var isChecked = $this.find('input[name="trackerId"]')[0];
	if (isChecked != undefined) {
		checked(isChecked);
		checked($this.find('input[name="trackerGroupId"]')[0]);
	}
});

//등록
$('.btnRec').on('click', 'button[data-reg]', function(){
	location.href = _cp + "/tracker/reg";
});

// 삭제
$('.btnRec').on('click', 'button[data-del]', function(){
	del();
});


function checked(isChecked){
	if(isChecked.checked){
		isChecked.checked = false;
	} else {
		isChecked.checked = true;
	}
}

function del(){
	
	var check = $('.tableBody').find('input[name="trackerId"]');
	var delCnt = 0;
	for(var i=0; i<check.length; i++){
		if(check[i].checked == true){
			delCnt = delCnt + 1
		}
	}
	
	var retVal = confirm("선택된 "+ delCnt +"개의 트래커를 삭제합니다.\n삭제 하신 후에는 복구 할 수 없습니다.");
	if (retVal == false) {
		return false;
	}

	var params = $("#tableForm").serialize();

	$.ajax({
		type:"POST",
		url	: _cp + '/tracker/del',
		data: params,
		success:function() {
			alert('트래커 삭제에 성공 하였습니다.');
			location.replace(_cp + '/tracker/list'); 
		}, error:function(error) {
			alert('트래커 삭제에 실패 하였습니다.');
		}
	});
}
