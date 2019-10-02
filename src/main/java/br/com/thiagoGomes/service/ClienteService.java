package br.com.thiagoGomes.service;

import br.com.thiagoGomes.domain.Cliente;
import br.com.thiagoGomes.repositories.ClienteRepository;
import br.com.thiagoGomes.repositories.EnderecoRepository;
import br.com.thiagoGomes.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente buscar(Integer id) {
        final Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto Não encontrado: Id: " + id + " ,tipo: " + Cliente.class.getName()));
    }

}
