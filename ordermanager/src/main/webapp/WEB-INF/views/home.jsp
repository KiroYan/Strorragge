<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8">
	<title><spring:message code="label.title" /></title>
	<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" type="text/css" />
</head>
<body>

<h2 class="basic-grey"><spring:message code="label.title" /></h2>

<ul class="basic-grey">
<li><a href="<c:url value="/newOrder" />">
	<spring:message code="label.neworder" />
</a>
</li>
<li><a href="<c:url value="/listOrders" />">
	<spring:message code="label.listorders" />
</a>
</li>
<li><a href="<c:url value="/logout" />">
	<spring:message code="label.logout" />
</a>
</li>
</ul>



</body>
</html>