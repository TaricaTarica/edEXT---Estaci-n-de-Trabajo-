package datatypes;

import java.time.LocalDate;

public class DtDocente extends DtUsuario {
private DtInstituto instituto;
	
	//constructores
	public DtDocente() {}
	public DtDocente(String nickname, String nombre, String apellido, String correo, LocalDate fechaNac, String contrasenia) {
		super(nickname, nombre, apellido, correo, fechaNac, contrasenia);
	}
	
	//getters-setters
	public DtInstituto getInstituto() {
		return this.instituto;
	}
	public void setInstituto(DtInstituto instituto) {
		this.instituto = instituto;
	}
}
