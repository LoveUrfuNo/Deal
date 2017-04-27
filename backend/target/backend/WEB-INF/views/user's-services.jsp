<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
