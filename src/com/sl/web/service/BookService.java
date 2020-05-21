package com.sl.web.service;

import com.sl.web.bean.BookBean;
import com.sl.web.bean.SearchResult;
import com.sl.web.dao.BookDao;
import com.sl.web.dao.UserDao;
import com.sl.web.response.SqlResponse;

public class BookService {
    private BookDao bookDao = new BookDao();
    private UserDao userDao = new UserDao();

    public BookService() {
    }

    public SearchResult<BookBean> search(int userid, String bookname,String type, int page, int count) {
        int i = userDao.verify(userid);
        if (i != 1) return null;
        return bookDao.search(bookname,type,page,count);
    }

    public SqlResponse add(int userid,BookBean bean){
        int i = userDao.verify(userid);
        if (i != 1)return null;
        int count = bookDao.insert(bean);
        return SqlResponse.buildResponse(count,2);
    }

    public SqlResponse delete(int userid, int bookid) {
        int i = userDao.verify(userid);
        if (i != 1)return null;
        //int count = bookDao.delete(bookname);
        return null;
    }

    public void close() {
        bookDao.close();
        userDao.close();
    }

}
