package teste.springboot.repository;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import teste.springboot.model.Categoria;
import teste.springboot.model.Gastos;

@Component
public class DatabaseLoader implements CommandLineRunner {
	@Autowired
    private CategoriaRepository repository;
    @Autowired
    private GastosRepository gastosRepository;

 
    @Override
    public void run(String... strings) throws Exception {
    	
    	Categoria categoria1 = new Categoria( "Joe Biden");
    	Categoria categoria2 = new Categoria( "President Obama");
    	Categoria categoria3 = new Categoria( "Crystal Mac");
    	Categoria categoria4 = new Categoria( "James Henry");
    	
    	this.repository.save(categoria1);
        this.repository.save(categoria2);
        this.repository.save(categoria3);
        this.repository.save(categoria4);
    	
    	
//    	this.gastosRepository.save(new Gastos( "Joe Biden",Double.valueOf("10.00"), new Date(),categoria1));
//    	this.gastosRepository.save(new Gastos( "President Obama",Double.valueOf("10.00"), new Date(),categoria2));
//    	this.gastosRepository.save(new Gastos( "Crystal Mac",Double.valueOf("10.00"), new Date(),categoria3));
//    	this.gastosRepository.save(new Gastos( "James Henry",Double.valueOf("10.00"), new Date(),categoria4));
    }
}