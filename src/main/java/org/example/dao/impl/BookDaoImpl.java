package org.example.dao.impl;

import org.example.dao.BaseDao;
import org.example.dao.BookDao;
import org.example.pojo.Book;

import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql= "insert into t_book(`name`,`price`,`author`,`sales`,`stock`,`img_path`) values(?,?,?,?,?,?)";

        return update(sql,book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),book.getImgPath());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql = " delete from t_book where id = ? ";
        return update(sql, id);
    }

    @Override
    public int updateBook(Book book) {
        //更新book时带上id选择条件
        String sql = "update t_book set `name`=?,`price`=?,`author`=?,`sales`=?,`stock`=?,`img_path`=? where `id`=?";
        return update(sql,book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        //通过id找到一本书
        String sql="select * from t_book where id= ? ";
        return queryForOne(Book.class,sql,id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select * from t_book" ;
        return queryForList(Book.class,sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_book";
        //return (Integer) queryForSingleValue(sql);
        //这里不能直接转成 integer, 先转成 number 在调用 intValue()方法即可
        Number count= (Number) queryForSingleValue(sql);
        //返回总记录数
        return count.intValue();
    }

    @Override
    public List<Book> queryForItems(int begin, int pageSize) {
        //求当前页数据
        String sql = "select * from t_book limit ? , ? ";
        return queryForList(Book.class,sql,begin,pageSize);
    }

    @Override
    public List<Book> queryForPriceRange(Integer min, Integer max) {
        /*select * from t_book where price between 0 and 100 order by price;*/
        String sql = "select * from t_book where price between ? and ? order by price";

        return queryForList(Book.class,sql,min,max);
    }

    @Override
    public Integer queryCountByPriceRange(Integer min, Integer max) {
        String sql = "select count(*) from t_book where price between ? and ?";

        Number count = (Number)queryForSingleValue(sql,min,max);

        return count.intValue();
    }
}
