package br.edu.utfpr.tcc.controller;

import br.edu.utfpr.tcc.model.Anuncio;
import br.edu.utfpr.tcc.model.Produto;
import br.edu.utfpr.tcc.model.Usuario;
import br.edu.utfpr.tcc.model.service.UsuarioService;
import br.edu.utfpr.tcc.repository.AnuncioRepository;
import br.edu.utfpr.tcc.repository.ProdutoRepository;
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
import java.util.List;

@Controller
@RequestMapping("/anuncios")
public class AnuncioController {

	@Autowired
	private AnuncioRepository anuncioRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ProdutoRepository produtosRepository;

	@GetMapping
	public ModelAndView listar() {
		Usuario usuario = (Usuario) usuarioService.loadUserByUsername(
				SecurityContextHolder.getContext().getAuthentication().getName()
		);

		ModelAndView modelAndView = new ModelAndView("anuncio/lista");
		modelAndView.addObject("anuncios", anuncioRepository.buscarAnunciosUsuario(usuario.getId()));
		modelAndView.addObject("anuncio", new Anuncio());

		return modelAndView;
	}


	@GetMapping("lista/{id}")
	public ModelAndView verLista(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("anuncio/listaprodutos");
		modelAndView.addObject("produtos", produtosRepository.listarProdutosAnuncio(id));
		return modelAndView;
	}

	@GetMapping("valida")
	public ModelAndView validar() {
		ModelAndView modelAndView = new ModelAndView("anuncio/valida");
		modelAndView.addObject("anuncios", anuncioRepository.findAll());
		modelAndView.addObject("anuncio", new Anuncio());

		return modelAndView;
	}

	@GetMapping("valida/{id}")
	@ResponseBody
	public void salvarvalidacao(@PathVariable Long id) {
		anuncioRepository.validarAnuncio(id);
	}

	@GetMapping("editarValidacao/{id}")
	@ResponseBody
	public void editarValidacao(@PathVariable Long id) {
		anuncioRepository.editarValidacao(id);
	}

	@GetMapping({"novo"})
	public ModelAndView novo(Anuncio anuncio) {
		ModelAndView modelAndView = new ModelAndView("anuncio/lista");
		if (anuncio != null) {
			modelAndView.addObject(anuncio);
		}else {
			modelAndView.addObject(new Anuncio());
		}
		return modelAndView;
	}

	@PostMapping("ajax")
	public ResponseEntity<?> salvar(@Valid Anuncio anuncio, BindingResult result,
							   RedirectAttributes attributes) {
		Usuario usuario = (Usuario) usuarioService.loadUserByUsername(
				SecurityContextHolder.getContext().getAuthentication().getName()
		);

		anuncio.setUsuario(usuario);

		if ( result.hasErrors() ) {
			return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		anuncioRepository.save(anuncio);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("ajax/{id}")
	@ResponseBody
	public Anuncio editar(@PathVariable Long id) {
		return anuncioRepository.findById(id).orElse(null);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id){
		try {
			anuncioRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
