package logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import datatypes.DtEdicion;

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
		this.fechaIni = dte.getFechaIni();
		this.fechaFin = dte.getFechaFin();
		this.cupo = dte.getCupo();
		this.fechaPub = dte.getFechaPub();
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
	}
	
	public String nombresDocentes() {
		String retorno = new String();
		for(Docente doc: this.docentes) {
			retorno = retorno + " - " + doc.getNickname();
		}
		return retorno;
	}

}
