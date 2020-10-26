package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import interfaces.IControladorUsuario;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.SwingConstants;

public class ModificarUsuario extends JInternalFrame {
	IControladorUsuario iconUsr;
	private static final long serialVersionUID = 1L;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldContrasenia;
	private JComboBox<String> comboBoxUsuario;
	private JDateChooser dateChooserFechaNac;

	
	public ModificarUsuario(IControladorUsuario iconUsr) {
		this.iconUsr = iconUsr;
		
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(false);
		setTitle("Modificar Usuario");
		setBounds(100, 100, 450, 250);
		getContentPane().setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setBounds(109, 31, 46, 14);
		getContentPane().add(lblUsuario);
		
		comboBoxUsuario = new JComboBox<String>();
		comboBoxUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionarUsuario_actionPerformed(e);
			}
		});
		comboBoxUsuario.setBounds(165, 25, 173, 20);
		getContentPane().add(comboBoxUsuario);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(109, 74, 46, 14);
		getContentPane().add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(165, 68, 173, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApellido.setBounds(109, 102, 46, 14);
		getContentPane().add(lblApellido);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(165, 96, 173, 20);
		getContentPane().add(textFieldApellido);
		textFieldApellido.setColumns(10);
		
		JLabel lblContrasenia = new JLabel("Contrasenia");
		lblContrasenia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContrasenia.setBounds(109, 148, 46, 14);
		getContentPane().add(lblContrasenia);
		
		textFieldContrasenia = new JTextField();
		textFieldContrasenia.setBounds(165, 136, 173, 20);
		getContentPane().add(textFieldContrasenia);
		textFieldContrasenia.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha de Nacimiento");
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFecha.setBounds(51, 130, 105, 14);
		getContentPane().add(lblFecha);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmarModificarUsuario_actionPerformed(e);
			}
		});
		btnConfirmar.setBounds(109, 172, 111, 23);
		getContentPane().add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelarModificacion_actionPerformed(e);
			}
		});
		btnCancelar.setBounds(230, 172, 111, 23);
		getContentPane().add(btnCancelar);
		
		dateChooserFechaNac = new JDateChooser();
		dateChooserFechaNac.setBounds(166, 124, 173, 20);
		getContentPane().add(dateChooserFechaNac);
		

	}
	public void comboBoxInit() {
		DefaultComboBoxModel<String> modelUsuarios = new DefaultComboBoxModel<String>(iconUsr.listarUsuarios());
		comboBoxUsuario.setModel(modelUsuarios);
	}
	public void cancelarModificacion_actionPerformed(ActionEvent e) {
		limpiarCampos();
		setVisible(false);
	}
	public void seleccionarUsuario_actionPerformed(ActionEvent e) {
		this.textFieldNombre.setText(iconUsr.getNombreUsuario(comboBoxUsuario.getSelectedItem().toString()));
		this.textFieldApellido.setText(iconUsr.getApellidoUsuario(comboBoxUsuario.getSelectedItem().toString()));		
		this.textFieldContrasenia.setText(iconUsr.getContraseniaUsuario(comboBoxUsuario.getSelectedItem().toString()));		
		LocalDate fecha = iconUsr.getFechaUsuario(comboBoxUsuario.getSelectedItem().toString());		
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(fecha.atStartOfDay(defaultZoneId).toInstant());
		this.dateChooserFechaNac.setDate(date);
	}
	public void confirmarModificarUsuario_actionPerformed(ActionEvent e) {
		if(comprobarCampos()) {
			String nickname = this.comboBoxUsuario.getSelectedItem().toString();
			String nombre = this.textFieldNombre.getText();
			String apellido = this.textFieldApellido.getText();
			String contrasenia = this.textFieldContrasenia.getText();



			//OBTENGO LAS FECHAS DATE Y LAS CONVIERTO A LOCALDATE
			Date fechaNacD = new Date();
			fechaNacD = this.dateChooserFechaNac.getDate();
			LocalDate fechaNac = fechaNacD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();		

		

			iconUsr.modificarUsuario(nickname, nombre, apellido, fechaNac,contrasenia);
			JOptionPane.showMessageDialog(this, "Usuario modificado con exito", "Modificacion exitosa", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	private boolean comprobarCampos() {
		Date fechaNacD = this.dateChooserFechaNac.getDate();
		int comboBoxUsuario = this.comboBoxUsuario.getItemCount();
		String nombre = this.textFieldNombre.getText();
		String apellido = this.textFieldApellido.getText();
		String contrasenia = this.textFieldContrasenia.getText();
		if(nombre.isEmpty() || apellido.isEmpty() || contrasenia.isEmpty() || comboBoxUsuario == 0 || fechaNacD ==  null) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacios", "Error",
	                JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	private void limpiarCampos() {
		this.textFieldContrasenia.setText("");
		this.textFieldApellido.setText("");
		this.textFieldNombre.setText("");
		this.dateChooserFechaNac.setCalendar(null);
	}
}
