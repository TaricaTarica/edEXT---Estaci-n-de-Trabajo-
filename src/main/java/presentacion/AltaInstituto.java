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
import javax.swing.SwingConstants;



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
		setBounds(100, 100, 450, 200); //ac� modificas el tama�o de la ventana
		getContentPane().setLayout(null);
		txtNombreInstituto = new JTextField();
		txtNombreInstituto.setBounds(137, 52, 188, 20);
		getContentPane().add(txtNombreInstituto);
		txtNombreInstituto.setColumns(10);
		
		JLabel lblNombreInstituto = new JLabel("Nombre");
		lblNombreInstituto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreInstituto.setBounds(0, 55, 127, 14);
		getContentPane().add(lblNombreInstituto);
		
		JButton btnNewButton = new JButton("Ingresar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IngresarAltaInstituto_actionPerformed(e); //La funci�n est� definida abajo
			}
		});
		btnNewButton.setBounds(137, 95, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CancelarAltaInstituto_actionPerformed(e); //La funci�n est� definida abajo
			}
		});
		btnCancelar.setBounds(236, 95, 89, 23);
		getContentPane().add(btnCancelar);
		
	}
	/*FUNCIONES CREADAS POR HUMANOS*/
	
	protected void IngresarAltaInstituto_actionPerformed(ActionEvent e) {
		String nombreInstituto = this.txtNombreInstituto.getText();
		if (comprobarCampos()) {
			try {
				this.iconCur.AltaInstituto(nombreInstituto);
				JOptionPane.showMessageDialog(this, "Instituto creado con exito", "Alta exitosa",
                        JOptionPane.INFORMATION_MESSAGE);
				limpiarCampos();
			}
			catch(InstitutoRepetido_Exception ex){
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
			JOptionPane.showMessageDialog(this, "Debe ingresar un nombre", "Error",
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
