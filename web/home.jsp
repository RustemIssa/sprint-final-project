<%@ page import="models.News" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <%@include file="sdn.jsp" %>
</head>
<body>

<% ArrayList<News> frontnews = (ArrayList<News>) request.getAttribute("news");
    User user = (User) request.getSession().getAttribute("currentUser");
    if (user.getRoleId() == 1) {

%>
<%@include file="navAdmin.jsp" %>
<%
} else {
%>
<%@include file="nav.jsp" %>
<%
    }
    for (News n : frontnews) { %>

<div class="container" style="margin: 20px">
    <div class="card">
        <div class="card-body">
            <h6 style="color: darkgray"> Category: <%=n.getCategoryId().getName()%>
            </h6>
            <h4><a href="/openpost?id=<%=n.getId()%> "><%=n.getTitle()%></a>
            </h4>
            <p><%=n.getText()%>
            </p>
            <h6>Дата: <%=n.getPostDate()%>
            </h6>
        </div>
    </div>
</div>
<%
    }
%>
</body>
</html>
