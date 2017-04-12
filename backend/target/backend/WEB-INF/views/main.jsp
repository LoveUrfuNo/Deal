<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]> <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]> <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Deal</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Facebook and Twitter integration -->
    <meta property="og:title" content=""/>
    <meta property="og:image" content=""/>
    <meta property="og:url" content=""/>
    <meta property="og:site_name" content=""/>
    <meta property="og:description" content=""/>
    <meta name="twitter:title" content=""/>
    <meta name="twitter:image" content=""/>
    <meta name="twitter:url" content=""/>
    <meta name="twitter:card" content=""/>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/small_logo.icon"
          type="image/x-icon">

    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/favicon.ico">

    <!-- <link href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,700' rel='stylesheet' type='text/css'> -->

    <!-- Animate.css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/animate.css">
    <!-- Icomoon Icon Fonts-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/icomoon.css">
    <!-- Bootstrap  -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
    <!-- Owl Carousel -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/owl.carousel.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/owl.theme.default.min.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">

    <!--Vegas Slider-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vegas/vegas.min.css">

    <!-- Modernizr JS -->
    <script src="${pageContext.request.contextPath}/resources/js/modernizr.min.js"></script>
    <!-- FOR IE9 below -->
    <!--[if lt IE 9]>
    <script src="/resources/js/respond.min.js"></script>
    <![endif]-->

</head>
<body>


<div id="page">
    <span class="backgroundSlider"></span>
    <div class="page-inner">
        <nav class="gtco-nav" role="navigation">
            <div class="gtco-container">
                <div class="row">
                    <div class="col-sm-4 col-xs-12">
                        <div id="gtco-logo"><a href="#">Deal <em>.</em></a></div>
                    </div>
                    <div class="col-xs-8 text-right menu-1">
                        <ul>
                            <li><a href="#">Профиль</a></li>
                            <li><a href="#">Найти услугу</a></li>
                            <li><a href="#">Предложить услугу</a></li>
                            <li><a href="#">Поддержка</a></li>
                            <li class="btn-cta"><a href="#"><span>Начать</span></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </nav>

        <header id="gtco-header" class="gtco-cover" role="banner"
                style="">
            <div class="overlay"></div>
            <div class="gtco-container">
                <div class="row">
                    <div class="col-md-12 col-md-offset-0 text-left">
                        <div class="row row-mt-15em">
                            <div class="col-md-7 mt-text animate-box" data-animate-effect="fadeInUp">
                                <span class="intro-text-small">Добро пожаловать в Deal</span>
                                <h1>Покупай и продавай вместе с нами</h1>
                            </div>

                            <div class="col-md-4 col-md-push-1 animate-box" data-animate-effect="fadeInRight">

                                <!-- До регистрации -->
                                <div class="form-wrap">
                                    <div class="tab">
                                        <ul class="tab-menu">
                                            <li class="active gtco-first"><a href="#" data-tab="signup">Регистрация</a>
                                            </li>
                                            <li class="gtco-second"><a href="#" data-tab="login">Вход</a></li>
                                        </ul>
                                        <div class="tab-content">
                                            <div class="tab-content-inner active" data-content="signup">

                                                <form:form method="POST" modelAttribute="userForm"
                                                           action="/registration"
                                                           class="form-signin">
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                            <spring:bind path="username">
                                                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                                                    <label for="username">E-mail</label>
                                                                    <form:input type="email" path="username"
                                                                                class="form-control"
                                                                                placeholder="ivanov@email.com"/>
                                                                    <form:errors path="username"/>
                                                                </div>
                                                            </spring:bind>
                                                        </div>
                                                    </div>
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                            <spring:bind path="password">
                                                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                                                    <label for="password">Пароль</label>
                                                                    <form:input type="password" path="password"
                                                                                class="form-control"
                                                                                placeholder="Пароль"/>
                                                                    <form:errors path="password"/>
                                                                </div>
                                                            </spring:bind>
                                                        </div>
                                                    </div>
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                            <spring:bind path="confirmPassword">
                                                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                                                    <label for="confirmPassword">Пароль</label>
                                                                    <form:input type="password" path="confirmPassword"
                                                                                class="form-control"
                                                                                placeholder="Подтвердите пароль"/>
                                                                    <form:errors path="confirmPassword"/>
                                                                </div>
                                                            </spring:bind>
                                                        </div>
                                                    </div>

                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                            <input type="submit" class="btn btn-primary"
                                                                   value="Зарегистрироваться">
                                                        </div>
                                                    </div>
                                                </form:form>

                                            </div>

                                            <div class="tab-content-inner" data-content="login">
                                                <form method="POST" action="${contextPath}/login" class="form-signin">

                                                    <div class="form-group ${error != null ? 'has-error' : ''}">
                                                        <div class="row form-group">
                                                            <div class="col-md-12">
                                                                <label for="username">Логин или e-mail</label>
                                                                <span>${message}</span>
                                                                <input name="username" type="text" class="form-control"
                                                                       placeholder="Имя пользователя"
                                                                       autofocus="true"/>
                                                            </div>
                                                        </div>
                                                        <div class="row form-group">
                                                            <div class="col-md-12">
                                                                <label for="password">Пароль</label>
                                                                <input name="password" type="password"
                                                                       class="form-control" placeholder="Пароль"/>
                                                                <span>${error}</span>
                                                            </div>
                                                        </div>
                                                        <input type="hidden" name="${_csrf.parameterName}"
                                                               value="${_csrf.token}"/>
                                                        <div class="row form-group">
                                                            <div class="col-md-12">
                                                                <input type="submit" class="btn btn-primary"
                                                                       value="Войти">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                                <!-- После Регистрации или входа-->
                                <div class="form-wrap2">
                                    <div class="tab">
                                        <div class="profile">
                                            <a class="profileicon" href="#">
                                                <img src="http://ic.pics.livejournal.com/v_kosmetikovna/64617808/63542/63542_900.jpg">
                                            </a>
                                            <p>${pageContext.request.userPrincipal.name}</p>
                                            <c:if test="${pageContext.request.userPrincipal.name != null}">
                                                <form id="logoutForm" method="POST" action="${contextPath}/logout">
                                                    <input type="hidden" name="${_csrf.parameterName}"
                                                           value="${_csrf.token}"/>
                                                </form>
                                                <p>
                                                <div class="row form-group">
                                                    <div class="col-md-12">
                                                        <input type="submit" class="btn btn-primary"
                                                               onclick="document.forms['logoutForm'].submit()"
                                                               value="Выйте">
                                                    </div>
                                                </div>
                                                </p>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
    </div>
</div>


<div id="fh5co-intro-section">
    <div class="container">
        <div class="row">
            <div class="col-md-12 text-center">
                <h2>Мы упрощаем сделки</h2>
                <p>Попробуй сам <a href="#">предложить услугу</a></p>
            </div>
        </div>
    </div>
</div><!-- end fh5co-intro-section -->

<div class="owl-carousel owl-carousel1 owl-carousel-fullwidth fh5co-light-arrow animate-box"
     data-animate-effect="fadeIn">
    <div class="item">
        <iframe width="1920" height="906" src="https://www.youtube.com/embed/yGDCd5LMEmw?rel=0&amp;controls=0"
                frameborder="0" allowfullscreen></iframe>
    </div>
    <div class="item">
        <img src="http://great-usa.ru/wp-content/uploads/2016/12/piano-1406526_1920.jpg" alt="image">
    </div>

    <div class="item">
        <img src="http://svadbavm.ru/upload/iblock/62f/couple.jpg" alt="image">
    </div>
    <div class="item">
        <img src="http://conceptartworld.com/wp-content/uploads/2013/11/Juan-Pablo-Roldan-morla_NeverEnding_Story.jpg"
             alt="image">
    </div>

</div>


<div id="fh5co-common-section">
    <div class="container">
        <div class="heading-section text-center">
            <h2>Кем мы являемся</h2>
        </div>
        <div class="row">
            <div class="col-md-10 col-md-offset-1">
                <div class="col-md-6 col-sm-6 services-num services-num-text-right">
                    <span class="number-holder"></span>
                    <div class="desc">
                        <h3>Школьники делают сайт</h3>
                        <p>Собралась команда и начала создавать что-то</p>
                    </div>
                </div>
                <div class="col-md-6 col-sm-6 services-num">
                    <span class="number-holder"></span>
                    <div class="desc">
                        <h3>С нами сотрудничают такие компании как</h3>
                        <p>кто-то, что-то</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div><!-- end fh5co-common-section -->
<div class="fh5co-parallax"
     style="background-image: url(http://www.publicdomainpictures.net/pictures/180000/velka/hand-with-thumb-up.jpg);"
     data-stellar-background-ratio="0.5">
    <div class="overlay"></div>
    <div class="container">
        <div class="row">
            <div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 text-center fh5co-table">
                <div class="fh5co-intro fh5co-table-cell">
                    <h1 class="text-center">Совершать сделки легко</h1>
                    <p>Сделано с любовью <a href="#">Deal</a></p>
                </div>
            </div>
        </div>
    </div>
</div><!-- end: fh5co-parallax -->
<div id="fh5co-services-section">
    <div class="container">
        <div class="heading-section text-center">
            <h2>Удобное мобильное приложение</h2>
        </div>
        <div class="row">
            <div class="col-md-4 col-sm-6">
                <div class="fh5co-services-right">
                    <div class="fh5co-table2 text-center">
                        <div class="fh5co-table-cell2">
                            <i class="icon-heart3"></i>
                        </div>
                    </div>
                    <div class="holder-section">
                        <h3>Массаж</h3>
                        <p>Каждый хочет массаж </p>
                    </div>
                </div>
                <div class="fh5co-services-right">
                    <div class="fh5co-table2 fh5co-table2-color-2 text-center">
                        <div class="fh5co-table-cell2">
                            <i class="icon-laptop"></i>
                        </div>
                    </div>
                    <div class="holder-section">
                        <h3>Программирование</h3>
                        <p>Найди разработчика себе по душе </p>
                    </div>
                </div>
                <div class="fh5co-services-right">
                    <div class="fh5co-table2 fh5co-table2-color-3 text-center">
                        <div class="fh5co-table-cell2">
                            <i class="icon-video"></i>
                        </div>
                    </div>
                    <div class="holder-section">
                        <h3>Редактирование видео</h3>
                        <p>Нужен свадебный фильм? Просто попроси! </p>
                    </div>
                </div>
            </div>

            <div class="col-md-4 text-center">
                <div class="fhco-hero2">
                    <img class="img-responsive" src="${pageContext.request.contextPath}/resources/images/iphone6.png"
                         alt="iphone6">
                </div>
            </div>

            <div class="col-md-4 col-sm-6">
                <div class="fh5co-services">
                    <div class="fh5co-table2 fh5co-table2-color-4 text-center">
                        <div class="fh5co-table-cell2">
                            <i class="icon-mobile"></i>
                        </div>
                    </div>
                    <div class="holder-section">
                        <h3>Починим электронику</h3>
                        <p>Сломанный телефон - не проблема </p>
                    </div>
                </div>
                <div class="fh5co-services">
                    <div class="fh5co-table2 fh5co-table2-color-5 text-center">
                        <div class="fh5co-table-cell2">
                            <i class="icon-gears"></i>
                        </div>
                    </div>
                    <div class="holder-section">
                        <h3>Сантехника</h3>
                        <p>Механика вызывали? </p>
                    </div>
                </div>
                <div class="fh5co-services">
                    <div class="fh5co-table2 fh5co-table2-color-6 text-center">
                        <div class="fh5co-table-cell2">
                            <i class="icon-piechart"></i>
                        </div>
                    </div>
                    <div class="holder-section">
                        <h3>Домашнее задание</h3>
                        <p>Не можешь сделать дз? А первокурсник сделает его за сотку </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div><!-- end: fh5co-services-section -->
<div id="fh5co-featured-work-section">
    <div class="container-fluid">
        <div class="heading-section text-center">
            <h2>Большое количество сфер услуг</h2>
        </div>
        <div class="owl-carousel owl-carousel2">
            <div class="item">
                <!-- <a href="#" class="image-popup"> -->
                <img src="https://render.fineartamerica.com/images/images-profile-flow/350/images/artworkimages/medium/1/swamp-thing-teshia-art.jpg"
                     alt="image">
                <a href="#" class="pop-up-overlay text-center">
                    <div class="desc">
                        <h3>Искусство</h3>
                        <span>Живопись</span>
                    </div>
                </a>
            </div>
            <div class="item">
                <img src="http://weblada.ru/uploads/posts/2011-01/1295599609_455.jpg" alt="image">
                <a href="#" class="pop-up-overlay pop-up-overlay-color-2 text-center">
                    <div class="desc">
                        <h3>Перевозки</h3>
                        <span>Переезд</span>
                    </div>
                </a>
            </div>
            <div class="item">
                <!-- <a href="#" class="image-popup"> -->
                <img src="http://www.na-obzor.ru/image_galery/82/2516.jpg" alt="image">
                <!-- </a> -->
                <a href="#" class="pop-up-overlay pop-up-overlay-color-3 text-center">
                    <div class="desc">
                        <h3>Мобильные приложения</h3>
                        <span>Андройд</span>
                    </div>
                </a>
            </div>
            <div class="item">
                <!-- <a href="#" class="image-popup"> -->
                <img src="${pageContext.request.contextPath}/resources/images/portfolio_pic4.jpg" alt="image">
                <!-- </a> -->
                <a href="#" class="pop-up-overlay pop-up-overlay-color-4 text-center">
                    <div class="desc">
                        <h3>Camera Lens</h3>
                        <span>Illustration</span>
                    </div>
                </a>
            </div>
            <div class="item">
                <!-- <a href="#" class="image-popup"> -->
                <img src="images/portfolio_pic5.jpg" alt="image">
                <!-- </a> -->
                <a href="#" class="pop-up-overlay text-center">
                    <div class="desc">
                        <h3>Card</h3>
                        <span>Card</span>
                    </div>
                </a>
            </div>
            <div class="item">
                <!-- <a href="#" class="image-popup"> -->
                <img src="images/portfolio_pic6.jpg" alt="image">
                <!-- </a> -->
                <a href="#" class="pop-up-overlay pop-up-overlay-color-2 text-center">
                    <div class="desc">
                        <h3>Shoes</h3>
                        <span>Brand</span>
                    </div>
                </a>
            </div>
            <div class="item">
                <!-- <a href="#" class="image-popup"> -->
                <img src="images/portfolio_pic7.jpg" alt="image">
                <!-- </a> -->
                <a href="#" class="pop-up-overlay text-center">
                    <div class="desc">
                        <h3>Magazine</h3>
                        <span>Web</span>
                    </div>
                </a>
            </div>
            <div class="item">
                <!-- <a href="#" class="image-popup"> -->
                <img src="images/portfolio_pic8.jpg" alt="image">
                <!-- </a> -->
                <a href="#" class="pop-up-overlay pop-up-overlay-color-3 text-center">
                    <div class="desc">
                        <h3>VCard</h3>
                        <span>Card</span>
                    </div>
                </a>
            </div>
            <div class="item">
                <!-- <a href="#" class="image-popup"> -->
                <img src="images/portfolio_pic9.jpg" alt="image">
                <!-- </a> -->
                <a href="#" class="pop-up-overlay pop-up-overlay-color-4 text-center">
                    <div class="desc">
                        <h3>Paper</h3>
                        <span>Illustration</span>
                    </div>
                </a>
            </div>
        </div>
    </div>
</div><!-- end fh5co-featured-work-section -->
<div id="fh5co-blog-section">
    <div class="container">
        <div class="heading-section text-center">
            <h2>Недавние обновления</h2>
        </div>
        <div class="row">
            <div class="col-md-4 blog-section">
                <span>22 <small>Марта 2017</small></span>
                <h3><a href="#">Начали разрабатывать что-то</a></h3>
                <p>Что-то сделали, работает хорошо, но не очень, скоро релиз</p>
                <a class="btn btn-primary" href="#">Подробнее</a>
            </div>
            <div class="col-md-4 blog-section">
                <span>13 <small>Май 2017</small></span>
                <h3><a href="#">Начали разрабатывать что-то</a></h3>
                <p>FЧто-то сделали, работает хорошо, но не очень, скоро релиз</p>
                <a class="btn btn-primary" href="#">Подробнее</a>
            </div>
            <div class="col-md-4 blog-section">
                <span>01 <small>Апрель 2017</small></span>
                <h3><a href="#">Начали разрабатывать что-то</a></h3>
                <p>Что-то сделали, работает хорошо, но не очень, скоро релиз</p>
                <a class="btn btn-primary" href="#">Подробнее</a>
            </div>
        </div>
    </div>
</div><!-- end: fh5co-blog-section -->
<div class="fh5co-parallax"
     style="background-image: url(https://cdn2.professor-falken.com/wp-content/uploads/2017/01/hombre-cuello-camisa-corbata-barba-mano-Fondos-de-Pantalla-HD-professor-falken.com_.jpg);"
     data-stellar-background-ratio="0.5">
    <div class="overlay"></div>
    <div class="container">
        <div class="row">
            <div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 text-center fh5co-table">
                <div class="fh5co-intro fh5co-table-cell">
                    <h1 class="text-center">Стань ближе к успеху</h1>
                    <p>С компанией <a href="#">Deal</a></p>
                </div>
            </div>
        </div>
    </div>
</div><!-- end: fh5co-parallax -->
<footer>
    <div id="footer" class="fh5co-border-line">
        <div class="container">
            <div class="row">
                <div class="col-md-8 col-md-offset-2 text-center">
                    <p>Использование сайта, в том числе подача объявлений, означает согласие с пользовательским
                        соглашением.
                        Оплачивая услуги на сайте, вы принимаете оферту <a href="#">Deal</a>. Все права защищены. <br>Сделано
                        с <i class="icon-heart3 love"></i> by <a href="#" target="_blank">Deal</a><br> Создатели <a
                                href="https://vk.com/id35713161" target="_blank">Никита</a> &amp; <a
                                href="https://vk.com/yurok_e" target="_blank">Юрий</a> &amp; <a
                                href="https://vk.com/id153656293" target="_blank">Максим</a> &amp; <a
                                href="https://vk.com/id352078925" target="_blank">Андрей</a></p>
                    <p class="fh5co-social-icons">
                        <a href="#"><i class="icon-twitter-with-circle"></i></a>
                        <a href="#"><i class="icon-facebook-with-circle"></i></a>
                        <a href="#"><i class="icon-instagram-with-circle"></i></a>
                    </p>
                </div>
            </div>
        </div>
    </div>
</footer>


<!-- jQuery -->
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<!-- jQuery Easing -->
<script src="${pageContext.request.contextPath}/resources/js/jquery.easing.1.3.js"></script>
<!-- Bootstrap -->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<!-- Waypoints -->
<script src="${pageContext.request.contextPath}/resources/js/jquery.waypoints.min.js"></script>
<!-- Owl carousel -->
<script src="${pageContext.request.contextPath}/resources/js/owl.carousel.min.js"></script>
<!-- Stellar -->
<script src="${pageContext.request.contextPath}/resources/js/jquery.stellar.min.js"></script>

<!-- Vegas -->
<script src="${pageContext.request.contextPath}/resources/vegas/vegas.min.js"></script>

<!-- Main JS (Do not remove) -->
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>

</body>
</html>