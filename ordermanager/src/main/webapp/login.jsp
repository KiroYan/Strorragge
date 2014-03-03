<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8">
	<title><spring:message code="label.title" /></title>
	<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" type="text/css" />
</head>
<body>

<c:if test="${not empty param.error}">
	<font color="red"> <spring:message code="label.loginerror" />
	: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} </font>
</c:if>
<form class="basic-grey" method="POST" action="<c:url value="/j_spring_security_check" />">
		<label id="title"><spring:message code="label.title" /></label>
		<label><spring:message code="label.login" /></label>
		<input type="text" name="j_username" />
		<label><spring:message code="label.password" /></label>
		<input type="password" name="j_password" />
		<input class="button" type="submit" value="Login" />
		<input class="button" type="reset" value="Reset" />
</form>
</body>
</html>