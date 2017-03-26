<%--
  Created by IntelliJ IDEA.
  User: andrey
  Date: 26.03.17
  Time: 23:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация на deal</title>
    <link href="../../../resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../../../resources/css/check_in.css" type="text/css" rel="stylesheet">
    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
</head>
<body>
<div class="registration">
    <div class="form-group">
        <a href="../../../WEB-INF/views/input/main.jsp"> <img src="../images/deal.png"></a><br>
        <label for="email">E-mail</label><br>
        <input class="form-control" placeholder="example@email.com" id="email" name="email" type="text"><br>
        <label for="login">Логин: </label>
        <input class="form-control" placeholder="Введите логин" id="login" name="login" type="text"><br>
        <label for="password">Пароль: </label>
        <input class="form-control" placeholder="Придумайте пароль" name="password" type="password"><br>
        <label for="password-repeat">Повторите пароль: </label> <!-- учу java-script-->
        <input class="form-control" placeholder="Введите пароль снова" id="confirm_password" type="password"><br>
        <form>
            <input class="btn btn-default" id="submit" type="submit" formaction="../main.html" value="Зарегистрироваться">
        </form>
    </div>
</div>
</body>
</html>
