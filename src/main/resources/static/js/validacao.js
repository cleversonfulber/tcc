$(document).ready(function() {

    $('#frm').validate({
        rules:{
            nome: {
                required: true,
                maxlength: 100,
                minlength: 3
            },
            estado: {
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
            email: {
                required: true,
                email:true
            },
            telefone: {
                required: false,
                maxlength: 13
            },
            celular: {
                required: true,
                maxlength: 14,
                minlength: 14
            },
            sobrenome: {
                required: true,
                maxlength: 60,
                minlength: 3
            },
            cpfCnpj: {
                required: true
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
                maxlength: 8,
                minlength: 8
            },
            numero: {
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

