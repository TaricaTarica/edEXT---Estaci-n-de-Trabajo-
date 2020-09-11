package logica;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import datatypes.DtProgramaFormacion;
import persistencia.Conexion;


public class ManejadorProgramaFormacion {
	private static ManejadorProgramaFormacion instancia = null;
	//private List<ProgramaFormacion> programas = new ArrayList<>();
	
	private ManejadorProgramaFormacion() {}
	
	public static ManejadorProgramaFormacion getInstancia() {
		if (instancia == null)
			instancia = new ManejadorProgramaFormacion();
		return instancia;
	}

	public void agregarProgramaFormacion(ProgramaFormacion pf) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		em.getTransaction().begin();
		
		em.persist(pf);
		
		em.getTransaction().commit();

	}
	
	public boolean existeProgramaFormacion(String pf) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		ProgramaFormacion programaformacion = em.find(ProgramaFormacion.class, pf);
		if(programaformacion != null) {
			return true;
		}
		return false;
	}
	
	public ProgramaFormacion buscarProgramaFormacion(String pf) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		ProgramaFormacion programaformacion = em.find(ProgramaFormacion.class, pf);
		return programaformacion;
	}
	
	public ArrayList<String> getNombreProgramas(){
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
	
		Query query = em.createQuery("select p from ProgramaFormacion p");
		List<ProgramaFormacion> listProgramasFormacion = (List<ProgramaFormacion>) query.getResultList();
		
		ArrayList<String> retorno = new ArrayList<>();
		for(ProgramaFormacion pf: listProgramasFormacion) {
			retorno.add(pf.getNombre());
		}
		return retorno;
	}
}
