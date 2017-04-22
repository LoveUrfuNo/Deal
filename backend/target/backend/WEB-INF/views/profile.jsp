<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Deal | Profile</title>
    <link href = "${pageContext.request.contextPath}/resources/css/bootstrap.css" rel ="stylesheet" type = "text/css">
    <link href = "${pageContext.request.contextPath}/resources/css/profile.css" type = "text/css" rel="stylesheet">
    <meta http-equiv = "content-type" content = "text/html" charset = "UTF-8">
</head>

<body>
<div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h2>
            Дарова ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Выйти</a>
        </h2>
    </c:if>
</div>
</body>
</html>