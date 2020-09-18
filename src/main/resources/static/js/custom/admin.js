$(function (){

    // displays the report html page in the iframe of the backstage
    $("#reportBtn").click(function (){
        $("#pages").html('<iframe id=\"pages\" src=\"/admin/report\" style=\"width: 100%;height: 100%;\" ></iframe>');
    })

    // displays the course html page in the iframe of the backstage
    $("#courseBtn").click(function (){
        $("#pages").html('<iframe id=\"pages\" src=\"/admin/course\" style=\"width: 100%;height: 100%;\" ></iframe>');
    })
})