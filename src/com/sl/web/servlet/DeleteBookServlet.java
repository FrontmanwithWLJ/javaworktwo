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
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * 删除图书
 */
@WebServlet("/DeleteBook")
public class DeleteBookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String bookidtmp = req.getParameter("bookid");
        Object o = session.getAttribute("msg");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        int bookid = -1;
        if ("".equals(bookidtmp)||(o == null)){
            out.println("Do you login? After three seconds turn to login or <a href='user/login.jsp'>click here!</a>");
            resp.setHeader("refresh","3;user/login.jsp");
            return;
        }
        bookid = Integer.parseInt(bookidtmp);
        Message message = (Message)o;//登录信息保存在会话里，获取用户的识别码，验证通过才执行删除
        BookService bookService = new BookService();
        SqlResponse response = bookService.delete(message.getID(),bookid);
        bookService.close();
        if (response.isSuccess()&&!response.isNotFound()){
            //搜索结果保存在会话里的，
            Object tmp = session.getAttribute("search-result");
            if (tmp != null){
                ArrayList<BookBean> list = (ArrayList<BookBean>) tmp;
                for (int i=0;i<list.size();i++){
                    if (list.get(i).getID() == bookid){
                        list.remove(i);
                        resp.setHeader("refresh","0;book/del_book.jsp?del=0");
                        return;
                    }
                }
                out.println("Not found this book,not delete book;After three seconds turn back or <a href='book/del_book.jsp'>click here!</a>");
                resp.setHeader("refresh","3;book/del_book.jsp?del=0");
            }
        }
        out.println("Do you login? After three seconds turn to login or <a href='user/login.jsp'>click here!</a>");
        resp.setHeader("refresh","3;user/login.jsp");
    }
}
