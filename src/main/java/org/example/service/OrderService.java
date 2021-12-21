package org.example.service;

import org.example.pojo.Cart;
import org.example.pojo.Order;

import java.util.List;

public interface OrderService {

    String createOrder(Cart cart,Integer userId);

    /**
     * 从数据库中查询订单
     * @return 返回从数据中查询到的结果
     */
    List<Order> queryOrder();
}
