<%--
  Created by IntelliJ IDEA.
  User: DENIS
  Date: 02.06.2020
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>${err}</p>
<form action="/login" method="POST">
    <input type="text" name="login" placeholder="login">
    <input type="password" name="pass" placeholder="password">
    <input type="submit">
</form>
</body>
</html>
