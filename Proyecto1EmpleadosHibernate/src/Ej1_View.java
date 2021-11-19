import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import primero.Empleados;
import primero.SessionFactoryUtil;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ej1_View {


	private JFrame frame;
	JTextField txtNEmpleado;
	JTextField txtApellido;
	JTextField txtOficio;
	JTextField txtSalario;
	JTextField txtComision;
	JTextField txtFecha;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ej1_View window = new Ej1_View();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
	}

	/**
	 * Create the application.
	 */
	public Ej1_View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
		
		txtNEmpleado = new JTextField();
		txtNEmpleado.setBounds(116, 36, 86, 20);
		frmGestinDeEmpleados.getContentPane().add(txtNEmpleado);
		txtNEmpleado.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(116, 61, 153, 20);
		frmGestinDeEmpleados.getContentPane().add(txtApellido);
		txtApellido.setColumns(10);
		
		txtOficio = new JTextField();
		txtOficio.setBounds(116, 86, 153, 20);
		frmGestinDeEmpleados.getContentPane().add(txtOficio);
		txtOficio.setColumns(10);
		
		txtSalario = new JTextField();
		txtSalario.setBounds(116, 111, 86, 20);
		frmGestinDeEmpleados.getContentPane().add(txtSalario);
		txtSalario.setColumns(10);
		
		txtComision = new JTextField();
		txtComision.setBounds(116, 136, 86, 20);
		frmGestinDeEmpleados.getContentPane().add(txtComision);
		txtComision.setColumns(10);
		
		JComboBox CmbxDepartamento = new JComboBox();
		CmbxDepartamento.setToolTipText("Elige departamento");
		CmbxDepartamento.setBounds(279, 73, 145, 22);
		frmGestinDeEmpleados.getContentPane().add(CmbxDepartamento);
		
		JComboBox CmbxDirector = new JComboBox();
		CmbxDirector.setToolTipText("Elige director");
		CmbxDirector.setBounds(279, 106, 145, 22);
		frmGestinDeEmpleados.getContentPane().add(CmbxDirector);
		
		JLabel lblNewLabel_6 = new JLabel("FECHA ALTA:");
		lblNewLabel_6.setBounds(245, 139, 83, 14);
		frmGestinDeEmpleados.getContentPane().add(lblNewLabel_6);
		
		txtFecha = new JTextField();
		txtFecha.setBounds(326, 136, 86, 20);
		frmGestinDeEmpleados.getContentPane().add(txtFecha);
		txtFecha.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("(yyyy-MM-dd)");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblNewLabel_7.setBounds(336, 153, 58, 14);
		frmGestinDeEmpleados.getContentPane().add(lblNewLabel_7);
		
		JButton btnConsultar = new JButton("CONSULTAR EMPLEADO");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultar();
			}
		});
		btnConsultar.setBounds(245, 35, 179, 23);
		frmGestinDeEmpleados.getContentPane().add(btnConsultar);
		
		JButton btnInsertar = new JButton("INSERTAR");
		btnInsertar.setBounds(48, 183, 102, 23);
		frmGestinDeEmpleados.getContentPane().add(btnInsertar);
		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setBounds(170, 183, 102, 23);
		frmGestinDeEmpleados.getContentPane().add(btnEliminar);
		
		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.setBounds(289, 183, 102, 23);
		frmGestinDeEmpleados.getContentPane().add(btnModificar);
		
		JButton btnSalir = new JButton("SALIR");
		btnSalir.setBounds(115, 217, 102, 23);
		frmGestinDeEmpleados.getContentPane().add(btnSalir);
		
		JButton btnLimpiar = new JButton("LIMPIAR");
		btnLimpiar.setBounds(237, 217, 102, 23);
		frmGestinDeEmpleados.getContentPane().add(btnLimpiar);
		
		frmGestinDeEmpleados.setVisible(true);
	}
	
	public void consultar() {
		String numero = txtNEmpleado.getText();
		
		SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
		Session session = sesion.openSession();
		
		Empleados emp = new Empleados();
		emp = (Empleados) session.load(Empleados.class, Byte.valueOf(numero));
		
		
	
	}
	
}
