package logica;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import datatypes.DtCurso;
import persistencia.Conexion;

public class ManejadorInstituto {
	private static ManejadorInstituto instancia = null;
	//private List<Instituto> institutos = new ArrayList<>();
	
	private ManejadorInstituto() {}
	
	public static ManejadorInstituto getInstancia() {
		if (instancia == null)
			instancia = new ManejadorInstituto();
		return instancia;
	}

	public void agregarInstituto(Instituto instituto) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		em.getTransaction().begin();
		
		em.persist(instituto);
		
		em.getTransaction().commit();
	}
	
	public Instituto buscarInstituto(String nombre) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		Instituto instituto = em.find(Instituto.class, nombre);
		return instituto;
	}
	public boolean existeInstituto(String nombre) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		Instituto instituto = em.find(Instituto.class, nombre);
		if(instituto != null) {
			return true;
		}
		return false;
	}
	public ArrayList<String> getNombreInstitutos(){
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
	
		Query query = em.createQuery("select i from Instituto i");
		List<Instituto> listInstitutos = (List<Instituto>) query.getResultList();
		
		ArrayList<String> retorno = new ArrayList<>();
		for(Instituto i: listInstitutos) {
			retorno.add(i.getNombre());
		}
		return retorno;
	}
	
	/*public ArrayList<String> getNombreCursos(String strInstituto){
		ArrayList<String> retorno = new ArrayList<>();

		// ACA TENGO QUE PEDIRLE EL NOMBRE DE CADA CURSO al instituto
		
		//retorno = instituto.obterneCusso()
		
		
		for(Instituto i: institutos) {
			retorno.add(i.getCurso(strInstituto));
		}
		return retorno;
	}*/
	
	public List<Instituto> getInstitutos(){
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
	
		Query query = em.createQuery("select i from Instituto i");
		List<Instituto> listInstitutos = (List<Instituto>) query.getResultList();
		return listInstitutos;
	}
	
}
