package presentacion;


import javax.swing.JInternalFrame;
//import java.awt.EventQueue;


import interfaces.IControladorUsuario;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import datatypes.DtDocente;
import datatypes.DtEstudiante;
import datatypes.DtUsuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class ConsultaUsuario extends JInternalFrame {
	
	IControladorUsuario iconUsr;
	private JComboBox<String> comboBoxUsuarios;
	private JComboBox<String> comboBoxEdiciones;
	private JComboBox<String> comboBoxProgramas;
	private static final long serialVersionUID = 1L;
	private JTextField textFieldNickname;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldCorreo;
	private JTextField textFieldFechaNac;
	private JTextPane textPane;
	private JLabel lblProgramas;
	
	/**
	 * Create the frame.
	 */
	public ConsultaUsuario(IControladorUsuario iconUsr) {
		
		this.iconUsr = iconUsr;
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(false);
		
        setTitle("Consulta de Usuario");
		setBounds(100, 100, 472, 327);
		getContentPane().setLayout(null);
		
		JLabel lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setBounds(33, 35, 70, 15);
		getContentPane().add(lblUsuarios);
		comboBoxUsuarios = new JComboBox<String>();
		comboBoxUsuarios.setBounds(106, 33, 144, 19);
		comboBoxUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaUsuario_ActionPerformed(e);
			}
		});
		getContentPane().add(comboBoxUsuarios);
		
		JLabel lblEdiciones = new JLabel("Ediciones de Curso");
		lblEdiciones.setBounds(275, 35, 160, 15);
		getContentPane().add(lblEdiciones);
		
		
		lblProgramas = new JLabel("Programas de Formacion");
		lblProgramas.setBounds(275, 78, 160, 15);
		getContentPane().add(lblProgramas);
		
		comboBoxEdiciones = new JComboBox();
		comboBoxEdiciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seleccionarEdicionCurso(arg0);
			}
		});
		comboBoxEdiciones.setBounds(275, 48, 160, 19);
		getContentPane().add(comboBoxEdiciones);
		
		
		comboBoxProgramas = new JComboBox();
		comboBoxProgramas.setBounds(275, 94, 160, 19);
		getContentPane().add(comboBoxProgramas);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(33, 106, 70, 15);
		getContentPane().add(lblNombre);
		
		JLabel lblNickname = new JLabel("Nickname");
		lblNickname.setBounds(33, 61, 70, 40);
		getContentPane().add(lblNickname);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(33, 140, 70, 15);
		getContentPane().add(lblApellido);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(33, 175, 70, 15);
		getContentPane().add(lblCorreo);
		
		JLabel lblFechaNac = new JLabel("Fecha Nac.");
		lblFechaNac.setBounds(33, 213, 70, 15);
		getContentPane().add(lblFechaNac);
		
		textFieldNickname = new JTextField();
		textFieldNickname.setBounds(106, 72, 144, 19);
		getContentPane().add(textFieldNickname);
		textFieldNickname.setColumns(10);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(106, 104, 144, 19);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(106, 140, 144, 19);
		getContentPane().add(textFieldApellido);
		textFieldApellido.setColumns(10);
		
		textFieldCorreo = new JTextField();
		textFieldCorreo.setBounds(106, 173, 144, 19);
		getContentPane().add(textFieldCorreo);
		textFieldCorreo.setColumns(10);
		
		textFieldFechaNac = new JTextField();
		textFieldFechaNac.setBounds(106, 211, 144, 19);
		getContentPane().add(textFieldFechaNac);
		textFieldFechaNac.setColumns(10);
		
		
		textPane = new JTextPane();
		textPane.setBounds(275, 131, 160, 99);
		getContentPane().add(textPane);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(185, 261, 117, 25);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarEdicion(textPane);
				limpiarCampos();
				setVisible(false);
			}
		});
		getContentPane().add(btnSalir);

	}
	public void comboBoxInit() {
		DefaultComboBoxModel<String> modelUsuarios = new DefaultComboBoxModel<String>(iconUsr.listarUsuarios());
		comboBoxUsuarios.setModel(modelUsuarios);
	}
	public void comboBoxInitEdicionesE() {
		String strEstudiante = this.comboBoxUsuarios.getSelectedItem().toString();
		DefaultComboBoxModel<String> modelEdiciones = new DefaultComboBoxModel<String>(iconUsr.listarEdicionesE(strEstudiante));
		comboBoxEdiciones.setModel(modelEdiciones);
	}
	public void comboBoxInitEdicionesD() {
		String strDocente = this.comboBoxUsuarios.getSelectedItem().toString();
		DefaultComboBoxModel<String> modelEdiciones = new DefaultComboBoxModel<String>(iconUsr.listarEdicionesD(strDocente));
		comboBoxEdiciones.setModel(modelEdiciones);
	}
	public void comboBoxInitProgramasE() {//FALTA  IMPLEMENTAR PORQUE NO HAY INSCRIPCION DE ESTUDIANTE A PROGRAMA
		DefaultComboBoxModel<String> modelProgramas = new DefaultComboBoxModel<String>();
		comboBoxProgramas.setModel(modelProgramas);
	}
	
	public void ConsultaUsuario_ActionPerformed(ActionEvent e) {
		String strUsuario = this.comboBoxUsuarios.getSelectedItem().toString();
		DtUsuario dtu= iconUsr.ConsultaUsuario(strUsuario);
		
		this.textFieldNickname.setText(dtu.getNickname());
		this.textFieldNombre.setText(dtu.getNombre());
		this.textFieldApellido.setText(dtu.getApellido());
		this.textFieldCorreo.setText(dtu.getCorreo());
		this.textFieldFechaNac.setText(dtu.getfechaNac().toString());
		
		if(iconUsr.esEstudiante(dtu.getNickname())) {
			this.comboBoxProgramas.setEnabled(true);
			this.lblProgramas.setEnabled(true);
			comboBoxInitEdicionesE();
			comboBoxInitProgramasE();
		}else{
			this.comboBoxProgramas.setEnabled(false);
			this.lblProgramas.setEnabled(false);
			comboBoxInitEdicionesD();
		}
			
	}
	protected void CancelarConsultaUsuario_actionPerformed(ActionEvent e) {
		limpiarCampos();
		setVisible(false);
	}
	private void limpiarCampos() {
		this.textFieldNickname.setText("");
		this.textFieldNombre.setText("");
		this.textFieldApellido.setText("");
		this.textFieldCorreo.setText("");
		this.textFieldFechaNac.setText("");
	}
	// Printea la edicion de curso en el JTextPane
	
		public void seleccionarEdicionCurso(ActionEvent arg0) {
			String nombreUsuario = comboBoxUsuarios.getSelectedItem().toString();
			String nombreEdicion = comboBoxEdiciones.getSelectedItem().toString();
			printEdicion(textPane, iconUsr.AtributosEdicion(nombreUsuario,nombreEdicion));
		}
		
		public void printEdicion(JTextPane textPane, String[] arrayEdicion) {
			//textPane.setDocument(new PlainDocument()); // Se crea un nuevo documento para limpiar el texto
			//for (int i = 0; i < 6; i++) {
				textPane.setText(arrayEdicion[0]+"\n"+arrayEdicion[1]+"\n"+arrayEdicion[2]+"\n"+arrayEdicion[3]+"\n"+arrayEdicion[4]+"\n"+arrayEdicion[5]);
			//}
		}
		public void limpiarEdicion(JTextPane textPane) {
				this.textPane.setText("");
		}
}
