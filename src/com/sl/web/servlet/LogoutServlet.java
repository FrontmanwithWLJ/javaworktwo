package com.sl.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 退出登录，清除会话中的登录信息
 */
@WebServlet("/Logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //退出登录，就是把session中的信息删除
        req.getSession().removeAttribute("msg");
        HttpSession session = req.getSession();
        session.removeAttribute("search-result");
        //然后重定向打开主页
        resp.sendRedirect("index.jsp");
    }
}
