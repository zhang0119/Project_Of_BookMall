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

    /**
     * 清空购物车
     * @param req 请求对象
     * @param resp 相应对象
     * @throws IOException 抛出异常对象
     */
    protected void clearCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //1.从session中获得购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //2.直接调用cart 对象里面的clear()方法,即清空购物车
        cart.clear();

        //3.重定向返回原页面
        resp.sendRedirect(req.getContextPath()+"/pages/cart/cart.jsp");
    }

    /**
     * 根据id删除购物车中的图书
     * @param req 请求对象
     * @param resp 相应对象
     * @throws IOException 抛出IO异常
     */
    protected void deleteItemInCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        /*
            思路：获取session中存储的cart.items，找到对应图书的id,然后删除即可.
         */
        //1.获得用户想要删除的book的id
        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        //通知session中删除指定id的图书
        //先得到购物陈对象,这里我们最好判断 cart对象是否为空
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart!=null){
            //根据id删除购物车中指定的图书
            cart.deleteItem(id);
        }

        //重定向回到cart.jsp页面
        resp.sendRedirect(req.getContextPath()+"/pages/cart/cart.jsp");
    }

    /**
     * 查询价格区间内的所有图书并实现分页
     * @param req 请求对象
     * @param resp 相应对象
     * @throws ServletException 处理的异常1
     * @throws IOException 处理的异常2
     */
    protected void bookOfPriceRange(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), 4);

        //从客户端接受最大值和最小值
        int min = WebUtils.parseInt(req.getParameter("min"), 0);
        //这里我默认价格区间的最大值是一个很大的值，未设置峰值时，默认查询所有图书
        int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);

        //调用 bookOfPriceRange()方法得到我们想要的 page对象
        Page<Book> page = bookService.bookByPrice(pageNo,pageSize,min, max);

        //在这里设置url即可,这里必须携带参数min和max
        //page.setUrl("client/bookServlet?action=bookOfPriceRange");

        StringBuilder sb = new StringBuilder("client/bookServlet?action=bookOfPriceRange");

        if(req.getParameter("min")!=null){
            sb.append("&min=").append(req.getParameter("min"));
        }

        if(req.getParameter("max")!=null){
            sb.append("&max=").append(req.getParameter("max"));
        }

        page.setUrl(sb.toString());

        //把这个page对象保存到 request 对象中
        req.setAttribute("page",page);

        //请求转发到 /client/index.jsp页面中
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
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
        //这里我们还有把书名(bookName)也保存到session域对象中，后面要用
        String bookName = book.getName();
        req.getSession().setAttribute("bookName",bookName);
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
        //resp.sendRedirect(req.getContextPath()+"/pages/cart/cart.jsp");

        //当我们点击“加入购物车”按钮后，页面是不能跳转的，只有我们点击“购物车”才能跳转到购物车
        //Referer是HTTP请求Header的一部分，
        // 当浏览器向Web服务器发送请求的时候，请求头信息一般需要包含Referer。
        // 该Referer会告诉服务器我是从哪个页面链接过来的，服务器基此可以获得一些信息用于处理。
        //现在我们重定向到这个referer
        resp.sendRedirect(req.getHeader("referer"));
        //这里输出结果是：http://localhost:8080/book/index.jsp


    }

    protected void pageForPriceRange(HttpServletRequest req, HttpServletResponse resp){

        Object books = req.getAttribute("books");

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

        //这里我觉得要做一个页码边界处理，防止用户输入不合理的页码
        //如果 当前页码 > 总页码，回到 最后一页
        //如果 当前页码 < 1，回到 第一页
        if(pageNo>page.getPageTotal()){
            pageNo=page.getPageTotal();
            page.setPageNo(pageNo);
            //重新查询一个新的page对象
            page = bookService.page(pageNo,pageSize);
        }else if(pageNo<1){
            //解决当前页码 <1
            page.setPageNo(1);
            page = bookService.page(1, pageSize);
        }

        System.out.println("----------------------");
        System.out.println("page对象:"+page);
        System.out.println("----------------------");

        page.setUrl("client/bookServlet?action=page");

        //3.将page对象保存都request对象中
        req.setAttribute("page",page);

        //4.请求转发到到book_manager.jsp页面
        //req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
        //这里我们请求转发到 pages/client/index.jsp
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }


}
