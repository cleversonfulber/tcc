package br.edu.utfpr.tcc.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "enderecos")
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "usuario_id", referencedColumnName = "id")
	@JsonIgnore
	private Usuario usuario;

	@NotNull(message = "Preencha o campo estado!")
	@ManyToOne
	@JoinColumn(name = "cidade_id", referencedColumnName = "id")
	private Cidade cidade;

	@NotNull(message = "A descricão do endereço é obrigatório.")
	@Size(max = 15, message = "A descricaão do endereço deve conter no máximo 15 caracteres.")
	@Column(name = "descricao", nullable = false, length = 15)
	private String descricao;

	@NotNull(message = "O CEP é obrigatório.")
	@Column(name = "cep", nullable = false, length = 60)
	private String cep;

	@Column(name = "numero", nullable = false)
	private Integer numero;

	@NotNull(message = "O bairro do endereço é obrigatório.")
	@Size(max = 60, message = "O bairro do endereço deve conter no máximo 60 caracteres.")
	@Column(name = "bairro", nullable = false,  length = 60)
	private String bairro;

	@NotNull(message = "A rua do endereço é obrigatório.")
	@Size(max = 60, message = "A rua do endereço deve conter no máximo 60 caracteres.")
	@Column(name = "rua", nullable = false, length = 60)
	private String rua;


	@Size(max = 254, message = "A descrição do endereço deve conter no máximo 254 caracteres.")
	@Column(name = "complemento", length = 254)
	private String complemento;

}
