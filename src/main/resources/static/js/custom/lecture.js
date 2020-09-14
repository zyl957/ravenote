$(document).ready(function(){
    let $hs = $(".hideShow");
    let $e = $("#editor");

    let $nb = $("#noteButton");
    let $sb = $("#submitButton");
    let $lb1 = $("#loadButton1");
    let $lb2 = $("#loadButton2");
    let $lb3 = $("#loadButton3");
    let $lbAll = $("#loadAllButton");
    let $mnb = $("#myNotesButton");
    let $mcb = $("#myCollectionButton");
    let $cb = $(".colButtons")
    let $hn = $(".hideNote");
    let $sn = $(".showNote");

    let $rt = $(".requiredTitle");
    let $he = $("#hideEditor");
    let $ic = $("#inputContent");
    let $mn = $(".mainNote");

    // variables definition for function "load"
    let initIndex = 4;  // the number of notes get displayed initially
    let index = initIndex;  //a variable for storing the number of wanted displayed notes
    let index2 = 0;
    let index3 = 0;
    let index4 = 0;
    let hasMore = false;    // check if there are more notes on this page than display index

    let mnb = true;     // whether "my note" button is activated
    let mcb = true;     //whether "my collection" button is activated

    let colList = [];

    let reportNoteId = 0;
    let noteNumber = 0;
    let notePageUrl = " ";

    //function to fold the notes area to make it display certain amount of notes. There are 4 levels of display amount.
    function load(){
        //initialise all of the buttons
        $lb1.hide();
        $lb2.hide();
        $lb3.hide();
        $lbAll.hide();

        // traverse all of the notes div
        $mn.each(function (i){
            if(i<=index){
                this.style.display = "block";   // show numbers of notes less than display index
            }else {
                hasMore = true;
                this.style.display = "none";    //hide
                $lb1.show();
                $lbAll.show();
            }
        });
    }

    // initialise the load()
    function loadInit(){
        $mn.show();
        $(".loadMore").show();
        index = initIndex;
        index2 = 0;
        index3 = 0;
        index4 = 0;
        hasMore = false;
        load();
    }

    loadInit();

    //function to get the list of ids of collected notes from server and update the status of every note that whether it has been collected by current user.
    function checkCollection(){
        //initialise all add and remove buttons
        $(".addCollection").show();
        $(".removeCollection").hide();

        $.ajax({
            type: "POST",
            dataType: "json",
            contentType:"application/json; charset=UTF-8",
            url: "/collection" ,
            success: function (ids) {
                ids.sort();     //slightly improves the performance by sorting this list
                colList = ids;
                $mn.each(function (){
                    let noteId = parseInt(this.id)+4;
                    let addColId = parseInt(this.id)+5;
                    let removeColId = parseInt(this.id)+6;  //ids of required values of each note div

                    // if there is an id equals the note id of current note div, hide add button and show remove button
                    for (let i=0; i < ids.length; i++){
                        if (parseInt($("#"+noteId).val())===colList[i]){
                            $("#"+addColId).hide();
                            $("#"+removeColId).show();
                            break;
                        }
                    }
                })
            },
            error : function() {
                alert("Error! COL");
            }
        });
    }

    checkCollection();

    // set styles for tables in notes
    $("table").attr("class","table table-striped table-bordered table-condensed");

    //make sure that user cannot submit note without a tittle
    $rt.on("input propertychange",function (){
        if ($rt.val()===""){
            $sb.attr("disabled","disabled");
            $sb.attr("title","A title is required here.");
        }else{
            $sb.removeAttr("disabled");
            $sb.removeAttr("title");
        }
    })

    // level 1 "load more" button initialisation
    $lb1.click(function (){
        index2 = 9;
        index = index2;
        hasMore = false;
        load();
        if (hasMore){
            $lb1.hide();
            $lb2.show();
        }
    });

    // level 2 "load more" button initialisation
    $lb2.click(function (){
        index3 = 14;
        index = index3;
        hasMore = false;
        load();
        if (hasMore){
            $lb1.hide();
            $lb3.show();
        }
    });

    // level 3 "load more" button initialisation
    $lb3.click(function (){
        index4 = 19;
        index = index4;
        hasMore = false;
        load();
        if (hasMore){
            $lb1.hide();
        }
    });

    // level 4 "load all" button initialisation
    $lbAll.click(function (){
        index = 1000000;
        load();
    });

    // the button to hide/show note contents
    $hs.click(function(event){
        let hsId = event.target.id;
        let ncId = parseInt(hsId)+1;
        $("#"+ncId).toggle();
    });

    //function of "my note" button
    $mnb.click(function (){
        if (mnb){   //if it is activated
            $mn.show();     //reset the display method of notes
            $(".loadMore").hide();
            $.ajax({        //get username of current user
                type: "POST",
                dataType: "json",
                contentType:"application/json; charset=UTF-8",
                url: "/getSessionUserAccount" ,
                success: function (userAccount) {
                    $mn.each(function (){
                        let author = $("#"+(parseInt(this.id)+3)).val()
                        if (author === userAccount.username){   //compare the username of current author and obtained username, if same, display
                            this.style.display = "block";
                        }
                        else {
                            this.style.display = "none";
                        }
                    })
                },
                error : function() {
                    alert("Error! UA");
                }
            });
            $mnb.removeAttr("class");
            $mnb.attr("class","btn btn-primary");   // change the background color of this button
            mnb = false;    // change the status
        }
        else {
            loadInit();
            $mnb.removeAttr("class");
            $mnb.attr("class","btn btn-default");
            mnb = true;
        }
    })

    //function for "add to/remove from collection"
    $cb.click(function (event){
        let thisId = parseInt(event.target.id);
        let noteId = 0;
        let isCollect = true;   //a value to store the status that whether this button is for add or remove
        if (thisId % 2 === 0){  //this is a remove button
            isCollect = false;
            noteId = parseInt($("#"+(thisId - 2)).val());
        }
        else {      //this is an add button
            noteId = parseInt($("#"+(thisId - 1)).val());
        }
        $.ajax({    //get username
            type: "POST",
            dataType: "json",
            contentType:"application/json; charset=UTF-8",
            url: "/getSessionUserAccount" ,
            success: function (userAccount) {
                $.ajax({    //second ajax request for update the collection database
                    type: "POST",
                    dataType: "json",
                    contentType:"application/json; charset=UTF-8",
                    url: "/collection/operation" ,
                    data: JSON.stringify({
                        "username" : userAccount.username ,
                        "noteId" : noteId,
                        "isCollect" : isCollect
                    }),
                    success: function () {
                        checkCollection();
                    },
                    error : function() {
                        alert("Error! COLLECT!");
                    }
                });
            },
            error : function() {
                alert("Error! UA!");
            }
        });
    })

    //function of "my collection" button
    $mcb.click(function (){
        if (mcb){
            $mn.show();
            $(".loadMore").hide();
            $mn.each(function (){
                let removeButId = parseInt(this.id)+6;
                if ($("#"+removeButId).css("display")==="none"){    //if remove button is hidden, thus this note is not collected
                    this.style.display = "none";    // we need to hide notes which are not in the collection
                }
                else{
                    this.style.display = "block";
                }
            })

            $mcb.removeAttr("class");
            $mcb.attr("class","btn btn-primary");
            mcb = false;
        }
        else{
            loadInit();
            $mcb.removeAttr("class");
            $mcb.attr("class","btn btn-default");
            mcb = true;
        }
    })

    //"Report this note" button
    $(".report").click(function (event){
        let thisId = parseInt(event.target.id);
        reportNoteId = parseInt($("#"+(thisId - 3)).val());
        noteNumber = parseInt($("#"+(thisId + 1)).val());
        notePageUrl = window.location.href;

    })

    //submit report button
    $("#reportSubmit").click(function (){
        let $reCo = $("#reportContents");
        if(!confirm("Are you sure you want to submit?")){
            return ;
        }
        $.ajax({        //get username of current user
            type: "POST",
            dataType: "json",
            contentType:"application/json; charset=UTF-8",
            url: "/getSessionUserAccount" ,
            success: function (userAccount) {
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    contentType:"application/json; charset=UTF-8",
                    url: "/report" ,
                    data: JSON.stringify({
                        "id" : null,
                        "username" : userAccount.username,
                        "noteId" : reportNoteId,
                        "pageUrl" : notePageUrl,
                        "noteNumber" : noteNumber,
                        "content" : $reCo.val()
                    }),
                    success: function () {
                        $reCo.val("");
                        alert("Report successfully!");
                    },
                    error : function() {
                        alert("Error! REPORT!");
                    }
                });
            },
            error : function() {
                alert("Error! UA");
            }
        });
    })

    //"create note" button
    $nb.click(function () {
        $e.show();      //show editor div
        $('html,body').animate({scrollTop:$e.offset().top}, 500);       //focus on it
        $nb.hide();
    });

    //"hide editor" button
    $he.click(function (){
        $e.hide();
        $('html,body').animate({scrollTop:$("#notesArea").offset().top}, 500);
        $nb.show();
    });

    //"hide from others" button
    $hn.click(function (event){
        let thisId = parseInt(event.target.id);
        let targetNoteId = parseInt($("#"+(thisId - 3)).val());

        let tf = confirm("Are you sure you want to hide this note? ");
        if (!tf){   //if the user is not ready, stop this function
            return null;
        }

        $.ajax({
            type: "POST",
            dataType: "json",
            contentType:"application/json; charset=UTF-8",
            url: "/hideShow" ,
            data: JSON.stringify({
                "noteId" : targetNoteId,
                "visibility": 0
            }),
            success: function () {
                window.location.reload();
            },
            error : function() {
                alert("Error! hide");
            }
        });
    })

    //"show to others" button
    $sn.click(function (event){
        let thisId = parseInt(event.target.id);
        let targetNoteId = parseInt($("#"+(thisId - 4)).val());

        let tf = confirm("Are you sure you want to show this note? ");
        if (!tf){   //if the user is not ready, stop this function
            return null;
        }

        $.ajax({
            type: "POST",
            dataType: "json",
            contentType:"application/json; charset=UTF-8",
            url: "/hideShow" ,
            data: JSON.stringify({
                "noteId" : targetNoteId,
                "visibility": 1
            }),
            success: function () {
                window.location.reload();
            },
            error : function() {
                alert("Error! show");
            }
        });
    })

    //"submit" button
    $sb.click(function (){
        let $title = $("#inputTitle").val() ;
        let $parentId = $("#noteParentId").val();

        let contentLength = $ic.val().length;
        if (contentLength>9900){    //the actual maximum length of content is 9900, extra space is for hidden key values
            contentLength = contentLength + 10;
        }
        // let user know how many characters are typed in the editor and confirm the submission operation
        let tf = confirm("Are you sure you want to submit? your content is "+ contentLength +" characters long, the maximum limit is 10000.");
        if (!tf){   //if the user is not ready, stop this function
            return null;
        }
        if (contentLength>10000){
            alert("Your input content is beyond the limit! Please check again! The maximum length is 10000 characters.")
            return null;
        }

        // ajax request to upload the note into database, both for "lecture" page and "note" page
        $.ajax({
            type: "POST",
            dataType: "json",
            contentType:"application/json; charset=UTF-8",
            url: "/submit" ,
            data: JSON.stringify({
                "id" : null,
                "pageId" : $("#notePageId").val(),
                "username" : $("#noteUsername").val(),
                "title" : $title,
                "content" : $ic.val(),
                "gmtCreate" : null,
                "gmtModified" : null,
                "visibility" : 1,
                "parentId" : $parentId
            }),
            success: function (response) {
                if (response.code === 1000) {
                    alert("Submit Successfully!");
                    if ($title==null){      //if this note have no titles, it is a reply.
                        window.location.replace("/note?noteId="+$parentId);
                    }else {
                        window.location.reload();
                    }

                }
                else {
                     alert("Abnormal!")
                }
            },
            error : function() {
                alert("Error！");
            }
        });
    })

});

