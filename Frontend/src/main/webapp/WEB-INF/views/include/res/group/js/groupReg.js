$(document).ready(function(){
	$('.leftmenu').find('div[data-sidebar-name="manage"]').addClass("active");

	$(".imgPreviewForm").hide();
});

var isTrackerGroupId = false;
var isTrackerGroupName = false;

// 아이디 중복확인
$("#inputForm").on('click', 'input[data-groupid-btn]', function(){
	var form = $("#inputForm");
	var trackerGroupId = form.find('input[name="trackerGroupId"]');
	groupCheck(trackerGroupId, "trackerGroupId", "군집 아이디", isTrackerGroupId);
	
// 이름 중복확인	
}).on('click', 'input[data-groupname-btn]', function(){
	var form = $("#inputForm");
	var trackerGroupName = form.find('input[name="trackerGroupName"]');
	groupCheck(trackerGroupName, "trackerGroupName", "군집 이름", isTrackerGroupName);
	
// 주소 검색	
}).on('click', 'input[data-address-btn]', function(){
	addressPopup();

}).on('click', 'input[name="address1"]', function(){
	addressPopup();

});


//아이디 중복확인
$("#inputForm").on('keydown', 'input[name="trackerGroupId"]', function(){
	groupKeyDown("trackerGroupId");
}).on('keydown', 'input[name="trackerGroupName"]', function(){
	groupKeyDown("trackerGroupName");
});




// 이미지 등록
$('.imgForm').on('click', 'input[data-imgreg-btn]', function(event){
	$('.imgForm').find('input[name="imgFile"]').click();
}).on('change', 'input[name="imgFile"]', function(event){
	groupImagePrewiew($(this));
});

// 파일제거
$('.imgPreviewForm').on('click', 'input[data-imgdel-btn]', function(){
	groupImageDel();
});


//취소
$(".btnRec").on('click', 'button[data-cancel-btn]', function(){
	history.back();

// 등록
}).on('click', 'button[data-reg-btn]', function(){
	if(true == regCheck()){
		reg($(this).data('reg-btn'));
	}
});

//군집제어 알고리즘 클릭
$(".btnControl").on('click', controlModeSet);

//KEY 이벤트 처리
$("#inputForm").on('keyup', 'input[name="trackerGroupId"]', function(){
	var regExp = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]|\s|\//g;
	$(this).val($(this).val().replace(regExp, ''));
	isTrackerGroupId = false;
	
}).on('keyup', 'input[name="phone"]', function(event){
	
	var pattern = /[^(0-9)|\-]/gi;   // 숫자, - 이외는 제거
	 
	if(pattern.test($(this).val())){
		$(this).val($(this).val().replace(pattern, ''));
	}
})

//군집제어 설정
function controlModeSet(){
	var el = this;
	
	var enableBl = "N";
	
	if($(el).hasClass('dis')){
		enableBl = "Y";
		$(el).prev().css({"display": "inline-block"});
		$(el).css({"display": "none"});
	}else{
		$(el).next().css({"display": "inline-block"});
		$(el).css({"display": "none"});
	}
	
	var controlInput = $(el.closest('.btnControlRec')).find('input');
	$(controlInput).val(enableBl);
	
	console.log($(controlInput).attr('name'), $(controlInput).val());
}

//아이디 입력값 체크
function groupKeyDown(name){
	if("trackerGroupId" == name){
		isTrackerGroupId = false;
		$('.groupIdDesc').text('군집 아이디 중복확인이 필요합니다.');
	} else {
		isTrackerGroupName = false;
		$('.groupNameDesc').text('군집 이름 중복확인이 필요합니다.');
	}
	
}


// 군집 중복 확인
function groupCheck(input, name, dsec){
	if("trackerGroupId" == name){
		$('.trackerGroupIdDesc').removeClass('text-success');
	} else {
		$('.trackerGroupNameDesc').removeClass('text-success');
	}

	input.val(input.val().trim());
	
	if("" == input.val()) {
		input.addClass('input-warning');
		$('.' + name + 'Desc').text(dsec + '을(를) 입력하여 주십시오.');
		return false;
	}
	
	if("trackerGroupId" == name){
		var pattern = /\s/g;
		if(input.val().match(pattern)) { 
			alert(dsec + "에는 공백을 사용할 수 없습니다.");
			input.focus();
			return false;
		}
	}
    var params = name + "=" + input.val();  

	$.ajax({
		type:"GET",
		url	: _cp + '/group/groupCheck',
		data: params,
		success:function(data) {
			if(data > 0){
				if("trackerGroupId" == name){
					isTrackerGroupId = false;
					$('.trackerGroupIdDesc').text('사용중인 군집 아이디 입니다.');
					input.removeClass('input-warning');
					input.addClass('input-warning');
				} else {
					isTrackerGroupName = false;
					$('.trackerGroupNameDesc').text('사용중인 군집 이름 입니다.');
					input.removeClass('input-warning');
					input.addClass('input-warning');
				}
			} else {
				if("trackerGroupId" == name){
					isTrackerGroupId = true;
					$('.trackerGroupIdDesc').text('사용가능한 아이디 입니다.');
					input.removeClass('input-warning');
					$('.trackerGroupIdDesc').addClass('text-success');

				} else {
					isTrackerGroupName = true;
					$('.trackerGroupNameDesc').text('사용가능한 군집 이름 입니다.');
					input.removeClass('input-warning');
					$('.trackerGroupNameDesc').addClass('text-success');
				}
			}
		}, error:function(error) {
			
		}
	});
}




// 주소 팝업 호출
function addressPopup() {
	var csrf = $('[name="_csrf"]').val();
	var pop = window.open(_cp + "/popup/juso?_csrf="+csrf,"juso_pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	
}

// 주소 API의 리턴값
function jusoCallBack(roadFullAddr, roadAddrPart1, addrDetail, siNm){
	codeAddress(roadAddrPart1);
	areaCode(siNm);
	var form = $("#inputForm");
	form.find('input[name="address1"]').val(roadAddrPart1);
	form.find('input[name="address2"]').val(addrDetail);
}

// 주소 코드 변환
function areaCode(areaName){
	var areaCode = 0;
	
	if("서울특별시" == areaName) {
		areaName = "경기도";
	} else if("부산광역시" == areaName) {
		areaName = "경상남도";
	} else if("대구광역시" == areaName) {
		areaName = "경상북도";
	} else if("인천광역시" == areaName) {
		areaName = "경기도";
	} else if("광주광역시" == areaName) {
		areaName = "전라남도";
	} else if("대전광역시" == areaName) {
		areaName = "충청남도";
	} else if("울산광역시" == areaName) {
		areaName = "경상남도";
	} else if("세종특별자치시" == areaName) {
		areaName = "충청남도";
	}
	
	
	if("서울특별시" == areaName) {
		areaCode = 02;
	} else if("부산광역시" == areaName) {
		areaCode = 51;
	} else if("대구광역시" == areaName) {
		areaCode = 53;
	} else if("인천광역시" == areaName) {
		areaCode = 52;
	} else if("광주광역시" == areaName) {
		areaCode = 62;
	} else if("대전광역시" == areaName) {
		areaCode = 42;
	} else if("울산광역시" == areaName) {
		areaCode = 52;
	} else if("세종특별자치시" == areaName) {
		areaCode = 44;
	} else if("경기도" == areaName) {
		areaCode = 31;
	} else if("강원도" == areaName) {
		areaCode = 33;
	} else if("충청북도" == areaName) {
		areaCode = 43;
	} else if("충청남도" == areaName) {
		areaCode = 41;
	} else if("전라북도" == areaName) {
		areaCode = 63;
	} else if("전라남도" == areaName) {
		areaCode = 61;
	} else if("경상북도" == areaName) {
		areaCode = 54;
	} else if("경상남도" == areaName) {
		areaCode = 55;
	} else if("제주특별자치도" == areaName) {
		areaName = "제주도"
		areaCode = 64;
	}
		
	
	var form = $("#inputForm");
	form.find('input[name="areaCode"]').val(areaCode);
	form.find('input[name="areaName"]').val(areaName);
}

// 주소 위도 경도 변환
function codeAddress(address, errCnt) {
	console.log("address : ", address);
	geocoder = new google.maps.Geocoder();
	geocoder.geocode( { 'address': address}, function(results, status) {
		if (status == google.maps.GeocoderStatus.OK) {

			var lat = results[0]['geometry']['location']['lat']();
			var lng = results[0]['geometry']['location']['lng']();
			var form = $("#inputForm");
			var latArr = String(lat).split(".");
			var lngArr = String(lng).split(".");
			var latVal = latArr[0] + "." + latArr[1].substring(0, 6);
			var lngVal = lngArr[0] + "." + lngArr[1].substring(0, 6);
//			var latVal = latArr[0] + "." + latArr[1];
//			var lngVal = lngArr[0] + "." + lngArr[1];
			form.find('input[name="locLat"]').val(latVal);

			
			form.find('input[name="locLng"]').val(lngVal);
			
		} else {
			if(!errCnt || errCnt < 3){
				var idx = address.lastIndexOf(' ');
				if(idx > -1){
					var add = address.substring(0, idx);
					console.warn(`# get geo address fail retry : ${address} -> ${add}`);
					
					codeAddress(add, (errCnt || 0) + 1);
					return;
				}
			}
			alert('위도 경도 조회 실패: ' + status);
		}
	});
}

function regCheck(){
	var isCheck = true;
	var form = $("#inputForm");
	form.find('p').text('');
	$('.input-warning').removeClass('input-warning');
	$('.trackerGroupIdDesc').removeClass('text-success');
	$('.trackerGroupNameDesc').removeClass('text-success');
	
	var trackerGroupId = form.find('input[name="trackerGroupId"]');
	if("" == trackerGroupId.val()) {
		trackerGroupId.addClass('input-warning');
		$('.trackerGroupIdDesc').text('군집 아이디를 입력하여 주십시오.');
		isCheck = false;
	}
	
	if((false == isTrackerGroupId) &&  ("" != trackerGroupId.val())){
		trackerGroupId.addClass('input-warning');
		$('.trackerGroupIdDesc').text('군집 아이디 중복확인을 하여 주십시오.');
		isCheck = false;
	}
	
	var trackerGroupName = form.find('input[name="trackerGroupName"]');
	if("" == trackerGroupName.val()) {
		trackerGroupName.addClass('input-warning');
		$('.trackerGroupNameDesc').text('군집 이름을 입력하여 주십시오.');
		isCheck = false;
	}
	if((false == isTrackerGroupName) &&  ("" != trackerGroupName.val())){
		trackerGroupName.addClass('input-warning');
		$('.trackerGroupNameDesc').text('군집 이름 중복확인을 하여 주십시오.');
		isCheck = false;
	}
	
	var address1 = form.find('input[name="address1"]');
	if("" == address1.val()) {
		address1.addClass('input-warning');
		$('.address1Desc').text('주소(위치)를 입력하여 주십시오.');
		isCheck = false;
	}
	
	var username = form.find('input[name="username"]');
	if("" == username.val()) {
		username.addClass('input-warning');
		$('.usernameDesc').text('담당자를 입력하여 주십시오.');
		isCheck = false;
	}
	
	var phone = form.find('input[name="phone"]');
	if("" == phone.val()) {
		phone.addClass('input-warning');
		$('.phoneDesc').text('연락처를 입력하여 주십시오.');
		isCheck = false;
	}
	
//	var regExp = /^\d{3}-\d{3,4}-\d{4}$/;
//	if ((!regExp.test(phone.val())) && ("" != phone.val())) {
//		phone.addClass('input-warning');
//		$('.phoneDesc').text('잘못된 휴대폰 번호입니다. 숫자, - 를 포함한 숫자만 입력하여주십시오.');
//		isCheck = false;
//	}
	
	var manager = form.find('input[name="manager"]');
	if("" == manager.val()) {
		manager.addClass('input-warning');
		$('.managerDesc').text('관리인을 입력하여 주십시오.');
		isCheck = false;
	}
	
	var owner = form.find('input[name="owner"]');
	if("" == owner.val()) {
		owner.addClass('input-warning');
		$('.ownerDesc').text('소유주를 입력하여 주십시오.');
		isCheck = false;
	}
	
	var builder = form.find('input[name="builder"]');
	if("" == builder.val()) {
		builder.addClass('input-warning');
		$('.builderDesc').text('시공사를 입력하여 주십시오.');
		isCheck = false;
	}	
	
	var installDate = form.find('input[name="installDate"]');
	if("" == installDate.val()) {
		installDate.addClass('input-warning');
		$('.installDateDesc').text('설치일자를 입력하여 주십시오.');
		isCheck = false;
	}
	
//	그룹제어알고리즘 값 변경
	if(isCheck){
		form.find('[name="avaControlMode"]').val(form.find('[name="avaControlMode"]').val() == "Y" ? true: false);
		form.find('[name="shadowControlMode"]').val(form.find('[name="shadowControlMode"]').val() == "Y" ? true: false);
		form.find('[name="sunsetControlMode"]').val(form.find('[name="sunsetControlMode"]').val() == "Y" ? true: false);
		form.find('[name="snowControlMode"]').val(form.find('[name="snowControlMode"]').val() == "Y" ? true: false);
		form.find('[name="windControlMode"]').val(form.find('[name="windControlMode"]').val() == "Y" ? true: false);
	}
	
	return isCheck;
	
}


function reg(regType){

	var imgFile = $('#inputForm').find('input[name="imgFile"]').val();
	
	if("" == imgFile){
		var params = $("#inputForm").serialize();
		
		$.ajax({
			type:"POST",
			url	: _cp + '/group/reg',
			data: params,
			success:function() {
				if("listmove" == regType){
					alert('군집 등록에 성공 하였습니다.');
					location.replace(_cp + '/group/list'); 
				} else {
					alert('군집 등록에 성공 하였습니다.');
					var form = $("#inputForm");
					var trackerGroupId = form.find('input[name="trackerGroupId"]');
					var trackerGroupName = form.find('input[name="trackerGroupName"]');
					
					isTrackerGroupId = false;
					trackerGroupId.val('');

					isTrackerGroupName = false;
					trackerGroupName.val('');
				}
			}, error:function(error) {
				alert('군집 등록에 실패 하였습니다.');
			}
		});
	} else {
		
	    $('#inputForm').ajaxForm({
			type:"POST",
			url	: _cp + '/group/reg/file',
			enctype : "multipart/form-data",
			beforeSend : function(xhr) {
				 xhr.setRequestHeader('X-CSRF-Token', $('[name="_csrf"]').val());
			}, success: function(response,status){
				if("listmove" == regType){
					alert('군집 등록에 성공 하였습니다.');
					location.replace(_cp + '/group/list'); 
				} else {
					alert('군집 등록에 성공 하였습니다.');
					var form = $("#inputForm");
					var trackerGroupId = form.find('input[name="trackerGroupId"]');
					var trackerGroupName = form.find('input[name="trackerGroupName"]');
					
					isTrackerGroupId = false;
					trackerGroupId.val('');

					isTrackerGroupName = false;
					trackerGroupName.val('');
				}
	        }, error: function(){
				alert('군집 등록에 실패 하였습니다.');
	        }                               
	    }).submit();
	}
}

function groupImageDel(){
	var form = $("#inputForm");
	$('.imgFileDesc').text('');
	var imgFileText = form.find('input[name="imgFileText"]');
	imgFileText.removeClass('input-warning');
	imgFileText.val('');
	form.find('input[name="imgFile"]').val('');
	$(".imgPreviewForm").hide();
}


function groupImagePrewiew($this){
	var form = $("#inputForm");
	$('.imgFileDesc').text('');
	var imgFileText = form.find('input[name="imgFileText"]');
	imgFileText.removeClass('input-warning');


	var file = $this[0].files[0];
	var filename = file.name;
	var extension = filename.slice(filename.indexOf(".") + 1).toLowerCase();

	if((extension != "jpg") && (extension != "jpeg") && (extension != "gif") && (extension != "png")){
		imgFileText.addClass('input-warning');
		$('.imgFileDesc').text('이미지 파일(jpg, jpeg, gif, png)만 등록 가능합니다.');
		form.find('input[name="imgFile"]').val('');
		return;
	}
	
	form.find('input[name="imgFileText"]').val(form.find('input[name="imgFile"]').val());
	
	
	var _URL = window.URL || window.webkitURL;

	
	var img = new Image();
	img.src = _URL.createObjectURL(file);
	img.onload = function() {
			
		if (file) {
			var reader = new FileReader();

			reader.onload = function (e) {
				form.find('.imgPreview').attr('src', e.target.result);
			}
			
			reader.readAsDataURL(file);
		}
		
    }
	
	$(".imgPreviewForm").show();
}
