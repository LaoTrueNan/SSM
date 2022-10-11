<% Person p =  (Person) request.getAttribute("person"); %>
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
    <table style="border: 1px solid black">
        <tr>
            <th>res</th>
<%--            <th>国家代码</th>--%>
<%--            <th>地区</th>--%>
<%--            <th>人口</th>--%>
        </tr>
        <tr>
            <td><%=p.getName()+"--"+p.getAge()%></td>
<%--            <td><%=city.getCountryCode()%></td>--%>
<%--            <td><%=city.getDistrict()%></td>--%>
<%--            <td><%=city.getPopulation()%></td>--%>
        </tr>
    </table>

</div>
</body>
</html>
