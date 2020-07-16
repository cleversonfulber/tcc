package br.edu.utfpr.tcc.controller;

import br.edu.utfpr.tcc.model.Categoria;
import br.edu.utfpr.tcc.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

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
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("categoria/lista");
		modelAndView.addObject("categorias", categoriaRepository.findAll());
		modelAndView.addObject("categoria", new Categoria());

		return modelAndView;
	}

	@GetMapping({"novo"})
	public ModelAndView novo(Categoria categoria) {
		ModelAndView modelAndView = new ModelAndView("categoria/lista");
		if (categoria != null) {
			modelAndView.addObject(categoria);
		}else {
			modelAndView.addObject(new Categoria());
		}
		return modelAndView;
	}

	@PostMapping("ajax")
	public ResponseEntity<?> salvar(@Valid Categoria categoria, BindingResult result,
							   RedirectAttributes attributes) {
		if ( result.hasErrors() ) {
			return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		categoriaRepository.save(categoria);
		return new ResponseEntity<>(HttpStatus.OK);
	}

//	@GetMapping("page")
//	public ModelAndView listar(@RequestParam("page") Optional<Integer> page,
//							 @RequestParam("size") Optional<Integer> size) {
//		int currentPage = page.orElse(1);
//		int pageSize = size.orElse(5);
//
//		Page<Categoria> list = categoriaRepository.findAll(
//				new PageRequest(currentPage -1, pageSize) );
//
//		ModelAndView modelAndView = new ModelAndView("categoria/lista");
//		modelAndView.addObject("lista", list);
//
//		if( list.getTotalPages() > 0) {
//			List<Integer> pageNumbers = IntStream
//					.rangeClosed(1, list.getTotalPages())
//					.boxed().collect(Collectors.toList());
//			modelAndView.addObject("pageNumbers", pageNumbers);
//		}
//		return modelAndView;
//	}

	@GetMapping("ajax/{id}")
	@ResponseBody
	public Categoria editar(@PathVariable Long id) {
			return categoriaRepository.findById(id).orElse(null);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id){
		try {
			categoriaRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
