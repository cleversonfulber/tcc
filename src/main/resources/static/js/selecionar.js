const endereco = new Endereco();
const enderecoSelecionado = document.getElementById('endereco');
const enderecos = $('#lista-enderecos .selecionar-endereco');
const listaEndereco = document.getElementById('lista-endereco');



subirEventos();
selecionado();

function subirEventos(){
    enderecos.on('click', (e) => endereco.pegarEndereco(e) );
//    $("#lista-enderecos tbody div tr td a.selecionar-endereco").click((e) => endereco.pegarEndereco(e));


}

function selecionado(){

    Swal.fire({
        title: 'Selecione um endereço!',
        showConfirmButton: true
    })
//    var anterior = document.referrer;
//
//    if(anterior == 'http://localhost:9000/carrinho' || 'http://localhost:9000/carrinho'){
//
//        $("th.recolhe-endereco").show();
//        $("td.recolhe-endereco").show();
//        $("div.recolhe-endereco").show();
//
//    }else{
//        $("th.recolhe-endereco").hide();
//        $("td.recolhe-endereco").hide();
//        $("div.recolhe-endereco").hide();
//    }
}