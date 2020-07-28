
//cruds do endereco

function save(urlDestino){
    $('#frm').validate();
    if (!$('#frm').valid()){
    	return false;
    }
    $.ajax({
        type: $('#frm').attr('method'),
        url: $('#frm').attr('action'),
        data: $('#frm').serialize(),
        success: function(){
            swal.fire('Salvo!', 'Registro salvo com sucesso!', 'success');
            setTimeout(function() {
                window.location = urlDestino;
            }, 2000);
        },
        error: function(){
            swal.fire('Erro!', 'Falha ao salvar o registro!', 'error');
        }
    });
}

function delet(id, url){
    swal.fire({
            title: "Confirma a remoção do registro?",
            text: "Esta ação não poderá ser desfeita!",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            cancelButtonText: "Cancelar",
            confirmButtonText: "Remover",
            closeOnConfirm: false
        })
        .then(() => {
            var destino = url + '/' + id;
            $.ajax({
                type: 'DELETE',
                url: destino,
                success: function(){
                    debugger;
                    $('#row_' + id).remove();
                    swal.fire('Removido!',
                        'Registro removido com sucesso!',
                        'success');
                },
                error: function(){
                    debugger;
                    swal.fire('Erro!',
                        'Falha ao remover registro!',
                        'error');
                }
            });
        });//FIM SWAWL;
}

function limparEndereco(){
    $('#id').val('');
    $('#descricao').val('');
    $('#cidade').val('');
    $('#uf').val('');
    $('#cep').val('');
    $('#numero').val('');
    $('#bairro').val('');
    $('#rua').val('');
    $('#complemento').val('');
    $('#modal-form').hide();
}

function limparForm(){
    $('#cidade').val('');
    $('#uf').val('');
    $('#cep').val('');
    $('#bairro').val('');
    $('#rua').val('');
}

function edit(url){
    $.get(url, function(entity, status){
        $('#id').val(entity.id);
        $('#descricao').val(entity.descricao);
        $('#cidade').val(entity.cidade);
        $('#uf').val(entity.uf);
        $('#cep').val(entity.cep);
        $('#numero').val(entity.numero);
        $('#bairro').val(entity.bairro);
        $('#rua').val(entity.rua);
        $('#complemento').val(entity.complemento);
    });
    $('#modal-form').modal();
}


function meu_callback(conteudo) {
    if (!("erro" in conteudo)) {
        //Atualiza os campos com os valores.
        document.getElementById('rua').value=(conteudo.logradouro);
        document.getElementById('bairro').value=(conteudo.bairro);
        document.getElementById('cidade').value=(conteudo.localidade);
        document.getElementById('uf').value=(conteudo.uf);
    } //end if.
    else {
        //CEP não Encontrado.
//        alert("CEP não encontrado.");
        swal.fire('Erro!','CEP não encontrado.','error');
        limparForm();
    }
}

function pesquisacep(valor) {

    //Nova variável "cep" somente com dígitos.
    var cep = valor.replace(/\D/g, '');

    //Verifica se campo cep possui valor informado.
    if (cep != "") {

        //Expressão regular para validar o CEP.
        var validacep = /^[0-9]{8}$/;

        //Valida o formato do CEP.
        if(validacep.test(cep)) {

            //Preenche os campos com "..." enquanto consulta webservice.
            document.getElementById('rua').value="...";
            document.getElementById('bairro').value="...";
            document.getElementById('cidade').value="...";
            document.getElementById('uf').value="...";

            //Cria um elemento javascript.
            var script = document.createElement('script');

            //Sincroniza com o callback.
            script.src = 'https://viacep.com.br/ws/'+ cep + '/json/?callback=meu_callback';

            //Insere script no documento e carrega o conteúdo.
            document.body.appendChild(script);

        } //end if.
        else {
            //cep é inválido.
//            alert("Formato de CEP inválido.");
            swal.fire('Erro!','Formato de CEP inválido.','error');
            limparForm();
        }
    } //end if.
    else {
        //cep sem valor, limpa formulário.
        limparForm();
    }
};
