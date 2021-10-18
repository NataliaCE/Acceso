package anexoII;

import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		AccesoBDatos abd = new AccesoBDatos();
		try {
			abd.conectar();
			//c
			System.out.println(abd.busquedaPorCodigo(100));
			System.out.println (abd.busquedaPorCodigo(7788));
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
