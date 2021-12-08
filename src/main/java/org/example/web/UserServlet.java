package org.example.web;

import org.example.pojo.User;
import org.example.service.UserService;
import org.example.service.impl.UserServiceImpl;
import org.example.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    /*
        处理用户登录的servlet
     */
    public void login(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //自动将数据封装到user bean中
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        User loginUser = userService.login(user);

        //判断 loginUser
        if(loginUser==null){

            //把错误信息，和回显的表单项信息，保存到request域中
            req.setAttribute("msg","用户名或密码错误!");
            req.setAttribute("username",username);

            //如果用户为空,登录失败,返回登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else{
            //登录成功
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }

    /*
        处理用户注册的servlet
     */
    public void register(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        //BeanUtils主要用在这个地方
        //这里user对象中的数据已经封装好了，可以直接使用user对象
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        //2.检查 验证码是否正确 --这里我们先写死<br/>
        //暂时验证码是： funny
        if("funny".equalsIgnoreCase(code)){
            //3.检查用户名是否合法
            if(userService.existsUsername(username)){
                //用户名已存在，不合法
                //携带不合法信息，提醒用户
                req.setAttribute("msg","用户名已存在,换个试试!");
                //调回注册页面
                req.getRequestDispatcher("/pages/user/register.jsp").forward(req,resp);
            }else{
                //用户名可用
                //调用 service保存到数据库中
                userService.registerUser(user);
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
