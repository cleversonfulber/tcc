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
//  var elemento = document.getElementById('valor');
//  var valor = elemento.value;
//
//  valor = valor + '';
//  valor = parseInt(valor.replace(/[\D]+/g,''));
//  valor = valor + '';
//  valor = valor.replace(/([0-9]{2})$/g, ",$1");
//
//  if (valor.length > 6) {
//    valor = valor.replace(/([0-9]{3}),([0-9]{2}$)/g, ".$1,$2");
//  }
//
//  elemento.value = valor;
//}