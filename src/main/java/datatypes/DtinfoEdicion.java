package datatypes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtinfoEdicion {
	private String nombre;
	private Calendar fechaInicio;
	private Calendar fechaFin;
	private int cupo;
	private Calendar fechaPub;
	
	
	//constructores
	public DtinfoEdicion() {}
	public DtinfoEdicion(final String nombre, final Calendar fechaIni, final Calendar fechaFin, final int cupo, final Calendar fechaPub) {
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
	public Calendar getfechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Calendar fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Calendar getfechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Calendar fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Calendar getFechaPub() {
		return fechaPub;
	}
	public void setFechaPub(Calendar fechaPub) {
		this.fechaPub = fechaPub;
	}
}
