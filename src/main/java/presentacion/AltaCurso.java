package presentacion;
import datatypes.DtCurso;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import interfaces.IControladorCurso;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import com.toedter.calendar.JDateChooser;

public class AltaCurso extends JInternalFrame {

	IControladorCurso iconCur;
	private JComboBox<String> comboBoxInstitutos;

	private static final long serialVersionUID = 1L;
	private JTextField textFieldNombre;
	private JTextField textFieldDescripcion;
	private JTextField textFieldDuracion;
	private JTextField textFieldCantHoras;
	private JTextField textFieldCantCreditos;
	private JTextField textFieldUrl;
	private JLabel lblDescripcion;
	private JLabel lblDuracion;
	private JLabel lblCantHoras;
	private JLabel lblCantCreditos;
	private JLabel lblURL;
	private JLabel lblFechaAlta;
	private JDateChooser dateChooserFechaAlta;


	/**
	 * Create the frame.
	 */
	public AltaCurso(IControladorCurso iconCur) {
		
		this.iconCur = iconCur;
		
		setTitle("Alta de Curso");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		comboBoxInstitutos = new JComboBox<String>();
		comboBoxInstitutos.setBounds(85, 32, 119, 20);
		getContentPane().add(comboBoxInstitutos);
		
		JLabel lblInstitutos = new JLabel("Institutos");
		lblInstitutos.setBounds(29, 35, 46, 14);
		getContentPane().add(lblInstitutos);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(85, 76, 119, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setBounds(85, 117, 119, 20);
		getContentPane().add(textFieldDescripcion);
		textFieldDescripcion.setColumns(10);
		
		textFieldDuracion = new JTextField();
		textFieldDuracion.setBounds(85, 161, 119, 20);
		getContentPane().add(textFieldDuracion);
		textFieldDuracion.setColumns(10);
		
		textFieldCantHoras = new JTextField();
		textFieldCantHoras.setBounds(85, 204, 119, 20);
		getContentPane().add(textFieldCantHoras);
		textFieldCantHoras.setColumns(10);
		
		textFieldCantCreditos = new JTextField();
		textFieldCantCreditos.setBounds(303, 76, 121, 20);
		getContentPane().add(textFieldCantCreditos);
		textFieldCantCreditos.setColumns(10);
		
		textFieldUrl = new JTextField();
		textFieldUrl.setBounds(303, 117, 121, 20);
		getContentPane().add(textFieldUrl);
		textFieldUrl.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(10, 79, 65, 14);
		getContentPane().add(lblNombre);
		
		lblDescripcion = new JLabel("Descripción");
		lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescripcion.setBounds(10, 120, 65, 14);
		getContentPane().add(lblDescripcion);
		
		lblDuracion = new JLabel("Duración");
		lblDuracion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDuracion.setBounds(10, 164, 65, 14);
		getContentPane().add(lblDuracion);
		
		lblCantHoras = new JLabel("Cant. horas");
		lblCantHoras.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCantHoras.setBounds(10, 207, 65, 14);
		getContentPane().add(lblCantHoras);
		
		lblCantCreditos = new JLabel("Cant. créditos");
		lblCantCreditos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCantCreditos.setBounds(225, 76, 68, 14);
		getContentPane().add(lblCantCreditos);
		
		lblURL = new JLabel("URL");
		lblURL.setHorizontalAlignment(SwingConstants.RIGHT);
		lblURL.setBounds(225, 117, 68, 14);
		getContentPane().add(lblURL);
		
		lblFechaAlta = new JLabel("Fecha alta");
		lblFechaAlta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaAlta.setBounds(225, 161, 68, 14);
		getContentPane().add(lblFechaAlta);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ACTION PERFORMED DE CONFIRMAR ALTA CURSO
				ConfirmarAltaCurso_ActionPerformed(e);
				
			}
		});
		btnConfirmar.setBounds(225, 223, 89, 23);
		getContentPane().add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnCancelar.setBounds(335, 223, 89, 23);
		getContentPane().add(btnCancelar);
		
		dateChooserFechaAlta = new JDateChooser();
		dateChooserFechaAlta.setBounds(303, 158, 121, 20);
		getContentPane().add(dateChooserFechaAlta);

	}
	
	public void comboBoxInit() {
		DefaultComboBoxModel<String> modelInstitutos = new DefaultComboBoxModel<String>(iconCur.listarInstitutos());
		comboBoxInstitutos.setModel(modelInstitutos);
	}
	
	public void ConfirmarAltaCurso_ActionPerformed(ActionEvent e) {
		//OBTENGO TODOS LOS DATOS DE LA PRESENTACION
		String nombre = this.textFieldNombre.getText();
		String descripcion = this.textFieldDescripcion.getText();
		String duracion = this.textFieldDuracion.getText();
		String cantHoras = this.textFieldCantHoras.getText();
		int h = Integer.parseInt(cantHoras);
		String cantCreditos = this.textFieldCantCreditos.getText();
		int c = Integer.parseInt(cantCreditos);
		String url = this.textFieldUrl.getText();		
		
		//OBTENGO LA FECHAS DATE Y LA CONVIERTO A LOCALDATE
		Date fechaAltaD = new Date();
		fechaAltaD = this.dateChooserFechaAlta.getDate();
		LocalDate fechaAlta = fechaAltaD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();		


		DtCurso curso = new DtCurso(nombre, descripcion, duracion, h, c, fechaAlta, url);
		
		String instituto = this.comboBoxInstitutos.getSelectedItem().toString();
		
		iconCur.AltaCurso(curso, instituto);
		JOptionPane.showMessageDialog(this, "Curso creado con exito", "Creacion exitosa", JOptionPane.INFORMATION_MESSAGE);
		
	}
}
