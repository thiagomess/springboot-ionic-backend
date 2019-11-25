package br.com.thiagoGomes.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.thiagoGomes.domain.Produto;
import br.com.thiagoGomes.dto.ProdutoDTO;
import br.com.thiagoGomes.resources.utils.URL;
import br.com.thiagoGomes.service.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService service;

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<Produto> find(@PathVariable Integer id) {
        final Produto produto = service.find(id);
        return ResponseEntity.ok().body(produto);
    }
    
    //URL: http://localhost:8080/produtos?nome=or&categorias=1,4
	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(name = "nome", defaultValue = "") String nome, 
			@RequestParam(name = "categorias", defaultValue = "") String categorias, 
			@RequestParam(name = "page", defaultValue = "0") Integer page, 
			@RequestParam(name = "linesPerPage", defaultValue = "24")Integer linesPerPage, 
			@RequestParam(name = "orderBy", defaultValue = "nome")String orderBy, 
			@RequestParam(name = "direction", defaultValue = "ASC")String direction) {
		
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Produto> listaProdutos = service.search(nomeDecoded, ids,  page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listaDto = listaProdutos.map(obj -> new ProdutoDTO(obj));

		return ResponseEntity.ok().body(listaDto);
	}

}
