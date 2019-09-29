package br.com.thiagoGomes.service;

import br.com.thiagoGomes.domain.Categoria;
import br.com.thiagoGomes.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;
    
    public Categoria buscar(Integer id){
        final Optional<Categoria> categoria = repository.findById(id);
        return categoria.orElse(null);
    }
}