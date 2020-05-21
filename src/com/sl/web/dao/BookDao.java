package com.sl.web.dao;

import com.sl.web.bean.BookBean;
import com.sl.web.bean.SearchResult;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class BookDao extends BaseDao<BookBean> {

    @Override
    public int insert(BookBean bookBean) {
        int count = SQLNOCONNECTED;
        if (statement == null) return count;
        try {
            count = statement.executeUpdate(
                    "insert into bookinfo (bookname, authorname,publishername, price,date)\n" +
                            "values ('"
                            + bookBean.getBookName() + "','"
                            + bookBean.getAuthorName() + "','"
                            + bookBean.getPublisherName() + "','"
                            + bookBean.getPrice() + "',"
                            + bookBean.getDate() + ");");
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
     * @param key   查询关键字 如果key为空，则查询所有数据 space will will split str to search
     * @param type
     * @param page  has the number of count ; in the page
     * @param count
     * @return
     */
    @Override
    public SearchResult<BookBean> search(String key, String type, int page, int count) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from bookinfo ");
        //key is not null or empty
        if (!"".equals(key)) {
            String[] keys = key.split(" ");//space split
            sql.append("where ");
            for (int i = 0; i < keys.length; i++) {
                if ("bookid".equals(type)) {
                    sql.append(type).append(" = ").append(keys[i]);
                } else
                    sql.append(type).append(" like '%").append(keys[i]).append("%'");
                if (i != keys.length - 1) {
                    sql.append(" or ");
                }
            }
            //sql.append(" group by bookname");
            try {
                ResultSet resultSet = statement.executeQuery(sql.toString());
                resultSet.last();
                int total = resultSet.getRow();
                resultSet.first();
                if (page<0)page=0;
                int current = count * page;
                int countTmp = count;
                if (current >= total) {//over flow ,get the last page
                    page = (total / count);
                    current = count * page;
                    countTmp = total % count;
                }
                resultSet.absolute(current);
                SearchResult<BookBean> result = new SearchResult<>();
                result.setCount(countTmp);
                result.setPage(page);
                result.setTotalPage((total/count) +1);
                ArrayList<BookBean> list = new ArrayList<>();
                result.setList(list);
                while (resultSet.next()) {
                    list.add(new BookBean(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getFloat(5),
                            resultSet.getDate(6)
                    ));
                    if (--countTmp == 0) {
                        break;
                    }
                }
//                for (int i = 0; i < count; i++) {
//                    list.add(new BookBean(
//                            resultSet.getInt(1),
//                            resultSet.getString(2),
//                            resultSet.getString(3),
//                            resultSet.getString(4),
//                            resultSet.getFloat(5),
//                            resultSet.getDate(6)
//                    ));
//                    resultSet.next();
//                }
                return result;
            } catch (SQLException t) {
                t.printStackTrace();
                return null;
            }
        }
        return null;
    }

    @Override
    public int delete(BookBean bookBean) {
        int count = SQLNOCONNECTED;
        if (statement == null) return count;
        try {
            count = statement.executeUpdate(
                    "delete from bookinfo where bookid="
                            + bookBean.getID());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            count = SQLERROR;
        }
        return count;
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
