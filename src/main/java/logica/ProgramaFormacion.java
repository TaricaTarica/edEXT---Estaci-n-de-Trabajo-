package logica;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import java.util.ArrayList;

import datatypes.DtCurso;
import datatypes.DtProgramaFormacion;

import java.util.ArrayList;

@Entity
public class ProgramaFormacion {
	@Id
	private String nombre;
	private String descripcion;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private LocalDate fechaAlta;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Curso> cursos = new ArrayList<>();

	//constructores
	public ProgramaFormacion() {}
	public ProgramaFormacion(DtProgramaFormacion dpf) {
		super();
		this.nombre =dpf.getNombre() ;
		this.descripcion = dpf.getDescripcion();
		this.fechaInicio = dpf.getFechaInicio();
		this.fechaFin = dpf.getFechaFin();
		this.fechaAlta = dpf.getFechaAlta();
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
	
	// Metodos para cursos
	public ArrayList<String> obtenerCursos(){
		ArrayList<String> lista = new ArrayList<>();
		for(Curso c:cursos) {
			lista.add(c.getNombre());
		}
		return lista;
	}
	
	public void agregarCurso(DtCurso dtc) {
		Curso c = new Curso(dtc);
		cursos.add(c);	
	}
	
	public void setCurso(Curso c) {
		this.cursos.add(c);
	}
	
	public Curso getCurso(String nombreCurso) {
		Curso retorno = new Curso();
		for(Curso c: this.cursos) {
			if(c.getNombre().equals(nombreCurso)) {
				retorno = c;
			} 
		}
		return retorno;
	}
}
