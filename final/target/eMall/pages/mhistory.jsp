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
    <title>用户管理</title>
</head>
<body>
<h1>下单记录</h1>
<% List<MyOrder> orders = new OrderService().getOrdersByUserId(((User) session.getAttribute("user")).getId());
    session.setAttribute("orders", orders);
%>
<a href="./muser.jsp">返回</a>
<table>
    <c:forEach var="order" items="${orders}">
        <tr>
            <th>订单号: </th>
            <td>${order.id}</td>
        </tr>
        <tr>
            <th>下单时间: </th>
            <td><fmt:formatDate value="${order.createdDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        </tr>
        <tr>
            <th>订单详情: </th>
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
            <th>总金额: </th>
            <td><fmt:formatNumber value="${order.total}" type="currency"/></td>
        </tr>
        <tr>
            <td colspan="2"><hr></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
