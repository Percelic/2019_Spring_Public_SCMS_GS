<%-- <%@ page session="false" %> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>


<%

String contextPath = request.getContextPath();
request.setAttribute("cp", contextPath);

request.setAttribute("vendors", request.getContextPath() + "/resources/vendors");
request.setAttribute("build", request.getContextPath() + "/resources/build");

request.setAttribute("res", request.getContextPath() + "/resources/res");
request.setAttribute("lib", request.getContextPath() + "/resources/lib");

%>
<jsp:useBean id="toDay" class="java.util.Date" />
<script>window._cp='${cp}';</script>
<script>window.build='${build}';</script>
<script>window.vendors='${vendors}';</script>
<script>window._res='${res}';</script>
<script>window._lib='${lib}';</script>