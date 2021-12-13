<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>首页</title>

    <%@include file="../common/head.jsp"%>

    <script type="text/javascript">

        $(function(){

            //当用户点击 "加入购物车"按钮后，向clientBookServlet发起请求
            $(".addCart").click(function(){
                //alert("hello,js!");
                //这里我们要拿到图书的id,这里我们要自定义bookId属性，通过这个属性拿到图书的id
                //使用attr()来获得id的值
                //this是当前遍历到的dom对象
                let bookId = $(this).attr("bookId");
                //(bookId);

                location.href="client/bookServlet?action=addItem&id="+bookId;
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
            <a href="pages/cart/cart.jsp">购物车</a>
            <a href="pages/manager/manager.jsp">后台管理</a>
            <a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
        </c:if>

    </div>
</div>
<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="" method="get">
                价格：<input id="min" type="text" name="min" value=""> 元 -
                <input id="max" type="text" name="max" value=""> 元
                <input type="submit" value="查询" />
            </form>
        </div>
        <div style="text-align: center">
            <span>您的购物车中有3件商品</span>
            <div>
                您刚刚将<span style="color: red">时间简史</span>加入到了购物车中
            </div>
        </div>



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

    <%--分页处理--%>
    <div id="page_nav">

        <a href="client/bookServlet?action=page&pageNo=1">首页</a>

        <%--上一页是当前页-1--%>
        <%--这里我们不能简单的这样做，还有有一个判断，如果当前页是第一页，那么它的上一页也是第一页--%>
        <c:if test="${requestScope.page.pageNo==1}">
            <%--如果pageNo==1，我直接让"上一页"消失--%>
            <%--<a href="client/bookServlet?action=page&pageNo=${requestScope.page.pageNo}&pageSize=${requestScope.page.pageSize}">上一页</a>--%>
        </c:if>

        <c:if test="${requestScope.page.pageNo!=1}">
            <a href="client/bookServlet?action=page&pageNo=${requestScope.page.pageNo-1}">上一页</a>
        </c:if>

        <c:choose>

            <%--第一种情况：即pageTotal<=5 时的情况，页码的范围是：1~5 --%>
            <c:when test="${requestScope.page.pageTotal<=5}">
                <c:forEach var="i" begin="1" end="${requestScope.page.pageTotal}">
                    <%--找到当前页码--%>
                    <c:if test="${requestScope.page.pageNo==i}">
                        【${requestScope.page.pageNo}】
                    </c:if>

                    <%--非当前页--%>
                    <c:if test="${requestScope.page.pageNo!=i}">
                        <a href="client/bookServlet?action=page?pageNo=${requestScope.page.pageNo}">i</a>
                    </c:if>
                </c:forEach>
            </c:when>

            <%--第二种情况:总页码大于5，即 pageTotal > 5 的情况--%>
            <c:when test="${requestScope.page.pageTotal>5}">

                <c:choose>
                    <%--a.当前页码为前面3个： 1,2,3 页码范围是：1-5--%>
                    <c:when test="${requestScope.page.pageNo<=3}">

                        <c:set var="begin" value="1"/>
                        <c:set var="end" value="5"/>

                    </c:when>

                    <%--当前页码为最后3个，假定为 8,9,10 页码范围是: pageTotal-4~pageTotal--%>
                    <c:when test="${requestScope.page.pageNo>requestScope.page.pageTotal-3}">

                        <c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
                        <c:set var="end" value="${requestScope.page.pageTotal}"/>
                    </c:when>

                    <%--最后一种是中间的情况，例如：4，5，6，7，8  范围是: pageNo-2~pageNo+2  --%>
                    <c:otherwise>

                        <c:set var="begin" value="${requestScope.page.pageNo-2}"/>
                        <c:set var="end" value="${requestScope.page.pageNo+2}"/>

                    </c:otherwise>

                </c:choose>

            </c:when>

        </c:choose>

        <%--这里是我抽取出来的公共代码--%>
        <c:forEach begin="${begin}" end="${end}" var="i">
            <%--当前页--%>
            <c:if test="${requestScope.page.pageNo==i}">
                【${i}】
            </c:if>
            <%--非当前页--%>
            <c:if test="${requestScope.page.pageNo!=i}">
                <a href="client/bookServlet?action=page&pageNo=${i}">${i}</a>
            </c:if>
        </c:forEach>


        <%--同理，下一页我也按照这种思路来处理--%>
        <c:if test="${requestScope.page.pageNo==requestScope.page.pageTotal}">
            <%--直接让“下一页”消失--%>
        </c:if>
        <c:if test="${requestScope.page.pageNo!=requestScope.page.pageTotal}">
            <%--上一页是当前页+1--%>
            <a href="client/bookServlet?action=page&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        </c:if>


        <a href="client/bookServlet?action=page&pageNo=${requestScope.page.pageTotal}">末页</a>
        ，共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录 到第<label for="pn_input"></label><input <%--value="4"--%> name="pn" id="pn_input"/>页
        <input type="button" value="确定">
    </div>

</div>


<%@include file="../common/footer.jsp"%>
</body>
</html>