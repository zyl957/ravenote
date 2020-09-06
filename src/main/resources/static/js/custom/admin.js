$(function (){

    $("#reportBtn").click(function (){
        $("#pages").html('<iframe id=\"pages\" src=\"/admin/report\" style=\"width: 100%;height: 100%;\" ></iframe>');
    })

    $("#courseBtn").click(function (){
        $("#pages").html('<iframe id=\"pages\" src=\"/admin/course\" style=\"width: 100%;height: 100%;\" ></iframe>');
    })
})