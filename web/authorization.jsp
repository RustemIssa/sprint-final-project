<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorization</title>
    <%@include file="sdn.jsp" %>
</head>
<body>
<br>
<div class="container col-4 ">
    <h4 style="text-align: center"> Авторизация на сайте </h4>
    <form action="/authorization" method="post">
        <div class="mb-3">
            <%
                String error = request.getParameter("error");
                if (error != null) {
            %>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                <strong>Не правильный e-mail или пароль!</strong>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <%
                }
            %>

            <label for="exampleInputEmail1" class="form-label"> Email </label>
            <input type="email" class="form-control" id="exampleInputEmail1" name="email"
                   aria - describedby="emailHelp">
        </div>
        <div class="mb-3">
            <label for="exampleInputPassword1" class="form-label"> Пароль </label>
            <input type="password" class="form-control" id="exampleInputPassword1" name="pass">
            <a href="/reg">Регистрация на сайте</a>
        </div>
        <button type="submit" class="btn btn-primary"> Авторизоваться</button>
    </form>
</div>
</body>
</html>
