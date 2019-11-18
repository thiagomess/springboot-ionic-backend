package br.com.thiagoGomes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.thiagoGomes.domain.Categoria;
import br.com.thiagoGomes.domain.Produto;
import br.com.thiagoGomes.repositories.CategoriaRepository;
import br.com.thiagoGomes.repositories.ProdutoRepository;
import br.com.thiagoGomes.service.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;
    
    @Autowired 
    private CategoriaRepository categoriaRepository;

    public Produto find(Integer id) {
        final Optional<Produto> produto = repository.findById(id);
        return produto.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto Não encontrado: Id: " + id + " ,tipo: " + Produto.class.getName()));
    }
    
    public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
    	
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		
		return repository.findDistinctByNomeIgnoreCaseContainingAndCategoriasIn(nome, categorias, pageRequest);
    }
    
    

}
