package com.sl.web.servlet;

import com.sl.web.bean.Message;
import com.sl.web.dao.UserDao;
import com.sl.web.response.SqlResponse;
import com.sl.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 处理注册请求
 */
@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String phoneNumber = req.getParameter("phone-number");

        UserService userService = new UserService();
        SqlResponse response = userService.register(username,password,phoneNumber);
        //由最上层决定关闭连接的时机
        userService.close();
        //获取会话，传递信息
        HttpSession session = req.getSession();
        Message message = (Message) session.getAttribute("msg");
        if (message == null){
            message = new Message();
            session.setAttribute("msg",message);
        }
        if (response.isFailure()){
            message.setNewMessage("注册失败，请重试:"+response.getMsg());
            req.getRequestDispatcher("user/register.jsp").forward(req,resp);
        }else {
            if (!response.isAlwaysExists()) {
                message.setUserName(username);
                message.setNewMessage("注册成功");
                req.getRequestDispatcher("user/login.jsp").forward(req, resp);
            }else {
                message.setNewMessage("用户已存在");
                req.getRequestDispatcher("user/register.jsp").forward(req,resp);
            }
        }
    }
}
