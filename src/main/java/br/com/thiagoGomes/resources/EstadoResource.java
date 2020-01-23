package br.com.thiagoGomes.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thiagoGomes.domain.Cidade;
import br.com.thiagoGomes.domain.Estado;
import br.com.thiagoGomes.dto.CidadeDTO;
import br.com.thiagoGomes.dto.EstadoDTO;
import br.com.thiagoGomes.service.CidadeService;
import br.com.thiagoGomes.service.EstadoService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

	@Autowired
	private EstadoService service;

	@Autowired
	private CidadeService cidadeService;

	@ApiOperation(value="Busca todos estados", response = EstadoDTO.class)
	@GetMapping
	public ResponseEntity<List<EstadoDTO>> find() {
		List<Estado> estados = service.findAllByOrderByNome();
		List<EstadoDTO> listDto = estados.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@ApiOperation(value="Busca cidades de um estado", response = CidadeDTO.class)
	@RequestMapping("/{estadoId}/cidades")
	public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId) {
		List<Cidade> cidades = cidadeService.findByEstado(estadoId);
		List<CidadeDTO> listCidade = cidades.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listCidade);
	}

}
