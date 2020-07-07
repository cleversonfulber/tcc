$(document).ready(function() {

    $('#frm').validate({
        rules:{
            nome: {
                required: true,
                maxlength: 100,
                minlength: 3
            },
            uf: {
                required: true
            },
            sigla: {
                required: true,
                maxlength: 2,
                minlength: 2
            },
            valor: {
                required: true
            },
            cor: {
                required: true
            },
            categoria: {
                required: true
            },
            marca: {
                required: true
            },
            tipo: {
                required: true
            },
            descricao: {
                required: true,
                maxlength: 15
            },
            descri:{
                required: true,
                maxlength: 254,
                minlength: 3
            },
            caracteristica: {
                required: true,
                maxlength: 254,
                minlength: 3
            },
            precoPromocional: {
                required: true
            },
            dataInicio: {
                required: true
            },
            dataFim: {
                required: true
            },
            username: {
                required: true,
                email:true
            },
            password:{
                required: true,
                minlength: 8,
                maxlength: 20
            },
            telefone: {
                required: false,
                maxlength: 14
            },
            celular: {
                required: true,
                maxlength: 15,
                minlength: 15
            },
            sobrenome: {
                required: true,
                maxlength: 60,
                minlength: 3
            },
            cpf: {
                required: true,
                minlength: 3

            },
            cnpj: {
                required: true,
                minlength: 3
            },
            dataNascimento: {
                required: true
            },
            genero: {
                required: true
            },
            produto: {
                required: true
            },
            imagem: {
                required: true
            },
            usuario: {
                required: true
            },
            qtde: {
                required: true,
                maxlength: 3
            },
            cidade: {
                required: true
            },
            cep: {
                required: true,
                maxlength: 9,
                minlength: 9
            },
            numero: {
                required: true,
                maxlength: 7
            },
            bairro: {
                required: true,
                maxlength: 60
            },
            rua: {
                required: true,
                maxlength: 60
            },
            complemento: {
                required: true,
                maxlength: 30
            }
        }
    });

});


$('input[name="FlgPontua"]').change(function () {
    if ($('input[name="FlgPontua"]:checked').val() === "cpf") {
        $('.cpf').show();
        $('.cpf *').attr('disabled', false);
        $('.cnpj').hide();
        $('.cnpj *').attr('disabled', true);
        $('.validar').hide();
        $('#validar').value("false");
    } else if ($('input[name="FlgPontua"]:checked').val() === "cnpj") {
        $('.cnpj').show();
        $('.cnpj *').attr('disabled', false);
        $('.cpf').hide();
        $('.cpf *').attr('disabled', true);
        $('.validar').show();
        $('#validar').value("true");
    } else{
        Swal.fire({
            type: 'error',
            title: 'Deu Erro!',
            timer: 2500,
            showConfirmButton: false
        })
    }
});

