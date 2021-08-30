$(document).ready(function(){
	$('.leftmenu').find('div[data-sidebar-name="manage"]').addClass("active");
});


$(document).ready(function(){

	var file = new Image;
	file.src = $('.imgPreview').attr('src');

	if(!file.complete){
		$(".imgPreviewForm").hide();
	} else {
		$(".imgPreviewForm").show();
	}
});

var isTrackerGroupName = true;


// 취소
$(".btnRec").on('click', 'button[data-cancel-btn]', function(){
	history.back();

// 수정
}).on('click', 'button[data-modify-btn]', function(){
	if(true == groupModifyCheck()){
		groupModify();
	}

// 트래커 배치 리스트
}).on('click', 'button[data-tracker-rearrange]', function(){
	var trackerGroupId = $(this).data('tracker-rearrange');
	location.href = _cp + '/group/trackerRearrange/' + trackerGroupId;
}).on('click', 'button[data-inverterReg-btn]', function(){
	location.href = _cp + '/inverter/reg'
}).on('click', 'button[data-trackerReg-btn]', function(){
	location.href = _cp + '/tracker/reg'
});




// 군집명 중복확인
$("#inputForm").on('keydown', 'input[name="trackerGroupName"]', function(){
	groupKeyDown();
}).on('click', 'input[data-address-btn]', function(){
	addressPopup();
}).on('click', 'input[name="address1"]', function(){
	addressPopup();
});

//아이디 중복확인
$("#inputForm").on('click', 'input[data-groupname-btn]', function(){
	groupCheck();
});

//이미지 등록
$('.imgForm').on('click', 'input[data-imgreg-btn]', function(event){
	$('.imgForm').find('input[name="imgFile"]').click();
}).on('change', 'input[name="imgFile"]', function(event){
	groupImagePrewiew($(this));
});

//군집제어 알고리즘 클릭
$(".btnControl").on('click', controlModeSet);

// 파일제거
$('.imgPreviewForm').on('click', 'input[data-imgdel-btn]', function(){
	groupImageDel();
});

//주소 API의 리턴값
function jusoCallBack(roadFullAddr, roadAddrPart1, addrDetail, siNm){
	codeAddress(roadAddrPart1);
	areaCode(siNm);
	var form = $("#inputForm");
	form.find('input[name="address1"]').val(roadAddrPart1);
	form.find('input[name="address2"]').val(addrDetail);
}

//주소 팝업 호출
function addressPopup() {
	var csrf = $('[name="_csrf"]').val();
	var pop = window.open(_cp + "/popup/juso?_csrf="+csrf,"juso_pop","width=570,height=420, scrollbars=yes, resizable=yes");

}

//주소 코드 변환
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
function codeAddress(address) {

	geocoder = new google.maps.Geocoder();
	geocoder.geocode( { 'address': address}, function(results, status) {
		if (status == google.maps.GeocoderStatus.OK) {

			var lat = results[0]['geometry']['location']['lat']();
			var lng = results[0]['geometry']['location']['lng']();
			var form = $("#inputForm");
			var latArr = String(lat).split(".");
			form.find('input[name="locLat"]').val(latArr[0] + "." + latArr[1].substring(0, 6));

			var lngArr = String(lng).split(".");
			form.find('input[name="locLng"]').val(lngArr[0] + "." + lngArr[1].substring(0, 6));

		} else {
			alert('위도 경도 조회 실패: ' + status);
		}
	});
}

//군집 중복 확인
function groupCheck(){

	var form = $("#inputForm");
	var trackerGroupName = form.find('input[name="trackerGroupName"]');
	var hiddenTrackerGroupName = form.find('input[name="hiddenTrackerGroupName"]');

	if(trackerGroupName.val() != hiddenTrackerGroupName.val()) {

		if("" == trackerGroupName.val()) {
			trackerGroupName.addClass('input-warning');
			$('.trackerGroupNameDesc').text('군집 이름을 입력하여 주십시오.');
			return false;
		}

		$.ajax({
			type:"GET",
			url	: _cp + '/group/groupCheck',
			data: {trackerGroupName : trackerGroupName.val()},
			success:function(data) {
				if(data > 0){
					isTrackerGroupName = false;
					$('.trackerGroupNameDesc').text('사용중인 군집 이름 입니다.');
					trackerGroupName.removeClass('input-warning');
					trackerGroupName.addClass('input-warning');
					$('.trackerGroupNameDesc').addClass('text-warning');
				} else {
					isTrackerGroupName = true;
					$('.trackerGroupNameDesc').text('사용가능한 군집 이름 입니다.');
					$('.trackerGroupNameDesc').removeClass('text-warning');
					trackerGroupName.removeClass('input-warning');
				}
			}, error:function(error) {

			}
		});
	} else {
		isTrackerGroupName = true;
		$('.trackerGroupNameDesc').text('사용가능한 군집 이름 입니다.');
		$('.trackerGroupNameDesc').removeClass('text-warning');
		trackerGroupName.removeClass('input-warning');
	}
}


// 군집명 입력값 체크
function groupKeyDown(){
	isTrackerGroupName = false;
}

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

// 군집 수정 체크
function groupModifyCheck(){
	var isCheck = true;
	var form = $("#inputForm");
//	form.find('p').text('');
$('.input-warning').removeClass('input-warning');


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
	$('.phoneDesc').text('담당자를 입력하여 주십시오.');
	isCheck = false;
}

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


function groupModify(){

	var imgFile = $('#inputForm').find('input[name="imgFile"]').val();

	if("" == imgFile){


		var params = $("#inputForm").serialize();

		$.ajax({
			type:"POST",
			url	: _cp + '/group/modify',
			data: params,
			success:function() {
				alert('군집 수정에 성공 하였습니다.');
				location.replace(_cp + '/group/list');

			}, error:function(error) {
				alert('군집 수정에 실패 하였습니다.');
			}
		});

	} else {

		$('#inputForm').ajaxForm({
			type:"POST",
			url	: _cp + '/group/modify/file',
			enctype : "multipart/form-data",
			beforeSend : function(xhr) {
				xhr.setRequestHeader('X-CSRF-Token', $('[name="_csrf"]').val());
			}, success: function(response,status){
				alert('군집 수정에 성공 하였습니다.');
				location.replace(_cp + '/group/list');
			}, error: function(){
				alert('군집 등록에 실패 하였습니다.');
			}
		}).submit();
	}

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
			success: function(response,status){
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
