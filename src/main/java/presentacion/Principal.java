package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import interfaces.Fabrica;
import interfaces.IControladorUsuario;
import publicadores.ControladorCursoPublish;
import publicadores.ControladorUsuarioPublish;
import interfaces.IControladorCurso;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class Principal {

	private JFrame frame;
	private AltaInstituto altaInstitutoInternalFrame;
	private AltaUsuario altaUsuarioInternalFrame;
	private ConsultaCurso consultaCursoInternalFrame;
	private AltaCurso altaCursoInternalFrame;
	private AltadeEdiciondeCurso altadeEdiciondeCursoInternalFrame;
	private CrearProgramadeFormacion CrearProgramadeFormacionInternalFrame;
	private ConsultaUsuario ConsultaUsuarioInternalFrame;
	private ConsultaProgramaFormacion ConsultaProgramaFormacionInternalFrame;
	private InscripcionaEdicionaCurso InscripcionaEdicionaCursoInternalFrame;
	private InscripcionaProgramaFormacion InscripcionaProgramaFormacionInternalFrame;
	private AgregarCursoProgFormacion agregarCursoProgFormacionInternalFrame;
	private ConsultaEdicionCurso consultaEdicionCursoInternalFrame;
	private ModificarUsuario modificarUsuarioInternalFrame;
	private AltaCategoria altaCategoriaInternalFrame;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
					JPanel p = new Fondo("/presentacion/edEXT_image2.jpg");
					p.setLayout(new BorderLayout());
					window.frame.getContentPane().add(p);
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
		
		ControladorUsuarioPublish cup = new ControladorUsuarioPublish();
		cup.publicar();
		ControladorCursoPublish ccp = new ControladorCursoPublish();
		ccp.publicar();
		
		Fabrica fabrica = Fabrica.getInstancia();
		IControladorUsuario iconUsr = fabrica.getIControladorUsuario();
		IControladorCurso iconCur = fabrica.getIControladorCurso();
		
		Dimension desktopSize = frame.getSize();
		Dimension jInternalFrameSize;
		
		
		/*FRAME ALTA INSTITUTO*/
		altaInstitutoInternalFrame = new AltaInstituto(iconCur);
		jInternalFrameSize = altaInstitutoInternalFrame.getSize();
		altaInstitutoInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
			    (desktopSize.height- jInternalFrameSize.height)/2);
		altaInstitutoInternalFrame.setVisible(false);
		frame.getContentPane().add(altaInstitutoInternalFrame);
		altaInstitutoInternalFrame.getContentPane().setLayout(null);
		
		/*FRAME ALTA USUARIO*/
		altaUsuarioInternalFrame = new AltaUsuario(iconUsr);
		jInternalFrameSize = altaUsuarioInternalFrame.getSize();
		altaUsuarioInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
			    (desktopSize.height- jInternalFrameSize.height)/2);
		altaUsuarioInternalFrame.setVisible(false);
		frame.getContentPane().add(altaUsuarioInternalFrame);
		altaUsuarioInternalFrame.getContentPane().setLayout(null);
		
		/*FRAME CONSULTA CURSO*/
		consultaCursoInternalFrame = new ConsultaCurso(iconCur);
		jInternalFrameSize = consultaCursoInternalFrame.getSize();
		consultaCursoInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
			    (desktopSize.height- jInternalFrameSize.height)/2);
		consultaCursoInternalFrame.setVisible(false);
		frame.getContentPane().add(consultaCursoInternalFrame);
		consultaCursoInternalFrame.getContentPane().setLayout(null);
		
		/*FRAME ALTA CURSO*/
		altaCursoInternalFrame = new AltaCurso(iconCur);
		jInternalFrameSize = altaCursoInternalFrame.getSize();
		altaCursoInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
			    (desktopSize.height- jInternalFrameSize.height)/2);
		altaCursoInternalFrame.setVisible(false);
		frame.getContentPane().add(altaCursoInternalFrame);
		altaCursoInternalFrame.getContentPane().setLayout(null);
		
		/*FRAME ALTA DE EDICION DE CURSO*/
		altadeEdiciondeCursoInternalFrame = new AltadeEdiciondeCurso(iconCur);
		jInternalFrameSize = altadeEdiciondeCursoInternalFrame.getSize();
		altadeEdiciondeCursoInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
			    (desktopSize.height- jInternalFrameSize.height)/2);
		altadeEdiciondeCursoInternalFrame.setVisible(false);
		frame.getContentPane().add(altadeEdiciondeCursoInternalFrame);
		altadeEdiciondeCursoInternalFrame.getContentPane().setLayout(null);
		
		/*FRAME ALTA DE PROGRAMA DE FORMACION*/
		CrearProgramadeFormacionInternalFrame = new CrearProgramadeFormacion(iconCur);
		jInternalFrameSize = CrearProgramadeFormacionInternalFrame.getSize();
		CrearProgramadeFormacionInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
			    (desktopSize.height- jInternalFrameSize.height)/2);
		CrearProgramadeFormacionInternalFrame.setVisible(false);
		frame.getContentPane().add(CrearProgramadeFormacionInternalFrame);
		CrearProgramadeFormacionInternalFrame.getContentPane().setLayout(null);
		
		/*FRAME CONSULTA USUARIO*/
		ConsultaUsuarioInternalFrame = new ConsultaUsuario(iconUsr);
		jInternalFrameSize = ConsultaUsuarioInternalFrame.getSize();
		ConsultaUsuarioInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
			    (desktopSize.height- jInternalFrameSize.height)/2);
		ConsultaUsuarioInternalFrame.setVisible(false);
		frame.getContentPane().add(ConsultaUsuarioInternalFrame);
		ConsultaUsuarioInternalFrame.getContentPane().setLayout(null);
		
		/*FRAME CONSULTA PROGRAMA*/
		ConsultaProgramaFormacionInternalFrame = new ConsultaProgramaFormacion(iconCur);
		jInternalFrameSize = ConsultaProgramaFormacionInternalFrame.getSize();
		ConsultaProgramaFormacionInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
			    (desktopSize.height- jInternalFrameSize.height)/2);
		ConsultaProgramaFormacionInternalFrame.setVisible(false);
		frame.getContentPane().add(ConsultaProgramaFormacionInternalFrame);
		ConsultaProgramaFormacionInternalFrame.getContentPane().setLayout(null);
		
		/*FRAME INSCRIPCION A EDICION DE CURSO*/
		InscripcionaEdicionaCursoInternalFrame = new InscripcionaEdicionaCurso(iconCur);
		jInternalFrameSize = InscripcionaEdicionaCursoInternalFrame.getSize();
		InscripcionaEdicionaCursoInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
			    (desktopSize.height- jInternalFrameSize.height)/2);
		InscripcionaEdicionaCursoInternalFrame.setVisible(false);
		frame.getContentPane().add(InscripcionaEdicionaCursoInternalFrame);
		InscripcionaEdicionaCursoInternalFrame.getContentPane().setLayout(null);
		
		/*FRAME AGREGAR CURSO PROG FORMACION*/
		agregarCursoProgFormacionInternalFrame = new AgregarCursoProgFormacion(iconCur);
		jInternalFrameSize = agregarCursoProgFormacionInternalFrame.getSize();
		agregarCursoProgFormacionInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
			    (desktopSize.height- jInternalFrameSize.height)/2);
		agregarCursoProgFormacionInternalFrame.setVisible(false);
		frame.getContentPane().add(agregarCursoProgFormacionInternalFrame);
		agregarCursoProgFormacionInternalFrame.getContentPane().setLayout(null);
		
		/*FRAME CONSULTA EDICION CURSO*/
		consultaEdicionCursoInternalFrame = new ConsultaEdicionCurso(iconCur);
		jInternalFrameSize = consultaEdicionCursoInternalFrame.getSize();
		consultaEdicionCursoInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
			    (desktopSize.height- jInternalFrameSize.height)/2);
		consultaEdicionCursoInternalFrame.setVisible(false);
		frame.getContentPane().add(consultaEdicionCursoInternalFrame);
		consultaEdicionCursoInternalFrame.getContentPane().setLayout(null);
		
		/*FRAME MODIFICAR USUARIO*/
		modificarUsuarioInternalFrame = new ModificarUsuario(iconUsr);
		jInternalFrameSize = modificarUsuarioInternalFrame.getSize();
		modificarUsuarioInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
			    (desktopSize.height- jInternalFrameSize.height)/2);
		modificarUsuarioInternalFrame.setVisible(false);
		frame.getContentPane().add(modificarUsuarioInternalFrame);
		modificarUsuarioInternalFrame.getContentPane().setLayout(null);
		
		/*FRAME INSCRIPCION A PROGRAMA DE FORMACION*/
		InscripcionaProgramaFormacionInternalFrame = new InscripcionaProgramaFormacion(iconCur);
		jInternalFrameSize = InscripcionaProgramaFormacionInternalFrame.getSize();
		InscripcionaProgramaFormacionInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
			    (desktopSize.height- jInternalFrameSize.height)/2);
		InscripcionaProgramaFormacionInternalFrame.setVisible(false);
		frame.getContentPane().add(InscripcionaProgramaFormacionInternalFrame);
		InscripcionaProgramaFormacionInternalFrame.getContentPane().setLayout(null);
		
		/*FRAME ALTA CATEGORIA*/
		altaCategoriaInternalFrame = new AltaCategoria(iconCur);
		jInternalFrameSize = altaCategoriaInternalFrame.getSize();
		altaCategoriaInternalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
			    (desktopSize.height- jInternalFrameSize.height)/2);
		altaCategoriaInternalFrame.setVisible(false);
		frame.getContentPane().add(altaCategoriaInternalFrame);
		altaCategoriaInternalFrame.getContentPane().setLayout(null);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 771, 556);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.getContentPane().setLayout(null);
		
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
		mntmAltaUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				altaUsuarioInternalFrame.comboBoxInit();
				altaUsuarioInternalFrame.deshabilitarCombobox();
				altaUsuarioInternalFrame.setVisible(true);
			}
		});
		mnAltas.add(mntmAltaUsuario);
		
		JMenuItem mntmAltaCurso = new JMenuItem("Alta de Curso");
		mntmAltaCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaCursoInternalFrame.comboBoxInit();
				altaCursoInternalFrame.setVisible(true);
			}
		});
		
		JMenuItem mntmAltaCategora = new JMenuItem("Alta Categor\u00EDa");
		mntmAltaCategora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaCategoriaInternalFrame.setVisible(true);
			}
		});
		mnAltas.add(mntmAltaCategora);
		mnAltas.add(mntmAltaCurso);
		
		JMenuItem mntmAltaEdicionCurso = new JMenuItem("Alta de Edición de Curso");
		mntmAltaEdicionCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				altadeEdiciondeCursoInternalFrame.setVisible(true);
				altadeEdiciondeCursoInternalFrame.comboBoxInit();
			}
		});
		mnAltas.add(mntmAltaEdicionCurso);
		
		JMenuItem mntmAltaProgFormacion = new JMenuItem("Alta de Prog. de Formación");
		mntmAltaProgFormacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearProgramadeFormacionInternalFrame.setVisible(true);
			}
		});
		mnAltas.add(mntmAltaProgFormacion);
		
		JMenuItem mntmAgregarCursoProgFormacion = new JMenuItem("Agregar Curso a Prog. de Formación");
		mntmAgregarCursoProgFormacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarCursoProgFormacionInternalFrame.comboBoxInit();
				agregarCursoProgFormacionInternalFrame.setVisible(true);
			}
		});
		mnAltas.add(mntmAgregarCursoProgFormacion);
		
		JMenu mnModificar = new JMenu("Modificar");
		menuBar.add(mnModificar);
		
		JMenuItem mntmModificarDatosUsuarios = new JMenuItem("Modificar Datos de Usuarios");
		mntmModificarDatosUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarUsuarioInternalFrame.comboBoxInit();
				modificarUsuarioInternalFrame.setVisible(true);
			}
		});
		mnModificar.add(mntmModificarDatosUsuarios);
		
		JMenu mnConsultas = new JMenu("Consultas");
		menuBar.add(mnConsultas);
		
		JMenuItem mntmConsultaUsuario = new JMenuItem("Consulta de Usuario");
		mntmConsultaUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultaUsuarioInternalFrame.setVisible(true);
				ConsultaUsuarioInternalFrame.comboBoxInit();
			}
		});
		mnConsultas.add(mntmConsultaUsuario);
		
		JMenuItem mntmConsultaCurso = new JMenuItem("Consulta de Curso");
		mntmConsultaCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				consultaCursoInternalFrame.setVisible(true);
				consultaCursoInternalFrame.comboBoxInitInstitutos();

			}
		});
		mnConsultas.add(mntmConsultaCurso);
		
		JMenuItem mntmConsultaEdicionCurso = new JMenuItem("Consulta de Edición de Curso");
		mntmConsultaEdicionCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultaEdicionCursoInternalFrame.comboBoxInstitutosInit();
				consultaEdicionCursoInternalFrame.setVisible(true);
			}
		});
		mnConsultas.add(mntmConsultaEdicionCurso);
		
		JMenuItem mntmConsultaProgFormacion = new JMenuItem("Consulta de Prog. de Formación");
		mntmConsultaProgFormacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaProgramaFormacionInternalFrame.setVisible(true);
				ConsultaProgramaFormacionInternalFrame.comboBoxInitProgramas();

			}
		});
		mnConsultas.add(mntmConsultaProgFormacion);
		
		JMenu mnInscripciones = new JMenu("Inscripciones");
		menuBar.add(mnInscripciones);
		
		JMenuItem mntmInscripcionEdicionCurso = new JMenuItem("Inscripción a Edición de Curso");
		mntmInscripcionEdicionCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InscripcionaEdicionaCursoInternalFrame.setVisible(true);
				InscripcionaEdicionaCursoInternalFrame.comboBoxInit();

			}
		});
		mnInscripciones.add(mntmInscripcionEdicionCurso);
		
		JMenuItem mntmInscripcionPrograma = new JMenuItem("Inscripción a Programa de Formación");
		mntmInscripcionPrograma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InscripcionaProgramaFormacionInternalFrame.setVisible(true);
				InscripcionaProgramaFormacionInternalFrame.comboBoxInitProgramas();
			}
		});
		mnInscripciones.add(mntmInscripcionPrograma);
	}
}
