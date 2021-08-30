$(document).ready(function(){
	$('.leftmenu').find('div[data-sidebar-name="manage"]').addClass("active");
});

// 취소
$(".btnRec").on('click', 'button[data-cancel-btn]', function(){
	history.back();

// 수정
}).on('click', 'button[data-modify-btn]', function(){
	if(true == modifyCheck()){
		modify();
	}

});

//KEY 이벤트 처리
$("#inputForm").on('keyup', 'input[name="phone"]', function(event){
	
	var pattern = /[^(0-9)|\-]/gi;   // 숫자, - 이외는 제거
	 
	if(pattern.test($(this).val())){
		$(this).val($(this).val().replace(pattern, ''));
	}
}).on('keyup', 'input[name="capacity"]', function(event){
	
	var pattern = /[^(0-9)|\.]/gi;   // 숫자, - 이외는 제거
	 
	if(pattern.test($(this).val())){
		$(this).val($(this).val().replace(pattern, ''));
	}
});

function groupApply(formid){
	
	var $form = $('#'+formid);
	var $model = $('#groupModel');
	
	$form.find('input[name="trackerGroupId"]').val($model.find('input[name="trackerGroupId"]').val());
	$form.find('input[name="trackerGroupName"]').val($model.find('input[name="trackerGroupName"]').val());
	$model.modal('hide');
}

//군집 수정 체크
function modifyCheck(){
	var isCheck = true;
	var form = $("#inputForm");
	form.find('p').text('');
	$('.input-warning').removeClass('input-warning');
	
	
	var trackerGroupName = form.find('input[name="trackerGroupName"]');
	if("" == trackerGroupName.val()) {
		trackerGroupName.addClass('input-warning');
		$('.trackerGroupNameDesc').text('군집 이름을 입력하여 주십시오.');
		isCheck = false;
	}
	
	var inverterName = form.find('input[name="inverterName"]');
	if("" == inverterName.val()) {
		inverterName.addClass('input-warning');
		$('.inverterNameDesc').text('인버터 모델명을 입력하여 주십시오.');
		isCheck = false;
	}
	
	var capacity = form.find('input[name="capacity"]');
	if("" == capacity.val()) {
		capacity.addClass('input-warning');
		$('.capacityDesc').text('인버터 용량을 입력하여 주십시오.');
		isCheck = false;
	}
	
	var builder = form.find('input[name="builder"]');
	if("" == builder.val()) {
		builder.addClass('input-warning');
		$('.builderDesc').text('시공사를 입력하여 주십시오.');
		isCheck = false;
	}
	
	var completionDate = form.find('input[name="completionDate"]');
	if("" == completionDate.val()) {
		completionDate.addClass('input-warning');
		$('.completionDateDesc').text('준공일을 입력하여 주십시오.');
		isCheck = false;
	}
	
	var manager = form.find('input[name="manager"]');
	if("" == manager.val()) {
		manager.addClass('input-warning');
		$('.managerDesc').text('관리인을 입력하여 주십시오.');
		isCheck = false;
	}
	
	var owner = form.find('input[name="owner"]');
	if("" == owner.val()) {
		owner.addClass('input-warning');
		$('.ownerDesc').text('소유주를 입력하여 주십시오.');
		isCheck = false;
	}
	
	var phone = form.find('input[name="phone"]');
	if("" == phone.val()) {
		phone.addClass('input-warning');
		$('.phoneDesc').text('연락처를 입력하여 주십시오.');
		isCheck = false;
	}
	
//	var regExp = /^\d{3}-\d{3,4}-\d{4}$/;
//	if ((!regExp.test(phone.val())) && ("" != phone.val())) {
//		phone.addClass('input-warning');
//		$('.phoneDesc').text('잘못된 휴대폰 번호입니다. 숫자, - 를 포함한 숫자만 입력하여주십시오.');
//		isCheck = false;
//	}
	
	var installDate = form.find('input[name="installDate"]');
	if("" == installDate.val()) {
		installDate.addClass('input-warning');
		$('.installDateDesc').text('설치일자를 입력하여 주십시오.');
		isCheck = false;
	}
	
	return isCheck;
	
}

function modify(){
	
	var params = $("#inputForm").serialize();

	$.ajax({
		type:"POST",
		url	: _cp + '/inverter/modify',
		data: params,
		success:function() {
			alert('인버터 수정에 성공 하였습니다.');
			location.replace(_cp + '/inverter/list'); 

		}, error:function(error) {
			alert('인버터 수정에 실패 하였습니다.');
		}
	});
	
}