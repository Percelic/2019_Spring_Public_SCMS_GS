<%@page import="java.security.acl.Group"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <%@ include file="/WEB-INF/views/include/common.jsp"%>
<!DOCTYPE html>
<html>
  <head>
         <script language='javascript'> 
				window.setTimeout('window.location.reload()',120000); //60초마다 새로고침
				</script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />  
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	
	<%
	String userID = null;
	if(session.getAttribute("user_ID") != null){
		userID = (String) session.getAttribute("userID");
	}
	%>
	
	
	
    <title>태양광모니터링시스템</title>
	
		
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

    <!-- Custom Theme Style -->
		<link rel="stylesheet" type="text/css" href="${build}/css/custom.min.css" rel="stylesheet">

  </head>

  <body class="nav-md">
    <div class="container body">
      <div class="main_container">
        <div class="col-md-3 left_col">
          <div class="left_col scroll-view">
            <div class="navbar nav_title" style="border: 0;">
              <a href="/Web/index" class="site_title"><i class="fa fa-sun-o"></i> <span>태양광 모니터링</span></a>
				 
            </div>

            <div class="clearfix"></div>

            <!-- menu profile quick info -->
   <!--          <div class="profile clearfix">
              <div class="profile_pic">
                <img src="images/img.jpg" alt="..." class="img-circle profile_img">
              </div>
              <div class="profile_info">
                <span>user,</span>
                <h2>Log</h2>
              </div>
            </div> -->
            <!-- /menu profile quick info -->

            <br />
			<!-- 여기서 부터 검색한다 -->
			<!-- 여기서 부터 검색한다 -->
            <!-- sidebar menu -->
            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
              <div class="menu_section">
                <ul class="nav side-menu">
                  <li><a><i class="fa fa-home"></i> Home <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="/Web/index">홈</a></li>
                    </ul>
                  </li>
          
                  <li><a><i class="fa fa-table"></i> 관리도구 <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="/Web/User_Board">사용자 관리</a></li>
                      <li><a href="/Web/Group_Board">군집 관리</a></li>
                      <li><a href="/Web/Tracker_Board">트래커 관리</a></li>
                      <li><a href="/Web/Inverter_Board">인버터 관리</a></li>
                    </ul>
                  </li>
                 </ul>
              </div>
    

            </div>
            <!-- /sidebar menu -->

          </div>
        </div>

        <!-- top navigation -->
        <div class="top_nav">
          <div class="nav_menu">
            <nav>
              <div class="nav toggle">
                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
              </div>
              
              <ul class="nav navbar-nav navbar-right">
                <li class="">
                  <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                    <img src="../../images/img.jpg" alt="">User<span class=" fa fa-angle-down"></span>
                  </a>
                  <ul class="dropdown-menu dropdown-usermenu pull-right">
                    <li><a href="javascript:;"> Profile</a></li>
                    <li>
                      <a href="javascript:;">
                        <span class="badge bg-red pull-right">50%</span>
                        <span>Settings</span>
                      </a>
                    </li>
                    <li><a href="javascript:;">Help</a></li>
                    <li><a href="/auth/login"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
                  </ul>
                </li>

                <li role="presentation">
              		
                </li>
              </ul>
            </nav>
          </div>
        </div>
        <!-- /top navigation -->
		<!-- 군집명 DB 뿌리기  -->
		<%
			//군집 뿌리기
			Map<String, Map> map = (Map<String, Map>) request.getAttribute("t_group");
			Map<String, Map> map1 = (Map<String, Map>) request.getAttribute("inverter_data");
			Map<String, Map> map2 = (Map<String, Map>) request.getAttribute("Alaram");
             Map<String, Object> group = map.get("t_group");
             Map<String, Object> inverter = map1.get("inverter_data");
             Map<String, Object> Alaram = map1.get("Alaram");
                        		  
		%>
			 <!-- /top navigation -->
					<!-- 군집명 DB 뿌리기  -->
					
        <!-- page content --> 
        <div class="right_col" role="main">
        
          <!-- top tiles -->
          <div class="row tile_count" style="margin-bottom: 0px;">
            <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
              <span class="count_top"><i class="fa fa-home">&nbsp;군집명</i></span>
              <div class="count" style="margin-top: 5px;"><%=group.get("t_group_name")%></div>
              <span class="count_bottom"><i class="green"> </i> </span>
            </div> 
            <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
              <span class="count_top"><i class="fa fa-user">&nbsp;사용자</i></span>
              <div class="count" style="margin-top: 5px;"><%=group.get("inverter_manager")%></div>
              <span class="count_bottom"></span>
            </div>
            <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
              <span class="count_top"><i class="fa fa-line-chart"></i>&nbsp;총 누적 발전량</span>
              <div class="count green"><%=group.get("inverter_data_output")%>&nbsp;&nbsp;<h5>KWh</h5></div>
              <span class="count_bottom"></span>
            </div>
            <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
              <span class="count_top"><i class="fa fa-line-chart"></i>&nbsp;금일 발전량</span>	
              <div class="count"><%=inverter.get("A") %><h5>KWh</h5></div>
              <span class="count_bottom"></span>
            </div>
            <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
              <span class="count_top"><i class="fa fa-line-chart"></i>&nbsp;전일 발전량</span>
              <div class="count"><%=inverter.get("B") %>&nbsp;&nbsp;<h5>KWh</h5></div>
              <span class="count_bottom"></span>
            </div>
            <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
              <span class="count_top"><i class="fa fa-line-chart"></i>&nbsp;현재 출력</span>
              <div class="count"><%=inverter.get("C") %>&nbsp;&nbsp;<h5>KWh</h5></div>
              <span class="count_bottom"></span>
            </div>
          </div>
          <!-- /top tiles -->
             <!--인버터 발전량  --> 
            	
           <div class="row">
          
          <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">
              <div class="dashboard_graph">

                <div class="row x_title">
                  <div class="col-md-6">
                    <h3>인버터 발전량</h3>
                  </div>
                </div>
                <!-- 너 여기서 끝나면 되 -->
				
                <div class="col-md-9 col-sm-9 col-xs-12">
                  <div id="chart_plot_02" class="demo-placeholder"></div>
                </div>
                <div class="col-md-3 col-sm-3 col-xs-12 bg-white">
                  <div class="x_title">
                    <h2>발전량 수치</h2>
                    <div class="clearfix"></div>
                  </div>
                  <div class="col-md-12 col-sm-12 col-xs-6">
                    <div>
                      <p>총 누적 발전량</p>
                      <div class="">
                        <div class="progress progress_sm" style="width: 76%;">
                          <div class="progress-bar bg-green" role="progressbar" data-transitiongoal=<%=inverter.get("D") %>></div>
                        </div>
                      </div>
                    </div>
                    <div>
                      <p>금일 발전량</p>
                      <div class="">
                        <div class="progress progress_sm" style="width: 76%;">
                          <div class="progress-bar bg-green" role="progressbar" data-transitiongoal="70"></div>
                        </div>
                      </div>
                    </div>
                    <div>
                      <p>전일 발전량</p>
                      <div class="">
                        <div class="progress progress_sm" style="width: 76%;">
                          <div class="progress-bar bg-green" role="progressbar" data-transitiongoal="60"></div>
                        </div>
                      </div>
                    </div>
                    <div>
                      <p>현재 출력</p>
                      <div class="">
                        <div class="progress progress_sm" style="width: 76%;">
                          <div class="progress-bar bg-green" role="progressbar" data-transitiongoal="100"></div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>

                <div class="clearfix"></div>
              </div>
            </div>

          </div> 
           <!--인버터 발전량  --> 
          <br />
			<!-- 센서 데이터 들어갈 DB문 -->
				<%
				Map<String, Map> sensor = (Map<String, Map>) request.getAttribute("sensor_data");
	             Map<String, Object> sensor_data = map.get("sensor_data");
				%>
				<!-- 센서 데이터 들어갈 DB문 -->
          <div class="row">

			<!-- 시작 -->
            <div class="col-md-6 col-sm-4 col-xs-12">
              <div class="x_panel tile fixed_height_320">
                <div class="x_title">
                  <h2>센서 데이터</h2>
                  <div class="clearfix"></div>
                </div>
                <div class="x_content">
                  <div class="widget_summary">
                    <div class="w_left w_25">
                      <span>모듈온도</span>
                    </div>
                    <div class="w_center w_55">
                      <div class="progress">
                        <div class="progress-bar bg-green" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width:<%=sensor_data.get("Sensor_Data_Module_TEMP")%>;">
                          <span class="sr-only">60% Complete</span>
                        </div>
                      </div>
                    </div>
                    <div class="w_right w_20">
                      <span><%=sensor_data.get("Sensor_Data_Module_TEMP")%>℃</span>
                    </div>
                    <div class="clearfix"></div>
                  </div>

                  <div class="widget_summary">
                    <div class="w_left w_25">
                      <span>대기온도</span>
                    </div>
                    <div class="w_center w_55">
                      <div class="progress">
                        <div class="progress-bar bg-green" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: <%=sensor_data.get("Sensor_Data_Module_TEMP")%>;">
                          <span class="sr-only">60% Complete</span>
                        </div>
                      </div>
                    </div>
                    <div class="w_right w_20">
                      <span><%=sensor_data.get("Sensor_Data_Ambient_TEMP")%>℃</span>
                    </div>
                    <div class="clearfix"></div>
                  </div>
                  <div class="widget_summary">
                    <div class="w_left w_25">
                      <span>수평일사량</span>
                    </div>
                    <div class="w_center w_55">
                      <div class="progress">
                        <div class="progress-bar bg-green" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: <%=sensor_data.get("Sensor_Data_Horizontal_IDT")%>;">
                          <span class="sr-only">60% Complete</span>
                        </div>
                      </div>
                    </div>
                    <div class="w_right w_20">
                      <span><%=sensor_data.get("Sensor_Data_Horizontal_IDT")%>W/m^2</span>
                    </div>
                    <div class="clearfix"></div>
                  </div>
                  <div class="widget_summary">
                    <div class="w_left w_25">
                      <span>수직일사량</span>
                    </div>
                    <div class="w_center w_55">
                      <div class="progress">
                        <div class="progress-bar bg-green" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: <%=sensor_data.get("Sensor_Data_Horizontal_IDT")%>;">
                          <span class="sr-only">60% Complete</span>
                        </div>
                      </div>
                    </div>
                    <div class="w_right w_20">
                      <span><%=sensor_data.get("Sensor_Data_Vertical_IDT")%>W/m^2</span>
                    </div>
                    <div class="clearfix"></div>
                  </div>
                  <div class="widget_summary">
                    <div class="w_left w_25">
                      <span>풍속</span>
                    </div>
                    <div class="w_center w_55">
                      <div class="progress">
                        <div class="progress-bar bg-green" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: <%=sensor_data.get("Sensor_Data_Wind_SPEED")%>;">
                        </div>
                      </div>
                    </div>
                    <div class="w_right w_20">
                      <span><%=sensor_data.get("Sensor_Data_Wind_SPEED")%>m/s</span>
                    </div>
                  </div> 
           

                </div>
              </div>
            </div>
            <!-- Rmx -->
            
            
				
			<%
			Map<String, Map> weather = (Map<String, Map>) request.getAttribute("weather");
            Map<String, Object> weather_t = map.get("weather");
			%>
              <!-- start of weather widget -->
                <div class="col-md-6 col-sm-4 col-xs-12">
                  <div class="x_panel">
                    <div class="x_title">
                      <h2>날씨정보</h2>
                      <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                      <div class="row">
                        <div class="col-sm-4">
                          <div class="temperature"><h5><span id="clock" ></span></h5>
                          </div>
                        </div>
                      </div>
                      <div class="row">
                        <div class="col-sm-4">
                          <div class="weather-icon">
                            <canvas height="84" width="84" id="partly-cloudy-day"></canvas>
                          </div>
                        </div>
                  
                      </div>
               

                      <div class="clearfix"></div>

                      <div class="row weather-days">
                        <div class="col-sm-2">
                          <div class="daily-weather">
                            <h2 class="day">지역</h2>
                            <h3 class="degrees"><%=weather_t.get("area_name")%></h3>
                          </div>
                        </div>
                        <div class="col-sm-2">
                          <div class="daily-weather">
                            <h2 class="day">최저온도</h2>
                            <h3 class="degrees"><%=weather_t.get("weather_T_Min") %></h3>
                          </div>
                        </div>
                        <div class="col-sm-2">
                          <div class="daily-weather">
                            <h2 class="day">최고온도</h2>
                            <h3 class="degrees"><%=weather_t.get("weather_T_Max") %></h3>
                          </div>
                        </div>
                        <div class="col-sm-2">
                          <div class="daily-weather">
                            <h2 class="day">미세먼지</h2>
                            <h3 class="degrees"><%=weather_t.get("A") %></h3>
                          </div>
                        </div>
                        <div class="col-sm-2">
                          <div class="daily-weather">
                            <h2 class="day">초미세먼지</h2>
                            <h3 class="degrees"><%=weather_t.get("Sensor_Data_Wind_SPEED") %></h3>
                          </div>
                        </div>
                        <div class="col-sm-2">
                          <div class="daily-weather">
                            <h2 class="day">오존</h2>
                            <h3 class="degrees"><%=weather_t.get("Sensor_Data_Vertical_IDT") %></h3>
                          </div>
                        </div>
                        <div class="clearfix"></div>
                      </div>
                    </div>
                  </div>
                </div>
          </div>
                <!-- end of weather widget -->


          <div class="row">
          	  <!--------------------- 지역 - 발전소 조회----------------------------  -->
             <div class="col-md-12 col-sm-15 col-xs-15">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>트래커 제어하기</h2>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">

                    <div class="col-md-8 col-sm-8 col-xs-12 form-group pull-left top_search">
                <div class="col-md-2">
				  <label for="list-types">군집 번호</label>
                <div class="select-wrap">
                  <span class="icon icon-arrow_drop_down"></span>
                  <select name="list-types" id="sel_GID" class="form-control d-block rounded-0">
                    <option value="">1</option>
                    <option value="">2</option>
                    <option value="">3</option>
                  </select>
                </div>
				</div>
                <div class="col-md-2">
                  <label for="list-types">트래커 아이디</label>
                <div class="select-wrap">
                  <span class="icon icon-arrow_drop_down"></span>
                  <select name="list-types" id="sel_TID" class="form-control d-block rounded-0">
                    <option value="">1</option>
                    <option value="">2</option>
                    <option value="">3</option>
                  </select>
                </div>
                </div>
                 <div class="col-md-2">
                   <label for="list-types">스노우 모드</label>
                 <input type="checkbox"  checked data-toggle="toggle" data-on="Snow On" data-off="Snow OFF" data-onstyle="success" data-offstyle="danger">
                 </div>
                 <div class="col-md-2">
                   <label for="list-types">그림자 모드</label>
               <input type="checkbox" checked data-toggle="toggle" data-on="Shadow On" data-off="Shadow OFF" data-onstyle="success" data-offstyle="danger">
                 </div>
                 <div class="col-md-2">
                   <label for="list-types">윈드 모드</label>
              <input type="checkbox" checked data-toggle="toggle" data-on="Wind On" data-off="Wind OFF" data-onstyle="success" data-offstyle="danger">
                 </div>
                 <div class="col-md-2">
                   <label for="list-types">음영회피 모드</label>
              <input type="checkbox" checked data-toggle="toggle" data-on="shadow On" data-off="Shadow OFF" data-onstyle="success" data-offstyle="danger">
                 </div>
                       <div class="clearfix"></div>
                 <div class="col-md-6">
                  <!-- Small modal -->
                  <button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target=".bs-example-modal-sm">트래커 제어버튼</button>

                  <div class="modal fade bs-example-modal-sm" tabindex="-3" role="dialog" aria-hidden="true">
                    <div class="modal-dialog modal-sm">
                      <div class="modal-content">

                        <div class="modal-header">
                          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span>
                          </button>
                          <h4 class="modal-title" id="myModalLabel2">원하시는 방향을 눌러주세요.</h4>
                        </div>
                   
                        <div class="modal-footer">
                        <button id="btn_left"   class="btn btn-primary" type="button"   style="margin-top: 23px;"onclick="doSend(getElementById('sel_GID').value + ',' + getElementById('sel_TID').value + ',left')">좌항</button>
                		<button  id="btn_right" class="btn btn-primary" type="button" style="margin-top: 23px;" onclick="doSend(getElementById('sel_GID').value + ',' + getElementById('sel_TID').value + ',right')">우향</button>
                		<button id="btn_right"  class="btn btn-primary" type="button"  style="margin-top: 23px;"  onclick="doSend(getElementById('sel_GID').value + ',' + getElementById('sel_TID').value + ',auto')">자동</button>
                          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>

                      </div>
                    </div>
                  </div>
                  <!-- /modals --> 
                 </div>
              </div> 
                    <!-- start pop-over -->
         
                    <!-- end pop-over -->
                          

                  </div>
                </div>
              </div>
						<!-- 트래커 제어 부분 -->
					<script type="text/javascript">
	var addr = "192.168.0.251:8889";
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
		writeToScreen(addr + " 트래커 서버에 연결되었습니다.");
		//doSend("Test Message");
	}
	
	function onClose(evt) {
		writeToScreen("연결해제");
	}
	
	function onMessage(evt) {
		writeToScreen('<span style="color: blue;"> 수신 : ' + evt.data + '</span>');
		//webSocket.close();
	}
	
	function onError(evt) {
		writeToScreen('<span style="color: red;">에러:</span> ' + evt.data);
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
						<!-- 트래커 제어 부분 -->
            <div>
            </div>


              <div class="row">
                             <div class="col-md-12 col-sm-15 col-xs-15">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>고장 정보</h2>
                    <ul class="nav navbar-right panel_toolbox">
                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                      </li>
                      <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                        <ul class="dropdown-menu" role="menu">
                          <li><a href="#">Settings 1</a>
                          </li>
                          <li><a href="#">Settings 2</a>
                          </li>
                        </ul>
                      </li>
                      <li><a class="close-link"><i class="fa fa-close"></i></a>
                      </li>
                    </ul>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
			

                    <div class="table-responsive">
                      <table class="table table-striped jambo_table bulk_action">
                        <thead>
                          <tr class="headings">
                            <th>
                              <input type="checkbox" id="check-all" class="flat">
                            </th>
                     		<th class="column-title">번호 </th>
                            <th class="column-title">군집 ID </th>
                            <th class="column-title">알림 타입 </th>
                            <th class="column-title">알림 등급 </th>
                            <th class="column-title">시간 </th>
                            <th class="column-title no-link last"><span class="nobr">Action</span>
                            </th>
                            <th class="bulk-actions" colspan="7">
                              <a class="antoo" style="color:#fff; font-weight:500;">Bulk Actions ( <span class="action-cnt"> </span> ) <i class="fa fa-chevron-down"></i></a>
                            </th>
                          </tr>
                        </thead>
					
                        <tbody>
                          <tr class="even pointer">
                            <td class="a-center ">
                              <input type="checkbox" class="flat" name="table_records">
                            </td>
                            <td class=" "><%=Alaram.get("alarm_idx")%></td>
                            <td class=" "><%=Alaram.get("t_group_id")%></td>
                            <td class=" "><%=Alaram.get("alarm_type")%><i class="success fa fa-long-arrow-up"></i></td>
                            <td class="a-right a-right"><%=Alaram.get("alarm_grade")%></td>
                            <td class="last"><%=Alaram.get("alarm_reg_data")%></td>
                          </tr>
                 
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
              </div>
              </div>
              <div class="row">
	

                <!-- end of weather widget -->
              </div>
            </div>
          </div>
        </div>
        <!-- /page content -->

      </div>
    </div>
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


    <!-- Custom Theme Scripts -->
	<script src="${build}/js/custom.min.js"></script>
    
    <!-- 현재 시간을 나타내는 Script -->
	<script>
		function printTime() {

			var clock = document.getElementById("clock"); // 출력할 장소 선택
			var now = new Date(); // 현재시간
			var nowTime =  (now.getMonth() + 1)	+ "월 " + now.getDate() + "일 &nbsp;" + now.getHours() + "시" + now.getMinutes() + "분 " + now.getSeconds() + "초";
			clock.innerHTML = nowTime; // 현재시간을 출력
			setTimeout("printTime()", 1000); // setTimeout(“실행할함수”,시간) 시간은1초의 경우 1000
		}
		window.onload = function() { // 페이지가 로딩되면 실행
			printTime();
		}
	</script>
	<!-- 끄 -->
	
  </body>
</html>
