<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/common.jsp"%>
	 <script src="${res}/inverter/js/inverterDetail.js"></script>
	<script type="text/javascript">
	$(document).ready(function () {
		//상품 등록 유효성 검사
		  $("#addBtn").click(function(){
			  var inverter_id = $("#inverter_id").val();
			  var inverter_model = $("#inverter_model").val();
			  var inverter_capacity = $("#inverter_capacity").val();
			  var inverter_phone = $("#inverter_phone").val();
			  var inverter_builder = $("#inverter_builder").val();
			  var inverter_manager = $("#inverter_manager").val();
			  var inverter_owner = $("#inverter_owner").val();
			  var inverter_username = $("#inverter_username").val();
			  
			  if(inverter_id=="" || !idJ.test(inverter_id)) {
				  alert("<spring:message code='en.inverter_02' text='def'/>");
				  $("#inverter_id").focus();
				  return false;
			  }else if(inverter_model=="" || !regType1.test(inverter_model)){
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
			  //확인 대화 상자
			  if(confirm("<spring:message code='en.register' text='def'/>")){
				  alert("<spring:message code='en.012' text='def'/>");
				  document.form1.action="${path}/inverterBoard/insert"; 
				  document.form1.submit();
			  }
		  });
	});
</script>
		 <!-- top navigation -->
        <input type="hidden" id="hidden_menu_id" value="inverter" />
        <div class="top_nav">
              <ul class="nav navbar-nav navbar-right">
              <li class="">
              </li>
                    <li class="">
                	<a  href="<c:url value="/inverterBoard/Inverter_insert?lang=en" />">
                	<img  src="${path}/image/USA1.png">
                	</a>
                </li>
                <li class="">
                	<a  href="<c:url value="/inverterBoard/Inverter_insert?lang=ko" />">
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
                <h3><spring:message code="inverter.insert" text="defaul text"/></h3>
              </div>
              
              <div class="clearfix"></div>

                 <!-- title- right -->
            </div>
            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
            
                  
                  <form name="form1" id="form1"  method="post" data-parsley-validate class="form-horizontal form-label-left">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <div class="form-group col-md-12">
      						<label for="inputEmail4"><spring:message code="inverter.a1" text="defaul text"/>&nbsp;*</label>
      				    	<select class="form-control"  id="t_group_id"  name="t_group_id" >
      				    		<c:forEach var="row" items="${inverter_group_choice}" varStatus="i">
									<option value="${row.t_group_id}">${row.t_group_name} / (${row.t_group_id})</option>
								</c:forEach>
							</select>  
    					</div>
    					
    					<div class="form-group col-md-12"></div> 
    					<div class="form-group col-md-12"></div> 
    						
    					<div class="form-group col-md-6">
      						<label  for="inputEmail4"><spring:message code="inverter.a3" text="defaul text"/>&nbsp;*</label>
      						<input name="inverter_id" id="inverter_id" required="required" class="form-control"  placeholder="<spring:message code="inverter.a3" text="defaul text"/>" maxlength="3">
	    					<div class="check_font" id="id_check"></div>
	    				</div>
                		
                       <div class="form-group col-md-6">
      				 		<label for="inputEmail4"><spring:message code="inverter.a5" text="defaul text"/>&nbsp;*</label>
      						<input name="inverter_model" id="inverter_model" class="form-control" required="required"  placeholder="<spring:message code="inverter.a5" text="defaul text"/>" maxlength="12">
    						<div class="check_font" id="model_check"></div>
    					</div>
    						
    					<div class="form-group col-md-12"></div>
    					<div class="form-group col-md-12"></div>
    						
                       <div class="form-group col-md-6">
      					<label for="inputEmail4"><spring:message code="inverter.a4" text="defaul text"/>&nbsp;*</label>
      					<input name="inverter_capacity" id="inverter_capacity" class="form-control" required="required" placeholder="<spring:message code="inverter.a4" text="defaul text"/>" maxlength="6">
    						<div class="check_font" id="capacity_check"></div>
    						</div>
    						
  	 					<div class="form-group col-md-6">
      					<label for="inputEmail4"><spring:message code="inverter.a9" text="defaul text"/>&nbsp;*</label>
      					<input name="inverter_builder" id="inverter_builder" class="form-control" required="required" placeholder="<spring:message code="inverter.a9" text="defaul text"/>"   type="text" maxlength="12">
    						<div class="check_font" id="builder_check"></div>
    					</div>
    					
    					<div class="form-group col-md-12"></div>
    					<div class="form-group col-md-12"></div>	
    						    						
    				 <div class="form-group col-md-6">
      					<label for="inputEmail4"><spring:message code="inverter.a6" text="defaul text"/>&nbsp;*</label>
      					<input name="inverter_manager" id="inverter_manager" class="form-control" required="required" placeholder="<spring:message code="inverter.a6" text="defaul text"/>"   type="text" maxlength="12">
    							<div class="check_font" id="manager_check"></div>
    						</div>
    							
    						
    				<div class="form-group col-md-6">
      					<label for="inputEmail4"><spring:message code="inverter.a8" text="defaul text"/>&nbsp;*</label>
      					<input name="inverter_owner" id="inverter_owner" class="form-control"  required="required" placeholder="<spring:message code="inverter.a8" text="defaul text"/>"  type="text" maxlength="12">
    							<div class="check_font" id="owner_check"></div>
    						</div>
    							<div class="form-group col-md-12"></div>
    							<div class="form-group col-md-12"></div>
    						
                        <div class="form-group col-md-6">
      					<label for="inputEmail4"><spring:message code="inverter.a10" text="defaul text"/>&nbsp;*</label>
      					<input name="inverter_phone" id="inverter_phone" class="form-control" required="required" placeholder="01000000000" type="text" maxlength="11">
    							<div class="check_font" id="phone_check"></div>
    						</div>
    						
    					<div class="form-group col-md-6">
      					<label for="inputEmail4"><spring:message code="inverter.a7" text="defaul text"/>&nbsp;*</label>
      					<input name="inverter_username" id="inverter_username" class="form-control" required="required" placeholder="<spring:message code="inverter.a7" text="defaul text"/>" type="text" maxlength="12">
    							<div class="check_font" id="username_check"></div>
    						</div>	
    						<div class="form-group col-md-12"></div>
    							<div class="form-group col-md-12"></div>
    						
                        <div class="form-group col-md-12 ">
                        <label for="inputEmail4"><spring:message code="inverter.a11" text="defaul text"/></label>
                       <textarea class="form-control col-sm-5" id="history" name="history" rows="5" maxlength="10000"></textarea>
                       <div class="check_font" id="history_check"></div> 
                      </div>
                      
                      <div class="form-group col-md-12"></div> 
                      <div class="form-group col-md-12"></div> 
            
            
			        <div class="ln_solid"></div>
                      <div class="form-group">
                        <div class="col-md-12">
                        	<input type="button" id="addBtn" value="<spring:message code="inverter.a12" text="defaul text"/>" class="btn btn-primary" />
							<!-- <button class="btn btn-primary" id="addBtn" value="확인" disabled="disabled"></button> -->
                            <a onClick="location.href='${path}/inverterBoard/Inverter_Board'" class="btn btn-primary"><spring:message code="inverter.a13" text="defaul text"/></a> 
                        </div>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
            </div>
	 
	<script type="text/javascript">
	//모든 공백 체크 정규식
	var empJ = /\s/g;
	//아이디 정규식
	var idJ = /^[1-9?]([0-9]{1,3})*$/;
	// 비밀번호 정규식
	var pwJ = /^[A-Za-z0-9]{4,16}$/; 
	// 이름 정규식
	var nameJ = /^[가-힣a-zA-Z]{2,12}$/;
	// 군집이름 정규식
	var GroupNameJ = /^[가-힣a-zA-Z-_]{2,30}$/;
	// 이메일 검사 정규식
	var mailJ = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	// 휴대폰 번호 정규식
	var phoneJ =  /^\d{3}-\d{3,4}-\d{4}$/;
	// IP 정규식
	var ipJ = /^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$/;
	// 숫자만 정규식
	var numberJ =  /^[0-9]{1,11}$/;
	var numberJ2 = /^\d+(?:[.]?[\d]?[\d])?$/;
	
	var regType1 = /^[A-Za-z0-9-_]{1,20}$/;
	
	//발전량 확인 정규식
	var regType2 = /^[-]?\d+(?:[.]\d+)?$/;
	
	
	$("#inverter_id").on("propertychange change keyup paste input",function() {
		// id = "id_reg" / name = "userId"
		var inverter_id = $('#inverter_id').val();
		$.ajax({
			url : '${path}/inverterBoard/idCheck.do?inverter_id='+ inverter_id,
			type : 'post',
			success : function(data) {	      
				
				if (data == 1) {
						// 1 : 아이디가 중복되는 문구
						$("#id_check").text("<spring:message code="en.01" text="defaul text"/>");
						$("#id_check").css("color", "red");
					} else {
						
						if(idJ.test(inverter_id)){
							// 0 : 아이디 길이 / 문자열 검사
							$("#id_check").text("");
				
						} else if(inverter_id == ""){
							
							$('#id_check').text("<spring:message code="en.005" text="defaul text"/>");
							$('#id_check').css('color', 'red');
							
						} else {
							
							$('#id_check').text("<spring:message code="en.005" text="defaul text"/>");
							$('#id_check').css('color', 'red');
						}
						
					}
				}, error : function() { 
				}
			});
		});
	
	//아이디 중복 검사 끝.
	
	
	//연락처에 특수문자 들어가지 않도록 설정
	$("#inverter_phone").on("propertychange change keyup paste input",function() {
	if (numberJ.test($(this).val())) {
			$("#phone_check").text('');
	} else {
		$('#phone_check').text('<spring:message code="en.07" text="defaul text"/>');
		$('#phone_check').css('color', 'red');
	}
});	
	
	//트래커 시공사에 특수문자 들어가지 않도록 설정
	$("#inverter_username").on("propertychange change keyup paste input",function() {
	if (nameJ.test($(this).val())) {
			$("#username_check").text('');
	} else {
		$('#username_check').text('<spring:message code="en.18" text="defaul text"/>');
		$('#username_check').css('color', 'red');
	}
});		
	
	//시공사에 특수문자 들어가지 않도록 설정
	$("#inverter_builder").on("propertychange change keyup paste input",function() {
	if (nameJ.test($(this).val())) {
			$("#builder_check").text('');
	} else {
		$('#builder_check').text('<spring:message code="en.15" text="defaul text"/>');
		$('#builder_check').css('color', 'red');
		
	}
});	
	//담당자 시공사에 특수문자 들어가지 않도록 설정
	$("#inverter_manager").on("propertychange change keyup paste input",function() {
	if (nameJ.test($(this).val())) {
			$("#manager_check").text('');
	} else { 
		$('#manager_check').text('<spring:message code="en.17" text="defaul text"/>');
		$('#manager_check').css('color', 'red');
	}
});	
	//소유주 시공사에 특수문자 들어가지 않도록 설정
	$("#inverter_owner").on("propertychange change keyup paste input",function() {
	if (nameJ.test($(this).val())) {
			$("#owner_check").text('');
	} else {
		$('#owner_check').text('<spring:message code="en.16" text="defaul text"/>');
		$('#owner_check').css('color', 'red');
	}
});	
	//핸드폰 번호 시공사에 특수문자 들어가지 않도록 설정
	$("#inverter_capacity").on("propertychange change keyup paste input",function() {
	if (numberJ2.test($(this).val())) {
			$("#capacity_check").text('');
	} else {
		$('#capacity_check').text('<spring:message code="en.022" text="defaul text"/>');
		$('#capacity_check').css('color', 'red');
	}
});	
	
	$('#inverter_model').on("propertychange change keyup paste input",function(){
		if(regType1.test($(this).val())){
			$("#model_check").text('');
		} else {
			$('#model_check').text('<spring:message code="en.21" text="defaul text"/>');
			$('#model_check').css('color', 'red');
		}
	});
	 
	</script>
        <!-- footer content -->
        <jsp:include page="/WEB-INF/views/popup/groupModel.jsp">
			<jsp:param name="formId" value="inputForm"/>
		</jsp:include>
