package org.example;

import org.example.pojo.Cart;
import org.example.pojo.CartItem;
import org.example.service.OrderService;
import org.example.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

public class OrderServiceTest {

    private OrderService orderService = new OrderServiceImpl();

    @Test
    public void createOrderTest(){

        //创建一个存储cartItem的Map集合
        LinkedHashMap<Integer, CartItem> items = new LinkedHashMap<>();

        //这里需要我们传入一个购物车对象和一个userId
        //购物车对象是放在 内存里面的，userId 必须是真实存在于数据库中
        Integer userId = 1;
        //创建一个购物车单品对象
        CartItem cartItem = new CartItem(null,"蛋炒饭",2,new BigDecimal(10),new BigDecimal(20));
        CartItem cartItem2 = new CartItem(null,"Java编程思想",5,new BigDecimal(6),new BigDecimal(30));

        items.put(1,cartItem);
        items.put(2,cartItem2);

        //创建一个购物车对象
        Cart cart = new Cart(7,new BigDecimal(90),items);

        String orderId = orderService.createOrder(cart, userId);

        System.out.println("订单号是:"+orderId);


    }
}
