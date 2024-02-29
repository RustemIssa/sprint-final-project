<%@ page import="models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <%@include file="sdn.jsp" %>
</head>
<body>
<%
    User currentUser = (User) request.getSession().getAttribute("currentUser");
    if (currentUser.getRoleId() == 1) {

%>
<%@include file="navAdmin.jsp" %>
<%
} else {
%>
<%@include file="nav.jsp" %>
<%
    }
%>
<div class="container col-4">
    <%
        User user = (User) request.getAttribute("profile");
        if (user != null) {
    %>
    <h2> Привет <%=user.getName()%>
    </h2>
    <h5>E-mail: <%=user.getEmail()%>
    </h5>
    <%
        }
        if (user.getRoleId() == 1) {

    %>
    <h5> Ваш статус - администратор</h5>
    <%
    } else {
    %>

    <h5> Ваш статус - пользователь</h5>
    <%
        }
    %>

    <br>
    <!-- Button trigger modal -->
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#editmodal">
        Изменить данные
    </button>
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#editpasmodal">
        Изменить пароль
    </button>

    <div class="modal fade" id="editmodal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="edituser" method="post">
                    <div class="modal-header">
                        <h5 class="modal-title fs-5">Редактирование пользователя ID: <%=user.getId()%>
                        </h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body">

                        <input type="hidden" name="id" value=<%=user.getId()%>>
                        <label class="form-label"> Имя </label>
                        <input type="text" class="form-control" name="name" value="<%=user.getName()%>">

                        <div class="mb-3">
                            <label for="exampleInputEmail1" class="form-label"> Email </label>
                            <input type="email" class="form-control" id="exampleInputEmail1" name="email"
                                   aria - describedby="emailHelp" value="<%=user.getEmail()%>">
                        </div>
                        <div class="mb-3">
                            <label for="exampleInputPassword1" class="form-label"> Текущий пароль </label>
                            <input type="password" class="form-control" name="pass">
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-success">Внести изменения</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="modal fade" id="editpasmodal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="editpassuser" method="post">
                    <div class="modal-header">
                        <h5 class="modal-title fs-5">Изменение пароля пользователя ID: <%=user.getId()%>
                        </h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" name="id" value=<%=user.getId()%>>
                        <div class="mb-3">
                            <%--@declare id="exampleinputpassword1"--%>
                            <label for="exampleInputPassword1" class="form-label"> Текущий пароль </label>
                            <input type="password" class="form-control" name="pass">
                        </div>

                        <div class="mb-3">
                            <label for="exampleInputPassword1" class="form-label"> Новый пароль </label>
                            <input type="password" class="form-control" name="pass1">
                        </div>
                        <div class="mb-3">
                            <label for="exampleInputPassword1" class="form-label"> Повторите новый пароль </label>
                            <input type="password" class="form-control" name="pass2">
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-success">Изменить пароль</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
