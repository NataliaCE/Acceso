package controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.AccesoBD;

@WebServlet("/InsertaLibros")
public class InsertaLibros extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public InsertaLibros() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		String idString = request.getParameter("id");
		String titulo = request.getParameter("titulo");
		String autor = request.getParameter("autor");
		boolean prestado = Boolean.parseBoolean(request.getParameter("prestado"));
		
		if(idString=="" || titulo=="" || autor=="") {
			try {
				request.getRequestDispatcher("InsercionVacia.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		} else {
			
			int id = Integer.parseInt(idString);
			
			AccesoBD db = new AccesoBD();
			db.conectar();
			
			if(db.insertar(id, titulo, autor, prestado)) {
				try {
					request.getRequestDispatcher("InsercionCorrecta.jsp").forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}
			else {
				try {
					request.getRequestDispatcher("InsercionFallida.jsp").forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}
			
			db.desconectar();
			
		}
		
	}

}
