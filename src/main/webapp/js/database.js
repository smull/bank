$(document).ready(function(){
   $("#send").on('click',function(event){
       event.preventDefault();



       $.ajax({
           url:"table.jsp",
           dataType: 'html',
           type: 'get',
           data:{
               tableName: $("#tableName").val()
           },
           success: function(data){
               $('#tableContent').html(data);
               $( "#tableContent" ).show( 'drop', {}, 500 );

           }
       });


   });
    $("#tableContent").on('click',"#insertButton",function(event){
        event.preventDefault();

        $.ajax({
            url:"insert",
            dataType: 'text',
            type:'post',
            data:$("#insertForm").serialize(),
            success: function(data){
                $('#tableContent').hide('drop', {}, 500 )
                $.ajax({
                    url:"table.jsp",
                    dataType: 'html',
                    type: 'get',
                    data:{
                        tableName: $("#tableName").val()
                    },
                    success: function(data){
                        $('#tableContent').html(data);
                        $( "#tableContent" ).show( 'drop', {}, 500 );

                    }
                });

            },
            error: function( jqXHR, textStatus, errorThrown){
                alert("Row insertion failed!")
            }

        });
    });
});