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
  <script>
$(function(){
	$("#btnLogin").click(function(){
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
		document.form1.action="${path}/auth/admin_login_check";
		document.form1.submit();
	});
});
</script>

  <body class="login" style="width: 1800px; height: 800px; margin-top: 200px;">


  
  
  
    <div>
      <a class="hiddenanchor" id="signup"></a>
      <a class="hiddenanchor" id="signin"></a>

      <div class="login_wrapper" style="background-color: blue" >
        <div class="animate form login_form" style="width: 690px; height: 390px;  background-image: url(${path}/image/LOGIN.jpg); right: 0px;left: 0px;">
          <section class="login_content">
            <form name="form1" method="post" style="width: 200px; height: 100px; margin-left: 100px; margin-top: 150px;">
              <div>
                <input type="text" class="form-control" placeholder="Username" id="user_id" name="user_id"  />
              </div>
              <div>
                <input type="password" class="form-control" placeholder="Password" id="user_pw" name="user_pw"  />
              </div>
              <div>
               <button   id="btnLogin" class="btn btn-lg btn-primary btn-block" type="submit">로 그 인</button>
				  <c:if test="${param.message == 'nologin' }">
				<div style="color:red;">
					먼저 로그인하세요.
				</div>
			</c:if>
			<c:if test="${message == 'error' }">
				<div style="color:red;">
					아이디 또는 비밀번호가 일치하지 않습니다.
				</div>
			</c:if>
			<c:if test="${message == 'logout'}">
				<div style="color:red;">
					로그아웃되었습니다.
				</div>
			</c:if>
              </div>

              <!-- <div class="clearfix"></div>
							<select name="jump" onchange="location.href=this.value">
							<option>선택</option>
							<option value=${path}/User_auth/user_Login>일반 사용자</option>
						</select> -->

					</form>
          </section>
        </div>
      </div>
    </div>
  </body>
</html>