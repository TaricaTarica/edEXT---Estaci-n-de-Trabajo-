package presentacion;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;

import excepciones.CategoriaRepetida_Exception;
import excepciones.InstitutoRepetido_Exception;
import interfaces.IControladorCurso;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;

public class AltaCategoria extends JInternalFrame {

private IControladorCurso iconCur;
	
	private static final long serialVersionUID = 1L;
	
		private JTextField textnombreCategoria;
	
	public AltaCategoria(IControladorCurso iconCur) {
		this.iconCur = iconCur;
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(false);
		setTitle("Alta de Categoría");
		setBounds(100, 100, 450, 200);
		getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(35, 56, 78, 14);
		getContentPane().add(lblNombre);
		
		textnombreCategoria = new JTextField();
		textnombreCategoria.setBounds(97, 53, 222, 20);
		getContentPane().add(textnombreCategoria);
		textnombreCategoria.setColumns(10);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmarAltaCategoria(e);
			}
		});
		btnConfirmar.setBounds(97, 89, 97, 23);
		getContentPane().add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelarAltaCategoria(e);
			}
		});
		btnCancelar.setBounds(222, 89, 97, 23);
		getContentPane().add(btnCancelar);

	}
	public void confirmarAltaCategoria(ActionEvent e) {
		String nombreCategoria = this.textnombreCategoria.getText();
		if(confirmarCampos()) {
			try {
				this.iconCur.AltaCategoria(nombreCategoria);
				JOptionPane.showMessageDialog(this, "Categoria creada con exito", "Alta exitosa",
                        JOptionPane.INFORMATION_MESSAGE);
				limpiarCampos();
			}
			catch(CategoriaRepetida_Exception ex){
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	public boolean confirmarCampos() {
		String nombreCategoria = this.textnombreCategoria.getText();
		if(nombreCategoria.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Debe ingresar un nombre", "Error",
	                JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	public void limpiarCampos() {
		this.textnombreCategoria.setText("");
	}
	public void cancelarAltaCategoria(ActionEvent e) {
		limpiarCampos();
		setVisible(false);
	}
}
