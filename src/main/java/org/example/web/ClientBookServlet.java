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

public class ClientBookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    /**
     *  这个是前台代码，帮助用户查询分页信息
     * @param req 请求对象
     * @param resp 响应对象
     * @throws ServletException 异常
     * @throws IOException IO异常
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.先获取用户传过来的 pageNo&pageSize(用户第一次访问默认是第一页，显示4条数据)
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), 4);

        //2.调用 service层的 page():Page对象
        Page<Book> page = bookService.page(pageNo, pageSize);

        /*System.out.println("----------------------");
        System.out.println("page对象:"+page);
        System.out.println("----------------------");*/

        //3.将page对象保存都request对象中
        req.setAttribute("page",page);

        //4.请求转发到到book_manager.jsp页面
        //req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
        //这里我们请求转发到 pages/client/index.jsp
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }


}
