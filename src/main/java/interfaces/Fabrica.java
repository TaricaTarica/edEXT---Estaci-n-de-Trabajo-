package interfaces;

import logica.ControladorCurso;
import logica.ControladorUsuario;

public class Fabrica {
	private static Fabrica instancia = null;
	private Fabrica(){}
	
	public static Fabrica getInstancia() {
		if (instancia == null)
			instancia = new Fabrica();
		return instancia;
	}
	
	public IControladorUsuario getIControladorUsuario() {
		return new ControladorUsuario();
	}
	public IControladorCurso getIControladorCurso() {
		return new ControladorCurso();
	}
}
