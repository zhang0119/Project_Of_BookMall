package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    /*
        图书编号
     */
    private Integer id;
    /*
        书名
     */
    private String name;
    /*
        图书单价
     */
    private BigDecimal price;
    /*
        作者
     */
    private String author;
    /*
        销量
     */
    private Integer sales;
    /*
        库存
     */
    private Integer stock;
    private String imgPath="static/img/default.jpg";

}
