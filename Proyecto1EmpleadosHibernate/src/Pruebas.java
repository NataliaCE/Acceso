import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import primero.Departamentos;
import primero.Empleados;
import primero.SessionFactoryUtil;

public class Pruebas {

	public static void main(String[] args) {
		
		SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
		Session session = sesion.openSession();
		
		Query q = session.createQuery("from Departamentos");
		List <Departamentos> lista = q.list();
		Iterator <Departamentos> it = lista.iterator();
		while(it.hasNext()) {
			Departamentos dep = (Departamentos) it.next();
			System.out.println(dep.getDeptNo() + dep.getDnombre());
		}
	}

}
