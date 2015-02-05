<%--
  Created by IntelliJ IDEA.
  User: denis_zavadsky
  Date: 1/24/15
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String login = request.getParameter("login");
    String password = request.getParameter("password");
    if (login!=null && password!=null && !login.isEmpty() && !password.isEmpty()){
        // Check user credentials from database
%>
    <div>
        <span style="font-style: italic">Welcome Dear <%= login%></span>
    </div>
<%
    } else {
        Boolean loginInvalid = false;
        Boolean passwordInvalid = false;

        if (login==null || login.isEmpty()){
            loginInvalid = true;
        }
        if (password==null || password.isEmpty()){
            passwordInvalid = true;
        }

        %>

    <form action="#">
        <div>Login: <input id="login" name="login" type="text" <%=(loginInvalid!=null && loginInvalid)?"class=\"invalid\"":""%>
                value="<%=(loginInvalid!=null && loginInvalid)?"":login%>"></div>
        <div>Password: <input id="password" name="password" type="password"
            <%=(passwordInvalid!=null && passwordInvalid)?"class=\"invalid\"":""%>
                              value="<%=(passwordInvalid!=null && passwordInvalid)?"":password%>">
        </div>
        <input type="button" id="loginButton" value="Login">
    </form>

<%
    }
%>