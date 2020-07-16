package br.edu.utfpr.tcc.controller;

import br.edu.utfpr.tcc.model.Anuncio;
import br.edu.utfpr.tcc.repository.AnuncioRepository;
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
@RequestMapping("/anuncios")
public class AnuncioController {

	@Autowired
	private AnuncioRepository anuncioRepository;

	@GetMapping
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("anuncio/lista");
		modelAndView.addObject("anuncios", anuncioRepository.findAll());
		modelAndView.addObject("anuncio", new Anuncio());

		return modelAndView;
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
