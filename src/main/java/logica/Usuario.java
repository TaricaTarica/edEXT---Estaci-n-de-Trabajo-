package logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Usuario {
	@Id
	private String nickname;
	
	private String nombre;
	private String apellido;
	private String correo;
	private LocalDate fechaNac;
	private String contrasenia;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Usuario> seguidos = new ArrayList<Usuario>();
	
	@ManyToMany(mappedBy = "seguidos")
	private List<Usuario> seguidores = new ArrayList<Usuario>();

	
	//constructores
	public Usuario() {
		super();
	}
	public Usuario(String nickname, String nombre, String apellido, String correo, LocalDate fechaNac, String contrasenia) {
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
	
	public List<Usuario> getSeguidos() {
		return seguidos;
	}

	public void setSeguidos(List<Usuario> seguidos) {
		this.seguidos = seguidos;
	}
	
	public List<Usuario> getSeguidores() {
		return seguidores;
	}

	public void setSeguidores(List<Usuario> seguidores) {
		this.seguidores = seguidores;
	}
	
	//Modificar los seguidos
	public void seguirUsuario(Usuario u) {
		if (!this.seguidos.contains(u)) {
			this.seguidos.add(u);
			u.agregarSeguidor(this);
		}
	}
	
	public void dejarSeguirUsuario(Usuario u) {
		this.seguidos.remove(u);
		u.quitarSeguidor(this);
	}
	
	//Modificar los seguidores
	public void agregarSeguidor(Usuario u) {
		if (!this.seguidores.contains(u)) {
			this.seguidores.add(u);			
		}

	}
	public void quitarSeguidor(Usuario u) {
		this.seguidores.remove(u);
	}

	
}
