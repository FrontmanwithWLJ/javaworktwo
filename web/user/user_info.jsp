<%@ page import="com.sl.web.bean.Message" %><%--
  Created by IntelliJ IDEA.
  User: frontman
  Date: 2020/5/12
  Time: 9:24 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Message message = (Message) session.getAttribute("msg");
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>个人信息</title>
    <script type="text/javascript">
        function doSubmit() {
            if (checkOldPassword()&&checkDoublePassword()){
                //alert(checkOldPassword()&&checkDoublePassword())
                document.getElementById("form").submit();
            }
        }
        function checkOldPassword() {
            const password = document.getElementById("old-password").value;
            const newPassword = document.getElementById("new-password").value;
            if (password === newPassword){
                document.getElementById("new-password-msg").innerText = "新密码不能与原密码一致"
                return false
            }
            return true
        }
        function checkDoublePassword(){
            const password = document.getElementById("new-password").value;
            const tmp = document.getElementById("confirm-new-password").value;
            if (password === ""){
                document.getElementById("new-password-msg").innerText = "密码不能为空"
                return false
            }
            if (password !== tmp){
                document.getElementById("mmsg").innerText = "两次输入的密码不一致"
                return false
            }
            document.getElementById("mmsg").innerText = ""
            return true
        }
    </script>
</head>
<body>
    <br>
    <br>
    <br>
    <br>

    <h3 align="center">修改密码</h3>
    <form id="form" style="text-align: center" action="${pageContext.request.contextPath}/UpdateInfo" method="post">
        <table align="center">
            <tr style="visibility: hidden">
                <td>
                    <input type="number" name="id" value="<%=message.getID()%>">
                </td>
            </tr>
            <tr style="visibility: hidden">
                <td>
                    <input type="text" name="username" value="<%=message.getUserName()%>">
                </td>
            </tr>
            <tr>
                <td>原密码：</td>
                <td>
                    <input type="password" name="password" id="old-password">
                </td>
            </tr>
            <tr>
                <td>新密码：</td>
                <td>
                    <input type="password" name="new-password" id="new-password" onblur="checkOldPassword()">
                    <label id="new-password-msg" style="color: red"></label>
                </td>
            </tr>
            <tr>
                <td>确认新密码：</td>
                <td>
                    <input type="password" id="confirm-new-password" onblur="checkDoublePassword()">
                    <label id="mmsg" style="color: red;"></label>
                </td>
            </tr>
        </table>
        <br>
        <br>
        <br>
        <button id="btn" type="button" style="color:#BC8F8F" onclick="doSubmit()">修改密码</button>
    </form>
<br>
<br>
</body>
</html>
