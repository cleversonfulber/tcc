const compra = new Carrinho();
const listaChecar = document.querySelector('#lista-checar tbody');
const carrinho = document.getElementById('carrinho');
const finalizarCompraBtn = document.getElementById('finalizar-pedido');
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


    finalizarCompraBtn.addEventListener('click', (e) => {compra.finalizarCompra(e)});

}

