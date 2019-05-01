package br.edu.utfpr.tcc.model;


import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "produtos")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Selecione uma categoria!")
	@ManyToOne
	@JoinColumn(name = "categoria_id", referencedColumnName = "id")
	private Categoria categoria;

	@NotNull(message = "Selecione uma marca!")
	@ManyToOne
	@JoinColumn(name = "marca_id", referencedColumnName = "id")
	private Marca marca;

	@NotNull(message = "Selecione um tipo!")
	@ManyToOne
	@JoinColumn(name = "tipo_id", referencedColumnName = "id")
	private Tipo tipo;

	@ManyToOne
	@JoinColumn(name = "promocao_id", referencedColumnName = "id")
	private Promocao promocao;

	@NotBlank(message = "O nome é obrigatório.")
	@Size(max = 60, message = "O nome deve conter no máximo 60 caracteres.")
	@Column(name = "nome", nullable = false, unique = true, length = 60)
	private String nome;

	@NotNull
	@NotBlank(message = "O preço deve ser informado.")
	@Column(name = "valor", nullable = false)
	private Double valor;

	@Column(name = "imagem")
	private String imagem;

	@NotBlank(message = "Informe uma descrição.")
	@Size(max = 254, message = "A descrição deve conter no máximo 254 caracteres.")
	@Column(name = "descricao", nullable = false, length = 254)
	private String descricao;

	@NotBlank(message = "Informe um tamanho.")
	@Size(max = 6, message = "O tamanho deve conter no máximo 6 caracteres.")
	@Column(name = "tamanho", nullable = false, length = 6)
	private String tamanho;

	@NotBlank(message = "Informe uma cor.")
	@Size(max = 20, message = "A cor deve conter no máximo 20 caracteres.")
	@Column(name = "cor", nullable = false,	 length = 20)
	private String cor;
}
