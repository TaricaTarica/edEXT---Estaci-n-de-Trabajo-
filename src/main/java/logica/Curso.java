package logica;

import datatypes.DtCurso;
import datatypes.DtEdicion;
import persistencia.Conexion;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import java.util.ArrayList;

@Entity
public class Curso {
	@Id
	private String nombre;
	private String descripcion;
	private String duracion;
	private int cantHoras;	//IMPLEMENTAR DtTime (horas y minutos)
	private int cantCreditos;
	private LocalDate fechaAlta;
	private String url;
	private String img;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Edicion> ediciones = new ArrayList<>();
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Curso> previas = new ArrayList<>();
	
	private ArrayList<String> programasAsociados = new ArrayList<>(); //tendr�a que ser igual a InscripcionED
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Categoria> categorias = new ArrayList<>();
	
	//constructores
	public Curso() {
		super();
	}
	
	//CONSTRUCTOR CON ATRIBUTOS
	/*public Curso(String nombre, String descripcion, String duracion, int cantHoras, int cantCreditos, Date fechaAlta,
			String url) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.duracion = duracion;
		this.cantHoras = cantHoras;
		this.cantCreditos = cantCreditos;
		this.fechaAlta = fechaAlta;
		this.url = url;
	}*/
	
	//CONSTRUCTOR CON DATATYPE	
	public Curso(DtCurso dtc) {
		this.nombre = dtc.getNombre();
		this.descripcion = dtc.getDescripcion();
		this.duracion = dtc.getDuracion();
		this.cantHoras = dtc.getCantHoras();
		this.cantCreditos = dtc.getCantCreditos();
		this.fechaAlta = dtc.getFechaAlta();
		this.url = dtc.getUrl();
		this.img = dtc.getImg();
	}

	//getters-setters
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDuracion() {
		return duracion;
	}
	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}
	public int getCantHoras() {
		return cantHoras;
	}
	public void setCantHoras(int cantHoras) {
		this.cantHoras = cantHoras;
	}
	public int getCantCreditos() {
		return cantCreditos;
	}
	public void setCantCreditos(int creditos) {
		this.cantCreditos = creditos;
	}
	public LocalDate getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(LocalDate fechaR) {
		this.fechaAlta = fechaR;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setPrevia(Curso c) {
		this.previas.add(c);
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getImg() {
		return this.img;
	}
	
	// Métodos para ediciones
	public void agregarEdicion(DtEdicion dte) {
		Edicion e = new Edicion(dte);
		ediciones.add(e);
		
		/*Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		em.getTransaction().begin();
		em.persist(e);
		em.getTransaction().commit();*/
	}
	public boolean BuscarEdicion(String ed) {;
		boolean aretornar=false;
		for(Edicion e: ediciones) {
			if (e.getNombre().equals(ed))
				aretornar=true;
		}
		return aretornar;
	}	
	
	public ArrayList<String> obtenerEdiciones(){
		ArrayList<String> lista = new ArrayList<>();
		for(Edicion e:ediciones) {
			lista.add(e.getNombre());
		}
		return lista;
	}
	
	//Métodos para programas asociados
	public void asociarPrograma(String strPrograma){
		this.programasAsociados.add(strPrograma);
	}
	
	public ArrayList<String> obtenerProgramasAsociados(){
		return this.programasAsociados;
	}
	
	public Edicion getEdicion(String nombre) {
		Edicion retorno = new Edicion();
		for(Edicion e: this.ediciones) {
			if(e.getNombre().equals(nombre)) {
				retorno = e;
			} 
		}
		return retorno;
	}
	
	public List<Edicion> getEdiciones(){
		List<Edicion> edicionesRetorno = new ArrayList<>();
		for(Edicion c: this.ediciones) {
			edicionesRetorno.add(c);
		}
		return edicionesRetorno;
	}
	public ArrayList<String> obtenerPrevias(){
		ArrayList<String> lista = new ArrayList<>();
		for(Curso p: previas) {
			lista.add(p.getNombre());
		}
		return lista;
	}
	public Categoria getCategoria(Categoria nombre) {
		Categoria retorno = null;
		for(Categoria e: this.categorias) {
			if(e.equals(nombre)) {
				retorno = e;
			} 
		}
		return retorno;
	}
	public void setCategorias(Categoria categoria){
		this.categorias.add(categoria);
	}
	public ArrayList<String>  getCategorias(){
		ArrayList<String> lista = new ArrayList<>();
		for(Categoria c: categorias) {
			lista.add(c.getNombre());
		}
		return lista;
	}
}
