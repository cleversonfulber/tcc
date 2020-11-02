package br.edu.utfpr.tcc.model;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "promocoes")
public class Promocao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "preco_promocional", nullable = false)
	private Double precoPromocional;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name= "data_inicio", nullable = false, columnDefinition = "DATE")
	private LocalDate dataInicio;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name= "data_fim", nullable = false, columnDefinition = "DATE")
	private LocalDate dataFim;

	@Column(name = "produto_id")
	private Long produto;
}
