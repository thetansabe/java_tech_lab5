<%--
  Created by IntelliJ IDEA.
  User: AN515-43
  Date: 10/13/2022
  Time: 11:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <%
    session.invalidate();
    response.sendRedirect("/login");
  %>
</body>
</html>
