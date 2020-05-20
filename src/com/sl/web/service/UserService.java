package com.sl.web.service;

import com.sl.web.response.SqlResponse;
import com.sl.web.bean.UserBean;
import com.sl.web.dao.UserDao;

import static com.sl.web.response.SqlResponse.buildResponse;

public class UserService {
    private UserDao userDao = new UserDao();
    public UserService(){ }

    /**
     *
     * @param bean 传入的bean不需要id，会在登录成功后自动写入 ,phoneNumber yet
     * @return
     */
    public SqlResponse login(UserBean bean){
        int result = userDao.search(bean);
        return buildResponse(result,1);
    }

    public SqlResponse register(String username,String password,String phoneNumber){
        int result = userDao.insert(new UserBean(username,password,phoneNumber));
        return buildResponse(result,-1);
    }

    public SqlResponse update(int id,String username,String password,String newPassword){
        UserBean bean = new UserBean(id,username,password);
        UserBean newBean = new UserBean(id,username,newPassword);
        int result = userDao.update(bean,newBean);
        return buildResponse(result,1);
    }

    public SqlResponse verifyId(int id){
        int result = userDao.verify(id);
        return buildResponse(result,1);
    }

    /**
     * 释放数据库连接资源
     */
    public void close(){
        userDao.close();
    }
}
