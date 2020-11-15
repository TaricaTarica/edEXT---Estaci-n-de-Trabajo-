package datatypes;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtEstudiante extends DtUsuario {
	public DtEstudiante() {}
	public DtEstudiante(String nickname, String nombre, String apellido, String correo, Calendar fechaNac, String contrasenia) {
		super(nickname, nombre, apellido, correo, fechaNac, contrasenia);
	}
}
