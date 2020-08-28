package interfaces;

import java.util.Date;

import excepciones.InstitutoRepetido_Exception;
import logica.Curso;


public interface IControladorCurso {
	/*IR AGREGANDO LAS OPERACIONES A MEDIDA QUE SE REQUIERAN/IMPLEMENTEN*/
	
	/*Alta Instituto*/
	public void AltaInstituto(String nombreInstituto) throws InstitutoRepetido_Exception;
	
	/*Alta ProgramaFormacion*/
	public void IngresarProgFormacion(String nombre, String descripcion, Date fechaInicio, Date fechaFin, Date fechaAlta);
	public void AltaProgramaFormacion();
	
	/*Alta Curso*/
	public void IngresarCurso(String nInstituto, String nombre, String desc, String duracion, int horas, int creditos, Date fechaR, String url);
	public void ingresarPrevia(String NombrePrevia);
	public void agregarPrevia(String nombrePrevia);
	public void AltaCurso();
}
