package interfaces;


import excepciones.UsuarioRepetido_Exception;
import datatypes.DtUsuario;


public interface IControladorUsuario {
	/*IR AGREGANDO LAS OPERACIONES A MEDIDA QUE SE REQUIERAN-IMPLEMENTEN*/
	
	/*Alta Usuario*/
	public void confirmarAlta(DtUsuario u) throws UsuarioRepetido_Exception;
	public void ingresarInstitutoDocente(String nombreInstituto);
	
	/*MULTIUSO*/
	public String[] listarInstitutos();
	
}
