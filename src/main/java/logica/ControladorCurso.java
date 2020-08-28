package logica;

import java.util.Date;

import excepciones.InstitutoRepetido_Exception;
import interfaces.IControladorCurso;

public class ControladorCurso implements IControladorCurso {
	private String nombreInstituto;
	private String nombreProgFormacion;
	private String descripcion;
	private Date fechaInicio;
	private Date fechaFin;
	private Date fechaAlta;
	private String nombreCurso;
	private String nombrePrevia;
	private String duracion;
	private int horas;
	private int creditos;
	private String url;
	private Date fechaR;
	public ControladorCurso(){}
	
	/*IR AGREGANDO LAS OPERACIONES A MEDIDA QUE SE REQUIERAN/IMPLEMENTEN*/
	
	/*Alta Instituto*/
	@Override
	public void AltaInstituto(String nombreInstituto) throws InstitutoRepetido_Exception {
		ManejadorInstituto mI = ManejadorInstituto.getInstancia();
		Instituto i = mI.buscarInstituto(nombreInstituto);
		if (i != null) {
			throw new InstitutoRepetido_Exception("¡El instituto " + nombreInstituto + " ya está registrado!");
		}
		i = new Instituto(nombreInstituto);
		mI.agregarInstituto(i);
	}
	
	/*Alta ProgramaFormacion*/
	@Override
	public void IngresarProgFormacion(String nombre, String descripcion, Date fechaInicio, Date fechaFin, Date fechaAlta) {
		this.nombreProgFormacion = nombre;
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.fechaAlta = fechaAlta;
	}
	@Override
	public void AltaProgramaFormacion() {
		ProgramaFormacion pf = new ProgramaFormacion(this.nombreProgFormacion, this.descripcion, this.fechaInicio, this.fechaFin, this.fechaAlta);
		ManejadorProgramaFormacion mPF = ManejadorProgramaFormacion.getInstancia();
		mPF.agregarProgramaFormacion(pf);
	}
	/*Alta Curso*/
	@Override
	public void IngresarCurso(String nInstituto, String nombre, String desc, String duracion, int horas, int creditos, Date fechaR, String url) {
		this.nombreInstituto = nInstituto;
		this.nombreCurso = nombre;
		this.descripcion = desc;
		this.duracion = duracion;
		this.horas = horas;
		this.creditos = creditos;
		this.fechaR = fechaR;
		this.url = url;
	}
	@Override
	public void ingresarPrevia(String NombrePrevia) {
		this.nombrePrevia = NombrePrevia;
	}
	@Override
	public void agregarPrevia(String nombrePrevia) {
		ManejadorInstituto mI = ManejadorInstituto.getInstancia();
		Instituto institutoCurso = mI.buscarInstituto(this.nombreInstituto);
		Curso previa = institutoCurso.getCurso(nombrePrevia);
		institutoCurso.setPreviaCurso(this.nombreCurso,previa);
	}
	@Override
	public void AltaCurso() {
		ManejadorInstituto mI = ManejadorInstituto.getInstancia();
		Instituto institutoCurso = mI.buscarInstituto(this.nombreInstituto);
		Curso c = new Curso(this.nombreCurso, this.descripcion, this.duracion, this.horas, this.creditos, this.fechaR, this.url);
		institutoCurso.setCurso(c);
	}
}
