<%@ page import="javax.naming.Context" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.ResultSetMetaData" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="com.levelup.webtest.dao.DatabaseUtilBean" %>
<%@ page import="com.levelup.webtest.dao.ModelResult" %>
<%--
  Created by IntelliJ IDEA.
  User: denis_zavadsky
  Date: 1/17/15
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%

    String tableName = request.getParameter("table");
    if (tableName==null){
%>
<form action="show_table.jsp" method="get">


    Table Name: <input type="text" name="table">
    <input type="submit">
</form>
<%
    } else {
        DatabaseUtilBean dbUtilBean = new DatabaseUtilBean();
        ModelResult result = dbUtilBean.selectFromTable(tableName);
        session.setAttribute("result",result);

%>
<table>
    <thead>

<%
    List<String> columns = result.getColumns();
    for (int i=0; i<columns.size();i++){
        String columnName = columns.get(i);
%>
<th><%= columnName%></th>
<%
        }

%>
    </thead>
    <tbody>
<%

    List<List<String>> rowList = result.getValues();
    for (List<String> row : rowList){

%>
<tr>
<%
    for (String value : row){

%>
    <td><%= value%></td>
<%
    }
%>
</tr>
<%
        }

%>
    </tbody>
</table>

<form action="insert" method="post">
    <%
        for (String columnName: columns){
    %>
    <%=columnName%>:<input type="text" name="<%=columnName%>">
    <%
        }
    %>
    <input type="submit">
</form>
<%

    }
%>
</body>
</html>
