package br.edu.utfpr.tcc.controller;

import br.edu.utfpr.tcc.model.Cidade;
import br.edu.utfpr.tcc.repository.CidadeRepository;
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
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@GetMapping
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("cidade/lista");
		modelAndView.addObject("cidades", cidadeRepository.findAll());
		modelAndView.addObject("estados", estadoRepository.findAll() );
		modelAndView.addObject("cidade", new Cidade());

		return modelAndView;
	}

	@GetMapping({"novo"})
	public ModelAndView novo(Cidade cidade) {
		ModelAndView modelAndView = new ModelAndView("cidade/lista");

		if (cidade != null) {
			modelAndView.addObject(cidade);
		}else {
			modelAndView.addObject(new Cidade());
		}
		return modelAndView;
	}

	@PostMapping("ajax")
	public ResponseEntity<?> salvar(@Valid Cidade cidade, BindingResult result,
									RedirectAttributes attributes) {
		if ( result.hasErrors() ) {
			return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		cidadeRepository.save(cidade);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("ajax/{id}")
	@ResponseBody
	public Cidade editar(@PathVariable Long id) {
		return cidadeRepository.findById(id).orElse(null);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id){
		try {
			cidadeRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
