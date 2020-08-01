$(document).ready(function () {
    $('#dtVerticalScroll').DataTable({
        "scrollY": "200px",
        "scrollCollapse": true,
        "ordering" : false,
        "language": {
        "search": "Поиск:",
        "lengthMenu": "Показать _MENU_ записей.",
        "zeroRecords": "Поиск не дал результатов!",
        "infoEmpty": "Нет сохраненных конвертаций!",
        "info": "Показана страница _PAGE_ из _PAGES_",
        "infoFiltered": "(отфильтровано из _MAX_ записей)",
                "oPaginate": {
                    "sNext":    "След.",
                    "sPrevious": "Пред."
                }
        }
    });
});