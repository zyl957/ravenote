$(function (){

    let $username = $("#username");
    let $password = $("#password");
    let $confirm = $("#passwordConfirmation");
    let $submitBtn1 = $("#submitUa");

    let input1 = false;
    let input2 = false;
    let input3 = false;
    let input4 = false;

    let box = $(".drag");
    let bg = $(".bg");//background
    let text = $(".text");//inner text
    let dBtn = $(".d-btn");//slide block
    let distance = box.width() - dBtn.width();//total distance

    dBtn.on("mousedown",function(event){

        dBtn.attr("style","transition:all 0s ease 0s;");
        bg.attr("style","transition:all 0s ease 0s;");

        let downX = event.clientX;

        document.onmousemove = function (event){

            let moveX = event.clientX;

            let offsetX = moveX - downX;

            if( offsetX > distance){
                offsetX = distance;//make the block stay at the destination when the mouse passes it
            }else if( offsetX < 0){
                offsetX = 0;//move the block to the start if the user tries to pull it out of the left end
            }

            //auto-setting the background color and the offset based on the mouse route

            dBtn.attr("style","left:"+ offsetX +"px;");
            bg.attr("style","width:"+offsetX+"px;");


            if( offsetX === distance){

                //setting the status of success
                text.text("Of course not!")
                    .attr("style","background-color:lightgreen;color:#ffffff;");
                dBtn.attr("style","display:none;");


                //setting the status after the success
                input4 = true;
                //clear the mouse event
                dBtn.on("mousedown",function (){});
                document.onmousemove = null;

            }

            //add listener for mouse up
            document.onmouseup = function(e){

                //if the block is at the destination when the mouse is up, success
                if(input4){
                    return;
                }else{
                    //else reset the block (with an 1s delay)
                    dBtn.attr("style","left:0;");
                    bg.attr("style","width:0;");
                    dBtn.attr("style","transition:left 1s ease;");
                    bg.attr("style","transition:width 1s ease;");
                }
                //anyway clear the listener events
                document.onmousemove = null;
                document.onmouseup = null;
            }

        }
    });

    $username.click(function (){
        $("#username-hint").show(); //hints will show when user click related input boxes
    })
    $username.blur(function (){ //triggers when the input box loses the focus
        $("#username-hint").hide();
        $.ajax({
            type: "POST",
            dataType: "json",
            data:{username : $username.val()},
            url: "/signup/username_check" , // check if there is already a same name in the database
            success: function (response){
                if(response.code === 1000){
                    $("#username-warning").hide();
                    $("#username-pass").show();
                    input1 = true;  //passes the limitation
                }
                else{
                    $("#username-pass").hide();
                    $("#username-warning")
                        .show()
                        .text(response.message);
                    input1 = false;
                }
            },
            error : function() {
                alert("ERROR! at the username");
            }
        });
    })

    $password.click(function (){
        $("#password-hint").show();
    })
    $password.blur(function (){
        $("#password-hint").hide();
        $.ajax({
            type: "POST",
            dataType: "json",
            data:{password : $password.val()},
            url: "/signup/password_check" , //check the length of the password
            success: function (response){
                if(response.code === 1000){
                    $("#password-warning").hide();
                    $("#password-pass").show();
                    input2 = true;
                }
                else{
                    $("#password-pass").hide();
                    $("#password-warning")
                        .show()
                        .text(response.message);
                    input2 = false;
                }
                if ($confirm.val()!==$password.val()){  //the confirm box needs to be check again in case user fills in it before the password box
                    $("#confirm-warning").show();
                    $("#confirm-pass").hide();
                    input3 = false;
                }
            },
            error : function() {
                alert("ERROR! at the username");
            }
        });
    })

    $confirm.on("input propertychange",function (){
        if ($confirm.val()!==$password.val() || $confirm.val()===""){
            $("#confirm-warning").show();
            $("#confirm-pass").hide();
            input3 = false;
        }
        else{
            $("#confirm-warning").hide();
            $("#confirm-pass").show();
            input3 = true;
        }
    })

    //when clicking the submit button, check all four variables, if all true, pass
    $submitBtn1.on("click", function (){
        let conf = confirm("Are you sure you want to submit?");
        if (!conf){
            return null;
        }
        let sWarning = $("#submit-warning");
        if (input1 && input2 && input3 && input4){
            $("#signUpForm").submit();
        }
        else {
            sWarning.show();
            if (!input1){
                sWarning.text("Invalid input username.");
            }
            else if (!input2){
                sWarning.text("Invalid input password.");
            }
            else if(!input3){
                sWarning.text("Password did not get double confirmed.");
            }
            else if(!input4){
                sWarning.text("Please complete the slide verification above.");
            }
            return null;
        }
    })

})