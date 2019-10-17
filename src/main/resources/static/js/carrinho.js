class Carrinho{
    //Adicionar o produto ao carrinho
    comprarProduto(e){
        e.preventDefault();
        if(e.target.classList.contains('adicionar-carrinho')){
            const produto = e.target.parentElement.parentElement;
            this.lerDadosProduto(produto);
        }
    }

    lerDadosProduto(produto){
        const infoProduto = {
            imagen : produto.querySelector('img').src,
            nome : produto.querySelector('h4').textContent,
            valor : produto.querySelector('p').textContent,
            id : produto.querySelector('a').getAtribute('data-id'),
            qtda : 1
        }
        this.inserirCarrinho(infoProduto);
    }

    inserirCarrinho(produto){
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>
                <img src="${producto.imagen}" width=100>
            </td>
            <td>${produto.nome}</td>
            <td>${produto.valor}</td>
            <td>
                <a href="#" class="apagar-producto fas fa-times-circle" data-id="${produto.id}></a>
            </td>
        `;
        listaProdutos.appendChild(row);
    }

//    eliminarProducto(e){
//        e.preventDefault();
//        let producto, productoID;
//        if(e.target.classList.contains('borra-producto')){
//            e.target.parentElement.parentElement.remove();
//            producto = e.target.parentElement.parentElement;
//            productoID = producto.querySelector('a').getAtribute('data-id');
//        }
//    }
//
//    vaciarCarrito(e){
//        e.preventDefault();
//        while(listaProductos.firstChild){
//            listaProductos.removeChild(listaProductos.firstChild);
//        }
//        return false;
//    }
}