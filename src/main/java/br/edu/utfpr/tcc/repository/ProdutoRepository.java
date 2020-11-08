package br.edu.utfpr.tcc.repository;

import java.util.List;

import br.edu.utfpr.tcc.model.Produto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface ProdutoRepository extends JpaRepository<Produto, Long> {

//    Admin

    @Query(value = "select * from produtos inner join anuncios on anuncios.id = produtos.anuncio_id where usuario_id = ?1", nativeQuery = true)
    List<Produto> buscarProdutoUsuario(Long id);

    @Query(value = "select * from produtos inner join anuncios on anuncios.id = produtos.anuncio_id inner join usuarios on usuarios.id = anuncios.usuario_id where anuncios.id = ?1 and usuarios.id = ?2", nativeQuery = true)
    List<Produto> buscarProdutoAnuncio(Long id1, Long id2);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update anuncios set validar = null from produtos where produtos.anuncio_id = anuncios.id and produtos.id = ?1", nativeQuery = true)
    void validarAnuncio(Long id);

    @Query(value = "select * from produtos inner join anuncios on anuncios.id = produtos.anuncio_id where anuncios.id = ?1", nativeQuery = true)
    List<Produto> listarProdutosAnuncio(Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update produtos set promocao_id = ?1 where produtos.id = ?2", nativeQuery = true)
    void salvarPromocaoProduto(Long promocaoId, Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update produtos set promocao_id = null where produtos.promocao_id = ?1", nativeQuery = true)
    void excluirPromocaoProduto(Long id);


//    Cliente

    @Query(value = "select * from produtos inner JOIN anuncios on anuncios.id = produtos.anuncio_id where produtos.nome ilike %?1% and data_inicio <= CURRENT_DATE and data_fim >= CURRENT_DATE and validar is null", nativeQuery = true)
    List<Produto> buscarProdutoNome(String nome, Pageable pageable);

    @Query(value = "select * from produtos inner JOIN anuncios on anuncios.id = produtos.anuncio_id where data_inicio <= CURRENT_DATE and data_fim >= CURRENT_DATE and validar is null order by produtos.nome", nativeQuery = true)
    List<Produto> buscarProduto( Pageable pageable);

    @Query(value = "select * from produtos inner JOIN anuncios on anuncios.id = produtos.anuncio_id where data_inicio <= CURRENT_DATE and data_fim >= CURRENT_DATE and validar is null order by random()", nativeQuery = true)
    List<Produto> buscarProdutoAleatorio( Pageable pageable);

    @Query(value = "select * from produtos as p inner JOIN anuncios as a on a.id = p.anuncio_id inner JOIN promocoes as r on r.id = p.promocao_id where a.data_inicio <= CURRENT_DATE and a.data_fim >= CURRENT_DATE and r.data_inicio <= CURRENT_DATE and r.data_fim >= CURRENT_DATE and validar is null", nativeQuery = true)
    List<Produto> buscarProdutoPromocao( Pageable pageable);

    @Query(value = "select * from produtos inner JOIN anuncios on anuncios.id = produtos.anuncio_id where categoria_id = ?1 and data_inicio <= CURRENT_DATE and data_fim >= CURRENT_DATE and validar is null", nativeQuery = true)
    List<Produto> buscarProdutoCategoria(Long id, Pageable pageable);

    @Query(value = "select * from produtos inner JOIN anuncios on anuncios.id = produtos.anuncio_id where marca_id = ?1 and data_inicio <= CURRENT_DATE and data_fim >= CURRENT_DATE and validar is null", nativeQuery = true)
    List<Produto> buscarProdutoMarca(Long id, Pageable pageable);

    @Query(value = "select * from produtos inner JOIN anuncios on anuncios.id = produtos.anuncio_id where tipo_id = ?1 and data_inicio <= CURRENT_DATE and data_fim >= CURRENT_DATE and validar is null", nativeQuery = true)
    List<Produto> buscarProdutoTipo(Long id, Pageable pageable);

    @Query(value = "select * from produtos inner JOIN anuncios on anuncios.id = produtos.anuncio_id where cor_id = ?1 and data_inicio <= CURRENT_DATE and data_fim >= CURRENT_DATE and validar is null", nativeQuery = true)
    List<Produto> buscarProdutoCor(Long id, Pageable pageable);

    @Query(value = "select * from produtos inner JOIN anuncios on anuncios.id = produtos.anuncio_id inner JOIN produtos_tamanhos on produtos_tamanhos.produto_id = produtos.id where tamanhos_id = ?1 and data_inicio <= CURRENT_DATE and data_fim >= CURRENT_DATE and validar is null", nativeQuery = true)
    List<Produto> buscarProdutotamanho(Long id, Pageable pageable);



}
