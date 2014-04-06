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
	<title>${news.title}</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />">
</head>
<body>


<form:form class="bootstrap-frm" id="news" method="post" action="${news.id}/addComment" commandName="comment">
<security:authorize ifAnyGranted="ROLE_ANONYMOUS">  
  <a href="/newsportal/login.jsp">Login</a>
</security:authorize>

<security:authorize ifAnyGranted="ROLE_ADMIN">
  Welcome, <security:authentication property="principal.username"/>! &nbsp; 
| <a href="<c:url value="/logout" />">Logout</a>     
</security:authorize>

| <a href="<c:url value="/index" />">Home</a>   

<security:authorize ifAnyGranted="ROLE_ADMIN">
   
| <a href="<c:url value="/edit/${news.id}" />">Edit news</a>     
</security:authorize>

<h2>${news.title}</h2>
	<table>		
			<tr>
				<td>${news.date}</td>
			</tr>
			<tr>
				<td>Posted by ${news.user.nickname}</td>
			</tr>
			<tr>
				<c:forEach items="${news.tags}" var="tag">
				<td>${tag.name}</td>
				</c:forEach>
			</tr>
			<tr>
				<td>${news.text}</td>
			</tr>	
			<tr>
				<td>Add comment</td>
			</tr>
			<tr>
				<td><form:textarea path="text" rows="7" cols="30" /></td>
		    </tr>
		    <tr>
			<td><input type="submit" class="button" value="Add comment" /></td>
		</tr>
			
	</table>
		
	<h4>Comments</h4>
	<c:if test="${!empty comments}">
	<table>
		<c:forEach items="${comments}" var="comment">
			<tr>
				<th>${comment.user.nickname}</th>
				<th>${comment.date}</th>
				<security:authorize ifAnyGranted="ROLE_ADMIN">
   				<td><a href="<c:url value="/${news.id}/deletecomments/${comment.id}" />">delete</a><td>     
			</security:authorize>
		    </tr>
			<tr>
				<td colspan="3">${comment.text}</td>
			</tr>
			<tr></tr>
		</c:forEach>
		
	</table>
	</c:if>
	<c:if test="${empty comments}">
		<p>No comments yet</p>
	</c:if>	
	
</form:form>





</body>
</html>