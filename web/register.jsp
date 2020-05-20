<%@ page import="com.sl.web.bean.Message" %><%--
  Created by IntelliJ IDEA.
  User: frontman
  Date: 2020/5/11
  Time: 12:10 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎注册我的图书管理系统</title>
</head>
<body>

<%
    Message message = (Message) session.getAttribute("msg");
    if (message == null) {
        message = new Message();
        message.setNewMessage("欢迎您的加入!");
    }
%>
<script type="text/javascript">
    function checkPassword(){
        const password = document.getElementById("password").value;
        const tmp = document.getElementById("confirmPassword").value;
        if (password === ""){
            document.getElementById("mmsg").innerText = "密码不能为空"
            return false
        }
        if (password !== tmp){
            document.getElementById("mmsg").innerText = "两次输入的密码不一致"
            return false
        }
        document.getElementById("mmsg").innerText = ""
        return true
    }
    function checkName() {
        let reg = /^[0-9a-zA-Z]+$/
        let name = document.getElementById("username").value
        if (name === ""){
            document.getElementById("namemsg").innerText = "不能为空"
            //document.getElementById("registerbtn").setAttribute("disable","disable");//设置不可点击
            //document.getElementById("registerbtn").disable = true
            return false
        }else if(!reg.test(name)){
            document.getElementById("namemsg").innerText = "only letter and number"
            return false
        }
        document.getElementById("namemsg").innerText = ""
        return true
        //document.getElementById("registerbtn").removeAttribute("disable");//设置不可点击
//        document.getElementById("registerbtn").disable = false
    }
    function checkPhoneNumber() {
        let number = document.getElementById("phone-number").value
        let reg = /^[0-9]+$/
        if (!reg.test(number)){
            document.getElementById("pnmsg").innerText = "only number"
            return false
        }else {
            document.getElementById("pnmsg").innerText = ""
            return true
        }
    }
    function doSubmit() {
        if (checkName()&&checkPassword()&&checkPhoneNumber()){
            document.getElementById("form").submit();
        }else {
            alert("check your information,please!")
        }
    }
</script>
<h1 align="center">欢迎注册我的图书管理系统</h1>
<form id="form" style="text-align: center" action="Register" method="post">
    <table align="center" border="0">
        <tr>
            <td><label style="color: red">*</label>账号：</td>
            <td>
                <input type="text" name="username" id="username" value="<%=message.getUserName()%>" onblur="checkName()">
                <label id="namemsg" style="color: red"></label>
            </td>
        </tr>
        <tr>
            <td><label style="color: red">*</label>密码：</td>
            <td>
                <input type="password" name="password" id="password">
            </td>
        </tr>
        <tr>
            <td><label style="color: red">*</label>确认密码：</td>
            <td>
                <input type="password" name="confirmPassword" id="confirmPassword" onblur="checkPassword()">
                <label id="mmsg" style="color: red;"></label>
            </td>
        </tr>
        <tr>
            <td>telephone:</td>
            <td>
                <input type="text" name="phone-number" id="phone-number" onblur="checkPhoneNumber()">
                <label id="pnmsg" style="color: red;"></label>
            </td>
        </tr>
    </table>
    <br>
    <br>
    <br>
    <br>
    <button id="registerbtn" style="color:#BC8F8F" onclick="doSubmit()">注册</button>
</form>
<br>
<div align="center">
<label><%=message.getNewMessage()%></label>
</div>
</body>
</html>
