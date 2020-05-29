
function editarEndereco(url){
    $.get(url, function(entity, status){
        $('#id').val(entity.id);
        $('#descricao').val(entity.descricao);
        $('#cidade').val(entity.cidade.id);
        $('#usuario').val(entity.usuario.id);
        $('#cep').val(entity.cep);
        $('#numero').val(entity.numero);
        $('#bairro').val(entity.bairro);
        $('#rua').val(entity.rua);
        $('#complemento').val(entity.complemento);
    });
    $('#modal-form').modal();
}

function limparEndereco(){
    $('#id').val('');
    $('#descricao').val('');
    $('#cidade').val('');
    $('#usuario').val('');
    $('#cep').val('');
    $('#numero').val('');
    $('#bairro').val('');
    $('#rua').val('');
    $('#complemento').val('');
    $('#modal-form').hide();
}

//cruds do endereco

function save(urlDestino){
    $.ajax({
        type: $('#frm').attr('method'),
        url: $('#frm').attr('action'),
        data: $('#frm').serialize(),
        success: function(){
            swal.fire('Salvo!', 'Registro salvo com sucesso!', 'success');
            window.location = urlDestino;
        },
        error: function(){
            swal.fire('Erro!', 'Falha ao salvar o registro!', 'error');
        }
    });
}

function delet(id, url){
    swal.fire({
            title: "Confirma a remoção do registro?",
            text: "Esta ação não poderá ser desfeita!",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            cancelButtonText: "Cancelar",
            confirmButtonText: "Remover",
            closeOnConfirm: false
        })
        .then(() => {
            var destino = url + '/' + id;
            $.ajax({
                type: 'DELETE',
                url: destino,
                success: function(){
                    debugger;
                    $('#row_' + id).remove();
                    swal.fire('Removido!',
                        'Registro removido com sucesso!',
                        'success');
                },
                error: function(){
                    debugger;
                    swal.fire('Erro!',
                        'Falha ao remover registro!',
                        'error');
                }
            });
        });//FIM SWAWL;
}

function clear(){
    $('#id').val('');
    $('#descricao').val('');
    $('#cidade').val('');
    $('#cep').val('');
    $('#numero').val('');
    $('#bairro').val('');
    $('#rua').val('');
    $('#complemento').val('');
    $('#modal-form').hide();
}

function edit(url){
    $.get(url, function(entity, status){
        $('#id').val(entity.id);
        $('#descricao').val(entity.descricao);
        $('#cidade').val(entity.cidade.id);
        $('#usuario').val(entity.principal);
        $('#cep').val(entity.cep);
        $('#numero').val(entity.numero);
        $('#bairro').val(entity.bairro);
        $('#rua').val(entity.rua);
        $('#complemento').val(entity.complemento);
    });
    $('#modal-form').modal();
}
