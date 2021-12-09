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
        <input type="hidden" name="action" value="addBook">
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
                <td><input name="name" type="text"/></td>
                <td><input name="price" type="text"/></td>
                <td><input name="author" type="text"/></td>
                <td><input name="sales" type="text"/></td>
                <td><input name="stock" type="text"/></td>

                <td><input type="submit" value="提交"/></td>
            </tr>


        </table>
    </form>


</div>

<%@include file="../common/footer.jsp"%>
</body>
</html>