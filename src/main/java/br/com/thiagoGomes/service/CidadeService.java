package br.com.thiagoGomes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.thiagoGomes.domain.Cidade;
import br.com.thiagoGomes.repositories.CidadeRepository;
import br.com.thiagoGomes.service.exceptions.ObjectNotFoundException;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repository;

	public List<Cidade> findByEstado(Integer estado_id) {
		List<Cidade> obj = repository.findCidades(estado_id);
		 if (obj.isEmpty()) {
			throw new ObjectNotFoundException("Nenhuma Cidade cadastrada");
		}
		 return obj;
	}

}
