<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Successfully insertion </title>
</head>
<body>
	<h1>Teacher inserted successfully</h1>
	<div>
		<p>${requestScope.insertedTeacher.lastname}</p>
		<p>${requestScope.insertedTeacher.firstname}</p>
		<p></p>
	</div>	
	 	
	<div>
		<a href="${pageContext.request.contextPath}/schoolapp/static/templates/teachersmenu.jsp">Return</a>
	</div>
	<li><a href="${pageContext.request.contextPath}/login.jsp">Logout</a></li>
</body>
</html>