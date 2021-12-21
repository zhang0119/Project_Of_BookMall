package org.example.dao;

import org.example.pojo.Order;

import java.util.List;

public interface OrderDao {

    /**
     * 保存订单的接口
     * @param order 待保存的订单
     * @return 返回影响的行数<br/>如果返回-1表示保存失败
     */
    int saveOrder(Order order);

    List<Order> queryOrder();

}
