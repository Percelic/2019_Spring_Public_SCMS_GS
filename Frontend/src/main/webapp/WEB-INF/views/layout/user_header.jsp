<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ include file="/WEB-INF/views/include/common.jsp"%>

<!--  HEAD 정보 -->
<div class="col-md-3 left_col">
          <div class="left_col scroll-view">
            <div class="navbar nav_title" style="border: 0;">
             <h3 align="center" style="color: white ; margin-top: 20px;"><span class="titleBold">태양광</span> <br />모니터링 시스템 </h3>
             <h3 align="center" style="color: #09f";>Solar<br>Monitoring<br>System V1.0</p>

              
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
		<spring:message code="en.account" arguments="${msg}" />
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


            <!-- sidebar menu -->
            <div id="" class="main_menu_side hidden-print main_menu">
              <div class="menu_section">
                <ul class="nav side-menu">
                  <li><a href="javascript:;" onclick="user_viewIndex();"><i class="fa fa-home"></i><h4><spring:message code="part.H10" text="default text"></spring:message></h4></a>
                  </li>
                  
  					 <li><a onclick="leftMenuDown();"><i class="fa fa-table"></i> <h4><spring:message code="part.H2" text="default text"></spring:message></h4> <span class="fa fa-chevron-down"></span></a>
  					 
                    <ul class="nav child_menu" id="child_menu">
                      <li id="group_menu_li"><a onclick="user_GroupBoard();"><h4><spring:message code="part.H7" text="default text"></spring:message></h4></a></li>
                      <li id="tracker_menu_li"><a onclick="user_TrackerBoard();"><h4><spring:message code="part.H8" text="default text"></spring:message></h4></a></li>
                      <li id="inverter_menu_li"><a onclick="user_InverterBoard();"><h4><spring:message code="part.H9" text="default text"></spring:message></h4></a></li>
                    </ul>
                    
                    <li><a href="https://drive.google.com/file/d/1zcICUZDidSV03m6oGoVZ6YILhIlaygoY/view?usp=sharing" target="_blank" ><h4><spring:message code="en.31" text="default text"></spring:message></h4></a></li>
                  </li>
                 </ul>
              </div>
            </div>
        </div>
        </div>
<form name="" id="head_form" method="get"> 
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<input type="hidden" id="head_t_group_id" name="t_group_id" value="${tGroupId}"/>
	<input type="hidden" id="head_lang" name="lang" value="${_lang}" />
	<input type="hidden" id="n_page" name="nPage" value="${nowPage}"/>
</form>		
					