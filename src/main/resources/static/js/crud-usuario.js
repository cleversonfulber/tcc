
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

function validarUsuario(id, url) {

	$.get( url+ '/' + id, function(entity, status){
		$('#id').val(entity.id);
		$('#nome').val(entity.nome);
		$('#sobrenome').val(entity.sobrenome);
		$('#username').val(entity.username);
//		$('#password').val(entity.password);
		$('#telefone').val(entity.telefone);
        $('#celular').val(entity.celular);
        $('#cpfCnpj').val(entity.cpfCnpj);
//        $('#dataNascimento').val(entity.dataNascimento);
//        $('#genero').val(entity.genero);
        $('#chk_3').prop('checked', true);

	});
	swal('Alerta!', 'Dados apenas para conferência!', 'warning');
	$('#modal-form').modal();
}

function validarLojista( url) {

    var id = $("#id");
    id = id[0].value;

    $.get( url+ '/salvar/' + id, function(entity, status){
	});
	swal({
        type: 'success',
        title: 'Parceiro Registrado!',
        timer: 2000,
        showConfirmButton: false
    });
	setTimeout(function() {
        window.location = url;
    }, 2000);

}

function salvarValidarUsuario(urlDestino){

    $.ajax({
        type: $('#frm').attr('method'),
        url: $('#frm').attr('action'),
        data: $('#frm').serialize(),
        function(){
            swal({
                type: 'success',
                title: 'Registro concluido com sucesso!',
                timer: 2000,
                showConfirmButton: false
            });
            setTimeout(function() {
                window.location = urlDestino;
            }, 2000);
        }
    });//Fim Ajax
}

//function validarSenha(){
//   senha = document.getElementsByName('password').value;
//   senha2 = document.getElementsByName('senha').value;
//
//   if(senha!= senha2) {
//        senha2.setCustomValidity("Senhas diferentes!");
//       return false;
//   }
//   return true;
//}

function salvarUsuario(urlDestino){

//    senha = document.getElementsByName('password').value;
//    senha2 = document.getElementsByName('current-password').value;
//
//    if(senha!= senha2) {
//        swal('Erro!', 'Senhas Incoretas!', 'error');
//        return error;
//   }
    $('#frm').validate();
        if (!$('#frm').valid()){
        	return false;
        }
    $.ajax({
        type: $('#frm').attr('method'),
        url: $('#frm').attr('action'),
        data: $('#frm').serialize(),
        success: function(){
            swal({
                type: 'success',
                title: 'Registro concluido com sucesso!',
                timer: 2000,
                showConfirmButton: false
            });
            setTimeout(function() {
                window.location = urlDestino;
            }, 2000);
        },
        error: function(){
            swal('Erro!', 'Falha ao salvar o registro!', 'error');
        }
    });//Fim Ajax
}