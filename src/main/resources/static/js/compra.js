const compra = new Carrinho();
const listaCompra = document.getElementById('lista-compra');
const carrinho = document.getElementById('carrinho');
const finalizarCompraBtn = document.getElementById('finalizar-pedido');
const cliente = document.getElementById('cliente');
const correio = document.getElementById('correio');

carregarEventos();

function carregarEventos(){
    document.addEventListener('DOMContentLoaded', compra.lerLocalStorageCompra());

    carrinho.addEventListener('click', (e)=> {compra.excluirProduto(e)});

    compra.calcularTotal();

    finalizarCompraBtn.addEventListener('click', (e) => {compra.finalizarCompra(e)});

}

