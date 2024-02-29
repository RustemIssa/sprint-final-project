<%@ page import="java.util.ArrayList" %>
<%@ page import="models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
    <%@include file="sdn.jsp" %>
</head>
<body>
<%@include file="navAdmin.jsp" %>
<br>

<div class="container">
    <br>
    <table class="table table-hover">
        <thead>
        <tr>
            <th> Id</th>
            <th> Имя</th>
            <th> E-mail</th>
            <th> Роль</th>
            <th> Детали</th>
        </tr>
        </thead>
        <tbody>
        <% ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");
            for (User u : users) { %>
        <tr>
            <td>
                <%= u.getId()%>
            </td>
            <td>
                <%= u.getName()%>
            </td>
            <td>
                <%= u.getEmail()%>
            </td>
            <td>
                <%= u.getRoleId()%>
            </td>
            <td>
                <a href="/userdetails?id=<%=u.getId() %>" class="btn btn-info">Детали</a>
            </td>
                <%
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>
