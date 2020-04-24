
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


function novo() {
    $('#modal-form').modal();
}

function limpar(){
    $('#id').val('');
    $('#nome').val('');
    $('#modal-form').hide();
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

redireciona(){
    window.location = "../";
}


