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
                if (response.code === 1000) {   //if success
                    window.location.replace("/home");
                }
                else {
                    console.log(response);
                    let $alert  = $("#alert");
                    $alert.empty();
                    $alert.append(response.message) ;
                    $alert.show();  //print error message in the alert box
                }
            },
            error : function() {
                alert("Error！");
            }
        });
    })
})

