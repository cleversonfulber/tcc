
function limparUsuario() {

	$('#id').val('');
	$('#nome').val('');
	$('#username').val('');
	$('#password').val('');
	$('#telefone').val('');
	$('#celular').val('');
	$('#cpfCnpj').val('');
	$('#dataNascimento').val('');
	$('#genero').val('');
	$('#frm input:checkbox:checked').each(function(){
		$(this).prop('checked', false);
	});

}

function editarUsuario(id, url) {

	$.get( url+ '/' + id, function(entity, status){
		$('#id').val(entity.id);
		$('#nome').val(entity.nome);
		$('#sobrenome').val(entity.sobrenome);
		$('#username').val(entity.username);
		$('#telefone').val(entity.telefone);
        $('#celular').val(entity.celular);
        $('#cpfCnpj').val(entity.cpfCnpj);
        $('#dataNascimento').val(entity.dataNascimento);
        $('#genero').val(entity.genero);
		$.each(entity.permissoes, function(i, item) {
			$('#chk_'+entity.permissoes[i].id).prop('checked', true);
		});
	});
	$('#modal-form').modal();
}