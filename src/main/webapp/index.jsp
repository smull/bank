<%@ page import="java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<body>


<%
    request.getParameter("test");
    response.getWriter();


    String myName = "My Name is Denis";
    myName+="!!!";

%>

<c:if test="${!empty param.add}">



</c:if>

<% if (true){%>
<p></p>
<%}%>
<c:set value="${sessionScope.myvar}" var="elvar" property="name" scope="session"/>

<c:forEach items="${elvar}" var="value">
    <td>${value}</td>
</c:forEach>

<%

    ArrayList<String> list = new ArrayList<String>();
    for (String value:list){
    %>

<% } %>
<h2>Hello World!</h2>
<h3>
    <%= myName %>
</h3>
</body>
</html>
