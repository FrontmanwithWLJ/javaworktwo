package com.sl.web.dao;

import com.sl.web.bean.BookBean;
import com.sl.web.bean.SearchResult;

import java.sql.SQLException;

public class BookDao extends BaseDao<BookBean> {

    @Override
    public int insert(BookBean bookBean) {
        int count = SQLNOCONNECTED;
        if (statement == null)return count;
        try {
            count = statement.executeUpdate(
                    "insert into bookinfo (bookname, publishername, date, price)\n" +
                    "values ('"
                    +bookBean.getBookName()+"','"
                    +bookBean.getPublisherName()+"','"
                    +bookBean.getDate()+"',"
                    +bookBean.getPrice()+");");
        } catch (SQLException t) {
            t.printStackTrace();
            count = SQLERROR;
        }
        return count;
    }

    @Override
    public int update(BookBean bookBean, BookBean newBean) {
        return 0;
    }

    /**
     *
     * @param key 查询关键字 如果key为空，则查询所有数据
     * @param page
     * @param count
     * @return
     */
    @Override
    public SearchResult<BookBean> search(String key, int page, int count) {
        return null;
    }

    @Override
    public int delete(BookBean bookBean) {
        return 0;
    }



    @Deprecated
    @Override
    public int search(BookBean bookBean) {
        return 0;
    }
    @Deprecated
    @Override
    public int verify(int id) {
        return 0;
    }
}
