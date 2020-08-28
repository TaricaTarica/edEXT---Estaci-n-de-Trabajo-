package logica;

import java.util.List;
import java.util.ArrayList;

public class Instituto {
	private String nombre;
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

	
	
	
	
}
