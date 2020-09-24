package presentacion;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;

import interfaces.IControladorCurso;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import datatypes.DtCurso;
import datatypes.DtCursoInfo;

import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.JSeparator;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.ScrollPane;

public class ConsultaCurso extends JInternalFrame {
	
	IControladorCurso iconCur;
	private JComboBox<String> comboBoxInstitutos;
	private JComboBox<String> comboBoxCursos;
	private JComboBox<String> comboBoxEdiciones;
	private JComboBox<String> comboBoxProgramas;

	private static final long serialVersionUID = 1L;
	private JTextField textFieldNombre;
	private JTextField textFieldDescripcion;
	private JTextField textFieldDuracion;
	private JTextField textFieldCantHoras;
	private JTextField textFieldCantCreditos;
	private JTextField textFieldUrl;
	private JTextField textFieldFechaAlta;
	private JTextPane textPane;
	private JList<String> listPrevias;
	private JList<String> listCategorias;
	private JScrollPane scrollPanePrevias;
	private JScrollPane scrollPaneCategorias;



	
	
	/**
	 * Create the frame.
	 */
	public ConsultaCurso(IControladorCurso iconCur) {
		
		this.iconCur = iconCur;
		
		setTitle("Consulta de Curso");
		setBounds(100, 100, 611, 404);
		
		comboBoxInstitutos = new JComboBox<String>();
		comboBoxInstitutos.setBounds(90, 26, 131, 20);
		comboBoxInstitutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxInitCursos();
			}
		});
		getContentPane().setLayout(null);
		getContentPane().add(comboBoxInstitutos);
		
		JLabel lblInstituto = new JLabel("Instituto");
		lblInstituto.setBounds(34, 29, 46, 14);
		lblInstituto.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblInstituto);
		
		JLabel lblCurso = new JLabel("Curso");
		lblCurso.setBounds(34, 62, 46, 14);
		lblCurso.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblCurso);
		
		comboBoxCursos = new JComboBox<String>();
		comboBoxCursos.setBounds(90, 59, 131, 20);
		comboBoxCursos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//CUANDO SELECCIONA UN CURSO, SE CARGA LA INFO EN LOS CAMPOS
				ConsultaCurso_ActionPerformed(e);
				comboBoxInitEdiciones();
				comboBoxInitProgramas();
				listPreviasInit();
				listCategoriasInit();
			}
		});
		getContentPane().add(comboBoxCursos);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(239, 340, 89, 23);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
				limpiarTextPane(textPane);
				setVisible(false);
			}
		});
		getContentPane().add(btnSalir);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(92, 101, 131, 20);
		textFieldNombre.setBackground(Color.WHITE);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 104, 72, 14);
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblNombre);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setBounds(92, 133, 131, 20);
		getContentPane().add(textFieldDescripcion);
		textFieldDescripcion.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(10, 136, 72, 14);
		lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblDescripcion);
		
		textFieldDuracion = new JTextField();
		textFieldDuracion.setBounds(92, 165, 131, 20);
		getContentPane().add(textFieldDuracion);
		textFieldDuracion.setColumns(10);
		
		JLabel lblDuracion = new JLabel("Duración");
		lblDuracion.setBounds(10, 168, 72, 14);
		lblDuracion.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblDuracion);
		
		textFieldCantHoras = new JTextField();
		textFieldCantHoras.setBounds(92, 197, 131, 20);
		getContentPane().add(textFieldCantHoras);
		textFieldCantHoras.setColumns(10);
		
		textFieldCantCreditos = new JTextField();
		textFieldCantCreditos.setBounds(92, 229, 131, 20);
		getContentPane().add(textFieldCantCreditos);
		textFieldCantCreditos.setColumns(10);
		
		textFieldUrl = new JTextField();
		textFieldUrl.setBounds(92, 261, 131, 20);
		getContentPane().add(textFieldUrl);
		textFieldUrl.setColumns(10);
		
		JLabel lblCantHoras = new JLabel("Cant. horas");
		lblCantHoras.setBounds(10, 200, 72, 14);
		lblCantHoras.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblCantHoras);
		
		JLabel lblCantCreditos = new JLabel("Cant. créditos");
		lblCantCreditos.setBounds(10, 232, 72, 14);
		lblCantCreditos.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblCantCreditos);
		
		JLabel lblUrl = new JLabel("URL");
		lblUrl.setBounds(10, 264, 72, 14);
		lblUrl.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblUrl);
		
		listPrevias = new JList<String>();
		listPrevias.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listPrevias.setBounds(304, 28, 105, 76);
		//getContentPane().add(listPrevias);
		
		listCategorias = new JList<String>();
		listCategorias.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listCategorias.setBounds(471, 29, 118, 76);
		
		JLabel lblPrevias = new JLabel("Previas");
		lblPrevias.setBounds(239, 29, 46, 14);
		getContentPane().add(lblPrevias);
		
		comboBoxEdiciones = new JComboBox<String>();
		comboBoxEdiciones.setBounds(336, 138, 152, 20);
		comboBoxEdiciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				infoEdicion(arg0);
			}
		});
		getContentPane().add(comboBoxEdiciones);
		
		
		comboBoxProgramas = new JComboBox<String>();
		comboBoxProgramas.setBounds(336, 183, 152, 20);
		comboBoxProgramas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				infoPrograma(arg0);
			}
		});
		getContentPane().add(comboBoxProgramas);
		
		JLabel lblEdiciones = new JLabel("Ediciones de Curso");
		lblEdiciones.setBounds(336, 123, 176, 14);
		getContentPane().add(lblEdiciones);
		
		JLabel lblNewLabel = new JLabel("Prog. de Formacion");
		lblNewLabel.setBounds(336, 169, 119, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblFechaAlta = new JLabel("Fecha alta");
		lblFechaAlta.setBounds(20, 295, 60, 14);
		lblFechaAlta.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblFechaAlta);
		
		textFieldFechaAlta = new JTextField();
		textFieldFechaAlta.setBounds(92, 292, 131, 20);
		getContentPane().add(textFieldFechaAlta);
		textFieldFechaAlta.setColumns(10);
		
		textPane = new JTextPane();
		textPane.setBounds(313, 215, 199, 97);
		getContentPane().add(textPane);
		
		scrollPanePrevias = new JScrollPane();
		scrollPanePrevias.setBounds(291, 26, 100, 76);
		getContentPane().add(scrollPanePrevias);
		
		scrollPaneCategorias = new JScrollPane();
		scrollPaneCategorias.setBounds(467, 26, 100, 76);
		getContentPane().add(scrollPaneCategorias);
		
		JLabel lblCategorias = new JLabel("Categorias");
		lblCategorias.setBounds(401, 29, 70, 15);
		getContentPane().add(lblCategorias);
		
	}
	
	public void comboBoxInitInstitutos() {
		DefaultComboBoxModel<String> modelInstitutos = new DefaultComboBoxModel<String>(iconCur.listarInstitutos());
		comboBoxInstitutos.setModel(modelInstitutos);
	}
	
	public void comboBoxInitCursos() {
		String strInstituto = this.comboBoxInstitutos.getSelectedItem().toString();
		DefaultComboBoxModel<String> modelCursos = new DefaultComboBoxModel<String>(iconCur.listarCursos(strInstituto));
		comboBoxCursos.setModel(modelCursos);
	}
	
	public void comboBoxInitEdiciones() {
		String strInstituto = this.comboBoxInstitutos.getSelectedItem().toString();
		String strCurso = this.comboBoxCursos.getSelectedItem().toString();
		DefaultComboBoxModel<String> modelEdiciones = new DefaultComboBoxModel<String>(iconCur.listarEdiciones(strInstituto, strCurso));
		comboBoxEdiciones.setModel(modelEdiciones);
	}
	
	public void ConsultaCurso_ActionPerformed(ActionEvent e) {
		//BUSCO EL INSTITUTO y CURSO SELECCIONADO
		String strInstituto = this.comboBoxInstitutos.getSelectedItem().toString();
		String strCurso = this.comboBoxCursos.getSelectedItem().toString();
		
		//CREO EL DT CURSOINFO
		DtCursoInfo dtcri = iconCur.ConsultaCurso(strInstituto, strCurso);

		//SETEO LOS CAMPOS
		this.textFieldNombre.setText(dtcri.getNombre());
		this.textFieldDescripcion.setText(dtcri.getDescripcion());
		this.textFieldDuracion.setText(dtcri.getDuracion());
		this.textFieldCantHoras.setText(Integer.toString(dtcri.getCantHoras()));
		this.textFieldCantCreditos.setText(Integer.toString(dtcri.getCreditos()));
		this.textFieldUrl.setText(dtcri.getUrl());
		this.textFieldFechaAlta.setText(dtcri.getFechaAlta().toString());
	}
	
	public void comboBoxInitProgramas() {
		String strInstituto = this.comboBoxInstitutos.getSelectedItem().toString();
		String strCurso = this.comboBoxCursos.getSelectedItem().toString();
		DefaultComboBoxModel<String> modelProgramas = new DefaultComboBoxModel<String>(iconCur.listarProgramasAux(strInstituto, strCurso));
		comboBoxProgramas.setModel(modelProgramas);
	}
	// Printea la edicion de curso en el JTextPane
	
		public void infoEdicion(ActionEvent arg0) {
			String nombreInstituto = comboBoxInstitutos.getSelectedItem().toString();
			String nombreCurso = comboBoxCursos.getSelectedItem().toString();
			String nombreEdicion = comboBoxEdiciones.getSelectedItem().toString();
			printEdicion(textPane, iconCur.AtributosEdicion(nombreInstituto, nombreCurso, nombreEdicion));
		}
		
		public void printEdicion(JTextPane textPane, String[] arrayEdicion) {
			textPane.setText(arrayEdicion[0]+"\n"+arrayEdicion[1]+"\n"+arrayEdicion[2]+"\n"+arrayEdicion[3]+"\n"+arrayEdicion[4]+"\n"+arrayEdicion[5]);
		}
		public void infoPrograma(ActionEvent arg0) {
			String nombrePrograma = comboBoxProgramas.getSelectedItem().toString();
			printPrograma(textPane, iconCur.AtributosPrograma(nombrePrograma));
		}
		
		public void printPrograma(JTextPane textPane, String[] arrayPrograma) {
			textPane.setText(arrayPrograma[0]+"\n"+arrayPrograma[1]+"\n"+arrayPrograma[2]+"\n"+arrayPrograma[3]+"\n"+arrayPrograma[4]);
		}
		public void limpiarTextPane(JTextPane textPane) {
			textPane.setText("");
		}
		public void listPreviasInit() {
			String[] previas = iconCur.listarPrevias(this.comboBoxInstitutos.getSelectedItem().toString(), this.comboBoxCursos.getSelectedItem().toString());
			this.listPrevias.setListData(new String[0]);
			DefaultListModel<String> listModel = new DefaultListModel<String>();
			for(String p: previas) {
				listModel.addElement(p);
			}		
					
			this.listPrevias.setModel(listModel);
			scrollPanePrevias.getViewport().setView(listPrevias);
			
		}
		public void listCategoriasInit() {
			String[] categorias = iconCur.listarCategoriasC(this.comboBoxInstitutos.getSelectedItem().toString(), this.comboBoxCursos.getSelectedItem().toString());
			this.listCategorias.setListData(new String[0]);
			DefaultListModel<String> listModel = new DefaultListModel<String>();
			for(String c: categorias) {
				listModel.addElement(c);
			}		
					
			this.listCategorias.setModel(listModel);
			scrollPaneCategorias.getViewport().setView(listCategorias);
			
		}
		
		public void limpiarCampos() {
			this.textFieldNombre.setText("");
			this.textFieldDescripcion.setText("");
			this.textFieldDuracion.setText("");
			this.textFieldCantHoras.setText("");
			this.textFieldCantCreditos.setText("");
			this.textFieldUrl.setText("");
			this.textFieldFechaAlta.setText("");
		}
}
