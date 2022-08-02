<%--
  Created by IntelliJ IDEA.
  User: guo.zhiqiang3
  Date: 2022/6/26
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>404</title>
</head>
<body>
  <h1>404 not found :(</h1>
  <h2>check your request target:<%=request.getAttribute("url").toString()%></h2>
</body>
</html>
