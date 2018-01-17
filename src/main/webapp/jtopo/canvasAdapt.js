$(window).resize(function () {
    resizeCanvas();
    setCenter();
});
function resizeCanvas() {
    var width = $(window).get(0).innerWidth;
    var height = $(window).get(0).innerHeight;
    $("#canvas").attr("width", width - 450);
    $("#canvas").attr("height", height - 190);
    $("#treeDemo").height(height - 100);
    $("#sidebar-sec").height(height - 91);
    $("#sideTree").height(height - 122);
}

$(document).ready(function () {
    resizeCanvas();
});

function cavansAdpt() {
    var width = $(window).get(0).innerWidth;
    var height = $(window).get(0).innerHeight;
    $(document).ready(function () {
        if ($('[class="ace-save-state ace-icon fa fa-angle-double-left"]')[0] !== undefined) {
            $("#canvas").attr("width", width - 300);
            $("#canvas").attr("height", height - 130);
        } else {
            $("#canvas").attr("width", width - 450);
            $("#canvas").attr("height", height - 190);
        }
        setCenter();
    });
}