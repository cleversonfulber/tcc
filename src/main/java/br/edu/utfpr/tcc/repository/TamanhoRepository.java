package br.edu.utfpr.tcc.repository;

import br.edu.utfpr.tcc.model.Tamanho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TamanhoRepository extends JpaRepository<Tamanho, Long> {

    @Query(value = "select * from tamanho inner JOIN produtos_tamanhos on produtos_tamanhos.tamanhos_id = tamanho.id where produto_id = ?1", nativeQuery = true)
    List<Tamanho> buscarTamanhoProduto(Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from produtos_tamanhos  where produtos_tamanhos.produto_id = ?1", nativeQuery = true)
    void excluirTamanhoProduto(Long id);
}
