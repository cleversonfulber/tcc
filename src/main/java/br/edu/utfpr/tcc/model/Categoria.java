package br.edu.utfpr.tcc.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "categorias")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", nullable = false, unique = true, length = 60)
	private String nome;
}
