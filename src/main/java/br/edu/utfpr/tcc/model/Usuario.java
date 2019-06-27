package br.edu.utfpr.tcc.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "dados_usuarios_id", referencedColumnName = "id")
	private DadosUsuarios dados_usuarios;

	@Column(name = "tipo", nullable = false)
	private Integer tipo;

	@NotNull(message = "O Nome é obrigatório.")
	@Column(name = "nome", nullable = false, length = 60)
	private String nome;

	@NotNull(message = "O email é obrigatório.")
	@Size(max = 60, message = "O email deve conter no máximo 60 caracteres.")
	@Column(name = "email", nullable = false, unique = true, length = 60)
	private String email;

	@NotNull(message = "A senha é obrigatório.")
	@Size(max = 25,min = 6, message = "A senha deve ter entre 6 e 25 caracteres.")
	@Column(name = "senha", nullable = false, length = 25)
	private String senha;
}
