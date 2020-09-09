package datatypes;

import java.time.LocalDate;

//import logica.Docente;

public class DtEdicion {
	private String nombre;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private int cupo;
	private LocalDate fechaPub;

	
	//constructores
	public DtEdicion() {}
	public DtEdicion(String nombre, LocalDate fechaIni, LocalDate fechaFin, int cupo, LocalDate fechaPub) {
		this.nombre = nombre;
		this.fechaInicio = fechaIni;
		this.fechaFin = fechaFin;
		this.cupo = cupo;
		this.fechaPub = fechaPub;
	}
	
	//getters-setters
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public LocalDate getFechaIni() {
		return fechaInicio;
	}
	public void setFechaIni(LocalDate fechaIni) {
		this.fechaInicio = fechaIni;
	}
	public LocalDate getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	public int getCupo() {
		return cupo;
	}
	public void setCupo(int cupo) {
		this.cupo = cupo;
	}
	public LocalDate getFechaPub() {
		return fechaPub;
	}
	public void setFechaPub(LocalDate fechaPub) {
		this.fechaPub = fechaPub;
	}

}
