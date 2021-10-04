package ud2_10;

import com.thoughtworks.xstream.XStream;

public class LecturaPeliculasXML {

	public static void main(String[] args) {
		
		XStream xstream = new XStream();
		xstream.alias("MisPeliculas", ListaPeliculas.class);
		xstream.alias("Pelicula", Pelicula.class);
		xstream.addImplicitCollection(ListaPeliculas.class, "lista");

	}

}
