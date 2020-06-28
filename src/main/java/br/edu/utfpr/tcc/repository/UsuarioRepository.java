package br.edu.utfpr.tcc.repository;

import br.edu.utfpr.tcc.model.Tamanho;
import br.edu.utfpr.tcc.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByUsername(String username);

	@Query(value = "select * from usuarios inner join anuncios on usuarios.id = anuncios.usuario_id inner join produtos on produtos.id = anuncios.produto_id where produtos.id = ?1", nativeQuery = true)
	List<Usuario> buscarUsuarioProduto(Long id);
}
