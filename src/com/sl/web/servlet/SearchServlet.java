package com.sl.web.servlet;

import com.sl.web.bean.BookBean;
import com.sl.web.bean.Message;
import com.sl.web.bean.SearchResult;
import com.sl.web.dao.UserDao;
import com.sl.web.response.SqlResponse;
import com.sl.web.service.BookService;
import com.sl.web.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@WebServlet("/Search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String text = new String(req.getParameter("search-text").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        HttpSession session = req.getSession();
        session.setAttribute("searchText", text);
        Message message = (Message) session.getAttribute("msg");
        if (message == null) {
            message = new Message();
            session.setAttribute("msg", message);
        }
        int id = message.getID();
        //未登录状态
        if (id == -1) {
            message.setNewMessage("您当前未登录，请登录后重试");
            //前往登录，等候返回
            resp.sendRedirect("user/login.jsp");
            return;
            //req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
        int page = Integer.parseInt(req.getParameter("page"));
        int count = Integer.parseInt(req.getParameter("count"));
        UserService userService = new UserService();
        SqlResponse response = userService.verifyId(id);
        userService.close();//free connection
        if (response.isSuccess()) {
            if (!response.isNotFound()) {
                BookService bookService = new BookService();
                SearchResult<BookBean> result = bookService.search(id, text, page, count);
                bookService.close();//free
                if (result != null) {
                    session.setAttribute("search-result",result.getList());
                    message.setNewMessage("search over.");
                } else {
                    message.setNewMessage("can't finish this work,maybe server sql has no working");
                }
                resp.sendRedirect("index.jsp");
            } else {
                message.setNewMessage("登录信息错误,请重新登录");
                resp.sendRedirect("user/login.jsp");
            }
        } else {
            message.setNewMessage("查询失败：" + response.getMsg());
            resp.sendRedirect("index.jsp");
        }
    }
}
