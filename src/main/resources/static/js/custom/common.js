$(function (){
    // displays the number of the unread notifications on the navigation bar
    $.ajax({    //get the username of the useraccount stored in the session
        type: "POST",
        dataType: "json",
        contentType:"application/json; charset=UTF-8",
        url: "/getSessionUserAccount" ,
        success: function (userAccount) {
            $.ajax({    //second ajax request for update the collection database
                type: "POST",
                data:{receiver : userAccount.username},
                url: "/notification/unreadNumber" ,
                success: function (count) {     // the number of the unread notifications
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