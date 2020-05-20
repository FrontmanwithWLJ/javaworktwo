package com.sl.web.dao;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.sl.web.bean.SearchResult;
import com.sl.web.utils.SqlConnect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 以下所有方法执行成功则返回操作数据的行数，失败则返回非正数
 * @return  -1 数据库未连接
 *          -2 数据库错误
 */
public abstract class BaseDao<T> {
    /**数据库未连接*/
    public static final int SQLNOCONNECTED = -1;
    /**数据库错误*/
    public static final int SQLERROR = -2;
    /**name exists*/
    public static final int NAMEEXISTS = -3;

    Statement statement = null;
    DruidPooledConnection connection = null;

    public BaseDao() {
        try {
            connection = SqlConnect.getInstance().getConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException t) {
            t.printStackTrace();
        }
    }

    /**
     * 插入新数据
     *
     * @param t
     * @return
     */
    int insert(T t) {
        return 0;
    }

    /**
     * 更新数据
     *
     * @param t
     * @param newBean
     * @return
     */
    int update(T t, T newBean) {
        return 0;
    }

    /**
     * 查找数据
     *
     * @param t
     * @return 查询到的条数
     */
    int search(T t) {
        return 0;
    }

    /**
     * 分页查询
     *
     * @param key 查询关键字 如果key为空，则查询所有数据
     * @return 返回查询结果
     */
    SearchResult<T> search(String key, int page, int count) {
        return null;
    }

    /**
     * 删除数据
     *
     * @param t
     * @return
     */
    int delete(T t) {
        return 0;
    }

    /**
     * 用于检验唯一识别码是否有效
     *
     * @param id
     * @return
     */
    int verify(int id) {
        return 0;
    }

    //释放资源,将连接对象返还给连接池
    public void close(){
        try {
            statement.close();
            connection.close();
        } catch (SQLException t) {
            t.printStackTrace();
        }
    }
}
