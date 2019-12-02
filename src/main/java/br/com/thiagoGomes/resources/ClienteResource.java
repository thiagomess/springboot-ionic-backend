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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.thiagoGomes.domain.Cliente;
import br.com.thiagoGomes.dto.ClienteDTO;
import br.com.thiagoGomes.dto.ClienteNewDTO;
import br.com.thiagoGomes.service.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;

	@GetMapping
	@RequestMapping("/{id}")
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		final Cliente cliente = service.find(id);
		return ResponseEntity.ok().body(cliente);
	}
	
	//http://localhost:8080/clientes/email?value=thiagogomes19@hotmail.com
	@GetMapping
	@RequestMapping("/email")
	public ResponseEntity<Cliente> findByEmail(@RequestParam(value="value") String email) {
		final Cliente cliente = service.findByEmail(email);
		return ResponseEntity.ok().body(cliente);
	}

	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto) {
		Cliente obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id) {
		Cliente obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')") // Somente ADMIN pode acessar este endpoint(Config no SecurityConfig)
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')") // Somente ADMIN pode acessar este endpoint(Config no SecurityConfig)
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> listaClientes = service.findAll();
		List<ClienteDTO> listaDto = listaClientes.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());

		return ResponseEntity.ok().body(listaDto);
	}

	@PreAuthorize("hasAnyRole('ADMIN')") // Somente ADMIN pode acessar este endpoint(Config no SecurityConfig)
	// http://localhost:8080/clientes/page?linesPerPage=2&page=3&direction=DESC&orderBy=id
	// Caso nao escolhido os atributos no param, será setado os valores default
	@GetMapping(value = "/page")
	public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(name = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(name = "direction", defaultValue = "ASC") String direction) {
		Page<Cliente> listaClientes = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listaDto = listaClientes.map(obj -> new ClienteDTO(obj));

		return ResponseEntity.ok().body(listaDto);
	}

	//endpoint que irá receber um arquivo
	@PostMapping("/picture")
	public ResponseEntity<Void> uploadProfile(@RequestParam("file") MultipartFile file) {
		URI uri = service.uploadProfilePicture(file);
		return ResponseEntity.created(uri).build();
	}

}
