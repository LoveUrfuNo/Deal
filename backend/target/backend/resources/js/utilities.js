/**
 * Created by Никита on 02.05.2017.
 */

var autoUpload = function () {
    var input = document.querySelector("input[type='file']");
    input.onchange = function () {
        this.form.submit();
    }
};

autoUpload();