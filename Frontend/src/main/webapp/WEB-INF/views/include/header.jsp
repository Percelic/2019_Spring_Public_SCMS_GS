<%@page import="java.security.acl.Group"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/common.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>태양광모니터링시스템</title>

<!-- Bootstrap -->
<link rel="stylesheet" type="text/css"
	href="${vendors}/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- bootstrap toggle -->
<link
	href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link rel="stylesheet" type="text/css"
	href="${vendors}/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- NProgress -->
<link rel="stylesheet" type="text/css"
	href="${vendors}/nprogress/nprogress.css" rel="stylesheet">
<!-- iCheck -->
<link rel="stylesheet" type="text/css"
	href="${vendors}/iCheck/skins/flat/green.css" rel="stylesheet">

<!-- bootstrap-progressbar -->
<link rel="stylesheet" type="text/css"
	href="${vendors}/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css"
	rel="stylesheet">
<!-- JQVMap -->
<link rel="stylesheet" type="text/css"
	href="${vendors}/jqvmap/dist/jqvmap.min.css" rel="stylesheet">
<!-- bootstrap-daterangepicker -->
<link rel="stylesheet" type="text/css"
	href="${vendors}/bootstrap-daterangepicker/daterangepicker.css"
	rel="stylesheet">

<!-- Custom Theme Style -->
<link rel="stylesheet" type="text/css"
	href="${build}/css/custom.min.css" rel="stylesheet">

</head>

<body class="nav-md">
	<!-- 끄 -->
	<!-- jQuery -->
	<script src="${vendors}/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="${vendors}/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- FastClick -->
	<script src="${vendors}/fastclick/lib/fastclick.js"></script>
	<!-- NProgress -->
	<script src="${vendors}/nprogress/nprogress.js"></script>
	<!-- Chart.js -->
	<script src="${vendors}/Chart.js/dist/Chart.min.js"></script>
	<!-- gauge.js -->
	<script src="${vendors}/gauge.js/dist/gauge.min.js"></script>
	<!-- bootstrap-progressbar -->
	<script
		src="${vendors}/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
	<!-- iCheck -->
	<script src="${vendors}/iCheck/icheck.min.js"></script>
	<!-- Skycons -->
	<script
		src="${vendors}/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
	<!-- Flot -->
	<script src="${vendors}/skycons/skycons.js"></script>
	<!-- jQuery Sparklines -->
	<script src="${vendors}/jquery-sparkline/dist/jquery.sparkline.min.js"></script>
	<!-- easy-pie-chart -->
	<script
		src="${vendors}/jquery.easy-pie-chart/dist/jquery.easypiechart.min.js"></script>


	<!-- Flot plugins -->
	<script src="${vendors}/Flot/jquery.flot.js"></script>
	<script src="${vendors}/Flot/jquery.flot.pie.js"></script>
	<script src="${vendors}/Flot/jquery.flot.time.js"></script>
	<script src="${vendors}/Flot/jquery.flot.stack.js"></script>
	<script src="${vendors}/Flot/jquery.flot.resize.js"></script>


	<script src="${vendors}/flot.orderbars/js/jquery.flot.orderBars.js"></script>
	<script src="${vendors}/flot.orderbars/js/jquery.flot.orderBars.js"></script>
	<script src="${vendors}/flot.curvedlines/curvedLines.js"></script>
	<!-- DateJS -->
	<script src="${vendors}/DateJS/build/date.js"></script>
	<!-- JQVMap -->
	<script src="${vendors}/jqvmap/dist/jquery.vmap.js"></script>
	<script src="${vendors}/jqvmap/dist/maps/jquery.vmap.world.js"></script>
	<script src="${vendors}/jqvmap/examples/js/jquery.vmap.sampledata.js"></script>
	<!-- bootstrap-daterangepicker -->
	<script src="${vendors}/moment/min/moment.min.js"></script>
	<script src="${vendors}/bootstrap-daterangepicker/daterangepicker.js"></script>

	<!-- 토글버튼 -->
	<script
		src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>

	<!-- Custom Theme Scripts -->
	<%-- 	<script src="${build}/js/custom.min.js"></script> <!-- 문제 사항.. --> --%>
	<script src="${build}/js/custom.min.js"></script>
	<!-- 문제 사항.. -->

</body>
</html>
