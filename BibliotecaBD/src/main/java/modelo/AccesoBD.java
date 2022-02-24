package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccesoBD {

	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String database = "biblioteca";
	private static String hostname = "localhost";
	private static String port = "3306";
	private static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + 
			database + "?serverTimezone=Europe/Madrid";
    private static String username = "root";
    private static String password = "root";
    private Connection conecta;
    
    public void conectar() {
    	try {
			Class.forName(driver);
			conecta = DriverManager.getConnection(url, username, password);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }
    
    public void desconectar() {
    	conecta = null; 
    }
    
    public boolean insertar(int id, String titulo, String autor, boolean prestado) {
    	String sql = "INSERT INTO libros VALUES (?, ?, ?, ?)";
    	
		try {
			PreparedStatement inserta = conecta.prepareStatement(sql);
			inserta.setInt(1, id);
	    	inserta.setString(2, titulo);
	    	inserta.setString(3, autor);
	    	inserta.setBoolean(4, prestado);
	    	
	    	inserta.executeUpdate();
	    	return true;
	    	
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
    }
    
    public boolean modificar(int id, String titulo, String autor, boolean prestado ) {
    	String sql = "UPDATE libros SET titulo = ?, autor = ?, prestado = ? WHERE id = ?";
    	
    	try {
			PreparedStatement modifica = conecta.prepareStatement(sql);
			modifica.setString(1, titulo);
			modifica.setString(2, autor);
			modifica.setBoolean(3, prestado);
			modifica.setInt(4, id);
			
			modifica.executeUpdate();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}	
    }
    
    public boolean borrar(int id) {
    	String sql = "DELETE FROM libros WHERE id = ?";
    	
    	try {
			PreparedStatement borra = conecta.prepareStatement(sql);
			borra.setInt(1, id);
			
			borra.executeUpdate();
			return true;
    	
    	} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
    }
    
    public ArrayList<Libro> consultar() {
    	ArrayList<Libro> lista = new ArrayList<Libro>();
    	String sql = "SELECT id, titulo, autor, prestado FROM libros";
    	
    	try {
			PreparedStatement consulta = conecta.prepareStatement(sql);
			ResultSet rs = consulta.executeQuery();
			
			while(rs.next()) {
				Libro l = new Libro(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4));
				lista.add(l);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return lista;
    }
	
}
