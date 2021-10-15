//EJERCICIO 2

package sesion1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class ControlAcceso {

	private JFrame frame;
	private JTextField txtUsuario;
	private JPasswordField txtContrasenya;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ControlAcceso window = new ControlAcceso();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ControlAcceso() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsuario.setBounds(61, 63, 86, 14);
		frame.getContentPane().add(lblUsuario);
		
		JLabel lblContrasenya = new JLabel("Contrase\u00F1a: ");
		lblContrasenya.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContrasenya.setBounds(61, 97, 86, 14);
		frame.getContentPane().add(lblContrasenya);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtUsuario.setBounds(157, 62, 139, 20);
		frame.getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblResultado = new JLabel("");
		lblResultado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblResultado.setBounds(61, 201, 306, 49);
		frame.getContentPane().add(lblResultado);
		
		JButton btnAcceso = new JButton("Acceder");
		btnAcceso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String usuario = txtUsuario.getText();
				String contrasenya = String.valueOf(txtContrasenya.getPassword());
				String resultado = null;
				AccesoBD abd = new AccesoBD();
				try {
					abd.conectar();
					resultado = abd.verificacion(usuario, contrasenya);
					
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(resultado != null) {
					lblResultado.setText("Bienvenido, " + resultado);
				} else {
					lblResultado.setText("El usuario o contraseña no son correctos.");
				}
				
			}
		});
		btnAcceso.setBounds(180, 143, 89, 23);
		frame.getContentPane().add(btnAcceso);
		
		txtContrasenya = new JPasswordField();
		txtContrasenya.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtContrasenya.setBounds(157, 96, 139, 20);
		frame.getContentPane().add(txtContrasenya);
		
		
	}
}
