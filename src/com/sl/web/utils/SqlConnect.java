package com.sl.web.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 统一管理连接对象
 */
public class SqlConnect {
    private DruidDataSource ds;
    private SqlConnect(){
        try {
            //ds =DruidDataSourceFactory.createDataSource(null);
            //Class.forName("com.alibaba.druid.pool.DruidDataSource");
            //Class.forName("com.mysql.cj.jdbc.Driver");
            ds = new DruidDataSource();
            ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
            ds.setUsername("root");
            ds.setPassword("wljsl+2240");
            ds.setUrl("jdbc:mysql://localhost:3306/java_work_two");
            ds.setMaxWait(6000);
            //在空闲连接回收器运行期间休眠时间，为负数则不运行回收器
            ds.setTimeBetweenEvictionRunsMillis(300000);
            ds.setInitialSize(1);//初始化数量
            ds.setMinIdle(1);//最小空闲连接数量 小于这个值则会重新创建
            ds.setMaxActive(10);//最大活跃数
            ds.setLoginTimeout(5000);//连接超时
            ds.setRemoveAbandoned(true);//连接超过限制时间 断开连接
            ds.setLogAbandoned(true);//连接超过时间时 输出错误log
            //检验数据库连接可用性时执行的sql查询
            //ds.setValidationQuery("SELECT 'x'");
            //设置连接是否会被空闲连接回收器检验，检验失败则从池中删除
            ds.setTestWhileIdle(true);
            //从连接池中取出之前检验是否可用
            ds.setTestOnBorrow(true);
            ds.init();//初始化
            //开启监控统计
            //d.setFilters("stat");
            //Connection conn = SqlConnect.getInstance().getConnection();

            //context = new InitialContext();
            //this.ds = (DataSource) context.lookup("java:comp/env/java_work_two");
        } catch (SQLException t) {
            t.printStackTrace();
            ds = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过此接口获得数据库连接对象，由连接池统一管理
     * @return Connection
     * @throws SQLException
     */
    public DruidPooledConnection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public Statement getStatement() throws SQLException {
        return getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
    }

    /**
     * 静态对象单例模式
     * @return
     */
    public static SqlConnect getInstance(){
        return Singleton.instance;
    }

    private static class Singleton{
        public static final SqlConnect instance = new SqlConnect();
    }
}
