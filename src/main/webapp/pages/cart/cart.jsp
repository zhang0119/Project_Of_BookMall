<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <%@include file="../common/head.jsp"%>

    <script type="text/javascript">

        $(function(){

            //用户清空购物车加入提醒功能
            $("#clearCart").click(function(){

                return confirm("你确定要清空购物车吗?");
            });

            //这里我加入用户删除购物车商品时提醒功能
            $(".deleteBook").click(function(){
                //this对象  confirm方法
                //先获取图书的名字
                let bookName = $(this).parent().parent().find("td:first").html();

                return confirm("你确定要删除【"+bookName+"】吗?");
            })
        })
    </script>
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
            <td>总金额</td>
            <td>操作</td>
        </tr>

        <c:forEach items="${sessionScope.cart.items}" var="cartItem">
            <tr>
                <td>${cartItem.value.name}</td>
                <td>${cartItem.value.count}</td>
                <td>${cartItem.value.price}</td>
                <td>${cartItem.value.totalPrice}</td>
                <td><a href="client/bookServlet?action=deleteItemInCart&id=${cartItem.value.id}" class="deleteBook">删除</a></td>
            </tr>
        </c:forEach>


    </table>

    <div class="cart_info">
        <%--如果session域对象中 cart对象不为空，以下内容才会输出，否则不输出以下内容--%>
        <c:if test="${not empty sessionScope.cart.items}">
            <span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
            <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
            <span class="cart_span"><a href="client/bookServlet?action=clearCart" id="clearCart">清空购物车</a></span>
            <span class="cart_span"><a href="client/orderServlet?action=createOrder">去结账</a></span>
        </c:if>

        <%--如果购物车为空，我们应该引导用户去购物，给一个去首页的连接--%>
        <c:if test="${empty sessionScope.cart.items}">
            <div style="text-align:center">
                亲，当前购物车为空，<a href="index.jsp" style="color:red">点击这里</a>去首页看看吧!
            </div>
        </c:if>

    </div>

</div>

<%@include file="../common/footer.jsp"%>
</body>
</html>