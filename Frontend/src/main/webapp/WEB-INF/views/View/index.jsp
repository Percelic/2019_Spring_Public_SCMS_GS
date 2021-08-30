<%@page import="java.net.InetAddress"%>
<%@page import="java.security.acl.Group"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ include file="/WEB-INF/views/include/common.jsp"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
	<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
    
<!DOCTYPE html>
<html>
		
	<!--  구글  -->
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<link href="${build}/css/pup.css" rel="stylesheet">
	<script type="text/javascript" src="${path}/resources/js/comm.js"></script>
<!-- --------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->
 
  <body class="nav-md">
    <div class="container body">
      <div class="main_container">
      
        <!-- top navigation -->
        <div class="top_nav">
              <ul class="nav navbar-nav navbar-right">
				<li class="">
				 	<a  href="javascript:langBtn('en')">
				<img  src="${path}/image/USA1.png">
					</a>
				</li>
				<li class="">
					<a  href="javascript:langBtn('ko')">
				<img  src="${path}/image/korean1.png" >
					</a>
				</li>
				<li class="clock_box">
					<spring:message code="en.monitoring_01" text="def"/> : <span id="clock"></span>
				</li>
              </ul> 
              
              
              <script>
				console.log("current lang is " + "${lang}");
			  </script>
        </div>
        <!-- /top navigation -->

        <!-- page content -->
        <div class="right_col" role="main">
          <!-- top tiles -->
        <div class="row top_tiles">
        	<div class="row" style="position:relative; width:1644px; height:130px; margin-left:0px;  margin-top:40px;">
		    	<div style="width:819.5px; height:130px; float:left;">
		           <div class="tile-stats">
		               <span class="count_top"><h4><i class="fa fa-home" style="margin-top: 10px; margin-left: 20px;">&nbsp;<spring:message code="part.A1" text="default text"></spring:message></i></h4></span>
		              <div class="count black" style="margin-top: 5px;" align="center" >${dto.t_group_name}</div>
		           </div>
		        </div>  
		        <div style="width:819.5px; height:130px; float:left; margin-left:5px;">
		          <div class="tile-stats" >
		              <span class="count_top"><h4><i class="fa fa-user" style="margin-top: 10px; margin-left: 20px;">&nbsp;<spring:message code="part.A2" text="default text"></spring:message></i></h4></span> 
		            <div class="count black" style="margin-top: 5px;" align="center">${dto.t_group_manager}</div>
		          </div>
		        </div>
	        </div>
	       
			
			<div class="row" style="position:relative; width:1644px; height:130px; margin-left:0px;">
		        <div style="width:409.5px; height:170px; float:left;">
			         <div class="tile-stats" >							<!-- 금일 발전량 -->
			           <span class="count_top"><h4><i class="fa fa-line-chart" style="margin-top: 10px; margin-left: 20px;"> &nbsp;</i><spring:message code="part.A3" text="default text"></spring:message></h4></span>
			           <div class="count " style="color: #07f;" align="center">${inverter_today.inverter_data_accu_energy}<h4 style="font-weight: 900;">kWh</h4></div>
			         </div>
		       </div>
		       
		       <div style="width:409.5px; height:170px; float:left; margin-left:2px;">
			          <div class="tile-stats" >             <!-- 전일발전량 --> 
			            <span class="count_top"><h4><i class="fa fa-line-chart"  style="margin-top: 10px; margin-left: 20px;">&nbsp;</i><spring:message code="part.A4" text="default text"></spring:message></h4></span>
			            <div class="count" style="color: #07f;" align="center">${inverter_date.inverter_data_accu_energy}<h4 style="font-weight: 900;">kWh</h4></div>
			          </div>
		        </div>
		        <div style="width:409.5px; height:170px; float:left; margin-left:2px;">
			          <div class="tile-stats" >							 <!-- 현재 출력 -->	
			             <span class="count_top"><h4><i class="fa fa-line-chart" style="margin-top: 10px; margin-left: 20px;">&nbsp;</i><spring:message code="part.A5" text="default text"></spring:message></h4></span>
			            <div class="count" style="color: #07f;" align="center">${total_data.inverter_data_output}<h4 style="font-weight: 900;">kW</h4></div>
			          </div>
		        </div>
		              
		         <div style="width:409.5px; height:170px; float:left; margin-left:2px;">
			         <div class="tile-stats" >								<!-- 총 누적 발전량 -->
			            <span class="count_top"><h4><i class="fa fa-line-chart" style="margin-top: 10px; margin-left: 20px;">&nbsp;</i><spring:message code="part.A6" text="default text"></spring:message></h4></span>
			          <div class="count" style="font-weight: 900; color: red;"  align="center">${Cumulative_power_generation.inverter_data_accu_energy}<h4 style="color: white-space; font-weight: 900;">kWh</h4></div>
			         </div>				
				 </div>
			 </div>
          </div>
          <!-- /top tiles -->




          <div class="row">
				<!-- 센서 데이터 -->
           <div style="width:548.98px; height:300px; float:left;">
              <div class="x_panel fixed_height_320" style="height: 290px;">
                  <div class="x_title" style="height: 76px; margin-top: 7px;">
                  	
                    <h2 style="font-size: 7mm; font-weight: bolder;">
                    <img src="${path}/image/h003.png">
                    <spring:message code="part.B1" text="default text"></spring:message></h2>
           
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
            <div style="width:1089.98px; height:301px; float:left; margin-left:5px;">
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
                            <h3 align="center">${weather.weather_ozone_concentration}&nbsp;ppm</h3>
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
	          <div style="width:1654px; height:191px; padding-right: 10px; padding-left: 0px;">
                 <div class="x_panel">
                    <h2 style="font-size: 7mm; font-weight: bolder; margin-bottom: 10px;">
                    &nbsp;<spring:message code="part.B3" text="default text"></spring:message></h2>
                      <div class="x_title">
                  </div>
                  	       <div class="col-sm-1" style="margin-top: 20px;">
                  <label for="list-types"><spring:message code="part.B3_1" text="default text"/></label><br />
                <div class="select-wrap">
                  <span class="icon icon-arrow_drop_down"></span>
                  <select name="list-types" id="sel_TID" class="form-control d-block rounded-0">
                  	<c:if test="${fn:length(trackerList) != 0}">
                  		<c:forEach items="${trackerList}" var="a">
                    		<option value="${a.tracker_id}">${a.tracker_id}</option>
                    	</c:forEach>
                  	</c:if>
                  	<c:if test="${fn:length(trackerList) == 0}">
                    	<option value="">No Data</option>
                  	</c:if>
                  </select>
                </div>
                </div>
              
                <div class="col-sm-1" style="margin-top: 20px;">
				<button type="button"  class="btn btn-info btn-sm left_col" data-toggle="modal" data-target=".bs-example-modal-sm" style="margin-top: 25px;">
				<spring:message code="part.B3_2" text="default text"/></button>
                  </div>
                  
                  
                  
                 	 <div class="col-sm-2" id="btn_group" style="margin-left: 150px; margin-top: 40px;">
	             		<label style="font-size:15px; text-align:right;"><spring:message code="part.B3_8" text="default text"/></label>
						<label class="switch switch-label switch-primary" style="width:48px; margin-left:10px; margin-bottom:-7px;">
							<input class="switch-input" id="shadowchk" type="checkbox" onClick="ShadowToggle(this);">
							<span class="switch-slider" data-checked="ON" data-unchecked="OFF"></span>
						</label>
											
<!-- 										<fieldset> -->
<%-- 											<button id="btn_shadowmode" class="btn btn-primary" onClick="doSend(${dto.t_group_id} + ',' + getElementById('sel_TID').value + ',' + '1,H' + ',' + '0,0,0,0')" style="margin: 0px 0px 0px 25px;"><spring:message code="part.B3_8" text="default text"/></button>		 --%>
<!-- 											<input type="checkbox" class="hide"/> -->
<!-- 										</fieldset>  -->
					</div>
                  	<div class="col-sm-2" style="margin-top: 40px;">
                  		<label style="font-size:15px; text-align:right;"><spring:message code="part.B3_9" text="default text"/></label>
						<label class="switch switch-label switch-custom1" style="width:48px; margin-left:10px; margin-bottom:-7px;">
							<input class="switch-input" id="snowchk" type="checkbox" onClick="SnowToggle(this);">
							<span class="switch-slider" data-checked="ON" data-unchecked="OFF"></span>
						</label>				
                  
<!-- 									<fieldset id="1"> -->
<%-- 										<button id="btn_snowmode" class="btn btn-primary" onClick="doSend(${dto.t_group_id} + ',' + getElementById('sel_TID').value + ',' + '1,S' + ',' + '0,0,0,0')" style="margin: 0px 0px 0px 25px;"><spring:message code="part.B3_9" text="default text"/></button> --%>
<!-- 										<input type="checkbox" class="hide"/> -->
<!-- 									</fieldset> -->
					</div>
                  	<div class="col-sm-2" style="margin-top: 40px;">
                  		<label style="font-size:15px; text-align:right;"><spring:message code="part.B3_10" text="default text"/></label>
						<label class="switch switch-label switch-primary" style="width:48px; margin-left:10px; margin-bottom:-7px;">
							<input class="switch-input" id="windchk" type="checkbox" onClick="WindToggle(this);">
							<span class="switch-slider" data-checked="ON" data-unchecked="OFF"></span>
						</label>
<!-- 										<fieldset id=""> -->
<%-- 									<button id="btn_windmode" class="btn btn-primary" onClick="doSend(${dto.t_group_id} + ',' + getElementById('sel_TID').value + ',' + '1,W' + ',' + '0,0,0,0')" style="margin: 0px 0px 0px 25px;"><spring:message code="part.B3_10" text="default text"/></button> --%>
<!-- 												<input type="checkbox" class="hide"/> -->
<!-- 										</fieldset> -->
					</div>
                   
                  <div class="col-sm-2" style="margin-top: 40px;">
                  		<label style="font-size:15px; text-align:right;"><spring:message code="part.B3_11" text="default text"/></label>
						<label class="switch switch-label switch-custom1" style="width:48px; margin-left:10px; margin-bottom:-7px;">
							<input class="switch-input" id="nightchk" type="checkbox" onClick="NightToggle(this);">
							<span class="switch-slider" data-checked="ON" data-unchecked="OFF"></span>
						</label>
<!-- 										<fieldset id="1"> -->
<%-- 								<button id="btn_nightmode" class="btn btn-primary" onClick="doSend(${dto.t_group_id} + ',' + getElementById('sel_TID').value + ',' + '1,N' + ',' + '0,0,0,0')" style="margin: 0px 0px 0px 25px;"><spring:message code="part.B3_11" text="default text"/></button>	 --%>
<!-- 											<input type="checkbox" class="hide"/> -->
<!-- 										</fieldset> -->
									</div> 


                  <div class="x_content">
                  
                  	
				    <div class="modal fade bs-example-modal-sm" tabindex="-3" role="dialog" aria-hidden="true">
                    <div class="modal-dialog modal-sm">
                      <div class="modal-content">
                      	
                        <div class="modal-header">
                          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span>
                          </button>
                          <h4 class="modal-title" id="myModalLabel2"><spring:message code="part.B3_7" text="default text"/></h4>
                        </div> 
                   
                        <div class="modal-footer">
                        <button id="btn_left" style="margin-right: 20px;" class="btn btn-danger" onmouseup="doSend(mouseupMessage())" onmousedown="interval_send(mousedownMessage('left'),5000)"><spring:message code="part.B3_3" text="default text"/></button>    
						<button id="btn_right" style="margin-right: 20px;"  class="btn btn-danger" onmouseup="doSend(mouseupMessage())" onmousedown="interval_send(mousedownMessage('right'),5000)"><spring:message code="part.B3_4" text="default text"/></button>
						<button id="btn_stop" style="margin-right: 20px;" class="btn btn-primary" onclick="doSend(mouseupMessage())" ><spring:message code="part.B3_5" text="default text"/></button>
                         
                        </div>
                      </div><!-- 1 --> 
                    </div><!-- 2 -->
                  </div><!-- 3 -->
                  <!--  모달 아이디 끝. -->

                  </div>
                </div>
              </div>
              <!-- 스크립트 -->
                          <script>
                          var $mode = 'h';
                          
                          $(document).ready(function(){
                            $("#hide").click(function(){
                              $("p").hide();
                            });
                            $("#show").click(function(){
                              $("p").show();
                            });
                           
                            
                            
                            InitToggle(); 
                          });
                          </script>
                          
                         <script type="text/javascript">
                         	
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
	          <div style="width:1654px; padding-right: 10px; padding-left: 0px;">
                 <div class="x_panel">
                 <h2 style="font-size: 7mm; font-weight: bolder; margin-bottom: 10px;">&nbsp;<spring:message code="part.B4" text="default text"></spring:message></h2>
                  <div class="x_title">
                  </div>
                  <div class="x_content">
			

                    <div class="table-responsive">
                      <table class="table table-hover">
                        <thead>
                          <tr class="headings">
                            <th class="column-title" style="text-align:center; width:20%;"><spring:message code="part.B4_4" text="default text"/> </th>
                            <th class="column-title" style="text-align:center; width:10%;"><spring:message code="part.B4_1" text="default text"/> </th>
                            <th class="column-title" style="text-align:center; width:10%;"><spring:message code="part.B4_3" text="default text"/> </th>
                            <th class="column-title" style="text-align:center; width:60%;"><spring:message code="part.B4_5" text="default text"/></th>
<%--                             <th class="column-title no-link last" style="text-align:center;"><span class="nobr"><spring:message code="part.B4_5" text="default text"></spring:message></span> --%>
                            </th>
                            <th class="bulk-actions" colspan="7">
                              <a class="antoo" style="color:#fff; font-weight:500;">Bulk Actions ( <span class="action-cnt"> </span> ) <i class="fa fa-chevron-down"></i></a>
                            </th>
                          </tr>
                        </thead>
					
                        <tbody>
                        	<c:forEach var="row" items="${t_alarm}">
	                        	<tr class="even pointer">
<%-- 		                          	<c:if test="${not empty t_alarm}}"> --%>
		                          		<td class=" " style="text-align:center;">${row.alarm_upt_data}</td>
		                          		<td class=" " style="text-align:center;">${row.tracker_id}</td>
			                            <td class="a-right a-right" style="text-align:center;">${row.alarm_status}</td>
			                            <td class="last" style="text-align:center;">${row.alarm_contents}</td>
<%-- 		                          	</c:if> --%>
		                            <c:if test="${empty t_alarm}">
		                          		<td class=" " colspan="4">No Data</td>
		                          	</c:if>
								</tr>
							</c:forEach>
                          <%--  <c:forEach items="${t_alarm}" var="a">
                           
                            	<tr class="even pointer">
	                            	<td class=" ">${a.t_group_id}</td>
		                            <td class=" ">${a.alarm_type}</td>
		                            <td class="a-right a-right">${a.alarm_status}</td>
		                            <td class="last">${a.alarm_upt_data}</td>
		                            <td class=" ">${a.alarm_grade}<i class="success fa fa-long-arrow-up"></i></td>
	                            </tr>	
                            </c:forEach> --%>
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

	<!-- 현재 시간을 나타내는 Script -->
	<script>
		function ShadowToggle(cb) {			
			if(cb.checked) {
				$mode = 'H';
				doSend("${dto.t_group_id}" + ',' + document.getElementById('sel_TID').value + ',' + '1,H' + ',' + '0,0,0,0');
				
				$("input:checkbox[id='snowchk']").prop("checked",false);
				$("input:checkbox[id='windchk']").prop("checked",false);
				$("input:checkbox[id='nightchk']").prop("checked",false);
				
				
			}
			else {
				$mode = 'h';
				doSend("${dto.t_group_id}" + ',' + document.getElementById('sel_TID').value + ',' + '1,h' + ',' + '0,0,0,0');
			}
		}
		
		function SnowToggle(cb) {
			if(cb.checked) {
				$mode = 'S';
				doSend("${dto.t_group_id}" + ',' + document.getElementById('sel_TID').value + ',' + '1,S' + ',' + '0,0,0,0');
				
				$("input:checkbox[id='shadowchk']").prop("checked",false);
				$("input:checkbox[id='windchk']").prop("checked",false);
				$("input:checkbox[id='nightchk']").prop("checked",false);
			}
			else {
				$mode = 'h';
				doSend("${dto.t_group_id}" + ',' + document.getElementById('sel_TID').value + ',' + '1,h' + ',' + '0,0,0,0');
			}
		}
		
		function WindToggle(cb) {
			if(cb.checked) {
				$mode = 'W';
				doSend("${dto.t_group_id}" + ',' + document.getElementById('sel_TID').value + ',' + '1,W' + ',' + '0,0,0,0');
				
				$("input:checkbox[id='shadowchk']").prop("checked",false);
				$("input:checkbox[id='snowchk']").prop("checked",false);
				$("input:checkbox[id='nightchk']").prop("checked",false);
			}
			else {
				$mode = 'h';
				doSend("${dto.t_group_id}" + ',' + document.getElementById('sel_TID').value + ',' + '1,h' + ',' + '0,0,0,0');
			}
		}
		
		function NightToggle(cb) {
			if(cb.checked) {
				$mode = 'N';
				doSend("${dto.t_group_id}" + ',' + document.getElementById('sel_TID').value + ',' + '1,N' + ',' + '0,0,0,0')
				
				$("input:checkbox[id='shadowchk']").prop("checked",false);
				$("input:checkbox[id='snowchk']").prop("checked",false);
				$("input:checkbox[id='windchk']").prop("checked",false);
			}
			else {
				$mode = 'h';
				doSend("${dto.t_group_id}" + ',' + document.getElementById('sel_TID').value + ',' + '1,h' + ',' + '0,0,0,0')
			}
		}
		
		function InitToggle() {
			$mode = "${dto.t_group_mode}";
			
			switch("${dto.t_group_mode}") {
			case 'h':
				$("input:checkbox[id='shadowchk']").prop("checked",false);
				$("input:checkbox[id='snowchk']").prop("checked",false);
				$("input:checkbox[id='windchk']").prop("checked",false);
				$("input:checkbox[id='nightchk']").prop("checked",false);
				break;
			case 'H':
				$("input:checkbox[id='shadowchk']").prop("checked",true);
				$("input:checkbox[id='snowchk']").prop("checked",false);
				$("input:checkbox[id='windchk']").prop("checked",false);
				$("input:checkbox[id='nightchk']").prop("checked",false);
				break;
			case 'S':
				$("input:checkbox[id='shadowchk']").prop("checked",false);
				$("input:checkbox[id='snowchk']").prop("checked",true);
				$("input:checkbox[id='windchk']").prop("checked",false);
				$("input:checkbox[id='nightchk']").prop("checked",false);
				break;
			case 'W':
				$("input:checkbox[id='shadowchk']").prop("checked",false);
				$("input:checkbox[id='snowchk']").prop("checked",false);
				$("input:checkbox[id='windchk']").prop("checked",true);
				$("input:checkbox[id='nightchk']").prop("checked",false);
				break;
			case 'N':
				$("input:checkbox[id='shadowchk']").prop("checked",false);
				$("input:checkbox[id='snowchk']").prop("checked",false);
				$("input:checkbox[id='windchk']").prop("checked",false);
				$("input:checkbox[id='nightchk']").prop("checked",true);
				break;
			default:
				$mode = 'h';
				break;
			}
		}
	
		function printTime() {
			var clock = document.getElementById("clock"); // 출력할 장소 선택
			var now = new Date(); // 현재시간
			
			var msg = "<spring:message code='en.clock' arguments='!,@,#,$'/>";
		 	msg = msg.replace('!',(now.getMonth() + 1) );
		 	msg = msg.replace('@',now.getDate());
		 	msg = msg.replace('#',now.getHours());
		 	msg = msg.replace('$',now.getMinutes());
		 	
			
			var nowTime = msg; // (now.getMonth() + 1)	+ "월 " + now.getDate() + "일 &nbsp;" + now.getHours() + "시" + now.getMinutes() + "분 ";
			clock.innerHTML = nowTime; // 현재시간을 출력
			
			//setTimeout("printTime()", 1000); // setTimeout(“실행할함수”,시간) 시간은1초의 경우 1000
		}
		window.onload = function() { // 페이지가 로딩되면 실행
			printTime();
		}  
		
		
		//트래커 제어하기
		var server_ExtIP = "<%=request.getServerName()%>";  
		var server_IntIP = "<%=InetAddress.getLocalHost().getHostAddress()%>";
		var addr = server_ExtIP == "127.0.0.1" ? server_IntIP + ":5100" : server_ExtIP + ":5100";
		
		var Uri = "ws://" + addr;
		
		var output;
		function init() {
		
			output = document.getElementById("output");
			init_WebSocket();
		}
	</script>
		
	<script language="javascript" type="text/javascript">
	
	var server_IntIP = "<%=InetAddress.getLocalHost().getHostAddress()%>";
	var server_ExtIP = "<%=request.getServerName()%>";
	
	
	
	var addr = server_IntIP == "localhost" ? server_IntIP + ":5100" : server_ExtIP + ":5100";
	//var addr = "192.168.0.251:7100"			// test server
	
	var Uri = "ws://" + addr;
	
	var output;
	
	var intervalObj1;

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
		//doSend("Test Message");
	}
	
	function onClose(evt) {
	}
	
	function onMessage(evt) {
		//webSocket.close();
	}
	
	function onError(evt) {
	}
	
	function doSend(message) {
		console.log(message);
		webSocket.send(message);
	}
	
	function mouseupMessage() {
		var _szMsg = ${dto.t_group_id} + ',' + document.getElementById('sel_TID').value + ',' + '0,' + $mode + ',' + '1,0' + ',' + '1,0';
		clearInterval(intervalObj1);
		
		return _szMsg;
	}
	
	function mousedownMessage(dir) {
		var _szMsg = "";
		
		switch(dir) { 
		case 'up':
			_szMsg = ${dto.t_group_id} + ',' + document.getElementById('sel_TID').value + ',' + '0,' + $mode + ',' + '1,1' + ',' + '0,0';
			break;
		case 'down':
			_szMsg = ${dto.t_group_id} + ',' + document.getElementById('sel_TID').value + ',' + '0,' + $mode + ',' + '1,2' + ',' + '0,0';
			break;
		case 'left':
			_szMsg = ${dto.t_group_id} + ',' + document.getElementById('sel_TID').value + ',' + '0,' + $mode + ',' + '0,0' + ',' + '1,1';
			break;
		case 'right':
			_szMsg = ${dto.t_group_id} + ',' + document.getElementById('sel_TID').value + ',' + '0,' + $mode + ',' + '0,0' + ',' + '1,2';
			break;
		}
		
		return _szMsg;
	}
	
	function interval_send(msg,interval) {
		doSend(msg);
		
		intervalObj1 = setInterval(function() {
			doSend(msg);
		},interval);
	}
	
	function writeToScreen(message) {
		var pre = document.createElement("p");
		pre.style.wordWrap = "break-word";
		pre.innerHTML = message;
		output.appendChild(pre);
	}
	
	window.addEventListener("load",init, false);
	
	window.onbeforeunload = function() {
		webSocket.onclose = function() {};
		webSocket.close();
	};
</script>
	<script>
       setTimeout(function(){
           location.reload();
       },900000); // 3000 milliseconds means 3 seconds.
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