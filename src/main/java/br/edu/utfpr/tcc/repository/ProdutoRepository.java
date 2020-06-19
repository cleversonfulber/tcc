package br.edu.utfpr.tcc.repository;

import java.util.List;

import br.edu.utfpr.tcc.model.Produto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface ProdutoRepository extends JpaRepository<Produto, Long> {

//    @Query(value = "select * from produtos where nome ilike %?1%", nativeQuery = true)
//    List<Produto> buscarProdutoNome(String nome, Pageable pageable);
    List<Produto> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

//    @Query(value = "select * from produtos where categoria_id = ?1", nativeQuery = true)
//    List<Produto> buscarProdutoumFiltro( String nome, Long id, Pageable pageable);


    @Query(value = "select * from produtos where categoria_id = ?1", nativeQuery = true)
    List<Produto> buscarProdutoCategoria(Long id, Pageable pageable);

    @Query(value = "select * from produtos where marca_id = ?1", nativeQuery = true)
    List<Produto> buscarProdutoMarca(Long id, Pageable pageable);

    @Query(value = "select * from produtos where tipo_id = ?1", nativeQuery = true)
    List<Produto> buscarProdutoTipo(Long id, Pageable pageable);

    @Query(value = "select * from produtos where cor_id = ?1", nativeQuery = true)
    List<Produto> buscarProdutoCor(Long id, Pageable pageable);

    @Query(value = "select * from produtos inner JOIN produtos_tamanho on produtos_tamanho.produto_id = produtos.id where tamanho_id = ?1", nativeQuery = true)
    List<Produto> buscarProdutotamanho(Long id, Pageable pageable);
}
