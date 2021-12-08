package org.example.dao;

import org.example.pojo.Book;

import java.util.List;

public interface BookDao {

    /*
        增加一本书
     */
    int addBook(Book book);

    /*
        删除一本书
     */
    int deleteBookById(Integer id);

    /*
        更新一本书信息
     */
    int updateBook(Book book);

    /*
        查询一本书
     */
    Book queryBookById(Integer id);

    /*
        查询所有图书
     */
    List<Book> queryBooks();

}
