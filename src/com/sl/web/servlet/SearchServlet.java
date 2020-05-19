package com.sl.web.servlet;

import com.sl.web.bean.Message;
import com.sl.web.dao.UserDao;
import com.sl.web.response.SqlResponse;
import com.sl.web.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String text = req.getParameter("search-text");
        HttpSession session = req.getSession();
        session.setAttribute("searchText",text);
        Message message = (Message) session.getAttribute("msg");
        if (message == null){
            message = new Message();
            session.setAttribute("msg",message);
        }
        int id = message.getID();
        //未登录状态
        if (id == -1){
            message.setNewMessage("您当前未登录，请登录后重试");
            //前往登录，等候返回
            resp.sendRedirect("login.jsp");
            return;
            //req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
        UserService userService = new UserService();
        SqlResponse response = userService.verifyId(id);
        if (response.isSuccess()){
            if (!response.isNotFound()){
                message.setNewMessage(
                        "<table style='text-align: center;alignment: center;border-color: black' border='1'>\n" +
                                "    <tr>\n" +
                                "        <td>唯一识别码</td>\n" +
                                "        <td>书名</td>\n" +
                                "        <td>作者</td>\n" +
                                "        <td>价格</td>\n" +
                                "        <td>出版日期</td>\n" +
                                "        <td>出版商</td>\n" +
                                "    " +
                                "</tr>\n" +
                                "   \n" +
                                "        <td>《时间简史》</td>\n" +
                                "        <td>斯蒂芬·威廉·霍金</td>\n" +
                                "    " +
                                "</table>");
                resp.sendRedirect("index.jsp");
            }else {
                message.setNewMessage("登录信息错误,请重新登录");
                resp.sendRedirect("login.jsp");
            }
        }else {
            message.setNewMessage("查询失败："+response.getMsg());
            resp.sendRedirect("index.jsp");
        }
    }
}
