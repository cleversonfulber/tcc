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
//
//	@ManyToOne
//	@JoinColumn(name = "cidade_id", referencedColumnName = "id")
//	private Cidade cidade;

	@Column(name = "cidade", nullable = false, length = 64)
	private String cidade;

	@Column(name = "uf", nullable = false, length = 64)
	private String uf;

	@Column(name = "descricao", nullable = false, length = 15)
	private String descricao;

	@Column(name = "cep", nullable = false, length = 60)
	private String cep;

	@Column(name = "numero", nullable = false)
	private Integer numero;

	@Column(name = "bairro", nullable = false,  length = 60)
	private String bairro;

	@Column(name = "rua", nullable = false, length = 60)
	private String rua;

	@Column(name = "complemento", length = 30)
	private String complemento;

}
