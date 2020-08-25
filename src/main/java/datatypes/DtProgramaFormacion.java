package datatypes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import logica.Curso;

public class DtProgramaFormacion {
	private String nombre;
	private String descripcion;
	private Date fechaIni;
	private Date fechaFin;
	
	private List<Curso> cursos = new ArrayList<>();

	//constructores
	public DtProgramaFormacion() {}
	public DtProgramaFormacion(String nombre, String descripcion, Date fechaIni, Date fechaFin) {
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
