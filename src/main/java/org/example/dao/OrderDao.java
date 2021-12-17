package org.example.dao;

import org.example.pojo.Order;

public interface OrderDao {

    /**
     * 保存订单
     * @param order 待保存的订单
     * @return 返回表示影响的行数<br/>返回-1表示保存失败
     */
    int saveOrder(Order order);
}
