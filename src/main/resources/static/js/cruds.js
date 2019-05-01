// // Categoria
//
// $('.btnNovoCat').on('click', function (event) {
//     event.preventDefault();
//
//     $('.modal-title').text('Novo') ;
//     $('#nomeCat').val('');
//
//     $('#modalCat').modal();
//
// });
//
// $('.btnEditCat').on('click', function (event) {
//     event.preventDefault();
//     var href = $(this).attr('href');
//
//     $('.modal-title').text('Editar');
//     $.get(href, function (categoria, status) {
//         $('#formNewEdit #id').val(categoria.id);
//         $('#formNewEdit #nomeCat').val(categoria.nome);
//     });
//
//     $('#modalCat').modal();
//
// });
//
// // Marca
//
// $('.btnNovoMarca').on('click', function (event) {
//     event.preventDefault();
//
//     $('.modal-title').text('Novo') ;
//     $('#nomeMarca').val('');
//
//     $('#modalMarca').modal();
//
// });
//
// $('.btnEditMarca').on('click', function (event) {
//     event.preventDefault();
//     var href = $(this).attr('href');
//
//     $('.modal-title').text('Editar');
//     $.get(href, function (marca, status) {
//         $('#formNewEdit #id').val(marca.id);
//         $('#formNewEdit #nomeMarca').val(marca.nome);
//     });
//
//     $('#modalMarca').modal();
//
// });
//
//
// // Tipo
//
// $('.btnNovoTipo').on('click', function (event) {
//     event.preventDefault();
//
//     $('.modal-title').text('Novo') ;
//     $('#nomeTipo').val('');
//
//     $('#modalTipo').modal();
//
// });
//
// $('.btnEditTipo').on('click', function (event) {
//     event.preventDefault();
//     var href = $(this).attr('href');
//
//     $('.modal-title').text('Editar');
//     $.get(href, function (tipo, status) {
//         $('#formNewEdit #id').val(tipo.id);
//         $('#formNewEdit #nomeTipo').val(tipo.nome);
//     });
//
//     $('#modalTipo').modal();
//
// });

// salvar---------------------------------------------------------------------------------------------------------------

function salvar(urlDestino){
    $.ajax({
        type: $('#frm').attr('method'),
        url: $('#frm').attr('action'),
        data: $('#frm').serialize(),
        success: function(){
            swal('Salvo!', 'Registro salvo com sucesso!', 'success');
            window.location = urlDestino;
        },
        error: function(){
            swal('Erro!', 'Falha ao salvar o registro!', 'error');
        }
    });//Fim Ajax
}

function editar(url){
    $.get(url, function(entity, status){
        $('#id').val(entity.id);
        $('#nome').val(entity.nome);
    });
    $('#modal-form').modal();
}

function excluir(id, url){
    swal({
            title: "Confirma a remoção do registro?",
            text: "Esta ação não poderá ser desfeita!",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            cancelButtonText: "Cancelar",
            confirmButtonText: "Remover",
            closeOnConfirm: false
        }, function(){
            var destino = url + '/' + id;
            $.ajax({
                type: 'DELETE',
                url: destino,
                success: function(){
                    $('#row_' + id).remove();
                    swal('Removido!',
                        'Registro removido com sucesso!',
                        'success');
                },
                error: function(){
                    swal('Erro!',
                        'Falha ao remover registro!',
                        'error');
                }
            });//Fim ajax
        }
    );//FIM SWAWL
}


