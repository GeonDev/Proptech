

function rememberName() {
    var userId = $("#userId").val();

    if($("#customCheck").is(":checked") == true){
        console.log(userId);
        localStorage.setItem("userName",userId);


    }else if($("#customCheck").is(":checked") == false){
        localStorage.removeItem("userName");
    }
}

function loginFunc() {
    var userId = $("#userId").val();

    if($("#customCheck").is(":checked") == true){
        localStorage.setItem("userName",userId);
    }

    $('#loginProc').submit();

}


$(function(){

    if( localStorage.userName){
        $("#userId").val(localStorage.userName);
        $("#customCheck").prop("checked", true);
    }

});