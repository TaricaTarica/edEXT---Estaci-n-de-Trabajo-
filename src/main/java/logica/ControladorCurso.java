package logica;

import interfaces.IControladorCurso;

public class ControladorCurso implements IControladorCurso {
	String nombreInstituto;
	public ControladorCurso(){}
	
	/*IR AGREGANDO LAS OPERACIONES A MEDIDA QUE SE REQUIERAN/IMPLEMENTEN*/
	
	@Override
	public void ingresarInstituto(String nombreInstituto) {
		this.nombreInstituto = nombreInstituto;
	}
	@Override
	public void AltaInstituto() {
		Instituto i = new Instituto(this.nombreInstituto);
		ManejadorInstituto mI = ManejadorInstituto.getInstancia();
		mI.agregarInstituto(i);
	}
}
