package br.com.thiagoGomes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.thiagoGomes.domain.Estado;
import br.com.thiagoGomes.repositories.EstadoRepository;
import br.com.thiagoGomes.service.exceptions.ObjectNotFoundException;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repository;

	public List<Estado> findAllByOrderByNome() {
		 List<Estado> estado = repository.findAllByOrderByNome();
		 if (estado.isEmpty()) {
			throw new ObjectNotFoundException("Nenhum estado cadastrado");
		}
		 return estado;
	}

}
