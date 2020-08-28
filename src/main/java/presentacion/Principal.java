package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import interfaces.Fabrica;
import interfaces.IControladorUsuario;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

public class Principal {

	private JFrame frame;
	private AltaInstituto altaInstitutoInternalFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
		
		Fabrica fabrica = Fabrica.getInstancia();
		IControladorUsuario icon = fabrica.getIControladorUsuario();
		
		// SOLAMENTE IMPORTE LA FABRICA Y LA INTERFAZ, E INSTANCIE EL CONTROLADOR
		// PERO NO IMPLEMENTE NADA DE LOGICA, NI RELACIONANDO LA PRESENTACION CON LA LOGICA
		// POR ESO LA ALERTA DE QUE "icon" SE INSTANCIA PERO NO SE UTILIZA
		
		altaInstitutoInternalFrame = new AltaInstituto();
		altaInstitutoInternalFrame.setLocation(60, 60);
		altaInstitutoInternalFrame.setSize(584, 300);
		
		altaInstitutoInternalFrame.setVisible(false);
		
		frame.getContentPane().add(altaInstitutoInternalFrame);
		altaInstitutoInternalFrame.getContentPane().setLayout(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 771, 556);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnAltas = new JMenu("Altas");
		menuBar.add(mnAltas);
		
		JMenuItem mntmAltaInstituto = new JMenuItem("Alta de Instituto");
		mntmAltaInstituto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				altaInstitutoInternalFrame.setVisible(true);
			}
		});
		mnAltas.add(mntmAltaInstituto);
		
		JMenuItem mntmAltaUsuario = new JMenuItem("Alta de Usuario");
		mnAltas.add(mntmAltaUsuario);
		
		JMenuItem mntmAltaCurso = new JMenuItem("Alta de Curso");
		mnAltas.add(mntmAltaCurso);
		
		JMenuItem mntmAltaEdicionCurso = new JMenuItem("Alta de Edición de Curso");
		mnAltas.add(mntmAltaEdicionCurso);
		
		JMenuItem mntmAltaProgFormacion = new JMenuItem("Alta de Prog. de Formación");
		mnAltas.add(mntmAltaProgFormacion);
		
		JMenuItem mntmAgregarCursoProgFormacion = new JMenuItem("Agregar Curso a Prog. de Formación");
		mnAltas.add(mntmAgregarCursoProgFormacion);
		
		JMenu mnModificar = new JMenu("Modificar");
		menuBar.add(mnModificar);
		
		JMenuItem mntmModificarDatosUsuarios = new JMenuItem("Modificar Datos de Usuarios");
		mnModificar.add(mntmModificarDatosUsuarios);
		
		JMenu mnConsultas = new JMenu("Consultas");
		menuBar.add(mnConsultas);
		
		JMenuItem mntmConsultaUsuario = new JMenuItem("Consulta de Usuario");
		mnConsultas.add(mntmConsultaUsuario);
		
		JMenuItem mntmConsultaCurso = new JMenuItem("Consulta de Curso");
		mnConsultas.add(mntmConsultaCurso);
		
		JMenuItem mntmConsultaEdicionCurso = new JMenuItem("Consulta de Edición de Curso");
		mnConsultas.add(mntmConsultaEdicionCurso);
		
		JMenuItem mntmConsultaProgFormacion = new JMenuItem("Consulta de Prog. de Formación");
		mnConsultas.add(mntmConsultaProgFormacion);
		
		JMenu mnInscripciones = new JMenu("Inscripciones");
		menuBar.add(mnInscripciones);
		
		JMenuItem mntmInscripcionEdicionCurso = new JMenuItem("Inscripción a Edición de Curso");
		mnInscripciones.add(mntmInscripcionEdicionCurso);
	}
}
