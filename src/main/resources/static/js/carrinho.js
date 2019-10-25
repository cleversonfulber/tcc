class Carrinho{
//    Adicionar o produto ao carrinho
    comprarProduto(e){
        e.preventDefault();
        if(e.target.classList.contains('.adicionar-carrinho')){
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
                <img th:src="${produto.imagem}" width=100>
            </td>
            <td>${produto.nome}</td>
            <td>${produto.valor}</td>
            <td>
                <a href="#" class="apagar-produto fas fa-times-circle" data-id="${produto.id}></a>
            </td>
        `;
        listaProdutos.appendChild(row);
    }

    eliminarProduto(e){
        e.preventDefault();
        let produto, produtoID;
        if(e.target.classList.contains('.apagar-produto')){
            e.target.parentElement.parentElement.remove();
            produto = e.target.parentElement.parentElement;
            produtoID = producto.querySelector('a').getAtribute('data-id');
        }
    }

    esvaciarCarrinho(e){
        e.preventDefault();
        while(listaProdutos.firstChild){
            listaProdutos.removeChild(listaProdutos.firstChild);
        }
        return false;
    }
}