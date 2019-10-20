package br.com.thiagoGomes.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thiagoGomes.domain.Cliente;
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

}
