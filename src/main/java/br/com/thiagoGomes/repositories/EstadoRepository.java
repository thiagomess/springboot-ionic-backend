package br.com.thiagoGomes.repositories;

import br.com.thiagoGomes.domain.Cidade;
import br.com.thiagoGomes.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

}
