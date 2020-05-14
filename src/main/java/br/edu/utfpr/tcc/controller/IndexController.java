package br.edu.utfpr.tcc.controller;

import br.edu.utfpr.tcc.model.Categoria;
import br.edu.utfpr.tcc.model.Produto;
import br.edu.utfpr.tcc.repository.*;
import br.edu.utfpr.tcc.services.S3Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/index")
public class IndexController {

	@GetMapping
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("index");
		return modelAndView;
	}

}
