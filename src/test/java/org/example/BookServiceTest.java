package org.example;

import org.example.pojo.Book;
import org.example.pojo.Page;
import org.example.service.BookService;
import org.example.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class BookServiceTest {

    private BookService bookService = new BookServiceImpl();

    @Test
    public void addBookTest(){
        Book book = new Book(null, "活着", new BigDecimal(120), "余华", 1500, 200, null);

        bookService.addBook(book);
    }

    @Test
    public void deleteBookByIdTest(){
        bookService.deleteBookById(22);
    }

    @Test
    public void updateBookTest(){
        Book book = new Book(20, "活着", new BigDecimal(120), "余华", 1500, 200, null);

        bookService.updateBook(book);
    }

    @Test
    public void queryBookById(){
        Book book = bookService.queryBookById(1);
        System.out.println(book);
    }

    @Test
    public void queryBooks(){
        List<Book> books = bookService.queryBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    /*
        这个测试方法测试能否获得page对象的
     */
    @Test
    public void pageTest(){
        //这里要切记： pageNo默认的值最小都要是1，千万不能是0，否则查询失败
        Page<Book> page = bookService.page(1, 5);

        System.out.println(page);
    }

    @Test
    public void bookByPriceTest(){
        Page<Book> bookPage = bookService.bookByPrice(2, 4, 10, 50);
        System.out.println("bookPage:"+bookPage);
    }
}
