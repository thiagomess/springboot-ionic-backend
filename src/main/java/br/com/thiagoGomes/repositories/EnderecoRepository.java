package br.com.thiagoGomes.repositories;

import br.com.thiagoGomes.domain.Cliente;
import br.com.thiagoGomes.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
