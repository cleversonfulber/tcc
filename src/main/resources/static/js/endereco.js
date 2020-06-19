class Endereco{
//    Selecionar endereço para finalizar compra
    pegarEndereco(e){
        e.preventDefault();
        if(e.target.classList.contains('selecionar-endereco')){

            const endereco = e.target.parentElement.parentElement;
            this.lerDadosEndereco(endereco);
        }
    }

    lerDadosEndereco(endereco){
        const infoEndereco = {
            nome : endereco.querySelector('h1').textContent,
            rua : endereco.querySelector('.rua').textContent,
            numero : endereco.querySelector('.numero').textContent,
            complemento : endereco.querySelector('.complemento').textContent,
            bairro: endereco.querySelector('.bairro').textContent,
            cidade : endereco.querySelector('.cidade').textContent,
            cep : endereco.querySelector('.cep').textContent,
            id : endereco.querySelector('.id_end').textContent
        }
        this.salvarEnderecoLocalStorage(infoEndereco);
        Swal.fire({
          type: 'success',
          title: 'Endereco Selecionado!',
          timer: 1000,
          showConfirmButton: false
        })
        window.location.replace("../checagem");
    }

//    selecionarEndereco(endereco){
//        const row = document.createElement('tr');
//        row.innerHTML = `
//            <th scope="col">
//                <p>${endereco.nome}</p>
//                <span>${endereco.rua}</span>,
//                <span>${endereco.numero}</span>,
//                <span>${endereco.complemento}</span>
//                <h5>${endereco.bairro}</h5>
//                <h5>${endereco.cidade}</h5>
//                <span>CEP: </span> <span>${endereco.cep}</span>
//            </th>
//        `;
//        this.salvarEnderecoLocalStorage(endereco);
//    }

    salvarEnderecoLocalStorage(endereco){
        let enderecos;
        enderecos = [];
        enderecos.push(endereco);
        localStorage.setItem('enderecos', JSON.stringify(enderecos));
    }

    esvaciarEnderecoStorage(){
        localStorage.removeItem('enderecos');
    }

    // mostrar o endereco selecionado

    lerLocalStorageEndereco(){
        let enderecoLS;
        enderecoLS = this.pegarEnderecosLocalStorage();
        enderecoLS.forEach(function(endereco){
            const row = document.createElement('tr');
            row.innerHTML = `
                <th scope="col">
                    <p>${endereco.nome}</p>
                    <span>${endereco.rua}</span>,
                    <span>${endereco.numero}</span>,
                    <span>${endereco.complemento}</span>
                    <h5>${endereco.bairro}</h5>
                    <h5>${endereco.cidade}</h5>
                    <span>CEP: </span> <span>${endereco.cep}</span>
                </th>
            `;
            enderecoChecar.appendChild(row);
        });

    }

    pegarEnderecosLocalStorage(){
        let enderecoLS;

        if(localStorage.getItem('enderecos') == null){
            enderecoLS = [];
        }
        else{
            enderecoLS = JSON.parse(localStorage.getItem('enderecos'));
        }
        return enderecoLS;
    }

    limpa_formulário_cep() {
        //Limpa valores do formulário de cep.
        document.getElementById('rua').value=("");
        document.getElementById('bairro').value=("");
        document.getElementById('cidade').value=("");
        document.getElementById('uf').value=("");
        document.getElementById('ibge').value=("");
    }

    meu_callback(conteudo) {
        if (!("erro" in conteudo)) {
            //Atualiza os campos com os valores.
            document.getElementById('rua').value=(conteudo.logradouro);
            document.getElementById('bairro').value=(conteudo.bairro);
            document.getElementById('cidade').value=(conteudo.localidade);
            document.getElementById('uf').value=(conteudo.uf);
            document.getElementById('ibge').value=(conteudo.ibge);
        } //end if.
        else {
            //CEP não Encontrado.
            limpa_formulário_cep();
            alert("CEP não encontrado.");
        }
    }

    pesquisacep(valor) {

        //Nova variável "cep" somente com dígitos.
        var cep = valor.replace(/\D/g, '');

        //Verifica se campo cep possui valor informado.
        if (cep != "") {

        //Expressão regular para validar o CEP.
        var validacep = /^[0-9]{8}$/;

        //Valida o formato do CEP.
        if(validacep.test(cep)) {
                //Preenche os campos com "..." enquanto consulta webservice.
                document.getElementById('rua').value="...";
                document.getElementById('bairro').value="...";
                document.getElementById('cidade').value="...";
                document.getElementById('uf').value="...";
                document.getElementById('ibge').value="...";

                //Cria um elemento javascript.
                var script = document.createElement('script');

                //Sincroniza com o callback.
                script.src = 'https://viacep.com.br/ws/'+ cep + '/json/?callback=meu_callback';

                //Insere script no documento e carrega o conteúdo.
                document.body.appendChild(script);

            } //end if.
            else {
                //cep é inválido.
                limpa_formulário_cep();
                alert("Formato de CEP inválido.");
            }
        } //end if.
        else {
            //cep sem valor, limpa formulário.
            limpa_formulário_cep();
        }
    }


}

