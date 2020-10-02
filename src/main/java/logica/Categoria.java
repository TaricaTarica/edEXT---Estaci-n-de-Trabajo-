package logica;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import datatypes.DtCurso;

@Entity
public class Categoria {
	@Id
	private String nombre;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Curso> cursos = new ArrayList<>();
		//constructores
		public Categoria() {
			super();
		}
		public Categoria(String nombre) {
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
		public ArrayList<String> obtenerCursos(){
			ArrayList<String> lista = new ArrayList<>();
			for(Curso c:cursos) {
				lista.add(c.getNombre());
			}
			return lista;
		}
		
		public void agregarCurso(Curso c) {
			cursos.add(c);
			
		}
		
		public ArrayList<String> obtenerCategoriaCursos(){
			//Retorna nombreInstituto-nombreCurso, por ejemplo BUCEO-BD2
			ArrayList<String> lista = new ArrayList<>();
			String nombreCategoriaCurso;
			for (Curso c: cursos) {
				nombreCategoriaCurso = this.nombre+"-"+c.getNombre();
				lista.add(nombreCategoriaCurso);
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
