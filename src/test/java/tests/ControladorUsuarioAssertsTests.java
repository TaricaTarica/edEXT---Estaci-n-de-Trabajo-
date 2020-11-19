package tests;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import interfaces.Fabrica;
import interfaces.IControladorUsuario;
import logica.Curso;
import logica.Edicion;
import logica.Instituto;
import persistencia.Conexion;
import interfaces.IControladorCurso;

import datatypes.DtUsuario;
import excepciones.CrearProgramaFormacionRepetido_Exception;
import excepciones.CursoRepetido_Exception;
import excepciones.EdicionRepatida_Exception;
import excepciones.InscripcionRepetida_Exception;
import excepciones.InstitutoRepetido_Exception;
import excepciones.UsuarioRepetido_Exception;
import datatypes.DtCurso;
import datatypes.DtDocente;
import datatypes.DtEdicion;
import datatypes.DtEstudiante;
import datatypes.DtProgramaFormacion;


public class ControladorUsuarioAssertsTests {
	private static	Fabrica fab = Fabrica.getInstancia();
	private static	IControladorUsuario iconUsr = fab.getIControladorUsuario();
	private static	IControladorCurso iconCur = fab.getIControladorCurso();
	private static Calendar fecha = Calendar.getInstance();
	private static LocalDate fechaLocalDate = LocalDate.now();
	private DtUsuario usrDoc = new DtDocente("pruebaDocente", "nombreDocente", "apellidoDocente", "docentePrueba@maail.com", fecha, "contraseniaDocente");
	private DtUsuario usrEst = new DtEstudiante("pruebaEstudiante", "nombreEstudiante", "apellidoEstudiante", "estudiantePrueba@maail.com", fecha, "contraseniaEstudiante");
	private static DtUsuario creado = new DtEstudiante("nickname", "nombre", "apellido", "mail@maail.com", fecha, "contrasenia");
	private static DtUsuario creadoDoc = new DtDocente("docente","nombreDocente", "apellidoDocente","mailDocente@maail.com" , fecha, "contraseniaDocente");
	private static DtCurso curso = new DtCurso("cursoPrueba", "descripcionPrueba", "duracionPrueba", 12, 12, fecha, "www.cursoprueba.com", "FING");
	private static DtEdicion edicion = new DtEdicion("edicionPrueba", fecha, fecha, 12, fecha);
	private static DtProgramaFormacion programa = new DtProgramaFormacion("PruebaPrograma", "descripcionPrueba", fecha, fecha, fecha);
	@BeforeClass
	public static void init() {
		try {
			iconCur.AltaInstituto("FING");
			iconCur.AltaCurso(curso, "FING");
			iconUsr.confirmarAlta(creado);
			iconUsr.ingresarInstitutoDocente("FING");
			iconUsr.confirmarAlta(creadoDoc);
			iconCur.AltadeEdiciondeCurso(edicion,"FING","cursoPrueba");
			iconCur.asociarEdicion(creadoDoc.getNickname(), edicion, "FING", curso.getNombre());
			iconCur.InscripcionaEdiciondeCurso("FING",fechaLocalDate,creado.getNickname(),curso.getNombre(),edicion.getNombre(),"Aceptado");
			iconCur.AltaCrearProgramadeFormacion(programa);
			
		}
		catch (InstitutoRepetido_Exception | UsuarioRepetido_Exception | CursoRepetido_Exception | EdicionRepatida_Exception | InscripcionRepetida_Exception | CrearProgramaFormacionRepetido_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		
	@Test
	public void testAltaUsuarioEstudiante() throws UsuarioRepetido_Exception {
		iconUsr.confirmarAlta(usrEst);
		assertEquals(iconUsr.existeUsuario("pruebaEstudiante"), true);
	}
	@Test
	public void testAltaUsuarioDocente() throws UsuarioRepetido_Exception {
		iconUsr.ingresarInstitutoDocente("FING");
		iconUsr.confirmarAlta(usrDoc);
		assertEquals(iconUsr.existeUsuario("pruebaDocente"), true);
	}
	@Test
	public void testConsultaUsuario() throws UsuarioRepetido_Exception {
		//DtUsuario creado = new DtEstudiante("nickname", "nombre", "apellido", "mail@maail.com", fecha, "contrasenia");
		//iconUsr.confirmarAlta(creado);
		DtUsuario obtenido = iconUsr.ConsultaUsuario(creado.getNickname());
		assertSame(creado.getNickname(), obtenido.getNickname());
	}
	@Test(timeout= 1000)
	public void testListarInstitutos() {
		iconUsr.listarInstitutos();
	}
	@Test(timeout=1000)
	public void testListarUsuarios() {
		iconUsr.listarUsuarios();
	}
	@Test
	public void testGetNombreUsuario() {
		DtUsuario obtenido = iconUsr.ConsultaUsuario(creado.getNickname()); 
		assertSame(iconUsr.getNombreUsuario(obtenido.getNickname()), creado.getNombre());
	}
	@Test
	public void testGetApellidoUsuario() {
		DtUsuario obtenido = iconUsr.ConsultaUsuario(creado.getNickname()); 
		assertSame(iconUsr.getApellidoUsuario(obtenido.getNickname()), creado.getApellido());
	}
	@Test
	public void testGetContraseniaUsuario() {
		DtUsuario obtenido = iconUsr.ConsultaUsuario(creado.getNickname()); 
		assertSame(iconUsr.getContraseniaUsuario(obtenido.getNickname()), creado.getContrasenia());
	}
	@Test
	public void testGetFechaUsuario() {
		DtUsuario obtenido = iconUsr.ConsultaUsuario(creado.getNickname());
		LocalDate fechaLocalDate  = LocalDateTime.ofInstant(creado.getfechaNac().toInstant(), creado.getfechaNac().getTimeZone().toZoneId()).toLocalDate();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-LL-dd");
		fechaLocalDate.format(formatter);
		assertEquals(iconUsr.getFechaUsuario(obtenido.getNickname()), fechaLocalDate);
	}
	@Test
	public void testModificarUsuario() throws UsuarioRepetido_Exception {
		DtUsuario usr = new DtEstudiante("estudiante","nombreEstudiante", "apellidoEstudiante","mailEstudiante@maail.com" , fecha, "contraseniaEstudiante");
		iconUsr.confirmarAlta(usr);
		LocalDate fechaLocalDate = LocalDateTime.ofInstant(fecha.toInstant(), fecha.getTimeZone().toZoneId()).toLocalDate();
		iconUsr.modificarUsuario(usr.getNickname(), "nombreModificado", "apellidoModificado", fechaLocalDate, "contraseniaModificada");
		assertNotEquals(usr.getNombre(), "nombre");
	}
	@Test
	public void testEsEstudiante() {
		assertTrue(iconUsr.esEstudiante(creado.getNickname()));
	}
	@Test
	public void testEsEstudiante2() throws UsuarioRepetido_Exception {
		
		assertSame(iconUsr.esEstudiante(creadoDoc.getNickname()), false);
	}
	@Test(timeout=1000)
	public void listarEdicionesD() {
		iconUsr.listarEdicionesD(creadoDoc.getNickname());
	}
	@Test(timeout=1000)
	public void listarEdicionesE() {
		iconUsr.listarEdicionesE(creado.getNickname());
	}
	@Test(timeout=1000)
	public void listarProgramasE() {
		iconUsr.listarProgramasE(creado.getNickname());
	}
	@Test
	public void testAtributosEdicion() { //PRUEBA CON ESTUDIANTE 
		String[] retorno = iconUsr.AtributosEdicion(creado.getNickname(), edicion.getNombre());
		assertEquals(retorno[0],"Nombre: "+edicion.getNombre());
	}
	@Test
	public void testAtributosEdicion2() { //PRUEBA CON DOCENTE
		String[] retorno = iconUsr.AtributosEdicion(creadoDoc.getNickname(), edicion.getNombre());
		assertEquals(retorno[0], "Nombre: "+edicion.getNombre());
	}
	@Test
	public void testAtributosPrograma() {
		String[] retorno = iconUsr.AtributosPrograma(programa.getNombre());
		assertEquals(retorno[0], "Nombre: "+programa.getNombre());
	}
	@Test
	public void testGetInstituto() {
		Instituto retorno = iconUsr.GetInstituto(creadoDoc.getNickname());
		assertEquals(retorno.getNombre(), "FING");
	}
	@Test
	public void testGetCurso() {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		Instituto instituto = em.find(Instituto.class, "FING");
		Curso cursoObtenido = instituto.getCurso(curso.getNombre());
		List<Curso> Cursos = new ArrayList<>();
		Cursos.add(cursoObtenido);
		String retorno = iconUsr.GetCurso(edicion.getNombre(),Cursos);
		assertEquals(retorno, curso.getNombre());
	}
	@Test(timeout=1000)
	public void testListarEdicionesEst() {
		iconUsr.listarEdicionesEst(creado.getNickname());
	}
	@Test
	public void testObtenerEdicion() {
		Edicion obtenida = iconUsr.ObtenerEdicion(edicion.getNombre(),creado.getNickname());
		assertEquals(obtenida.getNombre(), edicion.getNombre());
	}
	@Test
	public void testSeguirUsuario() {
		iconUsr.seguirUsuario(creado.getNickname(), creadoDoc.getNickname());
		assertTrue(iconUsr.esSeguidor(creado.getNickname(), creadoDoc.getNickname()));
	}
	@Test
	public void testDejarDeSeguir() {
		iconUsr.seguirUsuario(creado.getNickname(), creadoDoc.getNickname());
		iconUsr.dejarSeguir(creado.getNickname(), creadoDoc.getNickname());
		assertTrue(!iconUsr.esSeguidor(creado.getNickname(), creadoDoc.getNickname()));
	}
	@Test(timeout = 1000)
	public void testObtenerSeguidores() {
		iconUsr.seguirUsuario(creado.getNickname(), creadoDoc.getNickname());
		iconUsr.obtenerSeguidores(creadoDoc.getNickname());
	}
	@Test(timeout = 1000)
	public void testObtenerSeguidos() {
		iconUsr.seguirUsuario(creado.getNickname(), creadoDoc.getNickname());
		iconUsr.obtenerSeguidos(creado.getNickname());
	}
	@Test
	public void testNombresDocente() {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		Instituto instituto = em.find(Instituto.class, "FING");
		Curso cu = instituto.getCurso(curso.getNombre());
		Edicion ed = cu.getEdicion(edicion.getNombre());
		String nombreObtenido = iconUsr.nombresDocente(ed);
		assertEquals(nombreObtenido, " - "+creadoDoc.getNickname());
	}
	@Test(timeout = 1000)
	public void testGetCursos() {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		Instituto instituto = em.find(Instituto.class, "FING");
		iconUsr.getCursos(instituto);
	}
	@Test
	public void testExisteUsuario2() { //PRUEBO CON UN NICK QUE NO EXISTE
		assertTrue(!iconUsr.existeUsuario("HOLA"));
	}
	@Test(expected = UsuarioRepetido_Exception.class)
	public void testUsuarioRepetido() throws UsuarioRepetido_Exception {
		iconUsr.confirmarAlta(creado);
	}
	@Test 
	public void testAltaDocente() throws UsuarioRepetido_Exception {
		DtDocente u = new DtDocente();
		u.setNickname("doc");
		u.setNombre("doc");
		u.setApellido("doc");
		u.setCorreo("doc@maail.com");
		u.setFechaNac(fecha);
		u.setContrasenia("doc");
		iconUsr.ingresarInstitutoDocente("FING");
		iconUsr.confirmarAltaDocente(u);
		assertTrue(iconUsr.existeUsuario("doc"));
		
	}
	@Test
	public void testConfirmarAltaEstudiante() throws UsuarioRepetido_Exception {
		DtEstudiante u = new DtEstudiante();
		u.setNickname("est");
		u.setNombre("est");
		u.setApellido("est");
		u.setCorreo("est@maail.com");
		u.setFechaNac(fecha);
		u.setContrasenia("est");
		iconUsr.confirmarAltaEstudiante(u);
		assertTrue(iconUsr.existeUsuario("est"));
	}
	@Test(expected = UsuarioRepetido_Exception.class)
	public void testEstudianteRepetido() throws UsuarioRepetido_Exception {
		DtEstudiante u = new DtEstudiante();
		u.setNickname("est");
		u.setNombre("est");
		u.setApellido("est");
		u.setCorreo("est@maail.com");
		u.setFechaNac(fecha);
		u.setContrasenia("est");
		iconUsr.confirmarAltaEstudiante(u);
		iconUsr.confirmarAlta(u);
	}
	@Test(expected = UsuarioRepetido_Exception.class)
	public void testDocenteRepetido() throws UsuarioRepetido_Exception {
		DtDocente u = new DtDocente();
		u.setNickname("doc");
		u.setNombre("doc");
		u.setApellido("doc");
		u.setCorreo("doc@maail.com");
		u.setFechaNac(fecha);
		u.setContrasenia("doc");
		iconUsr.confirmarAltaDocente(u);
		iconUsr.confirmarAlta(u);
	}
	
	

}
