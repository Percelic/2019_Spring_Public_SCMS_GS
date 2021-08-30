<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ include file="/WEB-INF/views/include/common.jsp"%>
<!doctype html>
<html>
	<head>
		<title>태양광모니터링시스템</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />  
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <meta name="_csrf" content="${_csrf.token}"/>
		<meta name="_csrf_header" content="${_csrf.headerName}"/>
		
	 <!-- Bootstrap -->
    <link href="${vendors}/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="${vendors}/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="${vendors}/nprogress/nprogress.css" rel="stylesheet">
    <!-- iCheck -->
    <link href="${vendors}/iCheck/skins/flat/green.css" rel="stylesheet">
    <!-- bootstrap-wysiwyg -->
    <link href="${vendors}/google-code-prettify/bin/prettify.min.css" rel="stylesheet">
    <!-- Select2 -->
    <link href="${vendors}/select2/dist/css/select2.min.css" rel="stylesheet">
    <!-- Switchery -->
    <link href="${vendors}/switchery/dist/switchery.min.css" rel="stylesheet">
    <!-- starrr -->
    <link href="${vendors}/starrr/dist/starrr.css" rel="stylesheet">
    <!-- bootstrap-daterangepicker -->
    <link href="${vendors}/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="${build}/css/custom.min.css" rel="stylesheet">
    <link href="${build}/css/pup.css" rel="stylesheet">
    
	<script type="text/javascript" src="${path}/resources/js/comm.js"></script>
	<script type="text/javascript" src="${path}/resources/js/event.js"></script>
	
	</head>
	<body class="nav-md">
	<input type="hidden" id="comm_01" value="<spring:message code="comm.01" text="default text"/>" /><!-- 비밀번호변경 -->
	<input type="hidden" id="comm_02" value="<spring:message code="comm.02" text="default text"/>" /><!-- 현재비밀번호 -->
	<input type="hidden" id="comm_03" value="<spring:message code="comm.03" text="default text"/>" /><!-- 새 비밀번호 -->
	<input type="hidden" id="comm_04" value="<spring:message code="comm.04" text="default text"/>" /><!-- 새 비밀번호 확인 -->
	<input type="hidden" id="comm_05" value="<spring:message code="comm.05" text="default text"/>" /><!-- 수정 -->
	<input type="hidden" id="comm_06" value="<spring:message code="comm.06" text="default text"/>" /><!-- 닫기 -->
	<input type="hidden" id="comm_07" value="<spring:message code="comm.07" text="default text"/>" /><!-- 새비밀번호를 확인해주세요. -->
	
	<input type="hidden" id="en_24" value="<spring:message code="en.24" text="default text"/>" /><!-- 현재비밀번호를 입력해주세요. -->
		<div class="container body">
			<div class="main_container">
					<tiles:insertAttribute name="header" />
					
					<!-- CONTENTS -->
					<tiles:insertAttribute name="contents" />
			</div>
		</div>
		
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
    <!-- validator -->
     <script src="${vendors}/validator/validator.js"></script>
	</body>
</html>