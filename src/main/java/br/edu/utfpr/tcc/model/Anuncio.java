package br.edu.utfpr.tcc.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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

	@Column(name = "nome", nullable = false, length = 60)
	private String nome;

	@ManyToOne
	@JoinColumn(name = "usuario_id", referencedColumnName = "id")
	@JsonIgnore
	private Usuario usuario;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name= "data_inicio", nullable = false, columnDefinition = "DATE")
	private LocalDate dataInicio;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name= "data_fim", nullable = false, columnDefinition = "DATE")
	private LocalDate dataFim;

	private Boolean validar;
}
