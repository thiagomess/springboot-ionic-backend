package br.com.thiagoGomes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.thiagoGomes.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

	@Transactional(readOnly=true)
	List<Estado> findAllByOrderByNome();
}
