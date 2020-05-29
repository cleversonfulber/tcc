function editarProduto(url) {
	$.get(url, function(entity, status){
		$('#id').val(entity.id);
		$('#nome').val(entity.nome);
		$('#valor').val(entity.valor);
		$('#descricao').val(entity.descricao);
		$('#caracteristica').val(entity.caracteristica);
		$('#tamanho').val(entity.tamanho.id);
		$('#cor').val(entity.cor.id);
		$('#tipo').val(entity.tipo.id);
		$('#marca').val(entity.marca.id);
		$('#categoria').val(entity.categoria.id);
	});
	$('#modal-form').modal();
}


function saveUpload(urlDestino){
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
			swal('Salvo!', 'Registro salvo com sucesso!', 'success');
			window.location = urlDestino;
			
		},
		error: function(){
			swal('Erro!', 'Falha ao salvar o registro!', 'error');
		}
	});//Fim Ajax
}

function limparForm(){
	$('#id').val('');
	$('#nome').val('');
	$('#valor').val('');
	$('#descricao').val('')
	$('#caracteristica').val('')
	$('#tamanho').val('');
	$('#cor').val('');
	$('#tipo').val('' );
	$('#marca').val( '' );
	$('#categoria').val( '' );
	$('#imagem').val( '' );
	$('#modal-form').hide();
}