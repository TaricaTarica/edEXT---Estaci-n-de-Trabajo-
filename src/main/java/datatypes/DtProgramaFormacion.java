package datatypes;

//import java.util.ArrayList;
import java.time.LocalDate;
//import java.util.List;

//import logica.Curso;

public class DtProgramaFormacion {
	private String nombre;
	private String descripcion;
	//private String fechaInicio;
	private LocalDate fechaInicio;
	//private String fechaFin;
	private LocalDate fechaFin;
	//private String fechaAlta;
	private LocalDate fechaAlta;


	
	//private List<Curso> cursos = new ArrayList<>();

	//constructores
	public DtProgramaFormacion(String nombre, String descripcion, LocalDate fechaInicio, LocalDate fechaFin, LocalDate fechaAlta) {
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
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaIni(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalDate getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	public LocalDate getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
}
