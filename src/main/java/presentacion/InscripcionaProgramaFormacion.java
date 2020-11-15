package presentacion;


import javax.swing.JInternalFrame;

import interfaces.IControladorCurso;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import com.toedter.calendar.JDateChooser;

import excepciones.EdicionRepatida_Exception;
import excepciones.InscripcionRepetidaPF_Exception;
import excepciones.InscripcionRepetida_Exception;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class InscripcionaProgramaFormacion extends JInternalFrame {
	
	IControladorCurso iconCur;
	private JComboBox<String> comboBoxProgramas;
	private JComboBox<String> comboBoxEstudiantes;
	private JDateChooser dateChooserFechadeInscripcion;


	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */
	public InscripcionaProgramaFormacion(IControladorCurso iconCur) {
		this.iconCur = iconCur;
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(false);
		setTitle("Inscripcion a Programa de Formacion");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblProgramas = new JLabel("Programas");
		lblProgramas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProgramas.setBounds(40, 25, 120, 29);
		getContentPane().add(lblProgramas);
		
		JLabel lblEstudiantes = new JLabel("Estudiantes");
		lblEstudiantes.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEstudiantes.setBounds(50, 77, 120, 15);
		getContentPane().add(lblEstudiantes);
		
		JLabel lblFecha = new JLabel("Fecha de Inscripcion");
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFecha.setBounds(40, 123, 120, 15);
		getContentPane().add(lblFecha);
		
		comboBoxProgramas = new JComboBox<String>();
		comboBoxProgramas.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			//ACTION PERFORMED DE JCOMBOBOX
			comboBoxInitEstudiantes();
		}
		});
		comboBoxProgramas.setBounds(174, 25, 173, 24);
		getContentPane().add(comboBoxProgramas);
		
		comboBoxEstudiantes = new JComboBox<String>();
		comboBoxEstudiantes.setBounds(174, 72, 173, 24);
		getContentPane().add(comboBoxEstudiantes);
		
		dateChooserFechadeInscripcion = new JDateChooser();
		dateChooserFechadeInscripcion.setBounds(174, 123, 173, 19);
		getContentPane().add(dateChooserFechadeInscripcion);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaInscripcionaProgramadeFormacion_ActionPerformed(e);
			}
		});
		btnAceptar.setBounds(92, 176, 117, 25);
		getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CancelarInscripcionaProgramaFormacion_actionPerformed(e); //La funci�n est� definida abajo
			}
		});
		btnCancelar.setBounds(230, 176, 117, 25);
		getContentPane().add(btnCancelar);

	}
	protected void AltaInscripcionaProgramadeFormacion_ActionPerformed(ActionEvent e) {
		if(comprobarCampos()) {
			String programaformacion = this.comboBoxProgramas.getSelectedItem().toString();
			String estudiante = this.comboBoxEstudiantes.getSelectedItem().toString();
			Date fechaInsD = new Date();
			
			//FECHAS DATE LAS CONVIERTO A LOCALDATE
			LocalDate fechaIns = fechaInsD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
			try{
				this.iconCur.InscripcionaProgramaFormacion(fechaIns,estudiante,programaformacion);
				JOptionPane.showMessageDialog(this, "Inscripcion creada con exito", "Inscripcion exitosa", JOptionPane.INFORMATION_MESSAGE);
			}
			catch(InscripcionRepetidaPF_Exception ex){
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}	
	}
	public void comboBoxInitProgramas() {
		DefaultComboBoxModel<String> modelProgramas = new DefaultComboBoxModel<String>(iconCur.listarProgramas());
		comboBoxProgramas.setModel(modelProgramas);
	}
	public void comboBoxInitEstudiantes() {
		DefaultComboBoxModel<String> modelEstudaintes = new DefaultComboBoxModel<String>(iconCur.listarEstudiantes());
		comboBoxEstudiantes.setModel(modelEstudaintes);
	}
	protected void CancelarInscripcionaProgramaFormacion_actionPerformed(ActionEvent e) {
		limpiarCampos();
		setVisible(false);
	}
	private void limpiarCampos() {
		this.dateChooserFechadeInscripcion.setCalendar(null);
	}
	private boolean comprobarCampos() {
		Date fechaIns = this.dateChooserFechadeInscripcion.getDate();
		int comboBoxProgramas = this.comboBoxProgramas.getItemCount();
		int comboBoxEstudiantes = this.comboBoxEstudiantes.getItemCount();
		if (fechaIns == null || comboBoxProgramas == 0 || comboBoxEstudiantes == 0) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacios", "Error",
	                JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

}
