package logica;

import java.time.LocalDate;
import java.util.ArrayList;

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
			throw new UsuarioRepetido_Exception("El nickname "+ u.getNickname() +" y el correo " + u.getCorreo() +" est�n registrados");
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
	
}
