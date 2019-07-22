package teste.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import teste.springboot.model.Categoria;
import teste.springboot.repository.CategoriaRepository;

@RestController
public class CategoriaRestController {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("/api/categorias")
	Map<Long, String> all() {
	    List<Categoria> categorias = (List<Categoria>) categoriaRepository.findAll();
	    
	    Map<Long, String> formatted = new HashMap<Long, String>();
	    
	    for(Categoria categoria : categorias) {
	    	
	    	formatted.put(categoria.getId(), categoria.getNome());
	    }
	    
	    return formatted;
	    
	    
	  }
	
	@RequestMapping(value = "/api/categorias/all", method = { RequestMethod.GET, RequestMethod.POST })
	List<Categoria> getAll() {
	    List<Categoria> categorias = (List<Categoria>) categoriaRepository.findAll();
	
	    return categorias;
	}
	
	@RequestMapping(value = "/api/categorias/delete", method = RequestMethod.POST, consumes = {"multipart/form-data"}, produces = "application/json")
	public String delete(@RequestParam String id) {

	   categoriaRepository.deleteById(Long.valueOf(id)); 


	    return "{\"message\":\"Excluido com sucesso\"}";
	}
	
	@RequestMapping(value = "/api/categorias/id", method = RequestMethod.POST, consumes = {"multipart/form-data"})
	public Optional<Categoria> findById(@RequestParam  String categoriaId) {

	   Optional<Categoria> categoria = categoriaRepository.findById(Long.valueOf(categoriaId)); 

	    return categoria;
	}
	
	@RequestMapping(value = "/api/categorias/insert", method = RequestMethod.POST)
	public String insert(@RequestBody String nome) {
		
		
		JSONObject jsonObject = null;
		try {
		     jsonObject = new JSONObject(nome);
		}catch (JSONException err){
		     System.out.println( err.toString());
		}

	   Categoria categoria = new Categoria();
	   try {
		categoria.setNome((String) jsonObject.get("nome"));
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		categoriaRepository.save(categoria);



	    return "\"success\"";
	}
	
	@RequestMapping(value = "/api/categorias/edit", method = RequestMethod.POST)
	public String edit(@RequestBody String post) {
		
		try {
			
			JSONObject jsonObject = new JSONObject(post);


			Optional<Categoria> categoria = null;

			categoria = categoriaRepository.findById(Long.valueOf((Integer) jsonObject.get("id")));


		   
		   categoria.ifPresent(cat -> {

				try {
					cat.setNome((String) jsonObject.get("nome"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			   
			   categoriaRepository.save(cat);
			   
			});
		} catch (NumberFormatException | JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		   
		

		
		



	    return "\"success\"";
	}
	
}
