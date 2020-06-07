<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DENIS
  Date: 07.06.2020
  Time: 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/editProduct" method="POST">
    <input type="text" name="name" placeholder="name">
    <br/>
    <select name="category" id="">
        <c:forEach items="${categories}" var="category">
            <option>${category.name}</option>
        </c:forEach>
    </select>
    <br/>
    <input type="text" name="amount" placeholder="amount">
    <br/>
    <input type="text" name="price" placeholder="price">
    <br/>
    <input type="submit">
</form>
</body>
</html>
