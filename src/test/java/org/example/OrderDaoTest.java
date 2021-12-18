package org.example;

import org.example.dao.OrderDao;
import org.example.dao.impl.OrderDaoImpl;
import org.example.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDaoTest {

    private OrderDao orderDao = new OrderDaoImpl();

    @Test
    public void orderDaoTest(){

        Order order = new Order("123456789",new Date(),new BigDecimal(99),0,1);

        int i = orderDao.saveOrder(order);

        System.out.println(i!=-1?"保存订单成功":"保存订单失败");
    }

    /*@Test
    public void queryOrder(){

        List<Order> orders = orderDao.queryOrder();

        System.out.println(orders);
    }*/
}
