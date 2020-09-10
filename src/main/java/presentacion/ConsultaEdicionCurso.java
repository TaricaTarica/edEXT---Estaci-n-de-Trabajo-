package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.text.PlainDocument;

import interfaces.IControladorCurso;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsultaEdicionCurso extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private IControladorCurso iconCur;
	private JComboBox<String> comboBoxInstitutos;
	private JComboBox<String> comboBoxEdicionesCurso;
	private JComboBox<String> comboBoxCursos;
	private JTextPane textPane;

	public ConsultaEdicionCurso(IControladorCurso iconCur) {
		this.iconCur = iconCur;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		comboBoxInstitutos = new JComboBox<String>();
		comboBoxInstitutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seleccionarInstituto(arg0);
			}
		});
		comboBoxInstitutos.setBounds(139, 31, 150, 20);
		getContentPane().add(comboBoxInstitutos);

		comboBoxEdicionesCurso = new JComboBox<String>();
		comboBoxEdicionesCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seleccionarEdicionCurso(arg0);
			}
		});
		comboBoxEdicionesCurso.setBounds(139, 118, 150, 20);
		getContentPane().add(comboBoxEdicionesCurso);
		comboBoxEdicionesCurso.setEnabled(false);

		JLabel lblInstituto = new JLabel("Instituto");
		lblInstituto.setBounds(32, 34, 46, 14);
		getContentPane().add(lblInstituto);

		JLabel lblEdicionDeCurso = new JLabel("Edici\u00F3n de curso");
		lblEdicionDeCurso.setBounds(32, 121, 97, 14);
		getContentPane().add(lblEdicionDeCurso);

		textPane = new JTextPane();
		textPane.setBounds(32, 161, 257, 98);
		getContentPane().add(textPane);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancelar.setBounds(319, 236, 89, 23);
		getContentPane().add(btnCancelar);

		JLabel lblCurso = new JLabel("Curso");
		lblCurso.setBounds(32, 78, 46, 14);
		getContentPane().add(lblCurso);

		comboBoxCursos = new JComboBox<String>();
		comboBoxCursos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seleccionarCurso(arg0);
			}
		});
		comboBoxCursos.setBounds(139, 75, 150, 20);
		getContentPane().add(comboBoxCursos);
		comboBoxCursos.setEnabled(false);
	}

	public void comboBoxInstitutosInit() {
		DefaultComboBoxModel<String> modelInstitutos = new DefaultComboBoxModel<String>(iconCur.listarInstitutos());
		comboBoxInstitutos.setModel(modelInstitutos);
	}

	public void seleccionarInstituto(ActionEvent arg0) {
		comboBoxCursos.setEnabled(true);
		String nombreInstituto = (String) comboBoxInstitutos.getSelectedItem();
		DefaultComboBoxModel<String> modelEdicion = new DefaultComboBoxModel<String>(
				iconCur.listarCursos(nombreInstituto));
		comboBoxCursos.setModel(modelEdicion);
	}

	public void seleccionarCurso(ActionEvent arg0) {
		comboBoxEdicionesCurso.setEnabled(true);
		String nombreInstituto = (String) comboBoxInstitutos.getSelectedItem();
		String nombreCurso = (String) comboBoxCursos.getSelectedItem();
		DefaultComboBoxModel<String> modelEdicion = new DefaultComboBoxModel<String>(
				iconCur.ListarEdicionesCurso(nombreInstituto, nombreCurso));
		comboBoxEdicionesCurso.setModel(modelEdicion);
	}

	// Printea la edicion de curso en el JTextPane
	
	public void seleccionarEdicionCurso(ActionEvent arg0) {
		String nombreInstituto = comboBoxInstitutos.getSelectedItem().toString();
		String nombreCurso = comboBoxCursos.getSelectedItem().toString();
		String nombreEdicion = comboBoxEdicionesCurso.getSelectedItem().toString();
		printEdicion(textPane, iconCur.AtributosEdicion(nombreInstituto, nombreCurso, nombreEdicion));
	}
	
	public void printEdicion(JTextPane textPane, String[] arrayEdicion) {
		//textPane.setDocument(new PlainDocument()); // Se crea un nuevo documento para limpiar el texto
		//for (int i = 0; i < 6; i++) {
			textPane.setText(arrayEdicion[0]+"\n"+arrayEdicion[1]+"\n"+arrayEdicion[2]+"\n"+arrayEdicion[3]+"\n"+arrayEdicion[4]+"\n"+arrayEdicion[5]);
		//}
	}
	

}
