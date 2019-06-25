package teste.springboot.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import teste.springboot.model.Gastos;

@Repository
@Transactional
public interface GastosRepository extends CrudRepository<Gastos, Long>{
	
	@Query(value = "from Gastos t where data = :startDate")
	public List<Gastos> findGastosByData(@Param("startDate")Date startDate);

	@Query(value = "from Gastos t where t.categoria.nome = ?1")
	public List<Gastos> findGastosByCategoria(String nomepesquisa);
}
