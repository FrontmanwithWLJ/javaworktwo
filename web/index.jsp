<%@ page import="com.sl.web.bean.Message" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="msg" class="java.lang.String"/>
<%
    String searchText = (String)session.getAttribute("searchText");
    if (searchText == null)searchText = "";
    Integer selectIndexTmp = (Integer)session.getAttribute("selectIndex");
    if (selectIndexTmp == null)selectIndexTmp=0;
    int selectIndex = selectIndexTmp;
    Message message = (Message) session.getAttribute("msg");
    //存在message则说明用户已登录
    if (message == null){
        message = new Message();
        //session.setAttribute("msg",message);
        msg = "<a href=\"user/login.jsp\"><img src='pic/person.png' width='25' height='24' style='margin-bottom: -5px'>登录</a>\uD83C\uDF32<a href=\"user/register.jsp\">注册</a>\n";
    }else {
        msg = "<a href=''>录入图书</a> <a href=''>删除图书</a> <a href=\"user/user_info.jsp\"><img src='pic/person.png' width='25' height='24' style='margin-bottom: -5px'>"+message.getUserName()+"</a>\uD83C\uDF32<a href=\"javascript:void(0);\" onclick=\"quitLogin()\">退出</a>";
    }
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的图书管理系统</title>
    <style type="text/css">
        .search-box{
            width: 50%;
            height: 40px;
            margin-top: 0;
            border-radius: 20px;
            border: 1px solid gray;
            padding: 10px;
            font-size: 20px;
        }
    </style>
    <script type="text/javascript">
        function quitLogin() {
            //写了个空表提交,用来删除session里面的信息
            document.getElementById("form").submit()
            // history.go(0)
            // window.location = "login.jsp"
        }
        function keyPress(event) {
            //按下回车键
            if (event.keyCode === 13 && document.getElementById("search-box").value !== ""){
                search()
            }
        }
        function search() {
            let text = document.getElementById("search-box").value
            if (text.match(/^[ ]+$/))return//all space
            document.getElementById("search-form").submit()
        }
    </script>
</head>
<body>
<form id="form" action="${pageContext.request.contextPath}/Logout" method="get"></form>
<div style="text-align: right;margin-right: 10px">
    <%=msg%>
</div>
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
    <input placeholder="搜索书名/作者" name="search-text" id="search-box" class="search-box" type="text" value="<%=searchText%>" id="search-box" onkeypress="keyPress(event)">

</form>
<br>
<div align="center">
    <jsp:include page="book/book_search_result.jsp"></jsp:include>
<%--    <%=message.getNewMessage()%>--%>
</div>
</body>
</html>
