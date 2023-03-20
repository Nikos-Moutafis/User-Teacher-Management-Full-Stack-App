<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Web Development Course - eLearning AUEB</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/all.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/schoolapp/static/css/index.css">
</head>
<body>
    <div class="container-fluid">
        <div class="header">
            <div class="logo">
                <img src="${pageContext.request.contextPath}/schoolapp/static/img/aueb100b.png" alt="AUEB 100 years">
            </div>
            <div class="menu">
                <ul>
                    <li><a href="#">Home</a></li>
                    <li><a href="#">We Teach</a></li>
                    <li><a href="#">We Innovate</a></li>
                    <li><a href="${pageContext.request.contextPath}/login.jsp">Login</a></li>
                </ul>
            </div>
        </div>

        <div class="slider">
            <div class="title"><p>All you need is Love, Passion, Design,  Software and ... 
                "Coding Factory AUEB"</p></div>
            <div class="subtitle"><p>Become the Full Stack Programmer you meant to be with a deep knowledge
                in programming for empowering your career</p></div>
        </div>

        <div class="main">
            <div class="main-title">
                <h1>Why Coding Factory Course</h1>
            </div>
            <div class="main-text">
                <p>The program "Coding Factory" is designed 
                    based on the assumption that students have no prior knowledge of Software Development. 
                    It starts with presenting techniques for Java/SQL and Web Programming and then heads for 
                    designing and developing full stack desktop applications. </p>
            </div>
        </div>
        <div class="footer">
            <div class="copyright"> 
                <p>&copy; Coding Factory, AUEB, 
                    All rights reserved</p>
            </div>
            <div class="social"> 
                <p class="follow-us">Follow us:</p> 
                <i class="fab fa-facebook-square"></i>
                <i class="fab fa-twitter-square"></i>
            </div>
        </div>
    </div>
</body>
</html>
