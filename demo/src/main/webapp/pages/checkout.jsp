<%@ page import="com.eshop.model.entity.CartItem" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="com.eshop.model.entity.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>收银台</title>
  <link rel="stylesheet" href="../css/checkout.css">
</head>
<body>
<header class="header">
  <div class="logo-container">
    <h1 class="logo">电子商城</h1>
  </div>
</header>
<main class="checkout-main">
  <h1 class="checkout-heading">收银台</h1>
  <form name="checkoutForm" action="../CheckoutServlet" method="POST">
    <table class="checkout-table">
      <tr>
        <td>名字: </td>
        <td><input type="text" name="name" value="${user.name}" readonly></td>
      </tr>
      <tr>
        <td>邮箱地址: </td>
        <td><input type="text" name="email" value="${user.email}" readonly></td>
      </tr>
      <tr>
        <td>送货地址: </td>
        <td><input type="text" name="address" required></td>
      </tr>
      <tr>
        <td>总额: </td>
        <td><fmt:formatNumber value="${total}" type="currency"/></td>
      </tr>
    </table>
    <div class="checkout-btn-container">
      <button type="submit" class="checkout-btn">支付</button>
    </div>
  </form>
</main>
</body>
</html>