package logica;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import persistencia.Conexion;

public class ManejadorUsuario {
	private static ManejadorUsuario instancia = null;
	//private List<Usuario> usuarios = new ArrayList<>();
	
	private ManejadorUsuario() {}
	
	public static ManejadorUsuario getInstancia() {
		if (instancia == null)
			instancia = new ManejadorUsuario();
		return instancia;
	}

	public void agregarUsuario(Usuario usuario) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		em.getTransaction().begin();
		
		em.persist(usuario);
		
		em.getTransaction().commit();
	}
	
	
	public Usuario buscarUsuario(String nickname) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		Usuario usuario = em.find(Usuario.class, nickname);
		return usuario;
	}
	
	public Usuario buscarUsuarioC(String nickname) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		Usuario usuario = em.find(Usuario.class, nickname);
		return usuario;
	}
	
	public ArrayList<String> getNombreUsuarios(){
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
	
		Query query = em.createQuery("select u from Usuario u");
		
		List<Usuario> listUsuarios = (List<Usuario>) query.getResultList();
		
		ArrayList<String> aRetornar = new ArrayList<>();
		for(Usuario u: listUsuarios) {
			aRetornar.add(new String(u.getNickname()));
		}
		return aRetornar;
	}
	
	public ArrayList<String> getDocentes(){
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
	
		Query query = em.createQuery("select d from Docente d");
		
		List<Docente> listDocentes = (List<Docente>) query.getResultList();
		
		ArrayList<String> aRetornar = new ArrayList<>();
		for(Docente  d: listDocentes) {
			aRetornar.add(new String(d.getNickname()));
		}
		return aRetornar;
	}
	public ArrayList<String> getDocentesInstituto(String Instituto){
		//devuelve los docentes asociados a determinado instituto
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
	
		Query query = em.createQuery("select d from Docente d");
		
		List<Docente> listDocentes = (List<Docente>) query.getResultList();
		
		ArrayList<String> aRetornar = new ArrayList<>();
		for(Docente  d: listDocentes) {
			if(d.getInstituto().getNombre().equals(Instituto))
				aRetornar.add(new String(d.getNickname()));
		}
		return aRetornar;
	}
	
	public ArrayList<String> getEstudiantes(){
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
	
		Query query = em.createQuery("select e from Estudiante e");
		
		List<Estudiante> listEstudiantes = (List<Estudiante>) query.getResultList();
		
		ArrayList<String> aRetornar = new ArrayList<>();
		for(Estudiante  e: listEstudiantes) {
			aRetornar.add(new String(e.getNickname()));
		}
		return aRetornar;
	}
	public Estudiante BuscarEstudiante(String nickname){
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		Estudiante estudiante = em.find(Estudiante.class, nickname);
		return estudiante;
		
	}
	public boolean existeCorreo(String correo) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		
		boolean existe = !em.createQuery("SELECT correo FROM Usuario WHERE correo = :correo").setParameter("correo", correo).getResultList().isEmpty();
		return existe;
	}

	
}
