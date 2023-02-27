<%@ page import="com.eshop.model.entity.CartItem" %>
<%@ page import="com.eshop.model.entity.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>购物车</title>
    <link rel="stylesheet" href="../css/cart.css">
</head>
<body>
<header class="header">
    <div class="logo-container">
        <h1 class="logo">电子商城</h1>
    </div>
    <div class="user-info-container">
        <% if (session.getAttribute("user") != null) { %>
        <p class="user-greeting">你好, <%= ((User)session.getAttribute("user")).getName() %>!</p>
        <a href="./account.jsp" class="user-link">个人中心</a>
        <a href="../LogoutServlet" class="user-link">登出</a>
        <% } else { %>
        <a href="login" class="user-link">登录</a>
        <a href="register" class="user-link">注册</a>
        <% } %>
    </div>
</header>
<main class="cart-main">
    <h1 class="cart-heading">我的购物车</h1>
    <hr class="divider">
    <c:choose>
        <c:when test="${not empty items}">
            <table class="cart-table">
                <thead>
                <tr>
                    <th>商品名称</th>
                    <th>单价</th>
                    <th>数量</th>
                    <th>该项总价</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${items}" var="item">
                    <tr>
                        <td>${item.product.name}</td>
                        <td>${item.product.price}</td>
                        <td>${item.quantity}</td>
                        <td>${item.total}</td>
                        <td><a href="../CartServlet/delete?cart_id=${item.id}">删除</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <hr class="divider">
            <div class="total-container">
                <h2 class="total-heading">订单总价: ${total}</h2>
                <a href="./checkout.jsp" class="checkout-btn">确认下单</a>
            </div>
        </c:when>
        <c:otherwise>
            <p class="empty-cart-msg">购物车是空的, 快去逛逛吧!</p>
        </c:otherwise>
    </c:choose>

    <div class="cart-action">
        <a href="home.jsp" class="continue-shopping-btn">继续购物</a>
    </div>
</main>
</body>
</html>