<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/include/common.jsp"%>
<!DOCTYPE html>
<html>
  <head>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />  
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>

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
		
		 <!-- Datatables -->
    <link href="${vendors}/datatables.net-bs/css/dataTables.bootstrap.min.css" rel="stylesheet">
    <link href="${vendors}/datatables.net-buttons-bs/css/buttons.bootstrap.min.css" rel="stylesheet">
    <link href="${vendors}/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css" rel="stylesheet">
    <link href="${vendors}/datatables.net-responsive-bs/css/responsive.bootstrap.min.css" rel="stylesheet">
    <link href="${vendors}/datatables.net-scroller-bs/css/scroller.bootstrap.min.css" rel="stylesheet">
	<link href="${build}/css/pup.css" rel="stylesheet">
	<script type="text/javascript" src="${path}/resources/js/comm.js"></script>
  </head>

<body class="nav-md">
    <div class="container body">
      <div class="main_container">
           <!-- sidebar menu -->
            <!-- top navigation -->
        
        <div class="top_nav">
                  
              <ul class="nav navbar-nav navbar-right">
              <li class="">
              </li>
              
                    <li class="">
                	<a onClick="changeLang('en');">
                	<img  src="${path}/image/USA1.png">
                	</a>
                </li>
                <li class="">
                	<a onClick="changeLang('ko');">
                	<img  src="${path}/image/korean1.png">
                	</a>
                </li>
              </ul> 

        </div>
        <!-- /top navigation -->
        <!-- page content -->
        <input type="hidden" id="hidden_menu_id" value="inverter" />
        <div class="right_col" role="main">
          <div class="">
   

            <div class="clearfix"></div>

            <div class="row">
              <div style="width:1645px; margin-left:5px; margin-top:50px; ">
                <div class="x_panel">
                  <div class="x_title">
                  <h2><spring:message code="part.H9" text="default text"/></h2>
         
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                  <form action="${path}/inverterBoard/delete" method="post">
                  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <table id="datatable" class="table table-striped table-bordered">
                     <thead>
                        <tr bgcolor="#FFFFFF" onmouseover="this.style.backgroundColor='#E1FFFF'"
                        onmouseout="this.style.backgroundColor='#FFFFFF'">
                          <th style="text-align:center;"><spring:message code="part.G8" text="default text"></spring:message></th>
                          <th style="text-align:center;"><spring:message code="part.G1" text="default text"></spring:message></th>
                           <th style="text-align:center;"><spring:message code="part.G2" text="default text"></spring:message></th>
                           <th style="text-align:center;"><spring:message code="part.G3" text="default text"></spring:message></th>
                           <th style="text-align:center;"><spring:message code="part.G4" text="default text"></spring:message></th>
                           <th style="text-align:center;"><spring:message code="part.G5" text="default text"></spring:message></th>
                           <th style="text-align:center;"><spring:message code="part.G6" text="default text"></spring:message></th>
                           <th style="text-align:center;"><spring:message code="part.G7" text="default text"></spring:message></th>
                        </tr>
                      </thead>
              <tbody> 
                      <c:forEach var="row" items="${list}">
                        <tr bgcolor="#FFFFFF" onmouseover="this.style.backgroundColor='#E1FFFF'"
                        onmouseout="this.style.backgroundColor='#FFFFFF'">
                          <td align="center">${row.inverter_idx}</td>
                          <td align="center">${row.inverter_id}</td>
                          <td align="center">${row.inverter_group_name}</td>
                          <td align="center">${row.inverter_model}</td>
                          <td align="center">${row.inverter_capacity}</td>
                          <td align="center">${row.inverter_username}</td>
                          <td align="center">${row.inverter_phone}</td>
                          <td align="center">
                           <fmt:formatDate  value="${row.inverter_reg_date}" pattern="yyyy-MM-dd HH:mm:ss"/>
                           </td>
                        </tr>
                        </c:forEach>
                      </tbody>
                    </table>
        			</form>
                  </div>
                </div>
              </div>
                  </div><!-- Row -->
                </div>
              </div>
            </div>
          </div>
        <!-- /page content -->
		<script>
		</script>
<script type="text/javascript">

	$(document).ready(function() {
		console.log("current lang is " + "${lang}");
	});

</script>
		<!-- 체크박스 스크립트 -->
        <!-- footer content -->

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
    <!-- Datatables -->
    
    <script src="${vendors}/datatables.net/js/jquery.dataTables.min.js"></script>
    <script src="${vendors}/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
    <script src="${vendors}/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
    <script src="${vendors}/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
    <script src="${vendors}/datatables.net-buttons/js/buttons.flash.min.js"></script>
    <script src="${vendors}/datatables.net-buttons/js/buttons.html5.min.js"></script>
    <script src="${vendors}/datatables.net-buttons/js/buttons.print.min.js"></script>
    <script src="${vendors}/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
    <script src="${vendors}/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
    <script src="${vendors}/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
    <script src="${vendors}/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
    <script src="${vendors}/datatables.net-scroller/js/dataTables.scroller.min.js"></script>
    <script src="${vendors}/jszip/dist/jszip.min.js"></script>
    <script src="${vendors}/pdfmake/build/pdfmake.min.js"></script>
    <script src="${vendors}/pdfmake/build/vfs_fonts.js"></script>
    <script type="text/javascript" src="${path}/resources/js/comm.js"></script>

    <!-- Custom Theme Scripts -->
    <script src="${build}/js/custom.min.js"></script>

  </body>
</html>