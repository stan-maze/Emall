<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>错误</title>
    <link rel="stylesheet" href="../css/error.css">
</head>
<body>
<header class="header">
    <div class="logo-container">
        <h1>电子商城</h1>
    </div>
</header>

<main class="main">
    <h1 class="error-title">出现错误</h1>
    <% // 使当前页面的所有属性都无效化
        request.getSession().invalidate();
    %>
    <a class="error-link" href="./login.jsp">返回登录页面</a>
</main>
</body>
</html>
