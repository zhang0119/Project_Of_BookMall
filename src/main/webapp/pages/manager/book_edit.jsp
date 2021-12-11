<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>编辑图书</title>
    <%@include file="../common/head.jsp"%>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1 a {
            color:red;
        }

        input {
            text-align: center;
        }
    </style>
</head>
<body>
<div id="header">
    <span class="wel_word">编辑图书</span>

    <%@include file="../common/manager_menu.jsp"%>
</div>

<div id="main">
    <form action="bookServlet" method="get">
        <%--这里我们需要判断当前页面提交时是修改图书信息还是添加图书操作--%>
        <%--
            如果requestScope.book有值，代表这是修改图书信息操作。
            <br/>反之，这是添加图书操作
            这里我使用jstl来判断
        --%>
            <c:if test="${empty requestScope.book}">
                <input type="hidden" name="action" value="addBook">
            </c:if>
            <c:if test="${not empty requestScope.book}">
                <input type="hidden" name="action" value="updateBook">
                <input type="hidden" name="id" value="${requestScope.book.id}">
            </c:if>

        <table>
            <tr>
                <td>名称</td>
                <td>价格</td>
                <td>作者</td>
                <td>销量</td>
                <td>库存</td>
                <td colspan="2">操作</td>
            </tr>

            <tr>
                <td><input name="name" type="text" value="${requestScope.book.name}"/></td>
                <td><input name="price" type="text" value="${requestScope.book.price}"/></td>
                <td><input name="author" type="text" value="${requestScope.book.author}"/></td>
                <td><input name="sales" type="text" value="${requestScope.book.sales}"/></td>
                <td><input name="stock" type="text" value="${requestScope.book.stock}"/></td>

                <td><input type="submit" value="提交"/></td>
            </tr>


        </table>
    </form>


</div>

<%@include file="../common/footer.jsp"%>
</body>
</html>