package logica;

import java.time.LocalDate;

public class Docente extends Usuario {
	private Instituto instituto;
	
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
