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

        <c:forEach items="${requestScope.books}" var="book">
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

<%@include file="../common/footer.jsp"%>
</body>
</html>