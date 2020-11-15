package datatypes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtCursoInfo {
	private String nombre;
	private String descripcion;
	private String duracion;
	private int cantHoras;
	private int creditos;
	private Calendar fechaAlta;
	private String url;
	private String img;
	
	private List<DtProgramaFormacion> programas = new ArrayList<>();
	private List<DtEdicion> ediciones = new ArrayList<>();
	
	//constructores
	public DtCursoInfo() {}
	public DtCursoInfo(final String nombre,final String descripcion, final String duracion, final int cantHoras, final int creditos, final Calendar fechaAlta,
			final String url) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.duracion = duracion;
		this.cantHoras = cantHoras;
		this.creditos = creditos;
		this.fechaAlta = fechaAlta;
		this.url = url;
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
	public int getCantHoras() {
		return cantHoras;
	}
	public void setCantHoras(int cantHoras) {
		this.cantHoras = cantHoras;
	}
	public int getCreditos() {
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
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
}
