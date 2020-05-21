package com.sl.web.servlet;

import com.sl.web.bean.BookBean;
import com.sl.web.bean.Message;
import com.sl.web.bean.SearchResult;
import com.sl.web.response.SqlResponse;
import com.sl.web.service.BookService;
import com.sl.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/Search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String direction = req.getParameter("direction");
        if (direction==null||"".equals(direction)){
            resp.setHeader("refresh","1;user/login.jsp");
            return;
        }
        HttpSession session = req.getSession();
        String page = (String) session.getAttribute("currentPage");
        if (page==null||"".equals(page)){
            resp.setHeader("refresh","1;user/login.jsp");
            return;
        }
        int pageTmp = Integer.parseInt(page);
        if (direction.equals("up")){
            session.setAttribute("currentPage",pageTmp+1);
        }else if (direction.equals("down")){
            session.setAttribute("currentPage",pageTmp-1);
        }
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String text = new String(req.getParameter("search-text").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String type = req.getParameter("search-type");
        HttpSession session = req.getSession();
        Message message = (Message) session.getAttribute("msg");
        if (message == null) {
            message = new Message();
            session.setAttribute("msg", message);
        }
        switch (type){
            case "bookname":
                session.setAttribute("selectIndex",0);
                break;
            case "authorname":
                session.setAttribute("selectIndex",1);
                break;
            case "bookid":
                session.setAttribute("selectIndex",2);
                break;
            default:
                message.setNewMessage("查询失败：参数错误");
                resp.sendRedirect("index.jsp");
        }
        session.setAttribute("searchText", text);

        int id = message.getID();
        //未登录状态
        if (id == -1) {
            message.setNewMessage("您当前未登录，请登录后重试");
            //前往登录，等候返回
            resp.sendRedirect("user/login.jsp");
            return;
            //req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
        Integer pageTmp = (Integer) session.getAttribute("currentPage");
        Integer countTmp = (Integer) session.getAttribute("count");
        int page = (pageTmp==null)?0:pageTmp;
        int count = (countTmp==null)?20:countTmp;

        UserService userService = new UserService();
        SqlResponse response = userService.verifyId(id);
        userService.close();//free connection
        if (response.isSuccess()) {
            if (!response.isNotFound()) {
                BookService bookService = new BookService();
                SearchResult<BookBean> result = bookService.search(id, text, type, page, count);
                bookService.close();//free
                if (result != null) {
                    session.setAttribute("search-result",result.getList());
                    session.setAttribute("totalPages",result.getTotalPage());
                    session.setAttribute("currentPage",page+1);
                    session.setAttribute("count",count);
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
