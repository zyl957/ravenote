$(function (){
    let $it = $("#inputTitle");
    let $sb = $("#submitButton");
    let $sim =$(".simditor-body");    //Simditor will automatically generate a div.simditor-body to store autosave content

    $sim.empty();
    $sim.append($("#hiddenContext").val());     // Disable autosave of Simditor and append the content need to edit

    // disables the "submit" button if a note is being edited while the title is empty
    $it.on("input propertychange",function (){
            if ($it.val()===""){
                $sb.attr("disabled","disabled");
                $sb.attr("title","A title is required here.");
            }else{
                $sb.removeAttr("disabled");
                $sb.removeAttr("title");
            }
        })
})