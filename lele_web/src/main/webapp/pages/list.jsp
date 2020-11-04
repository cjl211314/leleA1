<%--
  Created by IntelliJ IDEA.
  User: LeLe
  Date: 2020/10/28
  Time: 23:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:forEach items="${list}" var="pro">
        ${pro.productName}<br>
    </c:forEach>
</body>
</html>
