package org.example.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.pojo.User;
import org.example.service.UserService;
import org.example.service.impl.UserServiceImpl;
import org.example.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    protected void existsUsername(HttpServletRequest req,HttpServletResponse resp){
        //用户会传过来username,这里我们负责判断是否合法
        String username = req.getParameter("username");
        //调用userService.existsUsername()方法判断用户名是否存在
        boolean result = userService.existsUsername(username);
        //创建一个Map集合
        Map<String, Integer> number = new HashMap<>();
        if(result){
            //用户名存在，即不合法
            number.put("result",0);
        }else{
            //用户名不存在，用户名合法
            number.put("result",1);
        }

        //创建一个gson对象
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonObj = gson.toJson(number);
        //将这个json返回到前端
        try {
            resp.getWriter().write(jsonObj);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
       注销用户的servlet
    */
    protected void logout(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        //1.销毁Session 中用户登录的信息(或者销毁Session)
        req.getSession().invalidate();
        //2.重定向回到首页
        resp.sendRedirect(req.getContextPath());
    }

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
            //保存用户登录的信息到session域中
            //这是是做用户登录---显示用户名
            req.getSession().setAttribute("user",loginUser);

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
        //12.12这次我们解决这个验证码问题
        //a.先获取数据库自动生成的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //b.当我们获得服务器生成的验证码后，要立即删除原来的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        //暂时验证码是： funny

        //这里我们做一个判断，主要是判断服务器生成的验证码是否合法
        if(token!=null && token.equalsIgnoreCase(code)){
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
                //保存新用户的信息到request作用域中,这里我们使用session作用域
                //session作用域主要用来保存用户登录之后的信息
                //req.setAttribute("user",user);
                req.getSession().setAttribute("user",user);
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
