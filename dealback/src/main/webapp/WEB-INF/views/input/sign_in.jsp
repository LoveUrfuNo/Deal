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
        <a href = "../../../WEB-INF/views/input/main.jsp"> <img src = "../images/deal.png"></a><br>
        <label for = "login">Логин: </label><br> <!— заголовок для формы —>
        <input class="form-control" placeholder="Имя пользователя" id = "login" name = "login" type = "text"><br> <!— ввод данных —>
        <label for = "password">Пароль: </label><br>
        <input class = "form-control" placeholder = "Пароль" id = "password" name = "password" type = "password"><br>
        <a href = "${pageContext.request.contextPath}/forgot">Забыли пароль?</a><br>
        <form>
            <input class = "btn btn-default" id="submit" type = "submit" formaction = "" value = "Войти"> <!— отсылка данных из форм на сервак —>
        </form>
    </div>
    <p><b>Нет аккаунта?</b><br> Создай свой <a href = "../../../WEB-INF/views/input/check_in.jsp">deal</a> аккаунт!</p>
</div>
</body>
</html>