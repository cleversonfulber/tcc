package br.edu.utfpr.tcc.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "produtos")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", nullable = false, unique = true, length = 60)
	private String nome;

	@Column(name = "valor", nullable = false)
	private Double valor;

	@Column(name = "imagem", length = 100, nullable = true)
	private String imagem;

	@Column(name = "descri", nullable = false, length = 254)
	private String descri;

	@Column(name = "caracteristica", nullable = false, length = 254)
	private String caracteristica;

	@ManyToOne
	@JoinColumn(name = "cor_id", referencedColumnName = "id")
	private Cor cor;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Tamanho> tamanho;

	@ManyToOne
	@JoinColumn(name = "categoria_id", referencedColumnName = "id")
	private Categoria categoria;

	@ManyToOne
	@JoinColumn(name = "marca_id", referencedColumnName = "id")
	private Marca marca;

	@ManyToOne
	@JoinColumn(name = "tipo_id", referencedColumnName = "id")
	private Tipo tipo;

	@ManyToOne
	@JoinColumn(name = "promocao_id", referencedColumnName = "id")
	private Promocao promocao;
}
