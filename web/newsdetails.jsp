<%@ page import="models.News" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.NewsCategory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>News details</title>
    <%@include file="sdn.jsp" %>
</head>
<body>
<%@include file="navAdmin.jsp" %>
<br>
<div class="container">

    <% News n = (News) request.getAttribute("news");%>

    <form action="/updatenews" method="post">
        <input type="hidden" name="id" value="<%=n.getId()%>">
        <label class="col-form-label">Заголовок</label>
        <input type="text" class="form-control" name="title" value="<%=n.getTitle()%>">

        <div class="mb-3">
            <label class="col-form-label">Текст поста</label>
            <textarea class="form-control" id="exampleFormControlTextarea1" rows="10"
                      name="text"><%=n.getText()%></textarea>
        </div>

        <div class="mb-3">
            <label class="col-form-label">Дата публикации:</label>
            <input type="datetime-local" class="form-control" name="date" value="<%=n.getPostDate()%>">
        </div>

        <div class="mb-3">
            <label class="col-form-label">Категория</label>
            <select class="form-select" aria-label="Default select example" name="category">
                <% ArrayList<NewsCategory> categories = (ArrayList<NewsCategory>) request.getAttribute("category");
                    for (NewsCategory cat : categories) {
                %>
                <option value="<%=cat.getId()%>"><%=cat.getName()%>
                </option>
                <%
                    }
                %>
            </select>
        </div>
        <button type="submit" class="btn btn-success">Сохранить изменения</button>
    </form>

</div>

</body>
</html>
