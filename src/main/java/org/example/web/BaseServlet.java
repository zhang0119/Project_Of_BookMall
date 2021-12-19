package org.example.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public abstract class BaseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //解决 post请求中文乱码问题
        //一定要在获取请求参数之前调用才有效
        req.setCharacterEncoding("utf-8");

        String action = req.getParameter("action");

        try {
            //这里的this对象指向不明？一会debug看看this的指向对象是谁?
            //通过debug，发现此处的 this 对象是 UserServlet的实例对象。
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            //一定要记得把 异常 抛给filter过滤器
            throw new RuntimeException(e);
        }
    }
}
