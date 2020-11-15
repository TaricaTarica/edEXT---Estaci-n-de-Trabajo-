package datatypes;

//import java.util.ArrayList;
import java.time.LocalDate;
//import java.util.List;
import java.util.Calendar;

//import logica.Curso;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtProgramaFormacion {
	private String nombre;
	private String descripcion;
	//private String fechaInicio;
	private Calendar fechaInicio;
	//private String fechaFin;
	private Calendar fechaFin;
	//private String fechaAlta;
	private Calendar fechaAlta;


	
	//private List<Curso> cursos = new ArrayList<>();

	//constructores
	public DtProgramaFormacion(final String nombre, final String descripcion, final Calendar fechaInicio,final Calendar fechaFin, final Calendar fechaAlta) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.fechaAlta = fechaAlta;

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
	public Calendar getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaIni(Calendar fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Calendar getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Calendar fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Calendar getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Calendar fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
}
