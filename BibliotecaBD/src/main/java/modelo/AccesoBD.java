package modelo;

import java.sql.Connection;

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
	
}
