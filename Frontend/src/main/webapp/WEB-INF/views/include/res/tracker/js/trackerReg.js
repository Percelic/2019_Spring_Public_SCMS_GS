$(document).ready(function(){
	$('.leftmenu').find('div[data-sidebar-name="manage"]').addClass("active");
});


// 아이디 사용가능 여부
var isTrackerId = false;

// 아이디 중복확인
$("#inputForm").on('click', 'input[data-trackerid-check-btn]', function(){
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

// 인버터 제거
$(".inverterList").on('click', 'input[data-inverter-del-btn]', function(){
	var inverterId = $(this).data('inverter-del-btn');
	$(".inverterList").find('.inverterId-' + inverterId).remove();
	
	inverterSearch();
});




// 인버터 추가
$(".inputForm").on('click', 'input[data-inverter-add-btn]', function(){
	var form = $("#inputForm");

	var trackerGroupName = form.find('input[name="trackerGroupName"]');
	if("" == trackerGroupName.val()) {
		trackerGroupName.addClass('input-warning');
		$('.trackerGroupNameDesc').text('군집 이름을 입력하여 주십시오.');
		return false;
	} else {
		trackerGroupName.removeClass('input-warning');
		$('.trackerGroupNameDesc').text('');
	}
	
	$("#inverterModel").modal();
	
});


//KEY 이벤트 처리
$("#inputForm").on('keyup', 'input[name="trackerId"]', function(){
	var regExp = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]|\s|\//g;
	$(this).val($(this).val().replace(regExp, ''));
	isTrackerId = false;
	
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

function groupApply(formid){
	
	var $form = $('#'+formid);
	var $model = $('#groupModel');
	
	$form.find('input[name="trackerGroupId"]').val($model.find('input[name="trackerGroupId"]').val());
	$form.find('input[name="trackerGroupName"]').val($model.find('input[name="trackerGroupName"]').val());
	$model.modal('hide');
	
	inverterSearch();
}

// 아이디 중복 확인
function idCheck(){
	var form = $("#inputForm");
	
	$('.trackerIdDesc').removeClass('text-success');

	var trackerId = form.find('input[name="trackerId"]');
	
	var trackerGroupId = form.find('input[name="trackerGroupId"]');
	if("" == trackerGroupId.val()) {
		$('.trackerIdDesc').text('군집 명을 입력하여 주십시오.');
		trackerId.removeClass('input-warning');
		trackerId.addClass('input-warning');
		return false;
	}
	
	
	
	trackerId.val(trackerId.val().trim());
	
	
	if("" == trackerId.val()) {
		$('.trackerIdDesc').text('아이디를 입력하여 주십시오.');
		return false;
	}
	
	var pattern = /\s/g;
    
	if( trackerId.val().match(pattern)) { 
		$('.trackerIdDesc').text('아이디에는 공백을 사용할 수 없습니다.');
		return false;
	}
	
	$.ajax({
		type:"GET",
		url	: _cp + '/tracker/trackerIdCheck',
		data: {trackerId : trackerId.val(), trackerGroupId : trackerGroupId.val()},
		async:false,
		success:function(data) {
			if(data > 0){
				isTrackerId = false;
				$('.trackerIdDesc').text('사용중인 아이디 입니다.');
				trackerId.addClass('input-warning');
			} else {
				isTrackerId = true;
				$('.trackerIdDesc').text('사용가능한 아이디 입니다.');
				trackerId.removeClass('input-warning');
				$('.trackerIdDesc').addClass('text-success');
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
	$('.input-warning').removeClass('input-warning');
	$('.trackerIdDesc').removeClass('text-success');
	
	var trackerGroupName = form.find('input[name="trackerGroupName"]');
	if("" == trackerGroupName.val()) {
		trackerGroupName.addClass('input-warning');
		$('.trackerGroupNameDesc').text('군집 이름을 입력하여 주십시오.');
		isCheck = false;
	}
	
	var trackerId = form.find('input[name="trackerId"]');
	if("" == trackerId.val()) {
		trackerId.addClass('input-warning');
		$('.trackerIdDesc').text('트레커 아이디를 입력하여 주십시오.');
		isCheck = false;
	}
	
	if((false == isTrackerId) &&  ("" != trackerId.val())){
		trackerId.addClass('input-warning');
		$('.trackerIdDesc').text('아이디 중복확인을 하여 주십시오.');
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
		url	: _cp + '/tracker/reg',
		data: params,
		success:function() {
			if("listmove" == regType){
				alert('트래커 등록에 성공 하였습니다.');
				location.replace(_cp + '/tracker/list'); 
			} else {
				alert('트래커 등록에 성공 하였습니다.');
				var form = $("#inputForm");
				var trackerId = form.find('input[name="trackerId"]');
				
				trackerId.val('');
				isTrackerId = false;
				$('.inverterList').empty();
				inverterSearch();
			}
		}, error:function(error) {
			alert('트래커 등록에 실패 하였습니다.');
		}
	});
	
}
