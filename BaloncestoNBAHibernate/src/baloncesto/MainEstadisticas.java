package baloncesto;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import primero.Estadisticas;
import primero.Jugadores;
import primero.SessionFactoryUtil;

public class MainEstadisticas {

	public static void main(String[] args) {
		SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
		Session session = sesion.openSession();
		
		Scanner s = new Scanner(System.in);
		System.out.print("Introduce el código del jugador:");
		int codigo = s.nextInt();
		s.close();
		
		//Consulta el jugador
		Jugadores jugador = (Jugadores) session.get(Jugadores.class, codigo);
		if(jugador == null) {
			System.out.println("Este jugador no existe.");
			System.exit(0);
		} else {
			String nombre = jugador.getNombre();
			String equipo = jugador.getEquipos().getNombre();
			
			System.out.println("\n\nDATOS DEL JUGADOR: " + codigo);
			System.out.println("Nombre : " + nombre);
			System.out.println("Equipo : " + equipo);
			System.out.printf("\n%-12s%-8s%-8s%-8s%-8s\n","Temporada","Ptos","Asis", "Tap", "Reb");
			System.out.println("============================================");
		}
		
		//Consulta estadisticas
		int registros = 0;
		Query q = session.createQuery("from Estadisticas as est where est.jugadores= :cod");
		q.setInteger("cod", codigo);
		List<Estadisticas> lista = q.list();
		Iterator it = lista.iterator();
		while(it.hasNext()) {
			Estadisticas est = (Estadisticas) it.next();
			System.out.printf("%-12s%-8s%-8s%-8s%-8s\n", "  " + est.getId().getTemporada(), est.getPuntosPorPartido(), 
					est.getAsistenciasPorPartido(), est.getTaponesPorPartido(), est.getRebotesPorPartido());
			registros++;
		}
		System.out.println("============================================");
		System.out.println("Número de registros : " + registros);
	}

}
