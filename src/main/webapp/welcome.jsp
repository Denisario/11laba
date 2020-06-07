<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DENIS
  Date: 02.06.2020
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="style/welcome.css">
    <link rel="stylesheet" href="style/header.css">
    <link rel="stylesheet" href="style/footer.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<a href="/login.jsp">Login</a>
<p>Hello, ${user}</p>
<a href="/register.jsp">Register</a>
<a href="/addProduct.jsp">Add product</a>
<table border="1">
    <tr>
        <th>Id</th>
        <th>Product</th>
        <th>Category</th>
        <th>Amount</th>
        <th>Price</th>
        <th>Edit</th>
    </tr>
    <c:forEach items="${products}" var="product">
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.productCategory.name}</td>
            <td>${product.amount}</td>
            <td>${product.price}</td>
            <td><a href="/editProduct?id=${product.id}">Edit</a></td>
        </tr>
    </c:forEach>
</table>
<jsp:include page="footer.jsp"/>
</body>
</html>
