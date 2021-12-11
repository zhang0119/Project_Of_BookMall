package org.example.web;

import org.example.pojo.Book;
import org.example.pojo.Page;
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

    /*
        处理删除图书的servlet
     */
    protected void deleteBookById(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //这里需要传入想要删除的图书编号id
        int bookId = WebUtils.parseInt(req.getParameter("id"), 0);
        //调用 deleteBookById() 方法删除所选图书
        bookService.deleteBookById(bookId);
        //重定向到 book_manager.jsp页面
        //这里注意删除也会有表单重复提价的问题,这里我们用重定向解决问题
        resp.sendRedirect(req.getContextPath()+"/bookServlet?action=list");
    }

    /*
        处理修改图书信息的servlet,这里分两步
        第一步，通过bookId找到需要修改的图书 即 getBookById
        第二步，修改图书信息并保存
        备注：book_edit.jsp 既做添加图书页面也做图书信息修改页面
     */
    protected void getBookById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int bookId = WebUtils.parseInt(req.getParameter("id"), 0);
        Book book = bookService.queryBookById(bookId);
        //将图书信息保存到request作用域中
        req.setAttribute("book",book);
        //请求转发到book_edit.jsp页面中
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }

    protected void updateBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //用户直接传入一个book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());

        //调用bookService.updateBook(book) 方法修改图书信息
        bookService.updateBook(book);

        //将修改后的图书信息保存到request作用域中，这一步必不可少，否则数据无法修改
        req.setAttribute("book",book);

        //重定向回到book_manager.jsp页面,并且还要有一个查询的操作
        //req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
        req.getRequestDispatcher("bookServlet?action=list").forward(req,resp);

    }

    /*
        这里我们编写处理图书分页的servlet
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.先获取用户传过来的 pageNo&pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), 5);

        //2.调用 service层的 page():Page对象
        Page<Book> page = bookService.page(pageNo, pageSize);

        /*System.out.println("----------------------");
        System.out.println("page对象:"+page);
        System.out.println("----------------------");*/

        //3.将page对象保存都request对象中
        req.setAttribute("page",page);

        //4.请求转发到到book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

}
