package logica;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class ProgramaFormacion {
	private String nombre;
	private String descripcion;
	private Date fechaIni;
	private Date fechaFin;
	
	private List<Curso> cursos = new ArrayList<>();

	//constructores
	public ProgramaFormacion() {}
	public ProgramaFormacion(String nombre, String descripcion, Date fechaIni, Date fechaFin) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
	}
	
	//getters-setters
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFechaIni() {
		return fechaIni;
	}
	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}	
}
