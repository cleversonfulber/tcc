function salvarPromocao(urlDestino){
    $('#frm').validate();
    if (!$('#frm').valid()){
    	return false;
    }

    let dataInicio = document.getElementById('dataInicio').value;
    let dataFim = document.getElementById('dataFim').value;

    // Precisamos quebrar a string para retornar cada parte
    const dataSplit1 = dataInicio.split('/');
    const dataSplit2 = dataFim.split('/');

    const dia1 = dataSplit1[0];
    const mes1 = dataSplit1[1];
    const ano1 = dataSplit1[2];

    const dia2 = dataSplit2[0];
    const mes2 = dataSplit2[1];
    const ano2 = dataSplit2[2];

    if (ano1 == ano2){
        if (mes1 < mes2){
            $.ajax({
                type: $('#frm').attr('method'),
                url: $('#frm').attr('action'),
                data: $('#frm').serialize(),
                success: function(){
                    swal('Salvo!', 'Registro salvo com sucesso!', 'success');
                    setTimeout(function() {
                        document.location.reload();
                    }, 2000);
                },
                error: function(){
                    swal('Erro!', 'Falha ao salvar o registro!', 'error');
                }
            });//Fim Ajax
        }else if(mes1 == mes2){
            if (dia1 < dia2){
                $.ajax({
                    type: $('#frm').attr('method'),
                    url: $('#frm').attr('action'),
                    data: $('#frm').serialize(),
                    success: function(){
                        swal('Salvo!', 'Registro salvo com sucesso!', 'success');
                        setTimeout(function() {
                            document.location.reload();
                        }, 2000);
                    },
                    error: function(){
                        swal('Erro!', 'Falha ao salvar o registro!', 'error');
                    }
                });//Fim Ajax
            }else{
                swal('Erro!', '"Data Final" deve ser Maior que a "Data Inicial!"', 'error');
            }
        }else{
            swal('Erro!', '"Data Final" deve ser Maior que a "Data Inicial!"', 'error');
        }
    }else if (ano1 < ano2){
        if (mes1 != mes2){
            $.ajax({
                type: $('#frm').attr('method'),
                url: $('#frm').attr('action'),
                data: $('#frm').serialize(),
                success: function(){
                    swal('Salvo!', 'Registro salvo com sucesso!', 'success');
                    setTimeout(function() {
                        document.location.reload();
                    }, 2000);
                },
                error: function(){
                    swal('Erro!', 'Falha ao salvar o registro!', 'error');
                }
            });//Fim Ajax
        }else{
            if (dia1 < dia2){
                $.ajax({
                    type: $('#frm').attr('method'),
                    url: $('#frm').attr('action'),
                    data: $('#frm').serialize(),
                    success: function(){
                        swal('Salvo!', 'Registro salvo com sucesso!', 'success');
                        setTimeout(function() {
                            document.location.reload();
                        }, 2000);
                    },
                    error: function(){
                        swal('Erro!', 'Falha ao salvar o registro!', 'error');
                    }
                });//Fim Ajax
            }else{
                swal('Erro!', '"Data Final" deve ser Maior que a "Data Inicial!"', 'error');
            }
            swal('Erro!', '"Data Final" deve ser Maior que a "Data Inicial!"', 'error');
        }
    }else{
        swal('Erro!', '"Data Final" deve ser Maior que a "Data Inicial!"', 'error');
    }
}

function editarPromocao(url){
    $.get(url, function(entity, status){
        $('#id').val(entity.id);
        $('#precoPromocional').val(entity.precoPromocional.toString().replace('.',','));
        $('#dataInicio').val(formatDate(entity.dataInicio));
        $('#dataFim').val(formatDate(entity.dataFim));
    });
    $('#modal-form').modal();
}

function formatDate2(data) {
    return (data.substr(0, 10).split('-').reverse().join('/'));
}

function formatDate(data) {
    return (data.substr(0, 10).split('-').reverse().join('/'));
}

function limparPromocao(){
    $('#id').val('');
    $('#precoPromocional').val('');
    $('#dataInicio').val('');
    $('#dataFim').val('');
    $('#modal-form').hide();
}

function voltarAterior(){
    window.history.back();
}
