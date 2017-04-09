<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: andrey
  Date: 26.03.17
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Deal</title>

    <!-- Bootstrap -->
    <link href="../../../resources/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../../../resources/css/index.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<div class="topBlock">
    <img class="logo" src="../../../WEB-INF/views/images/logo_without_outline.png">
    <p class="search_text"> Поиск: </p>
    <label>
        <input class="search_line" type="text" size="60">
    </label>
    <form>
        <input class="signin" id="signin" type="submit" formaction="/signin" value="Вход">
    </form>
    <form>
        <input class="signup" id="checkin" type="submit" formaction="/checkin" value="Регистрация">
    </form>
</div>
<div id="myCarousel" class="carousel slide" data-interval="7000">
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>

    <!-- Вставить изображения ОДИНАКОВОГО РАЗМЕРА и описание -->
    <div class="carousel-inner">
        <div class="item active">
            <img src="../../../WEB-INF/views/images/background.png" alt="...">
            <div class="carousel-caption">
                <h3>Слайд 1</h3>
                <p>Какой-нибудь текст</p>
            </div>
        </div>
        <div class="item">
            <img src="../../../WEB-INF/views/images/background.png" alt="...">
            <div class="carousel-caption">
                <h3>Слайд 2</h3>
                <p>Какой-нибудь текст</p>
            </div>
        </div>
        <div class="item">
            <img src="../../../WEB-INF/views/images/background.png" alt="...">
            <div class="carousel-caption">
                <h3>Слайд 3</h3>
                <p>Какой-нибудь текст</p>
            </div>
        </div>
    </div>

    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left"></span></a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right"></span></a>
</div>


<!-- Что за хуйня?
    <div class = "grey_background">
       <a class = "left_arrow"> <img src = "images/white_arrow_left.png"></a>
        <a class = "right_arrow"> <img src = "images/white_arrow_right.png"> </a>
    </div>
</div>-->

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../../../resources/js/bootstrap.min.js"></script>
</body>
</html>
