

function save() {
        var username = $("#inputUserName").val();
        var name = $("#inputName").val();
        var email = $("#InputEmail").val();
        var password = $("#InputPassword").val();
        var rePassword = $("#RepeatPassword").val();

        if(password != rePassword ){
            alert("Not matched Password!");
            return;
        }

        if(username == "" || name == "" || email == "" || password == "" || rePassword == "" ){
            alert("Check Blank");
            return;
        }

        $("form").attr("action", "/register");
        $("form").submit();
}



$(function(){

        $('#btn-reg').on('click', function () {
            save();
        });


});