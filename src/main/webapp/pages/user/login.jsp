<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录页面</title>

    <%@include file="../common/head.jsp"%>

</head>
<body>
<div id="login_header">

</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎登录</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>会员</h1>
                    <a href="register.jsp">立即注册</a>
                </div>
                <div class="msg_cont">
                    <b></b>
                    <span class="errorMsg">
                        <%--请输入用户名和密码--%>
                        <%--<%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%>--%>
                        <%--这里我们使用更好用的EL表达式来显示数据--%>
                        <%--同理，后面所有的我们都用EL表达式输出数据--%>
                        ${empty requestScope.msg?"请输入用户名和密码":requestScope.msg}
					</span>
                </div>
                <div class="form">
                    <form action="userServlet?action=login" method="post">
                        <label>用户名称：</label>
                        <label>
                            <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username"
                            <%--这里是回显用户名的--%>
                            value="${empty requestScope.username?"":requestScope.username}"/>
                        </label>
                        <br />
                        <br />
                        <label>用户密码：</label>
                        <label>
                            <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" />
                        </label>
                        <br />
                        <br />
                        <input type="submit" value="登录" id="sub_btn" />
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>


<%@include file="../common/footer.jsp"%>
</body>
</html>