<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" type="text/css" />	

</head>
<body>

<div id="menu" class="basic-grey"  >
<h2><spring:message code="label.listorders" /></h2>

<ul>
<li><a href="<c:url value="/newOrder" />">
	<spring:message code="label.neworder" />
</a>
</li>
<li><a href="<c:url value="/listOrders/1" />">
	<spring:message code="label.listorders" />
</a>
</li>
<li><a href="<c:url value="/logout" />">
	<spring:message code="label.logout" />
</a>
</li>
</ul>
</div>

</body>
</html>