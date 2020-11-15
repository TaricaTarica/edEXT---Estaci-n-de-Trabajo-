package datatypes;


import java.util.Calendar;

//import logica.Docente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtEdicion {
	private String nombre;
	private Calendar fechaInicio;
	private Calendar fechaFin;
	private int cupo;
	private Calendar fechaPub;

	
	//constructores
	public DtEdicion() {}
	public DtEdicion(final String nombre,final Calendar fechaIni,final Calendar fechaFin,final int cupo,final Calendar fechaPub) {
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
	public Calendar getFechaIni() {
		return fechaInicio;
	}
	public void setFechaIni(Calendar fechaIni) {
		this.fechaInicio = fechaIni;
	}
	public Calendar getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Calendar fechaFin) {
		this.fechaFin = fechaFin;
	}
	public int getCupo() {
		return cupo;
	}
	public void setCupo(int cupo) {
		this.cupo = cupo;
	}
	public Calendar getFechaPub() {
		return fechaPub;
	}
	public void setFechaPub(Calendar fechaPub) {
		this.fechaPub = fechaPub;
	}

}
