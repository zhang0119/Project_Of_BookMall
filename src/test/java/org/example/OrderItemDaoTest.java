package org.example;

import org.example.dao.OrderItemDao;
import org.example.dao.impl.OrderItemDaoImpl;
import org.example.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

public class OrderItemDaoTest {

    private OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Test
    public void orderItemDaoTest(){

        OrderItem orderItem = new OrderItem(null,"泰拉瑞亚",1,new BigDecimal(18),new BigDecimal(18),"123456789");

        int result = orderItemDao.saveOrderItem(orderItem);

        System.out.println("result:"+result);
    }
}
