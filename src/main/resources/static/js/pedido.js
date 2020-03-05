const carro = new Carrinho();
const carrinho = document.getElementById('carrinho');
const produtos = document.getElementById('lista-produtos');
const listaProdutos = document.getElementById('lista-carrinho');
const esvaciarCarrinhoBtn = document.getElementById('esvaciar-carrinho');
const processarPedidoBtn = document.getElementById('processar-pedido')

cargarEventos();

function cargarEventos(){
    produtos.addEventListener('click', (e) => {carro.comprarProduto(e)});

    carrinho.addEventListener('click', (e)=>{carro.excluirProduto(e)});

    esvaciarCarrinhoBtn.addEventListener('click', (e)=>{carro.esvaciarCarrinho(e)});

    document.addEventListener('DOMContentLoaded', carro.lerLocalStorage());

    processarPedidoBtn.addEventListener('click', (e) => {carro.processarPedido(e)});
}