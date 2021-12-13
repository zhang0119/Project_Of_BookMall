<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <%@include file="../common/head.jsp"%>
</head>
<body>

<div id="header">
    <span class="wel_word">购物车</span>

    <%@include file="../common/login_success_menu.jsp"%>
</div>

<div id="main">

    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
            <td>操作</td>
        </tr>
        <%--<tr>
            <td>时间简史</td>
            <td>2</td>
            <td>30.00</td>
            <td>60.00</td>
            <td><a href="#">删除</a></td>
        </tr>--%>

        <c:forEach items="${sessionScope.cart.items}" var="cartItem">
            <tr>
                <td>${cartItem.value.name}</td>
                <td>${cartItem.value.count}</td>
                <td>${cartItem.value.price}</td>
                <td>${cartItem.value.totalPrice}</td>
                <td><a href="#">删除</a></td>
            </tr>
        </c:forEach>


    </table>

    <div class="cart_info">
        <span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
        <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
        <span class="cart_span"><a href="#">清空购物车</a></span>
        <span class="cart_span"><a href="pages/cart/checkout.html">去结账</a></span>
    </div>

</div>

<%@include file="../common/footer.jsp"%>
</body>
</html>