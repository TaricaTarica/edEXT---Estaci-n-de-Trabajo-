package datatypes;

import java.time.LocalDate;
import java.util.Date;

public class DtUsuario {
	private String nickname;
	private String nombre;
	private String apellido;
	private String correo;
	private LocalDate fechaNac;
	private String contrasenia;
	
	//constructores
		public DtUsuario() {}
		public DtUsuario(String nickname, String nombre, String apellido, String correo, LocalDate fechaNac, String contrasenia) {
			super();
			this.nickname = nickname;
			this.nombre = nombre;
			this.apellido = apellido;
			this.correo = correo;
			this.fechaNac = fechaNac;
			this.contrasenia = contrasenia;
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
		public LocalDate getfechaNac() {
			return fechaNac;
		}
		public void setFechaNac(LocalDate fechaNac) {
			this.fechaNac = fechaNac;
		}
		public String getContrasenia() {
			return contrasenia;
		}
		public void setContrasenia(String contrasenia) {
			this.contrasenia = contrasenia;
		}
		
}
