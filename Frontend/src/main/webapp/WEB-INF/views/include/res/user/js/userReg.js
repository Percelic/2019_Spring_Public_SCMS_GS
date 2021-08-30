$(document).ready(function(){
	$('.leftmenu').find('div[data-sidebar-name="manage"]').addClass("active");

	$(".groupListForm").hide();
	$(".trackerListForm").hide();
});


// 아이디 사용가능 여부
var isUserId = false;

// 아이디 중복확인
$("#inputForm").on('click', 'input[data-userid-check-btn]', function(){
	userIdCheck();
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

$("#inputForm").on('change', 'select[name="role"]', function(){
	var role = $(this).val();
	setRoleBox(role);
});

//군집 제거
$(".groupList").on('click', 'input[data-group-del-btn]', function(){
	var groupId = $(this).data('group-del-btn');
	$(".groupList").find('.trackerGroupId-' + groupId).remove();
	groupSearch($('.modalSearchForm'), "search");
});

// 트래커 제거
$(".trackerList").on('click', 'input[data-tracker-del-btn]', function(){
	var trackerId = $(this).data('tracker-del-btn');
	$(".trackerList").find('.trackerId-' + trackerId).remove();
});

//KEY 이벤트 처리
$("#inputForm").on('keyup', 'input[name="userId"]', function(){
	var regExp = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]|\s|\//g;
	$(this).val($(this).val().replace(regExp, ''));
	isUserId = false;
	
}).on('keyup', 'input[name="phone"]', function(event){
	
	var pattern = /[^(0-9)|\-]/gi;   // 숫자, - 이외는 제거
	 
	if(pattern.test($(this).val())){
		$(this).val($(this).val().replace(pattern, ''));
	}
});

function setRoleBox(role){
	if("system_admin" == role) {
		$(".groupListForm").hide();
		$(".trackerListForm").hide();
	} else if("tracker_group_admin" == role) {
		$(".groupListForm").show();
		$(".trackerListForm").hide();
		groupSearch($('.modalSearchForm'), "search");
		
	} else if("tracker_admin" == role) {
		$(".groupListForm").hide();
		$(".trackerListForm").show();
	}
}

function groupApply(formId){
	
	var $form = $('#'+formId);
	var groupList = $form.find('.groupList');
	var $model = $('#groupModel');
	var groupId = $model.find('input[name="trackerGroupId"]').val();
	var groupName = $model.find('input[name="trackerGroupName"]').val();
	var groupItem = groupList.find('.groupId-' + groupId);
	
	if(groupItem.length > 0){
		$(".groupList").find('.groupId-' + groupId).remove();
	}
	
	var groupHtml = "<div class='groupId-" + groupId + "'>" + "</div>";
	groupList.append(groupHtml);
	
	groupItem = groupList.find('.groupId-' + groupId);
	var groupInputHtml = "<input type='text' class='inputField inverterId' readonly='readonly' value='" + groupName + " ( "+ groupId + " )'/>";
	var groupTextHtml = "<input type='hidden' name='trackerGroupId'value='" + groupId + "'/>";
	var groupBtnHtml = "<input type='button' class='btn overlap expand' data-group-del-btn='" + groupId + "' value='- 군집 제거'>";
	groupItem.append(groupInputHtml);
	groupItem.append(groupTextHtml);
	groupItem.append(groupBtnHtml);
}

// 사용자 아이디 중복 확인
function userIdCheck(){
	var form = $("#inputForm");
	var userId = form.find('input[name="userId"]');
	userId.val(userId.val().trim());
	
	$('.userIdDesc').removeClass('text-success');
	
	if("" == userId.val()) {
		$('.userIdDesc').text('아이디를 입력하여 주십시오.');
		userId.addClass('input-warning');
		return false;
	}
	
	var pattern = /\s/g;
    
	if( userId.val().match(pattern)) {
		$('.userIdDesc').text('아이디에는 공백을 사용할 수 없습니다.');
		userId.addClass('input-warning');
		return false;
	}
	
	$.ajax({
		type:"GET",
		url	: _cp + '/user/userIdCheck',
		data: {userId : userId.val()},
		async:false,
		success:function(data) {
			if(data > 0){
				isUserId = false;
				$('.userIdDesc').text('사용중인 아이디 입니다.');
				userId.addClass('input-warning');
			} else {
				isUserId = true;
				$('.userIdDesc').text('사용가능한 아이디 입니다.');
				userId.removeClass('input-warning');
				$('.userIdDesc').addClass('text-success');
			}
		}, error:function(error) {
			
		}
	});
}

// 사용자 등록 체크
function regCheck(){
	var isCheck = true;
	var form = $("#inputForm");
	form.find('p').text('');
	
	$('.input-warning').removeClass('input-warning');
	$('.userIdDesc').removeClass('text-success');
	
	var userId = form.find('input[name="userId"]');
	if("" == userId.val()) {
		userId.addClass('input-warning');
		$('.userIdDesc').text('아이디를 입력하여 주십시오.');
		isCheck = false;
	}
	
	if((false == isUserId) &&  ("" != userId.val())){
		userId.addClass('input-warning');
		$('.userIdDesc').text('아이디 중복확인을 하여 주십시오.');
		isCheck = false;
	}
	
	var passwd = form.find('input[name="passwd"]');
	if("" == passwd.val()) {
		passwd.addClass('input-warning');
		$('.passwdDesc').text('패스워드를 입력하여 주십시오.');
		isCheck = false;
	}
	
	var passwdCheck = form.find('input[name="passwdCheck"]');
	if("" == passwdCheck.val()) {
		passwdCheck.addClass('input-warning');
		$('.passwdCheckDesc').text('패스워드확인을 입력하여 주십시오.');
		isCheck = false;
	}
	
	if(passwd.val() != passwdCheck.val()){
		passwd.addClass('input-warning');
		passwdCheck.addClass('input-warning');
		$('.passwdDesc').text('비밀번호와 확인값이 다릅니다. 비밀번호를 다시 입력하여 주십시오.');
		
		passwd.val('');
		passwdCheck.val('');
		isCheck = false;
	}
	
	var username = form.find('input[name="username"]');
	if("" == username.val()) {
		username.addClass('input-warning');
		$('.usernameCheckDesc').text('이름을 입력하여 주십시오.');
		isCheck = false;
	}
	
	var phone = form.find('input[name="phone"]');
	if("" == phone.val()) {
		phone.addClass('input-warning');
		$('.phoneCheckDesc').text('연락처를 입력하여 주십시오.');
		isCheck = false;
	}
	
//	var regExp = /^\d{3}-\d{3,4}-\d{4}$/;
//	if ((!regExp.test(phone.val())) && ("" != phone.val())) {
//		phone.addClass('input-warning');
//		$('.phoneDesc').text('잘못된 휴대폰 번호입니다. 숫자, - 를 포함한 숫자만 입력하여주십시오.');
//		isCheck = false;
//	}
	
	var email = form.find('input[name="email"]');
	if("" == email.val()) {
		email.addClass('input-warning');
		$('.emailCheckDesc').text('이메일을 입력하여 주십시오.');
		isCheck = false;
	}
	
	var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i; 
	if ((!regExp.test(email.val())) && ("" != email.val())) {
		email.addClass('input-warning');
		$('.emailCheckDesc').text('잘못된 이메일 입니다. 다시 입력하여 주십시오.');
		isCheck = false;
	}
	
	return isCheck;
}


function reg(regType){
	
	var params = $("#inputForm").serialize();

	$.ajax({
		type:"POST",
		url	: _cp + '/user/reg',
		data: params,
		success:function() {
			if("listmove" == regType){
				alert('사용자 등록에 성공 하였습니다.');
				location.replace(_cp + '/user/list'); 
			} else {
				alert('사용자 등록에 성공 하였습니다.');
				window.location.reload();
			}
		}, error:function(error) {
			alert('사용자 등록에 실패 하였습니다.');
		}
	});
	
}
