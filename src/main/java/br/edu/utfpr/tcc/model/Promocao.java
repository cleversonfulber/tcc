package br.edu.utfpr.tcc.model;


import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "promocaos")
public class Promocao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotBlank(message = "O Preço promocional deve ser informado.")
	@Column(name = "preco_promocional", nullable = false)
	private Double preco_promocional;

	@NotNull
	@NotBlank(message = "A Data de inicio da promoção deve ser informado.")
	@Column(name= "data_inicio", nullable = false, columnDefinition = "DATE")
	private LocalDate dataInicio;

	@NotNull
	@NotBlank(message = "A Data do fim da promoção deve ser informado.")
	@Column(name= "data_fim", nullable = false, columnDefinition = "DATE")
	private LocalDate dataFim;
}
