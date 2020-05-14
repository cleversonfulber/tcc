package br.edu.utfpr.tcc.controller;

import br.edu.utfpr.tcc.model.Categoria;
import br.edu.utfpr.tcc.model.Produto;
import br.edu.utfpr.tcc.model.Usuario;
import br.edu.utfpr.tcc.repository.*;
import br.edu.utfpr.tcc.services.S3Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/produto")
@Controller
public class ProdController {

	@Autowired
	S3Services s3Services;

	@Autowired
	private ProdutoRepository produtoRepository;

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
	public ModelAndView produto() {
		ModelAndView modelAndView = new ModelAndView("produto");
		modelAndView.addObject("produtos", produtoRepository.findAll());
		modelAndView.addObject("categorias", categoriaRepository.findAll() );
		modelAndView.addObject("marcas", marcaRepository.findAll() );
		modelAndView.addObject("tipos", tipoRepository.findAll() );
		modelAndView.addObject("promocoes", promocaoRepository.findAll() );
		modelAndView.addObject("tamanhos", tamanhoRepository.findAll() );
		modelAndView.addObject("cores", corRepository.findAll() );
		modelAndView.addObject("produto", new Produto());

		return modelAndView;
	}

	@GetMapping("/{id}")
	public ModelAndView individual(@PathVariable Long id) {
		if(!produtoRepository.existsById(id)){
			ModelAndView modelAndView = new ModelAndView("erro/produto");
			modelAndView.addObject("produtos", produtoRepository.findAll());
			modelAndView.addObject("categorias", categoriaRepository.findAll() );
			modelAndView.addObject("marcas", marcaRepository.findAll() );
			modelAndView.addObject("tipos", tipoRepository.findAll() );
			modelAndView.addObject("promocoes", promocaoRepository.findAll() );
			modelAndView.addObject("tamanhos", tamanhoRepository.findAll() );
			modelAndView.addObject("cores", corRepository.findAll() );
			modelAndView.addObject("produto", new Produto());

			return modelAndView;
		}
		ModelAndView modelAndView = new ModelAndView("individual");

		modelAndView.addObject("produtos", produtoRepository.getOne(id));
		modelAndView.addObject("produtos2", produtoRepository.findAll());
		modelAndView.addObject("categorias", categoriaRepository.findAll() );
		modelAndView.addObject("marcas", marcaRepository.findAll() );
		modelAndView.addObject("tipos", tipoRepository.findAll() );
		modelAndView.addObject("promocoes", promocaoRepository.findAll() );
		modelAndView.addObject("tamanhos", tamanhoRepository.findAll() );
		modelAndView.addObject("cores", corRepository.findAll() );
		modelAndView.addObject("produto", new Produto());

		return modelAndView;
	}

//	@GetMapping("categoria/{id}")
//	public ModelAndView categoria(@PathVariable Long id) {
//
//		Categoria categoria = (Categoria) categoriaRepository.getOne(id);
//
//		ModelAndView modelAndView = new ModelAndView("produto");
//		modelAndView.addObject("produtos", produtoRepository.findAll();
//		modelAndView.addObject("categorias", categoriaRepository.getOne(id));
//		modelAndView.addObject("marcas", marcaRepository.findAll() );
//		modelAndView.addObject("tipos", tipoRepository.findAll() );
//		modelAndView.addObject("promocoes", promocaoRepository.findAll() );
//		modelAndView.addObject("tamanhos", tamanhoRepository.findAll() );
//		modelAndView.addObject("cores", corRepository.findAll() );
//		modelAndView.addObject("produto", new Produto());
//
//		return modelAndView;
//	}
//
//	@GetMapping("marca/{id}")
//	public ModelAndView marca(@PathVariable Long id) {
//
//		ModelAndView modelAndView = new ModelAndView("produto");
//		modelAndView.addObject("produtos", produtoRepository.findAll());
//		modelAndView.addObject("categorias", categoriaRepository.findAll() );
//		modelAndView.addObject("marcas", marcaRepository.getOne(id));
//		modelAndView.addObject("tipos", tipoRepository.findAll() );
//		modelAndView.addObject("promocoes", promocaoRepository.findAll() );
//		modelAndView.addObject("tamanhos", tamanhoRepository.findAll() );
//		modelAndView.addObject("cores", corRepository.findAll() );
//		modelAndView.addObject("produto", new Produto());
//
//		return modelAndView;
//	}
//
//	@GetMapping("tipo/{id}")
//	public ModelAndView tipo(@PathVariable Long id) {
//
//		ModelAndView modelAndView = new ModelAndView("produto");
//		modelAndView.addObject("produtos", produtoRepository.findAll());
//		modelAndView.addObject("categorias", categoriaRepository.findAll() );
//		modelAndView.addObject("marcas", marcaRepository.findAll() );
//		modelAndView.addObject("tipos", tipoRepository.getOne(id));
//		modelAndView.addObject("promocoes", promocaoRepository.findAll() );
//		modelAndView.addObject("tamanhos", tamanhoRepository.findAll() );
//		modelAndView.addObject("cores", corRepository.findAll() );
//		modelAndView.addObject("produto", new Produto());
//
//		return modelAndView;
//	}
}
