<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/common.jsp"%>
<script src="${res}/inverter/js/inverterDetail.js"></script>
<input type="hidden" id="hidden_menu_id" value="group" /> 
        <div class="top_nav">          
        
              <ul class="nav navbar-nav navbar-right">
              <li class="">
              </li>
              
                <li class="">
                	<a  href="<c:url value="/groupBoard/Group_Insert?lang=en" />">
                	<img  src="${path}/image/USA1.png">
                	</a>
                </li>
                <li class="">
                	<a  href="<c:url value="/groupBoard/Group_Insert?lang=ko" />">
                	<img  src="${path}/image/korean1.png">
                	</a>
                </li>
              </ul> 
        </div> <!-- top-_nav 끝. -->
        <!-- /top navigation -->

        <!-- page content -->
 		 <div class="right_col" role="main">
          <div class="">
            <div class="page-title">
              <div class="title_left">
                <h3><spring:message code="group.insert" text="defatul text"></spring:message></h3>
            </div><!-- title_left -->
			
			<div class="clearfix"></div>
                 <!-- title- right -->
              </div><!-- page-title -->
            </div><!--  class = "" -->
            
            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                    <form name="form1" id="form1" method="post" action="${path}/groupBoard/Group_Insert.do" data-parsley-validate class="form-horizontal form-label-left">
                          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                          
                     <div class="form-group col-md-6" id="divInputId">
      					<label for="inputEmail4"><spring:message code="group.a1" text="defatul text"></spring:message>&nbsp;*</label>
      					<input type="text" maxlength="3" name="t_group_id" id="t_group_id" class="form-control t_group_id"  placeholder="<spring:message code="group.a1" text="defatul text"></spring:message>" autofocus required required="required">
									<div class="check_font" id="id_check"></div>
    						</div>
    						
                     <div class="form-group col-md-6">
      					<label for="inputEmail4"><spring:message code="group.a2" text="defatul text"></spring:message>&nbsp;*</label>
      					<input name="t_group_name" maxlength="30" id="t_group_name" required="required" class="form-control"  placeholder="<spring:message code="group.a2" text="defatul text"/>">
									<div class="check_font" id="t_group_name_check"></div>
    						</div> 
    							<div class="form-group col-md-12"></div>
                		
                       <div class="form-group col-md-6">
      					<label for="inputEmail4"><spring:message code="group.a3" text="defatul text"></spring:message>&nbsp;*</label>
      					<input name="t_group_builder" maxlength="12" id="t_group_builder" required="required" class="form-control"  placeholder="<spring:message code="group.a3" text="defatul text"/>">
      							<div class="check_font" id="builder_check"></div>
    						</div>
    						
                       <div class="form-group col-md-6">
      					<label for="inputEmail4"><spring:message code="group.a4" text="defatul text"></spring:message>&nbsp;*</label>
      					<input name="t_group_owner" maxlength="12" id="t_group_owner" required="required" class="form-control"  placeholder="<spring:message code="group.a4" text="defatul text"/>">
      						<div class="check_font" id="owner_check"></div>
    						</div>

    							<div class="form-group col-md-12"></div>

    						
                        <div class="form-group col-md-6">
      					<label for="inputEmail4"><spring:message code="group.a5" text="defatul text"></spring:message>&nbsp;*</label>
      					<input name="t_group_phone" maxlength="11" id="t_group_phone" required="required" class="form-control" placeholder="<spring:message code="group.a5" text="defatul text"/>"  >
      						<div class="check_font" id="phone_check"></div>
    						</div>
    					
    					
                      <div class="form-group col-md-6">
      					<label for="inputEmail4"><spring:message code="group.a6" text="defatul text"></spring:message>&nbsp;*</label>
      					<input name="t_group_manager" maxlength="12" id="t_group_manager" required="required" class="form-control"  placeholder="<spring:message code="group.a6" text="defatul text"/>">
      						<div class="check_font" id="manager_check"></div>
    						</div>
    						
    							<div class="form-group col-md-12"></div> 
    						
    						
                      <div class="form-group col-md-6">
      					<label for="inputEmail4"><spring:message code="group.a9" text="defatul text"></spring:message>&nbsp;*</label>
      					<input name="t_group_username" maxlength="12" id="t_group_username" required="required" class="form-control"  placeholder="<spring:message code="group.a9" text="defatul text"/>">
      						<div class="check_font" id="username_check"></div>
    						</div>
                      <div class="form-group col-md-6">
      					<label for="inputEmail4"><spring:message code="group.a10" text="defatul text"></spring:message>&nbsp;*</label>
      					<input name="t_group_ip" maxlength="15" id="t_group_ip"  required="required" class="form-control"  placeholder="<spring:message code="group.a10" text="defatul text"/>">
      						<div class="check_font" id="ip_check"></div>
    						</div>
      
      
    							<div class="form-group col-md-12"></div>
      
      
                            	<div class="form-group col-md-12">
                            		<label style="display: block;" ><spring:message code="group.a7" text="defatul text"></spring:message></label>
                            		<input class="form-control" style="width: 40%; display: inline;" 
                            		placeholder="<spring:message code="group.a7" text="defaul text"/>" name="signUpUserPostNo" id="signUpUserPostNo" type="text" readonly="readonly" >
                            		<button type="button" class="btn btn-default" onclick="execPostCode();" style="margin-bottom: 5px;">
                            		<i class="fa fa-search"></i> <spring:message code="user.a12" text="default text"/></button>
                            	</div>
                            	
                            	<div class="form-group col-md-12"></div> 
                            	
                            	<div class="form-group col-md-6">
                            		<label style="display: block;" ><spring:message code="group.a08" text="defatul text"></spring:message>&nbsp;*</label>
                            		<input class="form-control" required="required"  style="top: 5px;" placeholder="<spring:message code="group.a08" text="default text"/>" name="t_group_addr" id="signUpUserCompanyAddress" type="text" readonly="readonly" />
                            	</div>
                            		
                            	<div class="form-group col-md-6">
                            		<label style="display: block;" ><spring:message code="group.a09" text="defatul text"></spring:message>&nbsp;*</label>
                            		<input class="form-control" required="required" placeholder="<spring:message code="group.a09" text="default text"/>" name="t_group_addr2" id="t_group_addr2" type="text"  />
                            		<div class="check_font" id="detailaddr_check"></div>
                            	</div>
                            	
                            	<div class="form-group col-md-12"></div> 
                            	
                            	       
                       <div class="form-group col-md-12 ">
                        <label for="inputEmail4"><spring:message code="group.a8" text="defatul text"></spring:message></label>
                       <textarea class="form-control col-sm-5" id="history" name="history" rows="5" maxlength="10000"></textarea>
                       <div class="check_font" id="history_check"></div> 
                      </div>
                      <div class="form-group col-md-12"></div> 
                            	
                   
										
			<div class="ln_solid"></div>
                      <div class="form-group">
                        <div class="col-md-12">
                    		 <%-- <button class="btn btn-primary" id="addBtn" disabled="disabled" value="확인"><spring:message code="group.a11" text="defaul text"></spring:message></button> --%>
                    		 <input type="button" class="btn btn-primary" id="addBtn" value="<spring:message code='group.a11' text='defaul text'/>">
                    		 
                            <a onClick="location.href='${path}/groupBoard/Group_Board'" class="btn btn-primary"><spring:message code="group.a12" text="defaul text"></spring:message></a> 
                        </div>
                      </div><!-- 확인 끝. -->
                      
                       </form>
                  </div>
                </div>
            </div>
		<script type="text/javascript">
		 $(document).ready(function () {
			$("#addBtn").click(function () {
				
				var t_group_id = $("#t_group_id").val();//군집 아이디
				var t_group_name = $("#t_group_name").val();// 군집 명
				var t_group_builder = $("#t_group_builder").val();// 시공사
				var t_group_owner = $("#t_group_owner").val();//소유주
				var t_group_phone = $("#t_group_phone").val();//핸드폰 번호
				var t_group_manager = $("#t_group_manager").val();//관리인 번호
				var t_group_username = $("#t_group_username").val();//관리인
				var signUpUserCompanyAddress = $("#signUpUserCompanyAddress").val();
				var t_group_ip = $("#t_group_ip").val();
	            var t_group_addr2 = $("#t_group_addr2").val();
				
				if(t_group_id == "" || !idJ.test(t_group_id)){
					alert("<spring:message code='en.group_02' text='def'/>");
					$("t_group_id").focus();
					return false;
				}else  if(t_group_name == "" || !GroupNameJ.test(t_group_name)){
					alert("<spring:message code='en.group_03' text='def'/>");
					$("t_group_name").focus();
					return false;
				}else	if(t_group_builder == "" || !nameJ.test(t_group_builder)){
					alert("<spring:message code='en.group_04' text='def'/>");
					$("t_group_builder").focus();
					return false;
				}else if(t_group_owner == "" || !nameJ.test(t_group_owner)){
					alert("<spring:message code='en.group_05' text='def'/>");
					$("t_group_owner").focus();
					return false;
				}else	if(t_group_phone == "" || !numberJ.test(t_group_phone)){
					alert("<spring:message code='en.group_06' text='def'/>");
					$("t_group_phone").focus();
					return false;
				}else if(t_group_manager == "" || !nameJ.test(t_group_manager)){
					alert("<spring:message code='en.group_07' text='def'/>");
					$("t_group_manager").focus();
					return false;
				}else if(t_group_username == "" || !nameJ.test(t_group_username)){
					alert("<spring:message code='en.group_08' text='def'/>");
					$("t_group_username").focus();
					return false;
					
				}else if(t_group_ip == "" || !nameJ.test(t_group_username)){
					alert("<spring:message code='en.group_09' text='def'/>");
					$("t_group_username").focus();
					return false;
				}else if(signUpUserCompanyAddress.length == 0){
					 alert("<spring:message code='en.group_10' text='def'/>");
	        		   $("#signUpUserCompanyAddress").focus();
	        		   return false;
				}else if(t_group_addr2 == 0 || !addrJ.test(t_group_addr2)){
					  alert("<spring:message code='en.group_11' text='def'/>");
	        		   $("#t_group_addr2").focus();
	        		   return false;
				}
				//확인 대화 상자
				  if(confirm("<spring:message code='en.register' text='def'/>")){
					  alert("<spring:message code='en.012' text='def'/>");
					  document.form1.action="${path}/groupBoard/Group_Insert.do";
					  document.form1.submit();
				  }else{
					  return false;
				  }
			  });		
		});
		</script> 
		
		<script type="text/javascript">
		
		//모든 공백 체크 정규식
		var empJ = /\s/g;
		//아이디 정규식
		var idJ = /^[1-9?]([0-9]{1,3})*$/;
		// 비밀번호 정규식
		var pwJ = /^[A-Za-z0-9]{4,12}$/; 
		// 이름 정규식
		var nameJ = /^[가-힣a-zA-Z]{2,12}$/;
		// 군집이름 정규식
		var GroupNameJ = /^[가-힣a-zA-Z-_0-9]{2,30}$/;
		// 이메일 검사 정규식
		var mailJ = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		// 휴대폰 번호 정규식
		var phoneJ =   /^\d{3}-\d{3,4}-\d{4}$/;
		// IP 정규식
		var ipJ =  /^(1|2)?\d?\d([.](1|2)?\d?\d){3}$/;
	
		var numberJ =  /^[0-9]+$/;
		var regType1 = /^[A-Za-z0-9+]*$/;
		
		var addrJ = /^[가-힣0-9a-zA-Z ]{1,200}$/;
		
		$("#t_group_id").on("propertychange change keyup paste input" ,function() {
			// id = "id_reg" / name = "userId"
			var t_group_id = $('#t_group_id').val();
			$.ajax({
				url : '${path}/groupBoard/idCheck.do?t_group_id='+ encodeURIComponent(t_group_id),
				type : 'get',
				success : function(data) {
					
					if (data == 1) {
							// 1 : 아이디가 중복되는 문구
							$("#id_check").text("<spring:message code="en.01" text="defaul text"/>");
							$("#id_check").css("color", "red");
// 							$("#addBtn").attr("disabled", true);
						} else {
							
							if(idJ.test(t_group_id)){
								// 0 : 아이디 길이 / 문자열 검사
								$("#id_check").text("");
// 								$("#addBtn").attr("disabled", false);
					
							} else if(t_group_id == ""){
								
								$('#id_check').text("<spring:message code="en.003" text="defaul text"/>");
								$('#id_check').css('color', 'red');
// 								$("#addBtn").attr("disabled", true);				
								
							} else {
								
								$('#id_check').text("<spring:message code="en.003" text="defaul text"/>");
								$('#id_check').css('color', 'red');
// 								$("#addBtn").attr("disabled", true);
							}
							
						}
					}, error : function() {
							console.log("실패"); 
					}
				});
			});
			$("#t_group_name").on("propertychange change keyup paste input" ,function() {
				// id = "id_reg" / name = "userId"
				var t_group_name = $('#t_group_name').val();
				$.ajax({
					url : '${path}/groupBoard/t_group_name.do?t_group_name='+ encodeURIComponent(t_group_name),
					type : 'get',
					success : function(data) {
						if (data == 1) {
								// 1 : 아이디가 중복되는 문구
								$("#t_group_name_check").text("<spring:message code="en.12" text="defaul text"/>");
								$("#t_group_name_check").css("color", "red");
// 								$("#addBtn").attr("disabled", true);
							} else {
								
								if(GroupNameJ.test(t_group_name)){
									// 0 : 아이디 길이 / 문자열 검사
									$("#t_group_name_check").text("");
// 									$("#addBtn").attr("disabled", false);
						
								} else if(t_group_name == ""){
									
									$('#t_group_name_check').text('<spring:message code="en.11" text="defaul text"/>');
									$('#t_group_name_check').css('color', 'red');
// 									$("#addBtn").attr("disabled", true);				
									
								} else {
									
									$('#t_group_name_check').text("<spring:message code="en.11" text="defaul text"/>");
									$('#t_group_name_check').css('color', 'red');
// 									$("#addBtn").attr("disabled", true);
								}
								
							}
						}, error : function() {
								console.log("실패"); 
						}
					});
				});
			
			//시공사에 특수문자 들어가지 않도록 설정
			$("#t_group_builder").on("propertychange change keyup paste input" ,function() {
			if (nameJ.test($(this).val())) {
					$("#builder_check").text('');
// 					$("#addBtn").attr("disabled", false);
			} else {
				$('#builder_check').text('<spring:message code="en.15" text="defaul text"/>');
				$('#builder_check').css('color', 'red');
// 				$("#addBtn").attr("disabled", true);
			}
		});	
			//소유주에 특수문자 들어가지 않도록 설정
			$("#t_group_owner").on("propertychange change keyup paste input" ,function() {
			if (nameJ.test($(this).val())) {
					$("#owner_check").text('');
// 					$("#addBtn").attr("disabled", false);
			} else {
				$('#owner_check').text('<spring:message code="en.16" text="defaul text"/>');
				$('#owner_check').css('color', 'red');
// 				$("#addBtn").attr("disabled", true); 
			}
		});	
			//담당자에 특수문자 들어가지 않도록 설정
			$("#t_group_manager").on("propertychange change keyup paste input" ,function() {
			if (nameJ.test($(this).val())) {
					$("#manager_check").text('');
// 					$("#addBtn").attr("disabled", false);
			} else {
				$('#manager_check').text('<spring:message code="en.17" text="defaul text"/>');
				$('#manager_check').css('color', 'red');
// 				$("#addBtn").attr("disabled", true);
			}
		});	
			//관리인에 특수문자 들어가지 않도록 설정
			$("#t_group_username").on("propertychange change keyup paste input" ,function() {
			if (nameJ.test($(this).val())) {
					$("#username_check").text('');
// 					$("#addBtn").attr("disabled", false);
			} else {
				$('#username_check').text('<spring:message code="en.18" text="defaul text"/>');
				$('#username_check').css('color', 'red');
// 				$("#addBtn").attr("disabled", true);
			}
		});	
			
			// 휴대전화
			$('#t_group_phone').on("propertychange change keyup paste input" ,function(){
				if(numberJ.test($(this).val())){
					$("#phone_check").text('');
// 					$("#addBtn").attr("disabled", false);
				} else {
					$('#phone_check').text('<spring:message code="en.07" text="defaul text"/>');
					$('#phone_check').css('color', 'red');
// 					$("#addBtn").attr("disabled", true);
				}
			});
			// 휴대전화
			$('#t_group_ip').on("propertychange change keyup paste input" ,function(){
				if(ipJ.test($(this).val())){
					$("#ip_check").text('');
// 					$("#addBtn").attr("disabled", false);
				} else {
					$('#ip_check').text('<spring:message code="en.19" text="defaul text"/>');
					$('#ip_check').css('color', 'red');
// 					$("#addBtn").attr("disabled", true);
				}
			});
			
			$('#signUpUserCompanyAddress').blur(function(){
				if(empJ.test($(this).val())){
					$("#addr2_check").text('');
// 					$("#addBtn").attr("disabled", false);
				} else {
					$('#addr2_check').text('<spring:message code="en.09" text="default text"></spring:message>');
					$('#addr2_check').css('color', 'red');
// 					$("#addBtn").attr("disabled", true);
				}
			});
			// 상세주소
			$('#t_group_addr2').on("propertychange change keyup paste input" ,function(){
				if(addrJ.test($(this).val())){
					$("#detailaddr_check").text('');
// 					$("#addBtn").attr("disabled", false);
				} else {
					$('#detailaddr_check').text('<spring:message code="en.010" text="default text"></spring:message>');
					$('#detailaddr_check').css('color', 'red');
// 					$("#addBtn").attr("disabled", true); 
				}
			});		
			
		</script>

       <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	   <script type="text/javascript">
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
	                if(data.buildingName != '' && data.apartment === 'Y'){
	                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                }
	                
	                // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                if(extraRoadAddr != '' ){
	                
	                    extraRoadAddr = ' (' + extraRoadAddr + ')';
	                }
	                // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
	                if(fullRoadAddr !== ''){
	                    fullRoadAddr += extraRoadAddr;
	                }
	                // 우편번호와 주소 정보를 해당 필드에 넣는다.  
// 	                console.log(data.zonecode);
// 	                console.log(fullRoadAddr);
	                
	                
	                $("[name=signUpUserPostNo]").val(data.zonecode);
	                $("[name=t_group_addr]").val(fullRoadAddr);
	                
	                /* document.getElementById('signUpUserPostNo').value = data.zonecode; //5자리 새우편번호 사용
	                document.getElementById('signUpUserCompanyAddress').value = fullRoadAddr;
	                document.getElementById('signUpUserCompanyAddressDetail').value = data.jibunAddress; */
	            }
	 		}).open();
	 	}
		
		//스크립트 시작할 것.
	 </script>