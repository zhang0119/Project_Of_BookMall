package org.example;

import org.example.pojo.Book;
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
}
