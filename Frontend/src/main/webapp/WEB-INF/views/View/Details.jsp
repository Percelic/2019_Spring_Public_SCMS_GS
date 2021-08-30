<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<script type="text/javascript" src="${path}/resources/js/event.js"></script>

<!-- LANG -->
<div class="top_nav">
	<ul class="nav navbar-nav navbar-right">
		<li class=""></li>
		<li class="">
<%-- 			<a  href="<c:url value="/View/Details?lang=en" />"> --%>
			<a  href="javascript:langBtn2('en')">
				<img  src="${path}/image/USA1.png">
			</a>
		</li>
		<li class="">
<%-- 			<a  href="<c:url value="/View/Details?lang=ko" />"> --%>
			<a  href="javascript:langBtn2('ko')">
				<img  src="${path}/image/korean1.png">
			</a>
		</li>
	</ul> 
	
			<script>
				console.log("current lang is " + "${lang}");
			  </script>
</div>
<!-- LANG END -->
<div class="right_col" role="main">
          <div class="">
            <div class="clearfix"></div>
             	<input type="hidden" name="${csrf.parameterName}" value="${csrf.token}"  />

            <div class="row">
              <div style="width:1645px; margin-left:5px; margin-top:50px; ">
                <div class="x_panel">
                  <div class="x_title">
                    <h2><spring:message code="part.H1" text="default text"></spring:message></h2>
         
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <table id="datatable" class="table table-striped table-bordered">
                      <thead>
                        <tr class="headings">
                          <td align="center" ><spring:message code="part.C1" text="default text"></spring:message></td>
                          <td align="center" ><spring:message code="part.C2" text="default text"></spring:message></td>
                          <td align="center" ><spring:message code="part.C3" text="default text"></spring:message></td>
                          <td align="center" ><spring:message code="part.C4" text="default text"></spring:message></td>
                          <td align="center" ><spring:message code="part.C5" text="default text"></spring:message></td>
                        </tr>
                      </thead>
                      <tbody>
					<c:forEach var="row" items="${list}">
                         <tr bgcolor="#FFFFFF" onmouseover="this.style.backgroundColor='#E1FFFF'"
                        onmouseout="this.style.backgroundColor='#FFFFFF'">
                          <td align="center" ><a href="javascript:;" onclick="viewIndex(${row.t_group_id});">${row.t_group_id}</a></td>
                          <td align="center" ><a href="javascript:;" onclick="viewIndex(${row.t_group_id});">${row.t_group_name}</a></td>
                          <td align="center" ><a href="javascript:;" onclick="viewIndex(${row.t_group_id});">${row.t_group_capacity}</a></td>
                          <td align="center" ><a href="javascript:;" onclick="viewIndex(${row.t_group_id});">${row.t_group_addr}</a></td>
                          <td align="center" ><a href="javascript:;" onclick="viewIndex(${row.t_group_id});">${row.t_group_ip}</a></td>
                        </tr>
                   </c:forEach>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
                  </div><!-- Row -->
                </div>
              </div>
        <!-- /page content -->
        
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