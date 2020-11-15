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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
	private JList<String> listCategorias;
	private JScrollPane scrollPaneCategorias;
	private JLabel lblCategorias;


	/**
	 * Create the frame.
	 */
	public AltaCurso(IControladorCurso iconCur) {
		
		this.iconCur = iconCur;
		
		setTitle("Alta de Curso");
		setBounds(100, 100, 632, 300);
		
		comboBoxInstitutos = new JComboBox<String>();
		comboBoxInstitutos.setBounds(96, 31, 119, 20);
		comboBoxInstitutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listPreviasInit();
				listCategoriasInit();
			}
		});
		getContentPane().setLayout(null);
		getContentPane().add(comboBoxInstitutos);
		
		JLabel lblInstitutos = new JLabel("Institutos");
		lblInstitutos.setBounds(29, 34, 56, 14);
		lblInstitutos.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblInstitutos);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(96, 63, 119, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setBounds(96, 95, 119, 20);
		getContentPane().add(textFieldDescripcion);
		textFieldDescripcion.setColumns(10);
		
		textFieldDuracion = new JTextField();
		textFieldDuracion.setBounds(96, 127, 119, 20);
		getContentPane().add(textFieldDuracion);
		textFieldDuracion.setColumns(10);
		
		textFieldCantHoras = new JTextField();
		textFieldCantHoras.setBounds(96, 159, 119, 20);
		getContentPane().add(textFieldCantHoras);
		textFieldCantHoras.setColumns(10);
		
		textFieldCantCreditos = new JTextField();
		textFieldCantCreditos.setBounds(96, 194, 119, 20);
		getContentPane().add(textFieldCantCreditos);
		textFieldCantCreditos.setColumns(10);
		
		textFieldUrl = new JTextField();
		textFieldUrl.setBounds(225, 139, 199, 20);
		getContentPane().add(textFieldUrl);
		textFieldUrl.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 65, 75, 14);
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblNombre);
		
		lblDescripcion = new JLabel("Descripci\u00F3n");
		lblDescripcion.setBounds(10, 97, 75, 14);
		lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblDescripcion);
		
		lblDuracion = new JLabel("Duraci\u00F3n");
		lblDuracion.setBounds(10, 129, 75, 14);
		lblDuracion.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblDuracion);
		
		lblCantHoras = new JLabel("Cant. horas");
		lblCantHoras.setBounds(10, 161, 75, 14);
		lblCantHoras.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblCantHoras);
		
		lblCantCreditos = new JLabel("Cr\u00E9ditos");
		lblCantCreditos.setBounds(0, 195, 75, 16);
		lblCantCreditos.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblCantCreditos);
		
		lblURL = new JLabel("URL");
		lblURL.setBounds(225, 124, 44, 14);
		lblURL.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(lblURL);
		
		lblFechaAlta = new JLabel("Fecha alta");
		lblFechaAlta.setBounds(225, 176, 65, 14);
		lblFechaAlta.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(lblFechaAlta);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(225, 223, 89, 23);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfirmarAltaCurso_ActionPerformed(e);
			}
		});
		getContentPane().add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(335, 223, 89, 23);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CancelarAltaCurso_ActionPerformed(e);
			}
		});
		getContentPane().add(btnCancelar);
		
		dateChooserFechaAlta = new JDateChooser();
		dateChooserFechaAlta.setBounds(292, 173, 132, 20);
		getContentPane().add(dateChooserFechaAlta);
		
		listPrevias = new JList<String>();
		listPrevias.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listPrevias.setBounds(303, 31, 121, 80);
		//getContentPane().add(listPrevias);
			
		JLabel lblPrevias = new JLabel("Previas");
		lblPrevias.setBounds(225, 34, 57, 14);
		lblPrevias.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblPrevias);
		
		scrollPanePrevias = new JScrollPane();
		scrollPanePrevias.setBounds(292, 31, 132, 100);
		getContentPane().add(scrollPanePrevias);
		
		listCategorias = new JList<String>();
		listCategorias.setBounds(491, 33, 119, 105);
		//getContentPane().add(listCategorias);
		
		scrollPaneCategorias = new JScrollPane();
		scrollPaneCategorias.setBounds(491, 34, 119, 105);
		getContentPane().add(scrollPaneCategorias);
		
		lblCategorias = new JLabel("Categorias");
		lblCategorias.setBounds(430, 34, 70, 15);
		getContentPane().add(lblCategorias);
	
		

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
		String instituto = this.comboBoxInstitutos.getSelectedItem().toString();
		
		//OBTENER LAS PREVIAS
		List<String> previas = (List<String>) this.listPrevias.getSelectedValuesList();
		List<String> categorias = (List<String>) this.listCategorias.getSelectedValuesList();
		
		if(comprobarCampos()){
			int h = Integer.parseInt(cantHoras);
			String cantCreditos = this.textFieldCantCreditos.getText();
			int c = Integer.parseInt(cantCreditos);
		
			//OBTENGO LA FECHAS DATE Y LA CONVIERTO A LOCALDATE
			Date fechaAltaD = new Date();
			fechaAltaD = this.dateChooserFechaAlta.getDate();
			LocalDate fechaAlta = fechaAltaD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();	
			Calendar fechaCalendar = GregorianCalendar.from(fechaAlta.atStartOfDay(ZoneId.systemDefault()));
			
			DtCurso curso = new DtCurso(nombre, descripcion, duracion, h, c, fechaCalendar, url, instituto);
						
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
			for(String cat: categorias) {
				this.iconCur.agregarCategorias(cat, instituto, curso.getNombre());
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
	public void listCategoriasInit() {
		String[] categorias = iconCur.listarCategorias();
		
		this.listCategorias.setListData(new String[0]);
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for(String c:categorias) {
			listModel.addElement(c);
		}		
	
		this.listCategorias.setModel(listModel);
		
		scrollPaneCategorias.getViewport().setView(listCategorias);

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