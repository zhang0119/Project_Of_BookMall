package org.example.web;

import org.example.pojo.User;
import org.example.service.UserService;
import org.example.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        //2.检查 验证码是否正确 --这里我们先写死<br/>
        //暂时验证码是： funny
        if("funny".equalsIgnoreCase(code)){
            //3.检查用户名是否合法
            if(userService.existsUsername(username)){
                //用户名已存在，不合法
                //调回注册页面
                req.getRequestDispatcher("/pages/user/register.html").forward(req,resp);
            }else{
                //用户名可用
                //调用 service保存到数据库中
                userService.registerUser(new User(null,username,password,email));
                //跳到注册成功后的页面 register_success.html
                req.getRequestDispatcher("/pages/user/register_success.jsp").forward(req,resp);
            }
        }else{
            //验证码不正确
            System.out.println("验证码["+code+"]不正确!");
            req.getRequestDispatcher("/pages/user/register.jsp").forward(req,resp);
        }


    }
}
