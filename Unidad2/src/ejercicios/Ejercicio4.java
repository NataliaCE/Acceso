package ejercicios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio4 {

	public static void main(String[] args) {
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("ficheros\\pares.txt"));
			int i = 0;
			bw.write(Integer.toString(i));
			while(i < 500) {
				i += 2;
				bw.newLine();
				bw.write(Integer.toString(i));
				
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("ficheros\\pares.txt"));
			String cadena = br.readLine();
			while(cadena != null) {
				System.out.println(cadena);
				cadena = br.readLine();
			}
			br.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

/**
* Realiza una clase UD2_4 que guarde en un fichero con nombre pares.txt los números
* pares que hay entre 0 y 500, un número en cada línea del fichero. Seguidamente lee el fichero y
* muéstralo por la consola. Incluye también tratamiento de excepciones.
*/