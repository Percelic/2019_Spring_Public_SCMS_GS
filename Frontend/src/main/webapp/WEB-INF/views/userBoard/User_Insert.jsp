<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ include file="/WEB-INF/views/include/common.jsp"%>
 <style>
    x_panel {
        background: #f8f8f8;
        padding: 60px 0;
    }
    
    #form1 > div {
        margin: 15px 0;
    }
</style>
<style type="text/css">
.layer { display: none; }
</style>

<input type="hidden" id="hidden_menu_id" value="user" /> 
        <div class="top_nav">
              <ul class="nav navbar-nav navbar-right">
              <li class="">
              </li>
             <li class="">
                	<a  href="<c:url value="/userBoard/User_Insert?lang=en" />">
                	<img  src="${path}/image/USA1.png">
                	</a>
                </li>
                <li class="">
                	<a  href="<c:url value="/userBoard/User_Insert?lang=ko" />">
                	<img  src="${path}/image/korean1.png">
                	</a>
                </li>
              </ul> 

        </div><!-- top-_nav 끝. -->
        <!-- /top navigation -->
					
        <!-- page content -->
        <div class="right_col" role="main">
          <div class="">
            <div class="page-title">
              <div class="title_left">
                <h3><spring:message code="user.insert" text="default text"></spring:message></h3>
              </div><!-- title_left -->
			
			
			
			<div class="clearfix"></div>
                 <!-- title- right -->
              </div><!-- page-title -->
            </div><!--  class = "" -->
            
            <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">
            
				<form id="form1"  method="post" action="${path}/userBoard/User_Insert.do"               data-parsley-validate class="form-horizontal form-label-left">
                   <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                      
                      <div class="form-group col-xs-4">
      					<label for="inputEmail4"><spring:message code="user.a1" text="default text"></spring:message>&nbsp;*</label>
      					<input type="text" name="user_id" maxlength="12"   id="user_id" autofocus required="required" class="form-control user_id"  placeholder="ID" required>
    							<div class="check_font" id="id_check"></div>
    						</div>
    						
    						
                      <div class="form-group col-xs-4">
      					<label for="inputEmail4"><spring:message code="user.a2" text="default text"></spring:message>&nbsp;*</label>
      					<input type="password" maxlength="16" name="user_pw" id="user_pw" value="****" readonly="readonly" style="background-color: #D5D5D5;"  required="required" class="form-control "  placeholder="Password">
      					<div class="check_font" id="pw_check"></div> 
    						</div>
    						
                      <div class="form-group col-xs-4">
      					<label for="inputEmail4"><spring:message code="user.a3" text="default text"></spring:message>&nbsp;*</label>
      					<input type="password" maxlength="16" name="user_pw2" value="****" readonly="readonly" style="background-color: #D5D5D5;"  id="user_pw2" required="required" class="form-control "  placeholder="Password">
      					<div class="check_font" id="pw2_check"></div>
      					
    						</div>
    						 <div class="form-group col-xs-12"></div>
    						
    						
                		
                         <div class="form-group col-xs-4"> 
      					<label for="inputEmail4"><spring:message code="user.a4" text="default text"></spring:message>&nbsp;*</label>
      					<input name="user_name" maxlength="12" id="user_name" required="required" class="form-control"  placeholder=<spring:message code="user.a4" text="default text"></spring:message>>
      					<div class="check_font" id="name_check"></div>
    						</div>
    						
                        <div class="form-group col-xs-4">
      					<label for="inputEmail4"><spring:message code="user.a5" text="default text"></spring:message>&nbsp;*</label>
      					<input type="text" maxlength="11" name="user_phone"  id="user_phone" required="required" class="form-control"  placeholder=<spring:message code="user.a5" text="default text"></spring:message> required>
    						<div class="check_font" id="phone_check"></div>

    						</div> 
    					

    					
                     <div class="form-group col-xs-4">
      					<label for="inputEmail4"><spring:message code="user.a6" text="default text"></spring:message>&nbsp;*</label> 
      					<input name="user_email" maxlength="30" id="user_email" maxlength="30" required="required" class="form-control"  placeholder=<spring:message code="user.a6" text="default text"></spring:message>>
    						<div class="check_font" id="email_check"></div>
    						</div>
    				   
                                          
                           	<div class="form-group col-xs-12">
                            		<label style="display: block;" ><spring:message code="user.a8" text="default text"/></label>
                            		<input class="form-control" style="width: 40%; display: inline;" 
                            		placeholder="<spring:message code="user.a8" text="defaul text"/>" name="signUpUserPostNo" id="signUpUserPostNo" type="text" readonly="readonly" >
                            		<button type="button" class="btn btn-default" onclick="execPostCode();" style="margin-bottom: 5px;">
                            		<i class="fa fa-search"></i> <spring:message code="user.a12" text="default text"></spring:message></button>
                            	</div>
                            	
                            	<div class="form-group col-xs-6">
                            		<label style="display: block;" ><spring:message code="user.a9" text="default text"/>&nbsp;*</label>
                            		<input class="form-control" name="user_addr" id="signUpUserCompanyAddress" type="text" readonly="readonly" required="required"  style="top: 5px;" placeholder="<spring:message code="user.a9" text="default text"/>">
                            	</div>
                            	
                            	 	<div class="form-group col-xs-6">
                            	 	<label style="display: block;" ><spring:message code="user.a21" text="default text"/>&nbsp;*</label>
                            		<input class="form-control" required="required" placeholder="<spring:message code="user.a21" text="default text"/>" name="user_addr_1" id="user_addr_1" type="text" maxlength="200" />
                            		<div class="check_font" id="addr2_check"></div>
                            		
                            	</div>
                            		 
                            	
								<div class="form-group col-xs-6">
									<label for="authority" class="control-label"><spring:message code="user.a11" text="default text"></spring:message>&nbsp;*</label>
									<select class="form-control"  id="authority" name="authority">
										<option value="ROLE_USER" ><spring:message code="user.a14" text="default text"></spring:message></option>
									    <option value="ROLE_ADMIN" selected="selected" ><spring:message code="user.a13" text="default text"></spring:message></option>
									</select>
								</div>
										 
										
								<div class="form-group col-xs-6">
									<label for="inputEmail4" class="control-label"><spring:message code="user.a15" text="defaul text"></spring:message>&nbsp;*</label>
									<select class="form-control" name="t_group_id" id="t_group_id">
											<option value="0">미 선택</option>
										<c:forEach var="row" items="${user_group_choice}">
											<option value="${row.t_group_id}">${row.t_group_name} (${row.t_group_id})</option>
										</c:forEach>
									</select>
								</div>
    						
    						<%-- <div class="form-group col-xs-6 layer">
								<label for="inputEmail4"><spring:message code="tracker.a1" text="defaul text"></spring:message></label>
								<select class="form-control" name="t_group_id" id="t_group_id">
									<c:forEach var="row" items="${tracker_group_choice}">
										<option value="${row.t_group_id}">${row.t_group_name} / 발전소 ID : (${row.t_group_id})</option>
									</c:forEach>
								</select>
							</div> --%>
							<div class="ln_solid"></div>
                      <div class="form-group"> 
                        <div class="col-xs-12">
							<input type="button" id="user_write" value="<spring:message code="user.a16" text="defaul text"/>" class="btn btn-primary" />
                          <%-- <button class="btn btn-primary signupbtn"value="확인" disabled="disabled" id="user_write"><spring:message code="user.a16" text="defaul text"/></button> --%>
                           <a onClick="location.href='${path}/userBoard/User_Board'" class="btn btn-primary"><spring:message code="user.a17" text="defaul text"/></a>
                        </div>
                      </div><!-- 확인 끝. -->
	
                    </form>
				</div>
					
					
                  </div>
                </div>


<script type="text/javascript">
		var isRegistered = false;
	
		$(document).ready(function () {
			$("#user_write").click(function () {
				
        	   var user_id = $("#user_id").val();
        	   var user_name = $("#user_name").val();
        	   var user_phone = $("#user_phone").val();
        	   var user_email = $("#user_email").val();
        	   var signUpUserCompanyAddress = $("#signUpUserCompanyAddress").val();
        	   var user_addr_1 = $("#user_addr_1").val();
        	   

        	   if(( user_id.length == 0 || !idJ.test(user_id) ) || isRegistered){
        		   alert("<spring:message code='en.user_02' text='def'/>");
        		   $("#user_id").focus();
        		   return false;
        	   }
        	   
        	   if(user_name.length == 0 || !nameJ.test(user_name)){
        		   alert("<spring:message code='en.user_03' text='def'/>");
        		   $("#user_name").focus();
        		   return false;
        	   }
        	   
        	   if(user_phone.length == 0 || !numberJ.test(user_phone)){
        		   alert("<spring:message code='en.user_04' text='def'/>");
        		   $("#user_phone").focus();
        		   return false;
        	   }
        	   if(user_email.length == 0 || !mailJ.test(user_email)){
        		   alert("<spring:message code='en.user_05' text='def'/>");
        		   $("#user_email").focus();
        		   return false;
        	   }
        	   
        	   if(signUpUserCompanyAddress.length == 0){
        		   alert("<spring:message code='en.user_06' text='def'/>");
        		   $("#signUpUserCompanyAddress").focus();
        		   return false;
        	   }
        	   
        	   if(user_addr_1.length == 0 || !addrJ.test(user_addr_1)){ 
        		   alert("<spring:message code='en.user_07' text='def'/>");
        		   $("#user_addr_1").focus();
        		   return false;
        	   }
        	   
        	   if(confirm("<spring:message code='en.011' text='default text'/>")){
        	   alert("<spring:message code='en.012' text='default text'/>");
        	   $("#form1").action="${path}/userBoard/User_Insert.do"; 
//         		$("#user_write").disabled=true;
        	   $("#form1").submit();
        	   }else{
        		   alert("<spring:message code='en.013' text='default text'/>");
        		   return false;
        	   }
        	   
           }
		);
		});
           </script>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	   <script type="text/javascript">
	   jQuery('#authority').change(function() {
			var state = jQuery('#authority option:selected').val();
			if ( state == 'ROLE_USER' ) {
				jQuery('.layer').show();
			} else {
				jQuery('.layer').hide(); 
			}
		});
	   
		function execPostCode() {
	 		new daum.Postcode({
	 			oncomplete: function(data) { 
	                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	                // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
	                var extraRoadAddr = ''; // 도로명 조합형 주소 변수
	                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                    extraRoadAddr += data.bname;
	                }
	                // 건물명이 있고, 공동주택일 경우 추가한다.
	                if(data.buildingName !== '' && data.apartment === 'Y'){
	                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                }
	                // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                if(extraRoadAddr !== ''){
	                    extraRoadAddr = ' (' + extraRoadAddr + ')';
	                }
	                // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
	                if(fullRoadAddr !== ''){
	                    fullRoadAddr += extraRoadAddr;
	                }
	                // 우편번호와 주소 정보를 해당 필드에 넣는다. 
	                console.log(data.zonecode);
	                console.log(fullRoadAddr);
	                
	                
	                $("[name=signUpUserPostNo]").val(data.zonecode);
	                $("[name=user_addr]").val(fullRoadAddr);
	                
	                /* document.getElementById('signUpUserPostNo').value = data.zonecode; //5자리 새우편번호 사용
	                document.getElementById('signUpUserCompanyAddress').value = fullRoadAddr;
	                document.getElementById('signUpUserCompanyAddressDetail').value = data.jibunAddress; */
	            }
	 		}).open();
	 	}
	 	
	 	$("#user_id").on("propertychange paste input",function() {
		// id = "id_reg" / name = "userId"
		var user_id = $('#user_id').val();
		$.ajax({
			url : '${path}/userBoard/idCheck.do?user_id='+ user_id,
			type : 'get',
			success : function(data) {							
				if (data == 1) {
						// 1 : 아이디가 중복되는 문구
						$("#id_check").text('<spring:message code="en.01" text="default text"></spring:message>');
						$("#id_check").css("color", "red");
						isRegistered = true;
// 						$("#user_write").attr("disabled", true);
					} else {
						if(idJ.test(user_id)){
							// 0 : 아이디 길이 / 문자열 검사
							$("#id_check").text("");
							isRegistered = false;
// 							$("#user_write").attr("disabled", false);
				
						} else if(user_id == ""){
							
							$('#id_check').text('<spring:message code="en.02" text="default text"></spring:message>');
							$('#id_check').css('color', 'red');
// 							$("#user_write").attr("disabled", true);				
							
						} else {
							
							$('#id_check').text("<spring:message code="en.03" text="default text"></spring:message>");
							$('#id_check').css('color', 'red');
// 							$("#user_write").attr("disabled", true);
						}
						
					}
				
					
				}, error : function() {
						console.log("실패");
				}
			});
		});
		
		//스크립트 시작할 것.
		
		
		//모든 공백 체크 정규식
		var empJ = /\s/g;
		//아이디 정규식
		var idJ = /^[a-z0-9]{4,12}$/;
		// 비밀번호 정규식-숫자, 문자, 특수문자 조합 8~16자리
		var pwJ = /^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9])(?=.*[0-9]).{8,16}$/;
		// 이름 정규식
		var nameJ = /^[가-힣a-zA-Z]{2,12}$/;
		// 이메일 검사 정규식
		var mailJ = /^[0-9a-zA-Z]([-_.,]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.,]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		// 휴대폰 번호 정규식
		var phoneJ = /^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/;
	 
		var numberJ = /^[0-9]{1,11}$/;
		
		var addrJ = /^[가-힣0-9a-zA-Z ]{1,200}$/;
		
	//아이디 체크여부 확인 (아이디 중복일 경우 = 0 , 중복이 아닐경우 = 1 )

	//비밀번호 유효성 검사
	//1-1 정규식 체크
	/* $("#user_pw").blur(function () {
		if(pwJ.test($('#user_pw').val())){
			console.log('true');
			$('#pw_check').text('');
			$("#addBtn").attr("disabled", false);
		}else{
			console.log('false');
			$('#pw_check').text('숫자, 문자, 특수문자 조합 8~20자리 입력');
			$('#pw_check').css('color', 'red');
			$("#addBtn").attr("disabled", true);
		}
	}); */
	
	//이름에 특수문자 들어가지 않도록 설정
	$("#user_name").on("propertychange change keyup paste input",function() {
	if (nameJ.test($(this).val())) {
			//console.log(nameJ.test($(this).val()));
			$("#name_check").text('');
// 			$("#user_write").attr("disabled", false);
	} else {
		$('#name_check').text('<spring:message code="en.06" text="default text"/>');
		$('#name_check').css('color', 'red');
// 		$("#user_write").attr("disabled", true);
	}
});	
	
	
	// 휴대전화
	$('#user_phone').on("propertychange change keyup paste input",function(){
		if(numberJ.test($(this).val())){
			//console.log(phoneJ.test($(this).val()));
			$("#phone_check").text('');
// 			$("#user_write").attr("disabled", false);
		} else {
			$('#phone_check').text('<spring:message code="en.07" text="default text"/>');
			$('#phone_check').css('color', 'red');
// 			$("#user_write").attr("disabled", true);
		}
	});
	
	// 이메일
	$('#user_email').on("propertychange change keyup paste input",function(){
		if(mailJ.test($(this).val())){
			//console.log(mailJ.test($(this).val()));
			$("#email_check").text('');
// 			$("#user_write").attr("disabled", false);
		} else {
			$('#email_check').text('<spring:message code="en.08" text="default text"/>');
			$('#email_check').css('color', 'red');
// 			$("#user_write").attr("disabled", true);
		}
	});
	
	// 주소
	$('#user_addr_1').on("propertychange change keyup paste input", function(){
		if(addrJ.test($(this).val())){
			console.log(empJ.test($(this).val()));
			$("#addr2_check").text('');
// 			$("#user_write").attr("disabled", false);
		} else {
			$('#addr2_check').text('<spring:message code="en.09" text="default text"></spring:message>');
			$('#addr2_check').css('color', 'red');
// 			$("#user_write").attr("disabled", true);		
			}	
	});
	 </script>
	
