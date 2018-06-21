<%--
  Created by IntelliJ IDEA.
  User: jw
  Date: 6/20/18
  Time: 7:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="/login" method="post">
    name: <input name="name" type="text">
    password: <input name="password" type="password"/>
    <input type="submit" value="登录">
</form>

<input type="button" value="sign up" onclick="location.href='signUp.html'"/>
</body>
</html>
