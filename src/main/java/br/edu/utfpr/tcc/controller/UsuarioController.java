package br.edu.utfpr.tcc.controller;

import br.edu.utfpr.tcc.model.Usuario;
import br.edu.utfpr.tcc.repository.PermissaoRepository;
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
@RequestMapping("usuario")
public class UsuarioController{

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PermissaoRepository permissaoRepository;

	@GetMapping
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("cadastrar");
		modelAndView.addObject("permissoes", permissaoRepository.findAll());
		modelAndView.addObject("usuario", new Usuario());

		return modelAndView;
	}

	@PostMapping()
	public ResponseEntity<?> saveAjax(@Valid Usuario entity,
									  BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}

		entity.setPassword(
				entity.getEncodedPassword(entity.getPassword()));
		usuarioRepository.save(entity);

		return new ResponseEntity<>(HttpStatus.OK);
	}

}