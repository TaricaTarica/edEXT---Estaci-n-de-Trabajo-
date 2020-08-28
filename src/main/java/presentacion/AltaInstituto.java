package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;


public class AltaInstituto extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextField txtNombreInstituto;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaInstituto frame = new AltaInstituto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AltaInstituto() {
		setTitle("Alta de Instituto");
		setBounds(100, 100, 692, 435);
		getContentPane().setLayout(null);
		
		txtNombreInstituto = new JTextField();
		txtNombreInstituto.setBounds(286, 102, 154, 20);
		getContentPane().add(txtNombreInstituto);
		txtNombreInstituto.setColumns(10);
		
		JLabel lblNombreInstituto = new JLabel("Nombre del Instituto");
		lblNombreInstituto.setBounds(137, 105, 143, 14);
		getContentPane().add(lblNombreInstituto);
		
		JButton btnNewButton = new JButton("Ingresar");
		btnNewButton.setBounds(259, 168, 89, 23);
		getContentPane().add(btnNewButton);

	}
}
