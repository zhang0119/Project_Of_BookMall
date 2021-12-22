<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>首页</title>

    <%@include file="../common/head.jsp"%>

    <script type="text/javascript">

        $(function(){

            //但我们在输入框中输入合法的页码可以跳转到指定的页码
            $("#endBtn").click(function(){

                //这里我们先拿到用户输入的数字
                let pageNo = $("#pn_input").val();
                //把输入发送给后端，完成分页后再把数据发送到前端遍历输出
                location.href="http://localhost:8080/book/client/bookServlet?action=page&pageNo="+pageNo;
            });


            //当用户点击 "加入购物车"按钮后，向clientBookServlet发起请求
            /*$(".addCart").click(function(){
                //alert("hello,js!");
                //这里我们要拿到图书的id,这里我们要自定义bookId属性，通过这个属性拿到图书的id
                //使用attr()来获得id的值
                //this是当前遍历到的dom对象
                let bookId = $(this).attr("bookId");
                //(bookId);

                location.href="client/bookServlet?action=addItem&id="+bookId;
            })*/


            //当用户点击 “加入购物车”后,这里我们使用ajax技术
            //这里我们需要查询加入购物车的商品件数，刚刚将什么图书放入购物车
            $(".addCart").click(function(){
                //这里我们获取加入购物车的图书id
                let bookId = $(this).attr("bookId");
                $.getJSON(
                    "client/bookServlet",
                    {
                        action:"addCart",
                        bookId:bookId
                    },
                    function(data){
                        $("#cart_totalCount").html("您的购物车中有<span style='color:red'>"+data.totalCount+"</span>商品ajax");
                        $("#last_product").html("您刚刚将<span style='color:red'>"+data.last_bookName+"<span>加入到购物车中ajax");

                    }
                )
            })
        })
    </script>

</head>
<body>

    <div id="header">

    <span class="wel_word">网上书城</span>
    <div>
        <%--
            这里我们需要做一些判断
            a.如果用户未登录，那么不用显示购物车和后台管理
            b.用户如果登录成功，那么不必显示登录和注册的链接
        --%>
        <c:if test="${empty sessionScope.user.username}">
            <a href="pages/user/login.jsp">登录</a> |
            <a href="pages/user/register.jsp">注册</a> &nbsp;&nbsp;
        </c:if>

        <c:if test="${not empty sessionScope.user.username}">
            <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临书城</span>
            <a href="pages/cart/cart.jsp">购物车</a>
            <a href="pages/manager/manager.jsp">后台管理</a>
            <a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
        </c:if>

    </div>
</div>
    <div id="main">
    <div id="book">
        <div class="book_cond">

            <form action="client/bookServlet" method="get">
                <%--加个隐藏域，设置 action=bookOfPriceRange--%>
                    <input type="hidden" name="action" value="bookOfPriceRange">
                    <%--这里我们要做的是价格区间的回显,这里我们使用el 表达式获取地址栏上价格区间值--%>
                价格：<label for="min"></label><input id="min" type="text" name="min" value="${param.min}"> 元 -
                <label for="max"></label><input id="max" type="text" name="max" value="${param.max}"> 元
                <input type="submit" value="查询" />
            </form>

        </div>

        <%--以下代码只有我们登录成功才会显示，这里做个判断--%>
        <c:if test="${not empty sessionScope.user}">

            <%--如果购物车为空，以下的代码也不显示，即使我们已完成登录--%>
            <c:if test="${empty sessionScope.cart.items}">
                <div style="text-align: center">
                        <%--<span>您的购物车中有3件商品</span>--%>
                    <span id="cart_totalCount">您的购物车中有<span style="color: red">${sessionScope.cart.totalCount}</span>件商品</span>
                    <div id="last_product">
                            <%--您刚刚将<span style="color: red">时间简史</span>加入到了购物车中--%>
                        您刚刚将<span style="color: red">${sessionScope.last_bookName}</span>加入到了购物车中
                    </div>
                </div>
            </c:if>

            <%--如果购物车为空，以下的代码也不显示，即使我们已完成登录--%>
            <c:if test="${not empty sessionScope.cart.items}">
                <div style="text-align: center">
                        <%--<span>您的购物车中有3件商品</span>--%>
                    <span id="cart_totalCount">您的购物车中有<span style="color: red">${sessionScope.cart.totalCount}</span>件商品</span>
                    <div id="last_product">
                            <%--您刚刚将<span style="color: red">时间简史</span>加入到了购物车中--%>
                        您刚刚将<span style="color: red">${sessionScope.last_bookName}</span>加入到了购物车中
                    </div>
                </div>
            </c:if>
        </c:if>




        <%--以下是图书展示部分，这里我们用 jstl 遍历输出图书信息--%>
        <c:forEach items="${requestScope.page.items}" var="book">

            <div class="b_list">
                <div class="img_div">
                    <%--<img class="book_img" alt="" src="static/img/default.jpg" />--%>
                    <img class="book_img" alt="" src="${book.imgPath}" />
                </div>

                <div class="book_info">
                    <div class="book_name">
                        <span class="sp1">书名:</span>
                        <%--<span class="sp2">时间简史</span>--%>
                        <span class="sp2">${book.name}</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">作者:</span>
                        <%--<span class="sp2">霍金</span>--%>
                        <span class="sp2">${book.author}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">价格:</span>
                        <%--<span class="sp2">￥30.00</span>--%>
                        <span class="sp2">${book.price}</span>
                    </div>

                    <div class="book_sales">

                        <span class="sp1">销量:</span>
                        <%--<span class="sp2">230</span>--%>
                        <span class="sp2">${book.sales}</span>
                    </div>

                    <div class="book_amount">
                        <span class="sp1">库存:</span>
                        <%--<span class="sp2">1000</span>--%>
                        <span class="sp2">${book.stock}</span>
                    </div>

                    <%--当用户点击这个按钮，要将图书添加到购物车中
                        这里用js绑定这个按钮，并发起一个请求，这个请求由clientBookServlet处理
                    --%>
                    <div class="book_add">
                        <button bookId="${book.id}" class="addCart">加入购物车</button>
                    </div>


                </div>
            </div>

        </c:forEach>
    </div>

        <div>
            <%--静态包含引入分页条--%>
            <%@include file="../common/page-nav.jsp"%>
        </div>


    </div>


        <%@include file="../common/footer.jsp"%>

</body>
</html>