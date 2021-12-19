<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div>
    <%--<span>欢迎<span class="um_span">韩总</span>光临尚硅谷书城</span>--%>
    <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临书城</span>
    <%--<a href="../order/order.jsp">我的订单</a>--%>
    <a href="pages/order/order.jsp">我的订单</a>
    <a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
    <a href="index.jsp">返回</a>
</div>

</body>
</html>
