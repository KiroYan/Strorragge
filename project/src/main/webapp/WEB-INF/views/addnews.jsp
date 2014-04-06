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
	<title>Add news</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />">
</head>
<body>

<form:form class="bootstrap-frm" id="news" method="post" action="addNews" commandName="news">

<security:authorize ifAnyGranted="ROLE_ANONYMOUS">  
  <a href="/newsportal/login.jsp">Login</a>
</security:authorize>

<security:authorize ifAnyGranted="ROLE_ADMIN">
  Welcome, <security:authentication property="principal.username"/>! &nbsp; 
| <a href="<c:url value="/logout" />">Logout</a>     
</security:authorize>

| <a href="<c:url value="/index" />">Home</a>  
<h2>Add news</h2>

			<form:label path="title">Title</form:label>
			<form:input path="title" />
			
			<form:label path="tags">Select tags|<a href="<c:url value="/newtag" />">+</a></form:label>
			<ul>  
				<form:checkboxes element="li" path="tags" items="${tagList}"></form:checkboxes>
			</ul>

			<form:label path="text">News content</form:label>
			<form:textarea path="text" rows="10" cols="50" />

			<input class="button" type="submit" value="Add news" />
</form:form>
</body>
</html>