package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.EventQueue;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;

import datatypes.DtProgramaFormacion;
import excepciones.CrearProgramaFormacionRepetido_Exception;
import interfaces.IControladorCurso;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import com.toedter.calendar.JDateChooser;

public class CrearProgramadeFormacion extends JInternalFrame {
	
	IControladorCurso iconCur;
	
	private JTextField textFieldNombre;
	private JTextField textFieldDescripcion;
	private JDateChooser dateChooserFechaInicio;
	private JDateChooser dateChooserFechaFin;
	private JDateChooser dateChooserFechaAlta;
	
	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */
	public CrearProgramadeFormacion(IControladorCurso iconCur) {
		
		this.iconCur = iconCur;
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(false);
		
        setTitle("Alta de Prog. de Formación");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(149, 28, 249, 19);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setBounds(148, 59, 250, 19);
		getContentPane().add(textFieldDescripcion);
		textFieldDescripcion.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(22, 28, 94, 15);
		getContentPane().add(lblNombre);
		
		JLabel lblDescripcion = new JLabel("Descripción");
		lblDescripcion.setBounds(22, 59, 94, 15);
		getContentPane().add(lblDescripcion);
		
		JLabel lblFechaInicio = new JLabel("Fecha de Inicio");
		lblFechaInicio.setBounds(22, 93, 94, 15);
		getContentPane().add(lblFechaInicio);
		
		JLabel lblFechaFin = new JLabel("Fecha de Fin");
		lblFechaFin.setBounds(22, 123, 94, 15);
		getContentPane().add(lblFechaFin);
		
		JLabel lblFechaAlta = new JLabel("Fecha de Alta");
		lblFechaAlta.setBounds(22, 154, 94, 15);
		getContentPane().add(lblFechaAlta);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfirmarCrearProgramadeFormacion_ActionPerformed(e);
			}
		});
		btnAceptar.setBounds(80, 212, 117, 25);
		getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CancelarCrearProgramadeFormacion_actionPerformed(e);
			}
		});
		btnCancelar.setBounds(222, 212, 117, 25);
		getContentPane().add(btnCancelar);
		
		dateChooserFechaInicio = new JDateChooser();
		dateChooserFechaInicio.setBounds(149, 89, 143, 20);
		getContentPane().add(dateChooserFechaInicio);
		
		dateChooserFechaFin = new JDateChooser();
		dateChooserFechaFin.setBounds(149, 120, 143, 20);
		getContentPane().add(dateChooserFechaFin);
		
		dateChooserFechaAlta = new JDateChooser();
		dateChooserFechaAlta.setBounds(149, 151, 143, 20);
		getContentPane().add(dateChooserFechaAlta);

	}
	protected void ConfirmarCrearProgramadeFormacion_ActionPerformed(ActionEvent e) {	
		if(comprobarCampos()) {
			//OBTENGO TODOS LOS DATOS DE LA PRESENTACION
			String nombre = this.textFieldNombre.getText();
			String Descripcion=this.textFieldDescripcion.getText();
			
			//OBTENGO LAS FECHAS DATE Y LAS CONVIERTO A LOCALDATE
			Date fechaIniD = new Date();
			fechaIniD = this.dateChooserFechaInicio.getDate();
			LocalDate fechaIni = fechaIniD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();		
			
			Date fechaFinD = new Date();
			fechaFinD = this.dateChooserFechaFin.getDate();
			LocalDate fechaFin = fechaFinD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();	
			
			Date fechaAltaD = new Date();
			fechaAltaD = this.dateChooserFechaAlta.getDate();
			LocalDate fechaAlta = fechaAltaD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
			Calendar fechaCalendarIni = GregorianCalendar.from(fechaIni.atStartOfDay(ZoneId.systemDefault()));
			Calendar fechaCalendarFin = GregorianCalendar.from(fechaFin.atStartOfDay(ZoneId.systemDefault()));
			Calendar fechaCalendarAlta = GregorianCalendar.from(fechaAlta.atStartOfDay(ZoneId.systemDefault()));
			
			//CREACION DEL DT PROGRAMA
			DtProgramaFormacion pf = new DtProgramaFormacion(nombre,Descripcion,fechaCalendarIni,fechaCalendarFin,fechaCalendarAlta); 
			try {
				this.iconCur.AltaCrearProgramadeFormacion(pf);
				JOptionPane.showMessageDialog(this, "Programa creado con exito", "Alta exitosa",
                        JOptionPane.INFORMATION_MESSAGE);
				limpiarCampos();
			}
			catch(CrearProgramaFormacionRepetido_Exception ex){
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
			
	}
	protected void CancelarCrearProgramadeFormacion_actionPerformed(ActionEvent e) {
		limpiarCampos();
		setVisible(false);
	}
	private void limpiarCampos() {
		this.textFieldNombre.setText("");
		this.textFieldDescripcion.setText("");
		this.dateChooserFechaInicio.setCalendar(null);
		this.dateChooserFechaFin.setCalendar(null);
		this.dateChooserFechaAlta.setCalendar(null);
		
	}
	private boolean comprobarCampos() {
		String nombre = this.textFieldNombre.getText();
		String Descripcion=this.textFieldDescripcion.getText();
		Date fechaIniD = this.dateChooserFechaInicio.getDate();
		Date fechaFinD = this.dateChooserFechaFin.getDate();
		Date fechaAltaD = this.dateChooserFechaAlta.getDate();
		if(nombre.isEmpty() || Descripcion.isEmpty() || fechaIniD == null || fechaFinD == null || fechaAltaD == null) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacios", "Error",
                    JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
}
