<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>注册</title>
    <link rel="stylesheet" type="text/css" href="../css/register.css">
</head>
<body>
<div class="header">
    <div class="logo-container">
        <h1>注册</h1>
    </div>
</div>
<div class="register-container">
    <h2>注册</h2>
    <form action="register" method="POST">
        <label for="name">用户名:</label>
        <input type="text" name="name" id="name" required>
        <br>
        <label for="email">邮箱:</label>
        <input type="email" name="email" id="email" required>
        <br>
        <label for="password">密码:</label>
        <input type="password" name="password" id="password" required>
        <br>
        <button type="submit">注册</button>
    </form>
    <c:if test="${not empty message}">
        <p class="error-message">${message}</p>
    </c:if>
    <p>已经拥有账户了？<a href="./login.jsp">点此登录</a></p>
</div>
</body>
</html>
