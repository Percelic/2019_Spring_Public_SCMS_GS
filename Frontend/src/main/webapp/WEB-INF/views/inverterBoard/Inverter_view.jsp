<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/common.jsp"%>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
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
            <div class="page-title">
              <div class="title_left">
                <h3><spring:message code="inverter.view" text="defaul text"></spring:message></h3>
              </div>
            </div>
            
            <div class="clearfix"></div>
            <div class="row">
				<form name="form1"  method="post" data-parsley-validate class="form-horizontal form-label-left">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<input type="hidden" name="inverter_idx" value="${idx}"/>
						
						<div class="form-group col-md-12"> 
							<label for="inputEmail4"><spring:message code="inverter.b2" text="defaul text"></spring:message>&nbsp;*</label>
							<select class="form-control" name="t_group_id" id="t_group_id" readonly="readonly" disabled>
								<option value="${dto.t_group_id}">${dto.inverter_group_name} / (${dto.t_group_id})</option>
							</select>	
						</div> 
						
						<div class="form-group col-md-12"></div> 
						
						<div class="form-group col-md-6">
							<label for="inputEmail4"><spring:message code="inverter.b1" text="defaul text"></spring:message>&nbsp;*</label>
							<input  class="form-control" type="text" name="inverter_id" id="inverter_id" value="${dto.inverter_id}" readonly="readonly"/>
						</div> 
                      
						<div class="form-group col-md-6">
							<label for="inputEmail4"><spring:message code="inverter.b3" text="defaul text"/>&nbsp;*</label>
							<input  class="form-control col-md-7 col-xs-12" type="text" name="inverter_model" id="inverter_model" value="${dto.inverter_model}" placeholder="<spring:message code="inverter.b3" text="defaul text"/>" maxlength="12">
							<div class="check_font" id="model_check"></div>
						</div> 
						
						<div class="form-group col-md-12"></div>
                      
						<div class="form-group col-md-6">
							<label for="inputEmail4"><spring:message code="inverter.b4" text="defaul text"/>&nbsp;*</label>
							<input  class="form-control " type="text" name="inverter_capacity" id="inverter_capacity" value="${dto.inverter_capacity}" placeholder="<spring:message code="inverter.b4" text="defaul text"/>" maxlength="8">
                      		<div class="check_font" id="capacity_check"></div>
						</div> 
                      
						<div class="form-group col-md-6">
							<label for="inputEmail4"><spring:message code="inverter.b5" text="defaul text"/>&nbsp;*</label>
							<input  class="form-control" type="text" name="inverter_builder" id="inverter_builder" value="${dto.inverter_builder}" placeholder="<spring:message code="inverter.b5" text="defaul text"/>" maxlength="12">
							<div class="check_font" id="builder_check"></div>
						</div> 
						
						<div class="form-group col-md-12"></div>
                      
						<div class="form-group col-md-6">
							<label for="inputEmail4"><spring:message code="inverter.a6" text="defaul text"/>&nbsp;*</label>
							<input  class="form-control" type="text" name="inverter_manager" id="inverter_manager" value="${dto.inverter_manager}" placeholder="<spring:message code="inverter.a6" text="defaul text"/>" maxlength="12">
							<div class="check_font" id="manager_check"></div>
						</div> 
                      
						<div class="form-group col-md-6">
							<label for="inputEmail4"><spring:message code="inverter.b7" text="defaul text"/>&nbsp;*</label>
							<input  class="form-control" type="text" name="inverter_owner" id="inverter_owner" value="${dto.inverter_owner}" placeholder="<spring:message code="inverter.b7" text="defaul text"/>" maxlength="12">
							<div class="check_font" id="owner_check"></div>
						</div> 
						
						<div class="form-group col-md-12"></div>
          
                        <div class="form-group col-md-6">
	      					<label for="inputEmail4"><spring:message code="inverter.b8" text="defaul text"/>&nbsp;*</label>
	      					<input class="form-control" name="inverter_phone" id="inverter_phone" value="${dto.inverter_phone}"placeholder="01000000000" maxlength="11" >
							<div class="check_font" id="phone_check"></div>
    					</div>
    					
    					<div class="form-group col-md-6">
							<label for="inputEmail4"><spring:message code="inverter.a7" text="defaul text"></spring:message>&nbsp;*</label>
							<input name="inverter_username" maxlength="6" id="inverter_username" maxlength="12" class="form-control" required="required" placeholder="<spring:message code="inverter.a7" text="defaul text"/>" value="${dto.inverter_username }">
							<div class="check_font" id="username_check"></div>
						</div>
						
						<div class="form-group col-md-12"></div>
						
						<div class="form-group col-md-6" id="dateRangePicker">
							<label for="inputEmail4"><spring:message code="inverter.b9" text="defaul text"></spring:message></label>
							<span><fmt:formatDate  value="${dto.inverter_reg_date}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
						</div>
				
						<div class="form-group col-md-12"></div> 
                      
						<div class="form-group col-md-12 ">
                        	<label for="inputEmail4"><spring:message code="inverter.b11" text="defaul text"></spring:message></label>
							<textarea class="form-control" id="history" name="history"   rows="5" maxlength="10000">${dto.history}</textarea>
							<div class="check_font" id="history_check"></div>  
						</div>
						
						<div class="form-group col-md-12"></div> 
    				</div>
    				
                     <%--       <div class="form-group col-md-12">
                         <label for="inputEmail4">????????????</label>
                       <fmt:formatDate  value="${dto.inverter_completion_date}" pattern="yyyy-MM-dd HH:mm:ss"/>
                      </div> --%>
                                           
						<div class="ln_solid"></div>
							<div class="form-group">
							<div class="col-md-12">
								<input type="button" class="btn btn-primary" value="<spring:message code="user.a18" text="defaul text"/>" id="btnUpdate"/>
								<a onClick="location.href='${path}/inverterBoard/Inverter_Board'" class="btn btn-primary"><spring:message code="user.a19" text="defaul text"/></a>
							</div>
						</div>
					</form>
                  </div>
                </div>
              </div>
              
        <!-- /?????? -->
        <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	   <script type="text/javascript">
	   $(document).ready(function () {
			  $("#btnUpdate").click(function(){
				  var t_group_id = $("#t_group_id").val();
				  var inverter_model = $("#inverter_model").val();
				  var inverter_capacity = $("#inverter_capacity").val();
				  var inverter_phone = $("#inverter_phone").val();
				  var inverter_builder = $("#inverter_builder").val();
				  var inverter_manager = $("#inverter_manager").val();
				  var inverter_owner = $("#inverter_owner").val();
				  var inverter_username = $("#inverter_username").val();
				  
				  if(inverter_model=="" || !regType1.test(inverter_model)){
					  alert("<spring:message code='en.inverter_03' text='def'/>");
					  $("#inverter_model").focus();
					 return false;					 
				  }else if(inverter_capacity == "" || !regType2.test(inverter_capacity)){
					  alert("<spring:message code='en.inverter_04' text='def'/>");
					  $("#inverter_capacity").focus();
					 return false;
					 
				  }else if(inverter_builder == "" || !nameJ.test(inverter_builder)){
					  alert("<spring:message code='en.inverter_05' text='def'/>");
					  $("#inverter_builder").focus();
					  return false;
					  
				  }else if(inverter_manager == "" || !nameJ.test(inverter_manager)){
					  alert("<spring:message code='en.inverter_06' text='def'/>");
					  $("#inverter_manager").focus();
					  return false;
					  
				  }else if(inverter_owner == "" || !nameJ.test(inverter_owner)){
					  alert("<spring:message code='en.inverter_07' text='def'/>");
					  $("#inverter_owner").focus();
					  return false;
				  }else if(inverter_phone == "" || !numberJ.test(inverter_phone)){ 
					  alert("<spring:message code='en.inverter_08' text='def'/>");
					  $("#inverter_phone").focus();
					  return false;
						  
				  }else if(inverter_username == "" || !nameJ.test(inverter_username)){
					  alert("<spring:message code='en.inverter_09' text='def'/>");
					  $("#inverter_username").focus();
					  return false;
					  
				  }
				  
				  //?????? ?????? ??????
				  if(confirm("<spring:message code='en.modify' text='def'/>")){
					  alert("<spring:message code='en.modify_success' text='def'/>")
					  document.form1.action="${path}/inverterBoard/update";
					  document.form1.submit();
				  }else
					  return false;
			  });
			
		});
		function execPostCode() {
	 		new daum.Postcode({
	 			oncomplete: function(data) { 
	                // ???????????? ???????????? ????????? ??????????????? ????????? ????????? ???????????? ??????.
	                // ????????? ????????? ?????? ????????? ?????? ????????? ????????????.
	                // ???????????? ????????? ?????? ?????? ????????? ??????('')?????? ????????????, ?????? ???????????? ?????? ??????.
	                var fullRoadAddr = data.roadAddress; // ????????? ?????? ??????
	                var extraRoadAddr = ''; // ????????? ????????? ?????? ??????
	                // ??????????????? ?????? ?????? ????????????. (???????????? ??????)
	                // ???????????? ?????? ????????? ????????? "???/???/???"??? ?????????.
	                if(data.bname !== '' && /[???|???|???]$/g.test(data.bname)){
	                    extraRoadAddr += data.bname;
	                }
	                // ???????????? ??????, ??????????????? ?????? ????????????.
	                if(data.buildingName !== '' && data.apartment === 'Y'){
	                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                }
	                // ?????????, ?????? ????????? ????????? ?????? ??????, ???????????? ????????? ?????? ???????????? ?????????.
	                if(extraRoadAddr !== ''){
	                    extraRoadAddr = ' (' + extraRoadAddr + ')';
	                }
	                // ?????????, ?????? ????????? ????????? ?????? ?????? ????????? ????????? ????????????.
	                if(fullRoadAddr !== ''){
	                    fullRoadAddr += extraRoadAddr;
	                }
	                // ??????????????? ?????? ????????? ?????? ????????? ?????????. 
	                console.log(data.zonecode);
	                console.log(fullRoadAddr);
	                
	                
	                $("[name=signUpUserPostNo]").val(data.zonecode);
	                $("[name=user_addr]").val(fullRoadAddr);
	                
	                /* document.getElementById('signUpUserPostNo').value = data.zonecode; //5?????? ??????????????? ??????
	                document.getElementById('signUpUserCompanyAddress').value = fullRoadAddr;
	                document.getElementById('signUpUserCompanyAddressDetail').value = data.jibunAddress; */
	            }
	 		}).open();
	 	}
	 </script>
        <!-- footer content -->
	
	<script type="text/javascript">
	//?????? ?????? ?????? ?????????
	var empJ = /\s/g;
	//????????? ?????????
	var idJ = /^[0-9]{1,3}$/;
	// ???????????? ?????????
	var pwJ = /^[A-Za-z0-9]{4,12}$/; 
	// ?????? ?????????
	var nameJ = /^[???-???a-zA-Z]{2,12}$/;
	// ???????????? ?????????
	var GroupNameJ = /^[???-???]{2,9}$/;
	// ????????? ?????? ?????????
	var mailJ = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	// ????????? ?????? ?????????
		var phoneJ = /^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/;
	// IP ?????????
	var ipJ = /^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$/;
	//????????? 
	var numberJ = /^[0-9]{1,11}$/;
	// ?????? ????????? ?????? ???
	var regType2 = /^[-]?\d+(?:[.]\d+)?$/;
	
	// ?????? ????????? ?????? ???
	//var regType1 = /^[A-Za-z0-9+]*$/;
	
	// 19-07-30 KGW Modified --> 19-0918 KGW Modified
// 	var regType1 = /^[A-Za-z0-9]{4,12}$/;
	var regType1 = /^[A-Za-z0-9-_]{1,20}$/;
	
	//????????? ???????????? ???????????? ???????????? ????????? ??????
	$("#inverter_phone").on("propertychange change keyup paste input",function() {
	if (numberJ.test($(this).val())) {
			$("#phone_check").text('');
	} else {
		$('#phone_check').text('<spring:message code="en.07" text="defaul text"></spring:message>');
		$('#phone_check').css('color', 'red');
	}
});	
	//????????? ???????????? ???????????? ???????????? ????????? ??????
	$("#inverter_builder").on("propertychange change keyup paste input",function() {
	if (nameJ.test($(this).val())) {
			$("#builder_check").text('');
	} else {
		$('#builder_check').text('<spring:message code="en.15" text="defaul text"></spring:message>');
		$('#builder_check').css('color', 'red');
		
	}
});	
	//????????? ???????????? ???????????? ???????????? ????????? ??????
	$("#inverter_manager").on("propertychange change keyup paste input",function() {
	if (nameJ.test($(this).val())) {
			$("#manager_check").text('');
	} else { 
		$('#manager_check').text('<spring:message code="en.17" text="defaul text"></spring:message>');
		$('#manager_check').css('color', 'red');
	}
});	
	//????????? ???????????? ???????????? ???????????? ????????? ??????
	$("#inverter_owner").on("propertychange change keyup paste input",function() {
	if (nameJ.test($(this).val())) {
			$("#owner_check").text('');
	} else {
		$('#owner_check').text('<spring:message code="en.16" text="defaul text"></spring:message>');
		$('#owner_check').css('color', 'red');
	}
});	
	//?????? ???????????? ????????? ??????
	$("#inverter_capacity").on("propertychange change keyup paste input",function() {
	if (regType2.test($(this).val())) {
			$("#capacity_check").text('');
	} else {
		$('#capacity_check').text('<spring:message code="en.222" text="defaul text"></spring:message>');
		$('#capacity_check').css('color', 'red');
	} 
});	
//??????
$("#inverter_model").on("propertychange change keyup paste input",function() {
	if(regType1.test($(this).val())){
		$("#model_check").text('');
	} else {
		$('#model_check').text('<spring:message code="en.21" text="defaul text"></spring:message>');
		$('#model_check').css('color', 'red');
	}
});
$('#inverter_username').on("propertychange change keyup paste input",function() {
	if(nameJ.test($(this).val())){
		$("#username_check").text('');
	} else {
		$('#username_check').text('<spring:message code="en.18" text="defaul text"></spring:message>');
		$('#username_check').css('color', 'red');
	}
});
	</script>