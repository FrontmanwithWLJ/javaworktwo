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
import java.io.PrintWriter;

/**
 * 更新用户信息
 */
@WebServlet("/UpdateInfo")
public class UpdateInfoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //这里用了id 用户名和原密码来修改密码,
        int id = Integer.parseInt(req.getParameter("id"));
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String newPassword = req.getParameter("new-password");
        HttpSession session = req.getSession();
        Message message = (Message) session.getAttribute("msg");
        if (message == null){
            message = new Message();
            session.setAttribute("msg",message);
        }
        UserService userService = new UserService();
        SqlResponse response = userService.update(id,username,password,newPassword);
        userService.close();
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        if (response.isSuccess()){
            if (response.isNotFound()){
                message.setNewMessage("incorrect password!!!");
                resp.sendRedirect("user/user_info.jsp");
                return;
            }
            message.setUserName(username);
            message.setID(id);
            out.println("Success!!! After three seconds will jump to login,or <a href='user/login.jsp'>Click here!</a> ");
            resp.setHeader("refresh","3,user/login.jsp");
        }else {
            message.setNewMessage("出现错误："+response.getMsg());
            resp.sendRedirect("user/user_info.jsp");
        }
    }
}
