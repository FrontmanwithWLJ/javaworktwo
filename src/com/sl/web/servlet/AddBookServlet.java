package com.sl.web.servlet;

import com.sl.web.bean.BookBean;
import com.sl.web.bean.Message;
import com.sl.web.response.SqlResponse;
import com.sl.web.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/AddBook")
public class AddBookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = 0;
        HttpSession session = req.getSession();
        Message message = (Message) session.getAttribute("msg");
        if (message == null)message=new Message();
        BookBean bookBean = new BookBean();
        try {
            id = Integer.parseInt(req.getParameter("userid"));
            bookBean.setBookName(req.getParameter("bookname"));
            bookBean.setAuthorName(req.getParameter("authorname"));
            bookBean.setPublisherName(req.getParameter("publishername"));
            bookBean.setPrice(Float.parseFloat(req.getParameter("price")));
            bookBean.setDate(Date.valueOf(req.getParameter("date")));
        }catch (NullPointerException e){
            e.printStackTrace();
            message.setNewMessage("args error!!!");
        }
        BookService bookService = new BookService();
        SqlResponse response = bookService.add(id,bookBean);
        bookService.close();
        message.setNewMessage(response.getMsg());
        resp.sendRedirect("book/add_book.jsp");
    }
}
