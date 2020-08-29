package presentacion;


import javax.swing.JInternalFrame;
import javax.swing.JTextField;

import excepciones.InstitutoRepetido_Exception;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;

import interfaces.IControladorCurso;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class AltaInstituto extends JInternalFrame {
	private IControladorCurso iconCur;
	
	private static final long serialVersionUID = 1L;
	
	private JTextField txtNombreInstituto;
	
	public AltaInstituto(IControladorCurso iconCur) {
		this.iconCur = iconCur;
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(false);
		setTitle("Alta de Instituto");
		setBounds(100, 100, 450, 200); //acá modificas el tamaño de la ventana
		getContentPane().setLayout(null);
		txtNombreInstituto = new JTextField();
		txtNombreInstituto.setBounds(147, 70, 154, 20);
		getContentPane().add(txtNombreInstituto);
		txtNombreInstituto.setColumns(10);
		
		JLabel lblNombreInstituto = new JLabel("Nombre del Instituto");
		lblNombreInstituto.setBounds(10, 73, 143, 14);
		getContentPane().add(lblNombreInstituto);
		
		JButton btnNewButton = new JButton("Ingresar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IngresarAltaInstituto_actionPerformed(e); //La función está definida abajo
			}
		});
		btnNewButton.setBounds(311, 69, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CancelarAltaInstituto_actionPerformed(e); //La función está definida abajo
			}
		});
		btnCancelar.setBounds(311, 103, 89, 23);
		getContentPane().add(btnCancelar);
		
	}
	/*FUNCIONES CREADAS POR HUMANOS*/
	
	protected void IngresarAltaInstituto_actionPerformed(ActionEvent e) {
		String nombreInstituto = this.txtNombreInstituto.getText();
		if (comprobarCampos()) {
			try {
				this.iconCur.AltaInstituto(nombreInstituto);
				JOptionPane.showMessageDialog(this, "El Instituto se ha creado con éxito", "Alta exitosa",
                        JOptionPane.INFORMATION_MESSAGE);
			}
			catch(InstitutoRepetido_Exception ex){
				JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	protected void CancelarAltaInstituto_actionPerformed(ActionEvent e) {
		limpiarCampos();
		setVisible(false);
	}
	
	protected boolean comprobarCampos() {
		String nombreInstituto = this.txtNombreInstituto.getText();
		if(nombreInstituto.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Debe ingresar un nombre", "ERROR",
	                JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else {
			return true;
		}
	}
	private void limpiarCampos() {
		this.txtNombreInstituto.setText("");
	}
}
