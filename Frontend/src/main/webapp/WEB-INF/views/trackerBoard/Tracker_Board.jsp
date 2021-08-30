<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/common.jsp"%>
<script type="text/javascript">
	<!-- 체크박스 스크립트 -->
	/* 체크박스 전체선택, 전체해제 */
	function checkAll(){
	      if( $("#th_checkAll").is(':checked') ){
	        $("input[name=tracker_idx]").prop("checked", true);
	      }else{
	        $("input[name=tracker_idx]").prop("checked", false);
	      }
	}
	
	function chkEvent() {
		var chkCnt = $("input[name=tracker_idx]:checked").length;
		var totalCnt = $("input[name=tracker_idx]").length;
		
		if(chkCnt == totalCnt) {
			$("#th_checkAll").prop("checked", true);
		} else {
			$("#th_checkAll").prop("checked", false);
		}
		
	}
	
	/* 체크박스 전체선택, 전체해제 */
		function checkAll(){
		      if( $("#th_checkAll").is(':checked') ){
		        $("input[name=tracker_idx]").prop("checked", true);
		      }else{
		        $("input[name=tracker_idx]").prop("checked", false);
		      }
		}
		
		function chkEvent() {
			var chkCnt = $("input[name=tracker_idx]:checked").length;
			var totalCnt = $("input[name=tracker_idx]").length;
			
			if(chkCnt == totalCnt) {
				$("#th_checkAll").prop("checked", true);
			} else {
				$("#th_checkAll").prop("checked", false);
			}
			
		}
		
		function deleteCheck() {
			var chkCnt = $("input[name=tracker_idx]:checked").length;
			var msg = "<spring:message code='en.tracker_01' arguments='#'/>";
			
			if(confirm(msg.replace('#',chkCnt))) {
				if(chkCnt > 0) {
					document.getElementById('del').action = "delete";
					document.getElementById('del').method = "post";
					document.getElementById('del').submit();
				}
				else {
					alert("<spring:message code='en.tracker_001' text='def'/>");
				}
			}
			else {
			}
		}
</script>

<script type="text/javascript">
		<!-- 체크박스 스크립트 -->
		
		
		</script>
<input type="hidden" id="hidden_menu_id" value="tracker" />
        <div class="top_nav">
              <ul class="nav navbar-nav navbar-right">
              <li class="">
              </li>
              
                 <li class="">
                	<a  href="<c:url value="/trackerBoard/Tracker_Board?lang=en" />">
                	<img  src="${path}/image/USA1.png">
                	</a>
                </li>
                <li class="">
                	<a  href="<c:url value="/trackerBoard/Tracker_Board?lang=ko" />">
                	<img  src="${path}/image/korean1.png">
                	</a>
                </li>
              </ul> 

        </div>
        <!-- /top navigation -->
      
        <!-- page content -->
        <div class="right_col" role="main">
          <div class="">
   

            <div class="clearfix"></div>

            <div class="row">
              <div style="width:1645px; margin-left:5px; margin-top:50px; ">
                <div class="x_panel">
                  <div class="x_title">
                  <h2><spring:message code="Tracker.a" text="default text"></spring:message></h2>
         
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                  <form id="del" onsubmit="return false;">
                      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <table id="datatable" class="table table-striped table-bordered">
                     <thead>
                        <tr bgcolor="#FFFFFF" onmouseover="this.style.backgroundColor='#E1FFFF'"
                        onmouseout="this.style.backgroundColor='#FFFFFF'">
                          <td align="center"><input type="checkbox"  id="th_checkAll" onclick="checkAll();" /></td>
                  		   <td align="center"><spring:message code="part.F1" text="default text"></spring:message></td>
                  		   <td align="center"><spring:message code="part.F2" text="default text"></spring:message></td>
                           <td align="center"><spring:message code="part.F3" text="default text"></spring:message></td>
                           <td align="center"><spring:message code="part.F4" text="default text"></spring:message></td>
                           <td align="center"><spring:message code="part.F5" text="default text"></spring:message></td>
                           <td align="center"><spring:message code="part.F6" text="default text"></spring:message></td>
                           <td align="center"><spring:message code="part.F7" text="default text"></spring:message></td>
                           <td align="center"><spring:message code="part.F8" text="default text"></spring:message></td>
                        </tr>
                      </thead>
              <tbody> 
                 <c:forEach var="row" items="${list}">
                        <tr bgcolor="#FFFFFF" onmouseover="this.style.backgroundColor='#E1FFFF'" onmouseout="this.style.backgroundColor='#FFFFFF'">
                          <td align="center"><input type="checkbox" onclick="chkEvent();" name="tracker_idx" value="${row.tracker_idx}" /></td>
                          <td align="center"><a href="${path}/trackerBoard/Tracker_view?tracker_idx=${row.tracker_idx}">${row.tracker_idx}</a></td>
                          <td align="center"><a href="${path}/trackerBoard/Tracker_view?tracker_idx=${row.tracker_idx}">${row.tracker_id}</a></td>
                          <td align="center"><a href="${path}/trackerBoard/Tracker_view?tracker_idx=${row.tracker_idx}">${row.t_group_name}</a></td>
                          <td align="center"><a href="${path}/trackerBoard/Tracker_view?tracker_idx=${row.tracker_idx}">${row.tracker_model}</a></td>
                          <td align="center"><a href="${path}/trackerBoard/Tracker_view?tracker_idx=${row.tracker_idx}">${row.tracker_capacity}</a></td>
                          <td align="center"><a href="${path}/trackerBoard/Tracker_view?tracker_idx=${row.tracker_idx}">${row.tracker_username}</a></td>
                          <td align="center"><a href="${path}/trackerBoard/Tracker_view?tracker_idx=${row.tracker_idx}">${row.tracker_phone}</a></td>
                          <td align="center">
                        <fmt:formatDate value="${row.tracker_reg_date}" pattern="yyyy-MM-dd HH:mm:ss"/>
                           </td>
                        </tr>
                         </c:forEach> 
                      </tbody>
                    </table>
                        <div>
                      <a onClick="location.href='${path}/trackerBoard/Tracker_Insert'" class="btn btn-primary"><spring:message code="part.D7" text="default text"></spring:message></a>            
                     <button class="btn btn-primary" onclick="deleteCheck()"><spring:message code="part.D8" text="default text"></spring:message></button>                    
        			</div>
        			</form>
        			<div class="page">
						${pageTag}
					</div>
                  </div>
                </div>
              </div>
                  </div><!-- Row -->
                </div>
              </div>
        <!-- /page content -->
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