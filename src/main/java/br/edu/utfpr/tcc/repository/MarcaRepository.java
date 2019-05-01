package br.edu.utfpr.tcc.repository;

import br.edu.utfpr.tcc.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
}
