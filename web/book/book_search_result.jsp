<%@ page import="java.util.ArrayList" %>
<%@ page import="com.sl.web.bean.BookBean" %><%--
  Created by IntelliJ IDEA.
  User: frontman
  Date: 2020/5/13
  Time: 2:11 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    boolean isDel = request.getParameter("del") == null;
    Object o = session.getAttribute("search-result");
    ArrayList<BookBean> list;
    StringBuilder strTmp = new StringBuilder();
    if (o != null){
        list = (ArrayList<BookBean>) o;
        if (list.size() ==0){
            strTmp.append("has no result");
            //return;
        }
        strTmp.append("<table style='text-align: center;alignment: center;border-color: black' border='1' cellspacing='0'> <tr><td>唯一识别码</td> <td>书名</td> <td>作者</td> <td>出版商</td> <td>价格</td> <td>出版日期</td>"+(isDel ?"":"<td>操作</td>")+"</tr>");
        list.forEach((bean) -> {
            strTmp.append("<tr> <td>").append(bean.getID())
                    .append("</td>  <td>")
                    .append(bean.getBookName())
                    .append("</td>  <td>")
                    .append(bean.getAuthorName())
                    .append("</td>  <td>")
                    .append(bean.getPublisherName())
                    .append("</td>  <td>")
                    .append(bean.getPrice())
                    .append("</td>  <td>")
                    .append(bean.getDate())
                    .append("</td> <tr>");
        });
    }
%>
<html>
<body>
<%=strTmp.toString()%>
</body>
</html>
