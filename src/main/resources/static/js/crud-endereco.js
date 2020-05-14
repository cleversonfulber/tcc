
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



