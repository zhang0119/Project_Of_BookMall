package org.example.web;

import org.example.pojo.Cart;
import org.example.pojo.Order;
import org.example.pojo.User;
import org.example.service.OrderService;
import org.example.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ClientOrderServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    /**
     * 这个方法是处理将订单信息遍历到order.jsp页面上
     * 仅仅做一个查询操作
     * @param req 请求对象
     * @param resp 相应对象
     */
    protected void queryOrder(HttpServletRequest req, HttpServletResponse resp){
        List<Order> orderList= orderService.queryOrder();
    }


    /**
     * 处理创建订单的servlet
     * @param req 请求对象
     * @param resp 相应对象
     * @throws IOException 可能会抛出IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //这里我们需要两个参数 cart 和 userId
        //cart 里面的数据我们可以从session域对象中获取
        //userId 同样也放在session域对象里面
        User user = (User) req.getSession().getAttribute("user");
        Integer userId=0;
        if(user!=null){
            userId = user.getId();
        }
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        String orderId="抱歉，你的订单不存在!";
        if(cart!=null){
            orderId = orderService.createOrder(cart, userId);

            //保存订单号到 session 域对象中，这里要防止表单重复提交
            req.getSession().setAttribute("orderId",orderId);
            //当我们生成订单后，此时应该要清空购物车
            cart.clear();
        }

        //最后我们重定向回到checkout页面
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }

}
