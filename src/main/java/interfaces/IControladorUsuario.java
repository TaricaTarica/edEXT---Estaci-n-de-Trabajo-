package interfaces;


import excepciones.UsuarioRepetido_Exception;

import java.time.LocalDate;

import datatypes.DtUsuario;


public interface IControladorUsuario {
	/*IR AGREGANDO LAS OPERACIONES A MEDIDA QUE SE REQUIERAN-IMPLEMENTEN*/
	
	/*Alta Usuario*/
	public void confirmarAlta(DtUsuario u) throws UsuarioRepetido_Exception;
	public void ingresarInstitutoDocente(String nombreInstituto);
	
	/*Consulta Usuario*/
	public DtUsuario ConsultaUsuario(String strUsuario);
	
	/*Modificar Usuario*/
	public String getNombreUsuario(String nickname);
	public String getApellidoUsuario(String nickname);
	public LocalDate getFechaUsuario(String nickname);
	public void modificarUsuario(String nickname, String nombre, String apellido, LocalDate fechaN);

	/*MULTIUSO*/
	public String[] listarInstitutos();
	public String[] listarUsuarios();
	boolean esEstudiante(String strNickname);
	String[] listarEdicionesD(String strDocente);
	String[] listarEdicionesE(String strEstudiante);

}
