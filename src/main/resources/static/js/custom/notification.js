$(function (){

    // the logic of the link of the title of the note on the notification page
    $(".targetTitle").click(function (event){
        let btnId = event.target.id;
        let linkId = parseInt(btnId)+1;
        let idId = parseInt(btnId)+2;
        let UnreadBool = parseInt(btnId)+3;
        let targetId = $("#"+linkId).val();
        if ($("#"+UnreadBool).val()==='1'){     // if this notification is unread
            $.ajax({    // mark it as read
                type: "POST",
                dataType: "json",
                url: "/notification/read" ,
                data:{id : parseInt($("#"+idId).val())},
                success: function (response) {
                    if (response.code===1000){
                        window.location.replace("/note?noteId="+targetId);  //if success, go to that corresponding note page
                    }else {
                        window.location.replace("/error");
                    }
                },
                error : function() {
                    alert("Error! Notification");
                }
            });
        }
        else{
            window.location.replace("/note?noteId="+targetId);
        }

    })

    // the logic of the button "Mark all as read"
    $("#readBtn").click(function (){
        $.ajax({    //get username
            type: "POST",
            dataType: "json",
            contentType:"application/json; charset=UTF-8",
            url: "/getSessionUserAccount" ,
            success: function (userAccount) {
                $.ajax({    //second ajax request for mark all as read
                    type: "POST",
                    dataType: "json",
                    data:{receiver : userAccount.username},
                    url: "/notification/allRead" ,
                    success: function (response) {
                        if (response.code===1000){
                            window.location.reload();
                        }
                    },
                    error : function() {
                        alert("Error! all read!");
                    }
                });
            },
            error : function() {
                alert("Error! UA!");
            }
        });
    })
})