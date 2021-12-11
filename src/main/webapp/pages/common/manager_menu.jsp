<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div>
<%--
    <a href="bookServlet?action=list">图书管理</a>
--%>
    <%--这里我们开始使用 分页处理图书--%>
    <a href="bookServlet?action=page">图书管理</a>
    <a href="order_manager.jsp">订单管理</a>
    <a href="index.jsp">返回商城</a>
</div>

</body>
</html>
