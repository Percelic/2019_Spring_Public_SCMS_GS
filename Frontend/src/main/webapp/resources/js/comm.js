

$(document).ready(function () {
	var menu = $("#hidden_menu_id").val();
	
	$("#child_menu li").removeClass("current-page");
	if(menu == "user") {
		$("#child_menu").show();
		$("#" + menu + "_menu_li").addClass("current-page");
	} else if(menu == "group") {
		$("#child_menu").show();
		$("#" + menu + "_menu_li").addClass("current-page");
	} else if(menu == "inverter") {
		$("#child_menu").show();
		$("#" + menu + "_menu_li").addClass("current-page");
	} else if(menu == "tracker") {
		$("#child_menu").show();
		$("#" + menu + "_menu_li").addClass("current-page");
	}
	
});
function leftMenuDown() {
	var display = $("#child_menu").css("display");
	
	if(display == "none") {
		$("#child_menu").slideDown();
	} else {
		$("#child_menu").slideUp();
	}
	window.event.cancelBubble=true;
}

//popup show
function pwPop() {
	var comm01 = $("#comm_01").val();
	var comm02 = $("#comm_02").val();
	var comm03 = $("#comm_03").val();
	var comm04 = $("#comm_04").val();
	var comm05 = $("#comm_05").val();
	var comm06 = $("#comm_06").val();
	var comm07 = $("#comm_07").val();
	var en24 = $("#en_24").val();
	var cont = "";

	cont += "<section class='pup_form' id='pop_pwd'>";
	cont += "	<div class='pup_box'>";
	cont += "		<h2 class='pup_header'>" + comm01 + "</h2>";
	cont += "		<div class='pup_body'>";
	cont += "			<div class='form-group'>";
	cont += "				<label for='now_pwd'>" + comm02 +  "</label>";
	cont += "				<input type='password' id='now_pwd' class='pop_update_pwd' maxlength='20' title=" + en24 + " />";
	cont += "			</div>";
	cont += "			<div class='form-group'>";
	cont += "				<label for='new_pwd'>" + comm03 + "</label>";
	cont += "				<input type='password' id='new_pwd' class='pop_update_pwd'  maxlength='20' title=" + comm07 + " />";
	cont += "			</div>";
	cont += "			<div class='form-group'>";
	cont += "				<label for='confirm_pwd'>" + comm04 + "</label>";
	cont += "				<input type='password' id='confirm_pwd' class='pop_update_pwd'  maxlength='20' title=" + comm07 + " />";
	cont += "			</div>";
	cont += "		</div>";
	cont += "		<div class='pup_bottom'>";
	cont += "			<a class='btn btn-primary' onclick='pwChange();'>" + comm05 + "</a>";
	cont += "			<a class='btn btn-primary' onclick='closePwPop();'>" + comm06 + "</a>";
	cont += "		</div>";
	cont += "		<a href='javascript:;' class='btn_close'  onclick='closePwPop();' title=" + comm06 + ">" + comm06 +"</a>";
	cont += "	</div>";
	cont += "</section>";
	
	$("#pwd_update_btn").after(cont);
}

//popup close
function closePwPop() {
	
	$("#pop_pwd").hide();
	$(".pop_update_pwd").val("");
}

//비밀번호 변경 로직 - 1.새비밀번호화 재확인 비밀번호 일치여부,  2. 현재비밀번호가 맞는지여부(nPwChk = true,false)true 변경
function pwChange() {
	var path = $("#path").val();
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var userId = $("#hidden_id").val();
	var nUserPw = $("#now_pwd").val();
	var newUserPw = $("#new_pwd").val();
	var confirmPw = $("#confirm_pwd").val();
	var pwJ = /(?=.*\d{1,50})(?=.*[~`!@#$%\^&*()-+=]{1,50})(?=.*[a-zA-Z]{1,50}).{8,20}$/;
	
	if(nUserPw == "") {
		alert("현재 비밀번호를 입력해주세요.");
		return;
	}
	
	if(pwJ.test(newUserPw) == false) {
		alert("비밀번호는 숫자, 문자, 특수문자 조합 8~20자리 입력");
		return;
	}
	
	if(newUserPw != "" && newUserPw != confirmPw) {
		alert("새 비밀번호/재확인 비밀번호를 확인해주세요.");
		return;
	}

	$.ajax({
		type : "POST"
		,url : path + "/User_auth/pwChange"
		,data : {"user_id" : userId, "user_pw" : nUserPw , "user_pw2" : newUserPw}
		,dataType : "json"
		,beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
	    }
		,success : function (data) {
			var result = data.result;
			
			if(result == false) {
				alert("현재 비밀번호를 다시 확인해주세요.");
				$(".pop_update_pwd").val("");
				return;
			} else {
				alert("비밀번호 변경 완료");
				closePwPop();
			}
			
		}
	});
}
