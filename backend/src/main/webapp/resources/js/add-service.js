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

var uploadServicesPhotos = function (_csrf) {
    var uploadPhotoForm = document.forms[0]
        , serviceInformationForm = document.forms[1];                //TODO: add validation
    var formData = new FormData(uploadPhotoForm);
    var xhr = new XMLHttpRequest();

    xhr.open("POST", "/uploadFile/loadServicePhoto+" + document.getElementById('nameOfService').value + "?" + _csrf, true);
    xhr.send(formData);

    serviceInformationForm.submit();
};
