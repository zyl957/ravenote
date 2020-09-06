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
    let bg = $(".bg");//背景
    let text = $(".text");//文字
    let dBtn = $(".d-btn");//滑块
    let distance = box.width() - dBtn.width();//滑动成功的宽度（距离）

    dBtn.on("mousedown",function(event){

        dBtn.attr("style","transition:all 0s ease 0s;");
        bg.attr("style","transition:all 0s ease 0s;");

        let downX = event.clientX;

        document.onmousemove = function (event){

            let moveX = event.clientX;

            let offsetX = moveX - downX;

            if( offsetX > distance){
                offsetX = distance;//如果滑过了终点，就将它停留在终点位置
            }else if( offsetX < 0){
                offsetX = 0;//如果滑到了起点的左侧，就将它重置为起点位置
            }

            //4.根据鼠标移动的距离来动态设置滑块的偏移量和背景颜色的宽度

            dBtn.attr("style","left:"+ offsetX +"px;");
            bg.attr("style","width:"+offsetX+"px;");

            //如果鼠标的水平移动距离 = 滑动成功的宽度
            if( offsetX === distance){

                //1.设置滑动成功后的样式
                text.text("Of course not!")
                    .attr("style","background-color:lightgreen;color:#ffffff;");
                dBtn.attr("style","display:none;");


                //2.设置滑动成功后的状态
                input4 = true;
                //成功后，清除掉鼠标按下事件和移动事件（因为移动时并不会涉及到鼠标松开事件）
                dBtn.on("mousedown",function (){});
                document.onmousemove = null;

            }

            //四、给文档注册鼠标松开事件
            document.onmouseup = function(e){

                //如果鼠标松开时，滑到了终点，则验证通过
                if(input4){
                    return;
                }else{
                    //反之，则将滑块复位（设置了1s的属性过渡效果）
                    dBtn.attr("style","left:0;");
                    bg.attr("style","width:0;");
                    dBtn.attr("style","transition:left 1s ease;");
                    bg.attr("style","transition:width 1s ease;");
                }
                //只要鼠标松开了，说明此时不需要拖动滑块了，那么就清除鼠标移动和松开事件。
                document.onmousemove = null;
                document.onmouseup = null;
            }

        }
    });

    $username.click(function (){
        $("#username-hint").show();
    })
    $username.blur(function (){
        $("#username-hint").hide();
        $.ajax({
            type: "POST",
            dataType: "json",
            data:{username : $username.val()},
            url: "/signup/username_check" ,
            success: function (response){
                if(response.code === 1000){
                    $("#username-warning").hide();
                    $("#username-pass").show();
                    input1 = true;
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
            url: "/signup/password_check" ,
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
                if ($confirm.val()!==$password.val()){
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