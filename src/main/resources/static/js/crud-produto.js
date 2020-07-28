function editarProduto(url) {
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


function saveUpload(urlDestino){
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
			document.location.reload(true);
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