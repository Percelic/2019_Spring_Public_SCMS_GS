$(document).ready(function(){
	$('.leftmenu').find('div[data-sidebar-name="manage"]').addClass("active");
});


// 아이디 사용가능 여부
var isInverterId = false;

// 아이디 중복확인
$("#inputForm").on('click', 'input[data-inverterid-check-btn]', function(){
	idCheck();
});

// 취소
$(".btnRec").on('click', 'button[data-cancel-btn]', function(){
	history.back();

// 등록
}).on('click', 'button[data-reg-btn]', function(){
	if(true == regCheck()){
		reg($(this).data('reg-btn'));
	}
});

// KEY 이벤트 처리
$("#inputForm").on('keyup', 'input[name="inverterId"]', function(){
	var regExp = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]|\s|\//g;
	$(this).val($(this).val().replace(regExp, ''));
	isInverterId = false;
	
}).on('keyup', 'input[name="phone"]', function(event){
	
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

function groupApply(formid) {
	
	var $form = $('#'+formid);
	var $model = $('#groupModel');
	
	$form.find('input[name="trackerGroupId"]').val($model.find('input[name="trackerGroupId"]').val());
	$form.find('input[name="trackerGroupName"]').val($model.find('input[name="trackerGroupName"]').val());
	$model.modal('hide');
}

// 아이디 중복 확인
function idCheck() {
	
	$('.inverterIdDesc').removeClass('text-success');
	
	var form = $("#inputForm");
	var inverterId = form.find('input[name="inverterId"]');
	
	var trackerGroupId = form.find('input[name="trackerGroupId"]');
	if("" == trackerGroupId.val()) {
		$('.inverterIdDesc').text('군집 명을 입력하여 주십시오.');
		inverterId.removeClass('input-warning');
		inverterId.addClass('input-warning');
		return false;
	}
	
	
	inverterId.val(inverterId.val().trim());
	
	if("" == inverterId.val()) {
		$('.inverterIdDesc').text('아이디를 입력하여 주십시오.');
		inverterId.addClass('input-warning');
		return false;
	}
	
	var pattern = /\s/g;
    
	if( inverterId.val().match(pattern)) { 
		$('.inverterIdDesc').text('아이디에는 공백을 사용할 수 없습니다.');
		inverterId.addClass('input-warning');
		return false;
	}
	
	$.ajax({
		type:"GET",
		url	: _cp + '/inverter/inverterIdCheck',
		data: {inverterId : inverterId.val(), trackerGroupId : trackerGroupId.val()},
		async:false,
		success:function(data) {
			if(data > 0){
				isInverterId = false;
				$('.inverterIdDesc').text('사용중인 아이디 입니다.');
				inverterId.addClass('input-warning');
			} else {
				isInverterId = true;
				$('.inverterIdDesc').text('사용가능한 아이디 입니다.');
				inverterId.removeClass('input-warning');
				$('.inverterIdDesc').addClass('text-success');
				
			}
		}, error:function(error) {
			
		}
	});
}

// 등록 체크
function regCheck(){
	var isCheck = true;
	var form = $("#inputForm");
	form.find('p').text('');
	$('.inverterIdDesc').removeClass('text-success');
	
	var trackerGroupName = form.find('input[name="trackerGroupName"]');
	if("" == trackerGroupName.val()) {
		trackerGroupName.addClass('input-warning');
		$('.trackerGroupNameDesc').text('군집 이름을 입력하여 주십시오.');
		isCheck = false;
	}
	
	var inverterId = form.find('input[name="inverterId"]');
	if("" == inverterId.val()) {
		inverterId.addClass('input-warning');
		$('.inverterIdDesc').text('인버터 아이디를 입력하여 주십시오.');
		isCheck = false;
	}
	
	if((false == isInverterId) &&  ("" != inverterId.val())){
		inverterId.addClass('input-warning');
		$('.inverterIdDesc').text('아이디 중복확인을 하여 주십시오.');
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


function reg(regType){
	
	var params = $("#inputForm").serialize();

	$.ajax({
		type:"POST",
		url	: _cp + '/inverter/reg',
		data: params,
		success:function() {
			if("listmove" == regType){
				alert('인버터 등록에 성공 하였습니다.');
				location.replace(_cp + '/inverter/list'); 
			} else {
				alert('인버터 등록에 성공 하였습니다.');
				var form = $("#inputForm");
				var inverterId = form.find('input[name="inverterId"]');
				
				inverterId.val('');
				isInverterId = false;
			}
		}, error:function(error) {
			alert('인버터 등록에 실패 하였습니다.');
		}
	});
	
}
