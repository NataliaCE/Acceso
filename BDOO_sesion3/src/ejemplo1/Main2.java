package ejemplo1;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;

import javax.crypto.AEADBadTagException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main2 {

	public static void main(String[] args) throws ParseException {
        
		AccesoBdatos abd = new AccesoBdatos();
		abd.conectar();
	//	abd.imprimirDepartamento(90);
	//	abd.imprimirDepartamento(40);
	//	abd.imprimirDepartamento(10);
		
    //  System.out.println(abd.insertarDepartamento(new DepartamentoEntity(60,"Recursos Humanos", "Chimillas")));
    //  System.out.println(abd.insertarDepartamento(new DepartamentoEntity(60,"Recursos Humanos", "Chimillas")));
    //  abd.imprimirDepartamento(60);
		
	//	System.out.println(abd.modificarDepartamento(new DepartamentoEntity(88,"RRHH", "Huerrios")));
	//	System.out.println(abd.modificarDepartamento(new DepartamentoEntity(60,"RRHH", "Esquedas")));
	//	abd.imprimirDepartamento(60);
		
	//	System.out.println(abd.borrarDepartamento(88));; // false no existe
	//	System.out.println(abd.borrarDepartamento(60));; // true
	//	System.out.println(abd.borrarDepartamento(10));; // false pues tiene empleados
		
	//	abd.imprimirDepartamento(10);
	
	//	abd.demoJPQL();
		
	//	abd.ejercicio8_1();
	//	abd.ejercicio8_2();
	//	abd.ejercicio8_3();
	//	abd.ejercicio8_4();
	//	abd.ejercicio8_5();
	//	abd.ejercicio8_6();
	//	abd.ejercicio8_7();
		abd.ejercicio8_8();
	//	abd.ejercicio8_9();
	//	abd.ejercicio8_10();
	//	abd.ejercicio8_11();
	//	abd.ejercicio8_12();
	//	abd.ejercicio8_13();
		
		System.out.println("-------------------------------------------");
		//System.out.println(abd.borrarEmpleado(2088));
		
		abd.desconectar();
	

	}

}

