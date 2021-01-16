$(".custom-file-input").on("change", function () {
    var fileName = $(this).val().split("\\").pop();
    $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
});

<!-- Hide icon upload button script-->
$('[name=icon-submit]').hide();

setInterval(function () {
    if ($('#imageURL1').val() != "") {
        $('[name=icon-submit]').show();
    } else {
        $('[name=icon-submit]').hide();
    }
}, 1000);

<!-- Hide model upload button script-->
    $('[name=model-submit]').hide();

    setInterval(function () {
    if ($('#imageURL2').val() != "") {
    $('[name=model-submit]').show();
} else {
    $('[name=model-submit]').hide();
}
}, 1000);