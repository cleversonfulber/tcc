package br.edu.utfpr.tcc.controller;

import br.edu.utfpr.tcc.model.Endereco;
import br.edu.utfpr.tcc.repository.EnderecoRepository;
import br.edu.utfpr.tcc.repository.CidadeRepository;
import br.edu.utfpr.tcc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/enderecos")
public class EnderecoController {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("endereco/lista");
		modelAndView.addObject("enderecos", enderecoRepository.findAll());
		modelAndView.addObject("cidades", cidadeRepository.findAll() );
		modelAndView.addObject("usuarios", usuarioRepository.findAll() );
		modelAndView.addObject("endereco", new Endereco());

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
