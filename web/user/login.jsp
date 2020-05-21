<%-- Created by IntelliJ IDEA. --%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page import="com.sl.web.bean.Message" %>
<html>
<%
    Message message = (Message) request.getAttribute("msg");
    if (message == null) {
        message = new Message();
        String msg = request.getParameter("msg");
        if (msg == null)msg = "welcome to join us!";
        message.setNewMessage(msg);
    }
%>
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
            if (password === "") {
                document.getElementById("password-msg").innerText = "must not null"
                return false
            }
            document.getElementById("password-msg").innerText = ""
            return true
        }

        function doSubmit() {
            if (checkName() && checkPassword()) {
                document.getElementById("form").submit()
            } else {
                alert("check your information,please")
            }
        }
    </script>
</head>
<body>

<h1 align="center">欢迎登录我的图书管理系统</h1>
<form style="text-align: center" id="form" action="${pageContext.request.contextPath}/Login" method="post">
    <table align="center" border="0">
        <tr>
            <td>账号：</td>
            <td>
                <input type="text" name="username" id="username" value="<%=message.getUserName()%>"
                       onblur="checkName()">
                <label id="username-msg" style="color: red"></label>
            </td>
        </tr>
        <tr>
            <td>密码：</td>
            <td>
                <input type="password" name="password" id="password" onblur="checkPassword()">
                <label id="password-msg" style="color: red;"></label>
            </td>
        </tr>
    </table>
    <br>
    <br>
    <br>
    <br>
    <button style="color:#BC8F8F" type="button" onclick="doSubmit()">登录</button>
</form>

<br>
<div align="center">
    <br>
    <label><%=message.getNewMessage()%>
    </label>
    <br>
    <label style="color: gray;size: B5">还没有账号吗? 点击 </label>
    <a href="register.jsp" style="color: red">注册</a>
    <br>
</div>
</body>
</html>