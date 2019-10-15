package br.edu.utfpr.tcc.controller;

import br.edu.utfpr.tcc.model.Produto;
import br.edu.utfpr.tcc.repository.*;
import br.edu.utfpr.tcc.services.S3Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndividualController {

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

	@GetMapping("/individual")
	public ModelAndView individual() {
		ModelAndView modelAndView = new ModelAndView("individual");
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
}
