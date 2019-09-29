package br.com.thiagoGomes.resources;

import br.com.thiagoGomes.domain.Categoria;
import br.com.thiagoGomes.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        final Categoria categoria = service.buscar(id);
        return ResponseEntity.ok().body(categoria);
    }

}
