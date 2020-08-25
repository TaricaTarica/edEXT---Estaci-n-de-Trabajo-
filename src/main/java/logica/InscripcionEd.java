package logica;

import java.util.Date;

public class InscripcionEd {
	private Date fecha;
	private Edicion edicion;
	
	//constructores
	public InscripcionEd() {}
	public InscripcionEd(Date fecha, Edicion edicion) {

		this.fecha = fecha;
		this.edicion = edicion;
	}
	
	//getters-setters
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Edicion getEdicion() {
		return edicion;
	}
	public void setEdicion(Edicion edicion) {
		this.edicion = edicion;
	}
	
	
	
}
