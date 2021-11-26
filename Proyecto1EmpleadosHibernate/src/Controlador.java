import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import primero.Departamentos;
import primero.Empleados;
import primero.SessionFactoryUtil;

public class Controlador implements ActionListener{
	
	private View vista;
	private SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
	
	
	Controlador(View vista) {
		this.vista = vista;
		rellenarDepartamentos();
		rellenarDirectores();
		this.vista.txtFecha.setText(ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String comando = arg0.getActionCommand();
		
		switch(comando) {
			case "CONSULTAR":
				consultar();
				break;
			
			case "INSERTAR":
				insertar();
				break;
		}
		
	}
	
	public void rellenarDepartamentos() {
		Session session = sesion.openSession();
		
		Query q = session.createQuery("from Departamentos");
		List <Departamentos> lista = q.list();
		Iterator <Departamentos> it = lista.iterator();
		while(it.hasNext()) {
			Departamentos dep = (Departamentos) it.next();
			this.vista.CmbxDepartamento.addItem(String.valueOf(dep.getDeptNo()) + " / " + dep.getDnombre());
		}
	}
	
	public void rellenarDirectores() {
		Session session = sesion.openSession();
		
		Query q = session.createQuery("from Empleados");
		List<Empleados> lista = q.list();
		Iterator<Empleados> it = lista.iterator();
		while(it.hasNext()) {
			Empleados emp = (Empleados) it.next();
			this.vista.CmbxDirector.addItem(String.valueOf(emp.getEmpNo()) + " / " + emp.getApellido());
		}
	}
	
	public void rellenarFecha() {
		
	}
	
	public void consultar() {
		String numero = this.vista.txtNEmpleado.getText();
		Empleados emp = new Empleados();
		
		try {
			Session session = sesion.openSession();
			emp = (Empleados) session.load(Empleados.class, Short.valueOf(numero));
			
			this.vista.txtApellido.setText(emp.getApellido());
			this.vista.txtOficio.setText(emp.getOficio());
			this.vista.txtSalario.setText(String.valueOf(emp.getSalario())+"€");
			if(emp.getComision() == null) {
				this.vista.txtComision.setText("Sin comisión");
			} else {
				this.vista.txtComision.setText(String.valueOf(emp.getComision()));
			}
			this.vista.txtFecha.setText(String.valueOf(emp.getFechaAlt()));
			
			Departamentos dep = emp.getDepartamentos();
			this.vista.CmbxDepartamento.setSelectedItem(String.valueOf(dep.getDeptNo()) + " / " + dep.getDnombre());
			
			for(int i = 0; i < this.vista.CmbxDirector.getItemCount(); i++) {
				String item = this.vista.CmbxDirector.getItemAt(i);
				if(item.startsWith(String.valueOf(emp.getDir()))) {
					this.vista.CmbxDirector.setSelectedIndex(i);
					break;
				}
			}
			
		} catch(ObjectNotFoundException e) {
			JOptionPane.showMessageDialog(null, "No existe este empleado", "Aviso", JOptionPane.WARNING_MESSAGE);
		}
	
	}
	
	public void insertar() {
		String numero = this.vista.txtNEmpleado.getText();
		String apellido = this.vista.txtApellido.getText();
		String oficio = this.vista.txtOficio.getText();
		String salario = this.vista.txtSalario.getText();
		String comision = this.vista.txtComision.getText();
		String fechaString = this.vista.txtFecha.getText();
		String dep = this.vista.CmbxDepartamento.getSelectedItem().toString();
		String dir = this.vista.CmbxDirector.getSelectedItem().toString();
		try {
			Date fecha = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(fechaString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
