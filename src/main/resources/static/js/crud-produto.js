
function novoProduto(){
    swal({
        title: "Seu anuncio passara por validação!",
        text: "Seus produtos estarão disponíveis após o anuncio ser validado.",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#0d7d27",
        cancelButtonText: "Cancelar",
        confirmButtonText: "Confirmar"
        }, function(){

            $('#modal-form').modal();
        }
    );

}

function editarProduto(url) {

    swal({
        title: "Seu anuncio passara por validação!",
        text: "Seus produtos estarão disponíveis após o anuncio ser validado.",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#0d7d27",
        cancelButtonText: "Cancelar",
        confirmButtonText: "Confirmar"
        }, function(){
            $.get(url, function(entity, status){
                $('#id').val(entity.id);
                $('#nome').val(entity.nome);
                $('#qtde').val(entity.cor.id);
                $('#valor').val(entity.valor.toString().replace('.',','));
                $('#descri').val(entity.descri);
                $('#caracteristica').val(entity.caracteristica);
                $('#cor').val(entity.cor.id);
                $('#tipo').val(entity.tipo.id);
                $('#marca').val(entity.marca.id);
                $('#categoria').val(entity.categoria.id);
        //		$('#anexos').val(entity.imagem);
                $.each(entity.tamanhos, function(i, item) {
                    $('#chk_'+entity.tamanhos[i].id).prop('checked', true);
                });
            });
            $('#modal-form').modal();
        }
    );
}


function saveUpload(urlDestino){

    var id = document.getElementById('anuncio').value;

    $.get( '/anuncios/editarValidacao/' + id, function(entity, status){
    });

    $('#frm').validate();
    if (!$('#frm').valid()){
        return false;
    }
    $("#loader").show();
	var formData = new FormData($('#frm')[0]);

	$.ajax({
		type: $('#frm').attr('method'),
		 url: $('#frm').attr('action'),
		data: formData,
		async: false,
		cache: false,
		contentType: false,
		processData: false,
		success: function(){
		    $("#loader").hide();
			swal('Salvo!', 'Registro salvo com sucesso!', 'success');
			setTimeout(function() {
                window.location = urlDestino;
            }, 2000);
			document.location.reload();
		},
		error: function(){
		    $("#loader").hide();
			swal('Erro!', 'Falha ao salvar o registro!', 'error');
		}
	});//Fim Ajax
}

function limparForm(){
	$('#id').val('');
	$('#nome').val('');
	$('#valor').val('');
	$('#descri').val('')
	$('#caracteristica').val('')
	$('#cor').val('');
	$('#tipo').val('' );
	$('#marca').val( '' );
	$('#categoria').val( '' );
	$('#qtde').val( '' );
	$('#frm input:checkbox:checked').each(function(){
        $(this).prop('checked', false);
    });
	$('#modal-form').hide();
}

//promocao

function verPromocao(url){
    window.location = url;
}

function novaPromocao(id){
    $('#modal-form2').modal();
    document.getElementById('idProduto').value = id;
}

function limparPromocao(){
    $('#id2').val('');
    $('#precoPromocional').val('');
    $('#dataInicio').val('');
    $('#dataFim').val('');
    $('#modal-form').hide();
}

function salvarPromocao(){
    $('#frm2').validate();
    if (!$('#frm2').valid()){
    	return false;
    }
//    var idProduto = document.getElementById('idProduto').value;
    var idPromocao = document.getElementById('id2').value;

    $.get( '/produtos/editarProduto/' + idPromocao, function(entity, status){
    });

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
                type: $('#frm2').attr('method'),
                url: $('#frm2').attr('action'),
                data: $('#frm2').serialize(),
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
                    type: $('#frm2').attr('method'),
                    url: $('#frm2').attr('action'),
                    data: $('#frm2').serialize(),
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
                type: $('#frm2').attr('method'),
                url: $('#frm2').attr('action'),
                data: $('#frm2').serialize(),
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
                    type: $('#frm2').attr('method'),
                    url: $('#frm2').attr('action'),
                    data: $('#frm2').serialize(),
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
