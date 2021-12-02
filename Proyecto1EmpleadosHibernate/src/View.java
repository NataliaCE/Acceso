import java.awt.Font;
import java.awt.TextComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class View extends JFrame {

	private JFrame frame;
	JTextField txtNEmpleado;
	JTextField txtApellido;
	JTextField txtOficio;
	JTextField txtSalario;
	JTextField txtComision;
	JTextField txtFecha;
	JComboBox<String> CmbxDepartamento;
	JComboBox<String> CmbxDirector;
	JButton btnConsultar;
	JButton btnInsertar;
	JButton btnEliminar;
	JButton btnModificar;
	JButton btnSalir;
	JButton btnLimpiar;
	
	View() {
		JFrame frmGestinDeEmpleados = new JFrame();
		frmGestinDeEmpleados.setTitle("Gesti\u00F3n de empleados");
		frmGestinDeEmpleados.setBounds(100, 100, 450, 300);
		frmGestinDeEmpleados.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGestinDeEmpleados.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("GESTI\u00D3N DE EMPLEADOS");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(68, 11, 153, 14);
		frmGestinDeEmpleados.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("N\u00BA EMPLEADO:");
		lblNewLabel_1.setBounds(24, 39, 83, 14);
		frmGestinDeEmpleados.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("APELLIDO:");
		lblNewLabel_2.setBounds(24, 64, 83, 14);
		frmGestinDeEmpleados.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("OFICIO:");
		lblNewLabel_3.setBounds(24, 89, 83, 14);
		frmGestinDeEmpleados.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("SALARIO:");
		lblNewLabel_4.setBounds(24, 114, 83, 14);
		frmGestinDeEmpleados.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("COMISI\u00D3N:");
		lblNewLabel_5.setBounds(24, 139, 83, 14);
		frmGestinDeEmpleados.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("FECHA ALTA:");
		lblNewLabel_6.setBounds(245, 139, 83, 14);
		frmGestinDeEmpleados.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("(yyyy-MM-dd)");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblNewLabel_7.setBounds(336, 153, 58, 14);
		frmGestinDeEmpleados.getContentPane().add(lblNewLabel_7);
		
		//EDIT TEXT
		txtNEmpleado = new JTextField();
		txtNEmpleado.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isDigit(c) || txtNEmpleado.getText().length() > 4) {
					e.consume();
				}
			}
		});
		txtNEmpleado.setBounds(116, 36, 86, 20);
		frmGestinDeEmpleados.getContentPane().add(txtNEmpleado);
		txtNEmpleado.setColumns(10);
		txtNEmpleado.setTransferHandler(null);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(116, 61, 153, 20);
		frmGestinDeEmpleados.getContentPane().add(txtApellido);
		txtApellido.setColumns(10);
		txtApellido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isAlphabetic(c) || txtApellido.getText().length() > 10) {
					e.consume();
				}
			}
		});
		
		txtOficio = new JTextField();
		txtOficio.setBounds(116, 86, 153, 20);
		frmGestinDeEmpleados.getContentPane().add(txtOficio);
		txtOficio.setColumns(10);
		txtOficio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isAlphabetic(c) || txtOficio.getText().length() > 10) {
					e.consume();
				}
			}
		});
		
		txtSalario = new JTextField();
		txtSalario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(c != 46) {
					if(!Character.isDigit(c) || txtSalario.getText().length() > 10) {
						e.consume();
					}
				}
				
			}
		});
		txtSalario.setBounds(116, 111, 86, 20);
		frmGestinDeEmpleados.getContentPane().add(txtSalario);
		txtSalario.setColumns(10);
		
		txtComision = new JTextField();
		txtComision.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isDigit(c) || txtSalario.getText().length() > 10) {
					e.consume();
				}
			}
		});
		txtComision.setBounds(116, 136, 86, 20);
		frmGestinDeEmpleados.getContentPane().add(txtComision);
		txtComision.setColumns(10);
		
		txtFecha = new JTextField();
		txtFecha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		txtFecha.setBounds(326, 136, 86, 20);
		frmGestinDeEmpleados.getContentPane().add(txtFecha);
		txtFecha.setColumns(10);
		
		//COMBO BOX
		CmbxDepartamento = new JComboBox();
		CmbxDepartamento.setToolTipText("Elige departamento");
		CmbxDepartamento.setBounds(279, 73, 145, 22);
		frmGestinDeEmpleados.getContentPane().add(CmbxDepartamento);
		
		CmbxDirector = new JComboBox();
		CmbxDirector.setToolTipText("Elige director");
		CmbxDirector.setBounds(279, 106, 145, 22);
		frmGestinDeEmpleados.getContentPane().add(CmbxDirector);
		
		//BOTONES
		btnConsultar = new JButton("CONSULTAR EMPLEADO");
		btnConsultar.setBounds(245, 35, 179, 23);
		frmGestinDeEmpleados.getContentPane().add(btnConsultar);
		
		btnInsertar = new JButton("INSERTAR");
		btnInsertar.setBounds(48, 183, 102, 23);
		frmGestinDeEmpleados.getContentPane().add(btnInsertar);
		
		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setBounds(170, 183, 102, 23);
		frmGestinDeEmpleados.getContentPane().add(btnEliminar);
		
		btnModificar = new JButton("MODIFICAR");
		btnModificar.setBounds(289, 183, 102, 23);
		frmGestinDeEmpleados.getContentPane().add(btnModificar);
		
		btnSalir = new JButton("SALIR");
		btnSalir.setBounds(115, 217, 102, 23);
		frmGestinDeEmpleados.getContentPane().add(btnSalir);
		
		btnLimpiar = new JButton("LIMPIAR");
		btnLimpiar.setBounds(237, 217, 102, 23);
		frmGestinDeEmpleados.getContentPane().add(btnLimpiar);
		
		frmGestinDeEmpleados.setVisible(true);
	}
	
	public void conectaControlador(Controlador c) {
		btnConsultar.addActionListener(c);
		btnConsultar.setActionCommand("CONSULTAR");
		
		btnInsertar.addActionListener(c);
		btnInsertar.setActionCommand("INSERTAR");
		
		btnEliminar.addActionListener(c);
		btnEliminar.setActionCommand("ELIMINAR");
		
		btnSalir.addActionListener(c);
		btnSalir.setActionCommand("SALIR");
		
		btnLimpiar.addActionListener(c);
		btnLimpiar.setActionCommand("LIMPIAR");
		
		btnModificar.addActionListener(c);
		btnModificar.setActionCommand("MODIFICAR");
	}
	
}
