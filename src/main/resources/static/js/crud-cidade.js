
function editarCidade(url){
    $.get(url, function(entity, status){
        $('#id').val(entity.id);
        $('#nome').val(entity.nome);
        $('#estado').val(entity.estado.id);
    });
    $('#modal-form').modal();
}

function limparCidade(){
    $('#id').val('');
    $('#nome').val('');
    $('#estado').val('');
    $('#modal-form').hide();
}



