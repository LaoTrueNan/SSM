<% Object url = request.getAttribute("url");
    String[] split = url.toString().split("/");%><%--
  Created by IntelliJ IDEA.
  User: guo.zhiqiang3
  Date: 2022/6/26
  Time: 13:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head.jsp"%>
<h1>fatal error :(</h1>
<h2>please check your settings in web.xml</h2>
<h2>you should add static resources like <%=split[split.length-1]%> to default servlet url patterns</h2>
</body>
</html>
