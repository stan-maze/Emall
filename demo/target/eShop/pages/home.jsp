<%@ page import="com.eshop.model.entity.Product" %>
<%@ page import="com.eshop.service.CartService" %>
<%@ page import="java.util.List" %>
<%@ page import="com.eshop.model.entity.User" %>
<%@ page import="com.eshop.service.ProductService" %>
<%@ page import="com.eshop.model.entity.CartItem" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>电子商城首页</title>
    <link rel="stylesheet" href="../css/home.css">
</head>
<body>
<header class="header">
    <div class="header-container">
        <div class="logo-container">
            <h1 class="logo">电子商城</h1>
        </div>
        <div class="user-info-container">
            <% if (session.getAttribute("user") != null) { %>
            <p class="user-greeting">欢迎, <%= ((User)session.getAttribute("user")).getName() %>!</p>
            <a href="./account.jsp" class="user-link">个人中心</a>
            <a href="../LogoutServlet" class="user-link">登出</a>
            <%
                List<CartItem> items = new CartService().getUncompletedCartItemsByUserId(((User) session.getAttribute("user")).getId());
                session.setAttribute("items", items);
                double total = 0;
                for (CartItem item: items) {
                    total += item.getTotal();
                }
                session.setAttribute("total", total);
            %>
            <% } else { %>
            <a href="login" class="user-link">登录</a>
            <a href="register" class="user-link">注册</a>
            <% } %>
        </div>
    </div>
</header>
<main class="main-content">
    <% if (session.getAttribute("products") == null) {
        List<Product> products = new ProductService().getAllProducts();
        request.setAttribute("products", products);
    } %>
    <div class="product-list">
        <c:forEach items="${products}" var="product">
            <div class="product">
                <img class="product-img" src="${product.imageUrl}" alt="${product.name}" width="100">
                <div class="product-info">
                    <h3 class="product-name">${product.name}</h3>
                    <p class="product-description">${product.description}</p>
                    <p class="product-price">${product.price}</p>
                </div>
                <div class="product-action">
                    <form class="cart-form" action="../CartServlet/add" method="POST">
                        <input type="hidden" name="product_id" value="${product.id}">
                        <input class="cart-quantity" type="number" name="quantity" value="1" min="1">
                        <button class="cart-btn" type="submit">加入购物车</button>
                    </form>
                </div>
            </div>
        </c:forEach>
    </div>
</main>
<div class="action-buttons">
    <a href="./cart.jsp" class="cart-btn">查看购物车</a>
</div>
</body>
</html>