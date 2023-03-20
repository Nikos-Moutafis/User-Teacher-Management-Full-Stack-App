<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Teachers Search</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/schoolapp/static/css/teachersmenu.css">
</head>
<body>
<div class="center">
    <p>Hello Admin, please choose if you want to access teachers menu or users menu</p>
</div>

<div class="center">

    <a href="${pageContext.request.contextPath}/schoolapp/static/templates/usersmenu.jsp">Users Menu</a>

</div>

<div class="centertwo">

    <a href="${pageContext.request.contextPath}/schoolapp/static/templates/teachersmenu.jsp">Teachers Menu</a>
</div>

<div class="centertwo">
    <a href="${pageContext.request.contextPath}/login.jsp">Logout</a>
</div>


</body>
</html>


