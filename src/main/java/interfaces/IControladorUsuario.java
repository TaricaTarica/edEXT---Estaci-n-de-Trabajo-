package interfaces;


import excepciones.UsuarioRepetido_Exception;
import logica.Curso;
import logica.Edicion;
import logica.Instituto;

import java.time.LocalDate;
import java.util.List;

import datatypes.DtDocente;
import datatypes.DtEstudiante;
import datatypes.DtUsuario;


public interface IControladorUsuario {
	/*IR AGREGANDO LAS OPERACIONES A MEDIDA QUE SE REQUIERAN-IMPLEMENTEN*/
	
	/*Alta Usuario*/
	public void confirmarAlta(DtUsuario u) throws UsuarioRepetido_Exception;
	public void ingresarInstitutoDocente(String nombreInstituto);
	/*DIVIDO LA FUNCIÓN CONFIRMARALTA EN DOS*/
	public void confirmarAltaDocente(DtDocente u) throws UsuarioRepetido_Exception;
	public void confirmarAltaEstudiante(DtEstudiante u) throws UsuarioRepetido_Exception;
	
	/*Consulta Usuario*/
	public DtUsuario ConsultaUsuario(String strUsuario);
	
	/*Modificar Usuario*/
	public String getNombreUsuario(String nickname);
	public String getApellidoUsuario(String nickname);
	public String getContraseniaUsuario(String nickname);
	public LocalDate getFechaUsuario(String nickname);
	public void modificarUsuario(String nickname, String nombre, String apellido, LocalDate fechaN,String constrasenia);
	
	/*SEGUIR/DEJAR SEGUIR USUARIO*/
	public void seguirUsuario(String nickname, String aSeguir);
	public boolean esSeguidor(String nickname, String aComprobar);
	public void dejarSeguir(String nickname, String dejarSeguir);
	public List<DtUsuario> obtenerSeguidores(String nickname);
	public List<DtUsuario> obtenerSeguidos(String nickname);

	/*MULTIUSO*/
	public String[] listarInstitutos();
	public String[] listarUsuarios();
	boolean esEstudiante(String strNickname);
	String[] listarEdicionesD(String strDocente);
	String[] listarEdicionesE(String strEstudiante);
	public String[] AtributosEdicion(String nombreUsuario,String nombreEdicion);
	String[] listarProgramasE(String strEstudiante);
	public String[] AtributosPrograma(String nombrePrograma);
	public Instituto GetInstituto(String strDocente);
	public String GetCurso(String strEdicion,List<Curso> Curso);
	public String[] listarEdicionesEst(String strEstudiante);
	public Edicion ObtenerEdicion(String strEdicion,String strEstudiante);
	public boolean existeUsuario(String nickname);
	public List<Curso> getCursos(Instituto ins);
	public String nombresDocente(Edicion ed);
	
	

}
