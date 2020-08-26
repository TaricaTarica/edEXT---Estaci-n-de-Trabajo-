package logica;

import java.util.Date;

public abstract class Usuario {
	private String nickname;
	private String nombre;
	private String apellido;
	private String correo;
	private Date fechaNac;
	
	//constructores
	public Usuario() {}
	public Usuario(String nickname, String nombre, String apellido, String correo, Date fechaNac) {
		super();
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.fechaNac = fechaNac;
	}
	
	//getters-setters
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public Date getfechaNac() {
		return fechaNac;
	}
	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}
	
	//operaciones
	
	//public abstract DtUsuario getinfoUsuario();	
	
}
