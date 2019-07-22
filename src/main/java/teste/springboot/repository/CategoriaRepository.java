package teste.springboot.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import teste.springboot.model.Categoria;
import teste.springboot.model.Gastos;

@Repository
@Transactional
public interface CategoriaRepository extends CrudRepository<Categoria, Long>{
	
	@Query(value = "from Categoria t where nome = :nome")
	public Categoria findByNome(@Param("nome")String nome);

}
