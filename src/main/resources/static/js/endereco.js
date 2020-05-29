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
            cep : endereco.querySelector('.cep').textContent
        }
        this.salvarEnderecoLocalStorage(infoEndereco);
        Swal.fire({
          type: 'success',
          title: 'Endereco Selecionado!',
          timer: 1000,
          showConfirmButton: false
        })
        window.location = "../checagem";
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

}
