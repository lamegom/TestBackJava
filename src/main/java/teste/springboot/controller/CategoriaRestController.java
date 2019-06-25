package teste.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import teste.springboot.model.Categoria;
import teste.springboot.repository.CategoriaRepository;

@RestController
public class CategoriaRestController {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("/api/categorias")
	Map<String, Long> all() {
	    List<Categoria> categorias = (List<Categoria>) categoriaRepository.findAll();
	    
	    Map<String, Long> formatted = new HashMap<String, Long>();
	    
	    for(Categoria categoria : categorias) {
	    	
	    	formatted.put(categoria.getNome(), null);
	    }
	    
	    return formatted;
	    
	    
	  }
	
}
