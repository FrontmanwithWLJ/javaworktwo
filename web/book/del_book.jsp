<%@ page import="com.sl.web.bean.Message" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String searchText = (String) session.getAttribute("searchText");
    if (searchText == null) searchText = "";
    Integer selectIndexTmp = (Integer) session.getAttribute("selectIndex");
    if (selectIndexTmp == null) selectIndexTmp = 0;
    int selectIndex = selectIndexTmp;
    Message message = (Message) session.getAttribute("msg");
    String msg = "";
    if (message != null)
        msg = "<a href='../index.jsp'>首页</a> <a href='add_book.jsp'>录入图书</a> <a href=\"user/user_info.jsp\"><img src='../pic/person.png' width='25' height='24' style='margin-bottom: -5px'>" + message.getUserName() + "</a>\uD83C\uDF32<a href=\"javascript:void(0);\" onclick=\"quitLogin()\">退出</a>";
    else message = new Message();
%>
<html>
<head>
    <title>DeleteBook</title>
    <style type="text/css">
        .search-box{
            width: 48%;
            height: 40px;
            margin-top: 0;
            border-radius: 20px;
            border: 1px solid gray;
            padding: 10px;
            font-size: 20px;
        }
    </style>
    <script>
    </script>
</head>
<body>
<div style="text-align: right;margin-right: 10px">
    <%=msg%>
</div>
<br>
<h1 align="center">Delete Book</h1>

<form id="search-form" style="align-content: center" method="post" action="Search">
    <input name="id" style="visibility: hidden" value="<%=message.getID()%>">
    <br>
    <select name="search-type" id="search-type" style="margin-left: 23%">
        <option value="bookname">书名</option>
        <option value="authorname">作者</option>
        <option value="bookid">ID</option>
    </select>
    <script>
        document.getElementById("search-type")[<%=selectIndex%>].selected = true
    </script>
    <input placeholder="搜索书名/作者" name="search-text" id="search-box" class="search-box" type="text"
           value="<%=searchText%>" id="search-box" onkeypress="keyPress(event)">
</form>

<div align="center">
    <jsp:include page="book_search_result.jsp">
        <jsp:param name="del" value="1"/>
    </jsp:include>
</div>

<br>
<br>
<br>
<br>
<div align="center">
    <%=message.getNewMessage()%>
</div>
</body>
</html>
