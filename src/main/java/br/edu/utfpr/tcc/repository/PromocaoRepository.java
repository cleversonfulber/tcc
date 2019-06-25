package br.edu.utfpr.tcc.repository;

import br.edu.utfpr.tcc.model.Promocao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromocaoRepository extends JpaRepository<Promocao, Long> {
}
