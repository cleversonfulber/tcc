const compra = new Carrinho();
const listaChecar = document.querySelector('#lista-checar tbody');
const carrinho = document.getElementById('carrinho');
const cliente = document.getElementById('cliente');
const correio = document.getElementById('correio');

const endereco = new Endereco();
const enderecoChecar = document.querySelector('#residencia tbody');

carregarEventos();

function carregarEventos(){

    document.addEventListener('DOMContentLoaded', compra.lerLocalStorageChecar());
    $("#carrinho tbody tr td a.excluir-produto").click((e) => compra.excluirProduto(e));
    $("#carrinho tbody tr td input.qtda").on('keyup mouseup', (e) => compra.alterarQtda(e));
    compra.calcularTotal();
    document.addEventListener('DOMContentLoaded', endereco.lerLocalStorageEndereco());



}

function enviarEmail(){

    var email = document.getElementById("email").innerHTML
    var endereco = document.getElementById("usuario").innerHTML
    var produto = document.getElementById("carrinho").innerHTML

    var corpo = endereco + '<br>' + produto

    Email.send({
        SecureToken : "46c1d9cf-d925-4e92-99d3-ea6d3509bc49", //email e senha criptografados
        To : email, // só funciona com gmail
        From : "cleversonfulber@alunos.utfpr.edu.br",
        Subject : "Recebemos seu pedido",
        Body : corpo
    }).then(
        Swal.fire({
            type: 'success',
            title: 'Pedido Finalizado com sucesso!',
            timer: 2500,
            showConfirmButton: false
        })

    );

    localStorage.clear();
    window.location.replace("../");
}

function finalizar(){

//    document.getElementById("valor").innerHTML = document.getElementById("total").innerHTML
//
//
//    document.getElementById("endereco").innerHTML = 1
//    document.getElementById("produtos").innerHTML = 1

    //var dados = JSON.stringify({"endereco_id":"1","produto_id":"1","valor": "112"});


//        type: "POST",
//        url: "/checagem/ajax",
//        dataType: 'JSON',
//        data: dados,

    $.ajax({
        type: $('#frm').attr('method'),
        url: $('#frm').attr('action'),
        data: $('#frm').serialize(),
        success: function(){
            enviarEmail();
            localStorage.clear();
            location.href = "../";
        },
        error: function(){
            Swal.fire('Erro!', 'Falha ao salvar o registro!', 'error');
        }
    });//Fim Ajax
}
