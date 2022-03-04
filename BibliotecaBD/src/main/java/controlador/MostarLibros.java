package controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.AccesoBD;
import modelo.Libro;

@WebServlet("/MostrarLibros")
public class MostarLibros extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public MostarLibros() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Libro> lista = new ArrayList<Libro>();
		
		AccesoBD bd = new AccesoBD();
		bd.conectar();
		lista = bd.consultar();
	
		try {
			request.setAttribute("Libros", lista);
			request.getRequestDispatcher("MostrarLibros.jsp")
				.forward(request, response);
			
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		bd.desconectar();
		
	}
	
}
