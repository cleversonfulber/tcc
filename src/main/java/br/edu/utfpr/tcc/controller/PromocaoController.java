package br.edu.utfpr.tcc.controller;

import br.edu.utfpr.tcc.model.Promocao;
import br.edu.utfpr.tcc.repository.PromocaoRepository;
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
@RequestMapping("/promocoes")
public class PromocaoController {

	@Autowired
	private PromocaoRepository promocaoRepository;

	@GetMapping
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("promocao/lista");
		modelAndView.addObject("promocoes", promocaoRepository.findAll());
		modelAndView.addObject("promocao", new Promocao());

		return modelAndView;
	}

	@GetMapping({"novo"})
	public ModelAndView novo(Promocao promocao) {
		ModelAndView modelAndView = new ModelAndView("promocao/lista");
		if (promocao != null) {
			modelAndView.addObject(promocao);
		}else {
			modelAndView.addObject(new Promocao());
		}
		return modelAndView;
	}

	@PostMapping("ajax")
	public ResponseEntity<?> salvar(@Valid Promocao promocao, BindingResult result,
							   RedirectAttributes attributes) {
		if ( result.hasErrors() ) {
			return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		promocaoRepository.save(promocao);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("ajax/{id}")
	@ResponseBody
	public Promocao editar(@PathVariable Long id) {
		return promocaoRepository.findOne(id);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id){
		try {
			promocaoRepository.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}