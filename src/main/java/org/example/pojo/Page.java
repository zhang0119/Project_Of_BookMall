package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Page是分页的模型对象
 * @param <T> 是具体的模块的 javaBean类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> {

    //表示每页显示5条数据
    private static final Integer PAGE_SIZE = 5;

    //当前页码
    private Integer pageNo;

    //总页码
    private Integer pageTotal;

    //当前页显示数量
    private Integer pageSize= PAGE_SIZE;

    //总记录数
    private Integer pageTotalCount;

    //当前页数据
    private List<T> items;

}
