<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
</head>
<body>
  <h1>New user credentials</h1>
  <p>user ${requestScope.user.username}</p>
  <p>password ${requestScope.user.password}</p>
  <a href="${pageContext.request.contextPath}/schoolapp/static/templates/usersmenu.jsp">Return</a>

</body>
</html>
