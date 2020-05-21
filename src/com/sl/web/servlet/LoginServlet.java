package com.sl.web.servlet;

import com.sl.web.bean.Message;
import com.sl.web.response.SqlResponse;
import com.sl.web.bean.UserBean;
import com.sl.web.dao.UserDao;
import com.sl.web.response.ResponseCode;
import com.sl.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //唯一识别码会在查询到结果后写入bean
        UserBean bean = new UserBean(username,password);
        UserService userService = new UserService();
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        SqlResponse response = userService.login(bean);
        //释放资源
        userService.close();
        //将要保存在会话中的消息
        Message message = new Message();

        //命令执行成功
        if (response.isSuccess()) {
            if (response.isNotFound()){
                //message.setUserName(bean.getUserName());
                //message.setNewMessage("用户不存在");
                resp.sendRedirect("user/login.jsp?msg=Not found user or incorrect password!");
            }else {
                message.setID(bean.getID());
                message.setUserName(bean.getUserName());
                message.setNewMessage("登录成功");
                message.setPhoneNumber(bean.getPhoneNumber());
                req.getSession().setAttribute("msg",message);
                resp.sendRedirect("index.jsp");
            }
        }else{//执行失败
            resp.sendRedirect("user/login.jsp?msg=The server is not work! contact admin,please!");
            //resp.setHeader("refresh","3;user/login.jsp");
            //resp.getWriter().write("登录失败,疑似服务器故障,两秒后跳转至登录页面,或者<a href='user/login.jsp'>点我直接跳转</a>");
        }
    }
}
