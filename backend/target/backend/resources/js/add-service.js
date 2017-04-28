/**
 * Created by Никита on 25.04.2017.
 */
var dropdown = $('.ui.dropdown')
    .dropdown()
;

var checkInput = $('input[name = "cost"]').on('keyup keypress', function(e) {
    if (e.keyCode == 8) {}
    else
    {
        var letters='1234567890';
        return (letters.indexOf(String.fromCharCode(e.which))!=-1);
    }
});

