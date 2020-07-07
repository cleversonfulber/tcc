package br.edu.utfpr.tcc.repository;

import java.util.List;

import br.edu.utfpr.tcc.model.Produto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface ProdutoRepository extends JpaRepository<Produto, Long> {

//    Admin

    @Query(value = " select  *, cast(to_char(valor, 'L9G999G990D99') as varchar(20)) from produtos inner join anuncios on anuncios.produto_id = produtos.id where usuario_id = ?1", nativeQuery = true)
    List<Produto> buscarProdutoUsuario(Long id);

//    Cliente

    @Query(value = "select * from produtos inner JOIN anuncios on anuncios.produto_id = produtos.id where nome ilike %?1% and data_inicio <= CURRENT_DATE and data_fim >= CURRENT_DATE", nativeQuery = true)
    List<Produto> buscarProdutoNome(String nome, Pageable pageable);

    @Query(value = "select * from produtos inner JOIN anuncios on anuncios.produto_id = produtos.id where data_inicio <= CURRENT_DATE and data_fim >= CURRENT_DATE", nativeQuery = true)
    List<Produto> buscarProduto( Pageable pageable);

    @Query(value = "select * from produtos inner JOIN anuncios on anuncios.produto_id = produtos.id where data_inicio <= CURRENT_DATE and data_fim >= CURRENT_DATE order by random()", nativeQuery = true)
    List<Produto> buscarProdutoAleatorio( Pageable pageable);

    @Query(value = "select * from produtos inner JOIN anuncios on anuncios.produto_id = produtos.id where data_inicio <= CURRENT_DATE and data_fim >= CURRENT_DATE and promocao_id is not null", nativeQuery = true)
    List<Produto> buscarProdutoPromocao( Pageable pageable);

    @Query(value = "select * from produtos inner JOIN anuncios on anuncios.produto_id = produtos.id where categoria_id = ?1 and data_inicio <= CURRENT_DATE and data_fim >= CURRENT_DATE", nativeQuery = true)
    List<Produto> buscarProdutoCategoria(Long id, Pageable pageable);

    @Query(value = "select * from produtos inner JOIN anuncios on anuncios.produto_id = produtos.id where marca_id = ?1 and data_inicio <= CURRENT_DATE and data_fim >= CURRENT_DATE", nativeQuery = true)
    List<Produto> buscarProdutoMarca(Long id, Pageable pageable);

    @Query(value = "select * from produtos inner JOIN anuncios on anuncios.produto_id = produtos.id where tipo_id = ?1 and data_inicio <= CURRENT_DATE and data_fim >= CURRENT_DATE", nativeQuery = true)
    List<Produto> buscarProdutoTipo(Long id, Pageable pageable);

    @Query(value = "select * from produtos inner JOIN anuncios on anuncios.produto_id = produtos.id where cor_id = ?1 and data_inicio <= CURRENT_DATE and data_fim >= CURRENT_DATE", nativeQuery = true)
    List<Produto> buscarProdutoCor(Long id, Pageable pageable);

    @Query(value = "select * from produtos inner JOIN anuncios on anuncios.produto_id = produtos.id inner JOIN produtos_tamanho on produtos_tamanho.produto_id = produtos.id where tamanho_id = ?1 and data_inicio <= CURRENT_DATE and data_fim >= CURRENT_DATE", nativeQuery = true)
    List<Produto> buscarProdutotamanho(Long id, Pageable pageable);
}
