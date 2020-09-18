package persistencia;

import java.io.Serializable;

//No es una entidad, debe implementar serializable
public class InscripcionPFID implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String estudiante;
	private String programaformacion;
	
	//Tiene que tener constructor por defecto
	public InscripcionPFID() {
		super();
	}


	//Tiene que implementar los getters y setters

	public String getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(String estudiante) {
		this.estudiante = estudiante;
	}
	public String getProgmaFormacion() {
		return programaformacion;
	}

	public void setProgmaFormacion(String programaformacion) {
		this.programaformacion = programaformacion;
	}
	
	//Tiene  que tener los m�todos hashCode y equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estudiante == null) ? 0 : estudiante.hashCode());
		result = prime * result + ((programaformacion == null) ? 0 : programaformacion.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InscripcionPFID other = (InscripcionPFID) obj;
		if (programaformacion == null) {
			if (other.programaformacion != null)
				return false;
		} else if (!programaformacion.equals(other.programaformacion))
			return false;
		if (estudiante == null) {
			if (other.estudiante != null)
				return false;
		} else if (!estudiante.equals(other.estudiante))
			return false;
		return true;
	}
	
}
