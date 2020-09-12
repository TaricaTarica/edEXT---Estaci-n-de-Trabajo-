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
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import datatypes.DtCurso;
import datatypes.DtCursoInfo;

import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JSeparator;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import java.awt.Color;

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
	private JList<String> listPrevias;
	
	
	/**
	 * Create the frame.
	 */
	public ConsultaCurso(IControladorCurso iconCur) {
		
		this.iconCur = iconCur;
		
		setTitle("Consulta de Curso");
		setBounds(100, 100, 450, 337);
		getContentPane().setLayout(null);
		
		comboBoxInstitutos = new JComboBox<String>();
		comboBoxInstitutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxInitCursos();
			}
		});
		comboBoxInstitutos.setBounds(90, 26, 131, 20);
		getContentPane().add(comboBoxInstitutos);
		
		JLabel lblInstituto = new JLabel("Instituto");
		lblInstituto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInstituto.setBounds(22, 29, 46, 14);
		getContentPane().add(lblInstituto);
		
		JLabel lblCurso = new JLabel("Curso");
		lblCurso.setHorizontalAlignment(SwingConstants.LEFT);
		lblCurso.setBounds(237, 29, 46, 14);
		getContentPane().add(lblCurso);
		
		comboBoxCursos = new JComboBox<String>();
		comboBoxCursos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//CUANDO SELECCIONA UN CURSO, SE CARGA LA INFO EN LOS CAMPOS
				ConsultaCurso_ActionPerformed(e);
				listPreviasInit();
				comboBoxInitEdiciones();
				comboBoxInitProgramas();
			}
		});
		comboBoxCursos.setBounds(293, 26, 119, 20);
		getContentPane().add(comboBoxCursos);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnSalir.setBounds(293, 253, 89, 23);
		getContentPane().add(btnSalir);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBackground(Color.WHITE);
		textFieldNombre.setBounds(90, 70, 131, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(10, 73, 72, 14);
		getContentPane().add(lblNombre);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setBounds(90, 101, 131, 20);
		getContentPane().add(textFieldDescripcion);
		textFieldDescripcion.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescripcion.setBounds(10, 104, 72, 14);
		getContentPane().add(lblDescripcion);
		
		textFieldDuracion = new JTextField();
		textFieldDuracion.setBounds(90, 132, 131, 20);
		getContentPane().add(textFieldDuracion);
		textFieldDuracion.setColumns(10);
		
		JLabel lblDuracion = new JLabel("Duración");
		lblDuracion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDuracion.setBounds(10, 135, 72, 14);
		getContentPane().add(lblDuracion);
		
		textFieldCantHoras = new JTextField();
		textFieldCantHoras.setBounds(90, 163, 131, 20);
		getContentPane().add(textFieldCantHoras);
		textFieldCantHoras.setColumns(10);
		
		textFieldCantCreditos = new JTextField();
		textFieldCantCreditos.setBounds(90, 194, 131, 20);
		getContentPane().add(textFieldCantCreditos);
		textFieldCantCreditos.setColumns(10);
		
		textFieldUrl = new JTextField();
		textFieldUrl.setBounds(90, 225, 131, 20);
		getContentPane().add(textFieldUrl);
		textFieldUrl.setColumns(10);
		
		JLabel lblCantHoras = new JLabel("Cant. horas");
		lblCantHoras.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCantHoras.setBounds(10, 166, 72, 14);
		getContentPane().add(lblCantHoras);
		
		JLabel lblCantCreditos = new JLabel("Cant. créditos");
		lblCantCreditos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCantCreditos.setBounds(10, 197, 72, 14);
		getContentPane().add(lblCantCreditos);
		
		JLabel lblUrl = new JLabel("URL");
		lblUrl.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUrl.setBounds(10, 228, 72, 14);
		getContentPane().add(lblUrl);
		
		listPrevias = new JList<String>();
		listPrevias.setBounds(293, 70, 119, 76);
		getContentPane().add(listPrevias);
		
		JLabel lblPrevias = new JLabel("Previas");
		lblPrevias.setBounds(237, 73, 46, 14);
		getContentPane().add(lblPrevias);
		
		comboBoxEdiciones = new JComboBox<String>();
		comboBoxEdiciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ACA LLAMO AL CU CONSULTA DE EDICION (NAREN)
			}
		});
		comboBoxEdiciones.setBounds(237, 169, 175, 20);
		getContentPane().add(comboBoxEdiciones);
		
		comboBoxProgramas = new JComboBox<String>();
		comboBoxProgramas.setBounds(237, 213, 175, 20);
		getContentPane().add(comboBoxProgramas);
		
		JLabel lblEdiciones = new JLabel("Ediciones de Curso");
		lblEdiciones.setBounds(237, 155, 175, 14);
		getContentPane().add(lblEdiciones);
		
		JLabel lblNewLabel = new JLabel("Prog. de Formacion");
		lblNewLabel.setBounds(237, 200, 119, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblFechaAlta = new JLabel("Fecha alta");
		lblFechaAlta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaAlta.setBounds(22, 259, 60, 14);
		getContentPane().add(lblFechaAlta);
		
		textFieldFechaAlta = new JTextField();
		textFieldFechaAlta.setBounds(90, 256, 131, 20);
		getContentPane().add(textFieldFechaAlta);
		textFieldFechaAlta.setColumns(10);
		
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
	public void listPreviasInit() {
		String[] previas = iconCur.listarPrevias(this.comboBoxInstitutos.getSelectedItem().toString(),this.comboBoxCursos.getSelectedItem().toString());
		this.listPrevias.setListData(new String[0]);
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for(String p: previas) {
			listModel.addElement(p);
		}		
				
		this.listPrevias.setModel(listModel);
	}
}
