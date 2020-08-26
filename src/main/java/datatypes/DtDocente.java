package datatypes;

import java.util.Date;

import logica.Instituto;

public class DtDocente extends DtUsuario {
private Instituto instituto;
	
	//constructores
	public DtDocente() {}
	public DtDocente(String nickname, String nombre, String apellido, String correo, Date fechaNac) {
		super(nickname, nombre, apellido, correo, fechaNac);
	}
	
	//getters-setters
	public Instituto getInstituto() {
		return this.instituto;
	}
	public void setInstituto(Instituto instituto) {
		this.instituto = instituto;
	}
}
