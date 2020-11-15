package datatypes;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import logica.InscripcionEd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtInscripcionEd {
	private String nombreEdicion;
	private String nicknameEstudiante;
	private Calendar fecha;
	private EstadoInscripcion estado;
	
	public DtInscripcionEd(final String nombreEdicion, final String nicknameEstudiante, final Calendar fecha) {
		this.nombreEdicion = nombreEdicion;
		this.nicknameEstudiante = nicknameEstudiante;
		this.fecha = fecha;
	}

	public DtInscripcionEd() {
		
	}
	
	public DtInscripcionEd(InscripcionEd ied) {
		this.nombreEdicion = ied.getEdicion().getNombre();
		this.nicknameEstudiante = ied.getEstudiante().getNickname();
		Calendar fechaCalendar = GregorianCalendar.from(ied.getFecha().atStartOfDay(ZoneId.systemDefault()));
		this.fecha = fechaCalendar;
		this.estado = ied.getEstado();
	}

	public String getNombreEdicion() {
		return nombreEdicion;
	}

	public void setNombreEdicion(String nombreEdicion) {
		this.nombreEdicion = nombreEdicion;
	}

	public String getNicknameEstudiante() {
		return nicknameEstudiante;
	}

	public void setNicknameEstudiante(String nicknameEstudiante) {
		this.nicknameEstudiante = nicknameEstudiante;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	public EstadoInscripcion getEstado() {
		return estado;
	}

	public void setEstado(EstadoInscripcion estado) {
		this.estado = estado;
	}
	
}

