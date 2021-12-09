package org.example.web;

import org.example.pojo.Book;
import org.example.service.BookService;
import org.example.service.impl.BookServiceImpl;
import org.example.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.通过BookService查询所有图书
        List<Book> books = bookService.queryBooks();

        //2.把所有图书信息保存到request作用域中
        req.setAttribute("books",books);

        //3.请求转发到 /pages/manager/book_manager.jsp 页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

    /*
        添加图书的servlet
     */
    protected void addBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //已经将用户输入的图书信息放入到 book 实例中
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());

        //将图书保存到数据库中
        bookService.addBook(book);

        //跳到图书列表
        //下面的请求转发，会导致重复提交问题，这里我们使用重定向来解决问题
        //req.getRequestDispatcher("bookServlet?action=list").forward(req,resp);

        //重定向解决
        //req.getContextPath() 得到的是工程路径
        resp.sendRedirect(req.getContextPath()+"/bookServlet?action=list");
    }
}
