package presentacion;

//import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

//import datatypes.DtCurso;
import datatypes.DtEdicion;
//import datatypes.DtDocente;
import interfaces.IControladorCurso;
import logica.Docente;

//import logica.Docente;
import javax.swing.JButton;
import excepciones.EdicionRepatida_Exception;
import com.toedter.calendar.JDateChooser;
import javax.swing.JCheckBox;
import javax.swing.JList;


public class AltadeEdiciondeCurso extends JInternalFrame {
	
	IControladorCurso iconCur;
	
	private JTextField textFieldNombre;
	private JTextField textFieldCupo;
	private JComboBox<String> comboBoxCursos;
	private JComboBox<String> comboBoxInstitutos;
	private JDateChooser dateChooserFechaInicio;
	private JDateChooser dateChooserFechaFin;
	private JDateChooser dateChooserFechaPub;
	private JList<String> listDocentes;
	

	private static final long serialVersionUID = 1L;
	/**
	 * Create the frame.
	 */
	public AltadeEdiciondeCurso(IControladorCurso iconCur) {
		
		this.iconCur = iconCur;
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(false);
		setTitle("Alta de Edicion de Curso");
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
				
		comboBoxInstitutos = new JComboBox<String>();
		comboBoxInstitutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//ACTION PERFORMED DE JCOMBOBOX DE CURSOS
				comboBoxInit2();
				listDocentesInit();
			}
		});
		comboBoxInstitutos.setBounds(106, 22, 70, 15);
		getContentPane().add(comboBoxInstitutos);
	
		JLabel lblInstituto = new JLabel("Institutos");
		lblInstituto.setBounds(26, 22, 70, 15);
		getContentPane().add(lblInstituto);
		
		comboBoxCursos = new JComboBox<String>();
		comboBoxCursos.setBounds(106, 49, 70, 15);
		getContentPane().add(comboBoxCursos);
		
		JLabel lblCurso = new JLabel("Cursos");
		lblCurso.setBounds(26, 49, 70, 15);
		getContentPane().add(lblCurso);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(80, 89, 114, 19);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldCupo = new JTextField();
		textFieldCupo.setBounds(80, 183, 114, 19);
		getContentPane().add(textFieldCupo);
		textFieldCupo.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(12, 91, 70, 15);
		getContentPane().add(lblNombre);
		
		JLabel lblFechaInicio = new JLabel("FechaInicio");
		lblFechaInicio.setBounds(12, 119, 70, 15);
		getContentPane().add(lblFechaInicio);
		
		JLabel lblFechafin = new JLabel("FechaFin");
		lblFechafin.setBounds(12, 150, 70, 15);
		getContentPane().add(lblFechafin);
		
		JLabel lblCupo = new JLabel("Cupo");
		lblCupo.setBounds(12, 185, 70, 15);
		getContentPane().add(lblCupo);
		
		JLabel lblFechapub = new JLabel("FechaPub");
		lblFechapub.setBounds(12, 216, 70, 15);
		getContentPane().add(lblFechapub);
		
		JLabel lblDocente = new JLabel("Docente");
		lblDocente.setBounds(209, 22, 70, 15);
		getContentPane().add(lblDocente);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfirmarAltadeEdiciondeCurso_ActionPerformed(e);
			}
		});
		btnAceptar.setBounds(288, 145, 117, 25);
		getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CancelarAltaInstituto_actionPerformed(e); //La funci�n est� definida abajo
			}
		});
		btnCancelar.setBounds(288, 180, 117, 25);
		getContentPane().add(btnCancelar);
		
		dateChooserFechaInicio = new JDateChooser();
		dateChooserFechaInicio.setBounds(80, 119, 114, 20);
		getContentPane().add(dateChooserFechaInicio);
		
		dateChooserFechaFin = new JDateChooser();
		dateChooserFechaFin.setBounds(80, 150, 114, 20);
		getContentPane().add(dateChooserFechaFin);
		
		dateChooserFechaPub = new JDateChooser();
		dateChooserFechaPub.setBounds(80, 215, 114, 20);
		getContentPane().add(dateChooserFechaPub);
		
		listDocentes = new JList<String>();
		listDocentes.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listDocentes.setBounds(288, 21, 117, 87);
		getContentPane().add(listDocentes);

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
	
	public void listDocentesInit() {
		String[] docentes = iconCur.listarDocentes();
		DefaultListModel<String> listModel = new DefaultListModel<String>();

		for(String doc: docentes) {
			listModel.addElement(doc);
		}		
				
		listDocentes.setModel(listModel);
		
	}
	
	protected void ConfirmarAltadeEdiciondeCurso_ActionPerformed(ActionEvent e) {
		//OBTENGO TODOS LOS DATOS DE LA PRESENTACION
		String nombre = this.textFieldNombre.getText();
		String cupo = this.textFieldCupo.getText();
		int c = Integer.parseInt(cupo);
		
		//OBTENER LOS DOCENTES
		List<String> docentes = (List<String>) this.listDocentes.getSelectedValuesList();
		
		//OBTENGO LAS FECHAS DATE Y LAS CONVIERTO A LOCALDATE
		Date fechaIniD = new Date();
		fechaIniD = this.dateChooserFechaInicio.getDate();
		LocalDate fechaIni = fechaIniD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();		
		
		Date fechaFinD = new Date();
		fechaFinD = this.dateChooserFechaFin.getDate();
		LocalDate fechaFin = fechaFinD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();	
		
		Date fechaPubD = new Date();
		fechaPubD = this.dateChooserFechaPub.getDate();
		LocalDate fechaPub = fechaPubD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		//CREACION DEL DT EDICION. INSTITUTO Y CURSO
		DtEdicion edicion = new DtEdicion(nombre,fechaIni,fechaFin,c,fechaPub); //COMO TRABAJAR EL DTDOCENTE(LISTA DE DOCENTES)
		String instituto = this.comboBoxInstitutos.getSelectedItem().toString();
		String curso = this.comboBoxCursos.getSelectedItem().toString();
			try{
				this.iconCur.AltadeEdiciondeCurso(edicion, instituto,curso);
				JOptionPane.showMessageDialog(this, "Edicion a sido creada con �xito", "Creac�on exitosa", JOptionPane.INFORMATION_MESSAGE);
			}
			catch(EdicionRepatida_Exception ex){
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		for(String doc: docentes) {
			this.iconCur.asociarEdicion(doc, edicion, instituto, curso);
		}


	}
	protected void CancelarAltaInstituto_actionPerformed(ActionEvent e) {
		limpiarCampos();
		setVisible(false);
	}
	private void limpiarCampos() {
		this.textFieldNombre.setText("");
		//this.textFieldFechaInicio.setText("");
		//this.textFieldFechaFin.setText("");
		this.textFieldCupo.setText("");
		//this.textFieldFechaPub.setText("");
		//this.textFieldDocente.setText("");
	}
}
