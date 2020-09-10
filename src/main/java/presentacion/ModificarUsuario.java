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

public class ModificarUsuario extends JInternalFrame {
	IControladorUsuario iconUsr;
	private static final long serialVersionUID = 1L;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
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
		lblUsuario.setBounds(30, 23, 46, 14);
		getContentPane().add(lblUsuario);
		
		comboBoxUsuario = new JComboBox<String>();
		comboBoxUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionarUsuario_actionPerformed(e);
			}
		});
		comboBoxUsuario.setBounds(86, 20, 180, 20);
		getContentPane().add(comboBoxUsuario);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(78, 68, 46, 14);
		getContentPane().add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(158, 65, 125, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(78, 93, 46, 14);
		getContentPane().add(lblApellido);
		
		JPanel panel = new JPanel();
		panel.setBounds(18, 11, 273, 37);
		getContentPane().add(panel);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(158, 90, 125, 20);
		getContentPane().add(textFieldApellido);
		textFieldApellido.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha de Nacimiento");
		lblFecha.setBounds(43, 135, 105, 14);
		getContentPane().add(lblFecha);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmarModificarUsuario_actionPerformed(e);
			}
		});
		btnConfirmar.setBounds(301, 180, 111, 23);
		getContentPane().add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelarModificacion_actionPerformed(e);
			}
		});
		btnCancelar.setBounds(202, 180, 89, 23);
		getContentPane().add(btnCancelar);
		
		dateChooserFechaNac = new JDateChooser();
		dateChooserFechaNac.setBounds(158, 129, 125, 20);
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

			//OBTENGO LAS FECHAS DATE Y LAS CONVIERTO A LOCALDATE
			Date fechaNacD = new Date();
			fechaNacD = this.dateChooserFechaNac.getDate();
			LocalDate fechaNac = fechaNacD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();		

		

			iconUsr.modificarUsuario(nickname, nombre, apellido, fechaNac);
			JOptionPane.showMessageDialog(this, "Usuario modificado con �xito", "Modificaci�n exitosa", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	private boolean comprobarCampos() {
		Date fechaNacD = this.dateChooserFechaNac.getDate();
		int comboBoxUsuario = this.comboBoxUsuario.getItemCount();
		String nombre = this.textFieldNombre.getText();
		String apellido = this.textFieldApellido.getText();
		if(nombre.isEmpty() || apellido.isEmpty() || apellido.isEmpty() || comboBoxUsuario == 0 || fechaNacD ==  null) {
			JOptionPane.showMessageDialog(this, "No pueden haber campos vac�os", "ERROR",
	                JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	private void limpiarCampos() {
		this.textFieldApellido.setText("");
		this.textFieldNombre.setText("");
		this.dateChooserFechaNac.setCalendar(null);
	}
}
