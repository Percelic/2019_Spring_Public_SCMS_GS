<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ include file="/WEB-INF/views/include/common.jsp"%>
	<script type="text/javascript">
	$(document).ready(function () {
		  $("#btnUpdate").click(function(){
				var t_group_name = $("#t_group_name").val();// 군집 명
				var t_group_builder = $("#t_group_builder").val();// 시공사
				var t_group_owner = $("#t_group_owner").val();//소유주
				var t_group_phone = $("#t_group_phone").val();//핸드폰 번호
				var t_group_manager = $("#t_group_manager").val();//관리인 번호
				var t_group_username = $("#t_group_username").val();//관리인
				var t_group_ip = $("#t_group_ip").val();			//IP
				var signUpUserCompanyAddress = $("#signUpUserCompanyAddress").val();
	            var t_group_addr2 = $("#t_group_addr2").val();
	            
	           if(t_group_name == "" || !GroupNameJ.test(t_group_name)){
						alert("<spring:message code='en.group_03' text='def'/>");
						$("#t_group_name").focus();
						return false;
				}else	if(t_group_builder == "" || !nameJ.test(t_group_builder)){
					alert("<spring:message code='en.group_04' text='def'/>");
					$("#t_group_builder").focus();
					return false;
				}else if(t_group_owner == "" || !nameJ.test(t_group_owner)){
					alert("<spring:message code='en.group_05' text='def'/>");
					$("#t_group_owner").focus();
					return false;
				}else	if(t_group_phone == "" || !numberJ.test(t_group_phone)){
					alert("<spring:message code='en.group_06' text='def'/>");
					$("#t_group_phone").focus();
					return false;
				}else if(t_group_manager == "" || !nameJ.test(t_group_manager)){
					alert("<spring:message code='en.group_07' text='def'/>");
					$("#t_group_manager").focus();
					return false;
				}else if(signUpUserCompanyAddress.length == 0){
					 alert("<spring:message code='en.group_10' text='def'/>");
	        		   $("#signUpUserCompanyAddress").focus();
	        		   return false;
				}else if(t_group_addr2 == "" || !addrJ.test(t_group_addr2)){
					  alert("<spring:message code='en.group_11' text='def'/>");
	        		   $("#t_group_addr2").focus();
	        		   return false;
				}else if(t_group_username == "" || !nameJ.test(t_group_username)){
					alert("<spring:message code='en.group_08' text='def'/>");
					$("#t_group_username").focus();
					return false;
				}else if(t_group_ip == "" || !ipJ.test(t_group_ip)){
					alert("<spring:message code='en.group_09' text='def'/>");
					$("#t_group_username").focus();
					return false;
				}
	            
	            
			  //확인 대화 상자
			  if(confirm("<spring:message code='en.modify' text='def'/>")){
				  alert("<spring:message code='en.modify_success' text='def'/>")
				  document.form1.action="${path}/groupBoard/Group_Update.do";
				  document.form1.submit();
			  }
		  });
		
	});
	</script>
	<!-- top navigation -->
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
        <!-- /top navigation -->
        <!-- page content -->
        <div class="right_col" role="main">
          <div class="">
          <div class="page-title">
              <div class="title_left">
                <h3><spring:message code="group.view" text="defaul text"></spring:message></h3>
              </div> 
				<div class="clearfix"></div>
				
                  </div>
            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
            
                  
                  <form name="form1" id="form1" action="${path}/groupBoard/Group_Update.do"   method="post" data-parsley-validate class="form-horizontal form-label-left">
                       <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                      
                     <div class="form-group col-md-4">
      					<label for="inputEmail4"><spring:message code="group.b2" text="defaul text"></spring:message>&nbsp;*</label>
      					<input name="t_group_id" id="t_group_id" class="form-control"  placeholder="ID" 
      					value="${dto.t_group_id}" readonly="readonly"/> 
    						</div>
    						
                           <div class="form-group col-md-4">
      					<label for="inputEmail4"><spring:message code="group.b1" text="defaul text"></spring:message>&nbsp;*</label>
      					<input name="t_group_name" id="t_group_name" class="form-control"  placeholder="<spring:message code="group.b1" text="defaul text"/>" maxlength="30" 
      					value="${dto.t_group_name}"/>
      					<div class="check_font" id="group_name_check"></div>
    						</div>
    						
                 	<div class="form-group col-md-4">
      					<label for="inputEmail4"><spring:message code="group.b10" text="defaul text"></spring:message>&nbsp;*</label>
      					<input name="t_group_builder" id="t_group_builder" class="form-control" placeholder="<spring:message code="group.b10" text="defaul text"/>" maxlength="12"
      					value="${dto.t_group_builder}"/>
						<div class="check_font" id="builder_check"></div>	
    						</div>
    						
    				<div class="form-group col-md-12"></div> 		
    						
    				<div class="form-group col-md-4">
      					<label for="inputEmail4"><spring:message code="group.b9" text="defaul text"></spring:message>&nbsp;*</label>
      					<input name="t_group_owner" id="t_group_owner" class="form-control" placeholder="<spring:message code="group.b9" text="defaul text"/>" maxlength="12"
      					value="${dto.t_group_owner}">
      					<div class="check_font" id="owner_check"></div>
  			 	</div>
  			 	
                        <div class="form-group col-md-4">
      					<label for="inputEmail4"><spring:message code="group.b4" text="defaul text"></spring:message>&nbsp;*</label>
      					<input name="t_group_phone" id="t_group_phone" class="form-control" placeholder="<spring:message code="group.b4" text="defaul text"/>" maxlength="11" 
      					value="${dto.t_group_phone}">
      					<div class="check_font" id="phone_check"></div>
    						</div>
                 		
                       <div class="form-group col-md-4">
      					<label for="inputEmail4"><spring:message code="group.b3" text="defaul text"></spring:message>&nbsp;*</label>
      					<input name="t_group_manager" id="t_group_manager" class="form-control"  placeholder="<spring:message code="group.b3" text="defaul text"/>" maxlength="12" 
      					value="${dto.t_group_manager}">
    					<div class="check_font" id="manager_check"></div>	
    						</div>
    						
    			<div class="form-group col-md-12"></div> 			
    					
    			  <div class="form-group col-md-12">
                       <label style="display: block;" ><spring:message code="group.a7" text="defaul text"></spring:message></label>
                       <input class="form-control" style="width: 40%; display: inline;" placeholder="<spring:message code="group.a7" text="defaul text"/>" name="signUpUserPostNo" id="signUpUserPostNo" type="text" readonly="readonly" >
                       <button type="button" class="btn btn-default" onclick="execPostCode();" style="margin-bottom: 5px;">
                            		<i class="fa fa-search"></i> <spring:message code="user.a12" text="defaul text"/></button>
                            	</div>
                 
                 <div class="form-group col-md-12"></div> 
                            	
                  <div class="form-group col-md-4">
                        <label for="inputEmail4"><spring:message code="group.a08" text="defaul text"></spring:message>&nbsp;*</label>
                        <input class="form-control"  style="top: 5px;" placeholder="<spring:message code="group.a08" text="defaul text"/>" name="t_group_addr" id="signUpUserCompanyAddress" type="text" readonly="readonly" 
                            		value="${dto.t_group_addr}" />
                            	</div>
                            	 	<div class="form-group col-md-4">
                            	 	<label for="inputEmail4"><spring:message code="group.a09" text="defaul text"></spring:message>&nbsp;*</label>
                            		<input class="form-control" placeholder="<spring:message code="group.a09" text="defaul text"/>" name="t_group_addr2" id="t_group_addr2" type="text" maxlength="200"
                            		value="${dto.t_group_addr2}"  />
                            		<div class="check_font" id="addr3_check"></div>
                            	</div>
                                                       	
    					
                      <div class="form-group col-md-4">
      					<label for="inputEmail4"><spring:message code="group.b8" text="defaul text"></spring:message>&nbsp;*</label>
      					<input name="t_group_username" id="t_group_username" class="form-control"   placeholder="<spring:message code="group.b8" text="defaul text"/>" maxlength="12"
      					value="${dto.t_group_username}">
      					<div class="check_font" id="username_check"></div>
    						</div>
    				   
                      <div class="form-group col-md-12"></div> 
    						
                
                       <div class="form-group col-md-12">
      					<label for="inputEmail4"><spring:message code="group.b16" text="defaul text"></spring:message>&nbsp;*</label>
      					<input name="t_group_ip" id="t_group_ip" class="form-control"  maxlength="15" 
      					value="${dto.t_group_ip}">
      					<div class="check_font" id="ip_check"></div>
    						</div>
    					
    					<div class="form-group col-md-12"></div> 	
    					         
                     <div class="form-group col-md-6">
      					<label for="inputEmail4"><spring:message code="group.b11" text="defaul text"></spring:message></label>
      					<input  class="form-control" id="tracker_cnt"  placeholder="ID" readonly="readonly" 
      					value="${dto.tracker_cnt}" />
    						</div>
                     <div class="form-group col-md-6 ">
      					<label for="inputEmail4"><spring:message code="group.b12" text="defaul text"></spring:message></label>
      					<input  class="form-control" id="inverter_cnt"  placeholder="ID" readonly="readonly" 
      					value="${dto.inverter_cnt}"/>
    						</div>
    						 
    						 <div class="form-group col-md-12"></div> 
            
                      
                        <div class="form-group col-md-12 ">
                        <label for="inputEmail4"><spring:message code="group.b13" text="defaul text"></spring:message></label>
                       <textarea class="form-control col-sm-5" id="history" name="history"   rows="5" maxlength="10000">${dto.history}</textarea> 
                       <div class="check_font" id="history_check"></div> 
                      </div>
            
            
			        <div class="ln_solid"></div>
                      <div class="form-group">
                        <div class="col-md-12">
                          <input class="btn btn-primary" type="button" value="<spring:message code="user.a18" text="defaul text"/>" id="btnUpdate"/>
                            <a onClick="location.href='${path}/groupBoard/Group_Board'" class="btn btn-primary"><spring:message code="user.a19" text="defaul text"/></a>
                            
                        </div>
                      </div>
					 </form>
                  </div>
                </div>
              </div> 
            </div>
        <!-- /주소 -->
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
	                $("[name=t_group_addr]").val(fullRoadAddr);
	                
	                /* document.getElementById('signUpUserPostNo').value = data.zonecode; //5자리 새우편번호 사용
	                document.getElementById('signUpUserCompanyAddress').value = fullRoadAddr;
	                document.getElementById('signUpUserCompanyAddressDetail').value = data.jibunAddress; */
	            }
	 		}).open();
	 	}
		
		//스크립트 시작할 것.
	 </script>
	
	<script type="text/javascript">
		//모든 공백 체크 정규식
		var empJ = /\s/g;
		//아이디 정규식
		var idJ = /^[0-9]{1,3}$/;
		// 비밀번호 정규식
		var pwJ = /^[A-Za-z0-9]{4,12}$/; 
		// 이름 정규식
		var nameJ = /^[가-힣a-zA-Z]{2,12}$/;
		// 군집이름 정규식
		var GroupNameJ = /^[가-힣a-zA-Z-_0-9]{2,30}$/;
		// 이메일 검사 정규식
		var mailJ = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		// 휴대폰 번호 정규식
		var phoneJ = /^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/;
		// IP 정규식
		var ipJ =  /^(1|2)?\d?\d([.](1|2)?\d?\d){3}$/;
		
		//숫자만 
		var numberJ = /^[0-9]{1,11}$/;
		var addrJ = /^[가-힣0-9a-zA-Z ]{1,200}$/;
		
		//발전소명에 특수문자 들어가지 않도록 설정
		$("#t_group_name").on("propertychange change keyup paste input",function() {
			if (GroupNameJ.test($(this).val())) {
					$("#group_name_check").text('');
			} else {
				$('#group_name_check').text('<spring:message code="en.11" text="defaul text"></spring:message>');
				$('#group_name_check').css('color', 'red');
			}
		});	
		
		//시공사에 특수문자 들어가지 않도록 설정
		$("#t_group_builder").on("propertychange change keyup paste input",function() {
		if (nameJ.test($(this).val())) {
				$("#builder_check").text('');
		} else {
			$('#builder_check').text('<spring:message code="en.15" text="defaul text"></spring:message>');
			$('#builder_check').css('color', 'red');
		}
	});	
		//소유주에 특수문자 들어가지 않도록 설정
		$("#t_group_owner").on("propertychange change keyup paste input",function() {
		if (nameJ.test($(this).val())) {
				$("#owner_check").text('');
		} else {
			$('#owner_check').text('<spring:message code="en.16" text="defaul text"></spring:message>');
			$('#owner_check').css('color', 'red');
		}
	});	
		//담당자에 특수문자 들어가지 않도록 설정
		$("#t_group_manager").on("propertychange change keyup paste input",function() {
		if (nameJ.test($(this).val())) {
				$("#manager_check").text('');
		} else {
			$('#manager_check').text('<spring:message code="en.17" text="defaul text"></spring:message>');
			$('#manager_check').css('color', 'red');
		}
	});	
		//관리인에 특수문자 들어가지 않도록 설정
		$("#t_group_username").on("propertychange change keyup paste input",function() {
		if (nameJ.test($(this).val())) {
				$("#username_check").text('');
		} else {
			$('#username_check').text('<spring:message code="en.18" text="defaul text"></spring:message>');
			$('#username_check').css('color', 'red');
		}
	});	
		
		// 휴대전화
		$('#t_group_phone').on("propertychange change keyup paste input",function() {
			if(numberJ.test($(this).val())){
				$("#phone_check").text('');
			} else {
				$('#phone_check').text('<spring:message code="en.07" text="defaul text"></spring:message>');
				$('#phone_check').css('color', 'red');
			}
		});
		// IP
		$('#t_group_ip').on("propertychange change keyup paste input",function() {
			if(ipJ.test($(this).val())){
				$("#ip_check").text('');
			} else {
				$('#ip_check').text('<spring:message code="en.19" text="defaul text"></spring:message>');
				$('#ip_check').css('color', 'red');
			}
		});
		
		$('#signUpUserCompanyAddress').on("propertychange change keyup paste input",function() {
			if(empJ.test($(this).val())){
				$("#addr2_check").text('');
			} else {
				$('#addr2_check').text('<spring:message code="en.09" text="default text"></spring:message>');
				$('#addr2_check').css('color', 'red');
			}
		});
		// 상세주소
		$('#t_group_addr2').on("propertychange change keyup paste input",function() {
			if(addrJ.test($(this).val())){
				$("#addr3_check").text('');
			} else {
				$('#addr3_check').text('<spring:message code="en.010" text="default text"></spring:message>');
				$('#addr3_check').css('color', 'red');
			}
		});
		
	 </script>