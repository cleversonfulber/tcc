class Carrinho{
//    Adicionar o produto ao carrinho
    comprarProduto(e){
        e.preventDefault();
        if(e.target.classList.contains('adicionar-carrinho')){
            const produto = e.target.parentElement.parentElement;
            this.lerDadosProduto(produto);
        }
    }

    lerDadosProduto(produto){
        const infoProduto = {
            imagem : produto.querySelector('img').src,
            nome : produto.querySelector('h4').textContent,
            valor : produto.querySelector('p').textContent,
            id : produto.querySelector('h1').textContent,
            qtda : 1
        }
        let produtosLS;
        produtosLS = this.pegarProdutosLocalStorage();
        produtosLS.forEach(function(produtoLS){
           if(produtoLS.id == infoProduto.id){
                produtosLS = produtoLS.id;
           }
        });
        if(produtosLS == infoProduto.id){
            Swal.fire({
              type: 'info',
              title: 'Oops',
              text: 'Este produto já está no carrinho',
              timer: 1500,
              showConfirmButton: false
            })
        }else{
            this.inserirCarrinho(infoProduto);
            Swal.fire({
              type: 'success',
              title: 'Produto adicionado ao carrinho!',
              timer: 1000,
              showConfirmButton: false
            })
        }

    }

    inserirCarrinho(produto){
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>
                <img src="${produto.imagem}" width=100>
            </td>
            <td>${produto.nome}</td>
            <td>${produto.valor}</td>


        `;
        listaProdutos.appendChild(row);
        this.salvarProdutosLocalStorage(produto);
    }

    excluirProduto(e){
        e.preventDefault();
        let produto, produtoID;
        if(!e.target.classList.contains('lista-compra')){
            e.target.parentElement.parentElement.remove();
            produto = e.target.parentElement.parentElement;
            produtoID = produto.querySelector('h1').textContent;
        }
        this.excluirProdutoLocalStorage(produtoID);
        this.calcularTotal();
    }


    esvaciarCarrinho(e){
        e.preventDefault();
        while(listaProdutos.firstChild){
            listaProdutos.removeChild(listaProdutos.firstChild);
        }
        this.esvaciarLocalStorage();
        return false;
    }

    salvarProdutosLocalStorage(produto){
        let produtos;
        produtos = this.pegarProdutosLocalStorage();
        produtos.push(produto);
        localStorage.setItem('produtos', JSON.stringify(produtos));
    }

    pegarProdutosLocalStorage(){
        let produtoLS;

        if(localStorage.getItem('produtos') == null){
            produtoLS = [];
        }
        else{
            produtoLS = JSON.parse(localStorage.getItem('produtos'));
        }
        return produtoLS;
    }

    excluirProdutoLocalStorage(produtoID){
        let produtoLS;
        produtoLS = this.pegarProdutosLocalStorage();
        produtoLS.forEach(function(produtoLS, index){
            if(produtoLS.id == produtoID){
                localStorage.removeItem(produtoLS.id);

                  /*var tabela = document.querySelector('lista-compra');
                  tabela.deleteRow(e.target.parentNode.parentNode.rowIndex);*/
            }
        });

        localStorage.setItem('produtos', JSON.stringify(produtoLS));
    }

    lerLocalStorage(){
        let produtoLS;
        produtoLS = this.pegarProdutosLocalStorage();
        produtoLS.forEach(function(produto){
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>
                    <img src="${produto.imagem}" width=100>
                </td>
                <td>${produto.nome}</td>
                <td>${produto.valor}</td>

            `;
            listaProdutos.appendChild(row);
        });

    }

    esvaciarLocalStorage(){
        localStorage.clear();
    }

    processarPedido(e){
        e.preventDefault();
        location.href = "../carrinho";
    }

    lerLocalStorageCompra(){
        let produtoLS;
        produtoLS = this.pegarProdutosLocalStorage();
        produtoLS.forEach(function(produto){
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>
                    <img src="${produto.imagem}" width=100>
                </td>
                <td>${produto.nome}</td>
                <td>${produto.valor}</td>
                <td>
                    <input type="number" class="form-control qtda" min="1" value=${produto.qtda}>
                </td>
                <td>${produto.valor * produto.qtda}</td>

                <td>
                    <a href="" class="fas fa-times-circle"
                            style="font-size: 25px" ></a>

                </td>
                <td>    <h1 th:text="${produto.id}" ></h1>
                </td>

            `;
            listaCompra.appendChild(row);
        });

    }

    calcularTotal(){
        let produtosLS;
        let total = 0, subtotal = 0, igv =0;
        produtosLS = this.pegarProdutosLocalStorage();
        for(let i = 0; i < produtosLS.length; i++){
            let element = Number(produtosLS[i].valor * produtosLS[i].qtda);
            total = total + element;
        }
        igv = parseFloat(total * 0.18).toFixed(2);
        subtotal = parseFloat(total-igv).toFixed(2);

        document.getElementById('subtotal').innerHTML = "R$ " + subtotal;
        document.getElementById('igv').innerHTML = "R$ " + igv;
        document.getElementById('total').innerHTML = "R$ " + total.toFixed(2);
    }

    finalizarCompra(e){
        e.preventDefault();

        if(compra.pegarProdutosLocalStorage().length == 0){
            Swal.fire({
              type: 'error',
              title: 'Carrinho Vazio!',
              text: 'Adicione um item ao carrinho.',
              timer: 2000,
              showConfirmButton: false
            }).then(function(){
                window.location = "../";
            })
        }else if(cliente.value == null){
            window.location = "../login";
        }
    }
}
