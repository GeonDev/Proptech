

function getTablePage(pageNum) {
    var targetPage = pageNum-1;

    var searchType = $("#inputGroupSelect1 option:selected").val();

    var pageSize = $("#inputGroupSelect2 option:selected").val();

    var searchValue = $("#inputGroupText1").val();

    var contentName = $("#contentTitle").html();

    var startDate =  $("#datetimepicker1 input").val();

    var endDate = $("#datetimepicker2 input").val();

    var url = "/table";

     if(contentName == "User List" ){
        url =  url + "/user-list?";
     } else if(contentName == "Associate List"){
        url =  url + "/associate-list?";
     }

     var selectedColumn = $("#inputGroupSelect3 option:selected");

     var selected = "";
     $(selectedColumn).each(function(index, selectedColumn){
         selected += "-" + $(this).val();
     });

    selected = selected.substr(1);

    url = url + "page=" + targetPage+ "&size=" + pageSize+ "&type=" + searchType + "&value="+searchValue + "&start="+startDate + "&end="+endDate+ "&selected="+selected

    window.location.href = url;
}

function getTableExcel(){


    var searchType = $("#inputGroupSelect1 option:selected").val();

    var searchValue = $("#inputGroupText1").val();

    var contentName = $("#contentTitle").html();

    var startDate =  $("#datetimepicker1 input").val();

    var endDate = $("#datetimepicker2 input").val();

    var url = "/table";

     if(contentName == "User List" ){
        url =  url + "/user-excel?";
     } else if(contentName == "Associate List"){
        url =  url + "/associate-excel?";
     }

    url = url + "type=" + searchType + "&value="+searchValue + "&start="+startDate + "&end="+endDate;

    window.location.href = url;

}


function getParameterByName(name) {
        name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
        var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
                results = regex.exec(location.search);
        return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}


function setupTableColumn(selectedColumn) {

      const table = document.getElementById('dataTable');
      for(var i = 0; i < table.rows.length; i++)  {
        table.rows[i].deleteCell(-1);
      }
}


function getDetailModalView(type, id ){

    console.log(type );
    console.log(id );
    $('#modalBox').modal('show');

    if(type == 'user' ){


    }else if(type == 'associate' ){

    }
}

$(function(){

    	var type = getParameterByName('type');
    	var value = getParameterByName('value');
    	var pageSize = getParameterByName('size');

        //이전에 선택한 칼럽을 불러오기
        var selectedColumn = getParameterByName("selected");
        if(selectedColumn != ""){
            //selectpicker 값은 배열로 전달해야 한다.
            var colArr = selectedColumn.split("-");
            $("#inputGroupSelect3").selectpicker('val', colArr);
        }

    	if( pageSize == ""){
    	    pageSize = 10;
    	}


    	$("#inputGroupSelect1").val(type).trigger('change');
    	$("#inputGroupSelect2").val(pageSize).trigger('change');
    	$("#inputGroupText1").val(value);




    	$('#datetimepicker1').datetimepicker({ format: 'YYYY-MM-DD'});
    	$('#datetimepicker2').datetimepicker({ format: 'YYYY-MM-DD', useCurrent: false });

    	$("#datetimepicker1").on("change.datetimepicker", function (e) { $('#datetimepicker2').datetimepicker('minDate', e.date); });
    	$("#datetimepicker2").on("change.datetimepicker", function (e) { $('#datetimepicker1').datetimepicker('maxDate', e.date); });



        //모달 열기
    	$('#modalClose').on('click', function(){
            $('#modalClose').modal('hide');

        });

        //모달 닫기 실행
    	$('#modalClose').on('click', function(){
            $('#modalClose').modal('hide');

        });


});