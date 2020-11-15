package datatypes;

import java.time.LocalDate;
import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtCurso {
	private String nombre;
	private String descripcion;
	private String duracion;
	private int horas;
	private int creditos;
	private Calendar fechaAlta; 
	private String url;
	private String img;
	private String nombreInst;
	
	//constructores
	public DtCurso() {}
	public DtCurso(final String nombre, final String descripcion, final String duracion, final int horas, final int creditos, final Calendar fechaAlta,
			final String url, final String nombreInst) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.duracion = duracion;
		this.horas = horas;
		this.creditos = creditos;
		this.fechaAlta = fechaAlta;
		this.url = url;
		this.nombreInst = nombreInst;
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
	public String getDuracion() {
		return duracion;
	}
	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}
	public int gethoras() {
		return horas;
	}
	public void sethoras(int horas) {
		this.horas = horas;
	}
	public int getcreditos() {
		return creditos;
	}
	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}
	public Calendar getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Calendar fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getImg() {
		return this.img;
	}
	public String getnombreInst() {
		return this.nombreInst;
	}
}
