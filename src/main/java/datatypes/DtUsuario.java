package datatypes;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtUsuario {
	private String nickname;
	private String nombre;
	private String apellido;
	private String correo;
	private Calendar fechaNac;
	private String contrasenia;
	
	//constructores
		public DtUsuario() {}
		public DtUsuario(final String nickname, final String nombre, final String apellido, final String correo, final Calendar fechaNac, final String contrasenia) {
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
		public Calendar getfechaNac() {
			return fechaNac;
		}
		public void setFechaNac(Calendar fechaNac) {
			this.fechaNac = fechaNac;
		}
		public String getContrasenia() {
			return contrasenia;
		}
		public void setContrasenia(String contrasenia) {
			this.contrasenia = contrasenia;
		}
		
}
