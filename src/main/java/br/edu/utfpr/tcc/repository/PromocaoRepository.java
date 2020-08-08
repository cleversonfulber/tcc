package br.edu.utfpr.tcc.repository;

import br.edu.utfpr.tcc.model.Produto;
import br.edu.utfpr.tcc.model.Promocao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PromocaoRepository extends JpaRepository<Promocao, Long> {

    //Admin
    @Query(value = "select * from promocoes inner join produtos on produtos.promocao_id = promocoes.id where produtos.id = ?1", nativeQuery = true)
    List<Promocao> buscarProdutoPromocao(Long id);
}
