
const compra = new Carrinho();
const listaCompra = document.querySelector('#lista-compra tbody');
const carrinho = document.getElementById('carrinho');
const freteBtn = document.getElementById('calcular-frete');
const cliente = document.getElementById('cliente');
const correio = document.getElementById('correio');
const finalizarCompraBtn = document.getElementById('finalizar-pedido');

carregarEventos();

function carregarEventos(){

    document.addEventListener('DOMContentLoaded', compra.lerLocalStorageCompra());

    $("#carrinho tbody tr td a.excluir-produto").click((e) => compra.excluirProduto(e));

    $("#carrinho tbody tr td input.qtda").on('keyup click', (e) => compra.alterarQtda(e));

    compra.calcularTotal();

    finalizarCompraBtn.addEventListener('click', (e) => {compra.finalizarCompra(e)});

}

function meu_callback(conteudo) {
   if (!("erro" in conteudo)) {

       const ORIGEM = '85560000';  // Verificar CEP de origem
       const PAC = '04510';
       const SEDEX = '04014';
       const CAIXA = 1;

       let request = new XMLHttpRequest();

       let url = new URL('https://cors-anywhere.herokuapp.com/http://ws.correios.com.br/calculador/CalcPrecoPrazo.aspx');

       url.searchParams.append('sCepOrigem', ORIGEM);

       let cep = document.getElementById('cep').value;

       // Adicionar tratamentos de cep inserido e cep válido
       cep = cep.replace(/[.-]/g, '');

       url.searchParams.append('sCepDestino', cep);
       url.searchParams.append('nCdServico', PAC);
       url.searchParams.append('nVlPeso', 1);  // Verificar se necessário alterar peso
       url.searchParams.append('nCdFormato', CAIXA);
       url.searchParams.append('nVlComprimento', 30);  // Verificar se necessário alterar comprimento
       url.searchParams.append('nVlAltura', 10);  // Verificar se necessário alterar altura
       url.searchParams.append('nVlLargura', 20);  // Verificar se necessário alterar largura
       url.searchParams.append('nVlDiametro', 0);
       url.searchParams.append('sCdMaoPropria', 'n');
       url.searchParams.append('nVlValorDeclarado', 100);
       url.searchParams.append('sCdAvisoRecebimento', 'n');
       url.searchParams.append('StrRetorno', 'xml');
       request.open('GET', url);
       request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
       request.onreadystatechange = function() {
           if(this.readyState == 4 && this.status == 200) {
               let parser = new DOMParser();
               let xml = parser.parseFromString(this.response, 'text/xml');

               let valor = xml.querySelector('Valor');
               let prazo = xml.querySelector('PrazoEntrega');

               if(valor) {
                   valor = valor.textContent;
               }

               if(prazo) {
                   prazo = prazo.textContent;
               }

               document.getElementById('igv').innerHTML = valor;
               document.getElementById('vlrFrete').innerHTML = valor;
               document.getElementById('frete').innerHTML = 'Frete: R$ ' + valor;
               document.getElementById('prazo').innerHTML = 'Prazo: ' + prazo + ' dias.';


               compra.calcularTotal();
           }
       }
       request.send();

    } //end if.
    else {
        //CEP não Encontrado.
        alert("CEP não encontrado.");
    }
}

function calcularFrete() {

   let cep = document.getElementById('cep');

   // Adicionar tratamentos de cep inserido e cep válido
   cep = cep.value.replace(/[.-]/g, '');

   if(cep == ""){
   // CEP em branco
   }else{
       //Expressão regular para validar o CEP.
       var validacep = /^[0-9]{8}$/;

       //Valida o formato do CEP.
       if(validacep.test(cep)) {

           //Cria um elemento javascript.
           var script = document.createElement('script');

           //Sincroniza com o callback.
           script.src = 'https://viacep.com.br/ws/'+ cep + '/json/?callback=meu_callback';

           //Insere script no documento e carrega o conteúdo.
           document.body.appendChild(script);
       } //fim if.
       else {
           //cep é inválido.
           alert("CEP inválido.");
       }
   }
}


