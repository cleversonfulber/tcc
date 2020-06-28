class Carrinho{
//    Adicionar o produto ao carrinho


    comprarProduto(e){
        e.preventDefault();
        if(e.target.classList.contains('adicionar-carrinho')){
            const produto = e.target.parentElement.parentElement;
            this.lerDadosProduto(produto);
            this.calcularQtde();
        }
    }

    verProduto(e){
        e.preventDefault();
        if(e.target.classList.contains('ver-produto')){
            const produto = e.target.parentElement.parentElement;
            this.lerProduto(produto);

        }
    }

    irCarrinho(e){
        e.preventDefault();
        if(e.target.classList.contains('processar-pedido')){
            window.location = "/carrinho";
        }
    }

    lerProduto(produto){
        const infoProduto = {
            id : produto.querySelector('h1').textContent,

        }
        window.location = "/produto/" + infoProduto.id;

    }

    lerDadosProduto(produto){
        const infoProduto = {
            imagem : produto.querySelector('img').src,
            nome : produto.querySelector('h4').textContent,
            valor : produto.querySelector('p').textContent,
            id : produto.querySelector('h1').textContent,
            sub: produto.querySelector('p').textContent,
            tamanho: produto.querySelector('#tamanho').value,
            qtda : 1,
            loja: produto.querySelector('.loja').textContent

        }
        let produtosLS, produtosSL;

        produtosLS = this.pegarProdutosLocalStorage();

        produtosLS.forEach(function(produtoLS){
           if(produtoLS.id == infoProduto.id){
                produtosSL = produtoLS.id;
           }
        });
        if(infoProduto.tamanho == ""){
            Swal.fire({
                type: 'info',
                title: 'Oops',
                text: 'Selecione um tamanho',
                timer: 1500,
                showConfirmButton: false
            })
        }else{
            if(produtosSL == infoProduto.id){
                    Swal.fire({
                    type: 'info',
                    title: 'Oops',
                    text: 'Este produto já está no carrinho',
                    timer: 1500,
                    showConfirmButton: false
                })
            }else if(produtosLS.length == 4){
                Swal.fire({
                    type: 'info',
                    title: 'Oops',
                    text: 'Você só pode adicionar 4 produtos ao carrinho.',
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

        const produtoID = e.target.parentElement.parentElement
            .parentElement.querySelector('h1').innerText;

        if(e.target.parentElement.parentElement.parentElement.localName == 'tr'){
            e.target.parentElement.parentElement.parentElement.remove();
        }
        else {
            e.target.parentElement.parentElement.parentElement.parentElement.remove();
        }

        this.excluirProdutoLocalStorage(produtoID);
        this.calcularTotal();

        if((this.pegarProdutosLocalStorage() || []).length === 0)
        {
           $("tr.sem-registros").show();
            $("tr.com-registros").hide();
        }
        else{
            $("tr.com-registros").show();
            $("tr.sem-registros").hide();
        }
    }

    alterarQtda(e){
        e.preventDefault();

        const produtoQtda = e.target.parentElement.parentElement
                    .querySelector('td input').value;

        const produtoID = e.target.parentElement.parentElement
            .querySelector('td h1').innerText;

        let produtosLS = this.pegarProdutosLocalStorage();

        produtosLS.forEach(function(produtosLS){
            if(produtosLS.id == produtoID){
                produtosLS.qtda = produtoQtda;
                produtosLS.sub = produtoQtda*produtosLS.valor;
                produtosLS.sub = parseFloat(produtosLS.sub.toFixed(2));
            }
        });

        localStorage.setItem('produtos', JSON.stringify(produtosLS));

        this.calcularTotal();
//        window.location = "/carrinho";
        document.location.reload(true);
    }

    esvaciarCarrinho(e){
        e.preventDefault();
        while(listaProdutos.firstChild){
            listaProdutos.removeChild(listaProdutos.firstChild);

        }
        row.innerHTML = `
                                <tr class="sem-registros">
                                    <td colspan="3">
                                        <h2>O carrinho está vazio...</h2>
                                    </td>
                                </tr>

                            `;
                    listaProdutos.appendChild(row);
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
        localStorage.setItem('produtos', JSON.stringify(produtoLS.filter(x => x.id != produtoID)));
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
                <td>R$ ${produto.nome}</td>
                <td>R$ ${produto.valor.replace('.',',')}</td>
            `;
            listaProdutos.appendChild(row);
        });

        if(produtoLS.length === 0) {
            $("tr.sem-registros").show();

        }
        else{
            $("tr.sem-registros").hide();
        }
        this.calcularQtde();
    }

    esvaciarLocalStorage(){
        localStorage.clear();
        this.calcularQtde();
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
                <td><p class="nome-produto">${produto.nome} - Tamanho: ${produto.tamanho}</p>
                <p class="text-loja">Vendido e entregue por: ${produto.loja}</p></td>
                <td>R$ ${produto.valor}</td>
                <td>
                    <input type="number" autocomplete="off" class="form-control qtda" min="1" max="6" value=${produto.qtda}>
                </td>

                <td>R$ ${produto.sub}</td>
                <td>
                    <h1 id="produto" style="display: none;">${produto.id}</h1>
                    <a href=""  class="excluir-produto fas fa-times-circle" style="font-size: 25px" >
                    </a>
                </td>
            `;
            listaCompra.appendChild(row);
        });

        if(produtoLS.length === 0) {
            $("tr.sem-registros").show();
            $("tr.com-registros").hide();

        }
        else{
            $("tr.com-registros").show();
            $("tr.sem-registros").hide();
        }

    }

    lerLocalStorageChecar(){
        let produtoLS;
        produtoLS = this.pegarProdutosLocalStorage();
        produtoLS.forEach(function(produto){
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>
                    <img src="${produto.imagem}" width=50>
                </td>
                <td>${produto.nome} <p>Tam: ${produto.tamanho} - Loja: ${produto.loja}</p></td>
                <td>${produto.qtda}</td>
                <td>R$ ${produto.sub}</td>
            `;
            listaChecar.appendChild(row);
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

        document.getElementById('subtotal').innerHTML = "R$ " + subtotal.replace('.',',');
        document.getElementById('igv').innerHTML = "R$ " + igv.replace('.',',');
        document.getElementById('total').innerHTML = "R$ " + total.toFixed(2).replace('.',',');
    }

    calcularQtde(){

        let produtosLS;
        let qtde = 0;
        produtosLS = this.pegarProdutosLocalStorage();
        for(let i = 0; i < produtosLS.length; i++){
//            let element = Number(produtosLS[i].qtda);
//            qtde = qtde + element;
//          achei mais interesante contar os itens do carrinho do que a qtda total
            qtde++;
        }

        document.getElementById('qtde').innerHTML = qtde;
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
        }else{
            Swal.fire({
                title: 'Selecione um endereço!',
                showConfirmButton: true
            }).then(function(){
                window.location = "../endereco";
            })
        }
    }
}
