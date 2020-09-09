package logica;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

public class Estudiante extends Usuario {
	private List<InscripcionEd> inscripcionesEd = new ArrayList<>();
	
	//constructores
	public Estudiante() {}
	public Estudiante(String nickname, String nombre, String apellido, String correo, LocalDate fechaNac) {
		super(nickname, nombre, apellido, correo, fechaNac);
	}
	
	//operaciones
/*	@Override 
	public DtUsuario getinfoUsuario() {
		return DtUsuario
	}
	public DtEstudiante infoEstudiante(){
		return DtEstudiante
	}*/
	
	public void agregarInscripcion(LocalDate FechaIns,Edicion e){
		InscripcionEd i = new InscripcionEd(FechaIns,e);
		inscripcionesEd.add(i);	
	}
	public boolean BuscarInscripcion(Edicion ed) {;
	boolean aretornar=false;
	for(InscripcionEd i: inscripcionesEd) {
		if (i.getEdicion().equals(ed))
			aretornar=true;
	}
	return aretornar;
	}
	
}
