package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import interfaces.IControladorCurso;
import logica.Curso;

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
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.ScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;

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
	private JList<String> listCategoriasP;
	private JScrollPane scrollPaneCursos;
	private JScrollPane scrollPaneCategorias;



	/**
	 * Create the frame.
	 */
	public ConsultaProgramaFormacion(IControladorCurso iconCur) {
		setTitle("Consulta de Programa de Formación");
		
		this.iconCur = iconCur;

		setBounds(100, 100, 561, 300);
		
		comboBoxProgramas = new JComboBox<String>();
		comboBoxProgramas.setBounds(175, 22, 140, 20);
		comboBoxProgramas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaPrograma_ActionPerformed(e);
				listCursosInit();
				listCategoriasInit();
			}
		});
		getContentPane().setLayout(null);
		getContentPane().add(comboBoxProgramas);
		
		JLabel lblProgramasFormacion = new JLabel("Programas de Formación");
		lblProgramasFormacion.setBounds(32, 25, 133, 14);
		lblProgramasFormacion.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblProgramasFormacion);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(121, 65, 195, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(34, 68, 77, 14);
		getContentPane().add(lblNombre);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setBounds(121, 96, 195, 20);
		getContentPane().add(textFieldDescripcion);
		textFieldDescripcion.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripción");
		lblDescripcion.setBounds(32, 99, 79, 14);
		getContentPane().add(lblDescripcion);
		
		textFieldFechaInicio = new JTextField();
		textFieldFechaInicio.setBounds(121, 127, 136, 20);
		getContentPane().add(textFieldFechaInicio);
		textFieldFechaInicio.setColumns(10);
		
		textFieldFechaFin = new JTextField();
		textFieldFechaFin.setBounds(121, 158, 136, 20);
		getContentPane().add(textFieldFechaFin);
		textFieldFechaFin.setColumns(10);
		
		textFieldFechaAlta = new JTextField();
		textFieldFechaAlta.setBounds(121, 189, 136, 20);
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
		btnSalir.setBounds(288, 224, 89, 23);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
				setVisible(false);
			}
		});
		getContentPane().add(btnSalir);
		

		listCursosP = new JList<String>();
		listCursosP.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listCursosP.setBounds(280, 127, 103, 82);
		//getContentPane().add(listCursosP);
		
		JLabel lblCursos = new JLabel("Cursos");
		lblCursos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCursos.setBounds(354, 37, 46, 14);
		getContentPane().add(lblCursos);
		
		scrollPaneCursos = new JScrollPane();
		scrollPaneCursos.setBounds(410, 34, 97, 82);
		getContentPane().add(scrollPaneCursos);
		
		listCategoriasP = new JList<String>();
		listCategoriasP.setBorder(new LineBorder(new Color(0, 0, 0)));
		listCategoriasP.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listCategoriasP.setBounds(410, 127, 97, 82);
		getContentPane().add(listCategoriasP);
		
		scrollPaneCategorias = new JScrollPane();
		scrollPaneCategorias.setBounds(462, 127, 113, 82);
		//getContentPane().add(scrollPaneCategorias);
		
		JLabel lblCategorias = new JLabel("Categorias");
		lblCategorias.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCategorias.setBounds(330, 128, 70, 15);
		getContentPane().add(lblCategorias);
		
	

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
	
	public void listCategoriasInit() {
	    String [] categorias = iconCur.listarCursosCategoriasP(this.comboBoxProgramas.getSelectedItem().toString());
		this.listCategoriasP.setListData(new String[0]);
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for(String c: categorias) {
			listModel.addElement(c);
		}		
				
		this.listCategoriasP.setModel(listModel);
		//scrollPaneCursos.getViewport().setView(listCategoriasP);
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

