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
}
