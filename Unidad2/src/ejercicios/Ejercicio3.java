package ejercicios;

import java.io.File;
import java.io.IOException;

public class Ejercicio3 {

	public static void main(String[] args) {
		boolean resultado;
		File directorio = new File("C:\\DAM2");
		File fichero = new File ("C:\\DAM2\\Natalia.txt");
		
		resultado = directorio.mkdir();
		if(resultado ) {
			System.out.println("Directorio creado.");
		} else {
			System.out.println("No se pudo crear el directorio.");
			//Posiblemente exista
			//System.exit(-1); //Terminamos
		}
		try {
			
			resultado = fichero.createNewFile();
			if (resultado) {
				System.out.println("Archivo creado.");
			} else {
				System.out.println("No se pudo crear el archivo.");
				//Posiblemente exista
			}
		} catch(IOException e) {
			System.out.println("Se produjo el error: " + e.getMessage());
		}
		
		resultado = fichero.delete();
		if(resultado) {
			System.out.println("Archivo borrado.");
		} else {
			System.out.println("No se pudo borrar el archivo.");
		}

		resultado = directorio.delete();
		if(resultado) {
			System.out.println("Directorio borrado.");
		} else {
			System.out.println("No se pudo borrar el directorio.");
		}
	}

}

/**
* Realiza una clase UD2_3 que complete la clase EjemploClaseFile02 de los materiales
* borrando el directorio y el fichero creados en ella (primero borra el fichero y después el
* directorio pues no se permite borrar directorios no vacíos).
*/