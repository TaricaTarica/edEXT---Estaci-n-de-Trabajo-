package publicadores;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

import configuraciones.WebServiceConfiguracion;
import datatypes.DtCurso;
import datatypes.DtCursoInfo;
import datatypes.DtEdicion;
import datatypes.DtInscripcionEd;
import datatypes.DtProgramaFormacion;
import datatypes.DtUsuario;
import datatypes.DtinfoEdicion;
import excepciones.CategoriaRepetida_Exception;
import excepciones.CrearProgramaFormacionRepetido_Exception;
import excepciones.CursoRepetido_Exception;
import excepciones.EdicionRepatida_Exception;
import excepciones.InscripcionRepetidaPF_Exception;
import excepciones.InscripcionRepetida_Exception;
import excepciones.InstitutoRepetido_Exception;
import excepciones.ProgramaCursoRepetido_Exception;
import excepciones.UsuarioRepetido_Exception;
import interfaces.Fabrica;
import interfaces.IControladorCurso;
import interfaces.IControladorUsuario;
import logica.Curso;
import logica.Edicion;
import logica.Instituto;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class ControladorCursoPublish {
	private Fabrica fabrica;
	private IControladorCurso icon;
	private WebServiceConfiguracion configuracion;
	private Endpoint endpoint;

	public ControladorCursoPublish() {
		fabrica = Fabrica.getInstancia();
		icon = fabrica.getIControladorCurso();
		try {
			configuracion = new WebServiceConfiguracion();
		} catch (Exception ex) {
			
		}
	}

	@WebMethod(exclude = true)
	public void publicar() {
		endpoint = Endpoint.publish("http://" + configuracion.getConfigOf("#WS_IP") + ":" + configuracion.getConfigOf("#WS_PORT") + "/controladorCurso", this);
		System.out.println("http://" + configuracion.getConfigOf("#WS_IP") + ":" + configuracion.getConfigOf("#WS_PORT") + "/controladorCurso");
	}
	
	@WebMethod(exclude = true)
	public Endpoint getEndpoint() {
        return endpoint;
	}
	
	//LOS MÉTODOS QUE VAMOS A PUBLICAR
	@WebMethod
	public void AltaInstituto(String nombreInstituto) throws InstitutoRepetido_Exception{
		icon.AltaInstituto(nombreInstituto);
	}
	@WebMethod
	public void AltaCrearProgramadeFormacion(DtProgramaFormacion pf)throws CrearProgramaFormacionRepetido_Exception{
		icon.AltaCrearProgramadeFormacion(pf);
	}
	@WebMethod
	public void IngresarCurso(String nInstituto, String nombre, String desc, String duracion, int horas, int creditos, Date fechaR, String url) {
		icon.IngresarCurso(nInstituto, nombre, desc, duracion, horas, creditos, fechaR, url);
	} 
	@WebMethod
	public void ingresarPrevia(String NombrePrevia) {
		icon.ingresarPrevia(NombrePrevia);
	}
	@WebMethod
	public void agregarPrevia(String nombrePrevia, String nombreInstituto, String nombreCurso){
		icon.agregarPrevia(nombrePrevia, nombreInstituto, nombreCurso);
	}
	@WebMethod
	public void agregarCategorias(String nombreCategoria, String nombreInstituto, String nombreCurso){
		icon.agregarCategorias(nombreCategoria, nombreInstituto, nombreCurso);
	}
	@WebMethod
	public void AltaCurso(DtCurso c, String i) throws CursoRepetido_Exception{
		icon.AltaCurso(c, i);
	}
	@WebMethod
	public DtCursoInfo ConsultaCurso(String strInstituto, String strCurso) {
		return icon.ConsultaCurso(strInstituto, strCurso);
	}
	@WebMethod
	public DtinfoEdicion ConsultaEdicion(String strInstituto, String strCurso,String strEdicion) {
		return icon.ConsultaEdicion(strInstituto, strCurso, strEdicion);
	}
	@WebMethod
	public DtCursoInfo ConsultaCursoCategoria(String nombreCategoria, String nombreCurso) {
			return icon.ConsultaCursoCategoria(nombreCategoria, nombreCurso);
	}
	@WebMethod
	public DtinfoEdicion ConsultaEdicionCategoria(String nombreCategoria, String nombreCurso,String strEdicion) {
		return icon.ConsultaEdicionCategoria(nombreCategoria, nombreCurso, strEdicion);
	}	
	@WebMethod
	public DtProgramaFormacion ConsultaProgramaFormacion(String strPrograma) {
		return icon.ConsultaProgramaFormacion(strPrograma);
	}
	@WebMethod
	public void AltadeEdiciondeCurso(DtEdicion e,String i,String c) throws EdicionRepatida_Exception{
		icon.AltadeEdiciondeCurso(e, i, c);
	}
	@WebMethod
	public void InscripcionaEdiciondeCurso(String i,Calendar FechaIns,String nickname,String c,String e,String estado) throws InscripcionRepetida_Exception{
		/*RECIBO UN CALENDAR Y ANTES DE PASARLO AL CONTROLADOR LO CONVIERTO A LOCALDATE*/
		LocalDate fechaLocalDate = LocalDateTime.ofInstant(FechaIns.toInstant(), FechaIns.getTimeZone().toZoneId()).toLocalDate();
		icon.InscripcionaEdiciondeCurso(i, fechaLocalDate, nickname, c, e, estado);
	}
	@WebMethod
	public void asociarEdicion(String strDocente, DtEdicion strEdicion, String strInstituto, String strCurso) {
		icon.asociarEdicion(strDocente, strEdicion, strInstituto, strCurso);
	}
	@WebMethod
	public void seleccionarestadoInscripcion(String nombreInstituto, String nombreCurso, String nombreEdicion, String nicknameEstudiante, String estado) {
		icon.seleccionarestadoInscripcion(nombreInstituto, nombreCurso, nombreEdicion, nicknameEstudiante, estado);
	}	
	@WebMethod
	public void InscripcionaProgramaFormacion(Calendar FechaIns,String nickname,String pf) throws InscripcionRepetidaPF_Exception{
		LocalDate fechaLocalDate = LocalDateTime.ofInstant(FechaIns.toInstant(), FechaIns.getTimeZone().toZoneId()).toLocalDate();
		icon.InscripcionaProgramaFormacion(fechaLocalDate, nickname, pf);
	}	
	@WebMethod
	public void agregarCursoProgFormacion(String programaFormacion, String InstitutoCurso) throws ProgramaCursoRepetido_Exception {
		icon.agregarCursoProgFormacion(programaFormacion, InstitutoCurso);
	}
	@WebMethod
	public void agregarCursoProgFormacion2(String programaFormacion, String Curso, String nombreInstituto) throws ProgramaCursoRepetido_Exception {
		icon.agregarCursoProgFormacion2(programaFormacion, Curso, nombreInstituto);
	}
	@WebMethod
	public void AltaCategoria(String nombreCategoria) throws CategoriaRepetida_Exception {
		icon.AltaCategoria(nombreCategoria);
	}
	/*MULTIUSO*/

	@WebMethod
	public String[] listarInstitutos(){
		return icon.listarInstitutos();
	}
	@WebMethod
	public String[] listarCursos(String StrCurso){
		return icon.listarCursos(StrCurso);
	}
	@WebMethod
	public String[] listarCursosCategoria(String StrCategoria){
		return icon.listarCursosCategoria(StrCategoria);
	}
	@WebMethod
	public String[] listarEdiciones(String strInstituto, String strCurso){
		return icon.listarEdiciones(strInstituto,strCurso);
	}
	@WebMethod
	public String[] listarEdicionesCategoria(String strCategoria, String strCurso){
		return icon.listarEdicionesCategoria(strCategoria,strCurso);
	}
	@WebMethod
	public String[] listarProgramas(){
		return icon.listarProgramas();
	}
	@WebMethod
	public String[] listarProgramasAux(String strInstituto, String strCurso){
		return icon.listarProgramasAux(strInstituto,strCurso);
	}
	@WebMethod
	public String[] listarDocentes(String Instituto){
		return icon.listarDocentes(Instituto);
	}
	@WebMethod
	public String[] listarEstudiantes(){
		return icon.listarEstudiantes();
	}
	@WebMethod
	public String[] listarInstitutoCursos(){
		return icon.listarInstitutoCursos();
	}
	@WebMethod
	public String[] listarProgFormacion(){
		return icon.listarProgFormacion();
	}
	@WebMethod
	public String[] ListarEdicionesCurso(String nombreInstituto, String nombreCurso){
		return icon.ListarEdicionesCurso(nombreInstituto,nombreCurso);
	}
	@WebMethod
	public String[] AtributosEdicion(String nombreInstituto, String nombreCurso, String nombreEdicion) {
		return icon.AtributosEdicion(nombreInstituto, nombreCurso, nombreEdicion);
	}
	@WebMethod
	public String[] AtributosPrograma(String nombrePrograma) {
		return icon.AtributosPrograma(nombrePrograma);
	}
	@WebMethod
	public String[] listarCursosP(String strPrograma){
		return icon.listarCursosP(strPrograma);
	}
	@WebMethod
	public String[] listarPrevias(String nombreInstituto, String nombreCurso){
		return icon.listarPrevias(nombreInstituto,nombreCurso);
	}
	@WebMethod
	public String[] listarCategorias(){
		return icon.listarCategorias();
	}
	@WebMethod
	public String[] listarCategoriasC(String nombreInstituto, String nombreCurso){
		return icon.listarCategoriasC(nombreInstituto,nombreCurso);
	}
	@WebMethod
	public String[] listarCursosCategoriasP(String strPrograma){
		return icon.listarCursosCategoriasP(strPrograma);
	}
	@WebMethod
	public String obtenerInstitutoCurso(String nombreCurso){
		return icon.obtenerInstitutoCurso(nombreCurso);
	}
	@WebMethod
	public String obtenerInstitutoCursoPrograma(String strPrograma, String strCurso){
		return icon.obtenerInstitutoCursoPrograma(strPrograma,strCurso);
	}
	@WebMethod
	public String getInstitutoDocente(String nickname){
		return icon.getInstitutoDocente(nickname);
	}
	@WebMethod
	public DtInscripcionEd[] obtenerInscripcionesEd(String nombreInstituto, String nombreCurso, String nombreEdicion){		
		List<DtInscripcionEd> dtinscipcionEd = icon.obtenerInscripcionesEd(nombreInstituto,nombreCurso,nombreEdicion);
		int i = 0;
		DtInscripcionEd[] ret = new DtInscripcionEd[dtinscipcionEd.size()];
        for(DtInscripcionEd ied : dtinscipcionEd) {
            ret[i]=ied;
            i++;
        }
        return ret;
	}
	@WebMethod
	public DtCurso[] busquedaCurso(String busqueda){
		List<DtCurso> cursos = icon.busquedaCurso(busqueda);
		int i = 0;
		DtCurso[] retorno = new DtCurso[cursos.size()];
		for(DtCurso dtc: cursos) {
			retorno[i] = dtc;
			i++;	
		}
		return retorno;
		}
}