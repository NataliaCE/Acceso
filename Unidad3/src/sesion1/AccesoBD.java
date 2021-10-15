//EJERCICIO 2

package sesion1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccesoBD {

	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String database = "sample";
	private static String hostname = "localhost";
	private static String port = "3306";
	private static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?serverTimezone=Europe/Madrid";
	private static String username = "root";
	private static String password = "root";
	
	public Connection conecta;
	
	public void conectar() throws SQLException, ClassNotFoundException {
		Class.forName(driver);
		conecta = DriverManager.getConnection(url, username, password);
	}
	
	public boolean usuarioExiste(String usuario) {
		boolean existe = false;
		try {
			Statement consulta = conecta.createStatement();
			ResultSet reg = consulta.executeQuery("SELECT * FROM usuaro WHERE username = '" + usuario + "'");
			if(reg.next()) existe = true;
			
		} catch (SQLException e) {
			System.out.println("Problema al conectar con la BD");
			e.printStackTrace();
		}
		return existe;
	}
	
	public String verificacion(String usuario, String contrasenya) {
		String nombre = null;
		try {
			Statement consulta = conecta.createStatement();
			ResultSet reg = consulta.executeQuery("SELECT * FROM usuario WHERE username = '" + usuario + 
					"' AND password = '" + contrasenya + "'");
			if(reg.next()) {
				nombre = reg.getString(3);
			}
			
		} catch (SQLException e) {
			System.out.println("Problema al conectar con la BD");
			e.printStackTrace();
		}
		return nombre;
	}
	
}
