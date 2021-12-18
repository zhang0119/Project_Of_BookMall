package org.example.service;

import org.example.pojo.Cart;

public interface OrderService {

    String createOrder(Cart cart,Integer userId);
}
