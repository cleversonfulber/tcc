const carro = new Carrinho();
const carrinho = document.getElementById('#carrinho');
const produtos = document.getElementById('.lista-produtos');
const listaProdutos = document.querySelector('#lista-carrinho tbody');
//const esvaciarCarrinhoBtn = document.getElementById('esvaciar-carrinho');

carregarEventos();

function carregarEventos(){
    produtos.addEventListener('click', (e)=>{carro.comprarProduto(e)});
//    document.getElementById('lista-produtos').addEventListener('click', (e)=>{carro.comprarProduto(e)});

//    carrinho.addEventListener('click', (e)=>{carro.eliminarProduto(e)});
//
//    esvaciarCarrinhoBtn.addEventListener('click', (e)=>{carro.esvaciarCarrinho(e)});
}