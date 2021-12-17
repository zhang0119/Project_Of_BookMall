package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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


}
