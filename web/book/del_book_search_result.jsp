<%@ page import="java.util.ArrayList" %>
<%@ page import="com.sl.web.bean.BookBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    boolean isDel = true;
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
            strTmp.append("<table id='tab' style='text-align: center;alignment: center;border-color: black' border='1' cellspacing='0'> <tr><td> 唯一识别码 </td> <td> 书名 </td> <td> 作者 </td> <td> 出版商 </td> <td> 价格 </td> <td> 出版日期 </td>" + (isDel ?"<td>操作</td>":"") + "</tr>");
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
                    strTmp.append("</td> <td> <button onclick='delColumn(this)' name='").append(bean.getID()).append("' >delete</button>");
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
    function delColumn(view){
        //alert("delete"+view.name)
        const i = view.parentNode.parentNode.rowIndex;
        document.getElementById("bookid").value = view.name
        document.getElementById("delete-book").submit()
        //alert(view.name+":"+document.getElementById("bookid").value)
        //document.getElementById("tab").deleteRow(i);
    }
</script>
<body>
<%=strTmp.toString()%>
<div style="width: 300px;visibility: <%=hiddenPageLabel%>">
    <label>total: <%=totalPages%>  current: <%=currentPage + 1%>
    </label>
    <div align="center">
        <form style="height: 27px;float: right;width: 50%" action="${pageContext.request.contextPath}/Search<%= isDel?"?del=0":""%>"
              method="get">
            <input name="direction" value="down" style="visibility: hidden">
            <button type="submit" style="width: 100%;">down</button>
            <input name="delete" value="0" style="visibility: hidden">
        </form>
        <form style="height: 27px;float: left;width: 50%" action="${pageContext.request.contextPath}/Search<%= isDel?"?del=0":""%>"
              method="get">
            <input name="direction" value="up" style="visibility: hidden">
            <button type="submit" style="width: 100%">up</button>
            <input name="delete" value="0" style="visibility: hidden">
        </form>
    </div>
</div>

<form action="../DeleteBook" id="delete-book" method="post" style="visibility: hidden">
    <input name="bookid" id="bookid">
</form>

</body>
</html>
