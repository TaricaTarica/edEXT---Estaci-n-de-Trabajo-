package logica;

import datatypes.DtCurso;
import persistencia.Conexion;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.util.ArrayList;

@Entity
public class Instituto {
	@Id
	private String nombre;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Curso> cursos = new ArrayList<>();
	
	//constructores
	public Instituto() {}
	public Instituto(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	//getters-setters
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setCurso(Curso c) {
		this.cursos.add(c);
	}
	public List<Curso> getCursos(){
		List<Curso> cursosRetorno = new ArrayList<>();
		for(Curso c: this.cursos) {
			cursosRetorno.add(c);
		}
		return cursosRetorno;
	}
	public Curso getCurso(String nombre) {
		Curso retorno = new Curso();
		for(Curso c: this.cursos) {
			if(c.getNombre().equals(nombre)) {
				retorno = c;
			} 
		}
		return retorno;
	}
	public void setPreviaCurso(String nombreCurso, Curso previa) {
		Curso c = this.getCurso(nombreCurso);
		c.setPrevia(previa);
	}
	
	// Metodos para cursos
	public ArrayList<String> obtenerCursos(){
		ArrayList<String> lista = new ArrayList<>();
		for(Curso c:cursos) {
			lista.add(c.getNombre());
		}
		return lista;
	}
	
	public void agregarCurso(DtCurso dtc) {
		Curso c = new Curso(dtc);
		cursos.add(c);
		
		/*Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();*/
	}
	
	public ArrayList<String> obtenerInstitutoCursos(){
		//Retorna nombreInstituto-nombreCurso, por ejemplo BUCEO-BD2
		ArrayList<String> lista = new ArrayList<>();
		String nombreInstitutoCurso;
		for (Curso c: cursos) {
			nombreInstitutoCurso = this.nombre+"-"+c.getNombre();
			lista.add(nombreInstitutoCurso);
		}
		return lista;
	}
	public boolean existeCurso(String nombreCurso) {
		for(Curso c: this.cursos) {
			if(c.getNombre().equals(nombreCurso))
				return true;
		}
		return false;
	}
	
}
