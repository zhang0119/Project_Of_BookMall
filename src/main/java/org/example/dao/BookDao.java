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

    /*
        查询图书的总记录数
        sql : select count(*) from t_book;
     */
    Integer queryForPageTotalCount();

    /*
        查询当前4页数据 (前面我设置了一页放置4条数据)
        sql : select * from t_book limit begin, pageSize;
        备注: begin= (pageNo-1)*pageSize
     */
    List<Book> queryForItems(int begin,int pageSize);

    /*
        根据价格区间查询图书
     */
    List<Book> queryForPriceRange(Integer min, Integer max,Integer begin,Integer pageSize);

    /*
        根据价格区间查询总记录数
     */
    Integer queryCountForPriceRange(Integer min,Integer max);
}
