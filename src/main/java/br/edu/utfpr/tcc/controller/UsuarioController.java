package br.edu.utfpr.tcc.controller;

import br.edu.utfpr.tcc.model.Permissao;
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

//	protected String getURL() {
//		return "usuario";
//	}
//


//	@GetMapping("novo")
//	protected ModelAndView form(Usuario entity) {
//		ModelAndView modelAndView = new ModelAndView(getURL() + "/form");
//		if (entity == null) {
//			modelAndView.addObject("usuario", new Usuario());
//		} else {
//			modelAndView.addObject("usuario", entity);
//		}
//		return modelAndView;
//	}

//	@GetMapping("{id}")
//	@ResponseBody
//	public Usuario edit(@PathVariable Long id) {
//		return usuarioRepository.findById(id).orElse(null);
//	}



//	@GetMapping
//	public ModelAndView list(@RequestParam("page") Optional<Integer> page,
//							 @RequestParam("size") Optional<Integer> size) {
//		int currentPage = page.orElse(1);
//		int pageSize = size.orElse(5);
//
//		Page<Usuario> list = usuarioRepository.findAll(
//				PageRequest.of(currentPage -1, pageSize) );
//
//		ModelAndView modelAndView = new ModelAndView( "list");
//		modelAndView.addObject("list", list);
//
//		modelAndView.addObject("permissoes", permissaoRepository.findAll());
//
//		if( list.getTotalPages() > 0) {
//			List<Integer> pageNumbers = IntStream
//					.rangeClosed(1, list.getTotalPages())
//					.boxed().collect(Collectors.toList());
//			modelAndView.addObject("pageNumbers", pageNumbers);
//		}
//		return modelAndView;
//	}

}