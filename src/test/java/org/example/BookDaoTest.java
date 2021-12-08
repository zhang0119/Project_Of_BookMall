package org.example;

import org.example.dao.BookDao;
import org.example.dao.impl.BookDaoImpl;
import org.example.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;

public class BookDaoTest {

    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBookTest(){

        Book book = new Book(null, "小王子", new BigDecimal(100), "King", 1000, 0, null);
        Book book2 = new Book(null, "活着", new BigDecimal(120), "余华", 1500, 200, null);
        Book book3 = new Book(null, "程序猿的一天", new BigDecimal(80), "tom", 1200, 30, null);

        int result = bookDao.addBook(book);
        System.out.println("result:"+result);

    }

    @Test
    public void deleteBookByIdTest(){

        int result = bookDao.deleteBookById(41);
        System.out.println(result);
    }

    @Test
    public void updateBookTest(){

        Book book = new Book(42, "水浒传", new BigDecimal(100), "施耐庵", 100, 10, null);
        int result = bookDao.updateBook(book);
        System.out.println("result:"+result);
    }
}
