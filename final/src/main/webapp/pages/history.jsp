<%@ page import="com.eshop.model.entity.MyOrder"%>
<%@ page import="com.eshop.model.entity.CartItem"%>
<%@ page import="java.util.List"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.eshop.service.OrderService" %>
<%@ page import="com.eshop.model.entity.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单历史记录</title>
    <link rel="stylesheet" href="../css/history.css">
</head>
<body>
<header class="header">
    <div class="logo-container">
        <h1>历史记录</h1>
    </div>
    <div class="user-greeting">
        <p>你好，<%= ((User)session.getAttribute("user")).getName() %>!</p>
    </div>
    <div class="user-link">
        <a href="../LogoutServlet">登出</a>
    </div>
</header>
<% List<MyOrder> orders = new OrderService().getOrdersByUserId(((User) session.getAttribute("user")).getId());
    session.setAttribute("orders", orders);
%>

<main class="history-main">
    <h1 class="history-heading">订单历史记录</h1>
    <div class="history-table-container">
        <table class="history-table">
            <tbody>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <th>订单号：</th>
                    <td>${order.id}</td>
                </tr>
                <tr>
                    <th>下单时间：</th>
                    <td><fmt:formatDate value="${order.createdDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
                <tr>
                    <th>商品：</th>
                    <td>
                        <table>
                            <c:forEach var="item" items="${order.items}">
                                <tr>
                                    <td>${item.product.name}</td>
                                    <td>${item.quantity} x <fmt:formatNumber value="${item.product.price}" type="currency"/></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </td>
                </tr>
                <tr>
                    <th>总价：</th>
                    <td><fmt:formatNumber value="${order.total}" type="currency"/></td>
                </tr>
                <tr>
                    <td colspan="2"><hr></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="history-action">
        <a href="./account.jsp" class="return-account-btn">返回个人信息</a>
    </div>
</main>
</body>
</html>
