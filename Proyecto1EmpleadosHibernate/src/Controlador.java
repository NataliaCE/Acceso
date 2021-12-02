import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransientPropertyValueException;
import org.hibernate.exception.ConstraintViolationException;

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
			case "ELIMINAR":
				eliminar();
				break;
			case "SALIR":
				salir();
				break;
			case "LIMPIAR":
				limpiar();
				break;
			case "MODIFICAR":
				modificar();
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
		session.close();
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
		session.close();
	}
	
	public void consultar() {
		String numero = this.vista.txtNEmpleado.getText();
		Empleados emp = new Empleados();
		Session session = sesion.openSession();
		
		try {
			emp = (Empleados) session.load(Empleados.class, Short.valueOf(numero));
			
			this.vista.txtApellido.setText(emp.getApellido());
			this.vista.txtOficio.setText(emp.getOficio());
			this.vista.txtSalario.setText(String.valueOf(emp.getSalario()));
			if(emp.getComision() != null) {
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
		session.close();
	
	}
	
	public boolean insertar() {
		//Recoger datos
		String numero = this.vista.txtNEmpleado.getText();
		String apellido = this.vista.txtApellido.getText();
		String oficio = this.vista.txtOficio.getText();
		String salario = this.vista.txtSalario.getText();
		String comision = this.vista.txtComision.getText();
		String fechaString = this.vista.txtFecha.getText();
		String depString = this.vista.CmbxDepartamento.getSelectedItem().toString();
		String dirString = this.vista.CmbxDirector.getSelectedItem().toString();
		//Validación
		if(numero.isEmpty() || apellido.isEmpty() || oficio.isEmpty() || salario.isEmpty() ||
				fechaString.isEmpty() || depString.isEmpty() || dirString.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Menos la comisión, es obligatorio completar todos los datos.",
					"Aviso", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		//Conversiones
		java.sql.Date fecha;
		try {
			java.util.Date fechaJava = new SimpleDateFormat("yyyy-MM-dd").parse(fechaString);
			fecha = new java.sql.Date(fechaJava.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		Short dir = Short.parseShort(dirString.substring(0, 4));
		Byte depNum = Byte.parseByte(depString.substring(0, 2));
		
		//Introducir datos
		Departamentos dep = new Departamentos(depNum);
		Empleados emp = new Empleados();
		emp.setEmpNo(Short.parseShort(numero));
		emp.setApellido(apellido);
		emp.setOficio(oficio);
		emp.setSalario(Float.parseFloat(salario));
		emp.setFechaAlt(fecha);
		emp.setDir(dir);
		emp.setDepartamentos(dep);
		if(!comision.isEmpty()) {
			emp.setComision(Float.parseFloat(comision));
		}
		
		//Insertar
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.save(emp);
			try {
				tx.commit();
				
			} catch(ConstraintViolationException e) {
				//java.sql.SQLIntegrityConstraintViolationException
				System.out.println("EMPLEADO DUPLICADO");
				System.out.printf("MENSAJE: %s%n", e.getMessage());
				System.out.printf("COD ERROR: %d%n", e.getErrorCode());
				System.out.printf("ERROR SQL: %s%n", e.getSQLException().getMessage());
				JOptionPane.showMessageDialog(null, "Este número de empleado ya existe", "Aviso", JOptionPane.WARNING_MESSAGE);
				session.close();
				return false;}
		} catch (TransientPropertyValueException e) {
			System.out.println("EL DEPARTAMENTO NO EXISTE");
			System.out.printf("MENSAJE: %s%n", e.getMessage());
			System.out.printf("Propiedad: %s%n", e.getPropertyName());
			JOptionPane.showMessageDialog(null, "Este departamento no existe", "Aviso", JOptionPane.WARNING_MESSAGE);
			session.close();
			return false;
		} catch (Exception e) {
			System.out.println("ERROR NO CONTROLADO....");
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error.", "Aviso", JOptionPane.WARNING_MESSAGE);
			session.close();
			return false;
		}
		session.close();
		JOptionPane.showMessageDialog(null, "Empleado insertado", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
		rellenarDirectores();
		return true;
	}
	
	public void eliminar() {
		Short numero = Short.parseShort(this.vista.txtNEmpleado.getText());
		Boolean esDirector = false;
		//Comprobar si es director
		Session session = sesion.openSession();
		ArrayList<Empleados> director = (ArrayList<Empleados>) session.createQuery("from Empleados as emp "
				+ "where emp.dir= :dir").setShort("dir", numero).list();
		if(director != null) {
			esDirector = true;
			JOptionPane.showMessageDialog(null, "Este empleado es un director", "Aviso", JOptionPane.WARNING_MESSAGE);
		}
		if(!esDirector) {
			Empleados empleado = (Empleados) session.get(Empleados.class, numero);
			if(empleado == null) {
				JOptionPane.showMessageDialog(null, "Este empleado no existe.", "Aviso", JOptionPane.WARNING_MESSAGE);
			} else {
				Transaction tx = session.beginTransaction();
				session.delete(empleado);
				tx.commit();
				JOptionPane.showMessageDialog(null, "Empleado borrado", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
				rellenarDirectores();
				
			}
		}
		session.close();
	}

	public void modificar() {
		String numero = this.vista.txtNEmpleado.getText();
		String apellido = this.vista.txtApellido.getText();
		String oficio = this.vista.txtOficio.getText();
		String salario = this.vista.txtSalario.getText();
		String comision = this.vista.txtComision.getText();
		String fechaString = this.vista.txtFecha.getText();
		String depString = this.vista.CmbxDepartamento.getSelectedItem().toString();
		String dirString = this.vista.CmbxDirector.getSelectedItem().toString();
		//Conversiones
		java.sql.Date fecha = null;
		try {
			java.util.Date fechaJava = new SimpleDateFormat("yyyy-MM-dd").parse(fechaString);
			fecha = new java.sql.Date(fechaJava.getTime());
		} catch (ParseException e) {
			System.out.println("Error al convertir la fecha.");
			e.printStackTrace();
		}
		Short dir = Short.parseShort(dirString.substring(0, 4));
		Byte depNum = Byte.parseByte(depString.substring(0, 2));
		//Validación
		if(numero.isEmpty() || apellido.isEmpty() || oficio.isEmpty() || salario.isEmpty() ||
				fechaString.isEmpty() || depString.isEmpty() || dirString.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Menos la comisión, es obligatorio completar todos los datos.",
					"Aviso", JOptionPane.WARNING_MESSAGE);
		} else {
			//Modificación
			Session session = sesion.openSession();
			Transaction tx = session.beginTransaction();
			Empleados emp = new Empleados();
			Departamentos dep = new Departamentos(depNum);
			try {
				emp = (Empleados) session.get(Empleados.class, Short.parseShort(numero));
				emp.setApellido(apellido);
				emp.setOficio(oficio);
				emp.setSalario(Float.parseFloat(salario));
				emp.setFechaAlt(fecha);
				emp.setDir(dir);
				emp.setDepartamentos(dep);
				if(!comision.equals("")) {
					emp.setComision(Float.parseFloat(comision));
				}
				session.update(emp);
				tx.commit();
				JOptionPane.showMessageDialog(null, "Empleado modificado.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
				rellenarDirectores();
				
			} catch(ObjectNotFoundException o) {
			System.out.println("NO EXISTE EL EMPLEADO...");
			}
			session.close();
		}
		
	}
	
	public void salir() {
		System.exit(0);
	}
	
	public void limpiar() {
		this.vista.txtNEmpleado.setText("");
		this.vista.txtApellido.setText("");
		this.vista.txtOficio.setText("");
		this.vista.txtSalario.setText("");
		this.vista.txtComision.setText("");
		this.vista.txtFecha.setText(ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
		this.vista.CmbxDepartamento.setSelectedIndex(0);
		this.vista.CmbxDirector.setSelectedIndex(0);
	}
}
