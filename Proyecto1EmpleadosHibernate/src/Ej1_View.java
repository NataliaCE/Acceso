import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import primero.Departamentos;
import primero.Empleados;
import primero.SessionFactoryUtil;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;

public class Ej1_View {


	private JFrame frame;
	JTextField txtNEmpleado;
	JTextField txtApellido;
	JTextField txtOficio;
	JTextField txtSalario;
	JTextField txtComision;
	JTextField txtFecha;
	JComboBox<String> CmbxDepartamento;
	JComboBox<String> CmbxDirector;
	SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
	
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
		
		CmbxDepartamento = new JComboBox();
		CmbxDepartamento.setToolTipText("Elige departamento");
		CmbxDepartamento.setBounds(279, 73, 145, 22);
		rellenarDepartamentos();
		frmGestinDeEmpleados.getContentPane().add(CmbxDepartamento);
		
		CmbxDirector = new JComboBox();
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
	public void rellenarDepartamentos() {
		SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
		Session session = sesion.openSession();
		
		Query q = session.createQuery("from Departamentos");
		List <Departamentos> lista = q.list();
		Iterator <Departamentos> it = lista.iterator();
		while(it.hasNext()) {
			Departamentos dep = (Departamentos) it.next();
			CmbxDepartamento.addItem(String.valueOf(dep.getDeptNo()) + "/" + dep.getDnombre());
		}
	}
	
	public void rellenarDirectores() {
		
	}
	
	public void consultar() {
		String numero = txtNEmpleado.getText();
		Empleados emp = new Empleados();
		
		try {
			Session session = sesion.openSession();
			emp = (Empleados) session.load(Empleados.class, Short.valueOf(numero));
			
			txtApellido.setText(emp.getApellido());
			txtOficio.setText(emp.getOficio());
			txtSalario.setText(String.valueOf(emp.getSalario())+"€");
			if(emp.getComision() == null) {
				txtComision.setText("Sin comisión");
			} else {
				txtComision.setText(String.valueOf(emp.getComision()));
			}
			txtFecha.setText(String.valueOf(emp.getFechaAlt()));
			
		} catch(ObjectNotFoundException e) {
			JOptionPane.showMessageDialog(null, "No existe este empleado", "Aviso", JOptionPane.WARNING_MESSAGE);
		}
	
	}
	
}
