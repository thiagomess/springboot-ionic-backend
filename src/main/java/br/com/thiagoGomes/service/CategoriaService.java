package br.com.thiagoGomes.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.thiagoGomes.domain.Categoria;
import br.com.thiagoGomes.dto.CategoriaDTO;
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

	public Categoria insert(@Valid Categoria obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Categoria update(Categoria obj) {
		Categoria newObj = find(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos.");
			// Esse erro será tratado na classe ResourceExceptionHandler
		}
	}

	public List<Categoria> findAll() {
		return repository.findAll();
	}

	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		//https://github.com/thiagomess/Springboot-forum-API_REST/commit/3af0426a36f61e6f4575884aa1ef20052c26c6ef
		//Outra forma de fazer seria igual este do link acima, pois nao precisa criar novas variaveis, utiliza as variaveis padrao da classe
		//que sao ?page=1&size=9&sort=nome,asc e mataria essa linha PageRequest
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}
	
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}

}
