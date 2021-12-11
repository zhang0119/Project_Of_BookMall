<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理</title>
    <%@include file="../common/head.jsp"%>
    <script type="text/javascript">
        $(function(){

            //这里我们做一个删除提醒功能
            $("a.deleteClass").click(function(){
                //这里介绍一个this对象，它代表当前遍历到的dom对象
                //这里我们先找到删除图书的name
                let bookName = $(this).parent().parent().find("td:first").text();
                return confirm("你确定要删除【"+bookName+"】吗?");
            });

            //这里我们实现的功能是跳转到指定的页面
            $(":input:eq(1)").click(function(){
                /*获取用户在输入框中的输入的页码*/
                let pageNo = $("#pn_input").val();
                //这里我设置跳转路径,设置请求路径
                /*
                    js中提供了一个location地址栏对象
                    它有一个属性叫 href,他可以获取浏览器地址栏中的地址
                    href属性可读，可写
                 */
                location.href="${requestScope.basePath}bookServlet?action=page&pageNo="+pageNo+"&pageSize=${requestScope.page.pageSize}";
            })
        })
    </script>
</head>
<body>

<div id="header">
    <span class="wel_word">图书管理系统</span>
    <%@include file="../common/manager_menu.jsp"%>
</div>

<div id="main">
    <table >
        <tr>
            <td>名称</td>
            <td>价格</td>
            <td>作者</td>
            <td>销量</td>
            <td>库存</td>
            <td colspan="2">操作</td>
        </tr>

        <%--这里我们不能从books里面取数据了，而是从 page里面取数据了--%>
        <%--<c:forEach items="${requestScope.books}" var="book">--%>
        <c:forEach items="${requestScope.page.items}" var="book">
            <tr>
                <td>${book.name}</td>
                <td>${book.price}</td>
                <td>${book.author}</td>
                <td>${book.sales}</td>
                <td>${book.stock}</td>

                <%--<td><a href="book_edit.jsp">修改</a></td>--%>
                <%--这里我们需要先查到图书信息，将其展示到页面上，等待我们修改--%>
                <td><a href="bookServlet?action=getBookById&id=${book.id}">修改</a></td>

                <td><a class="deleteClass" href="bookServlet?action=deleteBookById&id=${book.id}">删除</a></td>
            </tr>
        </c:forEach>


        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <%--<td><a href="book_edit.jsp">添加图书</a></td>--%>
            <td><a href="pages/manager/book_edit.jsp">添加图书</a></td>
        </tr>
    </table>
</div>

<%--分页处理--%>
<div id="page_nav">

    <a href="bookServlet?action=page&pageNo=1&pageSize=5">首页</a>


    <%--上一页是当前页-1--%>
    <%--这里我们不能简单的这样做，还有有一个判断，如果当前页是第一页，那么它的上一页也是第一页--%>
    <c:if test="${requestScope.page.pageNo==1}">
        <%--如果pageNo==1，我直接让"上一页"消失--%>
        <%--<a href="bookServlet?action=page&pageNo=${requestScope.page.pageNo}&pageSize=${requestScope.page.pageSize}">上一页</a>--%>
    </c:if>

    <c:if test="${requestScope.page.pageNo!=1}">
        <a href="bookServlet?action=page&pageNo=${requestScope.page.pageNo-1}&pageSize=${requestScope.page.pageSize}">上一页</a>
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
                    <a href="bookServlet?action=page?pageNo=${requestScope.page.pageNo}&pageSize=${requestScope.page.pageSize}">i</a>
                </c:if>
            </c:forEach>
        </c:when>

        <%--第二种情况:总页码大于5，即 pageTotal > 5 的情况--%>
        <c:when test="${requestScope.page.pageTotal>5}">

            <c:choose>
                <%--a.当前页码为前面3个： 1,2,3 页码范围是：1-5--%>
                <c:when test="${requestScope.page.pageNo<=3}">
                    <%--<c:forEach begin="1" end="5" var="i">
                        ${i}
                    </c:forEach>--%>
                    <c:forEach begin="1" end="5" var="i">
                        <%--当前页--%>
                        <c:if test="${i==requestScope.page.pageNo}">
                            【${i}】
                        </c:if>
                        <%--非当前页--%>
                        <c:if test="${requestScope.page.pageNo!=i}">
                            <a href="bookServlet?action=page&pageNo=${i}&pageSize=${requestScope.page.pageSize}">${i}</a>
                        </c:if>
                    </c:forEach>
                </c:when>

                <%--当前页码为最后3个，假定为 8,9,10 页码范围是: pageTotal-4~pageTotal--%>
                <c:when test="${requestScope.page.pageNo>requestScope.page.pageTotal-3}">
                    <c:forEach begin="${requestScope.page.pageTotal-4}" end="${requestScope.page.pageTotal}" var="i">
                        <%--当前页--%>
                        <c:if test="${requestScope.page.pageNo==i}">
                            【${i}】
                        </c:if>
                        <%--非当前页--%>
                        <c:if test="${requestScope.page.pageNo!=i}">
                            <a href="bookServlet?action=page&pageNo=${i}&pageSize=${requestScope.page.pageSize}">${i}</a>
                        </c:if>
                    </c:forEach>
                </c:when>

                <%--最后一种是中间的情况，例如：4，5，6，7，8  范围是: pageNo-2~pageNo+2  --%>
                <c:otherwise>
                    <c:forEach begin="${requestScope.page.pageNo-2}" end="${requestScope.page.pageNo+2}" var="i">
                        <%--当前页--%>
                        <c:if test="${requestScope.page.pageNo==i}">
                            【${i}】
                        </c:if>
                        <%--非当前页--%>
                        <c:if test="${requestScope.page.pageNo!=i}">
                            <a href="bookServlet?action=page&pageNo=${i}&pageSize=${requestScope.page.pageSize}">${i}</a>
                        </c:if>
                    </c:forEach>
                </c:otherwise>

            </c:choose>

        </c:when>

    </c:choose>

    <%--同理，下一页我也按照这种思路来处理--%>
    <c:if test="${requestScope.page.pageNo==requestScope.page.pageTotal}">
        <%--直接让“下一页”消失--%>
    </c:if>
    <c:if test="${requestScope.page.pageNo!=requestScope.page.pageTotal}">
        <%--上一页是当前页+1--%>
        <a href="bookServlet?action=page&pageNo=${requestScope.page.pageNo+1}&pageSize=${requestScope.page.pageSize}">下一页</a>
    </c:if>


    <a href="bookServlet?action=page&pageNo=${requestScope.page.pageTotal}&pageSize=${requestScope.page.pageSize}">末页</a>
    ，共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录 到第<label for="pn_input"></label><input <%--value="4"--%> name="pn" id="pn_input"/>页
    <input type="button" value="确定">
</div>
<br><br><br>
<%@include file="../common/footer.jsp"%>
</body>
</html>