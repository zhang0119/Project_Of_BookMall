package org.example.dao.impl;

import org.example.dao.BaseDao;
import org.example.dao.OrderDao;
import org.example.pojo.Order;

public class OrderDaoImpl extends BaseDao implements OrderDao {

    @Override
    public int saveOrder(Order order) {

        String sql = " insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?) ";

        return execute(sql, order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getStatus(), order.getUserId());

    }

    /*@Override
    public List<Order> queryOrder() {

        String sql = "select * from t_order";

        return queryForList(Order.class,sql);
    }*/
}
