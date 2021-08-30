<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/common.jsp"%>
<script type="text/javascript">
	//모든 공백 체크 정규식
	var empJ = /\s/g;
	//아이디 정규식
	var idJ = /^[A-Za-z0-9]{4,10}$/;
	// 비밀번호 정규식-숫자, 문자, 특수문자 조합 8~20자리
	var pwJ = /(?=.*\d{1,50})(?=.*[~`!@#$%\^&*()-+=]{1,50})(?=.*[a-zA-Z]{1,50}).{8,20}$/;
	// 이름 정규식
	var nameJ = /^[가-힣]{2,6}$/;
	// 이메일 검사 정규식
	var mailJ = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	// 휴대폰 번호 정규식
	var phoneJ = /^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/;
	$(document).ready(function () {
		  $("#btnUpdate").click(function(){
			  
    	   var user_name = $("#user_name").val();
    	   var user_phone = $("#user_phone").val();
    	   var user_email = $("#user_email").val();
    	   var signUpUserCompanyAddress = $("#signUpUserCompanyAddress").val();
    	   var user_addr_1 = $("#user_addr_1").val();

			if(user_name.length == 0 || !nameJ.test(user_name)){
			 alert('<spring:message code="en.user_03" text="default text"></spring:message>');
			 $("#user_name").focus();
			 return false;
			
			}
			if(user_phone.length == 0 || !numberJ.test(user_phone)){
			 /* alert("연락처 입력을 확인해 주세요"); */
			 alert('<spring:message code="en.user_04" text="default text"></spring:message>');
			 $("#user_phone").focus();
			 return false;
			}
			if(user_email.length == 0 || !mailJ.test(user_email)){
			 /* alert("이메일을 확인해 주세요"); */
			 alert('<spring:message code="en.user_05" text="default text"></spring:message>');
			 $("#user_email").focus();
			 return false;
			}
			if(signUpUserCompanyAddress.length == 0){
			 /* alert("주소를 입력해주세요"); */
			 alert('<spring:message code="en.user_06" text="default text"></spring:message>');
			 $("#signUpUserCompanyAddress").focus();
			 return false;
			}
			if(user_addr_1.length == 0 || !addrJ.test(user_addr_1)){
			 /* alert("상세주소를 입력해주세요"); */
			 alert('<spring:message code="en.user_07" text="default text"></spring:message>');
			 $("#user_addr_1").focus();
			 return false;
			}
			  
			  //확인 대화 상자
			  if(confirm('<spring:message code="en.modify" text="default text"></spring:message>')){
				 alert('<spring:message code="en.modify_success" text="default text"></spring:message>')
				  document.form1.action="${path}/userBoard/update.do";
				  document.form1.submit();
			  }
			  else {
				  return false;
			  }
		  });
		
	});
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
				<div class="page-title">
					<div class="title_left">
						<h3><spring:message code="user.view" text="defaul text"></spring:message></h3>
					</div>
		
					<div class="clearfix"></div>
				</div>
				
				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<form name="form1"  method="post" data-parsley-validate class="form-horizontal form-label-left">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							<div class="form-group col-md-4">
								<label  for="first-name"><spring:message code="user.a1" text="defaul text"/>&nbsp;*<span class="required"></span></label>
								<input type="text" id="user_id" name="user_id" required="required" class="form-control col-md-7 col-xs-12"
								value="${dto.user_id}" readonly="readonly"/>
							</div>
                      
							<div class="form-group col-md-4">
								<label for="inputEmail4"><spring:message code="user.a2" text="default text"/>&nbsp;*</label>
		      					<input class="btn form-control btn_reset" type="button" value="<spring:message code="user.a20" text="defaul text"/>" id="pw_reset" onclick="pwReset();"/>
							</div>
                		
							<div class="form-group col-md-4">
	                          	 <label for="inputEmail4"><spring:message code="user.a4" text="defaul text"/>&nbsp;*</label>
		                          <input  class="form-control  " type="text" name="user_name" id="user_name"
		                          value="${dto.user_name}" placeholder="<spring:message code="user.a4" text="defaul text"/>" maxlength="12">
	                          	<div class="check_font" id="name_check"></div>
	                     	 </div>
    						
							<div class="form-group col-md-4">
								<label for="inputEmail4"><spring:message code="user.a5" text="defaul text"/>&nbsp;*</label>
								<input class="form-control  " type="text" name="user_phone" id="user_phone" value="${dto.user_phone}"
								 placeholder="01000000000" maxlength="11">
								<div class="check_font" id="phone_check"></div>
							</div>
    						
							<div class="form-group col-md-4">
								<label for="middle-name"><spring:message code="user.a6" text="defaul text"/>&nbsp;*</label>
								<input class="form-control" type="text" name="user_email" id="user_email" value="${dto.user_email}"
								 placeholder="<spring:message code="user.a6" text="defaul text"/>" maxlength="30">
	                          	<div class="check_font" id="email_check"></div>
	                      	</div>
    				   
                      
                      		<div class="form-group col-md-12">
                            	<label style="display: block;" ><spring:message code="user.a8" text="defaul text"/></label>
                            	<input class="form-control" style="width: 40%; display: inline;" placeholder="<spring:message code="user.a8" text="defaul text"/>" name="signUpUserPostNo" id="signUpUserPostNo" type="text" readonly="readonly" >
                            	<button type="button" class="btn btn-default" onclick="execPostCode();" style="margin-bottom: 5px;">
                            	<i class="fa fa-search"></i> <spring:message code="user.a12" text="default text"/></button>
                            </div>
                            <div class="form-group col-md-6">
                            	<label style="display: block;" ><spring:message code="user.a9" text="defaul text"/>&nbsp;*</label>
                            	<input class="form-control" required="required"   style="top: 5px;" placeholder="<spring:message code="user.a9" text="default text"/>" name="user_addr" value="${dto.user_addr }" id="signUpUserCompanyAddress" type="text" readonly="readonly" />
                            </div>
                            <div class="form-group col-md-6">
                            	<label style="display: block;" ><spring:message code="user.a21" text="defaul text"/>&nbsp;*</label>
                            	<input class="form-control" required="required" placeholder="<spring:message code="user.a21" text="default text"/>" name="user_addr_1" id="user_addr_1" type="text" value="${dto.user_addr_1}" maxlength="200"  />
                            	<div class="check_font" id="addr2_check"></div>
                            </div>
                            	 
                      
                      
	                        <div class="form-group col-md-3">
	                        <label for="middle-name"><spring:message code="user.a11" text="defaul text"></spring:message>&nbsp;*</label>
	                         <input class="form-control col-md-7 col-xs-12" type="text" name="authority"
	                          value="<c:if test="${dto.authority == 'ROLE_ADMIN'}"><spring:message code="user.a13" text="default text"/></c:if><c:if test="${dto.authority != 'ROLE_ADMIN'}"><spring:message code="user.a14" text="default text"/></c:if>" readonly="readonly"/>
	                      </div>
							<div class="form-group col-md-3">
		                        <label for="middle-name"><spring:message code="user.a15" text="defaul text"></spring:message>&nbsp;*</label>
		                        <select class="form-control" name="t_group_id" id="group_name">
		                        		<option value='0'>미선택</option>
		                       	
										<c:forEach var="row" items="${user_group_choice}">
											<c:if test="${dto.t_group_id == row.t_group_id}">
												<option value="${row.t_group_id}" selected="selected">${row.t_group_name} ( ${row.t_group_id})</option>
											</c:if>
											
											<c:if test="${dto.t_group_id != row.t_group_id}">
												<option value="${row.t_group_id}">${row.t_group_name} (${row.t_group_id})</option>
											</c:if>
										</c:forEach>
									</select>
								<%-- <input class="form-control col-md-7 col-xs-12" type="text" name="group_name" value="${dto.group_name}" readonly="readonly"/> --%>
							</div>
	                        <div class="form-group col-md-6" style="margin-top: 25px;" id="dateRangePicker">
	                        <label for="inputEmail4"><spring:message code="inverter.b9" text="defaul text"></spring:message></label>
	                       <span><fmt:formatDate  value="${dto.user_reg_date}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
	                      </div>
                      
		        	 		<div class="ln_solid"></div>
                      <div class="form-group">
                      	 <div class="col-md-12">
                      	 <input class="btn btn-primary" type="button" id="btnUpdate" value="<spring:message code="user.a18" text="defaul text"></spring:message>">
                        <!-- <button class="btn btn-primary" id="btnUpdate" disabled="disabled"></button> -->
                        <a onClick="location.href='${path}/userBoard/User_Board'" class="btn btn-primary"><spring:message code="user.a19" text="defaul text"/></a>
                         </div>
                         </div>
                    </form>
					</div>
                </div>
                <!-- row -->
              </div> 
            </div>
        <!-- /주소 -->
	   <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	   <script type="text/javascript">
	      //모든 공백 체크 정규식
		var empJ = /s$/;
		//아이디 정규식
		var idJ = /^[a-z0-9]{4,12}$/;
		// 비밀번호 정규식
		var pwJ = /^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9])(?=.*[0-9]).{8,16}$/;
		// 이름 정규식
		var nameJ = /^[가-힣a-zA-Z]{2,12}$/;
		// 이메일 검사 정규식
		var mailJ = /^[0-9a-zA-Z]([-_.,]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.,]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		// 휴대폰 번호 정규식
		var phoneJ = /^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/;
		//숫자만 
		var numberJ = /^[0-9]{1,11}$/;
		
		var addrJ = /^[가-힣0-9a-zA-Z ]{1,200}$/;
		
		var Check = /[0]/;
		$("#user_pw2").blur(function() {
			// id = "id_reg" / name = "userId"
			var user_pw2 = $('#user_pw2').val();
			$.ajax({
				url : '${path}/userBoard/PWCheck.do?user_pw2='+ user_pw2,
				type : 'get',
				success : function(data) {
					//console.log("1 = 중복o / 0 = 중복x : "+ data);							      
					
					if (data == 1) {
							// 1 : 아이디가 중복되는 문구
							$("#pw2_check").text("<spring:message code="en.28" text="defaul text"></spring:message>");
							$("#pw2_check").css("color", "red");
// 							$("#btnUpdate").attr("disabled", false);
						} else {
							
							if(pwJ.test(user_pw2)){
								// 0 : 아이디 길이 / 문자열 검사
								$("#pw2_check").text("<spring:message code="en.29" text="defaul text"></spring:message>");
// 								$("#btnUpdate").attr("disabled", true);
					
							} else if(user_pw2 == ""){
								
								$('#pw2_check').text('<spring:message code="en.04" text="defaul text"></spring:message>');
								$('#pw2_check').css('color', 'red');
// 								$("#btnUpdate").attr("disabled", true);				
								
							} else {
								
								$('#pw2_check').text("<spring:message code="en.04" text="defaul text"></spring:message>");
								$('#pw2_check').css('color', 'red');
// 								$("#btnUpdate").attr("disabled", true);
							}
							
						}
					}, error : function() {
							//console.log("실패"); 
					}
				});
			});
	   
		 //비밀번호 유효성 검사
		//1-1 정규식 체크
		$("#user_pw").blur(function () {
			if(pwJ.test($('#user_pw').val())){
				//console.log('true');
				$('#pw_check').text('');
// 				$("#btnUpdate").attr("disabled", false);
			}else{
				//console.log('false');
				$('#pw_check').text('<spring:message code="en.04" text="default text"/>');
				$('#pw_check').css('color', 'red');
// 				$("#btnUpdate").attr("disabled", true);
			}
		});
		
		//이름에 특수문자 들어가지 않도록 설정
		$("#user_name").on("propertychange change keyup paste input",function() {
			if (nameJ.test($(this).val())) {
					//console.log(nameJ.test($(this).val()));
					$("#name_check").text('');
// 					$("#btnUpdate").attr("disabled", false);
			} else {
				$('#name_check').text('<spring:message code="en.06" text="default text"/>');
				$('#name_check').css('color', 'red');
// 				$("#btnUpdate").attr("disabled", true);
			}
		/* $("#user_name").blur(function() {
		if (nameJ.test($(this).val())) {
				//console.log(nameJ.test($(this).val()));
				$("#name_check").text('');
				$("#addBtn").attr("disabled", false);
		} else {
			$('#name_check').text('<spring:message code="en.06" text="default text"></spring:message>');
			$('#name_check').css('color', 'red');
			$("#btnUpdate").attr("disabled", true);
		} */
	});	
		
		
		// 휴대전화
		$('#user_phone').on("propertychange change keyup paste input",function(){
			if(numberJ.test($(this).val())){
				//console.log(phoneJ.test($(this).val()));
				$("#phone_check").text('');
// 				$("#btnUpdate").attr("disabled", false);
			} else {
				$('#phone_check').text('<spring:message code="en.07" text="default text"/>');
				$('#phone_check').css('color', 'red');
// 				$("#btnUpdate").attr("disabled", true);
			}
		});
		/* $('#user_phone').blur(function(){
			
			if(phoneJ.test($(this).val())){
				//console.log(phoneJ.test($(this).val()));
				$("#phone_check").text('');
				$("#btnUpdate").attr("disabled", false);
			} else {
				$('#phone_check').text('<spring:message code="en.07" text="default text"></spring:message>');
				$('#phone_check').css('color', 'red');
				$("#btnUpdate").attr("disabled", true);
			}
		}); */
		
		// 이메일
		$('#user_email').on("propertychange change keyup paste input",function(){
			if(mailJ.test($(this).val())){
				//console.log(mailJ.test($(this).val()));
				$("#email_check").text('');
// 				$("#btnUpdate").attr("disabled", false);
			} else {
				$('#email_check').text('<spring:message code="en.08" text="default text"/>');
				$('#email_check').css('color', 'red');
// 				$("#btnUpdate").attr("disabled", true);
			}
		});
		/* $('#user_email').blur(function(){
			if(mailJ.test($(this).val())){
				//console.log(mailJ.test($(this).val()));
				$("#email_check").text('');
				$("#addBtn").attr("disabled", false);
			} else {
				$('#email_check').text('<spring:message code="en.08" text="default text"></spring:message>');
				$('#email_check').css('color', 'red');
				$("#btnUpdate").attr("disabled", true);
			}
		}); */
		
		// 주소
		// 주소
		$('#user_addr_1').on("propertychange change keyup paste input", function(){
			if(addrJ.test($(this).val())){
				console.log(empJ.test($(this).val()));
				$("#addr2_check").text('');
// 				$("#btnUpdate").attr("disabled", false);
			} else {
				$('#addr2_check').text('<spring:message code="en.09" text="default text"></spring:message>');
				$('#addr2_check').css('color', 'red');
// 				$("#btnUpdate").attr("disabled", true);		
				}	
		});
		/* $('#signUpUserCompanyAddress').blur(function(){
			if(empJ.test($(this).val())){
				//console.log(mailJ.test($(this).val()));
				$("#add2_check").text('');
				$("#btnUpdate").attr("disabled", false);
			} else {
				$('#addr2_check').text('<spring:message code="en.09" text="default text"/>');
				$('#addr2_check').css('color', 'red');
				$("#btnUpdate").attr("disabled", true);
			}
		}); */
		
		// 상세 주소
		$('#signUpUserCompanyAddress').blur(function(){
			if(empJ.test($(this).val())){
				//console.log(mailJ.test($(this).val()));
				$("#add3_check").text('');
// 				$("#btnUpdate").attr("disabled", false);
			} else {
				$('#addr3_check').text('<spring:message code="en.010" text="default text"/>');
				$('#addr3_check').css('color', 'red');
// 				$("#btnUpdate").attr("disabled", true);
			}
		});
		
		//발전소
		$('#group_name').change(function(){
// 			$("#btnUpdate").attr("disabled", false);
		});
		
		// check
		$('#check_1').blur(function(){
			if(Check.test($(this).val())){
				console.log(Check.test($(this).val()));
				$("#Check_check").text('');
// 				$("#btnUpdate").attr("disabled", false);
			} else {
				$('#Check_check').text('<spring:message code="en.27" text="default text"></spring:message>');
				$('#Check_check').css('color', 'red');
// 				$("#btnUpdate").attr("disabled", true);
			}
		});
		
		$(function(){
			//비밀번호 확인
				$('#user_pw3').blur(function(){
				   if($('#user_pw').val() != $('#user_pw3').val()){
				    	if($('#user_pw3').val()!=''){
					    alert("<spring:message code="en.05" text="default text"></spring:message>");
				    	    $('#user_pw3').val('');
				          $('#user_pw3').focus();
				       }
				    }
				})
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
		
		function pwReset() {
			if(confirm("<spring:message code='en.user_002' text='def'/>")) {
				var token = $("meta[name='_csrf']").attr("content");
				var header = $("meta[name='_csrf_header']").attr("content");
				var userId = $("#user_id").val();
				
				$.ajax({
					type : "POST"
					,url : "${path}/User_auth/pwReset"
					,data : {"user_id" : userId}
					,dataType : "json"
					,beforeSend: function(xhr) {
						xhr.setRequestHeader(header, token);
				    }
					,success : function (data) {
						alert("<spring:message code='en.user_003' text='def'/>");
					}
				});	
			}
		}
		
	 </script>
        <!-- footer content -->

    <!-- jQuery -->
    <script src="${vendors}/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="${vendors}/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- FastClick -->
    <script src="${vendors}/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->
    <script src="${vendors}/nprogress/nprogress.js"></script>
    <!-- bootstrap-progressbar -->
    <script src="${vendors}/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
    <!-- iCheck -->
    <script src="${vendors}/iCheck/icheck.min.js"></script>
    <!-- bootstrap-daterangepicker -->
    <script src="${vendors}/moment/min/moment.min.js"></script>
    <script src="${vendors}/bootstrap-daterangepicker/daterangepicker.js"></script>
    <!-- bootstrap-wysiwyg -->
    <script src="${vendors}/bootstrap-wysiwyg/js/bootstrap-wysiwyg.min.js"></script>
    <script src="${vendors}/jquery.hotkeys/jquery.hotkeys.js"></script>
    <script src="${vendors}/google-code-prettify/src/prettify.js"></script>
    <!-- jQuery Tags Input -->
    <script src="${vendors}/jquery.tagsinput/src/jquery.tagsinput.js"></script>
    <!-- Switchery -->
    <script src="${vendors}/switchery/dist/switchery.min.js"></script>
    <!-- Select2 -->
    <script src="${vendors}/select2/dist/js/select2.full.min.js"></script>
    <!-- Parsley -->
    <script src="${vendors}/parsleyjs/dist/parsley.min.js"></script>
    <!-- Autosize -->
    <script src="${vendors}/autosize/dist/autosize.min.js"></script>
    <!-- jQuery autocomplete -->
    <script src="${vendors}/devbridge-autocomplete/dist/jquery.autocomplete.min.js"></script>
    <!-- starrr -->
    <script src="${vendors}/starrr/dist/starrr.js"></script>
    <!-- Custom Theme Scripts -->
    <script src="${build}/js/custom.min.js"></script>
    
        <!-- Ion.RangeSlider -->
    <script src="${vendors}/ion.rangeSlider/js/ion.rangeSlider.min.js"></script>
    <!-- Bootstrap Colorpicker -->
    <script src="${vendors}/mjolnic-bootstrap-colorpicker/dist/js/bootstrap-colorpicker.min.js"></script>
     <!-- jquery.inputmask -->
    <script src="${vendors}/jquery.inputmask/dist/min/jquery.inputmask.bundle.min.js"></script>
     <!-- jQuery Knob -->
    <script src="${vendors}/jquery-knob/dist/jquery.knob.min.js"></script>
    <!-- Cropper -->
    <script src="${vendors}/cropper/dist/cropper.min.js"></script>
	
	
