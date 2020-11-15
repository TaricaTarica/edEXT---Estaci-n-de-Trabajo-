package logica;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import persistencia.InscripcionPFID;

@Entity
@IdClass(InscripcionPFID.class)
public class InscripcionPF {
	@Id
	@ManyToOne
	@JoinColumn(
			insertable=false,
			updatable=false
	)
	private Estudiante estudiante; //hay que cargar esto en la alta inscripcion
	@Id
	@ManyToOne
	@JoinColumn
	private ProgramaFormacion programaformacion;
	
	private LocalDate fecha;
	
	//constructores
	public InscripcionPF() {
		super();
	}
	public InscripcionPF(LocalDate fecha,Estudiante estudiante,ProgramaFormacion programaformacion){

		this.fecha = fecha;
		this.programaformacion = programaformacion;
		this.estudiante = estudiante;
	}
	
	//getters-setters
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public ProgramaFormacion getProgramaFormacion() {
		return programaformacion;
	}
	public void setProgramaFormacion(ProgramaFormacion programaformacion) {
		this.programaformacion = programaformacion;
	}
	
	
	
}
