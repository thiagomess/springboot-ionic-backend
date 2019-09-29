package br.com.thiagoGomes.repositories;

import br.com.thiagoGomes.domain.Categoria;
import br.com.thiagoGomes.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
