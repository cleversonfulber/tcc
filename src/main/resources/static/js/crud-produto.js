
function novoProduto(){
    swal({
        title: "Seu anúncio passará por validação!",
        text: "Seus produtos estarão disponíveis após o anúncio ser validado.",
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
        title: "O produto perderá o valor promocional!",
        text: "Seus produtos só estarão disponíveis após o anúncio ser válidado pelo administrador.",
        type: "warning",
        showCancelButton: true, 
        confirmButtonColor: "#0d7d27",
        cancelButtonText: "Cancelar",
        confirmButtonText: "Confirmar"
        }, function(){
            $.get(url, function(entity, status){
                $('#id').val(entity.id);
                $('#nome').val(entity.nome);
                $('#qtde').val(entity.qtde);
                $('#valor').val(entity.valor.toString().replace('.',','));
                $('#descri').val(entity.descri);
                $('#caracteristica').val(entity.caracteristica);
                $('#cor').val(entity.cor.id);
                $('#tipo').val(entity.tipo.id);
                $('#marca').val(entity.marca.id);
                $('#categoria').val(entity.categoria.id);
                $.each(entity.tamanhos, function(i, item) {
                    $('#chk_'+entity.tamanhos[i].id).prop('checked', true);
                });
            });
            $('#modal-form').modal();
        }
    );
}


function saveUpload(urlDestino){

//    mudar para não validado
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
