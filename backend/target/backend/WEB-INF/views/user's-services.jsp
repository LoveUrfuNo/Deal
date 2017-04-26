<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kosty
  Date: 26.04.2017
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="${pageContext.request.contextPath}/add_service">Добавить</a>
    <c:forEach var="service" items="${usersServices}">
        <h4>${service.nameOfService}</h4><a href="${pageContext.request.contextPath}/delete">Удалить</a>
    </c:forEach>
</body>
</html>
