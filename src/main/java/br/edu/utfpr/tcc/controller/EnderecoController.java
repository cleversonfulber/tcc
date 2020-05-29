package br.edu.utfpr.tcc.controller;

import br.edu.utfpr.tcc.model.Endereco;
import br.edu.utfpr.tcc.model.Produto;
import br.edu.utfpr.tcc.model.Usuario;
import br.edu.utfpr.tcc.model.service.UsuarioService;
import br.edu.utfpr.tcc.repository.*;
import br.edu.utfpr.tcc.services.S3Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	S3Services s3Services;

	@Autowired
	private UsuarioService usuarioService;
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
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("")
	public ModelAndView endereco() {
		Usuario usuario = (Usuario) usuarioService.loadUserByUsername(
				SecurityContextHolder.getContext().getAuthentication().getName()
		);

		ModelAndView modelAndView = new ModelAndView("endereco");
		modelAndView.addObject("produtos", produtoRepository.findAll());
		modelAndView.addObject("categorias", categoriaRepository.findAll());
		modelAndView.addObject("marcas", marcaRepository.findAll() );
		modelAndView.addObject("tipos", tipoRepository.findAll() );
		modelAndView.addObject("promocoes", promocaoRepository.findAll());
		modelAndView.addObject("tamanhos", tamanhoRepository.findAll());
		modelAndView.addObject("cores", corRepository.findAll());
		modelAndView.addObject("cidades", cidadeRepository.findAll() );
		modelAndView.addObject("enderecos", usuario.getEnderecos());
		modelAndView.addObject("usuarios", usuarioRepository.findAll() );
		modelAndView.addObject("endereco", new Produto());

		return modelAndView;
	}

	@GetMapping({"novo"})
	public ModelAndView novo(Endereco endereco) {
		ModelAndView modelAndView = new ModelAndView("endereco/lista");

		if (endereco != null) {
			modelAndView.addObject(endereco);
		}else {
			modelAndView.addObject(new Endereco());
		}
		return modelAndView;
	}

	@PostMapping("ajax")
	public ResponseEntity<?> salvar(@Valid Endereco endereco, BindingResult result,
									RedirectAttributes attributes) {
		Usuario usuario = (Usuario) usuarioService.loadUserByUsername(
				SecurityContextHolder.getContext().getAuthentication().getName()
		);

		endereco.setUsuario(usuario);

		if ( result.hasErrors() ) {
			return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		enderecoRepository.save(endereco);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("ajax/{id}")
	@ResponseBody
	public Endereco editar(@PathVariable Long id) {
		return enderecoRepository.findById(id).orElse(null);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id){
		try {
			enderecoRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
