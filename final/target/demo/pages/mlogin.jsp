<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.eshop.model.entity.User" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理员登录</title>
    <link rel="stylesheet" href="../css/login.css">
</head>
<body>
<div class="container">
    <h1 class="title">管理员登录</h1>
    <form class="form" method="post" action="mlogin">
        <div class="form-group">
            <label for="mid">账号:</label>
            <input type="text" id="mid" name="mid">
        </div>
        <div class="form-group">
            <label for="mpassword">密码:</label>
            <input type="password" id="mpassword" name="mpassword">
        </div>
        <button type="submit" class="btn">登录</button>
    </form>
    <c:if test="${not empty message}">
        <p class="error">${message}</p>
    </c:if>
    <a href="./login.jsp">返回登录界面</a>.</p>
</div>
</body>
</html>