package presentacion;
import datatypes.DtCurso;
import excepciones.CursoRepetido_Exception;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import interfaces.IControladorCurso;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;

import com.toedter.calendar.JDateChooser;
import javax.swing.JList;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.TextArea;

public class AltaCurso extends JInternalFrame {

	IControladorCurso iconCur;
	private JComboBox<String> comboBoxInstitutos;

	private static final long serialVersionUID = 1L;
	private JTextField textFieldNombre;
	private JTextField textFieldDescripcion;
	private JTextField textFieldDuracion;
	private JTextField textFieldCantHoras;
	private JTextField textFieldCantCreditos;
	private JTextField textFieldUrl;
	private JLabel lblDescripcion;
	private JLabel lblDuracion;
	private JLabel lblCantHoras;
	private JLabel lblCantCreditos;
	private JLabel lblURL;
	private JLabel lblFechaAlta;
	private JDateChooser dateChooserFechaAlta;
	private JList<String> listPrevias;
	private JScrollPane scrollPanePrevias;


	/**
	 * Create the frame.
	 */
	public AltaCurso(IControladorCurso iconCur) {
		
		this.iconCur = iconCur;
		
		setTitle("Alta de Curso");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		comboBoxInstitutos = new JComboBox<String>();
		comboBoxInstitutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listPreviasInit();
			}
		});
		comboBoxInstitutos.setBounds(96, 31, 119, 20);
		getContentPane().add(comboBoxInstitutos);
		
		JLabel lblInstitutos = new JLabel("Institutos");
		lblInstitutos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInstitutos.setBounds(29, 34, 56, 14);
		getContentPane().add(lblInstitutos);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(96, 65, 119, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setBounds(96, 96, 119, 20);
		getContentPane().add(textFieldDescripcion);
		textFieldDescripcion.setColumns(10);
		
		textFieldDuracion = new JTextField();
		textFieldDuracion.setBounds(96, 127, 119, 20);
		getContentPane().add(textFieldDuracion);
		textFieldDuracion.setColumns(10);
		
		textFieldCantHoras = new JTextField();
		textFieldCantHoras.setBounds(96, 158, 119, 20);
		getContentPane().add(textFieldCantHoras);
		textFieldCantHoras.setColumns(10);
		
		textFieldCantCreditos = new JTextField();
		textFieldCantCreditos.setBounds(96, 189, 119, 20);
		getContentPane().add(textFieldCantCreditos);
		textFieldCantCreditos.setColumns(10);
		
		textFieldUrl = new JTextField();
		textFieldUrl.setBounds(225, 139, 199, 20);
		getContentPane().add(textFieldUrl);
		textFieldUrl.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(10, 68, 75, 14);
		getContentPane().add(lblNombre);
		
		lblDescripcion = new JLabel("Descripci\u00F3n");
		lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescripcion.setBounds(10, 99, 75, 14);
		getContentPane().add(lblDescripcion);
		
		lblDuracion = new JLabel("Duraci\u00F3n");
		lblDuracion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDuracion.setBounds(10, 130, 75, 14);
		getContentPane().add(lblDuracion);
		
		lblCantHoras = new JLabel("Cant. horas");
		lblCantHoras.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCantHoras.setBounds(10, 161, 75, 14);
		getContentPane().add(lblCantHoras);
		
		lblCantCreditos = new JLabel("Cr\u00E9ditos");
		lblCantCreditos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCantCreditos.setBounds(10, 189, 75, 14);
		getContentPane().add(lblCantCreditos);
		
		lblURL = new JLabel("URL");
		lblURL.setHorizontalAlignment(SwingConstants.LEFT);
		lblURL.setBounds(225, 124, 44, 14);
		getContentPane().add(lblURL);
		
		lblFechaAlta = new JLabel("Fecha alta");
		lblFechaAlta.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaAlta.setBounds(225, 176, 65, 14);
		getContentPane().add(lblFechaAlta);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfirmarAltaCurso_ActionPerformed(e);
			}
		});
		btnConfirmar.setBounds(225, 223, 89, 23);
		getContentPane().add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CancelarAltaCurso_ActionPerformed(e);
			}
		});
		btnCancelar.setBounds(335, 223, 89, 23);
		getContentPane().add(btnCancelar);
		
		dateChooserFechaAlta = new JDateChooser();
		dateChooserFechaAlta.setBounds(292, 173, 132, 20);
		getContentPane().add(dateChooserFechaAlta);
		
		listPrevias = new JList<String>();
		listPrevias.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listPrevias.setBounds(303, 31, 121, 80);
		//getContentPane().add(listPrevias);
			
		JLabel lblPrevias = new JLabel("Previas");
		lblPrevias.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrevias.setBounds(225, 34, 57, 14);
		getContentPane().add(lblPrevias);
		
		scrollPanePrevias = new JScrollPane();
		scrollPanePrevias.setBounds(292, 31, 132, 100);
		getContentPane().add(scrollPanePrevias);
	
		

	}
	
	public void comboBoxInit() {
		DefaultComboBoxModel<String> modelInstitutos = new DefaultComboBoxModel<String>(iconCur.listarInstitutos());
		comboBoxInstitutos.setModel(modelInstitutos);
	}
	
	public void ConfirmarAltaCurso_ActionPerformed(ActionEvent e) {
		//OBTENGO TODOS LOS DATOS DE LA PRESENTACION
		String nombre = this.textFieldNombre.getText();
		String descripcion = this.textFieldDescripcion.getText();
		String duracion = this.textFieldDuracion.getText();
		String cantHoras = this.textFieldCantHoras.getText();
		String url = this.textFieldUrl.getText();
		
		//OBTENER LAS PREVIAS
		List<String> previas = (List<String>) this.listPrevias.getSelectedValuesList();
		
		if(comprobarCampos()){
			int h = Integer.parseInt(cantHoras);
			String cantCreditos = this.textFieldCantCreditos.getText();
			int c = Integer.parseInt(cantCreditos);
		
			//OBTENGO LA FECHAS DATE Y LA CONVIERTO A LOCALDATE
			Date fechaAltaD = new Date();
			fechaAltaD = this.dateChooserFechaAlta.getDate();
			LocalDate fechaAlta = fechaAltaD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();		

		
			DtCurso curso = new DtCurso(nombre, descripcion, duracion, h, c, fechaAlta, url);
			
			String instituto = this.comboBoxInstitutos.getSelectedItem().toString();
			
			try {
				iconCur.AltaCurso(curso, instituto);
				JOptionPane.showMessageDialog(this, "Curso creado con exito", "Alta exitosa", JOptionPane.INFORMATION_MESSAGE);
				limpiarCampos();
			}catch(CursoRepetido_Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			for(String p: previas) {
				this.iconCur.agregarPrevia(p, instituto, curso.getNombre());
			}
			
		}
		
	}
	public boolean comprobarCampos() {
		int comboBoxInstitutos = this.comboBoxInstitutos.getItemCount();
		JDateChooser dateChooserFechaAlta = this.dateChooserFechaAlta;
		Date fecha = dateChooserFechaAlta.getDate();
		String nombre = this.textFieldNombre.getText();
		String descripcion = this.textFieldDescripcion.getText();
		String duracion = this.textFieldDuracion.getText();
		String cantHoras = this.textFieldCantHoras.getText();
		String cantCreditos = this.textFieldCantCreditos.getText();
		String url = this.textFieldUrl.getText();
		if(comboBoxInstitutos == 0) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar un instituto", "Error",
                    JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (nombre.isEmpty() || descripcion.isEmpty() || duracion.isEmpty() || cantHoras.isEmpty() || cantCreditos.isEmpty() || url.isEmpty() || fecha == null) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacios", "Error",
                    JOptionPane.ERROR_MESSAGE);
			return false;
		}
		try {
			int h = Integer.parseInt(cantHoras);
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "La cantidad de horas debe ser un numero", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
		}
		try {
			int c = Integer.parseInt(cantCreditos);
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "La cantidad de creditos debe ser un numero", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
		}
		return true;
	}
	public void CancelarAltaCurso_ActionPerformed(ActionEvent e) {
		limpiarCampos();
		setVisible(false);
	}
	public void listPreviasInit() {
		String[] cursos = iconCur.listarCursos(this.comboBoxInstitutos.getSelectedItem().toString());
		
		this.listPrevias.setListData(new String[0]);
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for(String c: cursos) {
			listModel.addElement(c);
		}		
	
		this.listPrevias.setModel(listModel);
		
		scrollPanePrevias.getViewport().setView(listPrevias);

	}
	
	public void limpiarCampos() {
		this.dateChooserFechaAlta.setCalendar(null);
		this.textFieldNombre.setText("");
		this.textFieldDescripcion.setText("");
		this.textFieldDuracion.setText("");
		this.textFieldCantHoras.setText("");
		this.textFieldCantCreditos.setText("");
		this.textFieldUrl.setText("");
		this.listPrevias.clearSelection();
	}
}
