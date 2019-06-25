package teste.springboot.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import teste.springboot.model.Categoria;
import teste.springboot.model.Gastos;
import teste.springboot.repository.CategoriaRepository;
import teste.springboot.repository.GastosRepository;

@Controller
public class GastosController {
	
	@Autowired
	private GastosRepository gastosRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	@RequestMapping(method=RequestMethod.GET, value="/cadastrogastos")
	public ModelAndView inicio() throws JsonProcessingException {
		
		ModelAndView andView = new ModelAndView("cadastro/cadastrogastos");
		Iterable<Categoria> categoriaaIt = categoriaRepository.findAll();
		andView.addObject("categorias", categoriaaIt);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(categoriaaIt);
		andView.addObject("categoriasJosn", jsonInString2);
		
		
		andView.addObject("gastosobj", new Gastos());
		
		return andView;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="**/salvargastos")
	public ModelAndView salvar(Gastos pessoa) {
		
		gastosRepository.save(pessoa);
		
		ModelAndView andView = new ModelAndView("cadastro/cadastrogastos");
		Iterable<Gastos> gastosaIt = gastosRepository.findAll();
		andView.addObject("gastosobj", new Gastos());
		andView.addObject("gastos", gastosaIt);
		
		Iterable<Categoria> categoriaaIt = categoriaRepository.findAll();
		andView.addObject("categorias", categoriaaIt);
		
		return andView;
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/listagastos")
	public  ModelAndView pessoas() {
		
		ModelAndView andView = new ModelAndView("cadastro/cadastrogastos");
		Iterable<Gastos> gastosaIt = gastosRepository.findAll();
		andView.addObject("gastosobj", new Gastos());
		andView.addObject("gastos", gastosaIt);
		
		Iterable<Categoria> categoriaaIt = categoriaRepository.findAll();
		andView.addObject("categorias", categoriaaIt);
		
		return andView;
	}
	
	
	@GetMapping("/editargastos/{idgastos}")
	public  ModelAndView editar(@PathVariable("idgastos") Long codigousuario) {
		
		ModelAndView andView = new ModelAndView("cadastro/cadastrogastos");
		Optional<Gastos> gastos = gastosRepository.findById(codigousuario);
		
		andView.addObject("gastosobj", gastos);
		
		Iterable<Categoria> categoriaaIt = categoriaRepository.findAll();
		andView.addObject("categorias", categoriaaIt);
		
		return andView;
	}
	
	@GetMapping("/removergastos/{idgastos}")
	public  ModelAndView excluir(@PathVariable("idgastos") Long codigousuario) {
		
		gastosRepository.deleteById(codigousuario);;
		
		ModelAndView andView = new ModelAndView("cadastro/cadastrogastos");
		
		andView.addObject("gastosobj", new Gastos());
		andView.addObject("gastos", gastosRepository.findAll());
		
		Iterable<Categoria> categoriaaIt = categoriaRepository.findAll();
		andView.addObject("categorias", categoriaaIt);
		
		return andView;
	}
	
	@PostMapping("**/pesquisagastos")
	public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa) throws ParseException {
		
		ModelAndView andView = new ModelAndView("cadastro/cadastrogastos");
		
		andView.addObject("gastosobj", new Gastos());
		
		SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
		
		Date data1 = (Date) format1.parse(nomepesquisa);	
		
		andView.addObject("gastos", gastosRepository.findGastosByData(data1));
		
		
		Iterable<Categoria> categoriaaIt = categoriaRepository.findAll();
		andView.addObject("categorias", categoriaaIt);
		
		return andView;
	}
	
	@PostMapping("**/pesquisagastoscategoria")
	public ModelAndView pesquisarCategoria(@RequestParam("categoria") String nomepesquisa) {
		
		ModelAndView andView = new ModelAndView("cadastro/cadastrogastos");
		
		andView.addObject("gastosobj", new Gastos());
			
		
		andView.addObject("gastos", gastosRepository.findGastosByCategoria(nomepesquisa));
		
		
		Iterable<Categoria> categoriaaIt = categoriaRepository.findAll();
		andView.addObject("categorias", categoriaaIt);
		
		return andView;
	}
}
