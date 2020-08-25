package logica;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Estudiante extends Usuario {
	private List<InscripcionEd> inscripcionesEd = new ArrayList<>();
	
	//constructores
	public Estudiante() {}
	public Estudiante(String nickname, String nombre, String apellido, String correo, Date fechaNac) {
		super(nickname, nombre, apellido, correo, fechaNac);
	}
	
	//operaciones
/*	@Override 
	public DtUsuario getinfoUsuario() {
		return DtUsuario
	}
	public DtEstudiante infoEstudiante(){
		return DtEstudiante
	}
	public void agregarInscripcion(IscripcionEd ied){}
*/	
	
}
