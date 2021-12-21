package org.example.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单
 */
public class Order {

    /*
        订单号
     */
    private String orderId;

    /*
        订单创建时间
     */
    private Date createTime;

    /*
        订单价格
     */
    private BigDecimal price;

    /*
        订单状态
     */
    private Integer status;

    /*
        用户id
     */
    private Integer userId;

    public Order() {
    }

    public Order(String orderId, Date createTime, BigDecimal price, Integer status, Integer userId) {
        this.orderId = orderId;
        this.createTime = createTime;
        this.price = price;
        this.status = status;
        this.userId = userId;
    }

    public String getOrderId() {

        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + getOrderId() + '\'' +
                ", createTime=" + getCreateTime() +
                ", price=" + price +
                ", status=" + status +
                ", userId=" + getUserId() +
                '}';
    }
}
