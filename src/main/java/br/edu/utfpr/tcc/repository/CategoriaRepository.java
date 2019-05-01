package br.edu.utfpr.tcc.repository;

import br.edu.utfpr.tcc.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
