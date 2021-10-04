package ud2_10;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class EscribirPeliculasXML {

	public static void main(String[] args) {
		
		ListaPeliculas lista = new ListaPeliculas();
		
		lista.add(new Pelicula(1, "El señor de los anillos: La comunidad del anillo.", 2001, 
				"Ambientada en la Tierra Media, cuenta la histora de..."));
		lista.add(new Pelicula(2, "El señor de los anillos: Las dos torres", 2002, 
				"La trama de la pelicula comienza tras la disolución de la comunidad del anillo"));
		lista.add(new Pelicula(3, "El señor de los anillo: El retorno del rey", 2003, 
				"Trata sobre la última parte del viaje de que emprendieros los protagonistas."));
		
		try {
			XStream xstream = new XStream(new DomDriver("UTF-8"));
			
			//Nombra los nodos. Se introduce el nombre del nodo y la clase con la que se crea.
			xstream.alias("MisPeliculas", ListaPeliculas.class);
			xstream.alias("Pelicula", Pelicula.class);
			
			xstream.addImplicitCollection(ListaPeliculas.class, "lista");
			xstream.toXML(lista, new FileOutputStream("ficheros\\MisPeliculas.xml"));
			System.out.println("El fichero se está creando.");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}

}

/*Crea un paquete de nombre UD2_10 e incluye en él todas las clases necesarias para
construir el siguiente documento XML. Incluye otra clase que recorra el documento XML creado
y visualice sus datos por la consola.
*/