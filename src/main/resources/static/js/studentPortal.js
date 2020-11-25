$("document").ready(function () {
   $(".editable").hide();
});


const onEdit = () => {
    $(".editable-item").hide();
    $(".editable").show();
}