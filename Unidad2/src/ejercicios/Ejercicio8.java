package ejercicios;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Ejercicio8 {

	public static void main(String[] args) {
		String nombre;
		int antiguedad;
		String continua = "s";
		Scanner s = new Scanner(System.in);
		
		File archivo = new File("ficheros\\antiguedad_obj.dat");
		try {
			if(!archivo.exists()) {
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo));
				System.out.print("Profesor: ");
				nombre = s.next();
				System.out.print("Antiguedad: ");
				antiguedad = s.nextInt();
				oos.writeObject(new Profesor(nombre, antiguedad));
				oos.close();
			}
			
			System.out.print("¿Quieres continuar? s/n: ");
			continua = s.next();
			MiObjectOutputStream moos = new MiObjectOutputStream(new FileOutputStream(archivo, true));
			
			while(continua.equals("s")) {
				System.out.print("Profesor: ");
				nombre = s.next();
				System.out.print("Antiguedad: ");
				antiguedad = s.nextInt();
				
				moos.writeObject(new Profesor(nombre, antiguedad));
				
				System.out.print("¿Quieres continuar? s/n: ");
				continua = s.next();
			}
			moos.close();
			s.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
			
			try {
				while(true) {
					System.out.println(ois.readObject());
				}
				
			} catch(EOFException f) {
				System.out.println("---Fin del fichero---");
				
			} catch (ClassNotFoundException c) {
				c.printStackTrace();
			}
			ois.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
	}

}

/**
* Adaptación de los ejemplos vistos en los materiales con las clases ObjectInputStream y
* ObjectOuputStream. Realiza una clase UD2_8 que pida al usuario datos de varios profesores (nombre 
* y la antigüedad) y los inserte en el fichero antiguedad.dat_obj.dat. Si el fichero no
* existe se creará con los nuevos datos introducidos, en caso contrario se añadirán por el final.
* Antes de finalizar el código se recorrerá el fichero para visualizar su contenido. Prueba varias
* veces la ejecución de la clase.
*/