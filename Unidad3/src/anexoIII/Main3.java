package anexoIII;

import java.sql.*;
import java.util.Scanner;

public class Main3 {
	
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String database = "pedidos";
	private static String hostname = "localhost";
	private static String port = "3306";
	private static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?serverTimezone=Europe/Madrid";
	private static String username = "root";
	private static String password = "root";

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		int categoria;
		int porcentaje;
		int resultado;
		
		System.out.print("Introduce el número de categoría: ");
		categoria = s.nextInt();
		System.out.print("Introduce el número de porcentaje: ");
		porcentaje = s.nextInt();
		s.close();
		
		try {
			Class.forName(driver);
			Connection conecta = DriverManager.getConnection(url, username, password);
			
			String sql = "CALL incrementarPrecioCategoria(?, ?, ?);";
			CallableStatement consulta = conecta.prepareCall(sql);
			consulta.setInt(1, categoria);
			consulta.setInt(2, porcentaje);
			consulta.registerOutParameter(3, Types.INTEGER);
			consulta.execute();
			resultado = consulta.getInt(3);
			
			if(resultado == -1) {
				System.out.println("Ha habido un error.");
			}
			if(resultado == 0) {
				System.out.println("No se actualizó ningun producto de la categoría " + categoria);
			} else {
				System.out.println("Se incrementó un " + porcentaje + "% para " +
					resultado + " productos de la categoría " + categoria);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Hay un problema con la BD.");
			e.printStackTrace();
		}
	}
}
