package org.example;

import org.example.dao.BookDao;
import org.example.dao.impl.BookDaoImpl;
import org.example.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

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

        int result = bookDao.deleteBookById(21);
        System.out.println(result);
    }

    @Test
    public void updateBookTest(){

        Book book = new Book(3, "小王子", new BigDecimal(100), "安托万·德·圣埃克苏佩里", 15000, 100, null);
        int result = bookDao.updateBook(book);
        System.out.println("result:"+result);
    }

    @Test
    public void queryBookByIdTest(){
        Book book = bookDao.queryBookById(1);
        System.out.println("book:"+book);
    }

    @Test
    public void queryBooksTest(){
        List<Book> books = bookDao.queryBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageTotalCountTest(){
        //求总记录数
        Integer count = bookDao.queryForPageTotalCount();
        //这里我们得到总记录数
        System.out.println(count);
    }

    @Test
    public void queryForItemsTest(){
        List<Book> pages = bookDao.queryForItems(1, 5);

        for (Book page : pages) {
            System.out.println(page);
        }
    }

}
