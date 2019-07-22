package teste.springboot.springboot.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import teste.springboot.controller.CategoriaRestController;
import teste.springboot.model.Categoria;
import teste.springboot.repository.CategoriaRepository;

@RunWith(SpringRunner.class)
@EntityScan(basePackages="teste.springboot.model")
@ComponentScan(basePackages= {"teste.*"})
@EnableJpaRepositories(basePackages="teste.springboot.repository")
@WebMvcTest(CategoriaRestController.class)
public class CategoriaServiceTest {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
    private MockMvc mvc;
	
	@Test
	public void whenFindByName_thenReturnCategoria() {
	    // given
	    Categoria categoria = new Categoria("alex");
	    categoriaRepository.save(categoria);

	 
	    // when
	    Categoria found = categoriaRepository.findByNome(categoria.getNome());
	 
	    // then
	    assertThat(found.getNome())
	      .isEqualTo(categoria.getNome());
	}
	
	@Test
	public void givenCategorias_whenGetCategorias_thenReturnJsonArray()
	  throws Exception {
	     
		Categoria alex = new Categoria("alex");
	 
	 
	    mvc.perform(get("/api/categorias/all")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(jsonPath("$[0].name", is(alex.getNome())));
	}
}
