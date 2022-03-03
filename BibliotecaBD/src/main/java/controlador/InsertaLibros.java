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

	public InsertaLibros() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String titulo = request.getParameter("titulo");
		String autor = request.getParameter("autor");
		boolean prestado;
		if(request.getParameter("prestado").equals("Prestado")) {
			prestado = true;
		} else {
			prestado = false;
		}
		
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
