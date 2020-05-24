package com.sl.web.servlet;

import com.sl.web.bean.BookBean;
import com.sl.web.bean.Message;
import com.sl.web.bean.SearchResult;
import com.sl.web.response.SqlResponse;
import com.sl.web.service.BookService;
import com.sl.web.service.UserService;
import com.sl.web.utils.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 处理搜索请求，
 * 只处理分页查询，其中每页数量为固定20
 * 当前页面坐标保存在会话里，key:currentPage type:Integer
 */
@WebServlet("/Search")
public class SearchServlet extends HttpServlet {
    /**
     * 这个请求会被两个页面，由于需要在查询完毕后重定向回去，所以写了个区别标记
     */
    private boolean isDelete = false;

    /**
     * 接受下一页上一页的请求，对查询结果的操作
     * 注：会话里单次只保存当前页面的查询结果，不会保存所有结果
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * direction:   up->
         *              down->
         */
        String direction = req.getParameter("direction");
        if (direction == null || "".equals(direction)) {
            resp.setHeader("refresh", "1;user/login.jsp");
            return;
        }
        String delete = req.getParameter("delete");
        if (delete!=null){
            isDelete = true;
        }
        HttpSession session = req.getSession();
        Integer page = (Integer) session.getAttribute("currentPage");
        if (page == null) {
            resp.setHeader("refresh", "1;user/login.jsp");
            return;
        }
        if (direction.equals("up")) {
            session.setAttribute("currentPage", page - 1);
        } else if (direction.equals("down")) {
            session.setAttribute("currentPage", page + 1);
        }
        doPost(req, resp);
    }

    //too long
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Object deleteTmp = req.getParameter("delete");
        if (deleteTmp != null) isDelete = true;
        //在这里确定重定向的页面,默认首页
        String url = isDelete ? "book/del_book.jsp" : "index.jsp";
        isDelete = false;
        HttpSession session = req.getSession();
        String tmp = req.getParameter("search-text");
        if (tmp == null) tmp = (String) session.getAttribute("searchText");
        if (tmp == null) {
            resp.setHeader("refresh", "0;" + url);
            return;
        }
        String text = StringUtil.toUTF_8(tmp);

        String type = req.getParameter("search-type");
        if (type == null) {
            Integer i = (Integer) session.getAttribute("selectIndex");
            if (i == null) {
                resp.setHeader("refresh", "0;" + url);
                return;
            }
            switch (i) {
                case 1:
                    type = "authorname";
                    break;
                case 2:
                    type = "bookid";
                    break;
                default:
                    type = "bookname";
            }
        }

        Message message = (Message) session.getAttribute("msg");
        if (message == null) {
            message = new Message();
            session.setAttribute("msg", message);
        }
        switch (type) {
            case "bookname":
                session.setAttribute("selectIndex", 0);
                break;
            case "authorname":
                session.setAttribute("selectIndex", 1);
                break;
            case "bookid":
                session.setAttribute("selectIndex", 2);
                break;
            default:
                message.setNewMessage("查询失败：参数错误");
                resp.sendRedirect(url);
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
        int page = (pageTmp == null) ? 0 : pageTmp;
        int count = 20;//固定返回结果集的最大值

        BookService bookService = new BookService();
        SearchResult<BookBean> result = bookService.search(id, text, type, page, count);
        bookService.close();//free
        if (result != null) {
            session.setAttribute("search-result", result.getList());
            session.setAttribute("totalPages", result.getTotalPage());
            session.setAttribute("currentPage", result.getPage());
            message.setNewMessage("search over.");
        } else {
            message.setNewMessage("can't finish this work,maybe server sql has no working");
        }
        resp.setHeader("refresh", "0;" + url);

    }
}
