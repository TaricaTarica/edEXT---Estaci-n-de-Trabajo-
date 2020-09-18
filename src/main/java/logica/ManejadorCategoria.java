package logica;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import persistencia.Conexion;

public class ManejadorCategoria {
	private static ManejadorCategoria instancia = null;
	
	
	private ManejadorCategoria() {}
	
	public static ManejadorCategoria getInstancia() {
		if (instancia == null)
			instancia = new ManejadorCategoria();
		return instancia;
	}

	public void agregarCategoria(Categoria categoria) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		em.getTransaction().begin();
		
		em.persist(categoria);
		
		em.getTransaction().commit();
	}
	
	public Categoria buscarCategoria(String nombre) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		Categoria categoria = em.find(Categoria.class, nombre);
		return categoria;
	}
	public boolean existeCategoria(String nombre) {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		Categoria categoria = em.find(Categoria.class, nombre);
		if(categoria != null) {
			return true;
		}
		return false;
	}
	public ArrayList<String> getNombreCategorias(){
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
	
		Query query = em.createQuery("select c from Categoria c");
		List<Categoria> listCategorias = (List<Categoria>) query.getResultList();
		
		ArrayList<String> retorno = new ArrayList<>();
		for(Categoria c: listCategorias) {
			retorno.add(c.getNombre());
		}
		return retorno;
	}
}
