package org.example.web;

import org.example.pojo.Book;
import org.example.pojo.Cart;
import org.example.pojo.CartItem;
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

    private Cart cart = new Cart();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    /*我现在要写一个方法，专门处理用户将商品放入购物车的需求*/
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //1.用户点击“加入购物车”按钮后，会给后端传入添加的图书信息
        int bookId = WebUtils.parseInt(req.getParameter("id"), 0);
        //这里我通过bookId来查询这本图书的信息
        Book book = bookService.queryBookById(bookId);

        //这里我要做一个转化，即把book对象转变为cartItem对象
        //用户每次只能点击一次"加入购物车"的按钮，这里默认就是第一次提交的，总数量1，总金额就是第一本书的价格
        //后面都开始调用 cartItem 的 getTotalCount()方法和 getTotalPrice()方法
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());

        //此外，我还要更新总金额和总数量
        //这里简单的 new Cart()是不行的。如果我是第二次买同样一本书，那么又new了一次对象。显然不合理.
        //这里我们要做个判断
        //如果session域对象中没有购物车，那我就创建一个new Cart().
        //如果session中有购物车，那我直接将商品放入即可，不用重复新建对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }

        //购物车不为空，直接将商品加入购物车
        //在Cart类的addItem方法中我们已经将总数量和总金额都计算好了，这里直接添加即可!
        cart.addItem(cartItem);

        //测试代码
        System.out.println("-----------------------------");
        System.out.println("cartItem:"+cartItem);
        System.out.println("cart:"+cart);
        System.out.println("-----------------------------");



        //这里我们重定向回购物车页面 cart.jsp页面
        //为防止表单的重复提价，使用重定向方式
        resp.sendRedirect(req.getContextPath()+"/pages/cart/cart.jsp");

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