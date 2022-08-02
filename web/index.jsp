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
<a href="retrieveCity?cid=2">到Kabul</a>
<a href="retrieveCity?cid=1">到Taiyuan</a>
<a href="retrieveCity?cid=<%=i%>">random</a>
</body>
</html>
