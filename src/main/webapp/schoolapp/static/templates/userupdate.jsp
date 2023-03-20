<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Update</title>
</head>
<body>
<div>
    <form method="POST" action="${pageContext.request.contextPath}/schoolapp/updateUser">
        <label for="tid">ID</label>
        <input id="tid" type="text" name="id" value="${param.id}" readonly><br>
        <label for="username">Username</label>
        <input id="username" type="text" name="username" value="${param.username}"><br>
        <label for="password">password</label>
        <input id="password" type="password" name="password" value="${param.password}"><br><br>

        <button type="submit">Update User</button>
    </form>
</div>

<c:if test="${requestScope.isError}">
    <p>${requestScope.message}</p>
</c:if>
</body>
</html>
