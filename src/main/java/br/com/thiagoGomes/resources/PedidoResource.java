package br.com.thiagoGomes.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.thiagoGomes.domain.Pedido;
import br.com.thiagoGomes.dto.PedidoDTO;
import br.com.thiagoGomes.service.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService service;

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<PedidoDTO> find(@PathVariable Integer id) {
        final Pedido pedido = service.find(id);
        PedidoDTO pedidoDTO = new PedidoDTO(pedido);
        return ResponseEntity.ok().body(pedidoDTO);
    }
    
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj) {
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping
	public ResponseEntity<Page<PedidoDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="instante") String orderBy,  //Campo que será ordenado
			@RequestParam(value="direction", defaultValue="DESC") String direction) {
		Page<Pedido> listaPedidos = service.findPage(page, linesPerPage, orderBy, direction);
		Page<PedidoDTO> listaDto = listaPedidos.map(obj -> new PedidoDTO(obj));
		return ResponseEntity.ok().body(listaDto);
	}

}
