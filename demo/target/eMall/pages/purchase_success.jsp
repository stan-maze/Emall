<%@ page import="com.eshop.model.entity.User" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>购买成功</title>
    <link rel="stylesheet" href="../css/purchase_success.css">
</head>
<body>
<header class="header">
    <div class="logo-container">
        <h1>电子商城</h1>
    </div>
    <div class="user-greeting">
        <p>你好，<%= ((User)session.getAttribute("user")).getName() %>!</p>
    </div>
    <div class="user-link">
        <a href="./account.jsp">个人信息</a>
        <a href="../LogoutServlet">登出</a>
    </div>
</header>

<main class="cart-main">
    <h1 class="cart-heading">购买成功!</h1>
    <p>您的购物车已被清空，请查收您的电子邮件。</p>
    <div class="cart-action">
        <a href="./home.jsp" class="continue-shopping-btn">返回首页</a>
    </div>
</main>
</body>
</html>
