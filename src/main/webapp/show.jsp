<%--
  Created by IntelliJ IDEA.
  User: denis_zavadsky
  Date: 1/29/15
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script src="http://code.jquery.com/jquery-1.11.1.js"></script>
    <script src="js/database.js"></script>
    <script src="js/jquery-ui.min.js"></script>

    <link rel="stylesheet" href="css/jquery-ui.css">
    <link rel="stylesheet" href="css/jquery-ui.theme.css">
</head>
<body>
<form action="show_table.jsp" method="get">


    Table Name: <input id="tableName" type="text" name="table">
    <input id="send" type="button" value="Show">
</form>

<div id="tableContent" style="display: none;">

</div>

</body>
</html>
