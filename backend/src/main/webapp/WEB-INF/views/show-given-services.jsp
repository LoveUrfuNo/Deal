<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Services</title>
</head>
<body>
<c:choose>
    <c:when test="${category.equals('1category')}">
        <h1>1category</h1>
        <c:forEach var="service" items="${services}">
            <h6>${service.nameOfService} -> ${service.usernameOfSeller}</h6>
        </c:forEach>
    </c:when>
    <c:when test="${category.equals('2category')}">
        <h1>2category</h1>
        <c:forEach var="service" items="${services}">
            <h6>${service.nameOfService} -> ${service.usernameOfSeller}</h6>
        </c:forEach>
    </c:when>
</c:choose>
</body>
</html>
