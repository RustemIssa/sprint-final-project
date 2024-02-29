<%@ page import="models.News" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <%@include file="sdn.jsp" %>
</head>
<body>
<%@include file="navAdmin.jsp"%>
<br>

<div class="container">
    <a href="/addnews" class="btn btn-success">Добавить новость</a>
    <br> <br>
    <table class="table table-hover">
        <thead>
        <tr>
            <th> Id</th>
            <th> Дата</th>
            <th> Категория</th>
            <th> Заголовок</th>
            <th> Детали</th>
        </tr>
        </thead>
        <tbody>

        <% ArrayList<News> frontnews = (ArrayList<News>) request.getAttribute("news");
            for (News n : frontnews) { %>

        <tr>
            <td>
                <%= n.getId()%>
            </td>
            <td>
                <%= n.getPostDate()%>
            </td>
            <td>
                <%= n.getCategoryId().getName()%>
            </td>
            <td>
                <%= n.getTitle()%>
            </td>
            <td>
                <a href="/newsdetails?id=<%=n.getId() %>" class="btn btn-info">Изменить</a>
                <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deletemodal">
                    Удалить пост
                </button>
                <!-- Modal -->
                <div class="modal fade" id="deletemodal" tabindex="-1" aria-labelledby="exampleModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <form action="/deletenews" method="post">
                                <div class="modal-header">
                                    <input type="hidden" name="id" value="<%=n.getId()%>">
                                    <h1 class="modal-title fs-5">Удаление поста id: <%=n.getId()%>
                                    </h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <H3> Вы уверены? </H3>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                                    <button type="submit" class="btn btn-danger">Удалить</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

            </td>
        </tr>
        <%
            }
        %>
        </tbody>

    </table>

</div>

</body>
</html>
