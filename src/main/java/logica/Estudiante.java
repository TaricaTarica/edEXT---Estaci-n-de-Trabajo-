package logica;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@Entity
public class Estudiante extends Usuario {
	@OneToMany(mappedBy="estudiante",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<InscripcionEd> inscripcionesEd = new ArrayList<>();
	
	//constructores
	public Estudiante() {
		super();
	}
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
	
	public void agregarInscripcion(InscripcionEd inscripcion){
		
		inscripcionesEd.add(inscripcion);	
	}
	public boolean BuscarInscripcion(Edicion ed) {;
	boolean aretornar=false;
	for(InscripcionEd i: inscripcionesEd) {
		if (i.getEdicion().equals(ed))
			aretornar=true;
	}
	return aretornar;
	}
	public String[] obtenerEdicionesE(){
		String[] ediciones_ret = new String[this.inscripcionesEd.size()];
        int i=0;
        for( InscripcionEd ied: this.inscripcionesEd) {
        	ediciones_ret[i]=ied.getEdicion().getNombre();
        	i++;
        }
        return ediciones_ret;	
	}
	
}
