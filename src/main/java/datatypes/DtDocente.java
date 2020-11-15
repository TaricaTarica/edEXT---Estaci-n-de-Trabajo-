package datatypes;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtDocente extends DtUsuario {
private DtInstituto instituto;
	
	//constructores
	public DtDocente() {}
	public DtDocente(String nickname, String nombre, String apellido, String correo, Calendar fechaNac, String contrasenia) {
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
