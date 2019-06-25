package br.edu.utfpr.tcc.model;


import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "estados")
public class Estado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O nome do estado é obrigatório.")
	@Size(max = 60, message = "O nome do estado deve conter no máximo 60 caracteres.")
	@Column(name = "nome", nullable = false, unique = true, length = 60)
	private String nome;

	@NotBlank(message = "O sigla do estado é obrigatório.")
	@Size(max = 2, message = "O sigla do estado deve conter no máximo 2 caracteres.")
	@Column(name = "sigla", nullable = false, unique = true, length = 2)
	private String sigla;

}