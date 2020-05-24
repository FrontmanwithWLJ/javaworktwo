<%@ page import="com.sl.web.bean.Message" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Message message = (Message) session.getAttribute("msg");
    String msg = "";
    if (message != null)
        msg = "<a href='../index.jsp'>首页</a> <a href='del_book.jsp?clear=0'>删除图书</a> <a href=\"../user/user_info.jsp\"><img src='../pic/person.png' width='25' height='24' style='margin-bottom: -5px'>" + message.getUserName() + "</a>\uD83C\uDF32<a href=\"javascript:void(0);\" onclick=\"quitLogin()\">退出</a>";
    else message = new Message();
%>
<html>
<head>
    <title>Add Book</title>
    <script type="javascript">
        function quitLogin() {
            //写了个空表提交,用来删除session里面的信息
            document.getElementById("form").submit()
            // history.go(0)
            // window.location = "login.jsp"
        }
    </script>
</head>
<body>
<div style="text-align: right;margin-right: 10px">
    <%=msg%>
</div>
<br>
<br>
<br>
<div align="center">

    <form style="align-content: center" action="${pageContext.request.contextPath}/AddBook" method="post">
        <input style="visibility: hidden" name="userid" value=<%=message.getID()%>>
        <table>
            <tr>
                <td>bookname</td>
                <td>authorname</td>
                <td>publihername</td>
                <td>price</td>
                <td>date</td>
            </tr>
            <tr>
                <td><input name="bookname"/></td>
                <td><input name="authorname"/></td>
                <td><input name="publishername"/></td>
                <td><input name="price" type="number"/></td>
                <td><input name="date" type="date"/></td>
            </tr>
        </table>
        <br>
        <br>
        <br>
        <br>
        <button style="align-content: end;width: 10%" type="submit">submit</button>
    </form>
</div>

<div align="center"><%=message.getNewMessage()%>
</div>
</body>
</html>
