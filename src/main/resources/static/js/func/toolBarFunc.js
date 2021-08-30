


function getToolBarModal(type, id ){

    console.log(type );
    console.log(userName );
    $('#modalBox').modal('show');

    if(type == 'profile' ){
        var urlPath = "/table/user-detail";

    	    $.ajax({
    	     type: 'GET'
    	     , url: urlPath
    	     , dataType: 'text'
    	     , success: function(data) {
    	         console.log(data)
    	    	 $( "#result .modal-content" ).html(data);
    	     }
    	    });

    }else if(type == 'account' ){
        var urlPath = "/table/user-account?id=" + id;

            $.ajax({
             type: 'GET'
             , url: urlPath
             , dataType: 'text'
             , success: function(data) {
                 console.log(data)
                 $( "#result .modal-content" ).html(data);
             }
            });
    }

}

$(function(){


        //모달 닫기 실행
    	$('#modalClose').on('click', function(){
            $('#modalClose').modal('hide');

        });

});