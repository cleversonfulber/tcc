package br.edu.utfpr.tcc.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Usuario implements Serializable, UserDetails {
	private static final long serialVersionUID = 1L;
	private static final BCryptPasswordEncoder bCrypt = 
			new BCryptPasswordEncoder(10);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 15, nullable = false)
	private String nome;

	@Column(length = 100, nullable = false, unique = true)
	private String username ;
	
	@Column(length = 512, nullable = false)
	private String password;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Permissao> permissoes;

	@OneToMany(mappedBy = "usuario", targetEntity = Endereco.class)
	private Set<Endereco> enderecos;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<>();
		list.addAll(permissoes);
		return list;
	}

	@Column(name = "telefone",  length = 60)
	private String telefone;

	@NotNull(message = "O celular  é obrigatório.")
	@Column(name = "celular", nullable = false,  length = 60)
	private String celular;

	@NotNull(message = "O Sobrenome é obrigatório.")
	@Column(name = "sobrenome", nullable = false, length = 15)
	private String sobrenome;

	@NotNull(message = "O CPF é obrigatório.")
	@Column(name = "cpf_cnpj", nullable = false, length = 60)
	private String cpfCnpj;

	@NotNull(message = "Preencha o campo data de nascimento!")
	@Column(name = "data_nascimento", nullable = false)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;

	@Column(name = "genero", nullable = false, length = 60)
	private String genero;

	public String getEncodedPassword(String password) {
		return bCrypt.encode(password);
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
