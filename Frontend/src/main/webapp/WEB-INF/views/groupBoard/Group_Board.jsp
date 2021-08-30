<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/common.jsp"%>
<script type="text/javascript">
	function checkAll() {
		if($("#th_checkAll").is(':checked')){
			$("input[name=t_group_id]").prop("checked", true);
		}else{
			$("input[name=t_group_id]").prop("checked", false);
			
		}
	} 
	
	function chkEvent() {
		var chkCnt = $("input[name=t_group_id]:checked").length;
		var totalCnt = $("input[name=t_group_id]").length;
		
		if(chkCnt == totalCnt) {
			$("#th_checkAll").prop("checked", true);
		} else {
			$("#th_checkAll").prop("checked", false);
		}
		
	}
	
	function deleteCheck() {
		var chkCnt = $("input[name=t_group_id]:checked").length;
		var msg = "<spring:message code='en.group_01' arguments='#'/>";
		 
		if(confirm(msg.replace('#',chkCnt))) {
			if(chkCnt > 0) {
				document.getElementById('del').action = "delete";
				document.getElementById('del').method = "post";
				document.getElementById('del').submit();
			}
			else {
				alert("<spring:message code='en.group_001' text='def'/>");
			}
		}
		else {
		}
	}
</script>
<input type="hidden" id="hidden_menu_id" value="group" />
        <div class="top_nav">
        
              <ul class="nav navbar-nav navbar-right">
              <li class="">
              </li>
              
                   <li class="">
                	<a  href="<c:url value="/groupBoard/Group_Board?lang=en" />">
                	<img  src="${path}/image/USA1.png">
                	</a>
                </li>
                <li class="">
                	<a  href="<c:url value="/groupBoard/Group_Board?lang=ko" />">
                	<img  src="${path}/image/korean1.png">
                	</a>
                </li>
              </ul> 

        </div>
        
        <!-- page content -->
        <div class="right_col" role="main">
          <div class="">
   

            <div class="clearfix"></div>

            <div class="row">
              <div style="width:1645px; margin-left:5px; margin-top:50px; ">
                <div class="x_panel">
                  <div class="x_title">
                  <h2><spring:message code="Group.AA" text="defualt text"></spring:message></h2>
         			<ul class="nav navbar-nav navbar-right">
         			</ul>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                  <form id="del" onsubmit="return false;">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <table id="datatable" class="table table-striped table-bordered">
                    <thead>
                         <tr bgcolor="#FFFFFF" onmouseover="this.style.backgroundColor='#E1FFFF'"       onmouseout="this.style.backgroundColor='#FFFFFF'">
                           <td align="center" style="margin-left: 20px;"><input type="checkbox" id="th_checkAll" onclick="checkAll();" /></td>
                                                                                                       
                          <td align="center"  ><spring:message code="part.E1" text="default text"></spring:message></td>
                          <td align="center" ><spring:message code="part.E2" text="default text"></spring:message> </td>
                          <td align="center" ><spring:message code="part.E3" text="default text"></spring:message> </td>
                          <td align="center" ><spring:message code="part.E4" text="default text"></spring:message></td>
                          <td align="center" ><spring:message code="part.E5" text="default text"></spring:message> </td>
                          <td align="center" ><spring:message code="part.E6" text="default text"></spring:message> </td>
                          <td align="center" ><spring:message code="part.E7" text="default text"></spring:message> </td>
                          <td align="center" ><spring:message code="part.E8" text="default text"></spring:message> </td>
                          <td align="center" ><spring:message code="part.E9" text="default text"></spring:message> </td>
                          <td align="center" ><spring:message code="part.E10" text="default text"></spring:message></td>
                        </tr>
                      </thead>
                    <tbody>
                      <c:forEach var="row" items="${list}">
                         <tr bgcolor="#FFFFFF" onmouseover="this.style.backgroundColor='#E1FFFF'"
                        onmouseout="this.style.backgroundColor='#FFFFFF'">
                          <td align="center"><input type="checkbox" onclick="chkEvent();" class="selectDelete_btn" name="t_group_id" value="${row.t_group_id}"></td>
                          <td align="center"><a href="${path}/groupBoard/Group_view?t_group_id=${row.t_group_id}">${row.t_group_id}</a></td>
                          <td align="center"><a href="${path}/groupBoard/Group_view?t_group_id=${row.t_group_id}">${row.t_group_name}</a></td>
                          <td align="center"><a href="${path}/groupBoard/Group_view?t_group_id=${row.t_group_id}">${row.t_group_addr}</a></td>
                          <td align="center"><a href="${path}/groupBoard/Group_view?t_group_id=${row.t_group_id}">${row.tracker_cnt}</a></td>
                          <td align="center"><a href="${path}/groupBoard/Group_view?t_group_id=${row.t_group_id}">${row.inverter_cnt}</a></td>
                          <td align="center"><a href="${path}/groupBoard/Group_view?t_group_id=${row.t_group_id}">${row.t_group_manager}</a></td>
                          <td align="center"><a href="${path}/groupBoard/Group_view?t_group_id=${row.t_group_id}">${row.t_group_builder}</a></td>
                          <td align="center"><a href="${path}/groupBoard/Group_view?t_group_id=${row.t_group_id}">${row.t_group_username}</a></td>
                          <td align="center"><a href="${path}/groupBoard/Group_view?t_group_id=${row.t_group_id}">${row.t_group_phone}</a></td>
                          <td align="center">
                          <fmt:formatDate value="${row.t_group_reg_date}" pattern="yyyy-MM-dd HH:mm:ss"/>
                          </td>
                        </tr>
                        </c:forEach>
                      </tbody>
                    </table>
                        <div>
                        <a onClick="location.href='${path}/groupBoard/Group_Insert'" class="btn btn-primary"><spring:message code="part.D7" text="defaul text"></spring:message></a>
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
