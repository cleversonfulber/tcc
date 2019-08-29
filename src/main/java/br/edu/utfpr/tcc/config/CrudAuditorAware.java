package br.edu.utfpr.tcc.config;

import br.edu.utfpr.tcc.model.Usuario;
import br.edu.utfpr.tcc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class CrudAuditorAware implements AuditorAware<Usuario> {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Optional<Usuario> getCurrentAuditor() {
		Authentication authentication =  SecurityContextHolder
												.getContext()
												.getAuthentication();
		return Optional.ofNullable(usuarioRepository.findByUsername(authentication.getName()));
	}
}
