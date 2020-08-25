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

	
	
	
	
}
