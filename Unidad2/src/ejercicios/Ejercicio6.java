package ejercicios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio6 {

	public static void main(String[] args) {
		String palabra;
		String linea;
		int contador = 0;
		int pos = 0;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(args[0]));
			palabra = args[1].toLowerCase();
			linea = br.readLine();
			
			while(linea != null) {
				linea = linea.toLowerCase();
				pos = linea.indexOf(palabra);
				
				//Busco la palabra, si está la quito, y vuelvo a buscar. Si ya no quedan, salgo del while
				while(pos != -1) {
					contador++;
					linea = linea.substring(pos + palabra.length());
					pos = linea.indexOf(palabra);
				}
				linea = br.readLine();
				
			}
			br.close();
			System.out.println("La palabra se repite " + contador + " veces.");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
/**
* Realiza una clase UD2_6 que indique cuántas veces aparece una palabra dentro de un
* fichero de texto (puedes crearlo con el bloc de notas). Tanto el nombre del fichero como la
* palabra se deben pasar como argumentos. No distinguir mayúsculas/minúsculas. Incluye
* también tratamiento de excepciones.
*/