<%@ page import="java.util.Random" %><%--
  Created by IntelliJ IDEA.
  User: guo.zhiqiang3
  Date: 2022/6/23
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<% int i = new Random().nextInt(10); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head.jsp"%>
<form action="city/changePersonInfo" method="post">
    <input type="number" name="id"><br>
<%--    <input type="text" name="name"><br>--%>
    <input type="text" name="text"><br>
    <input type="number" name="age"><br>
    <input type="submit" value="提交">

</form>
<a href="city/getPerson?id=<%=i%>">random</a>
</body>
</html>
