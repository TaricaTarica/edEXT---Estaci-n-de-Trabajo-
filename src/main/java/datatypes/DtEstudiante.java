package datatypes;

import java.util.Date;

public class DtEstudiante extends DtUsuario {
	public DtEstudiante() {}
	public DtEstudiante(String nickname, String nombre, String apellido, String correo, Date fechaNac) {
		super(nickname, nombre, apellido, correo, fechaNac);
	}
}
