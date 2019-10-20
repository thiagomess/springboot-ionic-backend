package br.com.thiagoGomes.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.thiagoGomes.domain.Pedido;
import br.com.thiagoGomes.repositories.PedidoRepository;
import br.com.thiagoGomes.service.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    public Pedido buscar(Integer id) {
        final Optional<Pedido> pedido = repository.findById(id);
        return pedido.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto Não encontrado: Id: " + id + " ,tipo: " + Pedido.class.getName()));
    }

}
