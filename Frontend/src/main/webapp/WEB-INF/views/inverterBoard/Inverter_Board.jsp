<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/common.jsp"%>
<script type="text/javascript">
		function checkAll(){
		      if( $("#th_checkAll").is(':checked') ){
		        $("input[name=inverter_idx]").prop("checked", true);
		      }else{
		        $("input[name=inverter_idx]").prop("checked", false);
		      }
		}
		
		function chkEvent() {
			var chkCnt = $("input[name=inverter_idx]:checked").length;
			var totalCnt = $("input[name=inverter_idx]").length;
			
			if(chkCnt == totalCnt) {
				$("#th_checkAll").prop("checked", true);
			} else {
				$("#th_checkAll").prop("checked", false);
			}
		}
		
		function deleteCheck() {
			var chkCnt = $("input[name=inverter_idx]:checked").length;
			var msg =  "<spring:message code='en.inverter_01' arguments='#'/>";
			 
			if(confirm(msg.replace('#',chkCnt))) {
				if(chkCnt > 0) {
					document.getElementById('del').action = "delete";
					document.getElementById('del').method = "post";
					document.getElementById('del').submit();
				}
				else {
					alert("<spring:message code='en.inverter_001' text='def'/>");
				}
			}
			else {
			}
		}
		
		
</script>
<input type="hidden" id="hidden_menu_id" value="inverter" />
<!-- top navigation -->
        <div class="top_nav">
        
              <ul class="nav navbar-nav navbar-right">
              <li class="">
              </li>
              
                    <li class="">
                	<a  href="<c:url value="/inverterBoard/Inverter_Board?lang=en" />">
                	<img  src="${path}/image/USA1.png">
                	</a>
                </li>
                <li class="">
                	<a  href="<c:url value="/inverterBoard/Inverter_Board?lang=ko" />">
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
                  <h2><spring:message code="Inverter.a" text="default text"></spring:message></h2>
         
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                  <form id="del" onsubmit="return false;">
                      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <table id="datatable" class="table table-striped table-bordered">
                     <thead>
                        <tr bgcolor="#FFFFFF" onmouseover="this.style.backgroundColor='#E1FFFF'"
                        onmouseout="this.style.backgroundColor='#FFFFFF'">
                           <td align="center"><input type="checkbox" id="th_checkAll" onclick="checkAll();"/></td>
                           <td align="center" ><spring:message code="part.G8" text="default text"></spring:message></td>
                           <td align="center" ><spring:message code="part.G1" text="default text"></spring:message></td>
                           <td align="center" ><spring:message code="part.G2" text="default text"></spring:message></td>
                           <td align="center" ><spring:message code="part.G3" text="default text"></spring:message></td>
                           <td align="center" ><spring:message code="part.G4" text="default text"></spring:message></td>
                           <td align="center" ><spring:message code="part.G5" text="default text"></spring:message></td>
                           <td align="center" ><spring:message code="part.G6" text="default text"></spring:message></td>
                           <td align="center" ><spring:message code="part.G7" text="default text"></spring:message></td>
                        </tr> 
                      </thead>
              <tbody> 
                      <c:forEach var="row" items="${list}">
                        <tr bgcolor="#FFFFFF" onmouseover="this.style.backgroundColor='#E1FFFF'"            onmouseout="this.style.backgroundColor='#FFFFFF'">
                          <td align="center"><input type="checkbox" onclick="chkEvent();" name="inverter_idx" value="${row.inverter_idx}" /></td>
                          <td align="center"><a href="${path}/inverterBoard/Inverter_view?inverter_idx=${row.inverter_idx}">${row.inverter_idx}</a></td>
                          <td align="center"><a href="${path}/inverterBoard/Inverter_view?inverter_idx=${row.inverter_idx}">${row.inverter_id}</a></td>
                          <td align="center"><a href="${path}/inverterBoard/Inverter_view?inverter_idx=${row.inverter_idx}">${row.inverter_group_name}</a></td>
                          <td align="center"><a href="${path}/inverterBoard/Inverter_view?inverter_idx=${row.inverter_idx}">${row.inverter_model}</a></td>
                          <td align="center"><a href="${path}/inverterBoard/Inverter_view?inverter_idx=${row.inverter_idx}">${row.inverter_capacity}</a></td>
                          <td align="center"><a href="${path}/inverterBoard/Inverter_view?inverter_idx=${row.inverter_idx}">${row.inverter_username}</a></td>
                          <td align="center"><a href="${path}/inverterBoard/Inverter_view?inverter_idx=${row.inverter_idx}">${row.inverter_phone}</a></td>
                          <td align="center">
                           <fmt:formatDate  value="${row.inverter_reg_date}" pattern="yyyy-MM-dd HH:mm:ss"/>
                           </td>
                        </tr>
                        </c:forEach>
                      </tbody>
                    </table>
                      <div>
                       <a onClick="location.href='${path}/inverterBoard/Inverter_insert'" class="btn btn-primary"><spring:message code="part.D7" text="default text"></spring:message></a>            
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
              
              <!-- 체크박스 스크립트 -->
		<script type="text/javascript">
		function checkAll(){
		      if( $("#th_checkAll").is(':checked') ){
		        $("input[name=inverter_idx]").prop("checked", true);
		      }else{
		        $("input[name=inverter_idx]").prop("checked", false);
		      }
		}
		
		function chkEvent() {
			var chkCnt = $("input[name=inverter_idx]:checked").length;
			var totalCnt = $("input[name=inverter_idx]").length;
			
			if(chkCnt == totalCnt) {
				$("#th_checkAll").prop("checked", true);
			} else {
				$("#th_checkAll").prop("checked", false);
			}
			
		}
		
		</script>
