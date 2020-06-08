<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="custom" uri="/WEB-INF/tld/tags.tld" %>

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
<form action="<c:url value="/login"/>" method="POST">
    <custom:sdaLabledTextField label="login" name="login"></custom:sdaLabledTextField>
    <br/>
    <input type="password" name="pass" placeholder="password">
    <br/>
    <custom:sdaSubmit value="Login"/>
</form>
</body>
</html>
