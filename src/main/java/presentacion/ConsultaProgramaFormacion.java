package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import interfaces.IControladorCurso;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import datatypes.DtCursoInfo;
import datatypes.DtProgramaFormacion;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.ScrollPane;

public class ConsultaProgramaFormacion extends JInternalFrame {


	private static final long serialVersionUID = 1L;
	IControladorCurso iconCur;
	private JTextField textFieldNombre;
	private JTextField textFieldDescripcion;
	private JTextField textFieldFechaInicio;
	private JTextField textFieldFechaFin;
	private JTextField textFieldFechaAlta;
	private JComboBox<String> comboBoxProgramas;
	private JList<String> listCursosP;
	private JScrollPane scrollPaneCursos;



	/**
	 * Create the frame.
	 */
	public ConsultaProgramaFormacion(IControladorCurso iconCur) {
		setTitle("Consulta de Programa de Formación");
		
		this.iconCur = iconCur;

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		comboBoxProgramas = new JComboBox<String>();
		comboBoxProgramas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaPrograma_ActionPerformed(e);
				listCursosInit();
			}
		});
		comboBoxProgramas.setBounds(209, 22, 174, 20);
		getContentPane().add(comboBoxProgramas);
		
		JLabel lblProgramasFormacion = new JLabel("Programas de Formación");
		lblProgramasFormacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblProgramasFormacion.setBounds(32, 25, 167, 14);
		getContentPane().add(lblProgramasFormacion);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(121, 65, 262, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(34, 68, 77, 14);
		getContentPane().add(lblNombre);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setBounds(121, 96, 262, 20);
		getContentPane().add(textFieldDescripcion);
		textFieldDescripcion.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripción");
		lblDescripcion.setBounds(32, 99, 79, 14);
		getContentPane().add(lblDescripcion);
		
		textFieldFechaInicio = new JTextField();
		textFieldFechaInicio.setBounds(121, 127, 86, 20);
		getContentPane().add(textFieldFechaInicio);
		textFieldFechaInicio.setColumns(10);
		
		textFieldFechaFin = new JTextField();
		textFieldFechaFin.setBounds(121, 158, 86, 20);
		getContentPane().add(textFieldFechaFin);
		textFieldFechaFin.setColumns(10);
		
		textFieldFechaAlta = new JTextField();
		textFieldFechaAlta.setBounds(121, 189, 86, 20);
		getContentPane().add(textFieldFechaAlta);
		textFieldFechaAlta.setColumns(10);
		
		JLabel lblFechaInicio = new JLabel("Fecha Inicio");
		lblFechaInicio.setBounds(32, 130, 79, 14);
		getContentPane().add(lblFechaInicio);
		
		JLabel lblFechaFin = new JLabel("Fecha Fin");
		lblFechaFin.setBounds(32, 161, 79, 14);
		getContentPane().add(lblFechaFin);
		
		JLabel lblFechaAlta = new JLabel("Fecha Alta");
		lblFechaAlta.setBounds(32, 192, 79, 14);
		getContentPane().add(lblFechaAlta);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
				setVisible(false);
			}
		});
		btnSalir.setBounds(168, 236, 89, 23);
		getContentPane().add(btnSalir);
		

		listCursosP = new JList<String>();
		listCursosP.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listCursosP.setBounds(280, 127, 103, 82);
		//getContentPane().add(listCursosP);
		
		JLabel lblCursos = new JLabel("Cursos");
		lblCursos.setBounds(224, 130, 46, 14);
		getContentPane().add(lblCursos);
		
		scrollPaneCursos = new JScrollPane();
		scrollPaneCursos.setBounds(270, 127, 113, 82);
		getContentPane().add(scrollPaneCursos);

	}
	
	public void comboBoxInitProgramas() {
		DefaultComboBoxModel<String> modelProgramas = new DefaultComboBoxModel<String>(iconCur.listarProgramas());
		comboBoxProgramas.setModel(modelProgramas);
	}
	
	public void ConsultaPrograma_ActionPerformed(ActionEvent e) {
		//BUSCO EL PROGRAMA
		String strPrograma = this.comboBoxProgramas.getSelectedItem().toString();
		
		//CREO EL DT PROGRAMA
		DtProgramaFormacion dtpf = iconCur.ConsultaProgramaFormacion(strPrograma);

		//SETEO LOS CAMPOS
		this.textFieldNombre.setText(dtpf.getNombre());
		this.textFieldDescripcion.setText(dtpf.getDescripcion());
		this.textFieldFechaInicio.setText(dtpf.getFechaInicio().toString());
		this.textFieldFechaFin.setText(dtpf.getFechaFin().toString());
		this.textFieldFechaAlta.setText(dtpf.getFechaAlta().toString());
	}
	public void listCursosInit() {
		String[] cursos = iconCur.listarCursosP(this.comboBoxProgramas.getSelectedItem().toString());
		this.listCursosP.setListData(new String[0]);
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for(String c: cursos) {
			listModel.addElement(c);
		}		
				
		this.listCursosP.setModel(listModel);
		scrollPaneCursos.getViewport().setView(listCursosP);
	}
	public void limpiarCampos() {
		this.textFieldNombre.setText("");
		this.textFieldDescripcion.setText("");
		this.textFieldFechaInicio.setText("");
		this.textFieldFechaFin.setText("");
		this.textFieldFechaAlta.setText("");
		this.listCursosP.setListData(new String[0]);
		this.scrollPaneCursos.getViewport().setView(this.listCursosP);
		
	}
	
}
