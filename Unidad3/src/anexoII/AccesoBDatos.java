package anexoII;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccesoBDatos {

	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String database = "demodb";
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
	
	public void desconectar() throws SQLException, ClassNotFoundException {
		conecta.close();
	}
	
	public Empleado busquedaPorCodigo(int codigo) {
		String sql = "SELECT * FROM emp WHERE empno = ?";
		Empleado emp = null;
		try {
			PreparedStatement consulta = conecta.prepareStatement(sql);
			consulta.setInt(1, codigo);
			ResultSet rs = consulta.executeQuery();
			if(rs.next()) {
				emp = new Empleado(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),
						rs.getDate(5), rs.getDouble(6), rs.getDouble(7), rs.getInt(8));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return emp;
	}
	
}
