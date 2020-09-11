package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;

import excepciones.ProgramaCursoRepetido_Exception;
import interfaces.IControladorCurso;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AgregarCursoProgFormacion extends JInternalFrame {
	
	private IControladorCurso iconCur;
	private static final long serialVersionUID = 1L;
	
	private JComboBox<String> comboBoxProgFormacion;
	private JComboBox<String> comboBoxCursos;
	
	
	public AgregarCursoProgFormacion(IControladorCurso iconCur) {
		this.iconCur = iconCur;
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(false);
		setTitle("Agregar Curso a Programa de Formación");
		setBounds(100, 100, 450, 180); //acá modificas el tamaño de la ventana
		getContentPane().setLayout(null);
		
		JLabel lblProgFormacion = new JLabel("Programa de Formaci\u00F3n");
		lblProgFormacion.setBounds(25, 27, 122, 14);
		getContentPane().add(lblProgFormacion);
		
		comboBoxProgFormacion = new JComboBox<String>();
		comboBoxProgFormacion.setBounds(157, 27, 218, 20);
		getContentPane().add(comboBoxProgFormacion);
		
		JLabel lblCurso = new JLabel("Curso");
		lblCurso.setBounds(67, 62, 46, 14);
		getContentPane().add(lblCurso);
		
		comboBoxCursos = new JComboBox<String>();
		comboBoxCursos.setBounds(157, 59, 218, 20);
		getContentPane().add(comboBoxCursos);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmarAgregarCursoProgFormacion_actionPerformed(e);
			}
		});
		btnConfirmar.setBounds(289, 95, 122, 23);
		getContentPane().add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelarAgregarCursoProgFormacion_actionPerformed(e);
			}
		});
		btnCancelar.setBounds(157, 95, 122, 23);
		getContentPane().add(btnCancelar);

	}
	public void comboBoxInit() {
		DefaultComboBoxModel<String> modelCursos = new DefaultComboBoxModel<String>(iconCur.listarInstitutoCursos());
		comboBoxCursos.setModel(modelCursos);
		DefaultComboBoxModel<String> modelProgFormacion = new DefaultComboBoxModel<String>(iconCur.listarProgFormacion());
		comboBoxProgFormacion.setModel(modelProgFormacion);
	}
	public void cancelarAgregarCursoProgFormacion_actionPerformed(ActionEvent e) {
		setVisible(false);
	}
	public void confirmarAgregarCursoProgFormacion_actionPerformed(ActionEvent e) {	
		if(comprobarCampos()) {
			String programaFormacion = this.comboBoxProgFormacion.getSelectedItem().toString();
			String InstitutoCurso = this.comboBoxCursos.getSelectedItem().toString();
			try {
				iconCur.agregarCursoProgFormacion(programaFormacion, InstitutoCurso);
				JOptionPane.showMessageDialog(this, "Curso agregado con éxito", "Agregación exitosa", JOptionPane.INFORMATION_MESSAGE);
			}
			catch(ProgramaCursoRepetido_Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
	}
	public boolean comprobarCampos() {
		int comboBoxProgFormacion = this.comboBoxProgFormacion.getItemCount();
		int comboBoxCursos = this.comboBoxProgFormacion.getItemCount();
		if(comboBoxCursos == 0 || comboBoxProgFormacion == 0) {
			JOptionPane.showMessageDialog(this, "No pueden haber campos vacíos", "ERROR",
		            JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
}
