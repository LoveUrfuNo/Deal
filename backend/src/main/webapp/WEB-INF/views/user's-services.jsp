<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>
    Овсянка,
    <c:if test="${user.firstName != null}">
        ${user.firstName}
    </c:if>
    <c:if test="${user.firstName == null}">
        ${user.login}
    </c:if>
</h2>
<a href="${pageContext.request.contextPath}/add_service">Добавить</a>
<c:forEach var="service" items="${user.services}">
    <h4>${service.nameOfService}</h4>
    <a href="/delete/${service.id}" onclick="return confirmDelete();">Удалить</a>
</c:forEach>
<br><br>
<a href="${pageContext.request.contextPath}/redirect" class="col-sm-12 btn btn-primary">Назад</a>

<script src="${pageContext.request.contextPath}/resources/js/user's-services.js"></script>
</body>
</html>
