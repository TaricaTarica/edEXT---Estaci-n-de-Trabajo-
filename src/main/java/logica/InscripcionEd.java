package logica;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


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
	private Estudiante estudiante; //hay que cargar esto en la alta inscripcion
	@Id
	@ManyToOne
	@JoinColumn
	private Edicion edicion;
	
	private Date fecha;
	
	//constructores
	public InscripcionEd() {
		super();
	}
	public InscripcionEd(Date fecha, Edicion edicion, Estudiante estudiante) {

		this.fecha = fecha;
		this.edicion = edicion;
		this.estudiante = estudiante;
	}
	
	//getters-setters
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Edicion getEdicion() {
		return edicion;
	}
	public void setEdicion(Edicion edicion) {
		this.edicion = edicion;
	}
	
	
	
}
