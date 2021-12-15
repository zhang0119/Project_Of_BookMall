<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<%--分页处理--%>
<div id="page_nav">

    <a href="${requestScope.page.url}&pageNo=1">首页</a>

    <%--上一页是当前页-1--%>
    <%--这里我们不能简单的这样做，还有有一个判断，如果当前页是第一页，那么它的上一页也是第一页--%>
    <c:if test="${requestScope.page.pageNo==1}">
        <%--如果pageNo==1，我直接让"上一页"消失--%>
        <%--<a href="client/bookServlet?action=page&pageNo=${requestScope.page.pageNo}&pageSize=${requestScope.page.pageSize}">上一页</a>--%>
    </c:if>

    <c:if test="${requestScope.page.pageNo!=1}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
    </c:if>



    <c:choose>

        <%--第一种情况：即pageTotal<=5 时的情况，页码的范围是：1~pageTotal --%>
        <c:when test="${requestScope.page.pageTotal<=5}">
            <c:set var="begin" value="1"/>
            <c:set var="end" value="${requestScope.page.pageTotal}"/>
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
            <%--<a href="client/bookServlet?action=page&pageNo=${i}">${i}</a>--%>
            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>


    <%--同理，下一页我也按照这种思路来处理--%>
    <c:if test="${requestScope.page.pageNo==requestScope.page.pageTotal}">
        <%--直接让“下一页”消失--%>
    </c:if>
    <c:if test="${requestScope.page.pageNo!=requestScope.page.pageTotal}">
        <%--上一页是当前页+1--%>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
    </c:if>


    <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    ，共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录 到第<label for="pn_input"></label><input name="pn" id="pn_input"/>页
    <input id="endBtn" type="button" value="确定">

</div>



</body>
</html>
