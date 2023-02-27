<%@ page import="com.eshop.model.entity.Product" %>
<%@ page import="com.eshop.service.ProductService" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商品管理</title>
    <script>
        function toggleEdit(id) {
            var editTable = document.getElementById('edit-table-' + id);
            if (editTable.style.display === 'none') {
                editTable.style.display = 'table';
                var inputFields = editTable.getElementsByTagName('input');
                for (var i = 0; i < inputFields.length; i++) {
                    inputFields[i].value = inputFields[i].getAttribute('value');
                }
            } else {
                editTable.style.display = 'none';
            }
        }
        function toggleAdd() {
            var addForm = document.getElementById('add-form');
            if (addForm.style.display === 'none') {
                addForm.style.display = 'table';
            } else {
                addForm.style.display = 'none';
            }
        }

    </script>
</head>
<body>
<% if (request.getSession().getAttribute("mlogin") == null) {%>
    <p>非法进入</p>
<%} else {%>
<h1>商品管理</h1>
<li class="menu-item"><a href="./mmenu.jsp">返回</a></li>

<% if (request.getSession().getAttribute("products") == null) {
    List<Product> products = new ProductService().getAllProducts();
    request.setAttribute("products", products);
}%>
<table>
    <thead>
    <tr>
        <th>商品编号</th>
        <th>商品名称</th>
        <th>商品描述</th>
        <th>单价</th>
        <th>图片</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${products}" var="product">
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.description}</td>
            <td>${product.price}</td>
            <td><img src="${product.imageUrl}" alt="${product.name}" width="100"></td>
            <td>
                <button onclick="toggleEdit('${product.id}')">编辑</button>
                <form id="edit-form-${product.id}" method="post" action="../ProductServlet/update">
                    <table id="edit-table-${product.id}" style="display: none;">
                        <tr>
                            <td>商品编号:</td>
                            <td><input type="text" name="id" value="${product.id}" readonly></td>
                        </tr>
                        <tr>
                            <td>商品名称:</td>
                            <td><input type="text" name="name" value="${product.name}"></td>
                        </tr>
                        <tr>
                            <td>商品描述:</td>
                            <td><input type="text" name="description" value="${product.description}"></td>
                        </tr>
                        <tr>
                            <td>单价:</td>
                            <td><input type="text" name="price" value="${product.price}"></td>
                        </tr>
                        <tr>
                            <td>图片:</td>
                            <td><input type="text" name="imageUrl" value="${product.imageUrl}"></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <button type="submit">保存更改</button>
                                <button type="button" onclick="toggleEdit('${product.id}')">取消更改</button>
                            </td>
                        </tr>
                    </table>
                </form>
                <form action="../ProductServlet/delete" method="post">
                    <input type="hidden" name="id" value="${product.id}">
                    <button type="submit">删除该商品</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<button onclick="toggleAdd()">添加商品</button>
<form id="add-form" method="post" action="../ProductServlet/add" style="display: none;">
    <table>
        <tr>
            <td>商品名称:</td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td>商品描述:</td>
            <td><input type="text" name="description"></td>
        </tr>
        <tr>
            <td>单价:</td>
            <td><input type="text" name="price"></td>
        </tr>
        <tr>
            <td>图片:</td>
            <td><input type="text" name="imageUrl"></td>
        </tr>
        <tr>
            <td colspan="2">
                <button type="submit">Save</button>
                <button type="button" onclick="toggleAdd()">取消</button>
            </td>
        </tr>
    </table>
</form>
<%}%>
</body>
</html>
