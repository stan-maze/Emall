<%@ page import="com.eshop.model.entity.User" %>
<%@ page import="com.eshop.service.UserService" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>用户管理</title>
</head>
<body>
<% if (request.getSession().getAttribute("mlogin") == null) {%>
<p>非法进入</p>
<%} else {%>
<h1>用户管理</h1>
<li class="menu-item"><a href="./mmenu.jsp">返回</a></li>
<% if (request.getSession().getAttribute("users") == null) {
  UserService userService = new UserService();
  List<User> users = userService.getAllUsers();
  List<Double> totals = new ArrayList<>();
  for (User user:users) {
    totals.add(userService.getUserTotalById(user.getId()));
  }
  request.setAttribute("totals", totals);
  request.setAttribute("users", users);

  }%>
<table>
  <thead>
  <tr>
    <th>用户名</th>
    <th>邮箱地址</th>
    <th>消费总金额</th>
    <th>操作</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${users}" var="user" varStatus="staus">
    <tr>
      <td>${user.name}</td>
      <td>${user.email}</td>
      <td>${totals[staus.index]}</td>
      <td>
        <form action="../mHistoryServlet" method="get">
          <input type="hidden" name="userId" value="${user.id}">
          <button type="submit">查看用户画像</button>
        </form>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<%}%>
</body>
</html>
