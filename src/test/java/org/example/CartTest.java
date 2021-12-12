package org.example;

import org.example.pojo.Cart;
import org.example.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

public class CartTest {

    @Test
    public void addItemTest(){

        //创建一个购物车对象
        Cart cart = new Cart();

        cart.addItem(new CartItem(1,"java从入门到精通",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(1,"java从入门到精通",3,new BigDecimal(100),new BigDecimal(300)));
        cart.addItem(new CartItem(2,"c++沉思录",4,new BigDecimal(120),new BigDecimal(480)));

        System.out.println("cart:"+cart);
    }

    @Test
    public void deleteItemTest(){
        Cart cart = new Cart();

        cart.addItem(new CartItem(1,"java从入门到精通",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(1,"java从入门到精通",3,new BigDecimal(100),new BigDecimal(300)));
        cart.addItem(new CartItem(2,"c++沉思录",4,new BigDecimal(120),new BigDecimal(480)));

        System.out.println("未删除之前的购物车:"+cart);

        cart.deleteItem(1);

        System.out.println("删除之后的购物车:"+cart);
    }
}
