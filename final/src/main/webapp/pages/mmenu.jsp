<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.eshop.model.entity.User" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理菜单</title>
    <link rel="stylesheet" href="../css/mmenu.css">
    <script type="text/javascript">
        function logout() {
            sessionStorage.removeItem("mlogin");
            sessionStorage.removeItem("mid");
            window.location.replace("<%= request.getContextPath() %>/pages/mlogin.jsp");
        }
    </script>
</head>
<body>
<div class="header">
    <div class="header__logo">管理菜单</div>
    <% if (request.getSession().getAttribute("mlogin") != null) { %>
    <div class="header__user">
        <span class="header__username">管理员 <%= request.getSession(true).getAttribute("mid") %></span>
        <a href="../LogoutServlet" class="header__logout-btn">退出登录</a>
    </div>
    <% } %>
</div>
<div class="container">
    <% if (request.getSession().getAttribute("mlogin") != null) { %>
    <h1 class="page-title">管理菜单</h1>
    <ul class="menu-list">
        <li class="menu-item"><a href="./mproduct.jsp">商品管理</a></li>
        <li class="menu-item"><a href="./msales.jsp">销售管理</a></li>
        <li class="menu-item"><a href="./muser.jsp">用户管理</a></li>
    </ul>
    <% } %>
</div>
</body>
</html>
