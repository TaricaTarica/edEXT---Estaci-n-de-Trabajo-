package datatypes;

import java.time.LocalDate;
import java.util.Date;

public class DtEstudiante extends DtUsuario {
	public DtEstudiante() {}
	public DtEstudiante(String nickname, String nombre, String apellido, String correo, LocalDate fechaNac, String contrasenia) {
		super(nickname, nombre, apellido, correo, fechaNac, contrasenia);
	}
}
