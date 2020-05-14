package br.edu.utfpr.tcc.controller;

import br.edu.utfpr.tcc.model.Marca;
import br.edu.utfpr.tcc.repository.MarcaRepository;
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
@RequestMapping("/marcas")
public class MarcaController {

	@Autowired
	private MarcaRepository marcaRepository;

	@GetMapping
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("marca/lista");
		modelAndView.addObject("marcas", marcaRepository.findAll());
		modelAndView.addObject("marca", new Marca());

		return modelAndView;
	}

	@GetMapping({"novo"})
	public ModelAndView novo(Marca marca) {
		ModelAndView modelAndView = new ModelAndView("marca/lista");
		if (marca != null) {
			modelAndView.addObject(marca);
		}else {
			modelAndView.addObject(new Marca());
		}
		return modelAndView;
	}

	@PostMapping("ajax")
	public ResponseEntity<?> salvar(@Valid Marca marca, BindingResult result,
									RedirectAttributes attributes) {
		if ( result.hasErrors() ) {
			return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		marcaRepository.save(marca);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("ajax/{id}")
	@ResponseBody
	public Marca editar(@PathVariable Long id) {
		return marcaRepository.findById(id).orElse(null);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id){
		try {
			marcaRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
