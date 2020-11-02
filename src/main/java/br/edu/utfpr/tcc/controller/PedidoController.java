package br.edu.utfpr.tcc.controller;

import br.edu.utfpr.tcc.model.*;
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
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	S3Services s3Services;

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private PedidoRepository pedidoRepository;
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
	public ModelAndView pedido() {
		Usuario usuario = (Usuario) usuarioService.loadUserByUsername(
				SecurityContextHolder.getContext().getAuthentication().getName()
		);

		ModelAndView modelAndView = new ModelAndView("pedido");
		modelAndView.addObject("pedidos", pedidoRepository.listarPedidos(usuario.getId()));
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
	public ModelAndView novo(Pedido pedido) {
		ModelAndView modelAndView = new ModelAndView("pedido/lista");
		if (pedido != null) {
			modelAndView.addObject(pedido);
		}else {
			modelAndView.addObject(new Categoria());
		}
		return modelAndView;
	}

	@PostMapping("/ajax")
	public ResponseEntity<?> salvar(@Valid Pedido pedido, BindingResult result,
									RedirectAttributes attributes) {
		if ( result.hasErrors() ) {
			return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		pedidoRepository.save(pedido);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
