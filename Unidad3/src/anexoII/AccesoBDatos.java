package anexoII;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
			PreparedStatement consulta = conecta.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
	
	public ArrayList<Empleado> busquedaPorOficio(String oficio) {
		ArrayList<Empleado> lista = new ArrayList<Empleado>();
		String sql = "SELECT * FROM emp WHERE job = ?";
		Empleado emp;
		try {
			PreparedStatement consulta = conecta.prepareStatement(sql);
			consulta.setString(1, oficio);
			ResultSet rs = consulta.executeQuery();
			while(rs.next()) {
				emp = new Empleado(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),
						rs.getDate(5), rs.getDouble(6), rs.getDouble(7), rs.getInt(8));
				lista.add(emp);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public ArrayList<Empleado> busquedaPorAntiguedad(Date fecha) {
		ArrayList<Empleado> lista = new ArrayList<Empleado>();
		String sql = "SELECT * FROM emp WHERE hiredate <= ?";
		Empleado emp;
		try {
			PreparedStatement consulta = conecta.prepareStatement(sql);
			consulta.setDate(1, fecha);
			ResultSet rs = consulta.executeQuery();
			while(rs.next()) {
				emp = new Empleado(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),
						rs.getDate(5), rs.getDouble(6), rs.getDouble(7), rs.getInt(8));
				lista.add(emp);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public int insertarEmpleado(Empleado emp) {
		String sql = "INSERT INTO emp VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		try {
			PreparedStatement consulta = conecta.prepareStatement(sql);
			consulta.setInt(1, emp.getNumEmpleado());
			consulta.setString(2, emp.getNombre());
			consulta.setString(3, emp.getTrabajo());
			consulta.setInt(4, emp.getManager());
			consulta.setDate(5, emp.getFechaContrato());
			consulta.setDouble(6, emp.getSalario());
			consulta.setDouble(7, emp.getComision());
			consulta.setInt(8, emp.getNumDepartamento());
			return consulta.executeUpdate();
			
		} catch (SQLException e) {
			return e.getErrorCode();
		}
	}
	
	public int actualizarSalario(int departamento, double porcentaje) {
		String sql = "UPDATE emp SET sal = ? * sal WHERE deptno = ?;";
		try {
			PreparedStatement consulta = conecta.prepareStatement(sql);
			consulta.setDouble(1, porcentaje + 1);
			consulta.setInt(2, departamento);
			return consulta.executeUpdate();
			
		} catch (SQLException e) {
			return e.getErrorCode();
		}
	}
	
	public int actualizarSalarioConTransacciones (int departamento, double porcentaje) {
		int resultado;
		String sql = "UPDATE emp SET sal = ? * sal WHERE deptno = ?;";
		try {
			conecta.setAutoCommit(false);
			PreparedStatement consulta = conecta.prepareStatement(sql);
			consulta.setDouble(1, porcentaje + 1);
			consulta.setInt(2, departamento);
			resultado = consulta.executeUpdate();
			conecta.commit();
		
		} catch (SQLException e) {
			resultado = e.getErrorCode();
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	public int borrarEmpleado(int numero) {
		String sql = "DELETE FROM emp WHERE empno = ?;";
		try {
			PreparedStatement consulta = conecta.prepareStatement(sql);
			consulta.setInt(1, numero);
			return consulta.executeUpdate();
			
		} catch (SQLException e) {
			return e.getErrorCode();
		}
		
	}
	
}
