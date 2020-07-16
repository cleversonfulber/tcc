package br.edu.utfpr.tcc.repository;

import br.edu.utfpr.tcc.model.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {
}
