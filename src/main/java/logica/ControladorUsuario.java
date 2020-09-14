package logica;

import java.time.LocalDate;
import java.util.ArrayList;
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
		if(nuevoUsr != null)
			throw new UsuarioRepetido_Exception("El nickname "+ u.getNickname() +" y/o el correo " + u.getCorreo() +" ya estan registrados");
		if(u instanceof DtEstudiante) {
			nuevoUsr = new Estudiante(u.getNickname(),u.getNombre(),u.getApellido(),u.getCorreo(),u.getfechaNac());
			mU.agregarUsuario(nuevoUsr);
		}
		if(u instanceof DtDocente) {
			ManejadorInstituto mI = ManejadorInstituto.getInstancia();
			Instituto institutoDocente = mI.buscarInstituto(this.nombreInstituto);
			Docente docente = new Docente (u.getNickname(),u.getNombre(),u.getApellido(),u.getCorreo(),u.getfechaNac());
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
		
		DtUsuario retorno = new DtUsuario(nickname, nombre, apellido, correo, fechaNac);
		
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
	public LocalDate getFechaUsuario(String nickname) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario usr = mU.buscarUsuario(nickname);
		return usr.getfechaNac();
	}
	
	@Override
	public void modificarUsuario(String nickname, String nombre, String apellido, LocalDate fechaN) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario usr = mU.buscarUsuario(nickname);
		usr.setNombre(nombre);
		usr.setApellido(apellido);
		usr.setFechaNac(fechaN);
		
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
}