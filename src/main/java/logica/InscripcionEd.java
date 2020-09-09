package logica;

import java.time.LocalDate;
import java.util.Date;

public class InscripcionEd {
	private LocalDate fecha;
	private Edicion edicion;
	
	//constructores
	public InscripcionEd() {}
	public InscripcionEd(LocalDate fecha, Edicion edicion) {

		this.fecha = fecha;
		this.edicion = edicion;
	}
	
	//getters-setters
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public Edicion getEdicion() {
		return edicion;
	}
	public void setEdicion(Edicion edicion) {
		this.edicion = edicion;
	}
	
	
	
}
