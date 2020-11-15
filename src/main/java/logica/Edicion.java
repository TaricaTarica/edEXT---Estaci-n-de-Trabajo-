package logica;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import datatypes.DtEdicion;
import persistencia.Conexion;

@Entity
public class Edicion {
	@Id
	private String nombre;
	private LocalDate fechaIni;
	private LocalDate fechaFin;
	private int cupo;
	private LocalDate fechaPub;
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Docente> docentes = new ArrayList<>();
	@OneToMany(mappedBy="edicion",cascade=CascadeType.ALL,orphanRemoval=true) 
	private List<InscripcionEd> inscripciones = new ArrayList<>(); //hay que cargar esto en la alta inscripcion
	
	//constructores
	
	// CONSTRUCTOR CON ATRIBUTOS
	public Edicion() {}
	/*public Edicion(String nombre, Date fechaIni, Date fechaFin, int cupo, Date fechaPub, Docente docente) {
		this.nombre = nombre;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
		this.cupo = cupo;
		this.fechaPub = fechaPub;
		this.docente = docente;
	}*/
	
	// CONSTRUCTOR CON DATA TYPE
	public Edicion(DtEdicion dte) {
		super();
		this.nombre = dte.getNombre();
		Calendar calendarIni = dte.getFechaIni();
		LocalDate fechaLocalDateIni = LocalDateTime.ofInstant(calendarIni.toInstant(), calendarIni.getTimeZone().toZoneId()).toLocalDate();
		this.fechaIni = fechaLocalDateIni;
		Calendar calendarFin = dte.getFechaFin();
		LocalDate fechaLocalDateFin = LocalDateTime.ofInstant(calendarFin.toInstant(), calendarFin.getTimeZone().toZoneId()).toLocalDate();
		this.fechaFin = fechaLocalDateFin;
		this.cupo = dte.getCupo();
		Calendar calendarPub = dte.getFechaPub();
		LocalDate fechaLocalDatePub = LocalDateTime.ofInstant(calendarPub.toInstant(), calendarPub.getTimeZone().toZoneId()).toLocalDate();
		this.fechaPub = fechaLocalDatePub;
	}
	
	//getters-setters
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public LocalDate getFechaIni() {
		return fechaIni;
	}
	public void setFechaIni(LocalDate fechaIni) {
		this.fechaIni = fechaIni;
	}
	public LocalDate getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	public int getCupo() {
		return cupo;
	}
	public void setCupo(int cupo) {
		this.cupo = cupo;
	}
	public LocalDate getFechaPub() {
		return fechaPub;
	}
	public void setFechaPub(LocalDate fechaPub) {
		this.fechaPub = fechaPub;
	}
	
	public List<Docente> getDocentes() {
		return docentes;
	}
	public void setDocente(Docente docente) {
		this.docentes.add(docente);
		/*Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		em.getTransaction().begin();
		em.persist(docente);
		em.getTransaction().commit();*/
	}
	
	public String nombresDocentes() {
		String retorno = new String();
		for(Docente doc: this.docentes) {
			retorno = retorno + " - " + doc.getNickname();
		}
		return retorno;
	}
	public void agregarInscripcion(InscripcionEd inscripcion){
		
		inscripciones.add(inscripcion);	
	}
	public List<InscripcionEd> getInscripciones(){
		if(!inscripciones.isEmpty()){
			return inscripciones;
		}else {
			return null;
		}
	}
	public String nombresDocente() {
		String retorno = new String();
		for(Docente doc: this.docentes) {
			retorno = doc.getNickname();
		}
		return retorno;
	}

}
