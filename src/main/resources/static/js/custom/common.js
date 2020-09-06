$(function (){
    $.ajax({    //get username
        type: "POST",
        dataType: "json",
        contentType:"application/json; charset=UTF-8",
        url: "/getSessionUserAccount" ,
        success: function (userAccount) {
            $.ajax({    //second ajax request for update the collection database
                type: "POST",
                data:{receiver : userAccount.username},
                url: "/notification/unreadNumber" ,
                success: function (count) {
                    $("#notiBadge").html(count);
                },
                error : function() {
                    alert("Error! all read!");
                }
            });
        },
        error : function() {

        }
    });


})