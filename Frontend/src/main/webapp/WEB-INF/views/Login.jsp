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
    <!-- Custom Theme Style -->
    <link href="${build}/css/custom.min.css" rel="stylesheet">
    <style type="text/css">
 body { 
 background: url(${path}/image/auth.jpg) no-repeat 50% 50% fixed; 
 
 	webkit-background-size: cover; 
 	moz-background-size: cover; 
 	o-background-size: cover; 
 	background-size: cover; }

}


    </style>

    
  </head>


 <body class="login" style="background-image: url(${path}/image/auth.jpg); background-size:center center;"  >
    <div>
      <a class="hiddenanchor" id="signup"></a>
      <a class="hiddenanchor" id="signin"></a>

      <div class="login_wrapper" style="background-color: blue" >
        <div class="animate form login_form">
          	<section class="login_content">
	            <form name="form1" action="${path}/User_auth/login_check.do" method="post" style="width: 200px; height: 100px; margin-left: 100px; margin-top: 150px;">
					<h5 style="font-style: oblique;  font-size: 12mm; color: #708090; ">태양광 모니터링 <br />시스템</h5>    
					<p class="subTx">Solar Monitoring System</p> 
					<input type="hidden" name="${csrf.parameterName}" value="${csrf.token}"  />
					<div>
						<input type="text" class="form-control" placeholder="Username" id="user_id" name="user_id"  />
					</div>
					<div>
						<input type="password" class="form-control" placeholder="Password" id="user_pw" name="user_pw"  />
					</div> 
					<div>
						<button id="btnLogin" class="btn btn-lg btn-primary btn-block" type="button">로 그 인</button>
						<button id="" style="display: none;" class="" type="button"></button>
						<span style="color: red;">${errMsg}</span>
					</div>
				</form>
			</section>
        </div>
      </div>
    </div>
  </body>
</html>