<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ page session="false" %> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script  src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<%--  <div style="text-align: right;">
<c:choose>
	<c:when test="${sessionScope.user_id == null }">
		<a href="${path}/auth/login1">로그인</a> | 
	</c:when>
	<c:otherwise>
		${sessionScope.user_name}님이 로그인중입니다.
		<a href="${path}/auth/logout.do">로그아웃</a>
	</c:otherwise>
</c:choose>
</div> - --%>
<script>
function autoRefresh_div()
{
$("#result").load("load.html");
}
setInterval('autoRefresh_div()', 5000); // 5초 후에 div 새로 고침
</script>
<%

String contextPath = request.getContextPath();

request.setAttribute("vendors", request.getContextPath() + "/include/vendors");
request.setAttribute("code", request.getContextPath() + "/include/code");
request.setAttribute("build", request.getContextPath() + "/include/build");
request.setAttribute("image", request.getContextPath() + "/include/image");



request.setAttribute("res", request.getContextPath() + "/include/res");
request.setAttribute("lib", request.getContextPath() + "/include/lib");

%>
<jsp:useBean id="toDay" class="java.util.Date" />
<script>window.build='${build}';</script>
<script>window.code='${code}';</script>
<script>window.vendors='${vendors}';</script>
<script>window._res='${res}';</script>
<script>window._lib='${lib}';</script>
<script>window._imageb='${image}';</script>

<link rel="stylesheet" href="${path}/include/css/main.css">