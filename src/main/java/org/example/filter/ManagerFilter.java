package org.example.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ManagerFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        //我们从session中获取user的对象，在这里判断
        Object user = req.getSession().getAttribute("user");

        if(user == null){
            //如果用户为空，从定向回到登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }else{
            //用户信息不为空，放行
            chain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {

    }
}
