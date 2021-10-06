package ud2_11;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import ud2_10.Pelicula;

public class CrearJSON {

	public static void main(String[] args) {
		
		List<Pelicula> lista = Arrays.asList(
				new Pelicula(1, "El se�or de los anillos: La comunidad del anillo.", 2001, 
						"Ambientada en la Tierra Media, cuenta la histora de..."),
				new Pelicula(2, "El se�or de los anillos: Las dos torres", 2002, 
						"La trama de la pelicula comienza tras la disoluci�n de la comunidad del anillo"),
				new Pelicula(3, "El se�or de los anillo: El retorno del rey", 2003, 
						"Trata sobre la �ltima parte del viaje de que emprendieros los protagonistas."));
		
		try {
			FileWriter fw = new FileWriter("ficheros\\MisPeliculas.json");
			
			new Gson().toJson(lista, fw);
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

/*Crea un paquete de nombre UD2_11 e incluye en �l todas las clases necesarias para
crear en disco un fichero JSON con la informaci�n que aparece en el ejercicio anterior. Incluye
tambi�n las dos clases que recorren el documento JSON creado que se han explicado en los
materiales.
*/