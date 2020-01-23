package br.com.thiagoGomes.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.thiagoGomes.domain.Categoria;
import br.com.thiagoGomes.dto.CategoriaDTO;
import br.com.thiagoGomes.service.CategoriaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;

	@ApiOperation(value="Busca por id", response = Categoria.class)
	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		final Categoria categoria = service.find(id);
		return ResponseEntity.ok().body(categoria);
	}

	@ApiOperation(value="Insere categoria")
	@PreAuthorize("hasAnyRole('ADMIN')") //Somente ADMIN pode acessar este endpoint(Config no SecurityConfig)
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDto) {
		Categoria obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value="Atualiza categoria")
	@PreAuthorize("hasAnyRole('ADMIN')") //Somente ADMIN pode acessar este endpoint(Config no SecurityConfig)
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDto, @PathVariable Integer id) {
		Categoria obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	

	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Não é possível excluir uma categoria que possui produtos"),
			@ApiResponse(code = 404, message = "Código inexistente") })
	@ApiOperation(value="Deleta categoria")
	@PreAuthorize("hasAnyRole('ADMIN')") //Somente ADMIN pode acessar este endpoint(Config no SecurityConfig)
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@ApiOperation(value="Retorna todas as categorias", response = CategoriaDTO.class)
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<Categoria> listaCategorias = service.findAll();
		List<CategoriaDTO> listaDto = listaCategorias.stream().map(obj -> new CategoriaDTO(obj))
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(listaDto);
	}
	
	//http://localhost:8080/categorias/page?linesPerPage=2&page=3&direction=DESC&orderBy=id
	//Caso nao escolhido os atributos no param, será setado os valores default
	@ApiOperation(value="Retorna todas as categorias por paginação", response = CategoriaDTO.class)
	@GetMapping(value = "/page")
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(name = "page", defaultValue = "0") Integer page, 
			@RequestParam(name = "linesPerPage", defaultValue = "24")Integer linesPerPage, 
			@RequestParam(name = "orderBy", defaultValue = "nome")String orderBy, 
			@RequestParam(name = "direction", defaultValue = "ASC")String direction) {
		Page<Categoria> listaCategorias = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> listaDto = listaCategorias.map(obj -> new CategoriaDTO(obj));

		return ResponseEntity.ok().body(listaDto);
	}

}
