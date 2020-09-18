package logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import persistencia.Conexion;

@Entity
public class Docente extends Usuario {
	@ManyToOne
	@JoinColumn
	private Instituto instituto;
	@ManyToMany(mappedBy = "docentes")
	private List<Edicion> edicionesAsociadas = new ArrayList<>();

	//constructores
	public Docente() {
		super();
	}
	public Docente(String nickname, String nombre, String apellido, String correo, LocalDate fechaNac, String contrasenia) {
		super(nickname, nombre, apellido, correo, fechaNac, contrasenia);
	}
	
	//getters-setters
	public Instituto getInstituto() {
		return this.instituto;
	}
	public void setInstituto(Instituto instituto) {
		this.instituto = instituto;
	}
	
	//Métodos para ediciones asociados
	public void asociarEdicion(Edicion edicion){
		this.edicionesAsociadas.add(edicion);
		/*Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		em.getTransaction().begin();
		em.persist(edicion);
		em.getTransaction().commit();*/
	}
	
	public List<Edicion> obtenerEdicionesAsociados(){
		return this.edicionesAsociadas;
	}

	//operaciones
/*	@Override 
	public DtUsuario getinfoUsuario() {
		return DtUsuario;
	}

	public DtDocente infoDocente(){
		return DtDocente;
	}
*/
}
