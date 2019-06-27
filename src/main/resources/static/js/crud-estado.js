
function editarEstado(url){
    $.get(url, function(entity, status){
        $('#id').val(entity.id);
        $('#nome').val(entity.nome);
        $('#sigla').val(entity.sigla);
    });
    $('#modal-form').modal();
}

function limparEstado(){
    $('#id').val('');
    $('#nome').val('');
    $('#sigla').val('');
    $('#modal-form').hide();
}



