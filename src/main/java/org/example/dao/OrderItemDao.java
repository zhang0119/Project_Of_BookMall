package org.example.dao;

import org.example.pojo.OrderItem;

public interface OrderItemDao {

    /**
     * 保存订单项
     * @param orderItem 待保存的订单项
     * @return 返回表示影响的行数<br/>返回-1表示插入失败
     */
    int saveOrderItem(OrderItem orderItem);
}
