$(document).ready(function(){
	$('.leftmenu').find('div[data-sidebar-name="manage"]').addClass("active");
	inverterSearch();
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

// 인버터 제거
$(".inverterList").on('click', 'input[data-inverter-del-btn]', function(){
	var inverterId = $(this).data('inverter-del-btn');
	$(".inverterList").find('.inverterId-' + inverterId).remove();
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
	
	var trackerName = form.find('input[name="trackerName"]');
	if("" == trackerName.val()) {
		trackerName.addClass('input-warning');
		$('.trackerNameDesc').text('트레커 모델명을 입력하여 주십시오.');
		isCheck = false;
	}
	
	var capacity = form.find('input[name="capacity"]');
	if("" == capacity.val()) {
		capacity.addClass('input-warning');
		$('.capacityDesc').text('트레커 용량을 입력하여 주십시오.');
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
		url	: _cp + '/tracker/modify',
		data: params,
		success:function() {
			alert('트래커 수정에 성공 하였습니다.');
			location.replace(_cp + '/tracker/list'); 

		}, error:function(error) {
			alert('트래커 수정에 실패 하였습니다.');
		}
	});
	
}