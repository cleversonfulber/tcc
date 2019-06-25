package br.edu.utfpr.tcc.repository;

import br.edu.utfpr.tcc.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {


}
