package br.edu.utfpr.tcc.repository;

import br.edu.utfpr.tcc.model.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {

    @Query(value = "select * from anuncios where usuario_id = ?1", nativeQuery = true)
    List<Anuncio> buscarAnunciosUsuario(Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update anuncios set validar = null where anuncios.id = ?1", nativeQuery = true)
    void validarAnuncio(Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update anuncios set validar = true where anuncios.id = ?1", nativeQuery = true)
    void editarValidacao(Long id);
}
