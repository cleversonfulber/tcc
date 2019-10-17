const carro = new Carrinho();
const carrinho = document.getElementById('.carrinho');
const produtos = document.getElementById('.lista-produtos');
const listaProdutos = document.querySelector('#lista-carrinho tbody');
//const vaciarCarritoBtn = document.getElementById('vaciar-carrito');

carregarEventos();

function carregarEventos(){
    produtos.addEventListener('click', (e)=>{carro.comprarProduto(e)});

    //carrito.addEventListener('click', (e)=>{carro.eliminarProducto(e)});

    //vaciarCarritoBtn.addEventListener('click', (e)=>{carro.vaciarCarrito(e)});
}