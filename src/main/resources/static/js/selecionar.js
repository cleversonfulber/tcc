const endereco = new Endereco();
const enderecoSelecionado = document.getElementById('endereco');
const enderecos = $('#lista-enderecos .selecionar-endereco');
const listaEndereco = document.getElementById('lista-endereco');



subirEventos();

function subirEventos(){
    enderecos.on('click', (e) => endereco.pegarEndereco(e) );
//    $("#lista-enderecos tbody div tr td a.selecionar-endereco").click((e) => endereco.pegarEndereco(e));


}