package br.edu.utfpr.tcc.controller;

import br.edu.utfpr.tcc.model.Estado;
import br.edu.utfpr.tcc.repository.EstadoRepository;
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
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;

	@GetMapping
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("estado/lista");
		modelAndView.addObject("estados", estadoRepository.findAll());
		modelAndView.addObject("estado", new Estado());

		return modelAndView;
	}

	@GetMapping({"novo"})
	public ModelAndView novo(Estado estado) {
		ModelAndView modelAndView = new ModelAndView("estado/lista");
		if (estado != null) {
			modelAndView.addObject(estado);
		}else {
			modelAndView.addObject(new Estado());
		}
		return modelAndView;
	}

	@PostMapping("ajax")
	public ResponseEntity<?> salvar(@Valid Estado estado, BindingResult result,
							   RedirectAttributes attributes) {
		if ( result.hasErrors() ) {
			return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		estadoRepository.save(estado);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("ajax/{id}")
	@ResponseBody
	public Estado editar(@PathVariable Long id) {
		return estadoRepository.findOne(id);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id){
		try {
			estadoRepository.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
