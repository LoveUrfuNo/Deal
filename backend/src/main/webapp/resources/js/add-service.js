/**
 *
 */
var dropdown = $('.ui.dropdown')
    .dropdown()
;

var exitConfirm = function () {
    $(window).on('beforeunload', function () {
        return "Данные будут утеряны. Вы уверенные что хотите покинуть страницу?";
    });

    $(document).on("submit", "form", function (event) {
        $(window).off('beforeunload');
    });
};

$(function () {
    exitConfirm();
});

