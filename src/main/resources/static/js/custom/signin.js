$(function (){
    // logic for the "submit" button on the sign-in page
    $("#sub").click(function (){
        $.ajax({    // pass the input username and the password to the controller via JSON object
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
                    let $alert  = $("#alert");
                    $alert.empty();
                    $alert.append(response.message) ;
                    $alert.show();  //print error message in the alert box
                }
            },
            error : function() {
                alert("Error! UA1");
            }
        });
    })
})

