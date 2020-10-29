package logica;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;

import datatypes.EstadoInscripcion;
import persistencia.InscripcionEdID;

@Entity
@IdClass(InscripcionEdID.class)
public class InscripcionEd {
	@Id
	@ManyToOne
	@JoinColumn(
			insertable=false,
			updatable=false
	)
	private Estudiante estudiante;
	@Id
	@ManyToOne
	@JoinColumn(
			insertable=false,
			updatable=false
	)
	private Edicion edicion;
	
	private LocalDate fecha;
	private EstadoInscripcion estado;
	
	//constructores
	public InscripcionEd() {
		super();
	}
	public InscripcionEd(LocalDate fecha, Edicion edicion, Estudiante estudiante,EstadoInscripcion estado) {

		this.fecha = fecha;
		this.edicion = edicion;
		this.estudiante = estudiante;
		this.estado = estado;
	}
	
	//getters-setters
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public Edicion getEdicion() {
		return edicion;
	}
	public void setEdicion(Edicion edicion) {
		this.edicion = edicion;
	}
	public Estudiante getEstudiante() {
		return estudiante;
	}
	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}
	public EstadoInscripcion getEstado() {
		return estado;
	}
	public void setEstadoInscripcion(EstadoInscripcion estado) {
		this.estado = estado;
	}
	
	
	
}
