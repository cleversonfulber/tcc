package br.edu.utfpr.tcc.controller;

import br.edu.utfpr.tcc.model.Promocao;
import br.edu.utfpr.tcc.repository.ProdutoRepository;
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

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping("/lista/{id}")
	public ModelAndView listar(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("promocao/lista");
		modelAndView.addObject("promocoes", promocaoRepository.buscarProdutoPromocao(id));
		modelAndView.addObject("promocao", new Promocao());
		modelAndView.addObject("produtos", produtoRepository.getOne(id) );

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
		produtoRepository.excluirPromocaoProduto(promocao.getId());
		produtoRepository.salvarPromocaoProduto(promocao.getId(), promocao.getProduto());

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("editar/{id}")
	@ResponseBody
	public Promocao editar(@PathVariable Long id) {
		return promocaoRepository.findById(id).orElse(null);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id){
		try {
			produtoRepository.excluirPromocaoProduto(id);
			promocaoRepository.deleteById(id);

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}