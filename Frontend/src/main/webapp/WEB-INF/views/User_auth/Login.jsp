<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/include/common.jsp"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>태양광 모니터링 시스템 </title>

    <!-- Bootstrap -->
    <link href="${vendors}/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="${vendors}/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="${vendors}/nprogress/nprogress.css" rel="stylesheet">
    <!-- Animate.css -->
    <link href="${vendors}/animate.css/animate.min.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="${build}/css/custom.min.css" rel="stylesheet">
    
    <script type="text/javascript" src="${vendors}/rsa.js/rsa.js"></script>
    <script type="text/javascript" src="${vendors}/rsa.js/jsbn.js"></script>
    <script type="text/javascript" src="${vendors}/rsa.js/prng4.js"></script>
    <script type="text/javascript" src="${vendors}/rsa.js/rng.js"></script>
    
        
    <style type="text/css">
 select {

    width: 200px; /* 원하는 너비설정 */
    padding: .8em .5em; /* 여백으로 높이 설정 */
    font-family: inherit;  /* 폰트 상속 */
    background: url('이미지 경로') no-repeat 95% 50%; /* 네이티브 화살표를 커스텀 화살표로 대체 */
    border: 1px solid #999;
    border-radius: 0px; /* iOS 둥근모서리 제거 */
    -webkit-appearance: none; /* 네이티브 외형 감추기 */
    -moz-appearance: none;
    appearance: none;
}
    </style>

    
  </head>
  <script type="text/javascript">
		function join() {
			location.href="${path}/User_auth/join";
		}
  </script>

  <body class="login" style="background-image: url(${path}/image/auth.jpg);"  >
        <div>
      <a class="hiddenanchor" id="signup"></a>
      <a class="hiddenanchor" id="signin"></a>

      <div class="login_wrapper" style="background-color: blue" >
        <div class="animate form login_form" >
          <article class="login_content">
            <form name="form1" id="login_Form" method="post" action="${path}/User_auth/login_check.do"> 
             <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
              <div>
			<h5 style="font-style: oblique;  font-size: 12mm; color: #708090; ">태양광 모니터링 <br />시스템</h5>    
						<p class="subTx">Solar Monitoring System</p>
                <input type="text" class="form-control" placeholder="Username" id="user_id" name="user_id"  />
              </div>
              <div>
                <input type="password" class="form-control" placeholder="Password" id="user_pw" name="user_pw"  />
              </div>
              	<div>
              		<!-- <button id="btnLogin" class="btn btn-lg btn-primary btn-block" type="submit">로 그 인</bu tton> -->
					<button id="btnLogin" class="btn btn-lg btn-primary btn-block" type="button">로 그 인</button>
<!-- 					<button id="btnLogin2" class="btn btn-lg btn-secondary" action="">로그인2_Test</a> -->
					<button id="" style="display: none;" class="" type="button"></button>
					<span style="color: red;" id="login_error_msg">${errMsg}</span>
				</div>
			</form>
			
			<form id="hiddenForm" name="hiddenForm" action="<%=request.getContextPath()%>/User_auth/loginchk.do" method="post">
				<input type="hidden" name="__user_id" id="__user_id"/>
				<input type="hidden" name="__user_pw" id="__user_pw"/>
			</form> 
          </article>
        </div>
      </div>
    </div>
  </body>
  
   <script>	
   
   $(document).ready(function(){
   		document.getElementById("user_id").value = "";
   });
   
   	
	$(function(){
		$("#user_id").keydown(function(key) {
			if(key.keyCode == 13) {
				pwChk();
			}
		});
		
		$("#user_pw").keydown(function(key) {
			if(key.keyCode == 13) {
				pwChk();
			}
		});
		$("#btnLogin").click(function(){
			$("#login_error_msg").html("");
			var userid=$("#user_id").val(); // 태그의 value 속성값
			var passwd=$("#user_pw").val();
			if(userid==""){
				alert("아이디를 입력하세요.");
				$("#user_id").focus();
				return;
			}
			if(passwd==""){
				alert("비밀번호를 입력하세요.");
				$("#user_pw").focus();
				return;
			}
		
			//Pw Fail chk
			pwChk();
			
		});
	});
	
// 	function loginTest() {
// 		rsa.setPublic("${RSAModulus}", "${RSAExponent}");
	
// 		var uID = $("#login_Form").find("#user_id").val();
// 		var uPW = $("#login_Form").find("#user_pw").val();
// 		$id.val(rsa.encrypt(uID));
// 		$pw.val(rsa.encrypt(uPW));
		
// 		//location.href = "${path}/User_auth/loginchk.do";
		
// 		$("#hiddenForm").action = "${path}/User_auth/loginchk.do";
// 		$("#hiddenForm").submit();
// 	}
	
	function  pwChk() {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		var userId = $("#user_id").val();
		var userPw = $("#user_pw").val();
		var rsa = new RSAKey();
			rsa.setPublic("${RSAModulus}", "${RSAExponent}");
		
		$.ajax({
			type : "POST"
			,url : "${path}/pwChk"
			,data : {"user_id" : rsa.encrypt(userId), "user_pw" : rsa.encrypt(userPw)}
			,async : false
			,dataType : "json"
			,beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
		    }
			,success : function (data) {
				var idYn = data.idYn;
				var idStat = data.idStat;
				var pwCnt = data.pwCnt;
				var loginSuccess = data.loginSuccess;
				var adminPwdChk = data.userUpdateDt;
				
				if(loginSuccess == false) {
					if(idYn == false) {
						$("#login_error_msg").text("아이디 또는 비밀번호가 일치하지 않습니다.");
						return;
					} else if(idStat == false) {
						alert("비밀번호를 5회 이상 틀리셨습니다. 3분 후 다시 시도해 주세요.");
						$("#user_pw").val("");
						return;
					} else {
						if(pwCnt < 5 ) {
							alert("비밀번호를 " + pwCnt + "회 틀리셨습니다. 5회 실패시 3분간 접속할 수 없습니다.");
							$("#user_pw").val("");
							return;
						}	
					}	
				} 
				
				if(adminPwdChk) {
					alert("기본제공된 비밀번호입니다. 비밀번호를 변경해주세요.");
				}
				
// 				$("#login_Form").submit(
// 					function(e) { 
// 						e.preventDefault();

// 						var $id = $("#hiddenForm input[name='user_id']");
// 						var $pw = $("#hiddenForm input[name='user_pw']");	
						
// 				 		var uID = $("#login_Form input[name='user_id']").val();
// 				 		var uPW = $("#login_Form input[name='user_pw']").val();
						
//    						var rsa = new RSAKey();
// 						rsa.setPublic("${RSAModulus}", "${RSAExponent}");
						
// 						$id.val(rsa.encrypt(uID));
// 	 					$pw.val(rsa.encrypt(uPW));
						
// 						$("#hiddenForm").attr("method","post");
// 						$("#hiddenForm").attr("action","${path}/User_auth/login_check.do");
// 						$("#hiddenForm").submit();
// 					}
// 				);


				// FOR login_Form SUBMIT				
				$("#login_Form").submit(
					function(e) {
 				 		var uID = $("#login_Form input[name='user_id']").val();
 				 		var uPW = $("#login_Form input[name='user_pw']").val();

						var rsa = new RSAKey();
 						rsa.setPublic("${RSAModulus}", "${RSAExponent}");
						
 						document.getElementById("login_Form").user_id.value = rsa.encrypt(uID);
 						document.getElementById("login_Form").user_pw.value = rsa.encrypt(uPW);
					}
				);
				
				$("#login_Form").submit(); 
			}
		});
	}	
</script>
</html>