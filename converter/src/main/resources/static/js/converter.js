$(document).ready(function () {
    $('#dtVerticalScroll').DataTable({
        "scrollY": "200px",
        "scrollCollapse": true,
        "ordering" : false,
        "language": {
        "zeroRecords": "Вы еще не производили конвертаций!",
                "oPaginate": {
                    "sNext":    "След.",
                    "sPrevious": "Пред."
                }
        }
    });
});