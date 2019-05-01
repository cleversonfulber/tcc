package br.edu.utfpr.tcc.controller;

import br.edu.utfpr.tcc.model.Tipo;
import br.edu.utfpr.tcc.repository.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

	@GetMapping({"","/"})
	public ModelAndView novo(Tipo tipo) {
		ModelAndView modelAndView = new ModelAndView("tipo/lista");
		modelAndView.addObject("tipos", tipoRepository.findAll());
		modelAndView.addObject(tipo);

		return modelAndView;
	}

	@PostMapping("/")
	public ModelAndView salvar(@Valid Tipo tipo, BindingResult result,
							   RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(tipo);
		}
		tipoRepository.save(tipo);
		attributes.addFlashAttribute("mensagem","Tipo salva com sucesso!");
		return new ModelAndView("redirect:/tipos/");
	}

	@GetMapping("/{id}")
	@ResponseBody
	public Tipo editar(@PathVariable Long id, RedirectAttributes attributes) {
		attributes.addFlashAttribute("mensagem","Tipo salva com sucesso!");
		return tipoRepository.findOne(id);
	}

	@DeleteMapping("/{id}")
	public String remover(@PathVariable Long id, RedirectAttributes attributes) {
		tipoRepository.delete(id);
		attributes.addFlashAttribute("mensagem", "Tipo removida com sucesso!");
		return "redirect:/tipos";
	}


}
