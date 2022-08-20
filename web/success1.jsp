<% Person person =(Person) request.getAttribute("person"); %>
<%@ page import="com.byd.gzq.bean.Person" %><%--
  Created by IntelliJ IDEA.
  User: guo.zhiqiang3
  Date: 2022/6/24
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head.jsp"%>
<div id="root">
    <table>
        <tr>
            <th>名称</th>
        </tr>
        <tr>
            <td><%=person.getName()%></td>
        </tr>
    </table>
</div>
</body>
</html>
