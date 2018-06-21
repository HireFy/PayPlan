<%--
  Created by IntelliJ IDEA.
  User: jw
  Date: 6/21/18
  Time: 11:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>showUser</title>
</head>
<body>
<c:forEach var="user" items="${users}">
    <ul>
        <li>${user.userName}</li>
        <li>${user.mail}</li>
        <li>${user.uuid}</li>
    </ul>
</c:forEach>
</body>
</html>
