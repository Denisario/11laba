<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="custom" uri="/WEB-INF/tld/tags.tld" %>
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
<a href="<c:url value="/login" />">Login</a>
<a href="<c:url value="/register" />">Register</a>
<a href="<c:url value="/addProduct" />">Add product</a>
<custom:sdaTable products="${products}"></custom:sdaTable>
<jsp:include page="footer.jsp"/>
</body>
</html>
