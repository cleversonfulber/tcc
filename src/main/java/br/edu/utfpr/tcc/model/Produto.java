package br.edu.utfpr.tcc.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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

	@NotNull(message = "Informe uma descrição.")
	@Size(max = 254, message = "A descrição deve conter no máximo 254 caracteres.")
	@Column(name = "caracteristica", nullable = false, length = 254)
	private String caracteristica;

	@NotNull(message = "Selecione uma Cor!")
	@ManyToOne
	@JoinColumn(name = "cor_id", referencedColumnName = "id")
	private Cor cor;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Tamanho> tamanho;

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
