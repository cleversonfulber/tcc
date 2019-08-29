package br.edu.utfpr.tcc.controller;

import br.edu.utfpr.tcc.model.Permissao;
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
@RequestMapping("usuarios")
public class UsuarioController{

	
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PermissaoRepository permissaoRepository;

	@GetMapping
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("usuario/lista");
		modelAndView.addObject("permissoes", permissaoRepository.findAll());
		modelAndView.addObject("usuario", new Usuario());

		return modelAndView;
	}

	@GetMapping({"novo"})
	public ModelAndView novo(Usuario entity) {
		ModelAndView modelAndView = new ModelAndView("usuario/lista");

		if (entity != null) {
			modelAndView.addObject(entity);
		}else {
			modelAndView.addObject(new Permissao());
		}
		return modelAndView;
	}

	@GetMapping("ajax/{id}")
	@ResponseBody
	protected Usuario editar(@PathVariable Long id) {
		return usuarioRepository.findById(id).orElse(null);
	}

	@PostMapping("ajax")
	public ResponseEntity<?> salvar(@Valid Usuario entity, BindingResult result,
			RedirectAttributes Atributes){
		if (result.hasErrors()) {
			return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		} 
		entity.setPassword(entity.getEncodedPassword(entity.getPassword()));
		usuarioRepository.save(entity);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id){
		try {
			usuarioRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
}



















