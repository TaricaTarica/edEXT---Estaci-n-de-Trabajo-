package datatypes;

import java.util.Date;

import logica.Docente;

public class DtEdicion {
	private String nombre;
	private Date fechaIni;
	private Date fechaFin;
	private int cupo;
	private Date fechaPub;
	private Docente docente;
	
	//constructores
	public DtEdicion() {}
	public DtEdicion(String nombre, Date fechaIni, Date fechaFin, int cupo, Date fechaPub, Docente docente) {
		this.nombre = nombre;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
		this.cupo = cupo;
		this.fechaPub = fechaPub;
		this.docente = docente;
	}
	
	//getters-setters
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	public int getCupo() {
		return cupo;
	}
	public void setCupo(int cupo) {
		this.cupo = cupo;
	}
	public Date getFechaPub() {
		return fechaPub;
	}
	public void setFechaPub(Date fechaPub) {
		this.fechaPub = fechaPub;
	}
	public Docente getDocente() {
		return docente;
	}
	public void setDocente(Docente docente) {
		this.docente = docente;
	}
}
