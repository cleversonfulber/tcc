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

function changeType() {
  let cpf = document.getElementById('pessoa-fisica');
  let cnpj = document.getElementById('pessoa-juridica');

  let cpfForm = document.getElementById('formulario-cpf');
  let cnpjForm = document.getElementById('formulario-cnpj');

  if(cpf.checked) {
    cpfForm.classList.remove('d-none');

    cnpjForm.classList.add('d-none');
    document.getElementById('input-cnpj').value = '';
  } else if(cnpj.checked) {
    cnpjForm.classList.remove('d-none');

    cpfForm.classList.add('d-none');
    document.getElementById('input-cpf').value = '';
  }
}
