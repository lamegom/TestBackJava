package teste.springboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Categoria {

	public Categoria() {}
	
	public Categoria( String nome) {
		super();
		this.nome = nome;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	public Long id;
	
	public String nome;
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria")
//    private List<Gastos> gastos;
//	
//	
//
//	public List<Gastos> getGastos() {
//		return gastos;
//	}
//
//	public void setGastos(List<Gastos> gastos) {
//		this.gastos = gastos;
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}
