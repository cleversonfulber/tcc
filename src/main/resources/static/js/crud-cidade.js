
function editarCidade(url){
    $.get(url, function(entity, status){
        $('#id').val(entity.id);
        $('#nome').val(entity.nome);
        $('#cidade').val(entity.estado.id);
    });
    $('#modal-form').modal();
}

function limparCidade(){
    $('#id').val('');
    $('#nome').val('');
    $('#cidade').val('');
    $('#modal-form').hide();
}



