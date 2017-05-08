<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Результаты</title>
</head>
<body>
<c:forEach var="service" items="${resultsSet}">
    <h2>${service.user.firstName != null ? service.user.firstName : service.user.login}
        -> ${service.nameOfService} всего за ${service.serviceCost}${service.currency}</h2>
</c:forEach>
<a href="${pageContext.request.contextPath}/redirect" class="col-sm-12 btn btn-primary">Назад</a>
</body>
</html>
