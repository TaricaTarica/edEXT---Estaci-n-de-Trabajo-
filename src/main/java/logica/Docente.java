package logica;

import java.time.LocalDate;
import java.util.ArrayList;

public class Docente extends Usuario {
	private Instituto instituto;
	private ArrayList<Edicion> edicionesAsociadas = new ArrayList<>();

	
	//constructores
	public Docente() {}
	public Docente(String nickname, String nombre, String apellido, String correo, LocalDate fechaNac, Instituto instituto) {
		super(nickname, nombre, apellido, correo, fechaNac);
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
	}
	
	public ArrayList<Edicion> obtenerEdicionesAsociados(){
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
