package br.edu.utfpr.tcc.controller;

import br.edu.utfpr.tcc.model.Produto;
import br.edu.utfpr.tcc.model.Promocao;
import br.edu.utfpr.tcc.model.Usuario;
import br.edu.utfpr.tcc.model.service.UsuarioService;
import br.edu.utfpr.tcc.repository.*;
import br.edu.utfpr.tcc.services.S3Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	S3Services s3Services;

	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private UsuarioService usuarioService;

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

	@Autowired
	private AnuncioRepository anuncioRepository;

	@GetMapping
	public ModelAndView listar() {

		Usuario usuario = (Usuario) usuarioService.loadUserByUsername(
				SecurityContextHolder.getContext().getAuthentication().getName()
		);

		ModelAndView modelAndView = new ModelAndView("produto/lista");
		modelAndView.addObject("produtos", produtoRepository.buscarProdutoUsuario(usuario.getId()) );
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

	@DeleteMapping("{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id){
		try {
			tamanhoRepository.excluirTamanhoProduto(id);
			produtoRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("ajax/{id}")
	@ResponseBody
	public Produto editar(@PathVariable Long id) {
		produtoRepository.validarAnuncio(id);

		return produtoRepository.findById(id).orElse(new Produto());
	}

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

				String url = s3Services.uploadFile(nomeArquivo, caminhoAnexo + nomeArquivo);
				Produto p = this.produtoRepository.getOne(id);
				p.setImagem(url);
				this.produtoRepository.save(p);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	@GetMapping("/anuncios/{id}")
	public ModelAndView anuncio(@PathVariable Long id){

		Usuario usuario = (Usuario) usuarioService.loadUserByUsername(
				SecurityContextHolder.getContext().getAuthentication().getName()
		);

		ModelAndView modelAndView = new ModelAndView("produto/lista");
		modelAndView.addObject("produtos", produtoRepository.buscarProdutoAnuncio(id,usuario.getId()) );
		modelAndView.addObject("categorias", categoriaRepository.findAll() );
		modelAndView.addObject("marcas", marcaRepository.findAll() );
		modelAndView.addObject("tipos", tipoRepository.findAll() );
		modelAndView.addObject("promocoes", promocaoRepository.findAll() );
		modelAndView.addObject("tamanhos", tamanhoRepository.findAll() );
		modelAndView.addObject("cores", corRepository.findAll() );
		modelAndView.addObject("usuncios", anuncioRepository.getOne(id) );
		modelAndView.addObject("produto", new Produto());

		return modelAndView;
	}

//	@GetMapping("editarProduto/{id}")
//	@ResponseBody
//	public void editarProduto(@PathVariable Long id) {
//		produtoRepository.editarProduto(id);
//	}



}
