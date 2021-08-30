<%@page import="java.security.acl.Group"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ include file="/WEB-INF/views/include/common.jsp"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
    
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>

    <title><spring:message code="site.title" text="태양광 모니터링 - default"/></title>

   <!-- Bootstrap -->
		<link rel="stylesheet" type="text/css" href="${vendors}/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
		<!-- bootstrap toggle -->
		<link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
    <!-- Font Awesome -->
		<link rel="stylesheet" type="text/css" href="${vendors}/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
		<link rel="stylesheet" type="text/css" href="${vendors}/nprogress/nprogress.css" rel="stylesheet">
    <!-- iCheck -->
		<link rel="stylesheet" type="text/css" href="${vendors}/iCheck/skins/flat/green.css" rel="stylesheet">

    <!-- bootstrap-progressbar -->
		<link rel="stylesheet" type="text/css" href="${vendors}/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
    <!-- JQVMap -->
		<link rel="stylesheet" type="text/css" href="${vendors}/jqvmap/dist/jqvmap.min.css" rel="stylesheet">
    <!-- bootstrap-daterangepicker -->
		<link rel="stylesheet" type="text/css" href="${vendors}/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">
		<!-- 하이테크 -->
	<link rel="stylesheet" type="text/css" href="https://www.highcharts.com/media/com_demo/css/highslide.css" />
    <!-- Custom Theme Style -->
		<link rel="stylesheet" type="text/css" href="${build}/css/custom.min.css" rel="stylesheet">
		
	<!--  구글  -->
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<script type="text/javascript" src="${path}/resources/js/comm.js"></script>
	<link href="${build}/css/pup.css" rel="stylesheet">
	<script type="text/javascript" src="${path}/resources/js/comm.js"></script>
	<input type="hidden" id="comm_01" value="<spring:message code="comm.01" text="default text"/>" /><!-- 비밀번호변경 -->
	<input type="hidden" id="comm_02" value="<spring:message code="comm.02" text="default text"/>" /><!-- 현재비밀번호 -->
	<input type="hidden" id="comm_03" value="<spring:message code="comm.03" text="default text"/>" /><!-- 새 비밀번호 -->
	<input type="hidden" id="comm_04" value="<spring:message code="comm.04" text="default text"/>" /><!-- 새 비밀번호 확인 -->
	<input type="hidden" id="comm_05" value="<spring:message code="comm.05" text="default text"/>" /><!-- 수정 -->
	<input type="hidden" id="comm_06" value="<spring:message code="comm.06" text="default text"/>" /><!-- 닫기 -->
	<input type="hidden" id="comm_07" value="<spring:message code="comm.07" text="default text"/>" /><!-- 새비밀번호를 확인해주세요. -->
<!-- --------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->
<!-- ---------------------------------------------------------------구글 차트  스크립트 start---------------------------------------------------------------------------------------------------------- -->
 
<!-- ---------------------------------------------------------------구글 차트  스크립트 End---------------------------------------------------------------------------------------------------------- -->
  </head>
 <!-- 최근 시간 -->
 <script type="text/javascript">
 
 </script>

 
  <body class="nav-md">
    <div class="container body">
      <div class="main_container">
        <div class="col-md-3 left_col" >
          <div class="left_col scroll-view" >
            <div class="navbar nav_title" style="border: 0;">
             <h3 align="center" style="color: white ; margin-top: 20px;"><span class="titleBold">태양광</span> <br />모니터링 시스템 </h3>
			 <h3 align="center" style="color: #09f";>Solar<br>Monitoring<br>System V1.0</h3> 

              
            </div>


            <!-- menu profile quick info -->
            <div class="profile clearfix">
              <div class="profile_pic" style="margin-top: 130px;">
                <img src="${path}/image/admin.png" alt="..." class="img-circle profile_img">
              </div>
              <div class="profile_info" style="margin-top: 130px;">
                   <h2 style="margin-bottom: 10px; margin-top: 15px;">       
     <c:choose>
	<c:when test="	<h2>${msg}</h2>">
	</c:when>
	<c:otherwise>
		${msg}님
		<a type="button" class="" href="${path}/User_auth/logout.do"><spring:message code="session.logout" text="default text"/></a>
		<br>
		<a type="button" class="btn_pass_update" id="pwd_update_btn" href="javascript:pwPop();"><spring:message code="session.changePW" text="default text"/></a>
		<input type="hidden" id="hidden_id" value="${msg}" />
		<input type="hidden" id="path" value="${path}" />
	</c:otherwise>
</c:choose>
</h2>
              </div>
            </div>
            <!-- /menu profile quick info -->

            <br />

			<form name="sender">
       			<input type="hidden" name="t_group_id"/>
       			<input type="hidden" name="lang"/>
       			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
     		</form>

            <!-- sidebar menu -->
            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
              <div class="menu_section">
			 <div class="clearfix"></div>
                <ul class="nav side-menu">
                                    <li><a href="${path}/User_View/User_index"><i class="fa fa-home"></i><h4><spring:message code="part.H10" text="default text"></spring:message></h4></a>
                  </li>
                  
  					 <li><a><i class="fa fa-table"></i> <h4><spring:message code="part.H2" text="default text"></spring:message></h4> <span class="fa fa-chevron-down"></span></a>
  					 
                     <ul class="nav child_menu">
                      <li><a href="${path}/User_groupBoard/Group_Board"><h4><spring:message code="part.H7" text="default text"></spring:message></h4></a></li>
                      <li><a href="${path}/User_trackerBoard/Tracker_Board"><h4><spring:message code="part.H8" text="default text"></spring:message></h4></a></li>
                      <li><a href="${path}/User_inverterBoard/Inverter_Board"><h4><spring:message code="part.H9" text="default text"></spring:message></h4></a></li>
                    </ul>
					<li><a href="https://drive.google.com/file/d/12u8t39sYIug7Mb3uMT-WR8teszsBB7kd/view?usp=sharing" ><h4><spring:message code="en.31" text="default text"></spring:message></h4></a></li>

	                  </li>
                </ul>
              </div>
            </div>
            <!-- /sidebar menu -->
          </div>
        </div>

        <!-- top navigation -->
        <div class="top_nav">
              <ul class="nav navbar-nav navbar-right">
				<li class="">
				</li>
				<li class="">
				 	<a  href="<c:url value="/User_groupBoard/Group_Board?lang=en" />">
				<img  src="${path}/image/USA1.png">
					</a>
				</li>
				<li class="">
					<a  href="<c:url value="/User_groupBoard/Group_Board?lang=ko" />">
				<img  src="${path}/image/korean1.png">
					</a>
				</li>
				<li class="clock_box">
					최근 업데이트 날짜 : <span id="clock"></span>
				</li>
              </ul> 
        </div>
        <!-- /top navigation -->

        <!-- page content -->
        <div class="right_col" role="main">
          <!-- top tiles -->
         <div class="row top_tiles" style="">
     <div class="animated flipInY col-lg-6 col-md-6 col-sm-6 col-xs-12">
                <div class="tile-stats">
                    <span class="count_top"><h4><i class="fa fa-home"   style="margin-top: 10px; margin-left: 20px;">&nbsp;<spring:message code="part.A1" text="default text"></spring:message></i></h4></span>
                   <div class="count black" style="margin-top: 5px;" align="center" >${dto.t_group_name}</div>
                </div>
              </div>  
              <div class="animated flipInY col-lg-6 col-md-3 col-sm-6 col-xs-12">
                <div class="tile-stats" >
                    <span class="count_top"><h4><i class="fa fa-user" style="margin-top: 10px; margin-left: 20px;">&nbsp;<spring:message code="part.A2" text="default text"></spring:message></i></h4></span> 
                  <div class="count black" style="margin-top: 5px;" align="center">${dto.t_group_manager}</div>
                </div>
              </div>

              <div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
                <div class="tile-stats" >							<!-- 금일 발전량 -->
                  <span class="count_top"><h4><i class="fa fa-line-chart" style="margin-top: 10px; margin-left: 20px;"> &nbsp;</i><spring:message code="part.A3" text="default text"></spring:message></h4></span>
                  <div class="count " style="color: #07f;" align="center">${inverter_today.inverter_data_accu_energy}<h4 style="font-weight: 900;">KWh</h4></div>
                </div>
              </div>
              <div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
                <div class="tile-stats" >             <!-- 전일발전량 --> 
                  <span class="count_top"><h4><i class="fa fa-line-chart"  style="margin-top: 10px; margin-left: 20px;">&nbsp;</i><spring:message code="part.A4" text="default text"></spring:message></h4></span>
                  <div class="count" style="color: #07f;" align="center">${inverter_date.inverter_data_accu_energy}<h4 style="font-weight: 900;">KWh</h4></div>
                </div>
              </div>
              <div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
                <div class="tile-stats" >							 <!-- 현재 출력 -->	
                   <span class="count_top"><h4><i class="fa fa-line-chart" style="margin-top: 10px; margin-left: 20px;">&nbsp;</i><spring:message code="part.A5" text="default text"></spring:message></h4></span>
                  <div class="count" style="color: #07f;" align="center">${total_data.inverter_data_output}<h4 style="font-weight: 900;">KWh</h4></div>
                </div>
              </div>
              
                <div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
                <div class="tile-stats" >								<!-- 총 누적 발전량 -->
                   <span class="count_top"><h4><i class="fa fa-line-chart" style="margin-top: 10px; margin-left: 20px;">&nbsp;</i><spring:message code="part.A6" text="default text"></spring:message></h4></span>
                 <div class="count" style="font-weight: 900; color: red;"  align="center">${Cumulative_power_generation.inverter_data_accu_energy}<h4 style="color: white-space; font-weight: 900;">KWh</h4></div>
                </div>				
              </div>
          </div>
          <!-- /top tiles -->




          <div class="row">
				<!-- 센서 데이터 -->
				          <div class="animated flipInY col-lg-4 col-md-4 col-sm-6 col-xs-12">
              <div class="x_panel fixed_height_320" style="height: 290px;">
                  <div class="x_title" style="height: 76px; margin-top: 7px;">
                    <h2 style="font-size: 7mm; font-weight: bolder;"><spring:message code="part.B1" text="default text"></spring:message></h2>
           
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                  <table class="" style="width:100%">
                  <tr>
                      <th style="width:37%;">
                        <p></p>
                      </th>
                      <th>
                      </th>
                    </tr>
                    <tr>
                   
                             <td>
                        <table class="tile_info">
                          <tr>
                            <td>
                              <p style="width: 200px;"><i class="fa fa-square blue"></i><spring:message code="part.B1_1" text="default text"/> </p>
                            </td>
                            <td align="left">${sensor_date.sensor_data_module_temp}&nbsp;℃</td>
                          </tr>
                          <tr>
                            <td>
                              <p><i class="fa fa-square green" ></i><spring:message code="part.B1_2" text="default text"/></p>
                            </td>
                            <td> ${sensor_date.sensor_data_ambient_temp}&nbsp;℃</td>
                          </tr>
                          <tr>
                            <td>
                              <p><i class="fa fa-square purple"></i><spring:message code="part.B1_3" text="default text"/> </p>
                            </td>
                            <td> ${sensor_date.sensor_data_horizontal_idt}&nbsp;w/m2</td>
                          </tr>
                          <tr>
                            <td>
                              <p><i class="fa fa-square aero"></i><spring:message code="part.B1_4" text="default text"/> </p>
                            </td> 
                            <td>${sensor_date.sensor_data_slope_idt}&nbsp;w/m2</td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                  </table>
                  </div>
                </div>
              </div>


				
			<!-- 날씨 정보 시작  -->
            <div class="col-md-8 col-sm-6 col-xs-12">
            <div class="x_panel">
                    <div class="x_title">
                      <h2 style="font-size: 7mm; font-weight: bolder; margin-bottom: 17px;">&nbsp; <img src="${path}/image/h004.png">
                                          <spring:message code="part.B2" text="default text"></spring:message></h2>
                    
                      <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                      <div class="row">
                        </div>
                      </div>
                      <div class="row">
                        <div class="col-sm-4">
                          <div class="weather-icon" style="margin-left: 90px;">
                            <canvas height="84" width="84" id="partly-cloudy-day"></canvas>
                          </div>
                        </div>
                        <div class="col-sm-6">
                          <div class="weather-text" >
                           <h3 style="font-weight:bolder; margin-left: 100px;">${GROUP_ADDR.t_group_addr}<br>
                           </h3>
                          </div> 
                        </div> 
                      </div>

                      <div class="clearfix"></div>

                     <div class="row weather-days">
                        <div class="col-sm-2">
                          <div class="daily-weather">
                            <h2 class="day" style="font-weight: normal;"><spring:message code="part.B2_1" text="default text"/></h2>
                            <h3 align="center">${weather.area_name}</h3>
                          </div>
                        </div>
                        
                          <div class="daily-weather">
                         <div class="col-sm-2">
                            <h2 class="day" style="font-weight: normal;"><spring:message code="part.B2_2" text="default text"/></h2>
                            <h3 align="center">${weather.weather_t_min}°C</h3>
                          </div>
                        </div>
                        
                          <div class="daily-weather">
                         <div class="col-sm-2">
                            <h2 class="day" style="font-weight: normal;"><spring:message code="part.B2_3" text="default text"/></h2>
                            <h3 align="center">${weather.weather_t_max}&nbsp;°C</h3>
                          </div>
                        </div>
                          <div class="daily-weather">
                        <div class="col-sm-2">
                            <h2 class="day"><spring:message code="part.B2_4" text="default text"/></h2>
                            <h3 align="center">${weather.weather_ultrafinedust_concentration}&nbsp;㎍/m³</h3>
                          </div>
                        </div>
                          <div class="daily-weather">
                       <div class="col-sm-2">
                            <h2 class="day"><spring:message code="part.B2_5" text="default text"/></h2>
                            <h3 align="center">${weather.weather_finedust_concentration}&nbsp;㎍/m³</h3>
                          </div>
                        </div>
                           <div class="col-sm-2">
                          <div class="daily-weather">
                            <h2 class="day"><spring:message code="part.B2_6" text="default text"/></h2>
                            <h3 align="center">${weather.weather_ozone_concentration}&nbsp;㎍/m</h3>
                          </div>
                        </div>
                        <div class="clearfix"></div>
                      </div>
                    </div>
                  </div>
            </div>
            
             <div class="clearfix"></div>
              <br />
              <div class="container">
  	 <div class="row">
            	<!-- 날씨 정보 끝  -->
				<!-- 센서 데이터 -->
              <!-- 스크립트 -->
                          				<script>
                          $(document).ready(function(){
                            $("#hide").click(function(){
                              $("p").hide();
                            });
                            $("#show").click(function(){
                              $("p").show();
                            });
                          });
                          </script>

              <!-- 스크립트 -->
              </div>
              </div>
             <div class="clearfix"></div>
              <br />
              <div class="container">
  	 <div class="row">
            	<!-- 날씨 정보 끝  -->
				<!-- 센서 데이터 -->
		<!-- 센서 데이터 -->
				          <div class="col-md-12 col-sm-12 col-xs-12" style="padding-right: 10px; padding-left: 10px;">
                 <div class="x_panel">
                 <h2 style="font-size: 7mm; font-weight: bolder; margin-bottom: 10px;">&nbsp;<spring:message code="part.B4" text="default text"></spring:message></h2>
                  <div class="x_title">
                  </div>
                  <div class="x_content">
			

                    <div class="table-responsive">
                      <table class="table table-hover">
                        <thead>
                          <tr class="headings">
                            <th class="column-title"><spring:message code="part.B4_1" text="default text"/> </th>
                            <th class="column-title"><spring:message code="part.B4_2" text="default text"/> </th>
                            <th class="column-title"><spring:message code="part.B4_3" text="default text"/> </th>
                            <th class="column-title"><spring:message code="part.B4_4" text="default text"/></th>
                            <th class="column-title no-link last"><span class="nobr"><spring:message code="part.B4_5" text="default text"></spring:message></span>
                            </th>
                            <th class="bulk-actions" colspan="7">
                              <a class="antoo" style="color:#fff; font-weight:500;">Bulk Actions ( <span class="action-cnt"> </span> ) <i class="fa fa-chevron-down"></i></a>
                            </th>
                          </tr>
                        </thead>
					
                        <tbody>
                          <tr class="even pointer">
                          	<c:if test="${not empty t_alarm}}">
                          		<td class=" ">${t_alarm.t_group_id}</td>
	                            <td class=" ">${t_alarm.alarm_type}</td>
	                            <td class="a-right a-right">${t_alarm.alarm_status}</td>
	                            <td class="last">${t_alarm.alarm_upt_data}</td>
	                            <td class=" ">${t_alarm.alarm_grade}<i class="success fa fa-long-arrow-up"></i></td>
                          	</c:if>
                            <c:if test="${empty t_alarm}">
                          		<td class=" " colspan="5">No Data</td>
                          	</c:if>
                          </tr>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
              </div>
              
              </div>
              </div>
			</div>
	</div>
				
            	
 
        </div>
        <!-- /page content -->
      </div><!-- 메인  -->

	<!-- 현재 시간을 나타내는 Script -->
	<script>
		$(document).ready(function() {
			
			goIndex();
			
		});
		
		function goIndex() {
    		var t = document.sender;
      		
      		t.t_group_id.value = ${t_group_id};
      		
      		t.action = "${path}/User_View/User_index";
      		t.method = "get";
      		
      		t.submit();
    	}
	
		function printTime() {
			var clock = document.getElementById("clock"); // 출력할 장소 선택
			var now = new Date(); // 현재시간
			var nowTime =  (now.getMonth() + 1)	+ "월 " + now.getDate() + "일 &nbsp;" + now.getHours() + "시" + now.getMinutes() + "분 ";
			clock.innerHTML = nowTime; // 현재시간을 출력
			setTimeout("printTime()", 1000); // setTimeout(“실행할함수”,시간) 시간은1초의 경우 1000
		}
		
		window.onload = function() { // 페이지가 로딩되면 실행
			printTime();
		}
		
		
		//트래커 제어하기
		var addr = "118.44.191.11:5600";
		var Uri = "ws://" + addr;
		
		var output;

		function init() {
		
			output = document.getElementById("output");
			init_WebSocket();
		}
		
		function init_WebSocket() {
			webSocket = new WebSocket(Uri);
			
			webSocket.onopen = function(event) {
				onOpen(event);
			}
			
			webSocket.onmessage = function(event) {
				onMessage(event);
			}	
		}
		function onOpen(evt) {
			/* writeToScreen(addr + " 트래커 서버에 연결되었습니다."); */
			//doSend("Test Message");
		}
		function onClose(evt) {
			/* writeToScreen("연결해제"); */
		}
		
		function onMessage(evt) {
			/* writeToScreen('<span style="color: blue;"> 수신 : ' + evt.data + '</span>'); */
			//webSocket.close();
		}
		
		function onError(evt) {
			/* writeToScreen('<span style="color: red;">에러:</span> ' + evt.data); */

		}
		//여기
		function doSend(message) {
			/*  writeToScreen("발신  : " + message);  */
			webSocket.send(message);
		}
		//여기
		 /* function writeToScreen(message) {
			var pre = document.createElement("p");
			pre.style.wordWrap = "break-word";
			pre.innerHTML = message;
			output.appendChild(pre);
		}  */
		
		window.addEventListener("load",init, false);
	</script>
	<script>
       /* setTimeout(function(){
           location.reload();
       },900000); */
    </script>
	
	<!-- script -->

	<!-- script -->
	<!-- 끄 -->
	   <!-- jQuery -->
	<script src="${vendors}/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
	<script src="${vendors}/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- FastClick -->
	<script src="${vendors}/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->
	<script src="${vendors}/nprogress/nprogress.js"></script>
    <!-- Chart.js -->
	<script src="${vendors}/Chart.js/dist/Chart.min.js"></script>
    <!-- gauge.js -->
	<script src="${vendors}/gauge.js/dist/gauge.min.js"></script>
    <!-- bootstrap-progressbar -->
	<script src="${vendors}/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
    <!-- iCheck -->
	<script src="${vendors}/iCheck/icheck.min.js"></script>
    <!-- Skycons -->
	<script src="${vendors}/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
   <!-- Flot -->
	<script src="${vendors}/skycons/skycons.js"></script>
	<!-- jQuery Sparklines -->
    <script src="${vendors}/jquery-sparkline/dist/jquery.sparkline.min.js"></script>
    <!-- easy-pie-chart -->
    <script src="${vendors}/jquery.easy-pie-chart/dist/jquery.easypiechart.min.js"></script>
    
    
     <!-- Flot plugins -->
	<script src="${vendors}/Flot/jquery.flot.js"></script>
	<script src="${vendors}/Flot/jquery.flot.pie.js"></script>
	<script src="${vendors}/Flot/jquery.flot.time.js"></script>
	<script src="${vendors}/Flot/jquery.flot.stack.js"></script>
	<script src="${vendors}/Flot/jquery.flot.resize.js"></script>
	
	
	<script src="${vendors}/flot.orderbars/js/jquery.flot.orderBars.js"></script>
	<script src="${vendors}/flot.orderbars/js/jquery.flot.orderBars.js"></script>
	<script src="${vendors}/flot.curvedlines/curvedLines.js"></script>
    <!-- DateJS -->
	<script src="${vendors}/DateJS/build/date.js"></script>
    <!-- JQVMap -->
	<script src="${vendors}/jqvmap/dist/jquery.vmap.js"></script>
	<script src="${vendors}/jqvmap/dist/maps/jquery.vmap.world.js"></script>
	<script src="${vendors}/jqvmap/examples/js/jquery.vmap.sampledata.js"></script>
    <!-- bootstrap-daterangepicker -->
	<script src="${vendors}/moment/min/moment.min.js"></script>
	<script src="${vendors}/bootstrap-daterangepicker/daterangepicker.js"></script>
	
	<!-- 토글버튼 -->
	<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
	
	<!-- highcharts.com -->
<script src="${code}/highcharts.js"></script>
<script src="${code}/modules/exporting.js"></script>
<script src="${code}/modules/export-data.js"></script>

<!-- Additional files for the Highslide popup effect -->
<script src="https://www.highcharts.com/media/com_demo/js/highslide-full.min.js"></script>
<script src="https://www.highcharts.com/media/com_demo/js/highslide.config.js" charset="utf-8"></script>

	
   <!-- Custom Theme Scripts -->
<%-- 	<script src="${build}/js/custom.min.js"></script> <!-- 문제 사항.. --> --%>
	<script src="${build}/js/custom.min.js"></script> <!-- 문제 사항.. -->
	
  </body>
</html>
