// var table =  '#minhaTabela'
// $('#maxRows').on('charge', function(){
//     $('.pagination').html('')
//     var trnum = 0
//     var maxRows = parseInt($(this).val())
//     var totalRows = $(table+' tbody tr').length
//     $(table+' tr:gt(0)').each(function(){
//         trnum++
//         if(trnum > maxRows){
//             $(this).hide()
//         }
//         if(trnum <= maxRows){
//             $(this).show()
//         }
//     })
//     if(totalRows > maxRows){
//         var pagenum = Math.ceil(totalRows/maxRows)
//         for (var i=1;i<=pagenum;){
//             $('.pagination').append('<li data-page="'+'">\<span>'+ i++ +'<span class="sr-only"(current)</span>' +
//                 '</span>\</li>').show()
//         }
//     }
//     $('.pagination li:first-child').addClass('active')
//     $('.pagination li').on('click',function(){
//         var pageNum = $(this).attr('data-page')
//         var trIndex = 0;
//         $('.pagination li').removeClass('active')
//         $(this).addClass('active')
//         $(table+' tr:gt(0)').each(function () {
//             trIndex++
//             if(trIndex > (maxRows*pageNum) || trIndex <= ((maxRows*pageNum)-maxRows)){
//                 $(this).hide()
//             } else{
//                 $(this).show()
//             }
//         })
//     })
// })
// $(function () {
//     $('table tr:eq(0)').prepend('<th>ID</th>')
//     var id = 0;
//     $('table tr:gt(0)').each(function () {
//         id++
//         $(this).prepend('<td>'+id+'</td>')
//     })
// })


// var qnt_result_pg = 50; //quantidade de registro por página
// var pagina = 1; //página inicial
// $(document).ready(function () {
//     listar_usuario(pagina, qnt_result_pg); //Chamar a função para listar os registros
// });
//
// function listar_usuario(pagina, qnt_result_pg){
//     var dados = {
//         pagina: pagina,
//         qnt_result_pg: qnt_result_pg
//     }
//     $.post('listar_usuario.php', dados , function(retorna){
//         //Subtitui o valor no seletor id="conteudo"
//         $("#conteudo").html(retorna);
//     });
// }