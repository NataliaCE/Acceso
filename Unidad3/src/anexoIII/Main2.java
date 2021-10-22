package anexoIII;

import java.sql.*;
import java.util.Scanner;

public class Main2 {

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
		String nombre;
		Double precio;
		int existencias;
		int minimo;
		
		System.out.print("Introduce el número de categoría: ");
		categoria = s.nextInt();
		System.out.println("Producto  -  Precio - Existencias - Minimo");
		System.out.println("-------------------------------------------");
		
		try {
			Class.forName(driver);
			Connection conecta = DriverManager.getConnection(url, username, password);
			
			String sql = "CALL productosCategoriaBajoMinimos(?);";
			CallableStatement consulta = conecta.prepareCall(sql);
			consulta.setInt(1, categoria);
			ResultSet rs = consulta.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString(1)+" | "+rs.getDouble(2)+" | "+rs.getInt(3)+" | "+rs.getInt(4));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Hay un problema con la BD.");
			e.printStackTrace();
		}
		
	}

}
