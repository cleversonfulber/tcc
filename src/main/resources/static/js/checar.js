const compra = new Carrinho();
const listaChecar = document.querySelector('#lista-checar tbody');
const carrinho = document.getElementById('carrinho');
const cliente = document.getElementById('cliente');
const correio = document.getElementById('correio');

const endereco = new Endereco();
const enderecoChecar = document.querySelector('#residencia tbody');

carregarEventos();

function carregarEventos(){

    document.addEventListener('DOMContentLoaded', compra.lerLocalStorageChecar());
    $("#carrinho tbody tr td a.excluir-produto").click((e) => compra.excluirProduto(e));
    $("#carrinho tbody tr td input.qtda").on('keyup mouseup', (e) => compra.alterarQtda(e));
    compra.calcularTotal();
    document.addEventListener('DOMContentLoaded', endereco.lerLocalStorageEndereco());

    this.calculaValorCEP();

}

function enviarEmail(){

    var email = document.getElementById("email").innerHTML
    var endereco = document.getElementById("usuario").innerHTML
    var produto = document.getElementById("carrinho").innerHTML

    var corpo = endereco + '<br>' + produto

    Email.send({
        SecureToken : "46c1d9cf-d925-4e92-99d3-ea6d3509bc49", //email e senha criptografados
        To : email, // só funciona com gmail
        From : "cleversonfulber@alunos.utfpr.edu.br",
        Subject : "Recebemos seu pedido",
        Body : corpo
    }).then(
        Swal.fire({
            type: 'success',
            title: 'Pedido Finalizado com sucesso!',
            timer: 2500,
            showConfirmButton: false
        })

    );

    localStorage.clear();
    redirecionar();
//    prepareDatabase("nn","Deu Erro!");
}

function redirecionar() {
  setTimeout(function() {
  		window.location.replace("../");
  }, 3000);
}

//function prepareDatabase(ready, error) {
//  return openDatabase('tcc', '1.0', 'Teste de inserir', 5*1024*1024, function (db) {
//    db.changeVersion('', '1.0', function (t) {
//      t.executeSql('insert into pedido( id, valor, endereco_id, produto_id) values (3,1,1,1)');
//    }, error);
//  });
//}

function finalizar(){

//    document.getElementById("valor").innerHTML = document.getElementById("total").innerHTML
//
//
//    document.getElementById("endereco").innerHTML = 1
//    document.getElementById("produtos").innerHTML = 1

    //var dados = JSON.stringify({"endereco_id":"1","produto_id":"1","valor": "112"});


//        type: "POST",
//        url: "/checagem/ajax",
//        dataType: 'JSON',
//        data: dados,

    var hoje = new Date();

    function formatDate(data) {
        return (data.getDate());
    }

    var enderecoP = document.getElementById("usuario").innerHTML
    var produtoP = document.getElementById("carrinho").innerHTML
    var totalP = document.getElementById("total").innerText
    var dataP = formatDate(hoje)



    $.ajax({
        type: "POST",
        url: "/pedido/ajax",
        data: JSON.stringify({ dataPedido : dataP,
                                endereco : enderecoP}),
        contentType: "application/json",

//        data: {
//          endereco : "enderecoP",
//          produto : "produtoP",
//          valor : "totalP",
//          dataPedido : dataP
//        },
        success: function(){
            enviarEmail();
            localStorage.clear();
            location.href = "../";
        },
        error: function(){
            Swal.fire('Erro!', 'Falha ao salvar o registro!', 'error');
        }
    });//Fim Ajax
}


//    Calcular valor cep

function calculaValorCEP() {
       $("div.loader").show();
       let cep = document.getElementById('cep').innerHTML;

       // Adicionar tratamentos de cep inserido e cep válido
       cep = cep.replace(/[.-]/g, '');

       if(cep == ""){
       // CEP em branco
       $("div.loader").hide();
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
               $("div.loader").hide();
           }
       }
    }

function   meu_callback(conteudo) {
       if (!("erro" in conteudo)) {

           const ORIGEM = '85560000';  // Verificar CEP de origem
           const PAC = '04510';
           const SEDEX = '04014';
           const CAIXA = 1;

           let request = new XMLHttpRequest();

           let url = new URL('https://cors-anywhere.herokuapp.com/http://ws.correios.com.br/calculador/CalcPrecoPrazo.aspx');

           url.searchParams.append('sCepOrigem', ORIGEM);

           let cep = document.getElementById('cep').innerHTML;

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
                   document.getElementById('prazos').innerHTML = prazo + ' dias';


                   compra.calcularTotal();
                   $("div.loader").hide();
               }
           }
           request.send();

        } //end if.
        else {
            //CEP não Encontrado.
            alert("CEP não encontrado.");
            $("div.loader").hide();
        }
    }
