


function getToolBarModal(type, userName ){

    console.log(type );
    console.log(userName );
    $('#modalBox').modal('show');

    if(type == 'profile' ){
        var urlPath = "/table/user-detail?id=" + userName;

    	    $.ajax({
    	     type: 'GET'
    	     , url: urlPath
    	     , dataType: 'text'
    	     , success: function(data) {
    	         console.log(data)
    	    	 $( "#result .modal-content" ).html(data);
    	     }
    	    });
    }else if(type == 'log' ){

    }

}

$(function(){


        //모달 닫기 실행
    	$('#modalClose').on('click', function(){
            $('#modalClose').modal('hide');

        });

});