package br.edu.utfpr.tcc.controller;

import br.edu.utfpr.tcc.model.Produto;
import br.edu.utfpr.tcc.repository.*;
import br.edu.utfpr.tcc.services.S3Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/produto")
@Controller
public class ProdController {

	@Autowired
	S3Services s3Services;

	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private MarcaRepository marcaRepository;
	@Autowired
	private TipoRepository tipoRepository;
	@Autowired
	private PromocaoRepository promocaoRepository;
	@Autowired
	private TamanhoRepository tamanhoRepository;
	@Autowired
	private CorRepository corRepository;

	@GetMapping("")
	public ModelAndView produto(Pageable pageable) {
		ModelAndView modelAndView = new ModelAndView("produto");
		modelAndView.addObject("produtos", produtoRepository.buscarProduto((Pageable) pageable));
		modelAndView.addObject("categorias", categoriaRepository.findAll() );
		modelAndView.addObject("marcas", marcaRepository.findAll() );
		modelAndView.addObject("tipos", tipoRepository.findAll() );
		modelAndView.addObject("cores", corRepository.findAll() );
		modelAndView.addObject("tamanhos", tamanhoRepository.findAll() );
		modelAndView.addObject("promocoes", promocaoRepository.buscarData() );
		modelAndView.addObject("promocoes2", promocaoRepository.buscarDataFora() );
		modelAndView.addObject("produto", new Produto());

		return modelAndView;
	}

	@GetMapping("/{id}")
	public ModelAndView individual(@PathVariable Long id, Pageable pageable) {
		if(!produtoRepository.existsById(id)){
			ModelAndView modelAndView = new ModelAndView("erro/produto");
			modelAndView.addObject("produtos", produtoRepository.buscarProdutoPromocao(pageable));
			modelAndView.addObject("categorias", categoriaRepository.findAll() );
			modelAndView.addObject("marcas", marcaRepository.findAll() );
			modelAndView.addObject("tipos", tipoRepository.findAll() );
			modelAndView.addObject("promocoes", promocaoRepository.buscarData() );
			modelAndView.addObject("promocoes2", promocaoRepository.buscarDataFora() );
			modelAndView.addObject("produto", new Produto());

			return modelAndView;
		}
		ModelAndView modelAndView = new ModelAndView("individual");

		modelAndView.addObject("produtos", produtoRepository.getOne(id));
		modelAndView.addObject("usuarios2", usuarioRepository.buscarUsuarioProduto(id));
		modelAndView.addObject("produtos2", produtoRepository.buscarProdutoPromocao(pageable));
		modelAndView.addObject("categorias", categoriaRepository.findAll() );
		modelAndView.addObject("marcas", marcaRepository.findAll() );
		modelAndView.addObject("tipos", tipoRepository.findAll() );
		modelAndView.addObject("cores", corRepository.findAll() );
		modelAndView.addObject("tamanhos", tamanhoRepository.findAll() );
		modelAndView.addObject("promocoes", promocaoRepository.buscarData() );
		modelAndView.addObject("promocoes2", promocaoRepository.buscarDataFora() );
		modelAndView.addObject("produto_tamanhos", tamanhoRepository.buscarTamanhoProduto(id) );
		modelAndView.addObject("produto", new Produto());

		return modelAndView;
	}

	@PostMapping("/buscarproduto")
	public ModelAndView pesquisar(@RequestParam("nome") String nome, Pageable pageable){
		ModelAndView modelAndView = new ModelAndView("produto");
		modelAndView.addObject("produtos", produtoRepository.buscarProdutoNome(nome,pageable));
		modelAndView.addObject("categorias", categoriaRepository.findAll() );
		modelAndView.addObject("marcas", marcaRepository.findAll() );
		modelAndView.addObject("tipos", tipoRepository.findAll() );
		modelAndView.addObject("cores", corRepository.findAll() );
		modelAndView.addObject("promocoes", promocaoRepository.buscarData() );
		modelAndView.addObject("promocoes2", promocaoRepository.buscarDataFora() );
		modelAndView.addObject("tamanhos", tamanhoRepository.findAll() );
		modelAndView.addObject("produto", new Produto());

		return modelAndView;
	}
//
//	@GetMapping("filtro")
//	public ModelAndView umFiltro(@RequestParam("classe")  String nome,
//								 @RequestParam("id") Long id,
//								 Pageable pageable){
//
//		ModelAndView modelAndView = new ModelAndView("produto");
//		modelAndView.addObject("produtos", produtoRepository.buscarProdutoumFiltro( nome, id, pageable));
//		modelAndView.addObject("categorias", categoriaRepository.findAll() );
//		modelAndView.addObject("marcas", marcaRepository.findAll() );
//		modelAndView.addObject("tipos", tipoRepository.findAll() );
//		modelAndView.addObject("cores", corRepository.findAll() );
//		modelAndView.addObject("tamanhos", tamanhoRepository.findAll() );
//
//		modelAndView.addObject("produto", new Produto());
//
//		return modelAndView;
//	}


	@GetMapping("/categoria/{id}")
	public ModelAndView categoria(@PathVariable Long id, Pageable pageable){
		ModelAndView modelAndView = new ModelAndView("produto");
		modelAndView.addObject("produtos", produtoRepository.buscarProdutoCategoria(id, pageable));
		modelAndView.addObject("categorias", categoriaRepository.findAll() );
		modelAndView.addObject("marcas", marcaRepository.findAll() );
		modelAndView.addObject("tipos", tipoRepository.findAll() );
		modelAndView.addObject("cores", corRepository.findAll() );
		modelAndView.addObject("tamanhos", tamanhoRepository.findAll() );
		modelAndView.addObject("promocoes", promocaoRepository.buscarData() );
		modelAndView.addObject("promocoes2", promocaoRepository.buscarDataFora() );
		modelAndView.addObject("categorias2", categoriaRepository.getOne(id) );

		modelAndView.addObject("produto", new Produto());

		return modelAndView;
	}

	@GetMapping("/marca/{id}")
	public ModelAndView marca(@PathVariable Long id, Pageable pageable){
		ModelAndView modelAndView = new ModelAndView("produto");
		modelAndView.addObject("produtos", produtoRepository.buscarProdutoMarca(id, pageable));
		modelAndView.addObject("categorias", categoriaRepository.findAll() );
		modelAndView.addObject("marcas", marcaRepository.findAll() );
		modelAndView.addObject("tipos", tipoRepository.findAll() );
		modelAndView.addObject("cores", corRepository.findAll() );
		modelAndView.addObject("tamanhos", tamanhoRepository.findAll() );
		modelAndView.addObject("promocoes", promocaoRepository.buscarData() );
		modelAndView.addObject("promocoes2", promocaoRepository.buscarDataFora() );
		modelAndView.addObject("marcas2", marcaRepository.getOne(id) );

		modelAndView.addObject("produto", new Produto());

		return modelAndView;
	}

	@GetMapping("/tipo/{id}")
	public ModelAndView tipo(@PathVariable Long id, Pageable pageable){
		ModelAndView modelAndView = new ModelAndView("produto");
		modelAndView.addObject("produtos", produtoRepository.buscarProdutoTipo(id, pageable));
		modelAndView.addObject("categorias", categoriaRepository.findAll() );
		modelAndView.addObject("marcas", marcaRepository.findAll() );
		modelAndView.addObject("tipos", tipoRepository.findAll() );
		modelAndView.addObject("cores", corRepository.findAll() );
		modelAndView.addObject("tamanhos", tamanhoRepository.findAll() );
		modelAndView.addObject("promocoes", promocaoRepository.buscarData() );
		modelAndView.addObject("promocoes2", promocaoRepository.buscarDataFora() );
		modelAndView.addObject("tipos2", tipoRepository.getOne(id) );

		modelAndView.addObject("produto", new Produto());

		return modelAndView;
	}

	@GetMapping("/cor/{id}")
	public ModelAndView cor(@PathVariable Long id, Pageable pageable){
		ModelAndView modelAndView = new ModelAndView("produto");
		modelAndView.addObject("produtos", produtoRepository.buscarProdutoCor(id, pageable));
		modelAndView.addObject("categorias", categoriaRepository.findAll() );
		modelAndView.addObject("marcas", marcaRepository.findAll() );
		modelAndView.addObject("tipos", tipoRepository.findAll() );
		modelAndView.addObject("cores", corRepository.findAll() );
		modelAndView.addObject("tamanhos", tamanhoRepository.findAll() );
		modelAndView.addObject("promocoes", promocaoRepository.buscarData() );
		modelAndView.addObject("promocoes2", promocaoRepository.buscarDataFora() );
		modelAndView.addObject("cores2", corRepository.getOne(id) );

		modelAndView.addObject("produto", new Produto());

		return modelAndView;
	}

	@GetMapping("/tamanho/{id}")
	public ModelAndView tamanho(@PathVariable Long id, Pageable pageable){
		ModelAndView modelAndView = new ModelAndView("produto");
		modelAndView.addObject("produtos", produtoRepository.buscarProdutotamanho(id, pageable));
		modelAndView.addObject("categorias", categoriaRepository.findAll() );
		modelAndView.addObject("marcas", marcaRepository.findAll() );
		modelAndView.addObject("tipos", tipoRepository.findAll() );
		modelAndView.addObject("cores", corRepository.findAll() );
		modelAndView.addObject("tamanhos", tamanhoRepository.findAll() );
		modelAndView.addObject("promocoes", promocaoRepository.buscarData() );
		modelAndView.addObject("promocoes2", promocaoRepository.buscarDataFora() );
		modelAndView.addObject("tamanhos2", tamanhoRepository.getOne(id) );

		modelAndView.addObject("produto", new Produto());

		return modelAndView;
	}

}
