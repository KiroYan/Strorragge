<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8">
	<title>News Portal</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />">
</head>
<body>

<c:if test="${not empty param.error}">
	<font color="red"> Login error
	: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} </font>
</c:if>
<form class="bootstrap-frm" method="POST" action="<c:url value="/j_spring_security_check" />">
		<label>Login</label>
		<input type="text" name="j_username" />
		<label>Password</label>
		<input type="password" name="j_password" />
		<input class="button" type="submit" value="Login" />
		<input class="button" type="reset" value="Reset" />
</form>
</body>
</html>