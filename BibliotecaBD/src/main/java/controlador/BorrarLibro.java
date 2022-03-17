package controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.AccesoBD;

@WebServlet("/BorrarLibro")
public class BorrarLibro extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public BorrarLibro() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		AccesoBD bd = new AccesoBD();
		bd.conectar();
		
		if(bd.borrar(id)) {
			try {
				request.getRequestDispatcher("BorradoCorrecto.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				request.getRequestDispatcher("BorradoFallido.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		bd.desconectar();
	}
	
}
