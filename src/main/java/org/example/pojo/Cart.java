package org.example.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {

    /*
        商品总金额
     */
    private Integer totalCount;

    /*
        总商品金额
     */
    private BigDecimal totalPrice;

    /*
        购物车商品
     */
    private Map<Integer,CartItem> items= new LinkedHashMap<>();

    /**
     * 添加商品到购物车里
     * @param cartItem 商品项
     */
    public void addItem(CartItem cartItem){

        //先查看购物车中是否已经添加此商品<br/>
        CartItem item = items.get(cartItem.getId());

        if(item == null){
            //如果购物车没有该商品，放入集合中即可。
            items.put(cartItem.getId(),cartItem);
        }else{
            //如果已添加，则数量+1，更新商品总数量
            item.setCount(item.getCount()+1);
            //multiply 是 两个 decimal类型的数字相乘,更新总金额
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }

    }

    /**
     * 删除购物车中的商品
     * @param id 待删除的商品id
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }

    /**
     * 清空购物车
     */
    public void clear(){
        items.clear();
    }

    /**
     * 修改商品的数量
     * @param id 待修改的商品id
     * @param count 想要修改的商品的数量
     */
    public void updateCount(Integer id,Integer count){
        //1.先判断购物车中是否有此商品,通过获取商品--id
        CartItem cartItem = items.get(id);
        if(cartItem!=null){
            //如果商品项不为空，我们修改才有意义
            //首先是修改总数量totalCount
            cartItem.setCount(count);
            //其次是修改总金额totalPrice,即 当前总数量*当前单价=总金额
            cartItem.setTotalPrice(new BigDecimal(cartItem.getCount()).multiply(cartItem.getPrice()));

        }else{
            System.out.println("修改的商品不存在");
        }

    }

    //购物车里面的总数量不是set的，而是我们通过计算的到的。
    //同理，总金额也是我们计算得到的
    //set()方法我们直接删除
    //这里我们是每增加一本书，则totalCount+1 另外totalCount是放在内存中的
    public Integer getTotalCount() {

        //初始化总数量为0
        totalCount=0;

        for(Map.Entry<Integer,CartItem> entry: items.entrySet() ){
            totalCount += entry.getValue().getCount();
        }

        return totalCount;
    }

    //获得购物车里面的总金额，这里的方法和totalCount差不多的
    public BigDecimal getTotalPrice() {

        //先初始化总金额为0
        totalPrice = new BigDecimal("0");

        //遍历Map集合，从中get每次加入的图书单价，再进行累加
        for(Map.Entry<Integer,CartItem> entry : items.entrySet()){
            //entry.getValue().getTotalPrice().multiply(new BigDecimal(entry.getValue().getCount()));
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                "\n"+"items=" + items +
                '}';
    }

    /* 这里的toString()方法不能对totalCount和totalPrice生效了，因为不是设置，而是获得
       这里我们直接调用总数量和总金额的方法
    */
    /*@Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + totalCount +
                ", totalPrice=" + totalPrice +
                ", items=" + items +
                '}';
    }*/

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    public Cart() {
    }

    public Cart(Integer totalCount, BigDecimal totalPrice, Map<Integer, CartItem> items) {
        this.totalCount = totalCount;
        this.totalPrice = totalPrice;
        this.items = items;
    }
}
