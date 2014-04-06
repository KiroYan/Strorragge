<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8">
	<title>News Portal</title>
</head>
<body>

<security:authorize ifAnyGranted="ROLE_ANONYMOUS">  
  <a href="/newsportal/login.jsp">Login</a>
</security:authorize>

<security:authorize ifAnyGranted="ROLE_ADMIN">
  Welcome, <security:authentication property="principal.username"/>! &nbsp; 
| <a href="<c:url value="/logout" />">Logout</a>     
</security:authorize>

| <a href="<c:url value="/index" />">Home</a>  
  
<h2>Add a tag</h2>

<form:form method="post" action="addTag" commandName="tag">

	<table>
		<tr>
			<td>Tag</td>
			<td><form:input path="name" /></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit"
				value="Add tag" /></td>
		</tr>
	</table>
</form:form>
</body>
</html>