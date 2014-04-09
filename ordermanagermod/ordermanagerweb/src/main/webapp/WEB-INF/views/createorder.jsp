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
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
	<script>
	
	
	
	$(document).ready(function() {
		$('#dishTypeSelect').change(function () {
			var selectedValue = $(this).prop('value');
			if (selectedValue == '-1') {
				$('#dishSelect').empty();
				$('#dishSelect').append($('<option value="-1" label="--- Select ---"/>'));
			} else {
				$.post('getDishes', selectedValue, function(data) {
					var dishList = data;
					$('#dishSelect').empty();
					$('#dishSelect').append($('<option value="-1" label="--- Select ---"/>'));
					$.each(dishList, function(key, value) {
						$('#dishSelect').append($('<option/>', {
							value : value.id,
							text : value.name
						}));
					});
				}, 'json');
			}
		});		
	});
	

	</script>
	
	
</head>
<body>



<form:form method="post" action="addItem" commandName="dish" class="basic-grey" id="menu">
<h2><spring:message code="label.neworder" /></h2>

	<form:label path="type.id">
		<spring:message code="label.category" />
	</form:label>
	
	<form:select class="select-style" id="dishTypeSelect" path="type.id">
   		<form:option value="-1" label="--- Select ---"/>
   		<form:options items="${dishTypeList}" itemValue="id"/>
	</form:select>
	
	<form:label path="id">
		<spring:message code="label.name" />
	</form:label>
	
	<form:select class="select-style" id="dishSelect" path="id">
   		<form:option value="-1" label="--- Select ---"/>
   		<form:options items="${dishList}" itemValue="id"/>
	</form:select>
	<input type="submit" class="button" value="<spring:message code="label.additem"/>" />
</form:form>


		
<form:form method="post" action="addOrder" commandName="order" class="basic-grey" id="menu">
	<h3><spring:message code="label.selected" /></h3>
<c:if test="${!empty currentOrder}">
	<table class="simple-little-table">
		<tr>
			<th><spring:message code="label.name" /></th>
			<th><spring:message code="label.category" /></th>
			<th><spring:message code="label.price" /></th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${currentOrder}" var="dish">
			<tr>
				<td>${dish.name}</td>
				<td>${dish.type.name}</td>
				<td>${dish.price}</td>
				<td><a href="deleteItem/${dish.id}"><spring:message code="label.delete" /></a></td>
			</tr>
		</c:forEach>
		<tr>
			<td><spring:message code="label.total" /></td>
			<td></td>
			<td>${totalPrice}</td>
		</tr>
	</table>
</c:if>
	<input type="submit" class="button" value="<spring:message code="label.addorder"/>" />
</form:form>


</body>
</html>
