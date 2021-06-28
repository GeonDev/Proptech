

function getTablePage(pageNum) {
    var targetPage = pageNum-1;

    var searchType = $("#inputGroupSelect1 option:selected").val();

    var searchValue = $("#inputGroupText1").val();

    var contentName = $("#contentTitle").html();

    var url = "/table";

     if(contentName == "User List" ){
        url =  url + "/user-list?";
     } else if(contentName == "Associate List"){
        url =  url + "/associate-list?";
     }

     url = url + "page=" + targetPage+ "&type=" + searchType + "&value="+searchValue;

    window.location.href = url;
}


function getParameterByName(name) {
        name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
        var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
                results = regex.exec(location.search);
        return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}


$(function(){

    	var type = getParameterByName('type');
    	var value = getParameterByName('value');
    	$("#inputGroupSelect1").val(type).trigger('change');
    	$("#inputGroupText1").val(value );

});