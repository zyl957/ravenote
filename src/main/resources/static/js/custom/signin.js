$(function (){
    $("#sub").click(function (){
        $.ajax({
            type: "POST",
            dataType: "json",
            contentType:"application/json; charset=UTF-8",
            url: "/signin" ,
            data: JSON.stringify({
                "username" : $("#inputUsername").val(),
                "password" : $("#inputPassword").val()
            }),
            success: function (response) {
                if (response.code === 1000) {
                    window.location.replace("/home");
                }
                else {
                    console.log(response);
                    let $alert  = $("#alert");
                    $alert.empty();
                    $alert.append(response.message) ;
                    $alert.show();
                }
            },
            error : function() {
                alert("Error！");
            }
        });
    })
})

