package logica;

import java.util.ArrayList;

import interfaces.IControladorUsuario;
import datatypes.DtDocente;
import datatypes.DtEstudiante;
import datatypes.DtUsuario;
import excepciones.UsuarioRepetido_Exception;

public class ControladorUsuario implements IControladorUsuario {
	
	public ControladorUsuario() {}
	private String nombreInstituto;
	/*IR AGREGANDO LAS OPERACIONES A MEDIDA QUE SE REQUIERAN/IMPLEMENTEN*/
	
	/*AltaUsuario*/
	@Override
	public void ingresarInstitutoDocente(String nombreInstituto) {
		this.nombreInstituto = nombreInstituto;
	}
	@Override
	public void confirmarAlta(DtUsuario u) throws UsuarioRepetido_Exception {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario nuevoUsr = mU.buscarUsuario(u.getNickname(), u.getCorreo());
		if(nuevoUsr != null)
			throw new UsuarioRepetido_Exception("El nickname "+ u.getNickname() +" y el correo " + u.getCorreo() +" están registrados");
		if(u instanceof DtEstudiante) 
			nuevoUsr = new Estudiante(u.getNickname(),u.getCorreo(),u.getNombre(),u.getApellido(),u.getfechaNac());
		if(u instanceof DtDocente) {
			ManejadorInstituto mI = ManejadorInstituto.getInstancia();
			Instituto institutoDocente = mI.buscarInstituto(this.nombreInstituto);
			nuevoUsr = new Docente (u.getNickname(),u.getCorreo(),u.getNombre(),u.getApellido(),u.getfechaNac(), institutoDocente);
		}
		mU.agregarUsuario(nuevoUsr);
		
	}
	
	/*MULTIUSO*/
	@Override
	public String[] listarInstitutos(){
		ManejadorInstituto mI = ManejadorInstituto.getInstancia();
		ArrayList<String> institutos;
		institutos = mI.getNombreInstitutos();
		String[] institutos_ret = new String[institutos.size()];
        int i=0;
        for(String ins: institutos) {
        	institutos_ret[i]=ins;
        	i++;
        }
        return institutos_ret;
	}
	
	
}
