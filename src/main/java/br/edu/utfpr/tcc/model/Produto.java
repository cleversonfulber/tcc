package br.edu.utfpr.tcc.model;

import lombok.Data;

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

	@NotNull(message = "O nome é obrigatório.")
	@Size(max = 45, message = "O nome deve conter no máximo 60 caracteres.")
	@Column(name = "nome", nullable = false, unique = true, length = 45)
	private String nome;

	@NotNull(message = "O preço deve ser informado.")
	@Column(name = "valor", nullable = false)
	private Double valor;

	@Column(name = "imagem", length = 100, nullable = true)
	private String imagem;

	@NotNull(message = "Informe uma descrição.")
	@Size(max = 254, message = "A descrição deve conter no máximo 254 caracteres.")
	@Column(name = "descricao", nullable = false, length = 254)
	private String descricao;

	@NotNull(message = "Selecione um Tamanho!")
	@ManyToOne
	@JoinColumn(name = "tamanho_id", referencedColumnName = "id")
	private Tamanho tamanho;

	@NotNull(message = "Selecione uma Cor!")
	@ManyToOne
	@JoinColumn(name = "cor_id", referencedColumnName = "id")
	private Cor cor;

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

}
