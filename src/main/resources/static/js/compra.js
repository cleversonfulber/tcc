
const compra = new Carrinho();
const listaCompra = document.querySelector('#lista-compra tbody');
const carrinho = document.getElementById('carrinho');
const finalizarCompraBtn = document.getElementById('finalizar-pedido');
const cliente = document.getElementById('cliente');
const correio = document.getElementById('correio');

carregarEventos();

function carregarEventos(){
    document.addEventListener('DOMContentLoaded', compra.lerLocalStorageCompra());

//    carrinho.addEventListener('click', (e)=>{compra.excluirProduto(e)});
    $("#carrinho tbody tr td a.excluir-produto").click((e) => compra.excluirProduto(e));

    $("#carrinho tbody tr td input.qtda").on('keyup mouseup', (e) => compra.alterarQtda(e));
//    $("#carrinho tbody tr td input.qtda").click((e) => compra.alterarQtda(e));

    compra.calcularTotal();

    finalizarCompraBtn.addEventListener('click', (e) => {compra.finalizarCompra(e)});

}

