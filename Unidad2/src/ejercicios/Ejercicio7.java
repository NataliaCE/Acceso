package ejercicios;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ejercicio7 {

	public static void main(String[] args) {
		
		
		try {
			DataOutputStream dos = new DataOutputStream(new FileOutputStream("ficheros\\antiguedad.dat"));
			for(int i = 0; i < 20; i++) {
				dos.writeInt((int) (Math.random() * 5 + 1));
			}
			dos.close();
			
			DataInputStream dis = new DataInputStream(new FileInputStream("ficheros\\antiguedad.dat"));
			int numero;
			int uno = 0;
			int two = 0;
			int tres = 0;
			int cuatro = 0;
			int cinco = 0;
			System.out.println("NUMEROS:");
			for(int i = 0; i < 20; i++) {
				numero = dis.readInt();
				switch(numero) {
					case 1: 
						uno++;
						System.out.println(uno);
						break;
					case 2:
						two++;
						System.out.println(two);
						break;
					case 3: 
						tres++;
						System.out.println(tres);
						break;
					case 4: 
						cuatro++;
						System.out.println(cuatro);
						break;
					case 5: 
						cinco++;
						System.out.println(cinco);
						break;
				}
			}
			dis.close();
			System.out.println("--------------");
			System.out.println("Hay " + uno + " unos.");
			System.out.println("Hay " + two + " doses.");
			System.out.println("Hay " + tres + " treses.");
			System.out.println("Hay " + cuatro + " cuatros.");
			System.out.println("Hay " + cinco + " cincos.");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
/*
* Realiza una clase UD2_7 que guarde 20 números enteros aleatorios comprendidos entre
* 1 y 5 en el fichero puntuación.dat. Completa el código abriendo el fichero para visualizarlos
* todos por la consola indicando al final cuántas veces se repiten cada uno de ellos. Incluye
* también tratamiento de excepciones.
*/