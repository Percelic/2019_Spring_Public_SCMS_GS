<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/common.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<script type="text/javascript">
	function checkAll() {
		if($("#th_checkAll").is(':checked')){
			$("input[name=user_id]").prop("checked", true);
		}else{
			$("input[name=user_id]").prop("checked", false);
			
		}
	}
	
	function chkEvent() {
		var chkCnt = $("input[name=user_id]:checked").length;
		var totalCnt = $("input[name=user_id]").length;
		
		if(chkCnt == totalCnt) {
			$("#th_checkAll").prop("checked", true);
		} else {
			$("#th_checkAll").prop("checked", false);
		}
		
	}
	
	function deleteCheck() {
		var chkCnt = $("input[name=user_id]:checked").length;
		var msg = "<spring:message code='en.user_01' arguments='#'/>";
		
		if(confirm(msg.replace('#',chkCnt))) {
			if(chkCnt > 0) {
				document.getElementById('del').action = "delete";
				document.getElementById('del').method = "post";
				document.getElementById('del').submit();
			}
			else {
				alert("<spring:message code='en.user_001' text='def'/>");
			}
		}
		else {
		}
	}
	</script>
<input type="hidden" id="hidden_menu_id" value="user" />
		<!-- top navigation -->
        <div class="top_nav">
              <ul class="nav navbar-nav navbar-right">
				<li class="">
				</li>
				<li class="">
				 	<a  href="<c:url value="/userBoard/User_Board?lang=en" />">
				<img  src="${path}/image/USA1.png">
					</a>
				</li>
				<li class="">
					<a  href="<c:url value="/userBoard/User_Board?lang=ko" />">
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
                    <h2><spring:message code="User.a" text="defaul text"></spring:message></h2>
         
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                  <form id="del" onsubmit="return false;">
                      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <table id="datatable" class="table table-striped table-bordered">
                      <thead>
                         <tr bgcolor="#FFFFFF" onmouseover="this.style.backgroundColor='#E1FFFF'"               onmouseout="this.style.backgroundColor='#FFFFFF'">
                          <td align="center"><input type="checkbox" id="th_checkAll" onclick="checkAll();" /></td>
                           <td align="center" ><spring:message code="part.D1" text="default text"></spring:message></td>
                           <td align="center"><spring:message code="part.D2" text="default text"></spring:message></td>
                           <td align="center"><spring:message code="part.D3" text="default text"></spring:message></td>
                           <td align="center"><spring:message code="part.D6" text="default text"></spring:message></td>
                           <td align="center"><spring:message code="part.D4" text="default text"></spring:message></td>
                           <td align="center"><spring:message code="part.D5" text="default text"></spring:message></td>
                        </tr>
                      </thead>
                      <tbody>
					<c:forEach var="row" items="${list}">
                       <tr bgcolor="#FFFFFF" onmouseover="this.style.backgroundColor='#E1FFFF'"
                        onmouseout="this.style.backgroundColor='#FFFFFF'">
                         <td align="center" ><input type="checkbox" onclick="chkEvent();" name="user_id" value="${row.user_id}"/></td>
                         <td align="center" >
                         	<a href="${path}/userBoard/User_view?user_id=${row.user_id}">
	                         	<c:if test="${row.authority == 'ROLE_ADMIN'}">
	                         		<spring:message code="user.a13" text="default text"/>
	                         	</c:if>
	                         	<c:if test="${row.authority != 'ROLE_ADMIN'}">
	                         		<spring:message code="user.a14" text="default text"/>
	                         	</c:if>
                         	</a>
                         </td>
                         <td align="center" ><a href="${path}/userBoard/User_view?user_id=${row.user_id}">${row.user_id}</a></td>
                         <td align="center" ><a href="${path}/userBoard/User_view?user_id=${row.user_id}">${row.user_name}</a></td>
                         <td align="center" ><a href="${path}/userBoard/User_view?user_id=${row.user_id}">${row.group_name}</a></td>
                         <td align="center" ><a href="${path}/userBoard/User_view?user_id=${row.user_id}">${row.user_email}</a></td>
                         <td align="center"  ><a href="${path}/userBoard/User_view?user_id=${row.user_id}">${row.user_phone}</a></td>
                        </tr>
                   </c:forEach>
                      </tbody>
                    </table>
                     <div>
                      	<a onClick="location.href='${path}/userBoard/User_Insert'" class="btn btn-primary"><spring:message code="part.D7" text="default text"></spring:message></a>
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
	