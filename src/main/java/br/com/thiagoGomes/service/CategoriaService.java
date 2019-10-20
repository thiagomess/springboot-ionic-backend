package br.com.thiagoGomes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.thiagoGomes.domain.Categoria;
import br.com.thiagoGomes.repositories.CategoriaRepository;
import br.com.thiagoGomes.service.exceptions.DataIntegrityException;
import br.com.thiagoGomes.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Categoria find(Integer id) {
        final Optional<Categoria> categoria = repository.findById(id);
        return categoria.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto Não encontrado: Id: " + id + " ,tipo: " + Categoria.class.getName()));
    }

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repository.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos."); 
			//Esse erro será tratado na classe ResourceExceptionHandler
		}
	}

	public List<Categoria> findAll() {
		return repository.findAll();
	}

}
