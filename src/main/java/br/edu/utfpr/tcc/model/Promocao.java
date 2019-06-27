package br.edu.utfpr.tcc.model;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "promocoes")
public class Promocao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "O Preço promocional deve ser informado.")
	@Column(name = "preco_promocional", nullable = false)
	private Double precoPromocional;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "A Data de inicio do anuncio deve ser informado.")
	@Column(name= "data_inicio", nullable = false, columnDefinition = "DATE")
	private LocalDate dataInicio;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "A Data do fim do anuncio deve ser informado.")
	@Column(name= "data_fim", nullable = false, columnDefinition = "DATE")
	private LocalDate dataFim;
}
