function salvarPromocao(urlDestino){

    $('#frm').validate();
    if (!$('#frm').valid()){
    	return false;
    }
    this.validaDatas();

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

function editarPromocao(url){
    $.get(url, function(entity, status){
        $('#id').val(entity.id);
        $('#precoPromocional').val(entity.precoPromocional.toString().replace('.',','));
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


function validaDatas(){

    var d = new Date();
    dataHora = (d.toLocaleString());
    // alert(d.toLocaleString());

    // Mostrando data no campo
    d =  $('#dataInicial').val();
    d = $('#dataInicial').val(dataHora);

//    $("span").text("Not valid!").show().fadeOut(1000);
//
//    var dataInicial = ($("input[name='dataInicial']").val());
    var dataFinal = ($("input[name='dataFim']").val());
    if (!dataInicial || !dataFinal) return false;
    if (dataInicial >= dataFinal) {
        alert("Data incorreta!");
        return false;
    } else {
        alert("Data Correta!");
        return true
    }
}



