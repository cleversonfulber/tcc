package br.edu.utfpr.tcc.controller;

import br.edu.utfpr.tcc.model.Cidade;
import br.edu.utfpr.tcc.model.Marca;
import br.edu.utfpr.tcc.model.Usuario;
import br.edu.utfpr.tcc.repository.PermissaoRepository;
import br.edu.utfpr.tcc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("usuarios")
public class UsuarioEditController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PermissaoRepository permissaoRepository;


	@GetMapping
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("usuario/lista");
		modelAndView.addObject("usuarios", usuarioRepository.findAll());
		modelAndView.addObject("permissoes", permissaoRepository.findAll());
		modelAndView.addObject("usuario", new Marca());

		return modelAndView;
	}

	@GetMapping({"novo"})
	protected ModelAndView novo(Usuario usuario) {
		ModelAndView modelAndView = new ModelAndView("usuario/lista");
		if (usuario != null) {
			modelAndView.addObject(usuario);
		}else {
			modelAndView.addObject(new Marca());
		}
		return modelAndView;
	}
	
	@GetMapping("{id}")
	@ResponseBody
	public Usuario editar(@PathVariable Long id) {
		return usuarioRepository.findById(id).orElse(null);
	}

	@PostMapping("ajax")
	public ResponseEntity<?> salvar(@Valid Usuario entity,BindingResult result,
                                       RedirectAttributes attributes) {
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


