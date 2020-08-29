package datatypes;

import java.util.ArrayList;
import java.util.List;

import datatypes.DtCurso;

public class DtInstituto {
	private String nombre;
	private List<DtCurso> cursos = new ArrayList<>();
	
	public DtInstituto() {}
	public DtInstituto(String nombre) {
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
