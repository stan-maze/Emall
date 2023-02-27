<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登录</title>
    <link rel="stylesheet" href="../css/login.css">
</head>
<body>
<div class="container">
    <h1 class="title">登录</h1>
    <form class="form" action="login" method="POST">
        <div class="form-group">
            <label for="email">邮箱地址: </label>
            <input type="email" name="email" id="email" required>
        </div>
        <div class="form-group">
            <label for="password">密码: </label>
            <input type="password" name="password" id="password" required>
        </div>
        <button class="btn" type="submit">登录</button>
    </form>
    <%-- 判断是否有 message 属性, 报错提示 --%>
    <c:if test="${not empty message}">
        <p class="error">${message}</p>
    </c:if>
    <p class="register-msg"><a href="./home.jsp">先逛逛</a></p>
    <p class="register-msg">还没有账号?<a href="./register.jsp">注册</a>.</p>
    <p class="manager-msg">您是管理员?<a href="./mlogin.jsp">管理员登录</a>.</p>
</div>
</body>
</html>