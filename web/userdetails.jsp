<%@ page import="models.User" %>
<%@ page import="models.News" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Comment" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>News</title>
    <%@include file="sdn.jsp" %>
</head>
<body>
<%@include file="navAdmin.jsp" %>
<div class="container col-6">
    <%
        User user = (User) request.getAttribute("user");
    %>
    <form action="edituseradmin" method="post">

        <h5 class="modal-title fs-5">Редактирование пользователя ID: <%=user.getId()%>
        </h5>
        <input type="hidden" name="id" value=<%=user.getId()%>>
        <label class="form-label"> Имя </label>
        <input type="text" class="form-control" name="name" value="<%=user.getName()%>">

        <label for="exampleInputEmail1" class="form-label"> Email </label>
        <input type="email" class="form-control" id="exampleInputEmail1" name="email"
               aria - describedby="emailHelp" value="<%=user.getEmail()%>">

        <label class="form-label"> Роль</label>
        <input type="number" class="form-control" name="role" min="1" max="2" value="<%=user.getRoleId()%>">

        <label class="form-label"> Текущий пароль </label>
        <input type="text" class="form-control" name="pass" value="<%=user.getPassword()%>">
        <br>
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-success">Внести изменения</button>
    </form>
</div>
</body>
</html>
