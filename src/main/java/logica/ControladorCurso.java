package logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import excepciones.InstitutoRepetido_Exception;
import excepciones.EdicionRepatida_Exception;
import excepciones.InscripcionRepetidaPF_Exception;
import excepciones.CategoriaRepetida_Exception;
import excepciones.CrearProgramaFormacionRepetido_Exception;
import excepciones.CursoRepetido_Exception;
import excepciones.InscripcionRepetida_Exception;
import excepciones.ProgramaCursoRepetido_Exception;



import interfaces.IControladorCurso;
import persistencia.Conexion;
import datatypes.DtCurso;
import datatypes.DtEdicion;
import datatypes.DtProgramaFormacion;
import datatypes.DtinfoEdicion;
import datatypes.EstadoInscripcion;
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
			throw new InstitutoRepetido_Exception("El instituto " + nombreInstituto + " ya esta registrado");
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
			throw new CrearProgramaFormacionRepetido_Exception("El nombre "+ programa.getNombre()+" ya esta registrado") ;
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
	public void agregarPrevia(String nombrePrevia, String nombreInstituto, String nombreCurso) {
		ManejadorInstituto mI = ManejadorInstituto.getInstancia();
		Instituto institutoCurso = mI.buscarInstituto(nombreInstituto);
		Curso curso = institutoCurso.getCurso(nombreCurso);
		Curso previa = institutoCurso.getCurso(nombrePrevia);
		curso.setPrevia(previa);
		
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		em.getTransaction().begin();
		em.persist(curso);
		em.persist(previa);
		em.getTransaction().commit();
	}
	@Override
	public void agregarCategorias(String nombreCategoria, String nombreInstituto, String nombreCurso) {
		ManejadorInstituto mI = ManejadorInstituto.getInstancia();
		Instituto institutoCurso = mI.buscarInstituto(nombreInstituto);
		Curso curso = institutoCurso.getCurso(nombreCurso);
		ManejadorCategoria mC = ManejadorCategoria.getInstancia();
		Categoria categoria = mC.buscarCategoria(nombreCategoria);
		curso.setCategorias(categoria);
		categoria.agregarCurso(curso);
		
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		em.getTransaction().begin();
		em.persist(curso);
		em.persist(categoria);
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
			throw new CursoRepetido_Exception("El curso "+ c.getNombre()+" ya esta registrado") ;
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
		String img = curso.getImg();
		
		
		DtCursoInfo retorno = new DtCursoInfo(nombre, descripcion, duracion, cantHoras, cantCreditos, fechaAlta, url);
		retorno.setImg(img);
		
		return retorno;
	}
	@Override
	public DtinfoEdicion ConsultaEdicion(String strInstituto, String strCurso,String strEdicion) {
		ManejadorInstituto mI = ManejadorInstituto.getInstancia();
		Instituto instituto = mI.buscarInstituto(strInstituto);
		Curso curso = instituto.getCurso(strCurso);
		Edicion edicion=curso.getEdicion(strEdicion);
		String nombre = edicion.getNombre();
		int cupo = edicion.getCupo();
		LocalDate fechaInicio = edicion.getFechaIni();
		LocalDate fechaFin = edicion.getFechaFin();
		LocalDate fechaPub = edicion.getFechaPub();
		//Ac� debo obtener la imagen
		
		DtinfoEdicion retorno = new DtinfoEdicion(nombre,fechaInicio,fechaFin,cupo, fechaPub);
		
		
		return retorno;
	}
	@Override
	public DtCursoInfo ConsultaCursoCategoria(String nombreCategoria, String nombreCurso) {
		ManejadorCategoria mC = ManejadorCategoria.getInstancia();
		Categoria categoria = mC.buscarCategoria(nombreCategoria);
		Curso curso = categoria.getCurso(nombreCurso);
		String nombre = curso.getNombre();
		String descripcion = curso.getDescripcion();
		String duracion = curso.getDuracion();
		int cantHoras = curso.getCantHoras();
		int cantCreditos = curso.getCantCreditos();
		String url = curso.getUrl();
		LocalDate fechaAlta = curso.getFechaAlta();
		String img = curso.getImg();
		
		DtCursoInfo retorno = new DtCursoInfo(nombre, descripcion, duracion, cantHoras, cantCreditos, fechaAlta, url);
		retorno.setImg(img);
		
		return retorno;
	}
	@Override
	public DtinfoEdicion ConsultaEdicionCategoria(String nombreCategoria, String nombreCurso,String strEdicion) {
		ManejadorCategoria mC = ManejadorCategoria.getInstancia();
		Categoria categoria = mC.buscarCategoria(nombreCategoria);
		Curso curso = categoria.getCurso(nombreCurso);
		Edicion edicion=curso.getEdicion(strEdicion);
		String nombre = edicion.getNombre();
		int cupo = edicion.getCupo();
		LocalDate fechaInicio = edicion.getFechaIni();
		LocalDate fechaFin = edicion.getFechaFin();
		LocalDate fechaPub = edicion.getFechaPub();
		//Ac� debo obtener la imagen
		
		DtinfoEdicion retorno = new DtinfoEdicion(nombre,fechaInicio,fechaFin,cupo, fechaPub);
		
		
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
			throw new EdicionRepatida_Exception("El nombre "+ e.getNombre()+" ya esta registrado");
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
			throw new InscripcionRepetida_Exception("La inscripcion ya esta registrada");
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
	
	@Override
	public void seleccionarestadoInscripcion(InscripcionEd ied, EstadoInscripcion estado) {
		ied.setEstadoInscripcion(estado);
		
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		em.getTransaction().begin();
		em.persist(ied);
		em.getTransaction().commit();
		
	}
	
	/*INSCRIPCION A PROGRAMA DE FORMACION*/
	@Override
	public void InscripcionaProgramaFormacion(Date FechaIns,String nickname,String pf) throws InscripcionRepetidaPF_Exception{
		ManejadorProgramaFormacion mPF = ManejadorProgramaFormacion.getInstancia();
		ProgramaFormacion programaformacion = mPF.buscarProgramaFormacion(pf);
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Estudiante est = mU.BuscarEstudiante(nickname);
		if (est.BuscarInscripcionPF(programaformacion)) {
			throw new InscripcionRepetidaPF_Exception("La inscripcion ya esta registrada");
		}
		InscripcionPF inscripcion = new InscripcionPF(FechaIns,est,programaformacion);
		est.agregarInscripcionPF(inscripcion);
		programaformacion.agregarInscripcion(inscripcion);
		
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		em.getTransaction().begin();
		em.persist(est);
		em.persist(programaformacion);
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
			throw new ProgramaCursoRepetido_Exception("El curso "+ nombreCurso +" ya pertenece al Programa de Formacion " + programaFormacion);
		}
		ManejadorInstituto mI = ManejadorInstituto.getInstancia();
		Instituto instituto = mI.buscarInstituto(nombreInstituto);
		Curso curso = instituto.getCurso(nombreCurso);
		curso.asociarPrograma(programa.getNombre());
		programa.setCurso(curso);
		
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		em.getTransaction().begin();
		em.persist(programa);
		em.getTransaction().commit();
	}
	/*Alta Categoria*/
	@Override
	public void AltaCategoria(String nombreCategoria) throws CategoriaRepetida_Exception {
		ManejadorCategoria mC = ManejadorCategoria.getInstancia();
		Categoria c = mC.buscarCategoria(nombreCategoria);
		if (c != null) {
			throw new CategoriaRepetida_Exception("La categoría " + nombreCategoria + " ya está registrada");
		}
		c = new Categoria(nombreCategoria);
		mC.agregarCategoria(c);
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
	public String[] listarCursosCategoria(String nombreCategoria) {
		ManejadorCategoria mC = ManejadorCategoria.getInstancia();
		Categoria categoria = mC.buscarCategoria(nombreCategoria);
		ArrayList <String> cursos;
		cursos = categoria.obtenerCursos();
		String[] cursos_ret = new String[cursos.size()];
        int i=0;
        for(String c: cursos) {
        	cursos_ret[i]=c;
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
	public String[] listarEdicionesCategoria(String strCategoria, String strCurso){
		ManejadorCategoria mC = ManejadorCategoria.getInstancia();
		Categoria categoria = mC.buscarCategoria(strCategoria);
		Curso curso = categoria.getCurso(strCurso);
		
		
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
	public String[] listarProgFormacion() {
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
			atributos_ed_curso_ret[2] = "Fecha de fin: " + edicion.getFechaFin();
			atributos_ed_curso_ret[3] = "Cupo: " + edicion.getCupo();
			atributos_ed_curso_ret[4] = "Fecha de publicacion: " + edicion.getFechaPub();
			atributos_ed_curso_ret[5] = "Docentes: " + edicion.nombresDocentes();
			return atributos_ed_curso_ret;
	}
	@Override
	public String[] AtributosPrograma(String nombrePrograma) {
		ManejadorProgramaFormacion mpf = ManejadorProgramaFormacion.getInstancia();
		ProgramaFormacion programa = mpf.buscarProgramaFormacion(nombrePrograma);
		String[] atributos_pf_curso_ret = new String[5];
		atributos_pf_curso_ret[0] = "Nombre: " + programa.getNombre();
		atributos_pf_curso_ret[1] = "Descripcion: " + programa.getDescripcion();
		atributos_pf_curso_ret[2] = "Fecha de inicio: " + programa.getFechaInicio();
	    atributos_pf_curso_ret[3] = "Fecha de fin: "+ programa.getFechaFin();
		atributos_pf_curso_ret[4] = "Fecha de Alta: " + programa.getFechaAlta();
		return atributos_pf_curso_ret;
	}
	@Override
	public String[] listarCursosP(String strPrograma){
		ManejadorProgramaFormacion mP = ManejadorProgramaFormacion.getInstancia();
		ProgramaFormacion programa= mP.buscarProgramaFormacion(strPrograma);
		ArrayList<String> cursosP;
		cursosP = programa.obtenerCursos();
		String[] cursosP_ret = new String[cursosP.size()];
        int i=0;
        for(String cur: cursosP) {
        	cursosP_ret[i]=cur;
        	i++;
        }
        return cursosP_ret;
	}
	@Override
	public String[] listarPrevias(String nombreInstituto, String nombreCurso){
		ManejadorInstituto mI = ManejadorInstituto.getInstancia();
		Instituto instituto = mI.buscarInstituto(nombreInstituto);
		Curso curso = instituto.getCurso(nombreCurso);
		ArrayList<String> previas;
		previas = curso.obtenerPrevias();
		String[] previas_ret = new String[previas.size()];
        int i=0;
        for(String p: previas) {
        	previas_ret[i]=p;
        	i++;
        }
        return previas_ret;		
	}
	@Override
	public String[] listarCategorias(){
		ManejadorCategoria mC  = ManejadorCategoria.getInstancia();
		ArrayList<String> categorias;
		categorias = mC.getNombreCategorias();
		String[] categorias_ret = new String[categorias.size()];
        int i=0;
        for(String cat: categorias) {
        	categorias_ret[i]=cat;
        	i++;
        }
        return categorias_ret;
	}
	
	@Override
	public String[] listarCategoriasC(String nombreInstituto, String nombreCurso){
		ManejadorInstituto mI = ManejadorInstituto.getInstancia();
		Instituto instituto = mI.buscarInstituto(nombreInstituto);
		Curso curso = instituto.getCurso(nombreCurso);
		ArrayList<String> categorias;
		categorias = curso.getCategorias();
		String[] categorias_ret = new String[categorias.size()];
        int i=0;
        for(String c: categorias) {
        	categorias_ret[i]=c;
        	i++;
        }
        return categorias_ret;		
	}
	@Override
	public ArrayList<String> listarCursosCategoriasP(String strPrograma){
		ManejadorProgramaFormacion mP = ManejadorProgramaFormacion.getInstancia();
		ProgramaFormacion programa= mP.buscarProgramaFormacion(strPrograma);
		ArrayList<Curso> cursosP;
		cursosP = programa.obtenerCursosP();
		
		//CATEGORIAS DE CADA CURSO
		ArrayList<String> categoriasC;
		
		//TODAS LAS CATEGORIAS 
		ArrayList<String> categoriasP_ret = new ArrayList<>();
		
        for(Curso cur: cursosP) {
        	categoriasC =cur.getCategorias();
        	
        	for(String cat: categoriasC) {
        		if(!categoriasP_ret.contains(cat))
        			categoriasP_ret.add(cat);
        	}
        	
        }
        return categoriasP_ret;
	}
	@Override
	public String obtenerInstitutoCurso(String nombreCurso) {
		ManejadorInstituto mI = ManejadorInstituto.getInstancia();
		List<Instituto> institutos = mI.getInstitutos();
		String nombreInstituto = null;
		for(Instituto i: institutos) {
			 if(i.getCurso(nombreCurso) != null) {
				 nombreInstituto = i.getNombre();
			 }
		}
		return nombreInstituto;
	}

	
}
