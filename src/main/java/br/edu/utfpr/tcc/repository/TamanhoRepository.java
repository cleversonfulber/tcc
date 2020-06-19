package br.edu.utfpr.tcc.repository;

import br.edu.utfpr.tcc.model.Tamanho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TamanhoRepository extends JpaRepository<Tamanho, Long> {

    @Query(value = "select * from tamanho inner JOIN produtos_tamanho on produtos_tamanho.tamanho_id = tamanho.id where produto_id = ?1", nativeQuery = true)
    List<Tamanho> buscarTamanhoProduto(Long id);
}
