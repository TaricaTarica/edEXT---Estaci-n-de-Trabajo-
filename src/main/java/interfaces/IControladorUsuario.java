package interfaces;

import java.util.Date;

public interface IControladorUsuario {
	/*IR AGREGANDO LAS OPERACIONES A MEDIDA QUE SE REQUIERAN-IMPLEMENTEN*/
	
	/*Alta Usuario*/
	public void ingresarUsuario(String nickname, String nombre, String apellido, String correo, Date fNac);
	public void ingresarInstituto(String nombreInstituto);
	public void confirmarAlta();
}
