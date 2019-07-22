package teste.springboot.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import teste.springboot.model.Gastos;
import teste.springboot.repository.CategoriaRepository;
import teste.springboot.repository.GastosRepository;

@RestController
public class GastosRestController {
	
	@Autowired
	private GastosRepository gastosRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("/api/gastos")
	Map<Long, String> all() {
	    List<Gastos> gastos = (List<Gastos>) gastosRepository.findAll();
	    
	    Map<Long, String> formatted = new HashMap<Long, String>();
	    
	    for(Gastos gasto : gastos) {
	    	
	    	formatted.put(gasto.getCodigousuario(), gasto.getDescricao());
	    }
	    
	    return formatted;
	    
	    
	  }
	
	@RequestMapping(value = "/api/gastos/all", method = { RequestMethod.GET, RequestMethod.POST })
	List<Gastos> getAll() {
	    List<Gastos> gastos = (List<Gastos>) gastosRepository.findAll();
	
	    return gastos;
	}
	
	@RequestMapping(value = "/api/gastos/delete", method = RequestMethod.POST, consumes = {"multipart/form-data"}, produces = "application/json")
	public String delete(@RequestParam String id) {

		gastosRepository.deleteById(Long.valueOf(id)); 


	    return "{\"message\":\"Excluido com sucesso\"}";
	}
	
	@RequestMapping(value = "/api/gastos/id", method = RequestMethod.POST, consumes = {"multipart/form-data"})
	public Optional<Gastos> findById(@RequestParam  String gastoId) {

	   Optional<Gastos> gasto = gastosRepository.findById(Long.valueOf(gastoId)); 

	    return gasto;
	}
	
	@RequestMapping(value = "/api/gastos/insert", method = RequestMethod.POST)
	public String insert(@RequestBody String nome) throws ParseException {
		
		System.out.println("nome: " + nome);
		
		JSONObject jsonObject = null;
		try {
		     jsonObject = new JSONObject(nome);
		}catch (JSONException err){
		     System.out.println( err.toString());
		}

		Gastos gasto = new Gastos();
	   try {
		   gasto.setDescricao((String) jsonObject.get("descricao"));
		   gasto.setValor(Double.valueOf((String) jsonObject.get("Valor")));
		   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
		   
		   Date data = formatter.parse((String) jsonObject.get("fromDate"));
		   
		   gasto.setData(data);
		   
		   Optional<Categoria> categoria = categoriaRepository.findById(Long.valueOf((Integer) jsonObject.get("categoria")));
		   
		   categoria.ifPresent(cat -> {

				gasto.setCategoria(cat);
			   
			});
		   
		   gastosRepository.save(gasto);
//		   
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	   



	   return "{\"messsage\": \"ok\"}";
	}
	
	@RequestMapping(value = "/api/gastos/edit", method = RequestMethod.POST)
	public String edit(@RequestBody String post) {
		
		try {
			
			JSONObject jsonObject = new JSONObject(post);


			Optional<Gastos> gasto = null;

			gasto = gastosRepository.findById(Long.valueOf((Integer) jsonObject.get("id")));


		   
			gasto.ifPresent(gas -> {

				try {
					gas.setDescricao((String) jsonObject.get("descricao"));
					gas.setValor(Double.valueOf((String) jsonObject.get("valor")));
//					gas.setData((String) jsonObject.get("data"));
//					gas.setCategoria((String) jsonObject.get("categoria"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			   
				gastosRepository.save(gas);
			   
			});
		} catch (NumberFormatException | JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		   
		

		
		



	    return "\"success\"";
	}
	
}
