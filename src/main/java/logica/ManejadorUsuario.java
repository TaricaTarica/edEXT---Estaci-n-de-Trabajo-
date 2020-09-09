package logica;

import java.util.ArrayList;
import java.util.List;

public class ManejadorUsuario {
	private static ManejadorUsuario instancia = null;
	private List<Usuario> usuarios = new ArrayList<>();
	
	private ManejadorUsuario() {}
	
	public static ManejadorUsuario getInstancia() {
		if (instancia == null)
			instancia = new ManejadorUsuario();
		return instancia;
	}

	public void agregarUsuario(Usuario usuario) {
		usuarios.add(usuario);
	}
	
	public Usuario buscarUsuario(String nickname, String correo) {
		Usuario aretornar = null;
		for(Usuario u: usuarios) {
			if (u.getNombre().equals(nickname) && u.getCorreo().equals(correo))
				aretornar=u;
		}
		return aretornar;
	}
	
	public Usuario buscarUsuario2(String nickname) {
		Usuario aretornar = null;
		for(Usuario u: usuarios) {
			if (u.getNickname().equals(nickname))
				aretornar=u;
		}
		return aretornar;
	}
	
	public Usuario buscarUsuarioC(String nickname) {
		Usuario aretornar = null;
		for(Usuario u: usuarios) {
			if (u.getNickname().equals(nickname))
				aretornar=u;
		}
		return aretornar;
	}
	
	public ArrayList<String> getNombreUsuarios(){
		ArrayList<String> retorno = new ArrayList<>();
		for(Usuario u: usuarios) {
			retorno.add(u.getNickname());
		}
		return retorno;
	}
	
	public ArrayList<String> getDocentes(){
		ArrayList<String> retorno = new ArrayList<>();
		for(Usuario u: usuarios) {
			if(u instanceof Docente) {
				retorno.add(u.getNickname());
			}
		}
		return retorno;
	}
	
	public ArrayList<String> getEstudiantes(){
		ArrayList<String> retorno = new ArrayList<>();
		for(Usuario u: usuarios) {
			if(u instanceof Estudiante) {
				retorno.add(u.getNickname());
			}
		}
		return retorno;
	}
	public Estudiante BuscarEstudiante(String nickname){
		Estudiante e=null;
		for(Usuario u: usuarios) {
			if(u instanceof Estudiante) {
				if(u.getNickname().equals(nickname)){
					e=(Estudiante) u;
				}
			}
		}
		return e;
	}

	
}
