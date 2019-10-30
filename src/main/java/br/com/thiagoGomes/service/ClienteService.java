package br.com.thiagoGomes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.thiagoGomes.domain.Cliente;
import br.com.thiagoGomes.dto.ClienteDTO;
import br.com.thiagoGomes.repositories.ClienteRepository;
import br.com.thiagoGomes.service.exceptions.DataIntegrityException;
import br.com.thiagoGomes.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente find(Integer id) {
        final Optional<Cliente> cliente = repository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto Não encontrado: Id: " + id + " ,tipo: " + Cliente.class.getName()));
    }
    
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir devido ter entidades relacionadas.");
			// Esse erro será tratado na classe ResourceExceptionHandler
		}
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		//https://github.com/thiagomess/Springboot-forum-API_REST/commit/3af0426a36f61e6f4575884aa1ef20052c26c6ef
		//Outra forma de fazer seria igual este do link acima, pois nao precisa criar novas variaveis, utiliza as variaveis padrao da classe
		//que sao ?page=1&size=9&sort=nome,asc e mataria essa linha PageRequest
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

}
