package teste.springboot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import teste.springboot.model.Categoria;

@Repository
@Transactional
public interface CategoriaRepository extends CrudRepository<Categoria, Long>{

}
