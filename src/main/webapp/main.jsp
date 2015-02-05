<%--
  Created by IntelliJ IDEA.
  User: denis_zavadsky
  Date: 1/24/15
  Time: 11:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Boolean loginInvalid = (Boolean) session.getAttribute("login_invalid");
    Boolean passwordInvalid = (Boolean) session.getAttribute("password_invalid");
%>
<html>
<head>
    <script src="http://code.jquery.com/jquery-1.11.1.js"></script>
    <script src="js/main.js"></script>

   <link rel="stylesheet" href="css/main.css">
    <title></title>
</head>
<body>
    <div class="content"></div>
    <div class="login">
        <form action="#">
            <div>Login: <input id="login" name="login" type="text" <%=(loginInvalid!=null && loginInvalid)?"class=\"invalid\"":""%>></div>
            <div>Password: <input id="password" name="password" type="password" <%=(passwordInvalid!=null && passwordInvalid)?"class=\"invalid\"":""%>></div>
            <input type="button" id="loginButton">
        </form>
    </div>
</body>
</html>
