package org.example.dao.impl;

import org.example.dao.BaseDao;
import org.example.dao.OrderItemDao;
import org.example.pojo.OrderItem;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {

    @Override
    public int saveOrderItem(OrderItem orderItem) {

        String sql = "insert into t_order_item(id,name,count,price,total_price,order_id) values(?,?,?,?,?,?)" ;

        return execute(sql, orderItem.getId(), orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getOrderId());
    }
}
