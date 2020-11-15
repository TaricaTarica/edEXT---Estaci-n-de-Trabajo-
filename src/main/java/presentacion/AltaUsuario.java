package presentacion;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.*;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import interfaces.IControladorUsuario;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import datatypes.DtUsuario;
import excepciones.UsuarioRepetido_Exception;
import datatypes.DtDocente;
import datatypes.DtEstudiante;


import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.SwingConstants;

public class AltaUsuario extends JInternalFrame {
	IControladorUsuario iconUsr;
	private static final long serialVersionUID = 1L;
	private JTextField textFieldNickname;
	private JTextField textFieldCorreo;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	
	private ButtonGroup grupoUsuarios;
	private JComboBox<String> comboBoxInstitutos;
	private JRadioButton rdbtnDocente;
	private JRadioButton rdbtnEstudiante;
	private JDateChooser dateChooserFechaNac;
	private JLabel lblContrasea;
	private JTextField textFieldContrasenia;
	private JLabel lblConfcontra;
	private JTextField textFieldConfirmarContrasenia;

	
	public AltaUsuario(IControladorUsuario iconUsr) {
		this.iconUsr = iconUsr;
				
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(false);
		setTitle("Alta de Usuario");
		setBounds(100, 100, 450, 280);
		getContentPane().setLayout(null);
		
		JLabel lblInstituto = new JLabel("Instituto");
		lblInstituto.setEnabled(false);
		lblInstituto.setBounds(229, 58, 46, 14);
		getContentPane().add(lblInstituto);
		
		JLabel lblNickname = new JLabel("Nickname");
		lblNickname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNickname.setBounds(10, 30, 62, 14);
		getContentPane().add(lblNickname);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCorreo.setBounds(10, 58, 62, 14);
		getContentPane().add(lblCorreo);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(10, 88, 62, 14);
		getContentPane().add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApellido.setBounds(10, 118, 62, 14);
		getContentPane().add(lblApellido);
		
		JLabel lblFechaNaciento = new JLabel("Fecha Nac.");
		lblFechaNaciento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaNaciento.setBounds(10, 147, 62, 14);
		getContentPane().add(lblFechaNaciento);
		
		textFieldNickname = new JTextField();
		textFieldNickname.setBounds(80, 30, 139, 20);
		getContentPane().add(textFieldNickname);
		textFieldNickname.setColumns(10);
		
		textFieldCorreo = new JTextField();
		textFieldCorreo.setText("");
		textFieldCorreo.setBounds(80, 58, 139, 20);
		getContentPane().add(textFieldCorreo);
		textFieldCorreo.setColumns(10);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(80, 85, 139, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(79, 116, 140, 20);
		getContentPane().add(textFieldApellido);
		textFieldApellido.setColumns(10);
		
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfirmarAltaUsuario_ActionPerformed(e);
			}
		});
		btnConfirmar.setBounds(283, 116, 99, 23);
		getContentPane().add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CancelarAltaUsuario_ActionPerformed(e);
			}
		});
		btnCancelar.setBounds(283, 150, 99, 23);
		getContentPane().add(btnCancelar);
		
		grupoUsuarios = new ButtonGroup();
		
		rdbtnEstudiante = new JRadioButton("Estudiante");
		rdbtnEstudiante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxInstitutos.setEnabled(false);
				lblInstituto.setEnabled(false);
			}
		});
		rdbtnEstudiante.setSelected(true);
		rdbtnEstudiante.setBounds(239, 29, 94, 23);
		getContentPane().add(rdbtnEstudiante);
		
		rdbtnDocente = new JRadioButton("Docente");
		rdbtnDocente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBoxInstitutos.setEnabled(true);
				lblInstituto.setEnabled(true);
			}
		});
		rdbtnDocente.setBounds(335, 29, 93, 23);
		getContentPane().add(rdbtnDocente);
		
		grupoUsuarios.add(rdbtnDocente);
		grupoUsuarios.add(rdbtnEstudiante);
		
		comboBoxInstitutos = new JComboBox<String>();
		comboBoxInstitutos.setBounds(285, 58, 121, 20);
		getContentPane().add(comboBoxInstitutos);
		
		dateChooserFechaNac = new JDateChooser();
		dateChooserFechaNac.setBounds(80, 146, 139, 20);
		getContentPane().add(dateChooserFechaNac);
		
		lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(10, 180, 94, 14);
		getContentPane().add(lblContrasea);
		
		textFieldContrasenia = new JTextField();
		textFieldContrasenia.setBounds(80, 177, 139, 20);
		getContentPane().add(textFieldContrasenia);
		textFieldContrasenia.setColumns(10);
		
		lblConfcontra = new JLabel("Conf.Contra.");
		lblConfcontra.setBounds(10, 205, 78, 14);
		getContentPane().add(lblConfcontra);
		
		textFieldConfirmarContrasenia = new JTextField();
		textFieldConfirmarContrasenia.setBounds(80, 202, 139, 20);
		getContentPane().add(textFieldConfirmarContrasenia);
		textFieldConfirmarContrasenia.setColumns(10);

	}
	public void comboBoxInit() {
		DefaultComboBoxModel<String> modelInstitutos = new DefaultComboBoxModel<String>(iconUsr.listarInstitutos());
		comboBoxInstitutos.setModel(modelInstitutos);
	}
	public void CancelarAltaUsuario_ActionPerformed(ActionEvent e) {
		limpiarCampos();
		setVisible(false);
	}
	public void ConfirmarAltaUsuario_ActionPerformed(ActionEvent e) {
		if (comprobarCampos()) {
			//OBTENGO TODOS LOS DATOS DE LA PRESENTACION
			String nickname = this.textFieldNickname.getText();
			String correo = this.textFieldCorreo.getText();
			String nombre = this.textFieldNombre.getText();
			String apellido = this.textFieldApellido.getText();
			String contrasenia = this.textFieldContrasenia.getText();
		
			//OBTENGO LAS FECHAS DATE Y LAS CONVIERTO A LOCALDATE
			Date fechaNacD = new Date();
			fechaNacD = this.dateChooserFechaNac.getDate();
			LocalDate fechaNac = fechaNacD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();		
			
			Calendar fechaCalendar = GregorianCalendar.from(fechaNac.atStartOfDay(ZoneId.systemDefault()));
			
			DtUsuario usr = null;
			
			if (rdbtnDocente.isSelected()) {
				String nInstituto = this.comboBoxInstitutos.getSelectedItem().toString();
				iconUsr.ingresarInstitutoDocente(nInstituto);
				usr = new DtDocente(nickname, nombre, apellido, correo, fechaCalendar, contrasenia);
			}else if(rdbtnEstudiante.isSelected()) {
				usr = new DtEstudiante(nickname, nombre, apellido, correo, fechaCalendar, contrasenia);
			}
			try {
				iconUsr.confirmarAlta(usr);
				JOptionPane.showMessageDialog(this, "Usuario creado con exito", "Alta exitosa", JOptionPane.INFORMATION_MESSAGE);
				limpiarCampos();
			}
			catch(UsuarioRepetido_Exception ex){
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	private void limpiarCampos() {
		this.textFieldApellido.setText("");
		this.textFieldNombre.setText("");
		this.textFieldCorreo.setText("");
		this.textFieldNickname.setText("");
		this.dateChooserFechaNac.setCalendar(null);
		this.textFieldContrasenia.setText("");
		this.textFieldConfirmarContrasenia.setText("");
	}
	private boolean comprobarCampos() {
		//int comboBoxInstitutos = this.comboBoxInstitutos.getItemCount();
		String nickname = this.textFieldNickname.getText();
		String correo = this.textFieldCorreo.getText();
		String nombre = this.textFieldNombre.getText();
		String apellido = this.textFieldApellido.getText();
		String contrasenia = this.textFieldContrasenia.getText();
		String confirmarContrasenia = this.textFieldConfirmarContrasenia.getText();

		
		
		if(nickname.isEmpty() || correo.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || contrasenia.isEmpty() || confirmarContrasenia.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacios", "Error",
	                JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(!verificarCorreo(correo)) {
			JOptionPane.showMessageDialog(this, "Debe ingresar un correo valido", "Error",
	                JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(!contrasenia.equals(confirmarContrasenia)) {
			JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden", "Error",
	                JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;		
	}
	private boolean verificarCorreo(String correo) {
		Pattern patt = Pattern.compile("([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+");
		Matcher matcher = patt.matcher(correo);
		if(matcher.find()) {
			return true;
		}
		else 
			return false;
	}
	
	public void deshabilitarCombobox() {
		comboBoxInstitutos.setEnabled(false);
	}

	
}
