package br.edu.utfpr.tcc.controller;

import br.edu.utfpr.tcc.model.Produto;
import br.edu.utfpr.tcc.repository.*;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;


@Controller
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private MarcaRepository marcaRepository;
	@Autowired
	private TipoRepository tipoRepository;
	@Autowired
	private PromocaoRepository promocaoRepository;
	@Autowired
	private TamanhoRepository tamanhoRepository;
	@Autowired
	private CorRepository corRepository;

	@GetMapping
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("produto/lista");
		modelAndView.addObject("produtos", produtoRepository.findAll());
		modelAndView.addObject("categorias", categoriaRepository.findAll() );
		modelAndView.addObject("marcas", marcaRepository.findAll() );
		modelAndView.addObject("tipos", tipoRepository.findAll() );
		modelAndView.addObject("promocoes", promocaoRepository.findAll() );
		modelAndView.addObject("tamanhos", tamanhoRepository.findAll() );
		modelAndView.addObject("cores", corRepository.findAll() );
		modelAndView.addObject("produto", new Produto());

		return modelAndView;
	}

	@GetMapping({"novo"})
	public ModelAndView novo(Produto produto) {
		ModelAndView modelAndView = new ModelAndView("produto/lista");

		if (produto != null) {
			modelAndView.addObject(produto);
		}else {
			modelAndView.addObject(new Produto());
		}
		return modelAndView;
	}

//	@PostMapping("ajax")
//	public ResponseEntity<?> salvar(@Valid Produto produto, BindingResult result,
//									RedirectAttributes attributes) {
//		if ( result.hasErrors() ) {
//			return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
//		}
//		produtoRepository.save(produto);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
//
//	@GetMapping("ajax/{id}")
//	@ResponseBody
//	public Produto editar(@PathVariable Long id) {
//		return produtoRepository.findOne(id);
//	}
//
//	@DeleteMapping("{id}")
//	public ResponseEntity<?> excluir(@PathVariable Long id){
//		try {
//			produtoRepository.delete(id);
//			return new ResponseEntity<>(HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//	}

	//método para salvar com upload de arquivos
	@PostMapping("upload")
	public ResponseEntity<?> salvar(@Valid Produto produto, BindingResult result,
									@RequestParam("anexos") MultipartFile[] anexos,
									HttpServletRequest request){

		if ( result.hasErrors() ) {
			return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		produtoRepository.save(produto);

		if (anexos.length > 0 && !anexos[0].getOriginalFilename().isEmpty()) {
			saveFile(produto.getId(), anexos, request);
		}


		return new ResponseEntity<>(HttpStatus.OK);
	}

	private void saveFile(Long id, MultipartFile[] anexos, HttpServletRequest request) {
		File dir = new File(request.getServletContext().getRealPath("/images/"));
		if (!dir.exists()) { //verifica se o diretório de armazenamento existe
			dir.mkdirs(); //não existindo, cria o diretório
		}

		String caminhoAnexo = request.getServletContext().getRealPath("/images/");
		int i = 0;
		for (MultipartFile anexo : anexos) {
			i++;
			String extensao = anexo.getOriginalFilename().substring(
					anexo.getOriginalFilename().lastIndexOf("."),
					anexo.getOriginalFilename().length());

			String nomeArquivo = id + "_" + i + extensao;

			try {
				FileOutputStream fileOut = new FileOutputStream(
						new File (caminhoAnexo + nomeArquivo));

				BufferedOutputStream stream = new BufferedOutputStream(fileOut);
				stream.write(anexo.getBytes());
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


	}
}