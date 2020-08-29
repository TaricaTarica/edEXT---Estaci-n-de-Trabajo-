package logica;

import java.util.ArrayList;
import java.util.List;

public class ManejadorInstituto {
	private static ManejadorInstituto instancia = null;
	private List<Instituto> institutos = new ArrayList<>();
	
	private ManejadorInstituto() {}
	
	public static ManejadorInstituto getInstancia() {
		if (instancia == null)
			instancia = new ManejadorInstituto();
		return instancia;
	}

	public void agregarInstituto(Instituto instituto) {
		institutos.add(instituto);
	}
	
	public Instituto buscarInstituto(String nombre) {
		Instituto aretornar = null;
		for(Instituto i: institutos) {
			if (i.getNombre().equals(nombre))
				aretornar= i;
		}
		return aretornar;
	}
	public boolean existeInstituto(String nombre) {
		boolean retorno = false;
		for(Instituto i: institutos) {
			if(i.getNombre().equals(nombre))
				retorno = true;
		}
		return retorno;
	}
	public ArrayList<String> getNombreInstitutos(){
		ArrayList<String> retorno = new ArrayList<>();
		for(Instituto i: institutos) {
			retorno.add(i.getNombre());
		}
		return retorno;
	}
}
