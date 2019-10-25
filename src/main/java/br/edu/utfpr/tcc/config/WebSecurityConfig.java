package br.edu.utfpr.tcc.config;

import br.edu.utfpr.tcc.model.service.UsuarioService;
import br.edu.utfpr.tcc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioService usuarioService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			
			.exceptionHandling().accessDeniedPage("/403")
			.and().formLogin().loginPage("/login")
			.defaultSuccessUrl("/")
			.failureUrl("/login?error=bad_credentials").permitAll()
			.and().logout()
			.logoutSuccessUrl("/login")
			.and().authorizeRequests()
				//.antMatchers("/categorias/**").hasAnyRole("USER", "ADMIN")
				//.antMatchers("/produtos/**").hasAnyRole("USER", "ADMIN")
				//.antMatchers("/tipos/**").hasAnyRole("ADMIN")
				.antMatchers("/home/**").permitAll()
				.antMatchers("/403/**").permitAll()
				.antMatchers("/**").permitAll()
				.antMatchers("/produto/**").permitAll()
				.antMatchers("/fragmentsinicial/**").permitAll()
				.antMatchers("/permissoes/**").permitAll()
				.antMatchers("/empresas/**").authenticated();
	}


	//cuidar para desbloquear as pastas
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/css/**")
			.antMatchers("/js/**")
			.antMatchers("/images/**")
			.antMatchers("/image/**")
			.antMatchers("/assets/**")
			.antMatchers("/webjars/**");
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return usuarioService;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService())
			.passwordEncoder(passwordEncoder());
	}
}









