<%@ page import="com.eshop.model.entity.User" %>
<%@ page import="com.eshop.service.UserService" %>
<%@ page import="com.eshop.service.OrderService" %>
<%@ page import="com.eshop.model.entity.MyOrder" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>账户管理</title>
    <link rel="stylesheet" type="text/css" href="../css/account.css">
    <script type="text/javascript">
        window.onload=function(){
            var formType = '<%= request.getParameter("formType") %>';
            if(formType !== 'null') {
                showForm(formType);
            }
        }
        function showForm(tabName) {
            var profileTab = document.getElementById("profile");
            var passwordTab = document.getElementById("password");
            if (tabName === "profile") {
                profileTab.style.display = "block";
                passwordTab.style.display = "none";
            } else {
                profileTab.style.display = "none";
                passwordTab.style.display = "block";
            }
        }
    </script>
</head>
<body>
<div class="logo-container">
    <h1 class="logo">电子商城</h1>
</div>
<h1>账户管理</h1>
<ul>
    <li><a href="#" onclick="showForm('profile')">个人资料</a></li>
    <li><a href="#" onclick="showForm('password')">修改密码</a></li>
    <li><a href="./history.jsp">历史记录</a></li>
    <li><a href="./home.jsp">返回</a></li>
</ul>
<hr>

<div id="profile" style="display: block">
    <h2>个人资料</h2>
    <form method="POST" action="../AccountServlet/email">
        <table>
            <tr>
                <td>用户名:</td>
                <td><input type="text" name="username" value="${user.name}" readonly></td>
            </tr>
            <tr>
                <td>邮箱:</td>
                <td><input type="text" name="email" value="${user.email}" readonly></td>
            </tr>
            <tr>
                <td>新邮箱:</td>
                <td><input type="email" name="newEmail"></td>
            </tr>
            <div>
                <c:if test="${not empty param.emailsuccess}">
                    <p style="color: green">邮箱已成功更新</p>
                </c:if>
            </div>
            <tr>
                <td></td>
                <td><button type="submit">保存更改</button></td>
            </tr>
        </table>
    </form>
</div>

<div id="password" style="display: none">
    <h2>更改密码</h2>
    <form method="POST" action="../AccountServlet/password">
        <table>
            <tr>
                <td>当前密码：</td>
                <td><input type="password" name="currentPassword"></td>
            </tr>
            <tr>
                <td>新密码：</td>
                <td><input type="password" name="newPassword"></td>
            </tr>
            <tr>
                <td>确认新密码：</td>
                <td><input type="password" name="confirmPassword"></td>
            </tr>
            <div>
                <c:if test="${param.pswsuccess == 1}">
                    <p style="color: red">密码更新成功</p>
                </c:if>
                <c:if test="${param.error == 1}">
                    <p style="color: red">旧密码不正确</p>
                </c:if>
                <c:if test="${param.error == 2}">
                    <p style="color: red">新密码不匹配</p>
                </c:if>
            </div>
            <tr>
                <td></td>
                <td><button type="submit">更改密码</button></td>
            </tr>
        </table>
    </form>
</div>

<div id="history" style="display: none">
    <h2>购买历史</h2>
    <% List<MyOrder> orders = new OrderService().getOrdersByUserId(((User) session.getAttribute("user")).getId());
        session.setAttribute("orders", orders);
    %>
    <table>
        <tr>
            <th>订单号</th>
            <th>下单时间</th>
            <th>下单详情</th>
            <th>总价</th>
        </tr>
        <c:forEach var="order" items="${orders}">
            <tr>
                <td>${order.id}</td>
                <td><fmt:formatDate value="${order.createdDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
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
                <td><fmt:formatNumber value="${order.total}" type="currency"/></td>
            </tr>
        </c:forEach>
    </table>
</div>
</div>
</body>
</html>


