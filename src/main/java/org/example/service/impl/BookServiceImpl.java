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

    @Override
    public List<Book> bookOfPriceRange(Integer min, Integer max) {
        //这里我们通过峰值求出 总记录数
        return bookDao.queryForPriceRange(min, max);
    }
}
