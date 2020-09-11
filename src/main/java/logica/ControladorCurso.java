package logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import excepciones.InstitutoRepetido_Exception;
import excepciones.EdicionRepatida_Exception;
import excepciones.CrearProgramaFormacionRepetido_Exception;
import excepciones.CursoRepetido_Exception;
import excepciones.InscripcionRepetida_Exception;
import excepciones.ProgramaCursoRepetido_Exception;



import interfaces.IControladorCurso;
import persistencia.Conexion;
import datatypes.DtCurso;
import datatypes.DtEdicion;
import datatypes.DtProgramaFormacion;
import datatypes.DtCursoInfo;


public class ControladorCurso implements IControladorCurso {
	private String nombreInstituto;
	private String nombreProgFormacion;
	private String descripcion;
	private Date fechaInicio;
	private Date fechaFin;
	private Date fechaAlta;
	private String nombreCurso;
	private String nombrePrevia;
	private String duracion;
	private int horas;
	private int creditos;
	private String url;
	private Date fechaR;
	public ControladorCurso(){}
	
	/*IR AGREGANDO LAS OPERACIONES A MEDIDA QUE SE REQUIERAN/IMPLEMENTEN*/
	
	/*Alta Instituto*/
	@Override
	public void AltaInstituto(String nombreInstituto) throws InstitutoRepetido_Exception {
		ManejadorInstituto mI = ManejadorInstituto.getInstancia();
		Instituto i = mI.buscarInstituto(nombreInstituto);
		if (i != null) {
			throw new InstitutoRepetido_Exception("ï¿½El instituto " + nombreInstituto + " ya estï¿½ registrado!");
		}
		i = new Instituto(nombreInstituto);
		mI.agregarInstituto(i);
	}
	
	/*Alta ProgramaFormacion*/
	/*@Override
	public void IngresarProgFormacion(String nombre, String descripcion, Date fechaInicio, Date fechaFin, Date fechaAlta) {
		this.nombreProgFormacion = nombre;
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.fechaAlta = fechaAlta;
	}*/
	
	@Override
	public void AltaCrearProgramadeFormacion(DtProgramaFormacion pf)throws CrearProgramaFormacionRepetido_Exception {
		ManejadorProgramaFormacion mPF = ManejadorProgramaFormacion.getInstancia();
		ProgramaFormacion programa = new ProgramaFormacion(pf);
		if(mPF.existeProgramaFormacion(programa.getNombre())){
			throw new CrearProgramaFormacionRepetido_Exception("El nombre "+ programa.getNombre()+" está registrado") ;
		}
		mPF.agregarProgramaFormacion(programa);
	}

	
	/*Alta Curso*/
	@Override
	public void IngresarCurso(String nInstituto, String nombre, String desc, String duracion, int horas, int creditos, Date fechaR, String url) {
		this.nombreInstituto = nInstituto;
		this.nombreCurso = nombre;
		this.descripcion = desc;
		this.duracion = duracion;
		this.horas = horas;
		this.creditos = creditos;
		this.fechaR = fechaR;
		this.url = url;
	}
	
	@Override
	public void ingresarPrevia(String NombrePrevia) {
		this.nombrePrevia = NombrePrevia;
	}
	
	@Override
	public void agregarPrevia(String nombrePrevia) {
		ManejadorInstituto mI = ManejadorInstituto.getInstancia();
		Instituto institutoCurso = mI.buscarInstituto(this.nombreInstituto);
		Curso previa = institutoCurso.getCurso(nombrePrevia);
		institutoCurso.setPreviaCurso(this.nombreCurso,previa);
		
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		em.getTransaction().begin();
		em.persist(institutoCurso);
		em.getTransaction().commit();
	}
	
	@Override
	/*public void AltaCurso() {
		ManejadorInstituto mI = ManejadorInstituto.getInstancia();
		Instituto institutoCurso = mI.buscarInstituto(this.nombreInstituto);
		Curso c = new Curso(this.nombreCurso, this.descripcion, this.duracion, this.horas, this.creditos, this.fechaR, this.url);
		institutoCurso.setCurso(c);
	}*/
	
	public void AltaCurso(DtCurso c, String i) throws CursoRepetido_Exception{
		ManejadorInstituto mI = ManejadorInstituto.getInstancia();
		Instituto instituto = mI.buscarInstituto(i);
		if(instituto.existeCurso(c.getNombre())) {
			throw new CursoRepetido_Exception("El curso"+ c.getNombre()+" ya está registrado") ;
		}
		
		instituto.agregarCurso(c);
		
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		em.getTransaction().begin();
		em.persist(instituto);
		em.getTransaction().commit();
	}
	
	public DtCursoInfo ConsultaCurso(String strInstituto, String strCurso) {
		ManejadorInstituto mI = ManejadorInstituto.getInstancia();
		Instituto instituto = mI.buscarInstituto(strInstituto);
		Curso curso = instituto.getCurso(strCurso);
		String nombre = curso.getNombre();
		String descripcion = curso.getDescripcion();
		String duracion = curso.getDuracion();
		int cantHoras = curso.getCantHoras();
		int cantCreditos = curso.getCantCreditos();
		String url = curso.getUrl();
		LocalDate fechaAlta = curso.getFechaAlta();
		
		DtCursoInfo retorno = new DtCursoInfo(nombre, descripcion, duracion, cantHoras, cantCreditos, fechaAlta, url);
		
		
		return retorno;
	}
	
	
	
	public DtProgramaFormacion ConsultaProgramaFormacion(String strPrograma) {
		ManejadorProgramaFormacion mP = ManejadorProgramaFormacion.getInstancia();
		ProgramaFormacion programa = mP.buscarProgramaFormacion(strPrograma);
		
		String nombre = programa.getNombre();
		String descripcion = programa.getDescripcion();
		LocalDate fechaInicio = programa.getFechaInicio();
		LocalDate fechaFin = programa.getFechaFin();
		LocalDate fechaAlta = programa.getFechaAlta();
		
		DtProgramaFormacion retorno = new DtProgramaFormacion(nombre, descripcion, fechaInicio, fechaFin, fechaAlta);
		
		return retorno;
	}
	
	
	/*Alta de Edicion de Curso*/
	public void AltadeEdiciondeCurso(DtEdicion e,String i,String c) throws EdicionRepatida_Exception{
		ManejadorInstituto mI = ManejadorInstituto.getInstancia();
		Instituto instituto = mI.buscarInstituto(i);
		Curso curso = instituto.getCurso(c);
		if(curso.BuscarEdicion(e.getNombre())){
			throw new EdicionRepatida_Exception("El nombre "+ e.getNombre()+" esta registrados");
		}
		curso.agregarEdicion(e);
		
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		em.getTransaction().begin();
		em.persist(curso);
		em.getTransaction().commit();
		
	}
	
	/*INSCRIPCION A EDICION DE CURSO*/
	@Override
	public void InscripcionaEdiciondeCurso(String i,Date FechaIns,String nickname,String c,String e) throws InscripcionRepetida_Exception{
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Estudiante est = mU.BuscarEstudiante(nickname);
		ManejadorInstituto mI = ManejadorInstituto.getInstancia();
		Instituto instituto = mI.buscarInstituto(i);
		Curso curso = instituto.getCurso(c);
		Edicion edicion=curso.getEdicion(e);
		if (est.BuscarInscripcion(edicion)) {
			throw new InscripcionRepetida_Exception("La inscripcion ya esta registrada!");
		}
		InscripcionEd inscripcion = new InscripcionEd(FechaIns, edicion, est);
		est.agregarInscripcion(inscripcion);
		edicion.agregarInscripcion(inscripcion);
		
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		em.getTransaction().begin();
		em.persist(est);
		em.persist(edicion);
		em.getTransaction().commit();
		
		
		
	}
	
	@Override
	public void asociarEdicion(String strDocente, DtEdicion strEdicion, String strInstituto, String strCurso) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Docente docente = (Docente) mU.buscarUsuarioC(strDocente);
		ManejadorInstituto mI = ManejadorInstituto.getInstancia();
		Instituto instituto = mI.buscarInstituto(strInstituto);
		Curso curso = instituto.getCurso(strCurso);
		Edicion edicion = curso.getEdicion(strEdicion.getNombre());
		docente.asociarEdicion(edicion);
		edicion.setDocente(docente);
		
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		em.getTransaction().begin();
		em.persist(edicion);
		em.persist(docente);
		em.getTransaction().commit();
	}
	
	/*Agregar Curso Programa Formaciï¿½n*/
	@Override
	public void agregarCursoProgFormacion(String programaFormacion, String InstitutoCurso) throws ProgramaCursoRepetido_Exception {
		ManejadorProgramaFormacion mPF = ManejadorProgramaFormacion.getInstancia();
		ProgramaFormacion programa = mPF.buscarProgramaFormacion(programaFormacion);
		
		String[] partes = InstitutoCurso.split("-");
		String nombreInstituto = partes[0];
		String nombreCurso = partes[1];
		
		//Curso c = programa.getCurso(nombreCurso);
		if (programa.existeCurso(nombreCurso)) {
			throw new ProgramaCursoRepetido_Exception("El curso "+ nombreCurso +" ya pertenece al Programa de Formación" + programaFormacion);
		}
		ManejadorInstituto mI = ManejadorInstituto.getInstancia();
		Instituto instituto = mI.buscarInstituto(nombreInstituto);
		Curso curso = instituto.getCurso(nombreCurso);
		programa.setCurso(curso);
		
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		em.getTransaction().begin();
		em.persist(programa);
		em.getTransaction().commit();
	}

	
	/*MULTIUSO*/
	@Override
	public String[] listarInstitutos(){
		ManejadorInstituto mI = ManejadorInstituto.getInstancia();
		ArrayList<String> institutos;
		institutos = mI.getNombreInstitutos();
		String[] institutos_ret = new String[institutos.size()];
        int i=0;
        for(String ins: institutos) {
        	institutos_ret[i]=ins;
        	i++;
        }
        return institutos_ret;
	}
	
	@Override
	public String[] listarCursos(String strInstituto){
		ManejadorInstituto mI = ManejadorInstituto.getInstancia();
		Instituto instituto = mI.buscarInstituto(strInstituto);
		ArrayList<String> cursos;
		cursos = instituto.obtenerCursos();		
		String[] cursos_ret = new String[cursos.size()];
        int i=0;
        for(String ins: cursos) {
        	cursos_ret[i]=ins;
        	i++;
        }
        return cursos_ret;		
	}
	
	@Override
	public String[] listarEdiciones(String strInstituto, String strCurso){
		ManejadorInstituto mI = ManejadorInstituto.getInstancia();
		Instituto instituto = mI.buscarInstituto(strInstituto);
		Curso curso = instituto.getCurso(strCurso);
		
		
		ArrayList<String> ediciones;
		ediciones = curso.obtenerEdiciones();		
		String[] ediciones_ret = new String[ediciones.size()];
        int i=0;
        for(String ins: ediciones) {
        	ediciones_ret[i]=ins;
        	i++;
        }
        return ediciones_ret;		
	}
	
	@Override
	public String[] listarProgramas(){
		ManejadorProgramaFormacion mP = ManejadorProgramaFormacion.getInstancia();
		ArrayList<String> programas;
		programas = mP.getNombreProgramas();
		String[] programas_ret = new String[programas.size()];
        int i=0;
        for(String ins: programas) {
        	programas_ret[i]=ins;
        	i++;
        }
        return programas_ret;
	}
	
	@Override
	public String[] listarProgramasAux(String strInstituto, String strCurso){
		ManejadorInstituto mI = ManejadorInstituto.getInstancia();
		Instituto instituto = mI.buscarInstituto(strInstituto);
		Curso curso = instituto.getCurso(strCurso);

		ArrayList<String> programas;
		programas = curso.obtenerProgramasAsociados();		
		String[] programas_ret = new String[programas.size()];
        int i=0;
        for(String ins: programas) {
        	programas_ret[i]=ins;
        	i++;
        }
        return programas_ret;		
	}
	
	@Override
	public String[] listarDocentes(String Instituto){
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();

		ArrayList<String> docentes = mU. getDocentesInstituto(Instituto);
		String[] docentes_ret = new String[docentes.size()];
        int i=0;
        for(String doc: docentes) {
        	docentes_ret[i]=doc;
        	i++;
        }
        return docentes_ret;		
	}
	@Override
	public String[] listarEstudiantes(){
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();

		ArrayList<String> estudiantes = mU.getEstudiantes();
		String[] estudiantes_ret = new String[estudiantes.size()];
        int i=0;
        for(String est: estudiantes) {
        	estudiantes_ret[i]=est;
        	i++;
        }
        return estudiantes_ret;		
	}
	
	@Override
	public String[] listarInstitutoCursos() {
		ManejadorInstituto mI = ManejadorInstituto.getInstancia();
		List<Instituto> institutos = mI.getInstitutos();
		ArrayList<String> cursos = new ArrayList<>();
		for(Instituto i: institutos) {
			ArrayList<String> institutoCursos = i.obtenerInstitutoCursos();
			for(String s: institutoCursos) {
				cursos.add(s);
			}
			 
		}
		String [] retorno = new String[cursos.size()];
		int x = 0;
		for(String c: cursos) {
			retorno[x] = c;
			x++;
		}
		return retorno;
	}
	@Override
	public String [] listarProgFormacion() {
		ManejadorProgramaFormacion mPF = ManejadorProgramaFormacion.getInstancia();
		ArrayList<String> programas = mPF.getNombreProgramas();
		String [] retorno = new String[programas.size()];
		int i = 0;
		for(String pf: programas) {
			retorno[i] = pf;
			i++;
		}
		return retorno;
	}
	
	@Override
	public String[] ListarEdicionesCurso(String nombreInstituto, String nombreCurso){
		ManejadorInstituto mI = ManejadorInstituto.getInstancia();
		Instituto instituto = mI.buscarInstituto(nombreInstituto);
		Curso curso = instituto.getCurso(nombreCurso);
		List<Edicion> listEdiciones = new ArrayList<>(); //No inicializar en null, utilizar = new ArrayList<>();
		listEdiciones = curso.getEdiciones();
		String[] cursos_ret = new String[listEdiciones.size()];
        for(int i=0; i < listEdiciones.size(); i++) {
        	cursos_ret[i]=listEdiciones.get(i).getNombre();
        }
		return cursos_ret;
	}
	
	//Print de edicion de curso
	@Override
	public String[] AtributosEdicion(String nombreInstituto, String nombreCurso, String nombreEdicion) {
		ManejadorInstituto mI = ManejadorInstituto.getInstancia();
		Instituto instituto = mI.buscarInstituto(nombreInstituto);
		Curso curso = instituto.getCurso(nombreCurso);
		Edicion edicion = curso.getEdicion(nombreEdicion);
		String[] atributos_ed_curso_ret = new String[6];
			atributos_ed_curso_ret[0] = "Nombre: " + edicion.getNombre();
			atributos_ed_curso_ret[1] = "Fecha de inicio: " + edicion.getFechaIni();
			atributos_ed_curso_ret[2] = "Fecha de finalizacion: " + edicion.getFechaFin();
			atributos_ed_curso_ret[3] = "Cupo: " + edicion.getCupo();
			atributos_ed_curso_ret[4] = "Fecha de publicacion: " + edicion.getFechaPub();
			atributos_ed_curso_ret[5] = "Docentes: " + edicion.nombresDocentes();
			return atributos_ed_curso_ret;
	}


}
