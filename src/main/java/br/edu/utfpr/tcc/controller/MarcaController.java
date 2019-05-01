package br.edu.utfpr.tcc.controller;

import br.edu.utfpr.tcc.model.Marca;
import br.edu.utfpr.tcc.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

	@GetMapping({"","/"})
	public ModelAndView novo(Marca marca) {
		ModelAndView modelAndView = new ModelAndView("marca/lista");
		modelAndView.addObject("marcas", marcaRepository.findAll());
		modelAndView.addObject(marca);

		return modelAndView;
	}

	@PostMapping("/")
	public ModelAndView salvar(@Valid Marca marca, BindingResult result,
							   RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(marca);
		}
		marcaRepository.save(marca);
		attributes.addFlashAttribute("mensagem","Marca salva com sucesso!");
		return new ModelAndView("redirect:/marcas/");
	}

	@GetMapping("/{id}")
	@ResponseBody
	public Marca editar(@PathVariable Long id, RedirectAttributes attributes) {
		attributes.addFlashAttribute("mensagem","Marca salva com sucesso!");
		return marcaRepository.findOne(id);
	}

	@DeleteMapping("/{id}")
	public String remover(@PathVariable Long id, RedirectAttributes attributes) {
		marcaRepository.delete(id);
		attributes.addFlashAttribute("mensagem", "Marca removida com sucesso!");
		return "redirect:/marcas";
	}


}
