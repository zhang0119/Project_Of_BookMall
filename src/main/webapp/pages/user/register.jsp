<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<meta charset="UTF-8">
		<title>会员注册页面</title>

		<%@include file="../common/head.jsp"%>
		<script type="text/javascript">

			//页面加载完成之后
			$(function(){
				//给注册绑定单击事件
				$("#sub_btn").click(function(){
					//验证用户名：必须由字母，数字下划线组成，并且长度为 5 到 12 位
					//1.获取用户名输入框里的内容
					let usernameText= $("#username").val();

					let usernamePattern = /^\w{5,12}$/;

					if(!usernamePattern.test(usernameText)){
						//用户名不合法
						$(".errorMsg").text("用户名不合法!");
						return false;
					}

					// 验证密码：必须由字母，数字下划线组成，并且长度为 5 到 12 位
					//1.先获取用户输入的密码
					let password = $("#password").val();
					let passwordPattern=/^\w{5,12}$/;

					if(!passwordPattern.test(password)){
						//密码不合法
						$(".errorMsg").text("密码格式不合法!");
						return false;
					}

					//确认密码
					let repwd = $("#repwd").val();
					if(repwd!==password){
						//两次密码不一致
						$(".errorMsg").text("两次密码不一致!");
						return false;
					}

					//验证邮箱
					//1.先获取用户输入的邮箱
					let email = $("#email").val();
					let emailPattern=/^([a-z0-9_-]+)@([\da-z-]+)\.([a-z]{2,6})$/;

					//判断邮箱格式是否正确
					if(!emailPattern.test(email)){
						//邮箱格式不正确
						$(".errorMsg").text("邮箱格式不正确!");
						return false;
					}

					//验证码验证
					//1.先获取用户输入的验证码
					let code = $("#code").val();
					//alert("去除空格前:["+code+"]");
					//去除前后空格
					//alert("去除空格后:["+$.trim(code)+"]");
					let newCode = $.trim(code);

					if(newCode==null || newCode===""){
						//表示验证码非法
						//提醒用户
						$(".errorMsg").text("验证码为空，请重新输入!");
						return false;
					}

					//如果用户注册合法必须清理之前的错误信息
					$("span.errorMsg").text("");

				})
			})
		</script>

	<style type="text/css">
		.login_form{
			height:420px;
			margin-top: 25px;
		}

	</style>
	</head>
	<body>
		<div id="login_header">

		</div>

			<div class="login_banner">

				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>

				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册会员</h1>
								<span class="errorMsg">

									<%--<%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%>--%>
									${empty requestScope.msg?"":requestScope.msg}
								</span>
							</div>
							<div class="form">
								<form action="userServlet?action=register" method="post">
									<label>用户名称：</label>
									<label for="username"></label><input class="itxt" type="text" placeholder="请输入用户名"
																		 autocomplete="off" tabindex="1" name="username" id="username"/>
									<br />
									<br />
									<label>用户密码：</label>
									<label for="password"></label><input class="itxt" type="password" placeholder="请输入密码"
																		 autocomplete="off" tabindex="1" name="password" id="password"/>
									<br />
									<br />
									<label>确认密码：</label>
									<label for="repwd"></label><input class="itxt" type="password" placeholder="确认密码"
																	  autocomplete="off" tabindex="1" name="repwd" id="repwd"/>
									<br />
									<br />
									<label>电子邮件：</label>
									<label for="email"></label><input class="itxt" type="text" placeholder="请输入邮箱地址"
																	  autocomplete="off" tabindex="1" name="email" id="email"/>
									<br />
									<br />
									<label>验证码：</label>
									<label for="code"></label><input class="itxt" type="text" style="width: 100px;" name="code" id="code"/>
									<img alt="" src="${requestScope.basePath}kaptcha.jpg" style="float: right;width: 160px" height="30px">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
								</form>
							</div>

						</div>
					</div>
				</div>
			</div>

		<%@include file="../common/footer.jsp"%>
	</body>
</html>