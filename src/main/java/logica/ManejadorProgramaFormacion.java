package logica;

import java.util.ArrayList;
import java.util.List;

import datatypes.DtProgramaFormacion;


public class ManejadorProgramaFormacion {
	private static ManejadorProgramaFormacion instancia = null;
	private List<ProgramaFormacion> programas = new ArrayList<>();
	
	private ManejadorProgramaFormacion() {}
	
	public static ManejadorProgramaFormacion getInstancia() {
		if (instancia == null)
			instancia = new ManejadorProgramaFormacion();
		return instancia;
	}

	public void agregarProgramaFormacion(DtProgramaFormacion pf) {
		ProgramaFormacion p = new ProgramaFormacion(pf);
		programas.add(p);
	}
	
	public boolean existeProgramaFormacion(String pf) {
		boolean aretornar = false;
		for(ProgramaFormacion p: programas) {
			if (p.getNombre().equals(pf))
				aretornar=true;
		}
		return aretornar;
	}
	
	public ProgramaFormacion buscarProgramaFormacion(String pf) {
		ProgramaFormacion aretornar = null;
		for(ProgramaFormacion p: programas) {
			if (p.getNombre().equals(pf))
				aretornar=p;
		}
		return aretornar;
	}
	
	public ArrayList<String> getNombreProgramas(){
		ArrayList<String> retorno = new ArrayList<>();
		for(ProgramaFormacion i: programas) {
			retorno.add(i.getNombre());
		}
		return retorno;
	}
}
