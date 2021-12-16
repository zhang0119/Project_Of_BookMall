package org.example.service.impl;

import org.example.dao.BookDao;
import org.example.dao.impl.BookDaoImpl;
import org.example.pojo.Book;
import org.example.pojo.Page;
import org.example.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {

        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(Integer pageNo, Integer pageSize) {

        //这里我们需要得到三个数据： pageTotalCount,pageTotal,items
        //a.获得 pageTotalCount 即总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        //b.获得 items 即当前页数据
        List<Book> items = bookDao.queryForItems((pageNo - 1) * pageSize, pageSize);

        //c.获得pageTotal
        int pageTotal = 0;
        int result= pageTotalCount%pageSize;
        if(result==0){
            //表示正好整除
            pageTotal = pageTotalCount/pageSize;
        }else{
            //结果余1，总页码 + 1
            pageTotal = (pageTotalCount/pageSize)+1;
        }

        return  new Page<Book>(pageNo,pageTotal,pageSize,pageTotalCount,items,null);
    }

    //这个业务方法主要的作用是封装用户设置的 page对象，并将其传送给servlet处理
    @Override
    public Page<Book> bookByPrice(Integer pageNo,Integer pageSize,Integer min, Integer max) {

        Page<Book> page = new Page<>();

        //设置当前页码
        page.setPageNo(pageNo);
        //设置每页显示数量
        page.setPageSize(pageSize);

        //这里我们拿到总记录数
        Integer counts = bookDao.queryCountForPriceRange(min,max);
        page.setPageTotalCount(counts);

        //总页码
        int pageTotal = 0;
        int result= counts%pageSize;
        if(result==0){
            //表示正好整除
            pageTotal = counts/pageSize;
        }else{
            //结果余1，总页码 + 1
            pageTotal = (counts/pageSize)+1;
        }
        page.setPageTotal(pageTotal);

        //获得 items 当前页数据
        //计算begin
        Integer begin = (pageNo-1)*pageSize;
        //这里我们是查出当前页数据，共计是4条，min和max限制了查找的价格区间，begin和pageSize是固定了查找的初始页码和每页显示的数量
        List<Book> items = bookDao.queryForPriceRange(min, max,begin,pageSize);
        page.setItems(items);

        return page;
    }
}
