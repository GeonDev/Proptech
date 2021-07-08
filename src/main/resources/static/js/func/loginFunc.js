

function rememberName() {
    var userId = $("#userID").val();

    if($("#customCheck").is(":checked") == true){
        console.log(userId);
        localStorage.setItem("userName",userId);


    }else if($("#customCheck").is(":checked") == false){
        localStorage.removeItem("userName");
    }
}

function loginFunc() {
    var userId = $("#userID").val();

    if($("#customCheck").is(":checked") == true){
        localStorage.setItem("userName",userId);
    }

    $('#loginProc').submit();

}


$(function(){

    if( localStorage.userName){
        $("#userID").val(localStorage.userName);
        $("#customCheck").prop("checked", true);
    }

});