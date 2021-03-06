const carro = new Carrinho();
const carrinho = document.getElementById('carrinho');
const produtos = document.getElementById('lista-produtos');
const listaProdutos = document.getElementById('lista-carrinho');
const esvaciarCarrinhoBtn = document.getElementById('esvaciar-carrinho');
const processarPedidoBtn = document.getElementById('processar-pedido');

cargarEventos();


function cargarEventos(){
    produtos.addEventListener('click', (e) => {carro.comprarProduto(e)});

    produtos.addEventListener('click', (e) => {carro.verProduto(e)});

    produtos.addEventListener('click', (e) => {carro.irCarrinho(e)});

    $("#carrinho tbody tr td a.excluir-produto").click((e) => carro.excluirProduto(e));

    esvaciarCarrinhoBtn.addEventListener('click', (e)=>{carro.esvaciarCarrinho(e)});

    document.addEventListener('DOMContentLoaded', carro.lerLocalStorage());

    processarPedidoBtn.addEventListener('click', (e) => {carro.processarPedido(e)});
}

//function formatarMoeda() {
//  var elemento = document.getElementById('#valor');
//  var valor = elemento;
//  elemento = valor.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'});;
//
//
//
//
//  elemento.value = valor;
//}
//
//$(document).ready(function(){
//	number.toLocaleString('pt-BR',{style:'currency', currency: 'BRL'});
// })

