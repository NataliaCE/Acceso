package ejercicios;

import java.io.File;

public class Ejercicio2 {

	public static void main(String[] args) {
			
		if(args.length != 0) {
			File f = new File(args[0]);
			if(f.exists()) {
				System.out.println("El archivo existe.");
			} else {
				System.out.println("El archivo no existe.");
			}
		} else {
			System.out.println("No se ha introducido nada.");
		}

	}

}
