package anexoII;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {

	public static void main(String[] args) {
		AccesoBDatos abd = new AccesoBDatos();
		try {
			abd.conectar();
			//C
			System.out.println(abd.busquedaPorCodigo(100));
			System.out.println (abd.busquedaPorCodigo(7788));
			System.out.println("\n-----------------------------------\n");
			
			//D
			System.out.println(abd.busquedaPorOficio("Profesor"));
			System.out.println(abd.busquedaPorOficio("CLERK"));
			System.out.println("\n-----------------------------------\n");
			
			//E
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			java.util.Date fecha = sdf.parse("1981/02/22");
			java.sql.Date sqlFecha = new java.sql.Date(fecha.getTime());
			System.out.println(abd.busquedaPorAntiguedad(sqlFecha));
			System.out.println("\n-----------------------------------\n");
			
			//F
			fecha = sdf.parse("2020/03/18");
			sqlFecha = new java.sql.Date(fecha.getTime());
			Empleado e1 = new Empleado(1, "CARRERA", "PROFESOR", 7788, sqlFecha, 700, 0, 20);
			System.out.println(abd.insertarEmpleado(e1));
			System.out.println("\n-----------------------------------\n");
			
			//I
			System.out.println(abd.actualizarSalario(30, 0.2));
			System.out.println(abd.actualizarSalario(44, 0.15));
			System.out.println("\n-----------------------------------\n");
			
			//J
			System.out.println(abd.borrarEmpleado(1));
			System.out.println(abd.borrarEmpleado(99));
			System.out.println(abd.borrarEmpleado(7839));
			System.out.println("\n-----------------------------------\n");
			
			//I con Transacciones
			System.out.println(abd.actualizarSalarioConTransacciones(30, 0.2));
			
		} catch (ClassNotFoundException | SQLException | ParseException e) {
			e.printStackTrace();
		}

	}

}
