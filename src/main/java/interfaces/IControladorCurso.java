package interfaces;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import excepciones.EdicionRepatida_Exception;
import excepciones.InscripcionRepetidaPF_Exception;
import excepciones.InscripcionRepetida_Exception;
import excepciones.InstitutoRepetido_Exception;
import excepciones.ProgramaCursoRepetido_Exception;
import logica.InscripcionEd;
import excepciones.CategoriaRepetida_Exception;
import excepciones.CrearProgramaFormacionRepetido_Exception;
import excepciones.CursoRepetido_Exception;
import datatypes.DtCurso;
import datatypes.DtEdicion;
import datatypes.DtInscripcionEd;
import datatypes.DtProgramaFormacion;
import datatypes.DtinfoEdicion;
import datatypes.EstadoInscripcion;
import datatypes.DtCursoInfo;


public interface IControladorCurso {
	/*IR AGREGANDO LAS OPERACIONES A MEDIDA QUE SE REQUIERAN/IMPLEMENTEN*/
	
	/*Alta Instituto*/
	public void AltaInstituto(String nombreInstituto) throws InstitutoRepetido_Exception;
	
	/*Alta ProgramaFormacion*/
	//public void IngresarProgFormacion(String nombre, String descripcion, Date fechaInicio, Date fechaFin, Date fechaAlta);
	public void AltaCrearProgramadeFormacion(DtProgramaFormacion pf) throws CrearProgramaFormacionRepetido_Exception;
	
	/*Alta Curso*/
	public void IngresarCurso(String nInstituto, String nombre, String desc, String duracion, int horas, int creditos, Date fechaR, String url);
	public void ingresarPrevia(String NombrePrevia);
	public void agregarPrevia(String nombrePrevia, String nombreInstituto, String nombreCurso);
	public void agregarCategorias(String nombreCategoria, String nombreInstituto, String nombreCurso);
	//public void AltaCurso();
	public void AltaCurso(DtCurso c, String i) throws CursoRepetido_Exception;
	
	/*Agregar Curso Programa Formaci�n*/
	public void agregarCursoProgFormacion(String programaFormacion, String curso) throws ProgramaCursoRepetido_Exception;
	public void agregarCursoProgFormacion2(String programaFormacion, String curso, String nombreInstituto) throws ProgramaCursoRepetido_Exception;
	
	
	/*Consulta Curso*/
	public DtCursoInfo ConsultaCurso(String strInstituto, String strCurso);
	public DtCursoInfo ConsultaCursoCategoria(String nombreCategoria, String nombreCurso);
	public String[] AtributosPrograma(String nombrePrograma);
	public String[] listarPrevias(String nombreInstituto, String nombreCurso);

	
	/*Consulta Programa*/
	public DtProgramaFormacion ConsultaProgramaFormacion(String strPrograma);
	
	/*Alta de Edicion de Curso*/
	public void AltadeEdiciondeCurso(DtEdicion e,String i,String c)throws EdicionRepatida_Exception;
	public DtinfoEdicion ConsultaEdicion(String strInstituto, String strCurso,String strEdicion);
	public DtinfoEdicion ConsultaEdicionCategoria(String nombreCategoria, String nombreCurso,String strEdicion);
	//public Edicion obtenerEdicion(); // FALTA IMPLEMENTAR EN EL CONTROLADOR
	
	/*Inscripcion a Edicion de Curso*/
	public void InscripcionaEdiciondeCurso(String i,Date FechaIns,String nickname,String c,String e,String estado) throws InscripcionRepetida_Exception;
	public void asociarEdicion(String strDocente, DtEdicion edicion, String strInstituto, String strCurso);
	
	/*Inscripcion a Programa de Formacion*/
	public void InscripcionaProgramaFormacion(Date FechaIns,String nickname,String pf) throws InscripcionRepetidaPF_Exception;

	/*Alta Categor�a*/
	void AltaCategoria(String nombreCategoria) throws CategoriaRepetida_Exception;
	 
	
	/*MULTIUSO*/
	public String[] listarInstitutos();
	public String[] listarCursos(String strInstituto);
	public String[] listarCursosCategoria(String nombreCategoria);
	public String[] listarEdiciones(String strInstituto, String strCurso);
	public String[] listarProgramas();
	public String[] listarProgramasAux(String strInstituto, String strCurso);
	public String[] listarDocentes(String Instituto);
	public String[] listarEstudiantes();
	public String[] listarEdicionesCategoria(String strCategoria, String strCurso);
	
	public String[] listarInstitutoCursos(); //Esta funci�n lista todos los cursos existentes en el sistema
	public String[] listarProgFormacion();
	public String[] ListarEdicionesCurso(String nombreInstituto, String nombreCurso);
	public String[] AtributosEdicion(String nombreInstituto, String nombreCurso, String nombreEdicion);
	public String[] listarCursosP(String strPrograma);

	public String[] listarCategorias();

	public String[] listarCategoriasC(String nombreInstituto, String nombreCurso);

	public String[] listarCursosCategoriasP(String strPrograma);

	public void seleccionarestadoInscripcion(String nombreInstituto, String nombreCurso, String nombreEdicion, String nicknameEstudiante, String estado);

	public String obtenerInstitutoCurso(String nombreCurso);

	public List<DtInscripcionEd> obtenerInscripcionesEd(String nombreInstituto, String nombreCurso, String nombreEdicion);

	public String[] listarInscripcionesAceptadas(String nombreInstituto,String nombreCurso,String nombreEdicion);
	
	public String getInstitutoDocente(String nickname);

	




	

}
