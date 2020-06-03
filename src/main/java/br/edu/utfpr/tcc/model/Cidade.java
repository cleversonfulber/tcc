package br.edu.utfpr.tcc.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cidades")
public class Cidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", nullable = false, length = 60)
	private String nome;

	@ManyToOne
	@JoinColumn(name = "estado_id", referencedColumnName = "id")
	private Estado estado;
}
