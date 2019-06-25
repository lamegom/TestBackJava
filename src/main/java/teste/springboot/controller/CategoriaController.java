package teste.springboot.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import teste.springboot.model.Categoria;
import teste.springboot.model.Gastos;
import teste.springboot.repository.CategoriaRepository;

@Controller
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@RequestMapping(method=RequestMethod.GET, value="/cadastrocategoria")
	public ModelAndView inicio() {
		
		ModelAndView andView = new ModelAndView("cadastro/cadastrocategoria");
		
		andView.addObject("categoriaobj", new Categoria());
		
		return andView;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="**/salvarcategoria")
	public ModelAndView salvar(Categoria categoria) {
		
		categoriaRepository.save(categoria);
		
		ModelAndView andView = new ModelAndView("cadastro/cadastrocategoria");
		Iterable<Categoria> categoriaaIt = categoriaRepository.findAll();
		andView.addObject("categoriaobj", new Categoria());
		andView.addObject("categorias", categoriaaIt);
		
		return andView;
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/listacategoria")
	public  ModelAndView pessoas() {
		
		ModelAndView andView = new ModelAndView("cadastro/cadastrocategoria");
		Iterable<Categoria> categoriaaIt = categoriaRepository.findAll();
		andView.addObject("categoriaobj", new Categoria());
		andView.addObject("categorias", categoriaaIt);
		
		return andView;
	}
	
	
	@GetMapping("/editarcategoria/{idcategoria}")
	public  ModelAndView editar(@PathVariable("idcategoria") Long id) {
		
		ModelAndView andView = new ModelAndView("cadastro/cadastrocategoria");
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		
		andView.addObject("categoriaobj", categoria);
		
		return andView;
	}
	
	@GetMapping("/removercategoria/{idcategoria}")
	public  ModelAndView excluir(@PathVariable("idcategoria") Long id) {
		
		categoriaRepository.deleteById(id);;
		
		ModelAndView andView = new ModelAndView("cadastro/cadastrocategoria");
		
		andView.addObject("categoriaobj", new Categoria());
		andView.addObject("categorias", categoriaRepository.findAll());
		
		return andView;
	}
	
}
