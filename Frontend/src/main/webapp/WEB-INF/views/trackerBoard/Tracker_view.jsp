<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/common.jsp"%>
        <!-- top navigation -->
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

<input type="hidden" id="hidden_menu_id" value="tracker" />
        <!-- page content -->
        <div class="right_col" role="main">
          <div class="">
          <div class="page-title">
              <div class="title_left">
                    <h3><spring:message code="tracker.view" text="defaul text"></spring:message></h3>
              </div>
				<div class="clearfix"></div>
				
                  </div>
            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                  <form name="form1"  method="post" data-parsley-validate class="form-horizontal form-label-left">
                          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                     	  <input type="hidden" name="tracker_idx" id="tracker_idx" value="${dto.tracker_idx}"/>  
                      
                       <div class="form-group col-md-12">
      					<label for="inputEmail4"><spring:message code="tracker.a1" text="defaul text"/>&nbsp;*</label>
      					<select class="form-control" name="t_group_id" id="t_group_id" readonly="readonly" disabled>
							<option value="${dto.t_group_id}">${dto.t_group_name} / (${dto.t_group_id})</option>
						</select>
							
					   </div>
                      
                      <div class="form-group col-md-12"></div> 
                      
                  <div class="form-group col-md-6">
						 <label for="inputEmail4"><spring:message code="tracker.a3" text="defaul text"/>&nbsp;*</label>	
                          <input type="text" name="tracker_id" id="tracker_id" class="form-control" required="required" class="form-control"
                          	value="${dto.tracker_id}" readonly="readonly"
                          	placeholder="<spring:message code="tracker.a3" text="defaul text"/>" maxlength="6" />
                      </div>
    						
    						
                 	 <div class="form-group col-md-6">
                        <label for="inputEmail4"><spring:message code="tracker.a4" text="defaul text"/>&nbsp;*</label>
                          <input type="text" name="tracker_model" id="tracker_model"  class="form-control" required="required" class="form-control"
                          	value="${dto.tracker_model}" 
                          	placeholder="<spring:message code="tracker.a4" text="defaul text"/>" maxlength="20" /> <!-- readonly = readonly 봉인 구문 -->
                      <div class="check_font" id="model_check"></div>
                      </div>
                      
                      <div class="form-group col-md-12"></div>
    						
    			  <div class="form-group col-md-6">
                         <label for="inputEmail4"><spring:message code="tracker.a5" text="defaul text"/>&nbsp;*</label>
                          <input  class="form-control" type="text"  class="form-control"  name="tracker_capacity" id="tracker_capacity"
                          value="${dto.tracker_capacity}" placeholder="<spring:message code="tracker.a5" text="defaul text"/>" maxlength="6" >
 					 <div class="check_font" id="capacity_check"></div>
                  </div>
    						
               <div class="form-group col-md-6">
                    <label for="inputEmail4"><spring:message code="tracker.a10" text="defaul text"/>&nbsp;*</label>
                    <input class="form-control" type="text"  class="form-control" name="tracker_builder" id="tracker_builder"
                      value="${dto.tracker_builder}" placeholder="<spring:message code="tracker.a10" text="defaul text"/>" maxlength="12">
                    <div class="check_font" id="builder_check"></div>
               </div>
               
               <div class="form-group col-md-12"></div>
                 		
               <div class="form-group col-md-6">
                  <label for="inputEmail4"><spring:message code="tracker.a6" text="defaul text"/>&nbsp;*</label>
                    <input class="form-control" type="text" name="tracker_manager" id="tracker_manager"
                    value="${dto.tracker_manager}" placeholder="<spring:message code="tracker.a6" text="defaul text"/>" maxlength="12">
                	<div class="check_font" id="manager_check"></div>
                </div>
		   
		       <div class="form-group col-md-6">
	               <label for="inputEmail4"><spring:message code="tracker.a9" text="defaul text"/>&nbsp;*</label>
	                <input class="form-control" type="text"  name="tracker_owner" id="tracker_owner" 
	            	   value="${dto.tracker_owner}" placeholder="<spring:message code="tracker.a9" text="defaul text"/>" maxlength="12">
	           		<div class="check_font" id="owner_check"></div>
	           </div>
	           
	           <div class="form-group col-md-12"></div>
    						
    					
				<div class="form-group col-md-6">
 					<label for="inputEmail4"><spring:message code="tracker.a7" text="defaul text"/>&nbsp;*</label>
 					<input name="tracker_phone" id="tracker_phone" class="form-control" 
 					value="${dto.tracker_phone}" placeholder="01000000000" maxlength="11">
 					<div class="check_font" id="phone_check"></div>
				</div>
    						
				<div class="form-group col-md-6">
 					<label for="inputEmail4"><spring:message code="tracker.a8" text="defaul text"/>&nbsp;*</label>
 					<input name="tracker_username" id="tracker_username" class="form-control" 
 					value="${dto.tracker_username}" placeholder="<spring:message code="tracker.a8" text="defaul text"/>" maxlength="12">
					<div class="check_font" id="username_check"></div>
				</div>	
                      
                <div class="form-group col-md-12"></div> 
                      
                <div class="form-group col-md-12" id="dateRangePicker">
                  	<label for="inputEmail4"><spring:message code="tracker.a11" text="defaul text"/></label>
                   	<span><fmt:formatDate  value="${dto.tracker_reg_date}"  pattern="yyyy-MM-dd HH:mm:ss"/></span>
                </div>
                
                <div class="form-group col-md-12"></div> 
                                                       	
    				 <div class="form-group col-md-12 ">
                        <label for="inputEmail4"><spring:message code="tracker.a12" text="defaul text"/></label>
                       <textarea class="form-control" id="history" name="history"   rows="5" maxlength="10000">${dto.history}</textarea> 
                       <div class="check_font" id="history_check"></div> 
                      </div>
                      
                <div class="form-group col-md-12"></div> 
    						
          
			      <div class="ln_solid"></div>
                      <div class="form-group">
                        <div class="col-md-12">
                        <input class="btn btn-primary" type="button" value="<spring:message code="user.a18" text="defaul text"/>" id="btnUpdate"/>
                        
                         <a onClick="location.href='${path}/trackerBoard/Tracker_Board'" class="btn btn-primary"><spring:message code="user.a19" text="defaul text"/></a>
                        </div>
                      </div>
					 </form>
                  </div>
                </div>
              </div> 
            </div>
            
            
         <script type="text/javascript">
	$(document).ready(function () {
		//상품 등록 유효성 검사
		  $("#btnUpdate").click(function(){
			  var t_group_id = $("#t_group_id").val();
			  var tracker_model = $("#tracker_model").val();
			  var tracker_capacity = $("#tracker_capacity").val();
			  var tracker_phone = $("#tracker_phone").val();
			  var tracker_builder = $("#tracker_builder").val();
			  var tracker_manager = $("#tracker_manager").val();
			  var tracker_owner = $("#tracker_owner").val();
			  var tracker_username = $("#tracker_username").val();
			  if(tracker_model=="" || !regType1.test(tracker_model)){
				  alert("<spring:message code='en.tracker_03' text='def'/>");
				  $("#tracker_model").focus();
				 return false;
			  }else if(tracker_capacity == "" || !regType2.test(tracker_capacity)){
				  alert("<spring:message code='en.tracker_04' text='def'/>");
				  $("#tracker_capacity").focus();
				 return false;
				 
			  }else if(tracker_builder == "" || !nameJ.test(tracker_builder)){
				  alert("<spring:message code='en.tracker_05' text='def'/>");
				  $("#tracker_builder").focus();
				  return false;
				  
			  }else if(tracker_manager == "" || !nameJ.test(tracker_manager)){
				  alert("<spring:message code='en.tracker_06' text='def'/>");
				  $("#tracker_manager").focus();
				  return false;
				  
			  }else if(tracker_owner == "" || !nameJ.test(tracker_owner)){
				  alert("<spring:message code='en.tracker_07' text='def'/>");
				  $("#tracker_owner").focus();
				  return false;
				  
			  }else if(tracker_phone == "" || !numberJ.test(tracker_phone)){ 
				  alert("<spring:message code='en.tracker_08' text='def'/>");
				 $("#tracker_phone").focus();
				  return false;
				  
			  }else if(tracker_username == "" || !nameJ.test(tracker_username)){
				  alert("<spring:message code='en.tracker_09' text='def'/>");
				  $("#tracker_username").focus();
				  return false;
				  
			  }
			  
			  //확인 대화 상자
			  if(confirm("<spring:message code='en.modify' text='def'/>")){
				  alert("<spring:message code='en.modify_success' text='def'/>");
				   document.form1.action="${path}/trackerBoard/update";
				  document.form1.submit();
			  }
		  });
		
	});
	
	//모든 공백 체크 정규식
	var empJ = /\s/g;
	//아이디 정규식
	var idJ = /^[0-9]{1,3}$/;
	// 비밀번호 정규식
	var pwJ = /^[A-Za-z0-9]{4,12}$/; 
	// 이름 정규식
	var nameJ = /^[가-힣a-zA-Z]{2,12}$/;
	// 이름 정규식
	var nameJJ = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|\*]+$/;
	// 군집이름 정규식
	var GroupNameJ = /^[가-힣]{2,9}$/;
	// 이메일 검사 정규식
	var mailJ = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	// 휴대폰 번호 정규식
	var phoneJ =  /^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/;
	// IP 정규식
	var ipJ = /^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$/;
	//숫자만 
	var numberJ = /^[0-9]{1,11}$/;
	// 영어 숫자만 허용 됨
	//var regType1 = /^[A-Za-z0-9+]*$/;
	// 19-07-30 KGW Modified
	var regType1 = /^[A-Za-z0-9-_]{1,20}$/;
	
	// 영어 숫자만 허용 됨
	var regType2 = /^[-]?\d+(?:[.]\d+)?$/;
	
	 	
	
 	//트래커 시공사에 특수문자 들어가지 않도록 설정
	$("#tracker_phone").on("propertychange change keyup paste input",function() {
	if (numberJ.test($(this).val())) {
			$("#phone_check").text('');
	} else {
		$('#phone_check').text('<spring:message code="en.07" text="defaul text"></spring:message>');
		$('#phone_check').css('color', 'red');
	}
	});	
 	
	//트래커 시공사에 특수문자 들어가지 않도록 설정
	$("#tracker_builder").on("propertychange change keyup paste input",function() {
	if (nameJ.test($(this).val())) {
			$("#builder_check").text('');
	} else {
		$('#builder_check').text('<spring:message code="en.15" text="defaul text"></spring:message>');
		$('#builder_check').css('color', 'red');
	}
	});	
	
	//담당자 시공사에 특수문자 들어가지 않도록 설정
	$("#tracker_manager").on("propertychange change keyup paste input",function() {
	if (nameJ.test($(this).val())) {
			$("#manager_check").text('');
	} else { 
		$('#manager_check').text('<spring:message code="en.17" text="defaul text"></spring:message>');
		$('#manager_check').css('color', 'red');
	}
	});
	
	//담당자 시공사에 특수문자 들어가지 않도록 설정
	$("#tracker_username").on("propertychange change keyup paste input",function() {
	if (nameJ.test($(this).val())) {
			$("#username_check").text('');
	} else { 
		$('#username_check').text('<spring:message code="en.18" text="defaul text"></spring:message>');
		$('#username_check').css('color', 'red');
	}
	});	
	
	//소유주 시공사에 특수문자 들어가지 않도록 설정
	$("#tracker_owner").on("propertychange change keyup paste input",function() {
	if (nameJ.test($(this).val())) {
		$("#owner_check").text('');
	} else {
		$('#owner_check').text('<spring:message code="en.16" text="defaul text"></spring:message>');
		$('#owner_check').css('color', 'red');
	}
	});	
		//핸드폰 번호 시공사에 특수문자 들어가지 않도록 설정
		$("#tracker_capacity").on("propertychange change keyup paste input",function() {
		if (regType2.test($(this).val())) {
				$("#capacity_check").text('');
		} else {
			$('#capacity_check').text('<spring:message code="en.22" text="defaul text"></spring:message>');
			$('#capacity_check').css('color', 'red');
		}
	});
	// 휴대전화
	$('#tracker_phone').on("propertychange change keyup paste input",function() {
		if(numberJ.test($(this).val())){
			$("#phone_check").text('');
		} else {
			$('#phone_check').text('<spring:message code="en.07" text="defaul text"></spring:message>');
			$('#phone_check').css('color', 'red');
		}
	});
	// 모델
	$('#tracker_model').on("propertychange change keyup paste input",function() {			
		if(regType1.test($(this).val())){
			$("#model_check").text('');
		} else {
			$('#model_check').text('<spring:message code="en.21" text="defaul text"></spring:message>');
			$('#model_check').css('color', 'red');
		}
	});
</script>