package br.edu.utfpr.tcc.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "anuncios")
public class Anuncio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Selecione um produto!")
	@ManyToOne
	@JoinColumn(name = "produto_id", referencedColumnName = "id")
	private Produto produto;

	@NotNull(message = "Selecione um usuario!")
	@ManyToOne
	@JoinColumn(name = "usuario_id", referencedColumnName = "id")
	private Usuario usuario;

	@NotNull(message = "A quantidade deve ser informada.")
	@Column(name = "qtde", nullable = false)
	private Integer qtde;

	@NotNull(message = "A Data de inicio do anuncio deve ser informado.")
	@Column(name= "data_inicio", nullable = false, columnDefinition = "DATE")
	private LocalDate dataInicio;

	@NotNull(message = "A Data do fim do anuncio deve ser informado.")
	@Column(name= "data_fim", nullable = false, columnDefinition = "DATE")
	private LocalDate dataFim;
}
