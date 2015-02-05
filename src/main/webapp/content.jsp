<%--
  Created by IntelliJ IDEA.
  User: denis_zavadsky
  Date: 1/31/15
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title></title>
</head>
<body>
<div>

    Hello, ${sessionScope.user.firstName}  ${sessionScope.user.lastName}!
    You are logged with email:  ${sessionScope.user.email}


    <div>
        <form action="/logout" method="post">
        <input type="submit" value="log out"/>
        </form>
    </div>



</div>
</body>
</html>
