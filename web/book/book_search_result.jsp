<%@ page import="java.util.ArrayList" %>
<%@ page import="com.sl.web.bean.BookBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    boolean isDel = request.getParameter("del") == null;
    String hiddenPageLabel = "hidden";
    Object o = session.getAttribute("search-result");
    ArrayList<BookBean> list;
    StringBuilder strTmp = new StringBuilder();
    if (o != null) {
        list = (ArrayList<BookBean>) o;
        if (list.size() == 0) {
            strTmp.append("has no result");
        } else {
            hiddenPageLabel = "visible";
            strTmp.append("<table style='text-align: center;alignment: center;border-color: black' border='1' cellspacing='0'> <tr><td> 唯一识别码 </td> <td> 书名 </td> <td> 作者 </td> <td> 出版商 </td> <td> 价格 </td> <td> 出版日期 </td>" + (isDel ? "" : "<td>操作</td>") + "</tr>");
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
                        .append(bean.getDate());
                if (isDel)
                    strTmp.append("</td> <td> <a onclick='del(this)'>delete</a>");
                strTmp.append("</td> </tr>");
            });
            strTmp.append("</table>");
        }
    }
    int totalPages = 0;
    int currentPage = 0;
    Object o1 = session.getAttribute("totalPages");
    if (o1 != null) totalPages = (Integer) o1;
    Object o2 = session.getAttribute("currentPage");
    if (o2 != null) currentPage = (Integer) o2;
%>
<html>
<script>
    document.getElementById()
</script>
<body>
<%=strTmp.toString()%>
<br>
<div style="width: 300px;visibility: <%=hiddenPageLabel%>">
    <label>total: <%=totalPages%> <br> current: <%=currentPage + 1%>
    </label>
    <div align="center">
        <form style="height: 27px;float: right;width: 50%" action="${pageContext.request.contextPath}/Search"
              method="get">
            <input name="direction" value="down" style="visibility: hidden">
            <button type="submit" style="width: 100%;">down</button>
        </form>
        <form style="height: 27px;float: left;width: 50%" action="${pageContext.request.contextPath}/Search"
              method="get">
            <input name="direction" value="up" style="visibility: hidden">
            <button type="submit" style="width: 100%">up</button>
        </form>
    </div>
</div>


</body>
</html>
