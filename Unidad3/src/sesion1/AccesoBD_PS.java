//EJERCICIO 2

package sesion1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccesoBD_PS {

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
	
	public String verificacion(String usuario, String contrasenya) {
		String nombre = null;
		try {
			String sql = "SELECT * FROM usuario WHERE username = ? AND password = ?";
			PreparedStatement consulta = conecta.prepareStatement(sql);
			consulta.setString(1, usuario);
			consulta.setString(2, contrasenya);
			ResultSet reg = consulta.executeQuery();
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
