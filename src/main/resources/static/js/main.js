$(document).ready(function() {
    $(".dropdown-toggle").dropdown();
});

$('#myModal').on('shown.bs.modal', function () {
    $('#myInput').trigger('focus')
})

$(function () {
    $('[data-toggle="tooltip"]').tooltip()
})