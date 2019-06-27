package br.edu.utfpr.tcc.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "dados_usuarios")
public class DadosUsuarios {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "endereco_id", referencedColumnName = "id")
	private Endereco endereco;

	@Column(name = "telefone",  length = 60)
	private String telefone;

	@NotNull(message = "O celular  é obrigatório.")
	@Column(name = "celular", nullable = false,  length = 60)
	private String celular;

	@NotNull(message = "O CPF é obrigatório.")
	@Column(name = "cpf", nullable = false, length = 60)
	private String cpf;

	@NotNull
	@Column(name= "data_nascimento", nullable = false, columnDefinition = "DATE")
	private LocalDate dataNascimento;

	@Column(name = "sexo", nullable = false, length = 60)
	private String sexo;
}
