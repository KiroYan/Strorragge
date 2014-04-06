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
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />">
</head>
<body>
 
  
<div class="bootstrap-frm" id="news"> 
	<security:authorize ifAnyGranted="ROLE_ANONYMOUS">  
  		<a href="/newsportal/login.jsp">Login</a>
	</security:authorize>

	<security:authorize ifAnyGranted="ROLE_ADMIN">
  		Welcome, <security:authentication property="principal.username"/>! &nbsp; 
		| <a href="<c:url value="/logout" />">Logout</a>     
	</security:authorize>

	| <a href="<c:url value="/index" />">Home</a>  

	<security:authorize ifAnyGranted="ROLE_ADMIN">  
		| <a href="<c:url value="/add" />">Add news</a>     
	</security:authorize> 
	
	<h2>News Portal</h2>

	<c:if test="${!empty newsPortion}">
		<table>
			<c:forEach items="${newsPortion}" var="news">
				<tr>
					<td cosplan="2">${news.date}</td>
					<security:authorize ifAnyGranted="ROLE_ADMIN">
   						<td><a href="<c:url value="/delete/${news.id}" />">delete</a><td> 
   					</security:authorize>
				</tr>
				<tr>
					<td cosplan="2"><a href="details/${news.id}">${news.title}</a></td>
				</tr>
				<tr>
					<td cosplan="2">
						<c:forEach items="${news.tags}" var="tag">
							<a href="<c:url value="/tag/${tag.name}/1" />">${tag.name} |</a>
						</c:forEach>
				    </td>
				</tr>	
			</c:forEach>

			<tr>
				<c:if test="${hasPrevious}">
					<td><a href="${previousPage}">previous</a></td>
				</c:if>
				<c:if test="${!hasPrevious}">
					<td>&nbsp</td>
				</c:if>
				<c:if test="${hasNext}">
					<td><a href="${nextPage}">next</a></td>
				</c:if>
				<c:if test="${!hasNext}">
					<td>&nbsp</td>
				</c:if>
			</tr>
			<tr>
				<td cosplan="2"><a href="allnews">show all</a>
			</tr>
		</table>
	</c:if>
</div>

</body>
</html>