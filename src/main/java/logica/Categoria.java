package logica;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Categoria {
	@Id
	private String nombre;
		//constructores
		public Categoria() {
			super();
		}
		public Categoria(String nombre) {
			super();
			this.nombre = nombre;
		}
		
		//getters-setters
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		
}
