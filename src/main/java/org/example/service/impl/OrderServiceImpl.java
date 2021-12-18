package org.example.service.impl;

import org.example.dao.BookDao;
import org.example.dao.OrderDao;
import org.example.dao.OrderItemDao;
import org.example.dao.impl.BookDaoImpl;
import org.example.dao.impl.OrderDaoImpl;
import org.example.dao.impl.OrderItemDaoImpl;
import org.example.pojo.*;
import org.example.service.OrderService;

import java.util.Date;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();


    @Override
    public String createOrder(Cart cart, Integer userId) {

        //随机产生一个orderId
        String orderId = System.currentTimeMillis()+""+userId;

        //创建一个order对象
        //这里status我们默认设置为0，表示未发货
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
        //保存订单到数据库中
        orderDao.saveOrder(order);

        //遍历购物车里面的商品，将其拆分为订单项
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()){
            CartItem cartItem = entry.getValue();
            //将cartItem对象转变为orderItem对象，注意这里的所有商品的orderId都要保持一个
            //因为都在一个购物车里面，所以订单号必然是一样
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);

            //调用orderItem对象保存这些订单项
            orderItemDao.saveOrderItem(orderItem);

            //这里还需要我们更新库存和销量,这里我们要通过bookId找到我们修改的图书
            Book book = bookDao.queryBookById(cartItem.getId());
            //新的销量 +
            book.setSales(book.getSales()+cartItem.getCount());
            //新的库存 -
            book.setStock(book.getStock()-cartItem.getCount());
            //更新完整的图书信息
            bookDao.updateBook(book);

        }

        return orderId;
    }
}
