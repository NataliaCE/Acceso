package anexoIII;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main1 {
	
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String database = "pedidos";
	private static String hostname = "localhost";
	private static String port = "3306";
	private static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?serverTimezone=Europe/Madrid";
	private static String username = "root";
	private static String password = "root";

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		int pedido;
		double importe = 0.0;
		
		System.out.print("Introduce el número de pedido: ");
		pedido = s.nextInt();
		s.close();
		
		try {
			Class.forName(driver);
			Connection conecta = DriverManager.getConnection(url, username, password);
			
			String sql = "SELECT importePedido(?);";
			CallableStatement consulta = conecta.prepareCall(sql);
			consulta.setInt(1, pedido);
			ResultSet rs = consulta.executeQuery();
			if(rs.next()) {
				importe = rs.getDouble(1);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error con la BD.");
			e.printStackTrace();
		}
		
		if(importe != -1) {
			System.out.println("El total del pedido " + pedido + " es " + importe);
		} else {
			System.out.println("El pedido " + pedido + " no existe.");
		}
		
	}

}
