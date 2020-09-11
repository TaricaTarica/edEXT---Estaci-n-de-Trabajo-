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
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setBounds(106, 26, 70, 15);
		getContentPane().add(lblUsuarios);
		comboBoxUsuarios = new JComboBox<String>();
		comboBoxUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaUsuario_ActionPerformed(e);
			}
		});
		comboBoxUsuarios.setBounds(186, 26, 159, 19);
		getContentPane().add(comboBoxUsuarios);
		
		JLabel lblEdiciones = new JLabel("Ediciones de Curso");
		lblEdiciones.setBounds(238, 78, 160, 15);
		getContentPane().add(lblEdiciones);
		
		
		JLabel lblProgramas = new JLabel("Programas de Formación");
		lblProgramas.setBounds(238, 130, 160, 15);
		getContentPane().add(lblProgramas);
		
		comboBoxEdiciones = new JComboBox();
		comboBoxEdiciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		comboBoxEdiciones.setBounds(238, 92, 160, 19);
		getContentPane().add(comboBoxEdiciones);
		
		comboBoxProgramas = new JComboBox();
		comboBoxProgramas.setBounds(238, 144, 160, 19);
		getContentPane().add(comboBoxProgramas);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(33, 92, 70, 15);
		getContentPane().add(lblNombre);
		
		JLabel lblNickname = new JLabel("Nickname");
		lblNickname.setBounds(33, 52, 70, 40);
		getContentPane().add(lblNickname);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(33, 117, 70, 15);
		getContentPane().add(lblApellido);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(33, 144, 70, 15);
		getContentPane().add(lblCorreo);
		
		JLabel lblFechaNac = new JLabel("Fecha Nac.");
		lblFechaNac.setBounds(33, 168, 70, 15);
		getContentPane().add(lblFechaNac);
		
		textFieldNickname = new JTextField();
		textFieldNickname.setBounds(106, 63, 114, 19);
		getContentPane().add(textFieldNickname);
		textFieldNickname.setColumns(10);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(106, 90, 114, 19);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(106, 117, 114, 19);
		getContentPane().add(textFieldApellido);
		textFieldApellido.setColumns(10);
		
		textFieldCorreo = new JTextField();
		textFieldCorreo.setBounds(106, 142, 114, 19);
		getContentPane().add(textFieldCorreo);
		textFieldCorreo.setColumns(10);
		
		textFieldFechaNac = new JTextField();
		textFieldFechaNac.setBounds(106, 166, 114, 19);
		getContentPane().add(textFieldFechaNac);
		textFieldFechaNac.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnAceptar.setBounds(108, 213, 117, 25);
		getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CancelarConsultaUsuario_actionPerformed(e);
			}
		});
		btnCancelar.setBounds(243, 213, 117, 25);
		getContentPane().add(btnCancelar);

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
	public void comboBoxInitProgramas() {
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
			comboBoxInitEdicionesE();
			comboBoxInitProgramas();
		}else{
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
}
