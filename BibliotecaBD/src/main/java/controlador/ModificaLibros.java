package controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.AccesoBD;

@WebServlet("/ModificaLibros")
public class ModificaLibros extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	public ModificaLibros() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String titulo = request.getParameter("titulo");
		String autor = request.getParameter("autor");
		
		String parametro = request.getParameter("prestado");
		boolean prestado = Boolean.parseBoolean(parametro);	
		
		AccesoBD bd = new AccesoBD();
		bd.conectar();
		
		if(bd.modificar(id, titulo, autor, prestado)) {
			try {
				request.getRequestDispatcher("ModificacionCorrecta.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				request.getRequestDispatcher("ModificacionFallida.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		bd.desconectar();
	}

}
