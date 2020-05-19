package com.sl.web.dao;

import com.sl.web.bean.SearchResult;
import com.sl.web.bean.UserBean;

import java.sql.*;

public class UserDao extends BaseDao<UserBean> {

    /**
     * @param statement
     * @param username
     * @return 
     */
    private boolean checkName(Statement statement,String username){
        try {
            ResultSet resultSet = statement.executeQuery("select username into userinfo where username='"+username+"'");
            resultSet.last();
            if (resultSet.getRow() == 1){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    @Override
    public int insert(UserBean userBean) {
        int count = SQLNOCONNECTED;
        if (statement == null)return count;
        try {
            count = statement.executeUpdate("insert into userinfo (userid, username, password) values(0,\"" + userBean.getUserName() + "\",\"" + userBean.getPassword() + "\")");
        } catch (SQLException t) {
            t.printStackTrace();
            count = SQLERROR;
        }
        return count;
    }

    @Override
    public int update(UserBean bean, UserBean newBean) {
        int count = SQLNOCONNECTED;
        if (statement == null)
            return count;
        try {
            count = statement.executeUpdate("update userinfo set password='"
                    + newBean.getPassword() + "' where userid = " + bean.getID() + " and username='" + bean.getUserName() + "' and password='" + bean.getPassword() + "'");
        } catch (SQLException t) {
            t.printStackTrace();
            count = SQLERROR;
        }
        return count;
    }

    /**
     * 如果查询到结果会把唯一识别码写入到bean中
     * @param bean
     * @return
     */
    @Override
    public int search(UserBean bean) {
        int count = SQLNOCONNECTED;
        if (statement == null)return count;
        try {
            ResultSet resultSet = statement.executeQuery("select * from userinfo where username='"
                    + bean.getUserName() + "' and password = '" + bean.getPassword() + "'");
            resultSet.last();
            count = resultSet.getRow();
            if (count == 1) {//找到数据将唯一识别吗写到bean中
                //第一列就是唯一识别码
                bean.setID(resultSet.getInt(1));
            }
        } catch (SQLException t) {
            t.printStackTrace();
            count = SQLERROR;
        }
        return count;
    }

    @Override
    public int verify(int id) {
        int result = SQLNOCONNECTED;
        if (statement == null)return result;
        try {
            ResultSet rs = statement.executeQuery("select * from userinfo where userid=" + id);
            rs.last();
            result = rs.getRow();
            //存在一条数据证明此id有效
        } catch (SQLException t) {
            t.printStackTrace();
            result = SQLERROR;
        }
        return result;
    }
    //感觉这两个方法暂时没有必要实现
    @Override
    public SearchResult<UserBean> search(String key, int page, int count) {
        return null;
    }
    @Override
    public int delete(UserBean bean) {
        return 0;
    }


}