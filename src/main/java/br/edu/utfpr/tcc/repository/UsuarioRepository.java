package br.edu.utfpr.tcc.repository;

import br.edu.utfpr.tcc.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByUsername(String username);

	@Query(value = "select * from usuarios inner join anuncios on usuarios.id = anuncios.usuario_id inner join produtos on produtos.anuncio_id = anuncios.id where produtos.id = ?1", nativeQuery = true)
	List<Usuario> buscarUsuarioProduto(Long id);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "with changed_hosts as ( update usuarios set validar = null where id = ?1 returning * ) update usuarios_permissoes set permissoes_id = 3 where usuario_id in (select id from changed_hosts)", nativeQuery = true)
	int validarParceiro(Long id);
}
