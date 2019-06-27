package br.edu.utfpr.tcc.controller;

import br.edu.utfpr.tcc.model.Tipo;
import br.edu.utfpr.tcc.repository.TipoRepository;
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
@RequestMapping("/tipos")
public class TipoController {

	@Autowired
	private TipoRepository tipoRepository;

	@GetMapping
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("tipo/lista");
		modelAndView.addObject("tipos", tipoRepository.findAll());
		modelAndView.addObject("tipo", new Tipo());

		return modelAndView;
	}

	@GetMapping({"novo"})
	public ModelAndView novo(Tipo tipo) {
		ModelAndView modelAndView = new ModelAndView("tipo/lista");
		if (tipo != null) {
			modelAndView.addObject(tipo);
		}else {
			modelAndView.addObject(new Tipo());
		}
		return modelAndView;
	}

	@PostMapping("ajax")
	public ResponseEntity<?> salvar(@Valid Tipo tipo, BindingResult result,
									RedirectAttributes attributes) {
		if ( result.hasErrors() ) {
			return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		tipoRepository.save(tipo);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("ajax/{id}")
	@ResponseBody
	public Tipo editar(@PathVariable Long id) {
		return tipoRepository.findById(id).orElse(null);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id){
		try {
			tipoRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
