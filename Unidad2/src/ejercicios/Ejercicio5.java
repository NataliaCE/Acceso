package ejercicios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Ejercicio5 {

	public static void main(String[] args) {
		try {
			int l = args[0].length();
			String nombre = args[0].substring(0, l - 4);
			String extension = args[0].substring(nombre.length());
			BufferedWriter bw = new BufferedWriter(new FileWriter(nombre + "_sort" + extension));
			
			BufferedReader br = new BufferedReader(new FileReader(args[0]));
			ArrayList<String> lista = new ArrayList<String>();
			String cadena;
			while((cadena = br.readLine()) != null) {
				lista.add(cadena);
			}
			br.close();
			

			Collections.sort(lista);
			
			for(String s : lista) {
				bw.write(s + "\n");
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

/**
* Realiza una clase UD2_5 que sea capaz de ordenar alfabéticamente las líneas contenidas
* en un fichero de texto (puedes crearlo con el bloc de notas). El nombre del fichero que contiene
* las líneas se debe pasar como argumento. El nombre del fichero resultado ya ordenado debe ser
* el mismo que el original añadiéndole la coletilla _sort al nombre. Incluye también tratamiento
* de excepciones.
*/