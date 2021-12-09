package baloncesto;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import primero.Equipos;
import primero.SessionFactoryUtil;

public class EquiposJugadores {

	public static void main(String[] args) {
		SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
		Session session = sesion.openSession();
		
		Query q = session.createQuery("from Equipos");
		List<Equipos> listaEquipos = q.list();
		int cantidad = listaEquipos.size();
		
		System.out.println("\nNúmero de equipos : " + cantidad);
		System.out.println("=================================");
		
		Iterator it = listaEquipos.iterator();
		while(it.hasNext()) {
			Equipos equipo = (Equipos) it.next();
			System.out.println("Nombre : " + equipo.getNombre());
			
			Query qu = session.createQuery("select j.codigo, j.nombre, avg(e.puntosPorPartido) "
					+ "from Jugadores as j join j.estadisticases as e group by j.nombre");
			List<Object> lista = qu.list();
			Iterator ite = lista.iterator();
			while(it.hasNext()) {
				Object[] fila = (Object[]) ite.next();
				System.out.println(fila[0] + ", " + fila[1] +" :    " + fila[2]);
			}
		}
	}

}
