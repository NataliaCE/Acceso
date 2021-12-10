package baloncesto;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import primero.Equipos;
import primero.Jugadores;
import primero.SessionFactoryUtil;

public class EquiposJugadores {

	public static void main(String[] args) {
		DecimalFormat df = new DecimalFormat("#.00");
		
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
			System.out.println("EQUIPO : " + equipo.getNombre() + "\n");
			
			Query qu = session.createQuery("select j.codigo, j.nombre, avg(e.puntosPorPartido) "
					+ "from Equipos as eq join eq.jugadoreses as j join j.estadisticases as e where eq.nombre = :eqNom "
					+ "group by j.nombre").setString("eqNom", equipo.getNombre());
			List<Object> lista = qu.list();
			for(int i = 0; i < lista.size(); i++) {
				Object[] fila = (Object[]) lista.get(i);
				System.out.println(fila[0] + ", " + fila[1] +" : " + df.format(fila[2]));
			}
			System.out.println("\n==================================\n");
			
			/*Query qJugador = session.createQuery("from Jugadores "
					+ "where equipos.nombre = :eNom").setString("eNom", equipo.getNombre());
			List<Jugadores> listaJugadores = qJugador.list();
			for(Jugadores j : listaJugadores) {
				try {
					double media = (double) session.createQuery("select avg(puntosPorPartido) "
							+ "from Estadisticas where jugadores.nombre = :jNom)").setString("jNom", j.getNombre()).uniqueResult();
					//System.out.println(j.getCodigo() + " - " + j.getNombre() + " - " + df.format(media));
					System.out.printf("%-4s%-20s%-6s\n", String.valueOf(j.getCodigo()), j.getNombre(), df.format(media));
				} catch(NullPointerException e) {
					//System.out.println(j.getCodigo() + " - " + j.getNombre() + " - Sin datos");
					System.out.printf("%-4s%-20s%-6s\n", String.valueOf(j.getCodigo()), j.getNombre(), "Sin datos");
				}
			}
			System.out.println("\n==================================\n");*/
			
		}
		session.close();
		System.exit(0);
	}

}
