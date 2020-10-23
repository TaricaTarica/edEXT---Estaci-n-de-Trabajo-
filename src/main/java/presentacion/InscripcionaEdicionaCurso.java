package presentacion;


import javax.swing.JInternalFrame;

import interfaces.IControladorCurso;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import com.toedter.calendar.JDateChooser;

import datatypes.EstadoInscripcion;
import excepciones.EdicionRepatida_Exception;
import excepciones.InscripcionRepetida_Exception;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class InscripcionaEdicionaCurso extends JInternalFrame {
	
	IControladorCurso iconCur;
	
	private JComboBox<String> comboBoxCursos;
	private JComboBox<String> comboBoxInstitutos;
	private JComboBox<String> comboBoxEdiciones;
	private JComboBox<String> comboBoxEstudiantes;
	private JDateChooser dateChooserFechadeInscripcion;
	private JComboBox<String> comboBoxEnum;

	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */
	public InscripcionaEdicionaCurso(IControladorCurso iconCur) {
		this.iconCur = iconCur;
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(false);
		setTitle("Inscripcion a Edicion a Curso");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblInstitutos = new JLabel("Institutos");
		lblInstitutos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInstitutos.setBounds(40, 26, 120, 15);
		getContentPane().add(lblInstitutos);
		
		JLabel lblCursos = new JLabel("Cursos");
		lblCursos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCursos.setBounds(40, 53, 120, 15);
		getContentPane().add(lblCursos);
		
		JLabel lblEdiciones = new JLabel("Ediciones");
		lblEdiciones.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEdiciones.setBounds(40, 80, 120, 29);
		getContentPane().add(lblEdiciones);
		
		JLabel lblEstudiantes = new JLabel("Estudiantes");
		lblEstudiantes.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEstudiantes.setBounds(40, 126, 120, 15);
		getContentPane().add(lblEstudiantes);
		
		JLabel lblEstados = new JLabel("Estados");
		lblEstados.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEstados.setBounds(40, 196, 120, 15);
		getContentPane().add(lblEstados);
		
		JLabel lblFecha = new JLabel("Fecha de Inscripcion");
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFecha.setBounds(40, 165, 120, 15);
		getContentPane().add(lblFecha);
		
		comboBoxInstitutos = new JComboBox<String>();
		comboBoxInstitutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//ACTION PERFORMED DE JCOMBOBOX DE CURSOS
				comboBoxInit2();
			}
		});
		comboBoxInstitutos.setBounds(174, 21, 173, 24);
		getContentPane().add(comboBoxInstitutos);
		
		comboBoxCursos = new JComboBox<String>();
		comboBoxCursos.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			//ACTION PERFORMED DE JCOMBOBOX DE CURSOS
			comboBoxInit3();
			}
		});
		comboBoxCursos.setBounds(174, 53, 173, 24);
		getContentPane().add(comboBoxCursos);
		
		comboBoxEdiciones = new JComboBox<String>();
		comboBoxEdiciones.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			//ACTION PERFORMED DE JCOMBOBOX
			comboBoxInit4();
		}
		});
		comboBoxEdiciones.setBounds(174, 85, 173, 24);
		getContentPane().add(comboBoxEdiciones);
		
		comboBoxEstudiantes = new JComboBox<String>();
		comboBoxEstudiantes.setBounds(174, 121, 173, 24);
		getContentPane().add(comboBoxEstudiantes);
		
		comboBoxEnum = new JComboBox<String>();
		comboBoxEnum.setBounds(174, 191, 193, 24);
		comboBoxEnum.addItem("Inscripto");
		comboBoxEnum.addItem("Aceptado");
		comboBoxEnum.addItem("Rechazado");
		getContentPane().add(comboBoxEnum);
		
		dateChooserFechadeInscripcion = new JDateChooser();
		dateChooserFechadeInscripcion.setBounds(174, 161, 173, 19);
		getContentPane().add(dateChooserFechadeInscripcion);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaInscripcionaEdiciondeCurso_ActionPerformed(e);
			}
		});
		btnAceptar.setBounds(103, 216, 117, 25);
		getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CancelarInscripcionaEdiciondeCurso_actionPerformed(e); //La funci�n est� definida abajo
			}
		});
		btnCancelar.setBounds(230, 216, 117, 25);
		getContentPane().add(btnCancelar);

	}
	protected void AltaInscripcionaEdiciondeCurso_ActionPerformed(ActionEvent e) {
		if(comprobarCampos()) {
			String instituto = this.comboBoxInstitutos.getSelectedItem().toString();
			String curso = this.comboBoxCursos.getSelectedItem().toString();
			String edicion = this.comboBoxEdiciones.getSelectedItem().toString();
			String estudiante = this.comboBoxEstudiantes.getSelectedItem().toString();
			String estado = null;
			if (comboBoxEnum.getSelectedItem()=="Inscripto") {
				//estado = EstadoInscripcion.Inscripto;
				estado="Inscripto";
			}
			else if (comboBoxEnum.getSelectedItem()=="Aceptado") {
				//estado = EstadoInscripcion.Aceptado;
				estado="Aceptado";
			}
			else if (comboBoxEnum.getSelectedItem()=="Rechazado") {
				//estado = EstadoInscripcion.Rechazado;
				estado="Rechazado";
			}
			Date fechaIns = new Date();
			fechaIns = this.dateChooserFechadeInscripcion.getDate();
			
			try{
				this.iconCur.InscripcionaEdiciondeCurso(instituto,fechaIns,estudiante,curso,edicion,estado);
				JOptionPane.showMessageDialog(this, "Inscripcion creada con exito", "Inscripcion exitosa", JOptionPane.INFORMATION_MESSAGE);
			}
			catch(InscripcionRepetida_Exception ex){
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}	
	}
	public void comboBoxInit() {
		DefaultComboBoxModel<String> modelInstitutos = new DefaultComboBoxModel<String>(iconCur.listarInstitutos());
		comboBoxInstitutos.setModel(modelInstitutos);
	}
	
	public void comboBoxInit2() {
		String strCurso = this.comboBoxInstitutos.getSelectedItem().toString();
		DefaultComboBoxModel<String> modelCursos = new DefaultComboBoxModel<String>(iconCur.listarCursos(strCurso));
		comboBoxCursos.setModel(modelCursos);
	}
	public void comboBoxInit3() {
		String strInstituto = this.comboBoxInstitutos.getSelectedItem().toString();
		String strCurso = this.comboBoxCursos .getSelectedItem().toString();
		DefaultComboBoxModel<String> modelEdiciones = new DefaultComboBoxModel<String>(iconCur.listarEdiciones(strInstituto, strCurso));
		comboBoxEdiciones.setModel(modelEdiciones);
	}
	public void comboBoxInit4() {
		DefaultComboBoxModel<String> modelEstudaintes = new DefaultComboBoxModel<String>(iconCur.listarEstudiantes());
		comboBoxEstudiantes.setModel(modelEstudaintes);
	}
	protected void CancelarInscripcionaEdiciondeCurso_actionPerformed(ActionEvent e) {
		limpiarCampos();
		setVisible(false);
	}
	private void limpiarCampos() {
		this.dateChooserFechadeInscripcion.setCalendar(null);
	}
	private boolean comprobarCampos() {
		Date fechaIns = this.dateChooserFechadeInscripcion.getDate();
		int comboBoxCursos = this.comboBoxCursos.getItemCount();
		int comboBoxInstitutos = this.comboBoxInstitutos.getItemCount();
		int comboBoxEdiciones = this.comboBoxEdiciones.getItemCount();
		int comboBoxEstudiantes = this.comboBoxEstudiantes.getItemCount();
		if (fechaIns == null || comboBoxCursos == 0 || comboBoxInstitutos == 0 || comboBoxEdiciones == 0 || comboBoxEstudiantes == 0) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacios", "Error",
	                JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	

}