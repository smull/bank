$(document).ready(function(){
    $(".login").on('click',"#loginButton",function(event){
        event.preventDefault();
        $.ajax(
            {
                type:'POST',
                url:'auth.jsp',
                dataType: 'html',
                data: {
                    login: $('#login').val(),
                    password: $('#password').val()
                },
                success: function(data){
                    $('.login').html(data);
                }
            }
        );
    });
});