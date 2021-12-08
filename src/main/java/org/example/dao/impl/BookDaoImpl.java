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

        String sql = "update t_book set `name`=?,`price`=?,`author`=?,`sales`=?,`stock`=?,`img_path`=? where `id`=?";
        return update(sql,book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        return null;
    }

    @Override
    public List<Book> queryBooks() {
        return null;
    }
}
