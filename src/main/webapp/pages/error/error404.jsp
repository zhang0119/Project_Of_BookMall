<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>该页面不存在-404</title>
    <%@include file="../common/head.jsp"%>
    <link rel="stylesheet" href="static/css/h-ui.reset.css">
    <link rel="stylesheet" href="static/css/index.css">

</head>
<body>

<div class="system">
    <%--<img src="picture/error404.png"/>--%>
    <img src="static/img/error404.png"/>
    <div class="title">
        <h2>页面走丢了...</h2>
        <h4>远方的朋友你好！非常抱歉，您所请求的页面不存在！</h4>
        <h4>请仔细检查您输入的网址是否正确。<a style="color: red" href="index.jsp">返回首页</a></h4>
    </div>
</div>

</body>
</html>
