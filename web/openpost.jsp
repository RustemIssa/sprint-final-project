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
<%@include file="nav.jsp" %>
<div class="container col-6">
    <%
        News n = (News) request.getAttribute("news");
        User user = (User) request.getSession().getAttribute("currentUser");

    %>
    <h6 class="text-secondary">Date: <%=n.getPostDate()%>, Category: <%=n.getCategoryId().getName()%>
    </h6>
    <h2><%=n.getTitle()%>
    </h2>
    <p><%=n.getText()%>
    </p>
    <br>
    <h5>Комментарии:</h5>
    <%
        ArrayList<Comment> comments = (ArrayList<Comment>) request.getAttribute("comments");
        for (Comment comment : comments) {
    %>

    <h6 class="text-secondary"><%=comment.getUserId().getName()%>, <%=comment.getPostDate().getDayOfMonth() + " "
            + comment.getPostDate().getMonth() + " " + comment.getPostDate().getHour() + ":"
            + comment.getPostDate().getMinute()%>
    </h6>
    <p><%=comment.getComment()%>
    </p>
    <br>
    <%
        }
    %>

    <br>
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#commentmodal">
        Добавить комментарий
    </button>

    <div class="modal fade" id="commentmodal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="/comment" method="post">
                    <div class="modal-header">
                        <h5 class="modal-title fs-5" id="exampleModalLabel">Добавление комментария
                        </h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" name="user_id" value=<%=user.getId()%>>
                        <input type="hidden" name="news_id" value=<%=n.getId()%>>
                        <h4>Ваш комментарий: </h4>
                        <textarea type="text" class="form-control" name="comment"> </textarea>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-success">Добавить</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
