<%--
  Created by IntelliJ IDEA.
  User: jw
  Date: 6/22/18
  Time: 9:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <style type="text/css">
        .uuidBox{
            background-color: lightgray;
            font-size: medium;
            color: black;
        }
    </style>
</head>
<body>
<h1>用户:${sessionScope.userName}</h1>

<button>获取uuid</button>

<h3>uuid:</h3>
<p class="uuidBox"></p>

<input type="button" value="login" onclick="location.href='login.html'"/>
</body>
<script src="js/getUuid.js"></script>
</html>
