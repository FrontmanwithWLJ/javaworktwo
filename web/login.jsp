<%-- Created by IntelliJ IDEA. --%>
<%@ page import="java.sql.*" language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ page import="com.sl.web.bean.UserBean" %>
<%@ page import="com.sl.web.bean.Message" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>登录界面</title>
    <script type="application/javascript">
        function checkName() {
            let reg = /^[0-9a-zA-Z]+$/
            let name = document.getElementById("username").value
            if (name === "") {
                document.getElementById("username-msg").innerText = "must not null"
                return false
            } else if (!reg.test(name)) {
                document.getElementById("username-msg").innerText = "not match"
                return false
            }
            document.getElementById("username-msg").innerText = ""
            return true
        }
        function checkPassword() {
            let password = document.getElementById("password").value
            if (password === ""){
                document.getElementById("password-msg").innerText = "must not null"
                return false
            }
            document.getElementById("password-msg").innerText = ""
            return true
        }
        function submit() {
            if (checkName()&&checkPassword()){
                document.getElementById("form").submit()
            }else {
                alert("check your information,please")
            }
        }
    </script>
</head>
<body>
<%
    Message message = (Message) request.getAttribute("msg");
    if (message == null) {
        message = new Message();
        message.setNewMessage("欢迎您的到来!");
    }
%>
<h1 align="center">欢迎登录我的图书管理系统</h1>
<form style="text-align: center" id="form" action="Login" method="post">
    <table align="center" border="0">
        <tr>
            <td>账号：</td>
            <td>
                <input type="text" name="username" id="username" value="<%=message.getUserName()%>" onblur="">
                <label id="username-msg"></label>
            </td>
        </tr>
        <tr>
            <td>密码：</td>
            <td>
                <input type="password" name="password">
                <label id="password-msg"></label>
            </td>
        </tr>
    </table>
    <br>
    <br>
    <br>
    <br>
    <button style="color:#BC8F8F" onclick="submit()">登录</button>
</form>
<br>
<div align="center">
    <br>
    <label><%=message.getNewMessage()%>
    </label>
    <br>
    <label style="color: gray;size: B5">还没有账号吗? 点击 </label>
    <a href="${pageContext.request.contextPath}/register.jsp" style="color: red">注册</a>
    <br>
</div>
</body>
</html>