<%@ page import="com.levelup.webtest.dao.DatabaseUtilBean" %>
<%@ page import="com.levelup.webtest.dao.ModelResult" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: denis_zavadsky
  Date: 1/29/15
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String tableName = request.getParameter("tableName");
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

<form id="insertForm" action="insert" method="post">
    <%
        for (String columnName: columns){
            if (!columnName.equalsIgnoreCase("id")){
    %>
    <%=columnName%>:<input type="text" name="<%=columnName%>">
    <%
            }
        }
    %>
    <input id="insertButton" type="button" value="Insert">
</form>
