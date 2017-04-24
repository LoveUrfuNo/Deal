;(function () {
    'use strict';
    // iPad and iPod detection
    var isiPad = function () {
        return (navigator.platform.indexOf("iPad") != -1);
    };

    var isiPhone = function () {
        return (
            (navigator.platform.indexOf("iPhone") != -1) ||
            (navigator.platform.indexOf("iPod") != -1)
        );
    };

    var fullHeight = function () {

        $('.js-fullheight').css('height', $(window).height());
        $(window).resize(function () {
            $('.js-fullheight').css('height', $(window).height());
        });

    };
    // Owl Carousel
    var owlCrouselFeatureSlide = function () {
        var owl = $('.owl-carousel1');
        owl.owlCarousel({
            items: 1,
            loop: true,
            margin: 0,
            responsiveClass: true,
            nav: true,
            dots: true,
            smartSpeed: 500,
            navText: [
                "<i class='icon-chevron-left owl-direction'></i>",
                "<i class='icon-chevron-right owl-direction'></i>"
            ]
        });

        $('.owl-carousel2').owlCarousel({
            loop: true,
            margin: 10,
            nav: true,
            dots: true,
            responsive: {
                0: {
                    items: 1
                },
                600: {
                    items: 3
                },
                1000: {
                    items: 3
                }
            },
            navText: [
                "<i class='icon-chevron-left owl-direction'></i>",
                "<i class='icon-chevron-right owl-direction'></i>"
            ]
        })
    };
    // Animations
    var contentWayPoint = function () {
        var i = 0;

        $('.animate-box').waypoint(function (direction) {

            if (direction === 'down' && !$(this.element).hasClass('animated')) {

                i++;

                $(this.element).addClass('item-animate');
                setTimeout(function () {

                    $('body .animate-box.item-animate').each(function (k) {
                        var el = $(this);
                        setTimeout(function () {
                            var effect = el.data('animate-effect');
                            if (effect === 'fadeIn') {
                                el.addClass('fadeIn animated');
                            } else {
                                el.addClass('fadeInUp animated');
                            }

                            el.removeClass('item-animate');
                        }, k * 200, 'easeInOutExpo');
                    });

                }, 100);

            }

        }, {offset: '85%'});
    };

    var parallax = function () {
        $(window).stellar({
            horizontalScrolling: false,
            hideDistantElements: false,
            responsive: true

        });
    };

    var formTab = function () {

        $('.tab-menu a').on('click', function (event) {
            var $this = $(this),
                data = $this.data('tab');

            $('.tab-menu li').removeClass('active');
            $this.closest('li').addClass('active')

            $('.tab .tab-content-inner').removeClass('active');
            $this.closest('.tab').find('.tab-content-inner[data-content="' + data + '"]').addClass('active');

            event.preventDefault();

        });

    };

    var burgerMenu = function () {

        $('body').on('click', '.js-gtco-nav-toggle', function (event) {
            var $this = $(this);


            if ($('body').hasClass('overflow offcanvas')) {
                $('body').removeClass('overflow offcanvas');
            } else {
                $('body').addClass('overflow offcanvas');
            }
            $this.toggleClass('active');
            event.preventDefault();

        });
    };

    var dropdown = function () {

        $('.has-dropdown').mouseenter(function () {

            var $this = $(this);
            $this
                .find('.dropdown')
                .css('display', 'block')
                .addClass('animated-fast fadeInUpMenu');

        }).mouseleave(function () {
            var $this = $(this);

            $this
                .find('.dropdown')
                .css('display', 'none')
                .removeClass('animated-fast fadeInUpMenu');
        });

    };

    var formTab = function () {

        $('.tab-menu a').on('click', function (event) {
            var $this = $(this),
                data = $this.data('tab');
            $('.tab-menu li').removeClass('active');
            $this.closest('li').addClass('active');
            $('.tab .tab-content-inner').removeClass('active');
            $this.closest('.tab').find('.tab-content-inner[data-content="' + data + '"]').addClass('active');
            event.preventDefault();
        });
    };

    var backgroundSlider = function () {
        var counter = 1;
        var image = $(".backgroundSlider");
        var images = ["http://www.2fons.ru/pic/201406/1920x1200/2fons.ru-21289.jpg", "http://www.nastol.com.ua/pic/201203/1920x1080/nastol.com.ua-17840.jpg", "http://img1.joyreactor.cc/pics/post/full/красивые-картинки-Нью-Йорк-америка-ночь-1076757.jpeg"];

        setInterval(function () {
            image.fadeOut(1000, function () {
                image.css("background-image", "url(" + images[counter++] + ")");
                image.fadeIn(1000);
            });
            if (counter === images.length) {
                counter = 0;
            }
        }, 10000);
    };

    var profileLoader = function () {
        var newForm = $('.form-wrap2').html();
        var locationAddress = location.pathname;
        var profileAddressNotActivated = "/profile";
        var profileAddressActivated = "/profile/registration";
        if (locationAddress === profileAddressNotActivated || locationAddress === profileAddressActivated) {
            $('.form-wrap').empty().append(newForm);
        }
    };

    var activateString = function () {
        var activateButton = $(".profile button");

        setInterval(function () {
            activateButton.removeClass("btn-info").addClass("btn-warning");
            setTimeout(function () {
                activateButton.removeClass("btn-warning").addClass("btn-info");
            },2000)
        },4000);
    };
    // Document on load.
    $(function () {
        fullHeight();
        formTab();
        owlCrouselFeatureSlide();
        contentWayPoint();
        backgroundSlider();
        parallax();
        profileLoader();
        activateString();
    });
}());