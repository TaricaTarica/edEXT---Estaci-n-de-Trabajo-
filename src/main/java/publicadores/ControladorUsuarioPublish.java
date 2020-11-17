package publicadores;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

import configuraciones.WebServiceConfiguracion;
import datatypes.DtDocente;
import datatypes.DtEstudiante;
import datatypes.DtUsuario;
import excepciones.UsuarioRepetido_Exception;
import interfaces.Fabrica;
import interfaces.IControladorUsuario;
import logica.Curso;
import logica.Edicion;
import logica.Instituto;


@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class ControladorUsuarioPublish {
	private Fabrica fabrica;
	private IControladorUsuario iconUsr;
	private WebServiceConfiguracion configuracion;
	private Endpoint endpoint;

	public ControladorUsuarioPublish() {
		fabrica = Fabrica.getInstancia();
		iconUsr = fabrica.getIControladorUsuario();
		try {
			configuracion = new WebServiceConfiguracion();
		} catch (Exception ex) {
			
		}
	}

	@WebMethod(exclude = true)
	public void publicar() {
		endpoint = Endpoint.publish("http://" + configuracion.getConfigOf("#WS_IP") + ":" + configuracion.getConfigOf("#WS_PORT") + "/controladorUsuario", this);
		System.out.println("http://" + configuracion.getConfigOf("#WS_IP") + ":" + configuracion.getConfigOf("#WS_PORT") + "/controladorUsuario");
	}
	
	@WebMethod(exclude = true)
	public Endpoint getEndpoint() {
        return endpoint;
	}
	
	//LOS MÉTODOS QUE VAMOS A PUBLICAR
	@WebMethod
	public boolean existeUsuario (String nickname) {
		return iconUsr.existeUsuario(nickname);
	}
	@WebMethod
	public DtUsuario ConsultaUsuario(String strUsuario) {
		return iconUsr.ConsultaUsuario(strUsuario);
	}
	@WebMethod
	public boolean esEstudiante(String strNickname) {
		return iconUsr.esEstudiante(strNickname);
	}
	@WebMethod
	public void confirmarAlta(DtUsuario u) throws UsuarioRepetido_Exception{
		iconUsr.confirmarAlta(u);
	}
	@WebMethod
	public void ingresarInstitutoDocente(String nombreInstituto) {
		iconUsr.ingresarInstitutoDocente(nombreInstituto);
	}
	@WebMethod
	public String[] listarInstitutos() {
		return iconUsr.listarInstitutos();
	}
	@WebMethod
	public String[] listarUsuarios(){
		return iconUsr.listarUsuarios();
	}
	@WebMethod
	public String getNombreUsuario(String nickname) {
		return iconUsr.getNombreUsuario(nickname);
	}
	@WebMethod
	public String getApellidoUsuario(String nickname) {
		return iconUsr.getApellidoUsuario(nickname);
	}
	@WebMethod
	public String getContraseniaUsuario(String nickname) {
		return iconUsr.getContraseniaUsuario(nickname);
	}
	/*@WebMethod
	public LocalDate getFechaUsuario(String nickname) {
		return iconUsr.getFechaUsuario(nickname);
	}*/
	@WebMethod
	public void modificarUsuario(String nickname, String nombre, String apellido, Calendar fechaN,String contrasenia) {
		LocalDate fechaLocalDate = LocalDateTime.ofInstant(fechaN.toInstant(), fechaN.getTimeZone().toZoneId()).toLocalDate();
		iconUsr.modificarUsuario(nickname, nombre, apellido, fechaLocalDate, contrasenia);
	}
	@WebMethod
	public String[] listarEdicionesD(String strDocente) {
		return iconUsr.listarEdicionesD(strDocente);
	}
	@WebMethod
	public String[] listarEdicionesE(String strEstudiante) {
		return iconUsr.listarEdicionesE(strEstudiante);
	}
	@WebMethod
	public String[] listarProgramasE(String strEstudiante) {
		return iconUsr.listarProgramasE(strEstudiante);
	}
	@WebMethod
	public String[] AtributosEdicion(String nombreUsuario, String nombreEdicion) {
		return iconUsr.AtributosEdicion(nombreUsuario, nombreEdicion);
	}
	@WebMethod
	public String[] AtributosPrograma(String nombrePrograma) {
		return iconUsr.AtributosPrograma(nombrePrograma);
	}
	@WebMethod
	public Instituto GetInstituto(String strDocente) {
		return iconUsr.GetInstituto(strDocente);
	}
	@WebMethod
	public String GetCurso(String strEdicion,Curso[] Cursos) {
		List<Curso> retCursos = new ArrayList<>();
		for(int i = 0; i < Cursos.length; i++) {
			retCursos.add(Cursos[i]);
		}
		return iconUsr.GetCurso(strEdicion, retCursos);
	}
	@WebMethod
	public String[] listarEdicionesEst(String strEstudiante) {
		return iconUsr.listarEdicionesEst(strEstudiante);
	}
	@WebMethod
	public Edicion ObtenerEdicion(String strEdicion,String strEstudiante) {
		return iconUsr.ObtenerEdicion(strEdicion, strEstudiante);
	}
	@WebMethod
	public void seguirUsuario(String nickname, String aSeguir) {
		iconUsr.seguirUsuario(nickname, aSeguir);
	}
	@WebMethod
	public boolean esSeguidor(String nickname, String aComprobar) {
		return iconUsr.esSeguidor(nickname, aComprobar);
	}
	@WebMethod
	public void dejarSeguir(String nickname, String dejarSeguir) {
		iconUsr.dejarSeguir(nickname, dejarSeguir);
	}
	@WebMethod
	public DtUsuario[] obtenerSeguidores(String nickname){
		List<DtUsuario> dtusr = iconUsr.obtenerSeguidores(nickname);
		int i = 0;
        DtUsuario[] ret = new DtUsuario[dtusr.size()];
        for(DtUsuario u : dtusr) {
            ret[i]=u;
            i++;
        }
        return ret;
	}
	@WebMethod
	public DtUsuario[] obtenerSeguidos(String nickname){
		List<DtUsuario> dtusr = iconUsr.obtenerSeguidos(nickname);
		int i = 0;
        DtUsuario[] ret = new DtUsuario[dtusr.size()];
        for(DtUsuario u : dtusr) {
            ret[i]=u;
            i++;
        }
        return ret;
	}
	@WebMethod
	public void confirmarAltaEstudiante(DtEstudiante u) throws UsuarioRepetido_Exception{
		iconUsr.confirmarAltaEstudiante(u);
	}
	@WebMethod
	public void confirmarAltaDocente(DtDocente u) throws UsuarioRepetido_Exception{
		iconUsr.confirmarAltaDocente(u);
	}
	@WebMethod
	public String nombresDocente(Edicion ed) {
		return iconUsr.nombresDocente(ed);
	}
	@WebMethod
	public Curso[] getCursos(Instituto ins){
		List<Curso> dtc = iconUsr.getCursos(ins);
		int i = 0;
        Curso[] ret = new Curso[dtc.size()];
        for(Curso c : dtc) {
            ret[i]=c;
            i++;
        }
        return ret;
	}
}
