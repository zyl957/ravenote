$(function (){

    $(".targetTitle").click(function (event){
        let btnId = event.target.id;
        let linkId = parseInt(btnId)+1;
        let idId = parseInt(btnId)+2;
        let UnreadBool = parseInt(btnId)+3;
        let targetId = $("#"+linkId).val();
        if ($("#"+UnreadBool).val()==='1'){
            $.ajax({
                type: "POST",
                dataType: "json",
                url: "/notification/read" ,
                data:{id : parseInt($("#"+idId).val())},
                success: function (response) {
                    if (response.code===1000){
                        window.location.replace("/note?noteId="+targetId);
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

    $("#readBtn").click(function (){
        $.ajax({    //get username
            type: "POST",
            dataType: "json",
            contentType:"application/json; charset=UTF-8",
            url: "/getSessionUserAccount" ,
            success: function (userAccount) {
                $.ajax({    //second ajax request for update the collection database
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