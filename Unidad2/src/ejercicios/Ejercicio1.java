package ejercicios;

import java.io.File;

public class Ejercicio1 {

	public static void main(String[] args) {
		File f = new File("C:\\Windows");
		File[] archivos = f.listFiles();
		for(File archivo : archivos) {
			if(archivo.isFile() && archivo.isHidden()) {
				System.out.println("Nombre: " + archivo.getName());
				System.out.println("Longitd: " + archivo.length());
				System.out.println("Se puede leer: " + archivo.canRead());
				System.out.println("Se puede escribir: " + archivo.canWrite());
			}
		}

	}

}
