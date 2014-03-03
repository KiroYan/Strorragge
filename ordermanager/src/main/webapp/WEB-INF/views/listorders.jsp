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

<h2 class="basic-grey"><spring:message code="label.listorders" /></h2>

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

<c:if test="${!empty orderList}">
	<table class="simple-little-table">
		<tr>
			<th><spring:message code="label.id" /></th>
			<th><spring:message code="label.date" /></th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${orderList}" var="order">
			<tr>
				<td>${order.id}</td>
				<td>${order.date}</td>
				<td><a href="viewOrder/${order.id}"><spring:message code="label.view" /></a></td>
				<td><a href="deleteOrder/${order.id}"><spring:message code="label.delete" /></a></td>
			</tr>
		</c:forEach>
	</table>
</c:if>
		



</body>
</html>