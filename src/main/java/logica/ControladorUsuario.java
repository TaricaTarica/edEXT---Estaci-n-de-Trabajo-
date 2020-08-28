package logica;

import java.util.Date;

import interfaces.IControladorUsuario;

public class ControladorUsuario implements IControladorUsuario {
	private String nickname;
	private String nombre; 
	private String apellido; 
	private String correo;
	private Date fNac;
	private String institutoDocente;
	
	public ControladorUsuario() {}
	/*IR AGREGANDO LAS OPERACIONES A MEDIDA QUE SE REQUIERAN/IMPLEMENTEN*/
	
	/*AltaUsuario*/
	@Override
	public void ingresarUsuario(String nickname, String nombre, String apellido, String correo, Date fNac) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.fNac = fNac;
	}
	@Override
	public void ingresarInstituto(String nombreInstituto) {
		this.institutoDocente = nombreInstituto;
	}
	@Override
	public void confirmarAlta() {
		if(this.institutoDocente.isEmpty()) {
			Estudiante e = new Estudiante(this.nickname,this.nombre,this.apellido,this.correo,this.fNac);
			ManejadorUsuario mU = ManejadorUsuario.getInstancia();
			mU.agregarUsuario(e);
		}
		else {
			Docente d = new Docente(this.nickname,this.nombre,this.apellido,this.correo,this.fNac);
			ManejadorInstituto mI = ManejadorInstituto.getInstancia();
			Instituto i = mI.buscarInstituto(institutoDocente);
			d.setInstituto(i);
			ManejadorUsuario mU = ManejadorUsuario.getInstancia();
			mU.agregarUsuario(d);
		}
		this.institutoDocente = "";
	}
	
	
}
