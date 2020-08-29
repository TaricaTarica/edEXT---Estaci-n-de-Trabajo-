package presentacion;

import java.util.Date;
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

public class AltaUsuario extends JInternalFrame {
	IControladorUsuario iconUsr;
	private static final long serialVersionUID = 1L;
	private JTextField textFieldDia;
	private JTextField textFieldAnio;
	private JTextField textFieldNickname;
	private JTextField textFieldCorreo;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	
	private ButtonGroup grupoUsuarios;
	private JComboBox<String> comboBoxInstitutos;
	private JComboBox comboBoxMes;
	private JRadioButton rdbtnDocente;
	private JRadioButton rdbtnEstudiante;

	
	public AltaUsuario(IControladorUsuario iconUsr) {
		this.iconUsr = iconUsr;
				
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(false);
		setTitle("Alta de Usuario");
		setBounds(100, 100, 450, 230);
		getContentPane().setLayout(null);
		
		JLabel lblInstituto = new JLabel("Instituto");
		lblInstituto.setEnabled(false);
		lblInstituto.setBounds(233, 50, 46, 14);
		getContentPane().add(lblInstituto);
		
		JLabel lblNick = new JLabel("Nick");
		lblNick.setBounds(10, 24, 46, 14);
		getContentPane().add(lblNick);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(10, 47, 46, 14);
		getContentPane().add(lblCorreo);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 72, 46, 14);
		getContentPane().add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(10, 97, 46, 14);
		getContentPane().add(lblApellido);
		
		JLabel lblDia = new JLabel("Dia");
		lblDia.setBounds(20, 148, 46, 14);
		getContentPane().add(lblDia);
		
		textFieldDia = new JTextField();
		textFieldDia.setBounds(41, 145, 25, 20);
		getContentPane().add(textFieldDia);
		textFieldDia.setColumns(10);
		
		JLabel lblMes = new JLabel("Mes");
		lblMes.setBounds(72, 148, 46, 14);
		getContentPane().add(lblMes);
		
		comboBoxMes = new JComboBox();
		comboBoxMes.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		comboBoxMes.setBounds(96, 145, 37, 20);
		getContentPane().add(comboBoxMes);
		
		JLabel lblAnio = new JLabel("A\u00F1o");
		lblAnio.setBounds(144, 148, 46, 14);
		getContentPane().add(lblAnio);
		
		textFieldAnio = new JTextField();
		textFieldAnio.setBounds(169, 145, 60, 20);
		getContentPane().add(textFieldAnio);
		textFieldAnio.setColumns(10);
		
		JLabel lblFechaNaciento = new JLabel("Fecha de Nacimiento");
		lblFechaNaciento.setBounds(10, 122, 209, 14);
		getContentPane().add(lblFechaNaciento);
		
		textFieldNickname = new JTextField();
		textFieldNickname.setBounds(62, 21, 157, 20);
		getContentPane().add(textFieldNickname);
		textFieldNickname.setColumns(10);
		
		textFieldCorreo = new JTextField();
		textFieldCorreo.setText("");
		textFieldCorreo.setBounds(62, 44, 157, 20);
		getContentPane().add(textFieldCorreo);
		textFieldCorreo.setColumns(10);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(62, 69, 157, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(61, 94, 158, 20);
		getContentPane().add(textFieldApellido);
		textFieldApellido.setColumns(10);
		
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfirmarAltaUsuario_ActionPerformed(e);
			}
		});
		btnConfirmar.setBounds(300, 118, 99, 23);
		getContentPane().add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CancelarAltaUsuario_ActionPerformed(e);
			}
		});
		btnCancelar.setBounds(300, 152, 99, 23);
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
		rdbtnEstudiante.setBounds(233, 15, 77, 23);
		getContentPane().add(rdbtnEstudiante);
		
		rdbtnDocente = new JRadioButton("Docente");
		rdbtnDocente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBoxInstitutos.setEnabled(true);
				lblInstituto.setEnabled(true);
			}
		});
		rdbtnDocente.setBounds(312, 15, 71, 23);
		getContentPane().add(rdbtnDocente);
		
		grupoUsuarios.add(rdbtnDocente);
		grupoUsuarios.add(rdbtnEstudiante);
		
		comboBoxInstitutos = new JComboBox<String>();
		comboBoxInstitutos.setBounds(290, 50, 121, 20);
		getContentPane().add(comboBoxInstitutos);

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
		String nickname = this.textFieldNickname.getText();
		String correo = this.textFieldCorreo.getText();
		String nombre = this.textFieldNombre.getText();
		String apellido = this.textFieldApellido.getText();
		String strdia = this.textFieldDia.getText();
		String strmes = this.comboBoxMes.getSelectedItem().toString();
		String stranio = this.textFieldAnio.getText();
		if (comprobarCampos()) {
			String fecha = strdia+"/"+strmes+"/"+stranio;
			LocalDate fechaN = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("d/M/yyyy"));
			DtUsuario usr = null;
			if (rdbtnDocente.isSelected()) {
				String nInstituto = this.comboBoxInstitutos.getSelectedItem().toString();
				iconUsr.ingresarInstitutoDocente(nInstituto);
				usr = new DtDocente(nickname, correo, nombre, apellido, fechaN);
			}else if(rdbtnEstudiante.isSelected()) {
				usr = new DtEstudiante(nickname, correo, nombre, apellido, fechaN);
			}
			try {
				iconUsr.confirmarAlta(usr);
				JOptionPane.showMessageDialog(this, "Usuario creado con éxito", "Creacíon exitosa", JOptionPane.INFORMATION_MESSAGE);
			}
			catch(UsuarioRepetido_Exception ex){
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Creación exitosa", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	private void limpiarCampos() {
		this.textFieldAnio.setText("");
		this.textFieldApellido.setText("");
		this.textFieldNombre.setText("");
		this.textFieldCorreo.setText("");
		this.textFieldNickname.setText("");
		this.textFieldDia.setText("");
	}
	private boolean comprobarCampos() {
		String nickname = this.textFieldNickname.getText();
		String correo = this.textFieldCorreo.getText();
		String nombre = this.textFieldNombre.getText();
		String apellido = this.textFieldApellido.getText();
		String strdia = this.textFieldDia.getText();
		String strmes = this.comboBoxMes.getSelectedItem().toString();
		String stranio = this.textFieldAnio.getText();
		
		if(nickname.isEmpty() || correo.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || strdia.isEmpty() || stranio.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No pueden haber campos vacíos", "ERROR",
	                JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(!verificarCorreo(correo)) {
			JOptionPane.showMessageDialog(this, "Debe ingresar un correo válido", "ERROR",
	                JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(!verificarFecha(strdia, stranio))
			return false;
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
	
	private boolean verificarFecha(String dia, String anio) {
		try {
            int d = Integer.parseInt(dia);
            if(d < 1 || d > 31) {
            	JOptionPane.showMessageDialog(this, "Día de nacimiento inválido", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            	return false;
    		}
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El día debe ser un número", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
		if (anio.length() != 4) {
			JOptionPane.showMessageDialog(this, "El año debe tener 4 dígitos", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
			return false;
		}
		try {
            int a = Integer.parseInt(anio);
            if (a > (Year.now().getValue())) {
            	JOptionPane.showMessageDialog(this, "El año ingresado no es válido", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            	return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El año debe ser un número", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
		return true;
		
	}
}
