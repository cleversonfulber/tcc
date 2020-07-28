package br.edu.utfpr.tcc.repository;

import br.edu.utfpr.tcc.model.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {

    @Query(value = "select * from anuncios where usuario_id = ?1", nativeQuery = true)
    List<Anuncio> buscarAnunciosUsuario(Long id);
}
