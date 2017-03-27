<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Вход на deal</title>
    <link href="../../../resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href = "../../../resources/css/sign_in.css" type = "text/css" rel = "stylesheet"> <!-- нижний перекрывает верхний-->
    <meta http-equiv = "Content-Type" content = "text/html" charset="UTF-8">
</head>
<body>
<div class="log-in">
    <div class="form-group">
        <spring:form method="post"  modelAttribute="userData" action="/welcome">
            <a href = "../../../WEB-INF/views/input/main.jsp"> <img src = "../images/deal.png"></a><br>
            <label for = "login">Логин: <spring:input class="form-control" path="login"/> </label><br> <!— заголовок для формы —>
            <%--<input class="form-control" placeholder="Имя пользователя" id = "login" name = "login" type = "text"><br> <!— ввод данных —>--%>
            <label for = "password">Пароль: <spring:input class = "form-control" for = "password" path="password"/></label><br>
            <%--<input class = "form-control" placeholder = "Пароль" id = "password" name = "password" type = "password"><br>--%>
            <a href = "${pageContext.request.contextPath}/forgot">Забыли пароль?</a><br>
            <form>
                <input class = "btn btn-default" id="submit" type = "submit" formaction = "/welcome" value = "Войти"> <!— отсылка данных из форм на сервак —>
            </form>
        </spring:form>
    </div>
    <p><b>Нет аккаунта?</b><br> Создай свой <a href = "${pageContext.request.contextPath}/checkin">deal</a> аккаунт!</p>
</div>
</body>
</html>