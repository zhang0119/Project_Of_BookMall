package org.example.service;

import org.example.pojo.Book;
import org.example.pojo.Page;

import java.util.List;

public interface BookService {

    /*
        增加一本书
     */
    void addBook(Book book);

    /*
        通过编号删除一本书
     */
    void deleteBookById(Integer id);

    /*
        更新图书信息
     */
    void updateBook(Book book);

    /*
        通过某个编号查询一本书
     */
    Book queryBookById(Integer id);

    /*
        查询全部图书
     */
    List<Book> queryBooks();

    /*
        分页功能的实现,这里我们获取整个page对象模型
     */
    Page<Book> page(Integer pageNo,Integer pageSize);
}
