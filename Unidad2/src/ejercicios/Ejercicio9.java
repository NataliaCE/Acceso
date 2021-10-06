package ejercicios;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

public class Ejercicio9 {

	public static void main(String[] args) throws IOException {
		
		Scanner s = new Scanner(System.in);
		File fichero = new File("ficheros\\ProfesFPSierraGuara.dat");
		int id;
		int posicion;
		int lectura;
		
		/*if(!fichero.exists()) {
			crearFichero(fichero);
		}*/
		crearFichero(fichero);
		
		System.out.print("Introduce el ID: ");
		id = s.nextInt();
		posicion = (id-1)*56;
		s.close();
		
		RandomAccessFile raf = new RandomAccessFile(fichero, "rw");
		raf.seek(posicion);
		int idPos = raf.readInt();
		
		System.out.println(idPos);
		
		
		/*try {
			RandomAccessFile raf = new RandomAccessFile(fichero, "rw");
			raf.seek(posicion);
			
			if(raf.length() < posicion) {
				System.out.println("Este fichero no es tan largo.");
				System.exit(-1);
			}
			
			lectura = raf.readInt();
			if(lectura == 0) {
				System.out.println("Este profesor ya ha sido borrado.");
				System.out.println(lectura);
				System.exit(-1);
			}
			if(lectura == id) {
				raf.seek(id);
				raf.writeInt(0);
				
			} else {
				System.out.println("El n�mero introducido no es un ID.");
				System.exit(-1);
			}
			raf.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}*/

	}
	
	public static void crearFichero(File f) {
		String profesores[] = {"Alberto Carrera", "Ana Ereza", "Antonio Lasierra", "Pura Plo", "Bel�n Carrera"};
		int departamentos[] = {10, 20, 20, 30, 40};
		Double antiguedad[] = {29.5, 18.0, 38.5, 28.0, 22.0};
		StringBuffer sb = null;
		int total = profesores.length;
		
		try {
			RandomAccessFile raf = new RandomAccessFile(f, "rw");
			
			for(int i = 0; i < total; i++) {
				raf.writeInt(i + 1);
				sb = new StringBuffer(profesores[i]);
				sb.setLength(20);
				raf.writeChars(sb.toString());
				raf.writeInt(departamentos[i]);
				raf.writeDouble(antiguedad[i]);
			}
			raf.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("----Error al crear el archivo.----");
		}
		
	}
	

}

/**
Adaptaci�n de los ejemplos vistos en los materiales con la clase RandomAccessFile.
Realiza una clase UD2_9 que pida al usuario el identificador del profesor y lo borre del fichero
ProfesFPSierraGuara.dat. Borrar un dato simplemente consiste en poner su campo id dentro del
fichero a 0 para indicar que ese registro no existe y su posici�n est� libre. Se deber� controlar
que:
- El identificador del profesor est� dentro de los l�mites del fichero.
- El identificador del profesor debe existir. Si ha sido borrado previamente se advertir� de
la situaci�n.
- Antes de finalizar el c�digo visualizar de manera secuencial todos los registros del
fichero para comprobar la operaci�n.
*/