package logica;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import datatypes.EstadoInscripcion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@Entity
public class Estudiante extends Usuario {
	@OneToMany(mappedBy="estudiante",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<InscripcionEd> inscripcionesEd = new ArrayList<>();
	@OneToMany(mappedBy="estudiante",cascade=CascadeType.ALL,orphanRemoval=true) 
	private List<InscripcionPF> inscripcionespf = new ArrayList<>(); 

	
	//constructores
	public Estudiante() {
		super();
	}
	public Estudiante(String nickname, String nombre, String apellido, String correo, LocalDate fechaNac, String contrasenia) {
		super(nickname, nombre, apellido, correo, fechaNac, contrasenia);
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
	public List<Edicion> obtenerEdicionesEd(){
		List<Edicion> ediciones = new ArrayList<>();
        for( InscripcionEd ied:inscripcionesEd) {
        	ediciones.add(ied.getEdicion());
        }
        return ediciones;	
	}
	
	public void agregarInscripcionPF(InscripcionPF inscripcion){
		inscripcionespf.add(inscripcion);	
	}
	public boolean BuscarInscripcionPF(ProgramaFormacion pf) {;
	boolean aretornar=false;
		for(InscripcionPF i: inscripcionespf) {
			if (i.getProgramaFormacion().equals(pf))
				aretornar=true;
		}
		return aretornar;
	}
	public String[] obtenerProgramasE(){
		String[] programas_ret = new String[this.inscripcionespf.size()];
        int i=0;
        for( InscripcionPF ipf: this.inscripcionespf) {
        	programas_ret[i]=ipf.getProgramaFormacion().getNombre();
        	i++;
        }
        return programas_ret;	
	}
	public String[] obtenerEdicionesEst(){
		String[] ediciones_ret = new String[this.inscripcionesEd.size()];
        int i=0;
        EstadoInscripcion inscripto=EstadoInscripcion.Inscripto;
        EstadoInscripcion aceptado=EstadoInscripcion.Aceptado;
        for( InscripcionEd ied: this.inscripcionesEd) {
        	if(ied.getEstado().equals(inscripto)|| ied.getEstado().equals(aceptado)) {
        	ediciones_ret[i]=ied.getEdicion().getNombre();
        	i++;
        	}
        }
        return ediciones_ret;	
	}
	public Edicion ObtenerEdicion(String strEdicion) {
		Edicion e=new Edicion();
        int i=0;
        for( InscripcionEd ied: this.inscripcionesEd) {
        	if(ied.getEdicion().getNombre().equals(strEdicion)) {
        		e=ied.getEdicion();
        	}	
        }
        return e;		 
	}
}
