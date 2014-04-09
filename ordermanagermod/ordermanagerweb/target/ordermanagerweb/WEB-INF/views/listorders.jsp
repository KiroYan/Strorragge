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
	<jsp:include page="header.jsp" />
</head>
<body>

<div id="menu" class="basic-grey"  >
<c:if test="${!empty orderList}">
	<table class="simple-little-table">
		<tr>
			<th>
			<c:if test="${idSorted}">
				<c:if test="${asc}">&#8593;</c:if>
				<c:if test="${!asc}">&#8595;</c:if>
			</c:if>
			<a href="<c:url value="/listOrders/id/${page} " />"><spring:message code="label.id" /></a></th>
			<th>
			<c:if test="${dateSorted}">
				<c:if test="${asc}">&#8593;</c:if>
				<c:if test="${!asc}">&#8595;</c:if>
			</c:if>
			<a href="<c:url value="/listOrders/date/${page}" />"><spring:message code="label.date" /></a></th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${orderList}" var="order">
			<tr>
				<td>${order.id}</td>
				<td>${order.date}</td>
				<td><a href="<c:url value="/viewOrder/${order.id}" />"><spring:message code="label.view" /></a></td>
				<td><a href="<c:url value="/deleteOrder/${order.id}" />"><spring:message code="label.delete" /></a></td>
			</tr>
		</c:forEach>
		<tr>
				<c:if test="${hasPrevious}">
					<td colspan="2"><a href="<c:url value="/listOrders/${page - 1}" />">previous</a></td>
				</c:if>
				<c:if test="${!hasPrevious}">
					<td colspan="2">&nbsp;</td>
				</c:if>
				<c:if test="${hasNext}">
					<td colspan="2"><a href="<c:url value="/listOrders/${page + 1}" />">next</a></td>
				</c:if>
				<c:if test="${!hasNext}">
					<td colspan="2">&nbsp;</td>
				</c:if>
			</tr>
	</table>
</c:if>
</div>



</body>
</html>
