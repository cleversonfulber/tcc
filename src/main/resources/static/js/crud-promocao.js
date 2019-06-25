
function editarPromocao(url){
    $.get(url, function(entity, status){
        $('#id').val(entity.id);
        $('#precoPromocional').val(entity.precoPromocional);
        $('#dataInicio').val( formatDate(entity.dataInicio) );
        if (entity.dataFim)
            $('#dataFim').val( formatDate(entity.dataFim) );
    });
    $('#modal-form').modal();
}

function limparPromocao(){
    $('#id').val('');
    $('#precoPromocional').val('');
    $('#dataInicio').val('');
    $('#dataFim').val('');
    $('#modal-form').hide();
}
function formatDate(inputFormat){
    function pad(s){
        return (s < 10) ? '0' + s : s;
    }
    var d = new Date(inputFormat);
    return [ pad(d.getDate()), pad(d.getMonth()+1), d.getFullYear() ].join('/');
}


