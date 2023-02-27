
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sales Management</title>
    <link rel="stylesheet" type="text/css" href="path/to/styles.css">
    <script src="path/to/scripts.js"></script>
</head>
<body>
<h1>Sales Management</h1>
<ul class="tab">
    <li><a href="sales.jsp?action=listProducts" class="tablinks" onclick="openTab(event, 'products')">Products</a></li>
    <li><a href="sales.jsp?action=listOrders" class="tablinks" onclick="openTab(event, 'orders')">Orders</a></li>
    <li><a href="sales.jsp?action=listUsers" class="tablinks" onclick="openTab(event, 'users')">Users</a></li>
</ul>

<div id="products" class="tabcontent">
    <h2>Products</h2>
    <form method="POST" action="sales.jsp?action=addProduct">
        <!-- Add product form -->
        <table>
            <tr>
                <td>Product name:</td>
                <td><input type="text" name="name" required></td>
            </tr>
            <tr>
                <td>Description:</td>
                <td><input type="text" name="description" required></td>
            </tr>
            <tr>
                <td>Price:</td>
                <td><input type="number" name="price" required></td>
            </tr>
            <tr>
                <td></td>
                <td><button type="submit">Add product</button></td>
            </tr>
        </table>
    </form>
    <br>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Actions</th>
        </tr>
        <c:forEach items="${products}" var="product">
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.description}</td>
                <td>${product.price}</td>
                <td>
                    <form method="POST" action="sales.jsp?action=deleteProduct">
                        <input type="hidden" name="id" value="${product.id}">
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<div id="orders" class="tabcontent">
    <h2>Orders</h2>
    <form method="GET" action="sales.jsp?action=listOrders">
        <label for="startDate">Start date:</label>
        <input type="date" name="startDate">
        <label for="endDate">End date:</label>
        <input type="date" name="endDate">
        <button type="submit">Search</button>
    </form>
    <br>
    <table>
        <tr>
            <th>Order ID</th>
            <th>Customer</th>
