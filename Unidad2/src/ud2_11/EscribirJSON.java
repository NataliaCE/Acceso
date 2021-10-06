package ud2_11;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import ud2_10.Pelicula;

public class EscribirJSON {

	public static void main(String[] args) {
		
		try {
			Gson gson = new Gson();
			String sFichero = new String(Files.readAllBytes(Paths.get("ficheros\\MisPeliculas.json")));
			List<Pelicula> lista = Arrays.asList(gson.fromJson(sFichero, Pelicula[].class));
			
			for(Pelicula p : lista) {
				System.out.println(p.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
