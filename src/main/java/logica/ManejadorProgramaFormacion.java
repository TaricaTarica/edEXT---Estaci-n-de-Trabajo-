package logica;

import java.util.ArrayList;
import java.util.List;

public class ManejadorProgramaFormacion {
	private static ManejadorProgramaFormacion instancia = null;
	private List<ProgramaFormacion> programas = new ArrayList<>();
	
	private ManejadorProgramaFormacion() {}
	
	public static ManejadorProgramaFormacion getInstancia() {
		if (instancia == null)
			instancia = new ManejadorProgramaFormacion();
		return instancia;
	}

	public void agregarProgramaFormacion(ProgramaFormacion programa) {
		programas.add(programa);
	}
	
	public ProgramaFormacion buscarProgramaFormacion(String nombre) {
		ProgramaFormacion aretornar = null;
		for(ProgramaFormacion pf: programas) {
			if (pf.getNombre().equals(nombre))
				aretornar=pf;
		}
		return aretornar;
	}
}
