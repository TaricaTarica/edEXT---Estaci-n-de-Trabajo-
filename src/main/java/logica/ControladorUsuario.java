package logica;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;

import interfaces.IControladorUsuario;
import persistencia.Conexion;
import datatypes.DtDocente;
import datatypes.DtEstudiante;
import datatypes.DtUsuario;
import excepciones.UsuarioRepetido_Exception;

public class ControladorUsuario implements IControladorUsuario {
	
	public ControladorUsuario() {}
	private String nombreInstituto;
	/*IR AGREGANDO LAS OPERACIONES A MEDIDA QUE SE REQUIERAN/IMPLEMENTEN*/
	
	
	/*AltaUsuario*/
	@Override
	public void ingresarInstitutoDocente(String nombreInstituto) {
		this.nombreInstituto = nombreInstituto;
	}
	@Override
	public void confirmarAlta(DtUsuario u) throws UsuarioRepetido_Exception {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario nuevoUsr = mU.buscarUsuario(u.getNickname());
		boolean existeCorreo = mU.existeCorreo(u.getCorreo());
		/*convierto a LOCALDATE*/
		Calendar calendar = u.getfechaNac();
		LocalDate fechaLocalDate = LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId()).toLocalDate();
		
		if(nuevoUsr != null || existeCorreo == true)
			throw new UsuarioRepetido_Exception("El nickname "+ u.getNickname() +" y/o el correo " + u.getCorreo() +" ya estan registrados");
		if(u instanceof DtEstudiante) {
			nuevoUsr = new Estudiante(u.getNickname(),u.getNombre(),u.getApellido(),u.getCorreo(),fechaLocalDate, u.getContrasenia());
			mU.agregarUsuario(nuevoUsr);
		}
		if(u instanceof DtDocente) {
			ManejadorInstituto mI = ManejadorInstituto.getInstancia();
			Instituto institutoDocente = mI.buscarInstituto(this.nombreInstituto);
			Docente docente = new Docente (u.getNickname(),u.getNombre(),u.getApellido(),u.getCorreo(),fechaLocalDate, u.getContrasenia());
			docente.setInstituto(institutoDocente);
			mU.agregarUsuario(docente);
		}
	}
	/*DIVIDO LA FUNCIÓN CONFIRMARALTA EN DOS*/
	@Override
	public void confirmarAltaEstudiante(DtEstudiante u) throws UsuarioRepetido_Exception {	
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario nuevoUsr = mU.buscarUsuario(u.getNickname());
		boolean existeCorreo = mU.existeCorreo(u.getCorreo());
		Calendar calendar = u.getfechaNac();
		LocalDate fechaLocalDate = LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId()).toLocalDate();
		if(nuevoUsr != null || existeCorreo == true)
			throw new UsuarioRepetido_Exception("El nickname "+ u.getNickname() +" y/o el correo " + u.getCorreo() +" ya estan registrados");
		else{
			nuevoUsr = new Estudiante(u.getNickname(),u.getNombre(),u.getApellido(),u.getCorreo(),fechaLocalDate, u.getContrasenia());
			mU.agregarUsuario(nuevoUsr);
		}
	}
	@Override
	public void confirmarAltaDocente(DtDocente u) throws UsuarioRepetido_Exception {	
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario nuevoUsr = mU.buscarUsuario(u.getNickname());
		boolean existeCorreo = mU.existeCorreo(u.getCorreo());
		Calendar calendar = u.getfechaNac();
		LocalDate fechaLocalDate = LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId()).toLocalDate();
		if(nuevoUsr != null || existeCorreo == true)
			throw new UsuarioRepetido_Exception("El nickname "+ u.getNickname() +" y/o el correo " + u.getCorreo() +" ya estan registrados");
		else{
			ManejadorInstituto mI = ManejadorInstituto.getInstancia();
			Instituto institutoDocente = mI.buscarInstituto(this.nombreInstituto);
			Docente docente = new Docente (u.getNickname(),u.getNombre(),u.getApellido(),u.getCorreo(),fechaLocalDate, u.getContrasenia());
			docente.setInstituto(institutoDocente);
			mU.agregarUsuario(docente);
		}
		
	}
	
	/*Consulta Usuario*/
	@Override
	public DtUsuario ConsultaUsuario(String strUsuario) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario usuario = mU.buscarUsuarioC(strUsuario);
		String nickname = usuario.getNickname();
		String nombre = usuario.getNombre();
		String apellido = usuario.getApellido();
		String correo = usuario.getCorreo();
		LocalDate fechaNac = usuario.getfechaNac();
		String contrasenia = usuario.getContrasenia();
		
		Calendar fechaCalendar = GregorianCalendar.from(fechaNac.atStartOfDay(ZoneId.systemDefault()));
		
		DtUsuario retorno = new DtUsuario(nickname, nombre, apellido, correo, fechaCalendar, contrasenia);
		
		return retorno;
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
	
	public String[] listarUsuarios(){
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		ArrayList<String> usuarios;
		usuarios = mU.getNombreUsuarios();
		String[] usuarios_ret = new String[usuarios.size()];
        int u=0;
        for(String usu: usuarios) {
        	usuarios_ret[u]=usu;
        	u++;
        }
        return usuarios_ret;
	}
	
	@Override
	public String getNombreUsuario(String nickname) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario usr = mU.buscarUsuario(nickname);
		return usr.getNombre();
	}
	
	@Override
	public String getApellidoUsuario(String nickname) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario usr = mU.buscarUsuario(nickname);
		return usr.getApellido();
	}
	@Override
	public String getContraseniaUsuario(String nickname) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario usr = mU.buscarUsuario(nickname);
		return usr.getContrasenia();

	}

	@Override
	public LocalDate getFechaUsuario(String nickname) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario usr = mU.buscarUsuario(nickname);
		return usr.getfechaNac();
	}
	
	@Override
	public void modificarUsuario(String nickname, String nombre, String apellido, LocalDate fechaN,String contrasenia) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario usr = mU.buscarUsuario(nickname);
		usr.setNombre(nombre);
		usr.setApellido(apellido);
		usr.setFechaNac(fechaN);
		usr.setContrasenia(contrasenia);

		
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		em.getTransaction().begin();
		em.persist(usr);
		em.getTransaction().commit();
	}
	@Override
	public boolean esEstudiante(String strNickname) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario usr = mU.buscarUsuario(strNickname);
		boolean es=false;
		if(usr instanceof Estudiante) {
			es=true;
		}else if(usr instanceof Docente) {
			es=false;
		}
		return es;
	}
	@Override
	public String[] listarEdicionesD(String strDocente) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario usr = mU.buscarUsuario(strDocente);
		List<Edicion> ediciones=((Docente) usr).obtenerEdicionesAsociados();
		String[] ediciones_ret = new String[ediciones.size()];
        int i=0;
        for(Edicion ed: ediciones) {
        	ediciones_ret[i]=ed.getNombre();
        	i++;
        }
        return ediciones_ret;	 
	}
	@Override
	public String[] listarEdicionesE(String strEstudiante) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario usr = mU.buscarUsuario(strEstudiante);
		String[] ediciones_ret = ((Estudiante) usr).obtenerEdicionesE();
        return ediciones_ret;	 
	}
	@Override
	public String[] listarProgramasE(String strEstudiante) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario usr = mU.buscarUsuario(strEstudiante);
		String[] programas_ret = ((Estudiante) usr).obtenerProgramasE();
        return programas_ret;	 
	}
	@Override
	public String[] AtributosEdicion(String nombreUsuario, String nombreEdicion) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario usuario = mU.buscarUsuario(nombreUsuario);
		String[] atributos_ed_curso_ret = new String[6];
		if(esEstudiante(usuario.getNickname())) {
			 List<Edicion> ediciones = new ArrayList<>();
			ediciones=((Estudiante) usuario).obtenerEdicionesEd();
			Edicion aretornar=null;			
			for(Edicion ed: ediciones) {
				if (ed.getNombre().equals(nombreEdicion)) {
					aretornar=ed;
				}
					
			}
				atributos_ed_curso_ret[0] = "Nombre: " + aretornar.getNombre();
				atributos_ed_curso_ret[1] = "Fecha de inicio: " + aretornar.getFechaIni();
				atributos_ed_curso_ret[2] = "Fecha de fin: " + aretornar.getFechaFin();
				atributos_ed_curso_ret[3] = "Cupo: " + aretornar.getCupo();
				atributos_ed_curso_ret[4] = "Fecha de publicacion: " + aretornar.getFechaPub();
				atributos_ed_curso_ret[5] = "Docentes: " + aretornar.nombresDocentes();
		}else{
			List<Edicion> ediciones = new ArrayList<>();
			ediciones=((Docente) usuario).obtenerEdicionesAsociados()	;
			Edicion aretornar=null;			
			for(Edicion ed: ediciones) {
				if (ed.getNombre().equals(nombreEdicion)) {
					aretornar=ed;
				}
					
			}
				atributos_ed_curso_ret[0] = "Nombre: " + aretornar.getNombre();
				atributos_ed_curso_ret[1] = "Fecha de inicio: " + aretornar.getFechaIni();
				atributos_ed_curso_ret[2] = "Fecha de fin: " + aretornar.getFechaFin();
				atributos_ed_curso_ret[3] = "Cupo: " + aretornar.getCupo();
				atributos_ed_curso_ret[4] = "Fecha de publicacion: " + aretornar.getFechaPub();
				atributos_ed_curso_ret[5] = "Docentes: " + aretornar.nombresDocentes();
		}
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
	public Instituto GetInstituto(String strDocente) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario usr = mU.buscarUsuario(strDocente);
		Instituto instituto=((Docente) usr).getInstituto();
        return instituto;	 
	}
	@Override
	public String GetCurso(String strEdicion,List<Curso> Cursos) {
		String curso=null;
        for(Curso cur: Cursos) {
			 if(cur.getEdicion(strEdicion) != null) {
				 curso = cur.getNombre();
			 }
		}
        return curso;	 
	}
	@Override
	public String[] listarEdicionesEst(String strEstudiante) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario usr = mU.buscarUsuario(strEstudiante);
		String[] ediciones_ret = ((Estudiante) usr).obtenerEdicionesEst();
        return ediciones_ret;	 
	}
	public Edicion ObtenerEdicion(String strEdicion,String strEstudiante) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario usr = mU.buscarUsuario(strEstudiante);
		Edicion ed = ((Estudiante) usr).ObtenerEdicion(strEdicion);
        return ed;	 
	}
	
	public boolean existeUsuario (String nickname) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario usr = mU.buscarUsuario(nickname);
		if(usr!=null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public void seguirUsuario(String nickname, String aSeguir) {
		/*Agrega al segundo a la lista de seguidos del primero y al primero a la lista de seguidores del segundo*/
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario usuario = mU.buscarUsuario(nickname);
		Usuario seguido = mU.buscarUsuario(aSeguir);
		usuario.seguirUsuario(seguido);
		seguido.agregarSeguidor(usuario);
		
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		em.getTransaction().begin();
		em.persist(usuario);
		em.persist(seguido);
		em.getTransaction().commit();
	}
	@Override
	public boolean esSeguidor(String nickname, String aComprobar) {
		/*Retorna true si el primero sigue al segundo*/
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario usuario = mU.buscarUsuario(nickname);
		List<Usuario> sigue = usuario.getSeguidos();
		boolean retorno = false;
		for(Usuario u: sigue) {
			if(u.getNickname().equals(aComprobar)) {
				retorno = true;
			}
		}
		return retorno;
	}
	@Override
	public void dejarSeguir(String nickname, String dejarSeguir) {
		/*elimina al segundo de la lista de seguidos del primero y al primero de la lista de seguidores del segundo*/
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario usuario = mU.buscarUsuario(nickname);
		Usuario seguido = mU.buscarUsuario(dejarSeguir);
		List<Usuario> usuarioSigue = usuario.getSeguidores();
		List<Usuario> seguidoSeguidores = seguido.getSeguidores();
		for (Usuario u: usuarioSigue) {
			if(u.getNickname().equals(dejarSeguir)) {
				usuario.dejarSeguirUsuario(seguido);
			}
		}
		for (Usuario u: seguidoSeguidores) {
			if(u.getNickname().equals(nickname)) {
				seguido.quitarSeguidor(usuario);
			}
		}
	}
	@Override
	public List<DtUsuario> obtenerSeguidores(String nickname){
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario u = mU.buscarUsuario(nickname);
		Calendar fechaCalendar = GregorianCalendar.from(u.getfechaNac().atStartOfDay(ZoneId.systemDefault()));
		List<Usuario> seguidores = u.getSeguidores();
		List<DtUsuario> seguidoresRetorno = new ArrayList<>();
		for(Usuario usr: seguidores) {
			DtUsuario dtu = new DtUsuario(usr.getNickname(), usr.getNombre(), usr.getApellido(), usr.getCorreo(), fechaCalendar, usr.getContrasenia());
			seguidoresRetorno.add(dtu);
		}
		return seguidoresRetorno;
	}
	@Override
	public List<DtUsuario> obtenerSeguidos(String nickname){
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario u = mU.buscarUsuario(nickname);
		Calendar fechaCalendar = GregorianCalendar.from(u.getfechaNac().atStartOfDay(ZoneId.systemDefault()));
		List<Usuario> seguidos = u.getSeguidos();
		List<DtUsuario> seguidosRetorno = new ArrayList<>();
		for(Usuario usr: seguidos) {
			DtUsuario dtu = new DtUsuario(usr.getNickname(), usr.getNombre(), usr.getApellido(), usr.getCorreo(), fechaCalendar, usr.getContrasenia());
			seguidosRetorno.add(dtu);
		}
		return seguidosRetorno;
	}
	

}