$(function () {
    $("#profileEditButton").click(function () {
        $("#profileA").hide();
        $("#profileB").show();
    });

    $("#profileGoBackButton").click(function () {
        $("#profileA").show();
        $("#profileB").hide();
    });

})
