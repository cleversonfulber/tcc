package br.edu.utfpr.tcc.model;


import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "cidades")
public class Cidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O nome do cidade é obrigatório.")
	@Size(max = 60, message = "O nome do cidade deve conter no máximo 60 caracteres.")
	@Column(name = "nome", nullable = false, length = 60)
	private String nome;


	@NotNull(message = "Preencha o campo estado!")
	@ManyToOne
	@JoinColumn(name = "estado_id", referencedColumnName = "id")
	private Estado estado;
}
