$(document).ready( function () {
    $('#lista-produtos').DataTable({
        "language": {
            "lengthMenu": "Mostrando _MENU_ registro por página",
            "zeroRecords": "Nada encontrado",
            "info": "Mostrando página _PAGE_ de _PAGES_",
            "infoEmpty": "Nenhum registro disponível",
            "infoFiltered": "(Filtrando de _MAX_ registros no total)",
            "search": "Pesquisar",
            "previous": "Anterior",
            "next": "Próximo"
        },
        "lengthMenu": [ [5, 25, 50, -1], [5, 25, 50, "All"] ]
    });
} );


