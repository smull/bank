<%--
  Created by IntelliJ IDEA.
  User: denis_zavadsky
  Date: 1/15/15
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Login Page</title>
</head>
<body>
<%
    String login = request.getParameter("login");
    String password = request.getParameter("pass");
    String email = request.getParameter("email");
    String address = request.getParameter("address");

    if ((login!=null && !login.isEmpty()) ||
            ( password!=null && !password.isEmpty())){
        session.setAttribute("login",login);
        session.setAttribute("password",password);
%>
    <form action="login.jsp" method="get">

        <%--Email: <input type="text" name="email">--%>
        Address: <input type="password" name="address">
        <input type="submit">
    </form>
<%
    }

    if (login == null && password == null){
        if (email!=null || address!=null) {
            login = (String) session.getAttribute("login");
            password = (String) session.getAttribute("password");
%>
    <div>
        <span>Login= <%= login%></span>
        <span>Password= <%= password%> </span>
        <span>Email= <%= email%></span>
        <span>Address= <%= address%> </span>
    </div>
<%
        } else {
%>
    <form action="login.jsp" method="get">

        Login: <input type="text" name="login">
        Password: <input type="password" name="pass">
        <input type="submit">
    </form>
<%
        }

    }

%>


</body>
</html>
