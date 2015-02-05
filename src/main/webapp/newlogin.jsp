<%@ page import="com.levelup.webtest.Constants" %>
<%--
  Created by IntelliJ IDEA.
  User: denis_zavadsky
  Date: 1/31/15
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
<form action="only" method="post">

    Email: <input type="text" name="<%=Constants.LOGIN_EMAIL_PARAMETER%>">
    Password: <input type="password" name="<%=Constants.LOGIN_PASSWORD_PARAMETER%>">
    <input type="submit">
</form>
</body>
</html>
