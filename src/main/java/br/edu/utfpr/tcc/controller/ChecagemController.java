package br.edu.utfpr.tcc.controller;

import br.edu.utfpr.tcc.model.Estado;
import br.edu.utfpr.tcc.model.Pedido;
import br.edu.utfpr.tcc.model.Produto;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/checagem")
public class ChecagemController {

    @Autowired
    S3Services s3Services;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private  PedidoRepository pedidoRepository;

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
    private EnderecoRepository enderecoRepository;

    @GetMapping()
    public ModelAndView checagem() {
        Usuario usuario = (Usuario) usuarioService.loadUserByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName()
        );

        ModelAndView modelAndView = new ModelAndView("checagem");
        modelAndView.addObject("produtos", produtoRepository.findAll());
        modelAndView.addObject("categorias", categoriaRepository.findAll());
        modelAndView.addObject("marcas", marcaRepository.findAll() );
        modelAndView.addObject("tipos", tipoRepository.findAll() );
        modelAndView.addObject("promocoes", promocaoRepository.findAll());
        modelAndView.addObject("tamanhos", tamanhoRepository.findAll());
        modelAndView.addObject("cores", corRepository.findAll());
        modelAndView.addObject("enderecos", usuario.getEnderecos());
        modelAndView.addObject("produto", new Produto());

        return modelAndView;
    }

    @PostMapping("ajax")
    public ResponseEntity<?> salvar(@Valid Pedido pedido, BindingResult result,
                                    RedirectAttributes attributes) {
        if ( result.hasErrors() ) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        pedidoRepository.save(pedido);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
