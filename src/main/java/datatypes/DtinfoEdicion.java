package datatypes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DtinfoEdicion {
	private String nombre;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private int cupo;
	private LocalDate fechaPub;
	
	
	//constructores
	public DtinfoEdicion() {}
	public DtinfoEdicion(String nombre, LocalDate fechaIni, LocalDate fechaFin, int cupo, LocalDate fechaPub) {
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
	public int getCupo() {
		return cupo;
	}
	public void setCupo(int cupo) {
		this.cupo = cupo;
	}
	public LocalDate getfechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalDate getfechaFin() {
		return fechaFin;
	}
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	public LocalDate getFechaPub() {
		return fechaPub;
	}
	public void setFechaPub(LocalDate fechaPub) {
		this.fechaPub = fechaPub;
	}
}
