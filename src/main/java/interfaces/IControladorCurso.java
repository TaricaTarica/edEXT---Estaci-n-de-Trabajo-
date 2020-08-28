package interfaces;

import java.util.Date;

import logica.Curso;

public interface IControladorCurso {
	/*IR AGREGANDO LAS OPERACIONES A MEDIDA QUE SE REQUIERAN/IMPLEMENTEN*/
	
	/*Alta Instituto*/
	public void ingresarInstituto(String nombreInstituto);
	public void AltaInstituto();
	
	/*Alta ProgramaFormacion*/
	public void IngresarProgFormacion(String nombre, String descripcion, Date fechaInicio, Date fechaFin, Date fechaAlta);
	public void AltaProgramaFormacion();
	
	/*Alta Curso*/
	public void IngresarCurso(String nInstituto, String nombre, String desc, String duracion, int horas, int creditos, Date fechaR, String url);
	public void ingresarPrevia(String NombrePrevia);
	public void agregarPrevia(String nombrePrevia);
	public void AltaCurso();
}
