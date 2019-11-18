package br.com.thiagoGomes.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.thiagoGomes.domain.Categoria;
import br.com.thiagoGomes.domain.Produto;

//Documentação do Spring data
//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	@Transactional(readOnly = true)
//	Se usar o padrao de nome do metodo usando na documentação que consta acima, nao precisa desta anotação @Query
//	O nome do metodo segundo o padrao ira fazer a mesma coisa que a JPQL da anotação
//	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE UPPER(obj.nome) LIKE UPPER(CONCAT('%',:nome,'%')) AND cat IN :categorias")
	Page<Produto> findDistinctByNomeIgnoreCaseContainingAndCategoriasIn(@Param("nome") String nome,
			@Param("categorias") List<Categoria> categorias, Pageable pageRequest);
}