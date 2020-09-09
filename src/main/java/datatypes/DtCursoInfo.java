package datatypes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DtCursoInfo {
	private String nombre;
	private String descripcion;
	private String duracion;
	private int cantHoras;
	private int creditos;
	private LocalDate fechaAlta;
	private String url;
	
	private List<DtProgramaFormacion> programas = new ArrayList<>();
	private List<DtEdicion> ediciones = new ArrayList<>();
	
	//constructores
	public DtCursoInfo() {}
	public DtCursoInfo(String nombre, String descripcion, String duracion, int cantHoras, int creditos, LocalDate fechaAlta,
			String url) {
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
	public LocalDate getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
