$('#frm').submit(function(e){

	$.ajax({
		type: $('#frm').attr('method'),
		 url: $('#frm').attr('action'),
		data: $('#frm').serialize(),
		success: function(){
			swal('Salvo!', 'Registro salvo com sucesso!', 'success');
			location.reload();
		},
		error: function(){
			swal('Errou!', 'Falha ao salvar o registro!', 'error');
		}
	});//Fim Ajax
	return false;
 });

function limparUsuario() {

	$('#id').val('');
	$('#nome').val('');
	$('#username').val('');
	$('#password').val('');
	$('#frm input:checkbox:checked').each(function(){
		$(this).prop('checked', false);
	});

}

function editarUsuario(id) {
	clearForm();
	$.get('usuario/' + id, function(entity, status){
		$('#id').val(entity.id);
		$('#nome').val(entity.nome);
		$('#username').val(entity.username);
		$.each(entity.permissoes, function(i, item) {
			$('#chk_'+entity.permissoes[i].id).prop('checked', true);
		});
	});
	$('#modal-form').modal();
}