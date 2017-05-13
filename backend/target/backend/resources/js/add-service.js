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

    $(document).on("submit", "form", function () {
        $(window).off('beforeunload');
    });
};

$(function () {
    exitConfirm();
});

var uploadServicesPhotos = function (str) {
    var uploadPhotoForm = document.forms[0]
        , serviceInformationForm = document.forms[1];                //TODO: add validation
    var formData = new FormData(uploadPhotoForm);
    var xhr = new XMLHttpRequest();

    serviceInformationForm.submit();
    // serviceInformationForm.body.getAttribute()
    xhr.open("POST", "/uploadFile/loadServicePhoto?" + str, true);
    xhr.send(formData);
};
