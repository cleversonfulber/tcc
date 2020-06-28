$(document).ready( function () {
    $('#minhaTable').DataTable({
        "language": {
            "lengthMenu": "Mostrando _MENU_ registro por página",
            "zeroRecords": "Nada encontrado",
            "info": "Mostrando página _PAGE_ de _PAGES_",
            "infoEmpty": "Nenhum registro disponível",
            "infoFiltered": "(Filtrando de _MAX_ registros no total)",
            "search": "Pesquisar:"
        },
        "lengthMenu": [ [5, 25, 50, -1], [5, 25, 50, "All"] ]
    });
    $('#tableCategoria').DataTable({
        "language": {
            "search": "Pesquisar",
            "zeroRecords": "Nada encontrado"
        }
    });
    $('#tableMarca').DataTable({
        "language": {
            "search": "Pesquisar",
            "zeroRecords": "Nada encontrado"
        }
    });
    $('#tableTipo').DataTable({
        "language": {
            "search": "Pesquisar",
            "zeroRecords": "Nada encontrado"
        }
    });
    $('#tableCor').DataTable({
        "language": {
            "search": "Pesquisar",
            "zeroRecords": "Nada encontrado"
        }
    });
    $('#tableTamanho').DataTable({
        "language": {
            "search": "Pesquisar",
            "zeroRecords": "Nada encontrado"
        }
    });
} );

$(document).ready(function() {

    function formata(v){
       return parseFloat(v).toLocaleString("pt-BR", { style: "currency" , currency:"BRL"});
    }

//    var valor = document.getElementById("vlr").innerHTML
//    document.getElementById("vlr").innerHTML = formata(valor)
//
//    var valor2 = document.getElementById("vlr2").innerHTML
//    document.getElementById("vlr2").innerHTML = formata(valor2)

//    document.getElementById("vlr2").innerHTML = document.getElementById("vlr2").innerHTML.replace('.',',')
});
