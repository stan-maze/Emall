<%@ page import="com.eshop.model.entity.Product" %>
<%@ page import="com.eshop.service.ProductService" %>
<%@ page import="com.eshop.service.CartService" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>销售管理</title>
</head>
<body>
<% if (request.getSession().getAttribute("mlogin") == null) {%>
<p>非法进入</p>
<%} else {%>
<li class="menu-item"><a href="./mmenu.jsp">返回</a></li>
<%
    if (session.getAttribute("products") == null) {
        ProductService productService = new ProductService();
        List<Product> products = productService.getAllProducts();
        List<Integer> Sales = new ArrayList<>();
        List<Double> Total = new ArrayList<>();
        List<Integer> customers = new ArrayList<>();
        for (Product product : products) {
            Sales.add(productService.getProductSalesById(product.getId()));
            Total.add(productService.getProductTotalsById(product.getId()));
            customers.add(productService.getProductUserById(product.getId()));
        }
        request.setAttribute("products", products);
        request.setAttribute("sales", Sales);
        request.setAttribute("total", Total);
        request.setAttribute("customers", customers);
    }
%>
<h1>销售管理</h1>
<table>
    <thead>
    <tr>
        <th>商品编号</th>
        <th>商品名称</th>
        <th>商品描述</th>
        <th>单价</th>
        <th>图片</th>
        <th>成交总数</th>
        <th>成交总金额</th>
        <th>不同客户数</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${products}" var="product" varStatus="staus">
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.description}</td>
            <td>${product.price}</td>
            <td><img src="${product.imageUrl}" alt="${product.name}" width="100"></td>
            <td>${sales[staus.index]}</td>
            <td>${total[staus.index]}</td>
            <td>${customers[staus.index]}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%}%>
</body>
</html>
